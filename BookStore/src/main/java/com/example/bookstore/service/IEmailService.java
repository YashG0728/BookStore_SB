package com.example.bookstore.service;

import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.Email;
import org.springframework.http.ResponseEntity;
public interface IEmailService {
    ResponseEntity<ResponseDto> sendMail(Email email);
}
