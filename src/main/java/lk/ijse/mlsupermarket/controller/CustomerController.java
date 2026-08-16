package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.CustomerDTO;
import lk.ijse.mlsupermarket.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, customers, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getCustomerById(@PathVariable long customerId) {
        CustomerDTO customer = customerService.getCustomerById(customerId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, customer, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/search/{customerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse searchCustomersByName(@PathVariable String customerName) {
        List<CustomerDTO> customers = customerService.searchCustomersByName(customerName);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, customers, ResponseMessage.SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteCustomer(@PathVariable long customerId) {
        customerService.deleteCustomer(customerId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }
}
