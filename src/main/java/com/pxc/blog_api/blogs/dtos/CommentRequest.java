package com.pxc.blog_api.blogs.dtos;

import lombok.Data;


public class CommentRequest {
    private String holdToken;
    private String name;
    private String email;
    private String content;
    private Integer blogId;

    public CommentRequest(String holdToken, String name, String email, String content, Integer blogId) {
        this.holdToken = holdToken;
        this.name = name;
        this.email = email;
        this.content = content;
        this.blogId = blogId;
    }

    public CommentRequest() {
    }

    public String getHoldToken() {
        return holdToken;
    }

    public void setHoldToken(String holdToken) {
        this.holdToken = holdToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}
