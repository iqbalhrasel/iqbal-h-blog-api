package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.time.LocalDateTime;

public class CommentDto {
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    private String content;

    public CommentDto(Integer id, LocalDateTime createdAt, String name, String content) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.content = content;
    }

    public CommentDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
