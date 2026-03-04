package com.module2.DepartmentManagement.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class DepartmentLocationValidator implements ConstraintValidator<DepartmentLocationValidation, String> {

    @Override
    public boolean isValid(String inputLocation, ConstraintValidatorContext constraintValidatorContext) {
        if (inputLocation == null) {return false;}
        List<String> locations = List.of("REMOTE", "LOCAL");
        return locations.contains(inputLocation);
    }
}
