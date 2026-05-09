package com.mchiir.starterkit.modules.user.mapper;

import com.mchiir.starterkit.modules.user.dto.CreateUserRequest;
import com.mchiir.starterkit.modules.user.dto.UserDto;
import com.mchiir.starterkit.modules.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(CreateUserRequest request);
}