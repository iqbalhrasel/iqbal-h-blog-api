package com.pxc.blog_api.iqs;

import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.iqs.dtos.InterviewAndPages;
import com.pxc.blog_api.iqs.services.InterviewQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/iq")
public class InterviewController {
    private final InterviewQuestionService interviewQuestionService;

    @GetMapping
    public InterviewAndPages getInterviewQs(@RequestParam(name = "pageNo") Integer pageNo,
                                            @RequestParam(name = "topicId") Short topicId){
        return interviewQuestionService.getIqsWithPage(pageNo, topicId);
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTopicNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "topic not found"));
    }
}
