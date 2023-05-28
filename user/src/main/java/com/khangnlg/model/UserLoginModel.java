package com.khangnlg.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginModel {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
