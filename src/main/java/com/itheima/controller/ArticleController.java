package com.itheima.controller;

import com.itheima.dto.ArticlePageReqDto;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        User user = ThreadLocalUtil.get();
        article.setCreateUser(user.getId());
        articleService.addArticle(article);
        return Result.success();
    }

    @PostMapping("/list")
    public Result<PageBean<Article>> listArticleByCondition(@RequestBody ArticlePageReqDto articlePageReqDto) {
        User user = ThreadLocalUtil.get();
        articlePageReqDto.setUserId(user.getId());
        PageBean<Article> articlePage = articleService.listArticleByCondition(articlePageReqDto);
        return Result.success(articlePage);
    }

    @GetMapping("detail")
    public Result<Article> getArticleDetail(@RequestParam("id") Integer id) {
        User user = ThreadLocalUtil.get();
        Article article = articleService.getArtDetailByIdAndUserId(id, user.getId());
        return Result.success(article);
    }

    @PutMapping
    public Result updateArticle(@RequestBody @Validated Article article) {
        User user = ThreadLocalUtil.get();
        article.setCreateUser(user.getId());
        articleService.updateArticle(article);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteArticle(@RequestParam("id") Integer id) {
        User user = ThreadLocalUtil.get();
        articleService.deleteArticleByIdAndUserId(id, user.getId());
        return Result.success();
    }
}
