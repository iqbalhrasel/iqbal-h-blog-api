package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

@Data
public class IqDraftDto {
    private Short topicId;
    private Integer id;
    private String question;
    private String answer;
}
