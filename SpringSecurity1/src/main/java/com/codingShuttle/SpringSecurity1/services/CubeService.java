package com.codingShuttle.SpringSecurity1.services;

import com.codingShuttle.SpringSecurity1.dto.CubeDto;

import java.util.List;

public interface CubeService {
    List<CubeDto> getAllCubes();

    CubeDto createNewCube(CubeDto cubeDto);

    CubeDto getCubeById(Long cubeId);
}
