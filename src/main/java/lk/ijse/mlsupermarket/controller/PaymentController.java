package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.PaymentDTO;
import lk.ijse.mlsupermarket.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse savePayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.savePayment(paymentDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, payments, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getPaymentByOrderId(@PathVariable long orderId) {
        PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, payment, ResponseMessage.SUCCESS_MESSAGE);
    }
}