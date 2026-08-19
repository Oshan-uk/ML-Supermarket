package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.PaymentDTO;
import lk.ijse.mlsupermarket.entity.Orders;
import lk.ijse.mlsupermarket.entity.Payment;
import lk.ijse.mlsupermarket.repository.OrderRepository;
import lk.ijse.mlsupermarket.repository.PaymentRepository;
import lk.ijse.mlsupermarket.service.PaymentService;
import lk.ijse.mlsupermarket.status.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public void savePayment(PaymentDTO paymentDTO) {
        log.info("Executes savePayment()");
        try{
            Optional<Orders> optionalOrders = orderRepository.findById(paymentDTO.getOrderId());
            if(optionalOrders.isEmpty()) throw new RuntimeException("Sorry, related order is not found!");

            Optional<Payment> optionalPayment = paymentRepository.findByOrder_OrderID(paymentDTO.getOrderId());
            if(optionalPayment.isPresent()) throw  new RuntimeException("Sorry, Payment Already Existing!");

            Payment payment = new Payment();
            payment.setOrder(optionalOrders.get());
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            payment.setPaymentDate(LocalDate.now());

            paymentRepository.save(payment);

        } catch (Exception e) {
            log.error("Error in savePayment()");
            throw e;
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        log.info("Execute getAllPayments()");

        try {
            List<Payment> payments = paymentRepository.findAll();
            List<PaymentDTO> paymentDTOList = new ArrayList<>();

            for (Payment payment : payments) {
                PaymentDTO paymentDTO = new PaymentDTO(
                        payment.getPaymentId(),
                        payment.getOrder().getOrderId(),
                        payment.getAmount(),
                        payment.getPaymentMethod(),
                        payment.getPaymentStatus(),
                        payment.getPaymentDate()
                );
                paymentDTOList.add(paymentDTO);
            }
            return paymentDTOList;
        } catch (Exception e) {
            log.error("Error in getAllPayments()", e);
            throw e;
        }
    }

    @Override
    public PaymentDTO getPaymentByOrderId(long orderId) {
        log.info("Execute getPaymentByOrderId()");

        try{
            Optional<Payment> optionalPayment = paymentRepository.findByOrder_OrderID(orderId);
            if(optionalPayment.isEmpty()) throw new RuntimeException("Sorry, No payment Found for this Order!");

            Payment payment = optionalPayment.get();
            return new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getOrder().getOrderId(),
                    payment.getAmount(),
                    payment.getPaymentMethod(),
                    payment.getPaymentStatus(),
                    payment.getPaymentDate()
            );
        } catch (Exception e) {
            log.error("Error in getPaymentByOrderId()");
            throw e;
        }
    }
}
