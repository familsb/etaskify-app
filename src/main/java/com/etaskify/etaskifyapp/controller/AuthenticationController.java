package com.etaskify.etaskifyapp.controller;

import com.etaskify.etaskifyapp.dto.ApiResponse;
import com.etaskify.etaskifyapp.dto.AuthRequest;
import com.etaskify.etaskifyapp.dto.AuthToken;
import com.etaskify.etaskifyapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ApiResponse<AuthToken> signIn(@RequestBody @Valid AuthRequest authRequest) {
        return ApiResponse.withSuccess(authenticationService.authenticate(authRequest));
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ApiResponse<AuthToken> signInD(@RequestBody @Valid AuthRequest authRequest) {
        return ApiResponse.withSuccess(authenticationService.authenticate(authRequest));
    }
}
