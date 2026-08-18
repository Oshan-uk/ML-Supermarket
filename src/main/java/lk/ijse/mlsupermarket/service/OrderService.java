package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.OrderDTO;
import lk.ijse.mlsupermarket.status.OrderProcess;

import java.util.List;

public interface OrderService {
    public void saveOrder(OrderDTO orderDTO);
    public void applyDiscounts(long orderId, double discountPercentage);
    public void processOrder(long orderId, OrderProcess orderProcess);
    public List<OrderDTO> getAllOrders();
    public OrderDTO getOrderById(long orderId);
}
