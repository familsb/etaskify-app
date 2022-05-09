package com.etaskify.etaskifyapp.service.impl;

import com.etaskify.etaskifyapp.config.JwtTokenUtil;
import com.etaskify.etaskifyapp.dto.AuthRequest;
import com.etaskify.etaskifyapp.dto.AuthToken;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.exception.AppException;
import com.etaskify.etaskifyapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthToken authenticate(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (Exception e) {
            throw new AppException(AppMessage.USERNAME_OR_PASSWORD_INCORRECT);
        }

        String token = jwtTokenUtil.generateToken(authRequest.getEmail());

        return AuthToken
                .builder()
                .token(token)
                .username(authRequest.getEmail())
                .build();
    }
}
