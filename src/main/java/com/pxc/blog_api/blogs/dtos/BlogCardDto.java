package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogCardDto {
    private Integer id;
    private String topicName;
    private String authorName;
    private LocalDateTime createdAt;
    private String url;
    private String title;
    private String metaText;
}
