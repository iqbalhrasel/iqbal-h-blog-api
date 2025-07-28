package com.pxc.blog_api.admin;

import com.pxc.blog_api.blogs.dtos.BlogDraftDto;
import com.pxc.blog_api.blogs.dtos.BlogSubmitRequest;
import com.pxc.blog_api.blogs.dtos.BlogDraftResponse;
import com.pxc.blog_api.blogs.exceptions.BlogNotFoundException;
import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.blogs.services.BlogService;
import com.pxc.blog_api.users.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/blogs")
public class AdminBlogController {
    private final BlogService blogService;

    public AdminBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable(name = "blogId") Integer blogId){
        blogService.deleteBlog(blogId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/draft")
    public ResponseEntity<BlogDraftResponse> addBlogToDraft(@RequestBody BlogSubmitRequest request){
        BlogDraftResponse response = blogService.addBlogToDraft(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/draft")
    public List<BlogDraftResponse> getAllDraftedBlogs(){
        return blogService.getAllDraftedBlogs();
    }

    @GetMapping("/draft/{blogId}")
    public ResponseEntity<BlogDraftDto> getDraftedBlog(@PathVariable(name = "blogId") Integer blogId){
        BlogDraftDto blogDraftDto = blogService.getDraftedBlog(blogId);

        return ResponseEntity.ok(blogDraftDto);
    }

    @PutMapping("/draft/{blogId}")
    public ResponseEntity<BlogDraftResponse> updateDraftedBlog(@PathVariable(name = "blogId") Integer blogId,
                                                               @RequestBody BlogSubmitRequest request){
        BlogDraftResponse blogDraftDto = blogService.updateDraftedBlog(blogId, request);

        return ResponseEntity.ok(blogDraftDto);
    }

    @PostMapping("/draft/{blogId}/publish-content")
    public ResponseEntity<Void> publishDraftedBlogByContent(@PathVariable(name = "blogId") Integer blogId,
                                                                           @RequestBody BlogSubmitRequest request){
        blogService.publishDraftedBlogByContent(blogId, request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/draft/{blogId}/publish")
    public ResponseEntity<Void> publishDraftedBlogById(@PathVariable(name = "blogId") Integer blogId){
        blogService.publishDraftedBlogById(blogId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/publish")
    public ResponseEntity<Void> publishBlog(@RequestBody BlogSubmitRequest request){
        blogService.publishBlog(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/publish/{blogId}")
    public ResponseEntity<Void> updatePublishedBlog(@PathVariable(name = "blogId") Integer blogId,
                                                     @RequestBody BlogSubmitRequest request){
        blogService.updatePublishedBlog(blogId, request);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTopicNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "topic not found"));
    }

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBlogNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "blog not found"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "user not found"));
    }
}
