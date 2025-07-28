package com.pxc.blog_api.blogs.dtos;

import lombok.Data;


public class TopicAddRequest {
    private String name;

    public TopicAddRequest(String name) {
        this.name = name;
    }

    public TopicAddRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
