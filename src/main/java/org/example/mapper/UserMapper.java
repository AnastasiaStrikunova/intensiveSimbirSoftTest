package org.example.mapper;

import org.example.dto.RegistrationRequestDto;
import org.example.dto.UserResponseDto;
import org.example.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity registrationRequestDtoToUserEntity(RegistrationRequestDto registrationRequestDto);
    UserResponseDto userEntityToUserResponseDto(UserEntity userEntity);
}
