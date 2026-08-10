package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSupplierRepository extends JpaRepository<Product, Long> {
}
