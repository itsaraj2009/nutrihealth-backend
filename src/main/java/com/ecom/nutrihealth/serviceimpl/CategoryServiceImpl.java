package com.ecom.nutrihealth.serviceimpl;

import com.ecom.nutrihealth.exception.APIException;
import com.ecom.nutrihealth.exception.ResourceNotFoundException;
import com.ecom.nutrihealth.model.Category;
import com.ecom.nutrihealth.payload.CategoryDTO;
import com.ecom.nutrihealth.payload.CategoryResponse;
import com.ecom.nutrihealth.repository.CategoryRepository;
import com.ecom.nutrihealth.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByandOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByandOrder);

        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

        if(categories.isEmpty()){
            throw new APIException("Category does not contains anything...");
        }

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());



        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategories(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category findCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(findCategory != null){
            throw new APIException("Category already exist with the name "+ categoryDTO.getCategoryName());
        }

        Category savedCategory = categoryRepository.save(category);


        return  modelMapper.map(savedCategory, CategoryDTO.class );
    }

    @Override
    public CategoryDTO deleteCategories(Long categoryId) {

        Category deletecategory  = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId",categoryId));

        categoryRepository.delete(deletecategory);
        return modelMapper.map(deletecategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategories(CategoryDTO categoryDTO, Long id) {

        Category savedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id",id));

        Category Category = modelMapper.map(categoryDTO, Category.class);

        savedCategory.setCategoryName(Category.getCategoryName());
        savedCategory = categoryRepository.save(savedCategory);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

}