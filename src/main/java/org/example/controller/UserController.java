package org.example.controller;

import org.example.dto.RegistrationRequestDto;
import org.example.dto.UserResponseDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${api-base-url}/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDto> register(@RequestBody RegistrationRequestDto registrationRequestDto, HttpServletRequest request) throws ServletException {
        return ResponseEntity.ok(userService.add(registrationRequestDto, request));
    }
}
