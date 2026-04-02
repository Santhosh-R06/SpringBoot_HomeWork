package com.codingShuttle.SpringSecurity1.controller;


import com.codingShuttle.SpringSecurity1.dto.CubeDto;
import com.codingShuttle.SpringSecurity1.services.CubeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cubes")
@RequiredArgsConstructor
public class CubeController {

    private final CubeService cubeService;

    Logger log = LoggerFactory.getLogger(CubeController.class);

    @GetMapping
    public List<CubeDto> getAllCubes() {
        log.info("getAllCubes");
        return cubeService.getAllCubes();
    }

    @GetMapping(path = "/{cubeId}")
    public CubeDto getCubeById(@PathVariable("cubeId") Long cubeId) {
        log.info("getCubeById");
        return cubeService.getCubeById(cubeId);
    }

    @PostMapping
    public CubeDto createCube(@RequestBody CubeDto cubeDto) {
        log.info("createCube");
        return cubeService.createNewCube(cubeDto);
    }


}
