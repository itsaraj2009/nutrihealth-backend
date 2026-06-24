package com.ecom.nutrihealth.service;

import com.ecom.nutrihealth.model.Category;
import com.ecom.nutrihealth.payload.CategoryDTO;
import com.ecom.nutrihealth.payload.CategoryResponse;


public interface CategoryService {

    CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategories(CategoryDTO categoryDTO);

    CategoryDTO deleteCategories(Long categoryId);

    CategoryDTO updateCategories(CategoryDTO categoryDTO, Long id);
}
