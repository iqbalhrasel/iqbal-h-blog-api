package com.pxc.blog_api.blogs.models;

import com.pxc.blog_api.blogs.dtos.CommentRequest;
import com.pxc.blog_api.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "created_at", insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified", insertable = false)
    private LocalDateTime lastModified;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "meta_text")
    private String metaText;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContentStatus status;

    public Blog(Integer id, Topic topic, User author, LocalDateTime createdAt, LocalDateTime lastModified, String url, String title, String content, String metaText, List<Comment> comments, ContentStatus status) {
        this.id = id;
        this.topic = topic;
        this.author = author;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.url = url;
        this.title = title;
        this.content = content;
        this.metaText = metaText;
        this.comments = comments;
        this.status = status;
    }

    public Blog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    public void addComment(CommentRequest commentRequest){
        var comment = new Comment();
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setContent(commentRequest.getContent());
        comment.setBlog(this);

        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", metaText='" + metaText + '\'' +
                ", status=" + status +
                '}';
    }
}
