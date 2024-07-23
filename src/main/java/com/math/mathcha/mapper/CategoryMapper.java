package com.math.mathcha.mapper;

import com.math.mathcha.dto.request.CategoryDTO;
import com.math.mathcha.entity.Category;

public class CategoryMapper {
    public static CategoryDTO mapToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setCategory_name(category.getCategory_name());
        return categoryDTO;
    }

    public static Category mapToCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategory_id(categoryDTO.getCategory_id());
        category.setCategory_name(categoryDTO.getCategory_name());
        return category;
    }
}
