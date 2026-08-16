package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.ProductSupplierDTO;

import java.util.List;

public interface ProductSupplierService {
    public void saveProductSupplier(ProductSupplierDTO productSupplierDTO);
    public void updateProductSupplier(ProductSupplierDTO productSupplierDTO);
    public List<ProductSupplierDTO> getAllProductSuppliers();
    public List<ProductSupplierDTO> getSuppliersByProduct(long productId);
    public void deleteProductSupplier(long productSupplierId);

}
