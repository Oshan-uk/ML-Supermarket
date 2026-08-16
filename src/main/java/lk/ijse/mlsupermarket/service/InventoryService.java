package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.InventoryDTO;
import lk.ijse.mlsupermarket.dto.response.StockLevelDTO;

import java.util.List;

public interface InventoryService {
    public void saveInventory(InventoryDTO inventoryDTO);
    public void updateInventoryQuantity(long productId, int quantity);
    public List<StockLevelDTO> getAllStockLevels();
    public InventoryDTO getInventoryByProductId(long productId);
    public List<InventoryDTO> getLowStockItems();
}
