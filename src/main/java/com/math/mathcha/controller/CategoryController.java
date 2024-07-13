package com.math.mathcha.controller;


import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.CategoryDTO;
import com.math.mathcha.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/category")
@SecurityRequirement(name = "api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('CONTENT_MANAGER')")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws IdInvalidException {
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @GetMapping("/get/{category_id}")
    public ResponseEntity<CategoryDTO> getCategoryById (@PathVariable("category_id") Integer category_id) throws IdInvalidException{
        CategoryDTO categoryDTO = categoryService.getCategoryById(category_id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<CategoryDTO>> getCategoryAll(){
        List<CategoryDTO> categoryDTOS = categoryService.getCategoryAll();
        return ResponseEntity.ok(categoryDTOS);
    }
}
