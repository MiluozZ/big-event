package com.itheima.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePwdDto {
    private Integer id;
    @NotEmpty
    @Size(min = 6)
    private String old_pwd;
    @NotEmpty
    @Size(min = 6)
    private String new_pwd;
    @NotEmpty
    @Size(min = 6)
    private String re_pwd;

}
