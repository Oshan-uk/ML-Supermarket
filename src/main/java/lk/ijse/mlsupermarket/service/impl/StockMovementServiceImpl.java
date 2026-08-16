package lk.ijse.mlsupermarket.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.mlsupermarket.dto.StockMovementDTO;
import lk.ijse.mlsupermarket.entity.Inventory;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.entity.StockMovement;
import lk.ijse.mlsupermarket.repository.InventoryRepository;
import lk.ijse.mlsupermarket.repository.ProductRepository;
import lk.ijse.mlsupermarket.repository.StockMovementRepository;
import lk.ijse.mlsupermarket.service.StockMovementService;
import lk.ijse.mlsupermarket.status.MovementType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class StockMovementServiceImpl implements StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    @Transactional
    public void recordMovement(StockMovementDTO stockMovementDTO) {
        log.info("Execute recordMovement()");
        try {
            Optional<Product> optionalProduct = productRepository.findById(stockMovementDTO.getProductId());
            if (optionalProduct.isEmpty()) throw new RuntimeException("Sorry, related product is not found");

            Optional<Inventory> optionalInventory = inventoryRepository.findByProduct_ProductId(stockMovementDTO.getProductId());
            if (optionalInventory.isEmpty()) throw new RuntimeException("Sorry, inventory record not found for this product");

            Inventory inventory = optionalInventory.get();

            int qty = stockMovementDTO.getQuantity();

            if (stockMovementDTO.getMovementType() == MovementType.IN) {
                inventory.setQuantity(inventory.getQuantity() + qty);

            } else if (stockMovementDTO.getMovementType() == MovementType.OUT) {

                if (inventory.getQuantity() < qty) throw new RuntimeException("Sorry, insufficient stock");
                inventory.setQuantity(inventory.getQuantity() - qty);

            } else {
                inventory.setQuantity(inventory.getQuantity() + qty);
            }
            inventoryRepository.save(inventory);

            StockMovement movement = new StockMovement();
            movement.setProduct(optionalProduct.get());
            movement.setMovementType(stockMovementDTO.getMovementType());
            movement.setQuantity(stockMovementDTO.getQuantity());
            movement.setMovementDate(stockMovementDTO.getMovementDate());
            movement.setReason(stockMovementDTO.getReason());

            stockMovementRepository.save(movement);
        } catch (Exception e) {
            log.error("Error in recordMovement()");
            throw e;
        }
    }

    @Override
    public List<StockMovementDTO> getAllMovements() {
        log.info("Execute getAllMovements()");
        try {

        } catch (Exception e) {
            log.error("Error in getAllMovements()");
            throw e;
        }
        return null;
    }

    @Override
    public List<StockMovementDTO> getMovementsByProduct(long productId) {
        log.info("Execute getMovementsByProduct()");
        try {

        } catch (Exception e) {
            log.error("Error in getMovementsByProduct()");
            throw e;
        }
        return  null;
    }
}
