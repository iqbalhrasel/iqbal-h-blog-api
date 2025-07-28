package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

@Data
public class CommentRequest {
    private String holdToken;
    private String name;
    private String email;
    private String content;
    private Integer blogId;
}
