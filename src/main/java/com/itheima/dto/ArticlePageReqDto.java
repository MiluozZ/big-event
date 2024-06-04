package com.itheima.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticlePageReqDto {
    //每页条数
    @NotNull
    private Integer pageSize;
    //当前页数
    @NotNull
    private Integer pageNum;
    //文章分类Id
    private Integer categoryId;
    //发布状态
    private String state;
    //用户id
    private Integer userId;
}
