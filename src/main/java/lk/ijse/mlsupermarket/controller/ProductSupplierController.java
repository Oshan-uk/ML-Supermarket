package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.ProductSupplierDTO;
import lk.ijse.mlsupermarket.service.ProductSupplierService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-suppliers")
public class ProductSupplierController {
    private final ProductSupplierService productSupplierService;

    public ProductSupplierController(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveProductSupplier(@RequestBody ProductSupplierDTO dto) {
        productSupplierService.saveProductSupplier(dto);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateProductSupplier(@RequestBody ProductSupplierDTO dto) {
        productSupplierService.updateProductSupplier(dto);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllProductSuppliers() {
        List<ProductSupplierDTO> productSupplierList = productSupplierService.getAllProductSuppliers();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, productSupplierList, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getSuppliersByProduct(@PathVariable long productId) {
        List<ProductSupplierDTO> productSupplierList = productSupplierService.getSuppliersByProduct(productId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, productSupplierList, ResponseMessage.SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{productSupplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteProductSupplier(@PathVariable long productSupplierId) {
        productSupplierService.deleteProductSupplier(productSupplierId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }
}
