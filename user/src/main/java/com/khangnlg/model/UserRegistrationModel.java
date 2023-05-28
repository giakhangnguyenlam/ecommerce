package com.khangnlg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {

    @NotBlank
    private String name;
    private long dateOfBirth;
    @Email
    private String email;
    private String address;
    @NotBlank
    private String gender;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String phone;

    private String grantedAuthority;

}
