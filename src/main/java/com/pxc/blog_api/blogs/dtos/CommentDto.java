package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    private String content;
}
