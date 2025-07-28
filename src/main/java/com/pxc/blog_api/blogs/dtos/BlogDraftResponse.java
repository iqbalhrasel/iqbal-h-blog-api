package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

@Data
public class BlogDraftResponse {
    private Integer id;
    private String topicName;
    private String title;
}
