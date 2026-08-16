package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO getCustomerById(long customerId);
    public List<CustomerDTO> searchCustomersByName(String customerName);
    public void deleteCustomer(long customerId);
}