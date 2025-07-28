package com.pxc.blog_api.iqs.dtos;

import lombok.Data;

import java.util.List;


public class InterviewAndPages {
    private List<InterviewQuestionDto> questions;
    private Integer totalPages;

    public InterviewAndPages(List<InterviewQuestionDto> questions, Integer totalPages) {
        this.questions = questions;
        this.totalPages = totalPages;
    }

    public InterviewAndPages() {
    }

    public List<InterviewQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<InterviewQuestionDto> questions) {
        this.questions = questions;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
