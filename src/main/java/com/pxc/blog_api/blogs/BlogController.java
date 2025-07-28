package com.pxc.blog_api.blogs;

import com.pxc.blog_api.blogs.dtos.*;
import com.pxc.blog_api.blogs.exceptions.BlogNotFoundException;
import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.blogs.services.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public BlogsWithPage getBlogs(@RequestParam(name = "pageNo") Integer pageNo,
                                  @RequestParam(name = "topicId") Short topicId){
        return blogService.getBlogs(pageNo, topicId);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable(name = "blogId") Integer blogId){
        BlogDto blogDto = blogService.getBlog(blogId);
        return ResponseEntity.ok(blogDto);
    }

    @GetMapping("/latest3blogs")
    public List<BlogCardDto> latest3Blogs(){
        return blogService.getLatest3Blogs();
    }

    @GetMapping("/all-content")
    public AllContent latestAllContent(){
        return blogService.getAllContent();
    }

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBlogNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "blog not found"));
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTopicNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "topic not found"));
    }
}
