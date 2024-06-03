package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) values (#{categoryName}, #{categoryAlias}, #{createUser} , now(), now())")
    void addCategory(Category category);

    @Select("select * from category where create_user = #{userId}")
    List<Category> findAllCategoryByUserId(Integer userId);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias} , update_time = now() where id = #{id} and create_user = #{createUser}")
    void updateCategory(Category category);

    @Delete("delete from category where id = #{id} and create_user = #{createUser}")
    void deleteCategoryByIdAndUserId(Category category);

    @Select("select * from category where id = #{id} and create_user = #{createUser}")
    Category findCategoryByIdAndUserId(Category category);
}
