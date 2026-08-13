package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.dto.ProductDTO;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.entity.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {

    List<ProductSupplier> findBySupplier_SupplierId(Long supplierId);

}
