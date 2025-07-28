package com.pxc.blog_api.iqs.dtos;

import lombok.Data;


public class IqDraftDto {
    private Short topicId;
    private Integer id;
    private String question;
    private String answer;

    public IqDraftDto(Short topicId, Integer id, String question, String answer) {
        this.topicId = topicId;
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public IqDraftDto() {
    }

    public Short getTopicId() {
        return topicId;
    }

    public void setTopicId(Short topicId) {
        this.topicId = topicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
