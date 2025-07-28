package com.pxc.blog_api.blogs.dtos;

import com.pxc.blog_api.iqs.dtos.InterviewQuestionDto;
import lombok.Data;

import java.util.List;


public class AllContent {
    private List<BlogCardDto> newsInterests;
    private List<BlogCardDto> latest4Blogs;
    private List<InterviewQuestionDto> latest3InterviewQuestion;

    public AllContent(List<BlogCardDto> newsInterests, List<BlogCardDto> latest4Blogs, List<InterviewQuestionDto> latest3InterviewQuestion) {
        this.newsInterests = newsInterests;
        this.latest4Blogs = latest4Blogs;
        this.latest3InterviewQuestion = latest3InterviewQuestion;
    }

    public AllContent() {
    }

    public List<BlogCardDto> getNewsInterests() {
        return newsInterests;
    }

    public void setNewsInterests(List<BlogCardDto> newsInterests) {
        this.newsInterests = newsInterests;
    }

    public List<BlogCardDto> getLatest4Blogs() {
        return latest4Blogs;
    }

    public void setLatest4Blogs(List<BlogCardDto> latest4Blogs) {
        this.latest4Blogs = latest4Blogs;
    }

    public List<InterviewQuestionDto> getLatest3InterviewQuestion() {
        return latest3InterviewQuestion;
    }

    public void setLatest3InterviewQuestion(List<InterviewQuestionDto> latest3InterviewQuestion) {
        this.latest3InterviewQuestion = latest3InterviewQuestion;
    }
}
