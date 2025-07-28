package com.pxc.blog_api.admin;

import com.pxc.blog_api.blogs.dtos.TopicAddRequest;
import com.pxc.blog_api.blogs.dtos.TopicDto;
import com.pxc.blog_api.blogs.dtos.TopicUpdateRequest;
import com.pxc.blog_api.blogs.services.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("admin/topics")
public class AdminTopicController {
    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDto> addTopic(@RequestBody TopicAddRequest request){
        TopicDto topicDto = topicService.addTopic(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicDto);
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable(name = "topicId") Short topicId,
                                                @RequestBody TopicUpdateRequest request){
        TopicDto topicDto = topicService.updateTopic(topicId, request);
        return ResponseEntity.ok(topicDto);
    }
}
