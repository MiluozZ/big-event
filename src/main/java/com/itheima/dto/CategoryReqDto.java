package com.itheima.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryReqDto extends CategoryDto {
    @NotNull
    private Integer id;
}
