package com.pxc.blog_api.auth.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
