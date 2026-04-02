package com.codingShuttle.SpringSecurity1.services.impl;

import com.codingShuttle.SpringSecurity1.dto.SignUpDto;
import com.codingShuttle.SpringSecurity1.dto.UserDto;
import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import com.codingShuttle.SpringSecurity1.exceptions.ResourceAlreadyExistsExceptions;
import com.codingShuttle.SpringSecurity1.exceptions.ResourceNotFoundException;
import com.codingShuttle.SpringSecurity1.repositories.UserRepository;
import com.codingShuttle.SpringSecurity1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("UserServiceImpl loadUserByUsername is called");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User with email " + email + " not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Service getAllUsers");
        List<UserDto> userEntities = userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        log.info("getAllUsers user count: {}", (long) userEntities.size());
        log.info("getAllUsers done");
        return userEntities;
    }

    @Override
    public UserDto getUserById(Long userId) {
        log.info("Service getUserById");
        log.info("getUserById for userId: {}", userId);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + userId + " not found"));
        log.info("getUserById user entity: {}", userEntity);
        log.info("getUserById done");
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        log.info("Service signUp");
        Optional<UserEntity> user = userRepository.findByEmail(signUpDto.getEmail());

        if (user.isPresent()) {
            log.error("User with email {} already exists", signUpDto.getEmail());
            throw new ResourceAlreadyExistsExceptions("User with email " + signUpDto.getEmail() + " already exists");
        }

        UserEntity toBeCreated = modelMapper.map(signUpDto, UserEntity.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));
        UserEntity savedEntity = userRepository.save(toBeCreated);
        log.info("User created: {}", savedEntity);
        log.info("Created user Id: {}", savedEntity.getId());
        log.info("User created done");
        return modelMapper.map(savedEntity, UserDto.class);

    }

    @Override
    public UserEntity getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }


}
