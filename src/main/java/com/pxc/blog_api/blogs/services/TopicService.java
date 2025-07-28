package com.pxc.blog_api.blogs.services;

import com.pxc.blog_api.blogs.dtos.TopicAddRequest;
import com.pxc.blog_api.blogs.dtos.TopicDto;
import com.pxc.blog_api.blogs.dtos.TopicUpdateRequest;
import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.blogs.mappers.TopicMapper;
import com.pxc.blog_api.blogs.models.Topic;
import com.pxc.blog_api.blogs.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<TopicDto> getAllTopics() {
        return topicRepository.findAllByOrderByIdAsc().stream().map(topicMapper::toDto).toList();
    }

    public TopicDto addTopic(TopicAddRequest request) {
        var topic = new Topic();
        topic.setName(request.getName());

        topicRepository.save(topic);

        return topicMapper.toDto(topic);
    }

    public TopicDto updateTopic(Short topicId, TopicUpdateRequest request) {
        var topic = topicRepository.findById(topicId).orElseThrow(TopicNotFoundException::new);
        topic.setName(request.getName());

        topicRepository.save(topic);

        return topicMapper.toDto(topic);
    }
}
