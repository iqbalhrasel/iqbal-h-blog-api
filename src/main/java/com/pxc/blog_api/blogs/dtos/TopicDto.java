package com.pxc.blog_api.blogs.dtos;

import lombok.Data;


public class TopicDto {
    private Short id;
    private String name;

    public TopicDto(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public TopicDto() {
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
