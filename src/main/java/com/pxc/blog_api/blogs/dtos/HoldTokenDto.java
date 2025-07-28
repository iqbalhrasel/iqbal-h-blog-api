package com.pxc.blog_api.blogs.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


public class HoldTokenDto {
    private String holdToken;

    public HoldTokenDto(String holdToken) {
        this.holdToken = holdToken;
    }

    public HoldTokenDto() {
    }

    public String getHoldToken() {
        return holdToken;
    }

    public void setHoldToken(String holdToken) {
        this.holdToken = holdToken;
    }
}
