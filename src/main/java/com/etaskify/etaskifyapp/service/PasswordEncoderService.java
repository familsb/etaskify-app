package com.etaskify.etaskifyapp.service;

public interface PasswordEncoderService {
    String bcryptEncryptor(String plainText);
}
