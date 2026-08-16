package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.StockMovementDTO;
import lk.ijse.mlsupermarket.service.StockMovementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-movements")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse recordMovement(@RequestBody StockMovementDTO stockMovementDTO) {
        stockMovementService.recordMovement(stockMovementDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllMovements() {
        List<StockMovementDTO> stockMovements = stockMovementService.getAllMovements();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, stockMovements, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getMovementsByProduct(@PathVariable long productId) {
        List<StockMovementDTO> stockMovements = stockMovementService.getMovementsByProduct(productId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, stockMovements, ResponseMessage.SUCCESS_MESSAGE);
    }
}

