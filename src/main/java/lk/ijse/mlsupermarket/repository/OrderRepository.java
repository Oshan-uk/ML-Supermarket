package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.dto.response.SalesReportDTO;
import lk.ijse.mlsupermarket.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT new lk.ijse.mlsupermarket.dto.response.SalesReportDTO(o.orderDate, o.orderId, o.customer.customerName, o.orderStatus, o.total) FROM Orders o WHERE (?1 IS NULL OR o.orderDate >= ?1) AND (?2 IS NULL OR o.orderDate <= ?2)")
    List<SalesReportDTO> getSalesReport(java.time.LocalDate startDate, java.time.LocalDate endDate);
}
