package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.dto.SupplierDTO;
import lk.ijse.mlsupermarket.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = " SELECT new lk.ijse.mlsupermarket.dto.SupplierDTO(s.supplierId,s.supplierName, s.contact, s.email, s.address) FROM Supplier s WHERE(?1 IS NULL OR s.supplierName LIKE %?1%)")
    List<SupplierDTO> searchSuppliersByName(String supplierName);
}
