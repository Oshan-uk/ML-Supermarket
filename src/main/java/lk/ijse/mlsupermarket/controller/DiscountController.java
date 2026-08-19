package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.DiscountDTO;
import lk.ijse.mlsupermarket.service.DiscountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveDiscount(@RequestBody DiscountDTO discountDTO) {
        discountService.saveDiscount(discountDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDiscount(@RequestBody DiscountDTO discountDTO) {
        discountService.updateDiscount(discountDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.getAllDiscounts();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, discounts, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getDiscountsByProduct(@PathVariable long productId) {
        List<DiscountDTO> discounts = discountService.getDiscountsByProduct(productId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, discounts, ResponseMessage.SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{discountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteDiscount(@PathVariable long discountId) {
        discountService.deleteDiscount(discountId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }
}