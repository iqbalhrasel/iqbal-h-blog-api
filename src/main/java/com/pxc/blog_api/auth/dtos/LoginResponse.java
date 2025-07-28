package com.pxc.blog_api.auth.dtos;

import com.pxc.blog_api.auth.jwt.Jwt;
import com.pxc.blog_api.blogs.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;

    public LoginResponse(Jwt accessToken, Jwt refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public LoginResponse() {
    }

    public Jwt getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(Jwt accessToken) {
        this.accessToken = accessToken;
    }

    public Jwt getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(Jwt refreshToken) {
        this.refreshToken = refreshToken;
    }
}
