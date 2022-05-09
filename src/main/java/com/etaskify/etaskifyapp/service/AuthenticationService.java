package com.etaskify.etaskifyapp.service;

import com.etaskify.etaskifyapp.dto.AuthRequest;
import com.etaskify.etaskifyapp.dto.AuthToken;

public interface AuthenticationService {
    AuthToken authenticate(AuthRequest authRequest);
}
