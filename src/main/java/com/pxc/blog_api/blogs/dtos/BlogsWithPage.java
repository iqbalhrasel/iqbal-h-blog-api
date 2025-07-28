package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BlogsWithPage {
    private List<BlogCardDto> blogs;
    private int totalPages;
}
