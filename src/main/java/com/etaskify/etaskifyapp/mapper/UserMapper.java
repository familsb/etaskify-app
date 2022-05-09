package com.etaskify.etaskifyapp.mapper;

import com.etaskify.etaskifyapp.dto.UserDto;
import com.etaskify.etaskifyapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> toUserDtoList(List<User> users);

    List<User> toUserList(List<UserDto> userDtos);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
