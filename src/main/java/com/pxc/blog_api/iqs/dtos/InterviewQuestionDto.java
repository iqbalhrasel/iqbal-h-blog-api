package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

import java.time.LocalDateTime;

public class InterviewQuestionDto {
    private Integer id;
    private String topicName;
    private LocalDateTime createdAt;
    private String question;
    private String answer;

    public InterviewQuestionDto(Integer id, String topicName, LocalDateTime createdAt, String question, String answer) {
        this.id = id;
        this.topicName = topicName;
        this.createdAt = createdAt;
        this.question = question;
        this.answer = answer;
    }

    public InterviewQuestionDto() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
