package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

public class TopicUpdateRequest {
    private String name;

    public TopicUpdateRequest(String name) {
        this.name = name;
    }

    public TopicUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
