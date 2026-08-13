package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.SupplierDTO;
import lk.ijse.mlsupermarket.entity.ProductSupplier;
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

        log.info("Execute getSupplierById()");

        try {

            Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);

            if (optionalSupplier.isEmpty()) {
                throw new RuntimeException("Sorry, related supplier is not found");
            }

            Supplier s = optionalSupplier.get();
            return new SupplierDTO(s.getSupplierId(), s.getSupplierName(), s.getContact(), s.getEmail(), s.getAddress());

        } catch (Exception e) {
            log.error("Error in getSupplierById()" );
            throw e;
        }
    }

    @Override
    public List<SupplierDTO> searchSuppliersByName(String supplierName) {

        log.info("Execute searchSuppliersByName() ");

        try {
            return supplierRepository.searchSuppliersByName(supplierName);

        } catch (Exception e) {
            log.error("Error in searchSuppliersByName() ");
            throw e;
        }
    }

    @Override
    public void deleteSupplier(long supplierId) {

        log.info("Execute deleteSupplier()");
        try {
            Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
            if (optionalSupplier.isEmpty()) {
                throw new RuntimeException("Sorry, related supplier is not found");
            }

            List<ProductSupplier> linkedRecords = productSupplierRepository.findBySupplier_SupplierId(supplierId);
            if (!linkedRecords.isEmpty()) {
                throw new RuntimeException("Sorry, cannot delete supplier — it is still linked to " + linkedRecords.size() + " product(s)");
            }

            supplierRepository.deleteById(supplierId);

        } catch (Exception e) {
            log.error("Error in deleteSupplier() : " + e.getMessage());
            throw e;
        }
    }
}
