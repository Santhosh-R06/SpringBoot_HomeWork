package com.codingShuttle.SpringSecurity1.services.impl;

import com.codingShuttle.SpringSecurity1.dto.SessionDto;
import com.codingShuttle.SpringSecurity1.entities.SessionEntity;
import com.codingShuttle.SpringSecurity1.entities.UserEntity;
import com.codingShuttle.SpringSecurity1.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl {

    Logger log = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ModelMapper modelMapper;


    public SessionDto setSessionForLoginWithUserId(SessionDto sessionDto) {
        log.info("SessionServiceImpl setSessionForLoginWithUserId");
        List<SessionEntity> userEntities = sessionRepository.findByUserId(sessionDto.getUserId());
        sessionRepository.deleteAllInBatch(userEntities);
        SessionEntity sessionEntity = modelMapper.map(sessionDto, SessionEntity.class);
        sessionEntity = sessionRepository.save(sessionEntity);
        log.info("Session saved for userId {}", sessionDto.getUserId());
        log.info("Saved Session Entity {}", sessionEntity);
        return modelMapper.map(sessionEntity, SessionDto.class);

    }


    public Boolean checkTokenValid(UserEntity user, String token) {

        log.info("SessionServiceImpl checkTokenValid");
        List<SessionEntity> sessionEntities = sessionRepository.findByUserId(user.getEmail());

        boolean isTokenValid = sessionEntities
                .stream()
                .anyMatch(session -> session.getToken().equals(token));

        log.info("SessionServiceImpl checkTokenValid isTokenValid {}", isTokenValid);
        return isTokenValid;
    }
}
