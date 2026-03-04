package com.module2.DepartmentManagement.controllers;



import com.module2.DepartmentManagement.dto.DepartmentDTO;
import com.module2.DepartmentManagement.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO savedDepartment = departmentService.createDepartment(inputDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody DepartmentDTO updateDepartment,  @PathVariable Long id) {

        return ResponseEntity.ok(departmentService.updateDepartmentById(updateDepartment,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id) {
        boolean isDeptDeleted = departmentService.deleteDepartmentById(id);
        if (isDeptDeleted) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "id") Long Id) {

        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(Id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok().body(departmentDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> partialUpdateDepartmentById(@PathVariable(name = "id") Long Id,
                                                                     @RequestBody Map<String, Object> updateDepartment) {
        DepartmentDTO departmentDTO = departmentService.partialUpdateDepartmentById(Id,updateDepartment);
        if (departmentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(departmentDTO);
    }


}
