package org.example.service.impl;

import org.example.dto.RegistrationRequestDto;
import org.example.dto.UserResponseDto;
import org.example.entity.UserEntity;
import org.example.exception.NotFoundException;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.security.Role;
import org.example.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponseDto add(RegistrationRequestDto registrationRequestDto, HttpServletRequest request) throws ServletException {
        dataValidation(registrationRequestDto);
        UserEntity userEntity = userMapper.registrationRequestDtoToUserEntity(registrationRequestDto);
        userEntity.setRole(Role.USER);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        //request.login("admin", "admin"); //при успешной регистрации пользователь становится авторизованным
        request.login(registrationRequestDto.getEmail(), registrationRequestDto.getPassword());
        return userMapper.userEntityToUserResponseDto(userEntity);
    }

    private void dataValidation(RegistrationRequestDto registrationRequestDto) {
        if (!registrationRequestDto.getPassword().equals(registrationRequestDto.getRepeatPassword())) {
            throw new NotFoundException("Пароли не совпадают");
        }
        if (userRepository.countByEmail(registrationRequestDto.getEmail()) > 0) {
            throw new NotFoundException(String.format("email = %s уже занят", registrationRequestDto.getEmail()));
        }
    }
}
