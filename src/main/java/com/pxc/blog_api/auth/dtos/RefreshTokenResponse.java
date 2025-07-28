package com.pxc.blog_api.auth.dtos;

import com.pxc.blog_api.blogs.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


public class RefreshTokenResponse {
    private String token;
    private Role role;

    public RefreshTokenResponse(String token, Role role) {
        this.token = token;
        this.role = role;
    }

    public RefreshTokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
