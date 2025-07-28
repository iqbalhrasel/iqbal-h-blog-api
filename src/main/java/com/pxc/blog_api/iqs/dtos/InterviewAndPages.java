package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

import java.util.List;

@Data
public class InterviewAndPages {
    private List<InterviewQuestionDto> questions;
    private Integer totalPages;
}
