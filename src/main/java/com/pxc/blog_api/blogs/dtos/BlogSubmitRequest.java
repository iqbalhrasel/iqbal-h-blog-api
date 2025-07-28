package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

@Data
public class BlogSubmitRequest {
    private Short topicId;
    private String url;
    private String title;
    private String metaText;
    private String content;
}
