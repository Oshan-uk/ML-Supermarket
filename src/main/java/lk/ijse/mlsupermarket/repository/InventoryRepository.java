package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct_ProductId(Long productId);
    List<Inventory>findByQuantityLessThanEqual(Integer reorderLevel);

}
