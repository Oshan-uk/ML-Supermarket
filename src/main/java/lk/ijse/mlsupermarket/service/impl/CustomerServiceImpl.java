package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.CustomerDTO;
import lk.ijse.mlsupermarket.entity.Customer;
import lk.ijse.mlsupermarket.repository.CustomerRepository;
import lk.ijse.mlsupermarket.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        log.info("Execute saveCustomer()");
        try {
            Customer customer = new Customer();
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setContact(customerDTO.getContact());
            customer.setEmail(customerDTO.getEmail());

            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("Error in saveCustomer()");
            throw e;
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        log.info("Execute updateCustomer()");
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerDTO.getCustomerId());
            if (optionalCustomer.isEmpty()) throw new RuntimeException("Sorry, related customer is not found");

            Customer customer = optionalCustomer.get();
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setContact(customerDTO.getContact());
            customer.setEmail(customerDTO.getEmail());

            customerRepository.save(customer);
        } catch (Exception e) {
            log.error("Error in updateCustomer()");
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        log.info("Execute getAllCustomers()");
        try {

        } catch (Exception e) {
            log.error("Error in getAllCustomers() ");
            throw e;
        }
        return null;
    }

    @Override
    public CustomerDTO getCustomerById(long customerId) {
        log.info("Execute getCustomerById()");
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCustomer.isEmpty()) throw new RuntimeException("Sorry, related customer is not found");

            Customer c = optionalCustomer.get();
            return new CustomerDTO(c.getCustomerId(), c.getCustomerName(), c.getContact(), c.getEmail());
        } catch (Exception e) {
            log.error("Error in getCustomerById()");
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> searchCustomersByName(String customerName) {
        log.info("Execute searchCustomersByName()");
        try {
            return customerRepository.searchCustomersByName(customerName);
        } catch (Exception e) {
            log.error("Error in searchCustomersByName()");
            throw e;
        }
    }

    @Override
    public void deleteCustomer(long customerId) {
        log.info("Execute deleteCustomer()");
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCustomer.isEmpty()) throw new RuntimeException("Sorry, related customer is not found");

            customerRepository.deleteById(customerId);
        } catch (Exception e) {
            log.error("Error in deleteCustomer()");
            throw e;
        }
    }
}