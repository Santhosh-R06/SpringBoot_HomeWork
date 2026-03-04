package com.module2.DepartmentManagement.services;


import com.module2.DepartmentManagement.dto.DepartmentDTO;
import com.module2.DepartmentManagement.entities.DepartmentEntity;
import com.module2.DepartmentManagement.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    private DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public Boolean isDepartmentExsist(Long deptId){
        return departmentRepository.existsById(deptId);
    }

    public DepartmentDTO createDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSavedepartmentEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(toSavedepartmentEntity);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO updateDepartment, Long id) {

        DepartmentEntity toUpdatedDepartmentEntity = modelMapper.map(updateDepartment, DepartmentEntity.class);
        toUpdatedDepartmentEntity.setId(id);
        DepartmentEntity savedDepartment = departmentRepository.save(toUpdatedDepartmentEntity);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public Boolean deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDTO partialUpdateDepartmentById(Long id, Map<String, Object> updateDepartment) {
        DepartmentEntity toUpdatedDepartmentEntity = departmentRepository.findById(id).get();
        updateDepartment.forEach((field, value) -> {
            Field fieldToBeUpdate = ReflectionUtils.findField(DepartmentEntity.class, field);
            assert fieldToBeUpdate != null;
            fieldToBeUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdate, toUpdatedDepartmentEntity, value);
        });
        return modelMapper.map(departmentRepository.save(toUpdatedDepartmentEntity), DepartmentDTO.class);
    }
}
