package com.pxc.blog_api.iqs.repositories;

import com.pxc.blog_api.blogs.models.ContentStatus;
import com.pxc.blog_api.iqs.dtos.IqDraftResponse;
import com.pxc.blog_api.iqs.models.InterviewQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IqRepository extends JpaRepository<InterviewQuestion, Integer> {
    @EntityGraph(attributePaths = {"topic"})
    @Query("SELECT iq FROM InterviewQuestion iq WHERE iq.status = 'PUBLISHED' ORDER BY iq.createdAt DESC LIMIT :limit")
    List<InterviewQuestion> getLatestLimitIqs(int limit);

    Page<InterviewQuestion> findByStatusAndTopicId(ContentStatus status, Short topicId, Pageable pageable);

    @EntityGraph(attributePaths = {"topic"})
    Page<InterviewQuestion> findByStatus(ContentStatus status, Pageable pageable);

    @Query("SELECT iq FROM InterviewQuestion iq WHERE iq.status = 'DRAFT' ORDER BY iq.createdAt DESC")
    List<InterviewQuestion> getAllDraftedIqs();
}
