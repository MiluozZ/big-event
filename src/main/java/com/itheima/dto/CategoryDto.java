package com.itheima.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {
    @NotEmpty
    private String categoryName;
    @NotEmpty
    private String categoryAlias;
}
