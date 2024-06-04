package com.itheima.mapper;

import com.itheima.dto.ArticlePageReqDto;
import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert(" insert into article (title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            " values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void addArticle(Article article);

    List<Article> listArticleByCondition(ArticlePageReqDto articlePageReqDto);

    @Select("select * from article where id = #{id} and create_user = #{userId}")
    Article getArtDetailByIdAndUserId(Integer id, Integer userId);

    @Update(" update article " +
            " set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = now() " +
            " where id = #{id} and create_user = #{createUser}")
    void updateArticle(Article article);

    @Delete("delete from article where id = #{id} and create_user = #{userId}")
    void deleteArticleByIdAndUserId(Integer id, Integer userId);
}
