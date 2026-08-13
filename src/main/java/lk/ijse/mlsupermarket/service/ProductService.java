package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.ProductDTO;
import lk.ijse.mlsupermarket.status.ProductStatus;

import java.util.List;

public interface ProductService {

    public void saveProduct(ProductDTO productDTO);
    public void updateProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    public ProductDTO getProductById(long productId);
    List<ProductDTO> searchProductsByName(String productName);
    List<ProductDTO> filterProducts(String categoryName, Double minPrice, Double maxPrice, ProductStatus status);
    public void changeProductStatus(long productId);


}
