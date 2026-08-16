package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.ProductSupplierDTO;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.entity.ProductSupplier;
import lk.ijse.mlsupermarket.entity.Supplier;
import lk.ijse.mlsupermarket.repository.ProductRepository;
import lk.ijse.mlsupermarket.repository.ProductSupplierRepository;
import lk.ijse.mlsupermarket.repository.SupplierRepository;
import lk.ijse.mlsupermarket.service.ProductSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductSupplierServiceImpl implements ProductSupplierService {
    private final ProductSupplierRepository productSupplierRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductSupplierServiceImpl(ProductSupplierRepository productSupplierRepository, ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productSupplierRepository = productSupplierRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveProductSupplier(ProductSupplierDTO dto) {
        log.info("Execute saveProductSupplier()");
        try {
            Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
            if (optionalProduct.isEmpty()) throw new RuntimeException("Sorry, related product is not found");

            Optional<Supplier> optionalSupplier = supplierRepository.findById(dto.getSupplierId());
            if (optionalSupplier.isEmpty()) throw new RuntimeException("Sorry, related supplier is not found");

            ProductSupplier productSupplier = new ProductSupplier();
            productSupplier.setProduct(optionalProduct.get());
            productSupplier.setSupplier(optionalSupplier.get());
            productSupplier.setCostPrice(dto.getCostPrice());

            productSupplierRepository.save(productSupplier);
        } catch (Exception e) {
            log.error("Error in saveProductSupplier()");
            throw e;
        }
    }

    @Override
    public void updateProductSupplier(ProductSupplierDTO dto) {
        log.info("Execute updateProductSupplier()");
        try {
            Optional<ProductSupplier> optionalProductSupplier = productSupplierRepository.findById(dto.getProductSupplierId());
            if (optionalProductSupplier.isEmpty()) throw new RuntimeException("Sorry, related record is not found");

            ProductSupplier productSupplier = optionalProductSupplier.get();
            productSupplier.setCostPrice(dto.getCostPrice());

            productSupplierRepository.save(productSupplier);
        } catch (Exception e) {
            log.error("Error in updateProductSupplier()");
            throw e;
        }
    }

    @Override
    public List<ProductSupplierDTO> getAllProductSuppliers() {
        log.info("Execute getAllProductSuppliers()");
        try {
            /////////////////////////////////////////////////////////
                                /* Do this Later */
            ////////////////////////////////////////////////////////
        } catch (Exception e) {
            log.error("Error in getAllProductSuppliers()");
            throw e;
        }
        return null;
    }

    @Override
    public List<ProductSupplierDTO> getSuppliersByProduct(long productId) {
        log.info("Execute getSuppliersByProduct()");
        try {
            /////////////////////////////////////////////////////////
                                /* Do this Later */
            ////////////////////////////////////////////////////////
        } catch (Exception e) {
            log.error("Error in getSuppliersByProduct()");
            throw e;
        }
        return null;
    }

    @Override
    public void deleteProductSupplier(long productSupplierId) {
        log.info("Execute deleteProductSupplier()");
        try {
            Optional<ProductSupplier> optionalProductSupplier = productSupplierRepository.findById(productSupplierId);
            if (optionalProductSupplier.isEmpty()) throw new RuntimeException("Sorry, related record is not found");

            productSupplierRepository.deleteById(productSupplierId);
        } catch (Exception e) {
            log.error("Error in deleteProductSupplier()");
            throw e;
        }
    }
}