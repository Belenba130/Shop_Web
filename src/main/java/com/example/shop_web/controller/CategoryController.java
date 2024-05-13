package com.example.shop_web.controller;

import com.example.shop_web.model.dto.request.CategoryRequest;
import com.example.shop_web.model.entity.CategoryEntity;
import com.example.shop_web.service.imp.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public Page<CategoryEntity> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "categoryId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return categoryService.getAllCategory(pageRequest);
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryEntity getById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/categories")
    public CategoryEntity addCategory(@RequestBody CategoryRequest request){
        return categoryService.addCategory(request);
    }
    @PutMapping("/categories/{categoryId}")
    public CategoryEntity updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request){
        return categoryService.updateCategory(categoryId,request);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
