package org.example.service;

import org.example.dto.RegistrationRequestDto;
import org.example.dto.UserResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
    UserResponseDto add(RegistrationRequestDto registrationRequestDto, HttpServletRequest request) throws ServletException;
}
