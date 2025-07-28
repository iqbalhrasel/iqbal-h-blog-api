package com.pxc.blog_api.blogs.mappers;

import com.pxc.blog_api.blogs.dtos.TopicDto;
import com.pxc.blog_api.blogs.models.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicDto toDto(Topic topic);
}
