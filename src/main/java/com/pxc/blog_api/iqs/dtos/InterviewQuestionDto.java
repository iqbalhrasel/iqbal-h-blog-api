package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewQuestionDto {
    private Integer id;
    private String topicName;
    private LocalDateTime createdAt;
    private String question;
    private String answer;
}
