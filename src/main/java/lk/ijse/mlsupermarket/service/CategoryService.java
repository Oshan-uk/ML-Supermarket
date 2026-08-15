package lk.ijse.mlsupermarket.service;

import lk.ijse.mlsupermarket.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public void saveCategory(CategoryDTO categoryDTO);
    public void updateCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(long categoryId);
    List<CategoryDTO> searchCategoriesByName(String categoryName);
    public void deleteCategory(long categoryId);
}