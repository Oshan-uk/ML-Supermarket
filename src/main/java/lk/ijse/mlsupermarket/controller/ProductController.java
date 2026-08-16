package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.ProductDTO;
import lk.ijse.mlsupermarket.service.ProductService;
import lk.ijse.mlsupermarket.status.ProductStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveProduct(@RequestBody ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, products, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getProductById(@PathVariable long productId) {
        ProductDTO product = productService.getProductById(productId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, product, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/search/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse searchProductsByName(@PathVariable String productName) {
        List<ProductDTO> products = productService.searchProductsByName(productName);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, products, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterProducts(@RequestParam(required = false) String categoryName, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) ProductStatus status) {
        List<ProductDTO> products = productService.filterProducts(categoryName, minPrice, maxPrice, status);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, products, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PatchMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse changeProductStatus(@PathVariable long productId) {
        productService.changeProductStatus(productId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }
}