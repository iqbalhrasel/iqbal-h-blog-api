package com.pxc.blog_api.blogs.repositories;

import com.pxc.blog_api.blogs.models.Blog;
import com.pxc.blog_api.blogs.models.ContentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @EntityGraph(attributePaths = {"author", "topic"})
    @Query("SELECT b FROM Blog b WHERE b.status = 'PUBLISHED' ORDER BY b.createdAt DESC LIMIT :limit")
    List<Blog> getLatestLimitBlogs( int limit);

    @EntityGraph(attributePaths = {"author", "topic"})
    @Query("SELECT b FROM Blog b WHERE b.topic.id=:topicId AND b.status = 'PUBLISHED' ORDER BY b.createdAt DESC LIMIT :limit")
    List<Blog> getLatestLimitBlogsByTopic(int topicId ,int limit);

    @EntityGraph(attributePaths = {"author", "topic", "comments"})
    @Query("SELECT b FROM Blog b WHERE b.id=:blogId AND b.status = 'PUBLISHED'")
    Optional<Blog> getBlogWithAuthorTopic(@Param(value = "blogId") Integer blogId);

    @EntityGraph(attributePaths = {"author"})
    Page<Blog> findByStatusAndTopicId(ContentStatus status, Short topicId, Pageable pageable);

    @EntityGraph(attributePaths = {"author", "topic"})
    Page<Blog> findByStatus(ContentStatus status, Pageable pageable);

    @Query("SELECT b FROM Blog b WHERE b.status = 'DRAFT'")
    List<Blog> getAllDraftedBlogs();
}
