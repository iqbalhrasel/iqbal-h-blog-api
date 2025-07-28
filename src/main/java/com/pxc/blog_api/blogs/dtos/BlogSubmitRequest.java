package com.pxc.blog_api.blogs.dtos;

import lombok.Data;


public class BlogSubmitRequest {
    private Short topicId;
    private String url;
    private String title;
    private String metaText;
    private String content;

    public BlogSubmitRequest(Short topicId, String url, String title, String metaText, String content) {
        this.topicId = topicId;
        this.url = url;
        this.title = title;
        this.metaText = metaText;
        this.content = content;
    }

    public BlogSubmitRequest() {
    }

    public Short getTopicId() {
        return topicId;
    }

    public void setTopicId(Short topicId) {
        this.topicId = topicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaText() {
        return metaText;
    }

    public void setMetaText(String metaText) {
        this.metaText = metaText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
