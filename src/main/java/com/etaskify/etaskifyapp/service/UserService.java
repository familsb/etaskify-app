package com.etaskify.etaskifyapp.service;

import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.UserDto;

import java.util.List;

public interface UserService {
    ResponseDto saveOrUpdateUser(UserDto userDto);
    List<UserDto> organizationUsers();

}
