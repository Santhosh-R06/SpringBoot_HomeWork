package com.codingShuttle.SpringSecurity1.services;

import com.codingShuttle.SpringSecurity1.dto.SignUpDto;
import com.codingShuttle.SpringSecurity1.dto.UserDto;
import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    UserDto signUp(SignUpDto signUpDto);

    UserEntity getUserByUserId(Long userId);
}
