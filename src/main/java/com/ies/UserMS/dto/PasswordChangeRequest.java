package com.ies.UserMS.dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private Integer userId;
    private String email;
    private String tempPassword;
    private String password;
    private String confirmPassword;

}
