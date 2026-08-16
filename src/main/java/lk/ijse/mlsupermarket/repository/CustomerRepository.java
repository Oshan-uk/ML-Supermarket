package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.dto.CustomerDTO;
import lk.ijse.mlsupermarket.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT new lk.ijse.mlsupermarket.dto.CustomerDTO(c.customerId, c.customerName, c.contact, c.email) FROM Customer c WHERE (?1 IS NULL OR c.customerName LIKE %?1%)")
    List<CustomerDTO> searchCustomersByName(String customerName);
}
