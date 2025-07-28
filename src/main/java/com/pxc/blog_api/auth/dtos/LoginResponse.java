package com.pxc.blog_api.auth.dtos;

import com.pxc.blog_api.auth.jwt.Jwt;
import com.pxc.blog_api.blogs.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
