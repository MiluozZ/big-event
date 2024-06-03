package com.itheima.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResDto extends CategoryDto {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
