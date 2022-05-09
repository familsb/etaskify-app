package com.etaskify.etaskifyapp.mapper;

import com.etaskify.etaskifyapp.dto.UserOwnerDto;
import com.etaskify.etaskifyapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserOwnerMapper {
    UserOwnerMapper INSTANCE = Mappers.getMapper(UserOwnerMapper.class);

    User toEntity(UserOwnerDto userOwnerDto);

    UserOwnerDto toDto(User user);
}
