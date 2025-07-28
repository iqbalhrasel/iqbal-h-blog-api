package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

public class IqDraftResponse {
    private Integer id;
    private String topicName;
    private String question;

    public IqDraftResponse(Integer id, String topicName, String question) {
        this.id = id;
        this.topicName = topicName;
        this.question = question;
    }

    public IqDraftResponse() {
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
