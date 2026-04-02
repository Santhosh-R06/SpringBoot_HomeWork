package com.codingShuttle.SpringSecurity1.services.impl;

import com.codingShuttle.SpringSecurity1.dto.LoginDto;
import com.codingShuttle.SpringSecurity1.dto.SessionDto;
import com.codingShuttle.SpringSecurity1.dto.UserDto;
import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtServiceImpl;

    private final SessionServiceImpl sessionServiceImpl;


    Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    public String login(LoginDto loginDto) {
        log.info("login AuthServiceImpl");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                        loginDto.getPassword())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        String token = jwtServiceImpl.generateJwtToken(userEntity);
        log.info("token AuthServiceImpl");
        log.info("Jwt token {}", token);

        SessionDto sessionDto = SessionDto.builder()
                .userId(loginDto.getEmail())
                .token(token)
                .build();
        log.info("Session Dto {}", sessionDto);
        SessionDto sessionDto1 = sessionServiceImpl.setSessionForLoginWithUserId(sessionDto);
        log.info("Session AuthServiceImpl");
        log.info("Session dto AuthServiceImpl setSessionForLoginWithUserId");
        log.info("Session Dto {}", sessionDto);
        log.info("Session Dto1 {}", sessionDto1);

        return token;
    }
}
