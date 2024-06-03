package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> findAllCategoryByUserId(Integer userId);

    void updateCategory(Category category);

    void deleteCategoryByIdAndUserId(Category category);

    Category findCategoryByIdAndUserId(Category category);
}
