package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogDto {
    private Integer id;
    private TopicDto topic;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private String url;
    private String title;
    private String content;
    private String metaText;
    private List<CommentDto> comments;
}
