package lk.ijse.mlsupermarket.controller;

import lk.ijse.mlsupermarket.constant.CommonResponse;
import lk.ijse.mlsupermarket.constant.ResponseCode;
import lk.ijse.mlsupermarket.constant.ResponseMessage;
import lk.ijse.mlsupermarket.dto.CategoryDTO;
import lk.ijse.mlsupermarket.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, categories, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getCategoryById(@PathVariable long categoryId) {
        CategoryDTO category = categoryService.getCategoryById(categoryId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, category, ResponseMessage.SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/search/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse searchCategoriesByName(@PathVariable String categoryName) {
        List<CategoryDTO> categories = categoryService.searchCategoriesByName(categoryName);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, categories, ResponseMessage.SUCCESS_MESSAGE);
    }

    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new CommonResponse(ResponseCode.OPERATION_SUCCESS, ResponseMessage.SUCCESS_MESSAGE);
    }
}