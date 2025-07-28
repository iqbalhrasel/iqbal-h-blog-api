package com.pxc.blog_api.blogs.mappers;

import com.pxc.blog_api.blogs.dtos.CommentDto;
import com.pxc.blog_api.blogs.dtos.CommentRequest;
import com.pxc.blog_api.blogs.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    void update(CommentRequest request, @MappingTarget Comment comment);
}
