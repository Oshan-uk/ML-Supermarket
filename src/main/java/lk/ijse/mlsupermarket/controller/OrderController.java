package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.OrderDTO;
import lk.ijse.mlsupermarket.service.OrderService;
import lk.ijse.mlsupermarket.status.OrderProcess;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveOrder(@RequestBody OrderDTO orderDTO){
        orderService.saveOrder(orderDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PatchMapping(value = "/{orderId}/discount", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse applyDiscount(@PathVariable long orderId, @RequestParam double discountPercentage){
        orderService.applyDiscounts(orderId,discountPercentage);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,ResponseMessage.SUCCESS_MESSAGE);
    }

    @PatchMapping(value = "/{orderId}/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse processOrder(@PathVariable long orderId, @RequestParam OrderProcess orderProcess){
        orderService.processOrder(orderId,orderProcess);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllOrders(){
        List<OrderDTO> orders = orderService.getAllOrders();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, orders, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getOrderById(@PathVariable long orderId){
        OrderDTO order = orderService.getOrderById(orderId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,order,ResponseMessage.SUCCESS_MESSAGE);
    }
}
