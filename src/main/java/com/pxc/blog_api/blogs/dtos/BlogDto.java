package com.pxc.blog_api.blogs.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogDto {
    private Integer id;
    private TopicDto topic;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private String url;
    private String title;
    private String content;
    private String metaText;
    private List<CommentDto> comments;

    public BlogDto(Integer id, TopicDto topic, String authorName, LocalDateTime createdAt, LocalDateTime lastModified, String url, String title, String content, String metaText, List<CommentDto> comments) {
        this.id = id;
        this.topic = topic;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.url = url;
        this.title = title;
        this.content = content;
        this.metaText = metaText;
        this.comments = comments;
    }

    public BlogDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMetaText() {
        return metaText;
    }

    public void setMetaText(String metaText) {
        this.metaText = metaText;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
