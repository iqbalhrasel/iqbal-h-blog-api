package com.pxc.blog_api.iqs.services;

import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.blogs.models.ContentStatus;
import com.pxc.blog_api.blogs.repositories.TopicRepository;
import com.pxc.blog_api.iqs.InterviewQuestionNotFoundException;
import com.pxc.blog_api.iqs.dtos.InterviewAndPages;
import com.pxc.blog_api.iqs.dtos.IqDraftDto;
import com.pxc.blog_api.iqs.dtos.IqDraftResponse;
import com.pxc.blog_api.iqs.dtos.IqSubmitRequest;
import com.pxc.blog_api.iqs.mappers.IqMapper;
import com.pxc.blog_api.iqs.models.InterviewQuestion;
import com.pxc.blog_api.iqs.repositories.IqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InterviewQuestionService {
    private final TopicRepository topicRepository;
    private final IqRepository iqRepository;
    private final IqMapper iqMapper;

    @Value("${perPageSize}")
    private int perPageSize;

    public InterviewAndPages getIqsWithPage(Integer pageNo, Short topicId) {
        if(topicId>0) {
            var topic = topicRepository.findById(topicId).orElse(null);
            if (topic == null)
                throw new TopicNotFoundException();
        }

        int pageIndex ;

        if(pageNo == 1 || pageNo == 0)
            pageIndex = 0;
        else pageIndex = pageNo - 1;

        Pageable pageable = PageRequest.of(pageIndex, perPageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<InterviewQuestion> questions;

        if(topicId == 0){
            questions = iqRepository.findByStatus(ContentStatus.PUBLISHED, pageable);
        } else {
            questions = iqRepository.findByStatusAndTopicId(ContentStatus.PUBLISHED, topicId, pageable);
        }

        var allIqs = questions.getContent();
        int totalPages = questions.getTotalPages();

        var interviewAndPages = new InterviewAndPages();
        interviewAndPages.setQuestions(allIqs.stream().map(iqMapper::toDto).toList());
        interviewAndPages.setTotalPages(totalPages);

        return interviewAndPages;
    }

    public void publishQna(IqSubmitRequest request) {
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        var iq = new InterviewQuestion();
        iq.setTopic(topic);
        iq.setQuestion(request.getQuestion());
        iq.setAnswer(request.getAnswer());
        iq.setStatus(ContentStatus.PUBLISHED);

        iqRepository.save(iq);
    }

    public IqDraftResponse draftQna(IqSubmitRequest request) {
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        var iq = new InterviewQuestion();
        iq.setTopic(topic);
        iq.setQuestion(request.getQuestion());
        iq.setAnswer(request.getAnswer());
        iq.setStatus(ContentStatus.DRAFT);

        iqRepository.save(iq);

        return iqMapper.toDraftResponse(iq);
    }

    public List<IqDraftResponse> getAllDraftedIqs() {
        return iqRepository.getAllDraftedIqs().stream().map(iq -> iqMapper.toDraftResponse(iq)).toList();
    }

    public void publishDraftedQna(Integer iqId) {
        var iq = iqRepository.findById(iqId).orElseThrow(InterviewQuestionNotFoundException::new);
        iq.setStatus(ContentStatus.PUBLISHED);
        iq.setCreatedAt(LocalDateTime.now());

        iqRepository.save(iq);
    }

    public void deleteQna(Integer iqId) {
        var iq = iqRepository.findById(iqId).orElseThrow(InterviewQuestionNotFoundException::new);

        iqRepository.delete(iq);
    }

    public IqDraftDto getDraftedIq(Integer iqId) {
        var iq = iqRepository.findById(iqId).orElseThrow(InterviewQuestionNotFoundException::new);
        return iqMapper.toDraftDto(iq);
    }

    public IqDraftResponse updateQna(Integer iqId, IqSubmitRequest request) {
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        var iq = iqRepository.findById(iqId).orElseThrow(InterviewQuestionNotFoundException::new);
        iq.setTopic(topic);
        iq.setQuestion(request.getQuestion());
        iq.setAnswer(request.getAnswer());

        iqRepository.save(iq);

        return iqMapper.toDraftResponse(iq);
    }

    public void publishDraftQna(Integer iqId, IqSubmitRequest request) {
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        var iq = iqRepository.findById(iqId).orElseThrow(InterviewQuestionNotFoundException::new);
        iq.setTopic(topic);
        iq.setQuestion(request.getQuestion());
        iq.setAnswer(request.getAnswer());
        iq.setStatus(ContentStatus.PUBLISHED);
        iq.setCreatedAt(LocalDateTime.now());

        iqRepository.save(iq);
    }

}
