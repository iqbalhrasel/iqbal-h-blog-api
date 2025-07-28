package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

@Data
public class BlogDraftDto {
    private Short topicId;
    private Integer blogId;
    private String url;
    private String title;
    private String metaText;
    private String content;
}
