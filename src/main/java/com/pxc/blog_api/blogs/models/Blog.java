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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
