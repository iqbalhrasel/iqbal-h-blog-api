package com.pxc.blog_api.blogs.models;

import com.pxc.blog_api.iqs.models.InterviewQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
