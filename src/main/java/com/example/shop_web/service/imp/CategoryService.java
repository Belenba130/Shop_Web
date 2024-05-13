package com.example.shop_web.service.imp;

import com.example.shop_web.model.dto.request.CategoryRequest;
import com.example.shop_web.model.dto.response.CategoryResponse;
import com.example.shop_web.model.entity.CategoryEntity;
import com.example.shop_web.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ProductServiceImpl productService;

    public Page<CategoryEntity> getAllCategory(Pageable pageable){
       return categoryRepository.findAll(pageable);
    }

    public CategoryEntity getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id){
        CategoryEntity category = categoryRepository.findById(id).orElse(null);
        categoryRepository.delete(category);
    }

    public CategoryEntity addCategory(CategoryRequest request){
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(request.getCategoryId());
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setStatus(request.getStatus());
        return categoryRepository.save(category);
    }

    public CategoryEntity updateCategory(Long id, CategoryRequest request){
        CategoryEntity Updatecategory = categoryRepository.findById(id).orElse(null);
        Updatecategory.setCategoryId(request.getCategoryId());
        Updatecategory.setCategoryName(request.getCategoryName());
        Updatecategory.setDescription(request.getDescription());
        Updatecategory.setStatus(request.getStatus());
        return categoryRepository.save(Updatecategory);
    }
}
