package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.InventoryDTO;
import lk.ijse.mlsupermarket.dto.response.StockLevelDTO;
import lk.ijse.mlsupermarket.entity.Inventory;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.repository.InventoryRepository;
import lk.ijse.mlsupermarket.repository.ProductRepository;
import lk.ijse.mlsupermarket.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void saveInventory(InventoryDTO inventoryDTO) {
        log.info("Execute saveInventory()");

        try{
            Optional<Product> optionalProduct = productRepository.findById((inventoryDTO.getProductId()));
            if(optionalProduct.isEmpty()) throw new RuntimeException("Sorry, Related Product is Not Found!");

            Optional<Inventory> existing = inventoryRepository.findByProduct_ProductId(inventoryDTO.getProductId());
            if(existing.isPresent()) throw new RuntimeException("Sorry, Inventory Already existing for this product.");

            Inventory inventory = new Inventory();
            inventory.setProduct(optionalProduct.get());
            inventory.setQuantity(inventoryDTO.getQuantity());
            inventory.setReorderLevel(inventoryDTO.getReorderLevel());

            inventoryRepository.save(inventory);

        } catch (Exception e) {
            log.error("Error in saveInventory()");
            throw e;
        }
    }

    @Override
    public void updateInventoryQuantity(long productId, int quantity) {
        log.info("Execute updateInventory()");
        try{
            Optional<Inventory> optionalInventory = inventoryRepository.findByProduct_ProductId(productId);
            if(optionalInventory.isEmpty()) throw new RuntimeException("Sorry, Inventory record Not Found!");

            Inventory inventory = optionalInventory.get();
            inventory.setQuantity(quantity);

            inventoryRepository.save(inventory);
        } catch (Exception e) {
            log.error("Error in updateInventory()");
            throw e;
        }
    }

    @Override
    public List<StockLevelDTO> getAllStockLevels() {
        log.info("Execute getAllStockLevels()");
        try{
            /////////////////////////////////////////////////////////
                              /* Do this Later */
            ////////////////////////////////////////////////////////
        } catch (Exception e) {
            log.error("Error in getAllStockLevels()");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public InventoryDTO getInventoryByProductId(long productId) {
        log.info("Execute getInventoryByProductId()");
        try{
            Optional<Inventory> optionalInventory = inventoryRepository.findByProduct_ProductId(productId);
            if(optionalInventory.isEmpty()) throw new RuntimeException("Sorry, Inventory record Not Found!");

            Inventory inventory = optionalInventory.get();
            return new InventoryDTO(
                    inventory.getInventoryId(),
                    inventory.getProduct().getProductId(),
                    inventory.getQuantity(),
                    inventory.getReorderLevel()
            );

        } catch (Exception e) {
            log.error("Error in  getInventoryByProductId()");
            throw e;
        }
    }

    @Override
    public List<InventoryDTO> getLowStockItems() {
        log.info("Execute getLowStockItems()");
        try{
            /////////////////////////////////////////////////////////
                              /* Do this Later */
            ////////////////////////////////////////////////////////
        } catch (Exception e) {
            log.error("Error in getLowStockItems()");
            throw new RuntimeException(e);
        }
        return List.of();
    }
}
