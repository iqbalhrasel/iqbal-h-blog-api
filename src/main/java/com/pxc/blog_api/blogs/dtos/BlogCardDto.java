package com.pxc.blog_api.blogs.dtos;

import java.time.LocalDateTime;

public class BlogCardDto {
    private Integer id;
    private String topicName;
    private String authorName;
    private LocalDateTime createdAt;
    private String url;
    private String title;
    private String metaText;

    public BlogCardDto(Integer id, String topicName, String authorName, LocalDateTime createdAt, String url, String title, String metaText) {
        this.id = id;
        this.topicName = topicName;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.url = url;
        this.title = title;
        this.metaText = metaText;
    }

    public BlogCardDto() {
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
}
