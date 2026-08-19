package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByProduct_ProductId(Long productId);
}
