package com.etaskify.etaskifyapp.controller;

import com.etaskify.etaskifyapp.dto.ApiResponse;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.UserDto;
import com.etaskify.etaskifyapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/save")
    public ApiResponse<ResponseDto> signUpOrganization(@Valid @RequestBody UserDto userDto) {
        return ApiResponse.withSuccess(userService.saveOrUpdateUser(userDto));
    }

    @GetMapping(value = "/organization-profile/users")
    public ApiResponse<List<UserDto>> organizationUsers() {
        return ApiResponse.withSuccess(userService.organizationUsers());
    }

}
