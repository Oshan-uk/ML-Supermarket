package lk.ijse.mlsupermarket.service;


import lk.ijse.mlsupermarket.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    public void saveSupplier(SupplierDTO supplierDTO);
    public void updateSupplier(SupplierDTO supplierDTO);
    List<SupplierDTO> getAllSuppliers();
    public SupplierDTO getSupplierById(long supplierId);
    List<SupplierDTO> searchSuppliersByName(String supplierName);
    public void deleteSupplier(long supplierId);
}
