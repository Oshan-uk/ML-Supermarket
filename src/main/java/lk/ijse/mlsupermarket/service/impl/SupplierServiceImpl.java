package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.SupplierDTO;
import lk.ijse.mlsupermarket.entity.Supplier;
import lk.ijse.mlsupermarket.repository.ProductSupplierRepository;
import lk.ijse.mlsupermarket.repository.SupplierRepository;
import lk.ijse.mlsupermarket.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductSupplierRepository productSupplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ProductSupplierRepository productSupplierRepository){
        this.supplierRepository = supplierRepository;
        this.productSupplierRepository = productSupplierRepository;
    }

    @Override
    public void saveSupplier(SupplierDTO supplierDTO){
        log.info("Executes saveSupplier()");

        try{
            Supplier supplier = new Supplier();
            supplier.setSupplierName(supplierDTO.getSupplierName());
            supplier.setContact(supplierDTO.getContact());
            supplier.setEmail(supplierDTO.getEmail());
            supplier.setAddress(supplierDTO.getAddress());

            supplierRepository.save(supplier);

        } catch (Exception e) {
            log.error("Error in saveSupplier()");
            throw e;
        }
    }

    @Override
    public void updateSupplier(SupplierDTO supplierDTO) {

        log.info("Execute updateSupplier()");

        try{
            Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierDTO.getSupplierId());

            if(optionalSupplier.isEmpty()){
                throw new RuntimeException("Sorry, related supplier is not found ");

            }
            Supplier supplier = optionalSupplier.get();

            supplier.setSupplierName(supplierDTO.getSupplierName());
            supplier.setContact(supplierDTO.getContact());
            supplier.setEmail(supplierDTO.getEmail());
            supplier.setAddress(supplierDTO.getAddress());

            supplierRepository.save(supplier);

        }catch (Exception e){
            log.error("Error in updateSupplier()");
            throw e;
        }

    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        log.info("Execute getAllSuppliers()");

        try {
        }catch (Exception e){
            throw e;
        }
        return null;
    }

    @Override
    public SupplierDTO getSupplierById(long supplierId) {
        return null;
    }

    @Override
    public List<SupplierDTO> searchSuppliersByName(String supplierName) {
        return List.of();
    }

    @Override
    public void deleteSupplier(long supplierId) {

    }
}
