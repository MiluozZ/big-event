package com.itheima.service.Impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    @Override
    public List<Category> findAllCategoryByUserId(Integer userId) {
        return categoryMapper.findAllCategoryByUserId(userId);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategoryByIdAndUserId(Category category) {
        categoryMapper.deleteCategoryByIdAndUserId(category);
    }

    @Override
    public Category findCategoryByIdAndUserId(Category category) {
        return categoryMapper.findCategoryByIdAndUserId(category);
    }
}
