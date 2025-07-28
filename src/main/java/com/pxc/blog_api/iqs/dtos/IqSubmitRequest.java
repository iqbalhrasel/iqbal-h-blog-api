package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

@Data
public class IqSubmitRequest {
    private Short topicId;
    private String question;
    private String answer;

    public IqSubmitRequest(Short topicId, String question, String answer) {
        this.topicId = topicId;
        this.question = question;
        this.answer = answer;
    }

    public IqSubmitRequest() {
    }

    public Short getTopicId() {
        return topicId;
    }

    public void setTopicId(Short topicId) {
        this.topicId = topicId;
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
