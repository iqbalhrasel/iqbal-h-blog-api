package com.pxc.blog_api.blogs;

import com.pxc.blog_api.blogs.dtos.CommentDto;
import com.pxc.blog_api.blogs.dtos.CommentRequest;
import com.pxc.blog_api.blogs.dtos.HoldTokenDto;
import com.pxc.blog_api.blogs.exceptions.BlogNotFoundException;
import com.pxc.blog_api.blogs.exceptions.BotSuspectException;
import com.pxc.blog_api.blogs.services.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentRequest commentRequest, HttpServletRequest servletRequest){
        CommentDto commentDto = commentService.addComment(commentRequest, servletRequest);
        return ResponseEntity.ok(commentDto);
    }

    @PostMapping("/hold")
    public ResponseEntity<HoldTokenDto> issueToken(HttpServletRequest request){
        HoldTokenDto holdTokenDto = commentService.issueToken(request);
        return ResponseEntity.ok(holdTokenDto);
    }

    @ExceptionHandler(BotSuspectException.class)
    public ResponseEntity<Map<String, String>> handleBotSuspect(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "suspected bot."));
    }

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBlogNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "blog not found"));
    }
}
