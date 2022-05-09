package com.etaskify.etaskifyapp.service.impl;

import com.etaskify.etaskifyapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendMail(String subject, String text, Collection<String> recipients) {

    }
}
