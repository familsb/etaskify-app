package com.etaskify.etaskifyapp.service.impl;

import com.etaskify.etaskifyapp.service.PasswordEncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String bcryptEncryptor(String plainText) {
        return passwordEncoder.encode(plainText);
    }
}
