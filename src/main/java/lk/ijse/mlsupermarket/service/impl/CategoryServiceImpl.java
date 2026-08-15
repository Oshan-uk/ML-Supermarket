package lk.ijse.mlsupermarket.service.impl;

import lk.ijse.mlsupermarket.dto.CategoryDTO;
import lk.ijse.mlsupermarket.entity.Category;
import lk.ijse.mlsupermarket.entity.Product;
import lk.ijse.mlsupermarket.repository.CategoryRepository;
import lk.ijse.mlsupermarket.repository.ProductRepository;
import lk.ijse.mlsupermarket.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, CategoryService categoryService){
        this.categoryRepository= categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        log.info("Executes saveCategory()");

        try{
            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setDescription(categoryDTO.getDescription());

            categoryRepository.save(category);
        } catch (Exception e) {
            log.error("Error in saveCategory()");
            throw e;
        }
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        log.info("Execute updateCategory()");

        try{
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getCategoryId());

            if (optionalCategory.isEmpty()) throw new RuntimeException("Sorry, related category is Not Found!");

            Category category = optionalCategory.get();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setDescription(categoryDTO.getDescription());

            categoryRepository.save(category);
        } catch (Exception e) {
            log.error("Error in updateCategory()");
            throw e;
        }
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        log.info("Execute getAllCategories()");

        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getDescription()
            );

            categoryDTOList.add(categoryDTO);
        }

        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(long categoryId) {
        log.info("Execute getCAtegoryById()");

        try{
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isEmpty()) throw new RuntimeException("Sorry, related category is Not Found!");

            Category category = optionalCategory.get();
            return new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getDescription()
            );
        } catch (Exception e) {
            log.error("Error in getCategoryById()");
            throw e;
        }
    }

    @Override
    public List<CategoryDTO> searchCategoriesByName(String categoryName) {
        log.info("Execute searchCategoriesByName()");

        try{
            return categoryRepository.searchCategoriesByName(categoryName);
        } catch (Exception e) {
            log.error("Error in searchCategoriesByName()");
            throw e;
        }
    }

    @Override
    public void deleteCategory(long categoryId) {
        log.info("Execute deleteCategory()");
        try{
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if(optionalCategory.isEmpty()) throw new RuntimeException("Sorry, related category is not found!");

            List<Product> linkedProducts = productRepository.findByCategory_CategoryId(categoryId);
            if(!linkedProducts.isEmpty()){
                throw new RuntimeException("Sorry, cannot delete category");
            }
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw e;
        }
    }
}
