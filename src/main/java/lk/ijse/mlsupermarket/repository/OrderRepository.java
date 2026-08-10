package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
