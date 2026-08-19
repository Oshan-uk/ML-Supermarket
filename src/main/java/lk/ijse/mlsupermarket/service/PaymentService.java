package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    public void savePayment(PaymentDTO paymentDTO);
    public List<PaymentDTO> getAllPayments();
    public PaymentDTO getPaymentByOrderId(long orderId);
}
