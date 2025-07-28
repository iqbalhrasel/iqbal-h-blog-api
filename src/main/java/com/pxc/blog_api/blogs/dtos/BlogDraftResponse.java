package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

public class BlogDraftResponse {
    private Integer id;
    private String topicName;
    private String title;

    public BlogDraftResponse(Integer id, String topicName, String title) {
        this.id = id;
        this.topicName = topicName;
        this.title = title;
    }

    public BlogDraftResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
