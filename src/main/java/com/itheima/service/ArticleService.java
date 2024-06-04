package com.itheima.service;

import com.itheima.dto.ArticlePageReqDto;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

public interface ArticleService {
    void addArticle(Article article);

    PageBean<Article> listArticleByCondition(ArticlePageReqDto articlePageReqDto);

    Article getArtDetailByIdAndUserId(Integer id, Integer userId);

    void updateArticle(Article article);

    void deleteArticleByIdAndUserId(Integer id, Integer userId);
}
