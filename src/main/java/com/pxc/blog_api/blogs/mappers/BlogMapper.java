package com.pxc.blog_api.blogs.mappers;

import com.pxc.blog_api.blogs.dtos.*;
import com.pxc.blog_api.blogs.models.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "topic.name", target = "topicName")
    BlogCardDto toCardDto(Blog blog);

    @Mapping(source = "author.name", target = "authorName")
    BlogDto toDto(Blog blog);

    void updateEntity(BlogSubmitRequest request, @MappingTarget Blog blog);

    @Mapping(target = "id", ignore = true)
    void updateEntityWithoutId(BlogSubmitRequest request, @MappingTarget Blog blog);

    @Mapping(source = "topic.name", target = "topicName")
    BlogDraftResponse toDraftResponse(Blog blog);

    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "id", target = "blogId")
    BlogDraftDto toDraftDto(Blog blog);
}
