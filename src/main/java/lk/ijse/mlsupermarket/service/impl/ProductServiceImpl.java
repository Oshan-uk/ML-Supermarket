package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.ProductDTO;
import lk.ijse.mlsupermarket.entity.Category;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.repository.CategoryRepository;
import lk.ijse.mlsupermarket.repository.ProductRepository;
import lk.ijse.mlsupermarket.service.ProductService;
import lk.ijse.mlsupermarket.status.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository= categoryRepository;
    }

    @Override
    public void saveProduct(ProductDTO productDTO) {
        log.info("Execute saveProduct()");

        try{
            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
            if(optionalCategory.isEmpty()) throw new RuntimeException("Sorry, related category is Not Found!");

            Product product = new Product();
            product.setProductName(productDTO.getProductName());
            product.setUnitPrice(productDTO.getUnitPrice());
            product.setBarcode(productDTO.getBarcode());
            product.setStatus(ProductStatus.ACTIVE);
            product.setCategory(optionalCategory.get());

            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error in saveProduct()");
            throw e;
        }
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        log.info("Execute updateProduct()");

        try{
            Optional<Product> optionalProduct = productRepository.findById(productDTO.getProductId());
            if (optionalProduct.isEmpty()) throw new RuntimeException("Sorry, related product is not found!");

            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
            if (optionalCategory.isEmpty()) throw new RuntimeException("Sorry, related category is not found!");

            Product product = optionalProduct.get();
            product.setProductName(productDTO.getProductName());
            product.setUnitPrice(productDTO.getUnitPrice());
            product.setBarcode(productDTO.getBarcode());
            product.setCategory(optionalCategory.get());

            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error in updateProduct()");
            throw e;
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("Execute getAllProducts()");

        try {
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOList = new ArrayList<>();

            for (Product product : products) {
                ProductDTO productDTO = new ProductDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getUnitPrice(),
                        product.getBarcode(),
                        product.getStatus(),
                        product.getCategory().getCategoryId()
                );

                productDTOList.add(productDTO);
            }
            return productDTOList;

        } catch (Exception e) {
            log.error("Error in getAllProducts()", e);
            throw e;
        }
    }

    @Override
    public ProductDTO getProductById(long productId) {
        log.info("Execute getProductById()", productId);
        try {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) throw new RuntimeException("Sorry, related product is not found");

            Product product = optionalProduct.get();
            return new ProductDTO(
                    product.getProductId(),
                    product.getProductName(),
                    product.getUnitPrice(),
                    product.getBarcode(),
                    product.getStatus(),
                    product.getCategory().getCategoryId()
            );
        } catch (Exception e) {
            log.error("Error in getProductById()");
            throw e;
        }
    }

    @Override
    public List<ProductDTO> searchProductsByName(String productName) {
        log.info("Execute searchProductsByName()");

        try{
            return productRepository.searchProductsByName(productName);
        } catch (Exception e) {
            log.error("Error in searchProductsByName()");
            throw e;
        }
    }

    @Override
    public List<ProductDTO> filterProducts(String categoryName, Double minPrice, Double maxPrice, ProductStatus status) {
        log.info("Execute filterProducts()");
        try{
            return productRepository.filterProducts(categoryName,minPrice, maxPrice,status);

        } catch (Exception e) {
            log.error("Error in filterProducts()");
            throw e;
        }
    }

    @Override
    public void changeProductStatus(long productId) {
        log.info("Executes changeProductStatus()");
        try{
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) throw new RuntimeException("Sorry, related product is not found");

            Product product = optionalProduct.get();
            product.setStatus(product.getStatus() == ProductStatus.ACTIVE ? ProductStatus.INACTIVE : ProductStatus.ACTIVE);

            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error in changeProductStatus()");
            throw e;
        }
    }
}
