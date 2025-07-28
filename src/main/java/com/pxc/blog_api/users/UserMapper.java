package com.pxc.blog_api.users;

import com.pxc.blog_api.auth.dtos.SignupRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest request);
}
