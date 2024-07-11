package com.math.mathcha.service;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CategoryDTO;
import com.math.mathcha.dto.request.CourseDTO;
import com.math.mathcha.entity.Category;
import com.math.mathcha.entity.Course;
import com.math.mathcha.mapper.CategoryMapper;
import com.math.mathcha.mapper.ChapterMapper;
import com.math.mathcha.repository.CategoryRepository;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.service.courseService.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CourseRepository courseRepository;
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws IdInvalidException {
        Category category = CategoryMapper.mapToCategory(categoryDTO);
        Category saveCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDTO(saveCategory);
    }

    public CategoryDTO getCategoryById(Integer category_id) throws IdInvalidException {
        Optional<Category> category = categoryRepository.findById(category_id);
        if (category.isPresent()) {
            return CategoryMapper.mapToCategoryDTO(category.get());
        }else{
            throw new IdInvalidException("Category với id = " + category_id + " không tồn tại");
        }
    }

    public List<CategoryDTO> getCategoryAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(CategoryMapper::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

}
