package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.dto.ProductDTO;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.status.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_CategoryId(Long categoryId);

    @Query(value = "SELECT new lk.ijse.mlsupermarket.dto.ProductDTO(p.productId, p.productName, p.unitPrice, p.barcode, p.status, p.category.categoryId) FROM Product p WHERE (?1 IS NULL OR p.productName LIKE %?1%)")
    List<ProductDTO> searchProductsByName(String productName);

    @Query(value = "SELECT new lk.ijse.mlsupermarket.dto.ProductDTO(p.productId, p.productName, p.unitPrice, p.barcode, p.status, p.category.categoryId) FROM Product p WHERE (?1 IS NULL OR p.category.categoryName LIKE %?1%) AND (?2 IS NULL OR p.unitPrice >= ?2) AND (?3 IS NULL OR p.unitPrice <= ?3) AND (?4 IS NULL OR p.status = ?4)")
    List<ProductDTO> filterProducts(@Param("categoryName") String categoryName, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("status") ProductStatus status);
}