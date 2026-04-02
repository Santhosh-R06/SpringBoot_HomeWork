package com.codingShuttle.SpringSecurity1.services.impl;

import com.codingShuttle.SpringSecurity1.dto.CubeDto;
import com.codingShuttle.SpringSecurity1.entities.CubeEntity;
import com.codingShuttle.SpringSecurity1.exceptions.ResourceNotFoundException;
import com.codingShuttle.SpringSecurity1.repositories.CubeRepository;
import com.codingShuttle.SpringSecurity1.services.CubeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CubeServiceImpl implements CubeService {

    @Autowired
    private CubeRepository cubeRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(CubeServiceImpl.class);

    @Override
    public List<CubeDto> getAllCubes() {
        log.info("getAllCubes");
        log.info("CubeServiceImpl.getAllCubes()");
        List<CubeDto> cubeEntities = cubeRepository
                .findAll()
                .stream()
                .map(cubeEntity -> modelMapper.map(cubeEntity, CubeDto.class))
                .collect(Collectors.toList());

        log.info("CubeServiceImpl.getAllCubes(): {}", cubeEntities);
        return cubeEntities;
    }

    @Override
    public CubeDto createNewCube(CubeDto cubeDto) {
        log.info("createCube");
        log.info("CubeServiceImpl.createCube(): {}", cubeDto);
        CubeEntity cubeEntity = modelMapper.map(cubeDto, CubeEntity.class);
        cubeEntity = cubeRepository.save(cubeEntity);
        log.info("CubeEntity: {}", cubeEntity);
        return modelMapper.map(cubeEntity, CubeDto.class);
    }

    @Override
    public CubeDto getCubeById(Long cubeId) {
        log.info("getCubeById");
        CubeEntity cubeEntity = cubeRepository
                .findById(cubeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cube with id: " + cubeId + " not found"));
        log.info("CubeEntity by getCubeById: {}", cubeEntity);
        return modelMapper.map(cubeEntity, CubeDto.class);
    }
}
