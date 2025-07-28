package com.pxc.blog_api.iqs.mappers;

import com.pxc.blog_api.iqs.dtos.InterviewQuestionDto;
import com.pxc.blog_api.iqs.dtos.IqDraftDto;
import com.pxc.blog_api.iqs.dtos.IqDraftResponse;
import com.pxc.blog_api.iqs.models.InterviewQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IqMapper {
    @Mapping(source = "topic.name", target = "topicName")
    InterviewQuestionDto toDto (InterviewQuestion question);

    @Mapping(source = "topic.id", target = "topicId")
    IqDraftDto toDraftDto (InterviewQuestion question);

    @Mapping(source = "topic.name", target = "topicName")
    IqDraftResponse toDraftResponse (InterviewQuestion question);
}
