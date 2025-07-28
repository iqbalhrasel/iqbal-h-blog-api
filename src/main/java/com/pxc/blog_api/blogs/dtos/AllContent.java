package com.pxc.blog_api.blogs.dtos;

import com.pxc.blog_api.iqs.dtos.InterviewQuestionDto;
import lombok.Data;

import java.util.List;

@Data
public class AllContent {
    private List<BlogCardDto> newsInterests;
    private List<BlogCardDto> latest4Blogs;
    private List<InterviewQuestionDto> latest3InterviewQuestion;
}
