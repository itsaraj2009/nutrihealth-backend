package com.ecom.nutrihealth.controller;

import com.ecom.nutrihealth.config.AppConstants;
import com.ecom.nutrihealth.payload.CategoryDTO;
import com.ecom.nutrihealth.payload.CategoryResponse;
import com.ecom.nutrihealth.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATAGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder)
    {
        CategoryResponse categoryResponse = categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> addCategories(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO =  categoryService.createCategories(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryDTO> editCategories(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        CategoryDTO updateCategoryDTO = categoryService.updateCategories(categoryDTO, id);
            return new ResponseEntity<>(updateCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategories(@PathVariable Long categoryId){
        CategoryDTO status = categoryService.deleteCategories(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

}