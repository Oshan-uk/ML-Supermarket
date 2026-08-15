package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.SupplierDTO;
import lk.ijse.mlsupermarket.service.SupplierService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveSupplier(@RequestBody SupplierDTO supplierDTO) {
        supplierService.saveSupplier(supplierDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateSupplier(@RequestBody SupplierDTO supplierDTO) {
        supplierService.updateSupplier(supplierDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, suppliers, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getSupplierById(@PathVariable long supplierId) {
        SupplierDTO supplier = supplierService.getSupplierById(supplierId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, supplier, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/search/{supplierName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse searchSuppliersByName(@PathVariable String supplierName) {
        List<SupplierDTO> suppliers = supplierService.searchSuppliersByName(supplierName);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, suppliers, ResponseMessage.SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteSupplier(@PathVariable long supplierId) {
        supplierService.deleteSupplier(supplierId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }
}