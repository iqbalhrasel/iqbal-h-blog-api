package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.util.List;


public class BlogsWithPage {
    private List<BlogCardDto> blogs;
    private int totalPages;

    public BlogsWithPage(List<BlogCardDto> blogs, int totalPages) {
        this.blogs = blogs;
        this.totalPages = totalPages;
    }

    public BlogsWithPage() {
    }

    public List<BlogCardDto> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogCardDto> blogs) {
        this.blogs = blogs;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
