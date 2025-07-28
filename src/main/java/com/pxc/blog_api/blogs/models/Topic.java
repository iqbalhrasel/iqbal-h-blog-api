package com.pxc.blog_api.blogs.models;

import com.pxc.blog_api.iqs.models.InterviewQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Blog> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "topic")
    private List<InterviewQuestion> questions = new ArrayList<>();

    public Topic(Short id, String name, List<Blog> blogs, List<InterviewQuestion> questions) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
        this.questions = questions;
    }

    public Topic() {
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

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<InterviewQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<InterviewQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
