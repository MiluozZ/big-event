package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dto.ArticlePageReqDto;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        articleMapper.addArticle(article);
    }

    @Override
    public PageBean<Article> listArticleByCondition(ArticlePageReqDto articlePageReqDto) {
        PageBean<Article> articlePageBean = new PageBean<>();
        PageHelper.startPage(articlePageReqDto.getPageNum(), articlePageReqDto.getPageSize());
        List<Article> articles = articleMapper.listArticleByCondition(articlePageReqDto);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        articlePageBean.setTotal(pageInfo.getTotal());
        articlePageBean.setItems(pageInfo.getList());
        return articlePageBean;
    }

    @Override
    public Article getArtDetailByIdAndUserId(Integer id, Integer userId) {
        return articleMapper.getArtDetailByIdAndUserId(id, userId);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticleByIdAndUserId(Integer id, Integer userId) {
        articleMapper.deleteArticleByIdAndUserId(id, userId);
    }
}
