package com.itheima.controller;

import com.itheima.dto.CategoryDto;
import com.itheima.dto.CategoryReqDto;
import com.itheima.dto.CategoryResDto;
import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated CategoryDto categoryDto) {
        User userToken = ThreadLocalUtil.get();

        Category category = new Category();
        category.setCreateUser(userToken.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryAlias(categoryDto.getCategoryAlias());
        categoryService.addCategory(category);

        return Result.success();
    }

    @GetMapping
    public Result<List<CategoryResDto>> findAllCategoryByUserId() {
        User userToken = ThreadLocalUtil.get();
        List<Category> categoryList = categoryService.findAllCategoryByUserId(userToken.getId());
        List<CategoryResDto> dtoList = categoryList.stream().map(cg -> {
            CategoryResDto categoryResDto = new CategoryResDto();
            categoryResDto.setId(cg.getId());
            categoryResDto.setCategoryName(cg.getCategoryName());
            categoryResDto.setCategoryAlias(cg.getCategoryAlias());
            categoryResDto.setCreateTime(cg.getCreateTime());
            categoryResDto.setUpdateTime(cg.getUpdateTime());
            return categoryResDto;
        }).toList();
        return Result.success(dtoList);
    }

    @PutMapping
    public Result updateCategory(@RequestBody @Validated CategoryReqDto categoryReqDto) {
        User userToken = ThreadLocalUtil.get();

        Category category = new Category();
        category.setId(categoryReqDto.getId());
        category.setCategoryName(categoryReqDto.getCategoryName());
        category.setCategoryAlias(categoryReqDto.getCategoryAlias());
        category.setCreateUser(userToken.getId());
        categoryService.updateCategory(category);

        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategoryById(@RequestParam @NotNull Integer id) {
        User userToken = ThreadLocalUtil.get();
        Category category = new Category();
        category.setId(id);
        category.setCreateUser(userToken.getId());
        categoryService.deleteCategoryByIdAndUserId(category);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<CategoryResDto> findCategoryById(@RequestParam @NotNull Integer id) {
        User userToken = ThreadLocalUtil.get();
        Category category = new Category();
        category.setId(id);
        category.setCreateUser(userToken.getId());
        category = categoryService.findCategoryByIdAndUserId(category);

        CategoryResDto categoryResDto = new CategoryResDto();
        categoryResDto.setId(category.getId());
        categoryResDto.setCategoryName(category.getCategoryName());
        categoryResDto.setCategoryAlias(category.getCategoryAlias());
        categoryResDto.setCreateTime(category.getCreateTime());
        categoryResDto.setUpdateTime(category.getUpdateTime());

        return Result.success(categoryResDto);
    }
}
