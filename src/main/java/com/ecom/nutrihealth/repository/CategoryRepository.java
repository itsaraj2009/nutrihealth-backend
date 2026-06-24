package com.ecom.nutrihealth.repository;

import com.ecom.nutrihealth.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByCategoryName(String categoryName);
}
