package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.StockMovementDTO;

import java.util.List;

public interface StockMovementService {
    public void recordMovement(StockMovementDTO stockMovementDTO);
    public List<StockMovementDTO> getAllMovements();
    public List<StockMovementDTO> getMovementsByProduct(long productId);
}
