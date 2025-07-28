package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

@Data
public class IqSubmitRequest {
    private Short topicId;
    private String question;
    private String answer;
}
