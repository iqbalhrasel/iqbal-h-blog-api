package com.pxc.blog_api.blogs.repositories;

import com.pxc.blog_api.blogs.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
