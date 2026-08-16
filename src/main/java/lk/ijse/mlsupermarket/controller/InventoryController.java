package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.InventoryDTO;
import lk.ijse.mlsupermarket.dto.response.StockLevelDTO;
import lk.ijse.mlsupermarket.service.InventoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveInventory(@RequestBody InventoryDTO inventoryDTO){
        inventoryService.saveInventory(inventoryDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllStockLevels() {
        List<StockLevelDTO> levels = inventoryService.getAllStockLevels();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, levels, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getInventoryByProductId(@PathVariable long productId){
        InventoryDTO inventoryDTO = inventoryService.getInventoryByProductId(productId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,inventoryDTO,ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/lowStock", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getLowStockItems(){
        List<InventoryDTO> lowStock = inventoryService.getLowStockItems();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,lowStock,ResponseMessage.SUCCESS_MESSAGE);
    }

    @PatchMapping(value= "/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateInventoryQuantity(@PathVariable long productId, @RequestParam int quantity){
        inventoryService.updateInventoryQuantity(productId, quantity);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS,ResponseMessage.SUCCESS_MESSAGE);
    }
}
