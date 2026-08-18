package lk.ijse.mlsupermarket.service.impl;

import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import lk.ijse.mlsupermarket.dto.OrderDTO;
import lk.ijse.mlsupermarket.dto.OrderItemDTO;
import lk.ijse.mlsupermarket.entity.*;
import lk.ijse.mlsupermarket.repository.*;
import lk.ijse.mlsupermarket.service.OrderService;
import lk.ijse.mlsupermarket.status.MovementType;
import lk.ijse.mlsupermarket.status.OrderProcess;
import lk.ijse.mlsupermarket.status.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final StockMovementRepository stockMovementRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, UserRepository userRepository, ProductRepository productRepository, InventoryRepository inventoryRepository, StockMovementRepository stockMovementRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        log.info("Execute saveOrder()",orderDTO);

        try{
            Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerId());
            if(optionalCustomer.isEmpty()) throw new RuntimeException("Sorry, related customer is not found!");

            Optional<User> optionalUser = userRepository.findById(orderDTO.getUserId());
            if(optionalUser.isEmpty()) throw new RuntimeException("Sorry, Related User is not found!");

            if(orderDTO.getItemList() == null || orderDTO.getItemList().isEmpty()) {
                throw new RuntimeException("Sorry, Order must contain at least one item!");
            }

            Orders orders = new Orders();
            orders.setOrderDate(LocalDate.now());
            orders.setDiscount(0.0);
            orders.setOrderStatus(OrderStatus.PENDING);
            orders.setCustomer(optionalCustomer.get());
            orders.setUser(optionalUser.get());

            Orders saveOrder = orderRepository.save(orders);

            double total = 0.0;
            List<OrderItem> orderItems = new ArrayList<>();

            for (OrderItemDTO itemDTO : orderDTO.getItemList()){
                Optional<Product> optionalProduct = productRepository.findById(itemDTO.getProductId());
                if(optionalProduct.isEmpty()) throw new RuntimeException("Sorry, Related Product is Not Found!");
                Product product = optionalProduct.get();

                Optional<Inventory> optionalInventory = inventoryRepository.findByProduct_ProductId(product.getProductId());
                if(optionalInventory.isEmpty()) throw new RuntimeException("Sorry No inventory record for - " + product.getProductName());
                Inventory inventory = optionalInventory.get();

                if(inventory.getQuantity() < itemDTO.getQuantity()){
                    throw new RuntimeException("Sorry, insufficient stock for "+ product.getProductName()+" (Available - "+inventory.getQuantity());
                }


                inventory.setQuantity(inventory.getQuantity() - itemDTO.getQuantity());
                inventoryRepository.save(inventory);

                StockMovement movement = new StockMovement();
                movement.setProduct(product);
                movement.setMovementType(MovementType.OUT);
                movement.setQuantity(itemDTO.getQuantity());
                movement.setMovementDate(LocalDate.now());
                movement.setReason("Sale - Order #" + saveOrder.getOrderId());
                stockMovementRepository.save(movement);


                double subtotal = product.getUnitPrice() * itemDTO.getQuantity();

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(saveOrder);
                orderItem.setProduct(product);
                orderItem.setQuantity(itemDTO.getQuantity());
                orderItem.setUnitPrice(product.getUnitPrice());
                orderItem.setSubtotal(subtotal);

                orderItems.add(orderItem);
                total += subtotal;
            }

            orderItemRepository.saveAll(orderItems);

            saveOrder.setTotal(total);
            orderRepository.save(saveOrder);
        } catch (Exception e) {
            log.error("Error in saveOrder()");
            throw e;
        }
    }

    @Override
    @Transactional
    public void applyDiscounts(long orderId, double discountPercentage) {
        log.info("Executes applyDiscounts()");

        try{
            Optional<Orders> optionalOrders = orderRepository.findById(orderId);
            if(optionalOrders.isEmpty()) throw new RuntimeException("Sorry, related Order is not found!");

            Orders orders = optionalOrders.get();
            double discountAmount = orders.getTotal() * (discountPercentage/100);
            orders.setTotal(orders.getTotal() - discountAmount);
            orders.setDiscount(discountPercentage);

            orderRepository.save(orders);
        } catch (Exception e) {
            log.error("Error in applyDiscounts()");
            throw e;
        }
    }

    @Override
    @Transactional
    public void processOrder(long orderId, OrderProcess orderProcess) {
        log.info("Execute processOrder()");

        try{
            Optional<Orders> optionalOrders = orderRepository.findById(orderId);
            if(optionalOrders.isEmpty()) throw new RuntimeException("Sorry, related order is not found!");

            Orders orders = optionalOrders.get();

            if(orderProcess == OrderProcess.CONTINUE){
                orders.setOrderStatus(OrderStatus.COMPLETED);
            } else if (orderProcess == OrderProcess.CANCEL) {
                orders.setOrderStatus(OrderStatus.CANCELLED);

                List<OrderItem> items = orderItemRepository.findByOrder_OrderId(orderId);

                for(OrderItem item : items){
                    Optional<Inventory> optionalInventory = inventoryRepository.findByProduct_ProductId(item.getProduct().getProductId());
                    if(optionalInventory.isPresent()){
                        Inventory inventory = optionalInventory.get();
                        inventory.setQuantity(inventory.getQuantity() + item.getQuantity());

                        inventoryRepository.save(inventory);

                        StockMovement movement = new StockMovement();
                        movement.setProduct(item.getProduct());
                        movement.setMovementType(MovementType.IN);
                        movement.setQuantity(item.getQuantity());
                        movement.setMovementDate(LocalDate.now());
                        movement.setReason("Order #" + orderId + " cancelled - STORE RESTORED");

                        stockMovementRepository.save(movement);
                    }
                }

            } else throw new RuntimeException("Sorry, Invalid Order Process!!!");

            orderRepository.save(orders);
        } catch (Exception e) {
            log.error("Error in processOrder()");
            throw e;
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        log.info("Execute getAllOrders()");

        try {
            List<Orders> orders = orderRepository.findAll();
            List<OrderDTO> orderDTOList = new ArrayList<>();

            for (Orders order : orders) {
                OrderDTO orderDTO = new OrderDTO(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getDiscount(),
                        order.getTotal(),
                        order.getOrderStatus(),
                        order.getCustomer().getCustomerId(),
                        order.getUser().getUserId(),
                        null
                );
                orderDTOList.add(orderDTO);
            }
            return orderDTOList;

        } catch (Exception e) {
            log.error("Error in getAllOrders()", e);
            throw e;
        }
    }

    @Override
    public OrderDTO getOrderById(long orderId) {
        log.info("Execute getOrderById()", orderId);
        try {
            Optional<Orders> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isEmpty()) throw new RuntimeException("Sorry, related order is not found");

            return mapToDTO(optionalOrder.get());
        } catch (Exception e) {
            log.error("Error in getOrderById()");
            throw e;
        }
    }


    private OrderDTO mapToDTO(Orders order) {

        List<OrderItem> orderItems = orderItemRepository.findByOrder_OrderId(order.getOrderId());
        List<OrderItemDTO> itemDTOs = new ArrayList<>();

        for (OrderItem item : orderItems) {
            OrderItemDTO itemDTO = new OrderItemDTO(
                    item.getOrderItemId(),
                    order.getOrderId(),
                    item.getProduct().getProductId(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getSubtotal()
            );

            itemDTOs.add(itemDTO);
        }
        return new OrderDTO(
                order.getOrderId(),
                order.getOrderDate(),
                order.getDiscount(),
                order.getTotal(),
                order.getOrderStatus(),
                order.getCustomer().getCustomerId(),
                order.getUser().getUserId(),
                itemDTOs
        );
    }
}
