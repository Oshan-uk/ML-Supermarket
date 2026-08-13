package lk.ijse.mlsupermarket.repository;

import lk.ijse.mlsupermarket.dto.CategoryDTO;
import lk.ijse.mlsupermarket.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "Select new lk.ijse.mlsupermarket.dto.CategoryDTO (c.categoryId, c.categoryName, c.description) from Category c where (?1 Is null or c.categoryName like %?1%)")
    List<CategoryDTO> searchCategoriesByName(String categoryName);
}
