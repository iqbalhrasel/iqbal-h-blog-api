package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

@Data
public class IqDraftResponse {
    private Integer id;
    private String topicName;
    private String question;
}
