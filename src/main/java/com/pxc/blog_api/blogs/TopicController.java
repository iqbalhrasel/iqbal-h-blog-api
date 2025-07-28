package com.pxc.blog_api.blogs;

import com.pxc.blog_api.blogs.dtos.TopicAddRequest;
import com.pxc.blog_api.blogs.dtos.TopicDto;
import com.pxc.blog_api.blogs.dtos.TopicUpdateRequest;
import com.pxc.blog_api.blogs.services.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<TopicDto> allTopics(){
        return topicService.getAllTopics();
    }

}
