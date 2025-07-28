package com.pxc.blog_api.blogs.services;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.pxc.blog_api.blogs.dtos.CommentDto;
import com.pxc.blog_api.blogs.dtos.CommentRequest;
import com.pxc.blog_api.blogs.dtos.HoldTokenDto;
import com.pxc.blog_api.blogs.exceptions.BlogNotFoundException;
import com.pxc.blog_api.blogs.exceptions.BotSuspectException;
import com.pxc.blog_api.blogs.mappers.CommentMapper;
import com.pxc.blog_api.blogs.models.Comment;
import com.pxc.blog_api.blogs.repositories.BlogRepository;
import com.pxc.blog_api.blogs.repositories.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommentService {
    private final BlogRepository blogRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentService(BlogRepository blogRepository,
                          CommentMapper commentMapper, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    private final Cache<String, String> tokenStore = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(5))
            .maximumSize(100)
            .build();

    public CommentDto addComment(CommentRequest commentRequest, HttpServletRequest servletRequest) {
        String ip = servletRequest.getRemoteAddr();
        String storedIp = tokenStore.getIfPresent(commentRequest.getHoldToken());

        if(storedIp != null && storedIp.equals(ip)){
            tokenStore.invalidate(commentRequest.getHoldToken());
        }
        else throw new BotSuspectException();

        var blog = blogRepository.findById(commentRequest.getBlogId()).orElseThrow(BlogNotFoundException::new);

        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setContent(commentRequest.getContent());

        commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    public HoldTokenDto issueToken(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String token = UUID.randomUUID().toString();

        tokenStore.put(token, ip);

        return new HoldTokenDto(token);
    }
}
