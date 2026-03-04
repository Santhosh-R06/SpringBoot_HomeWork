package com.module2.DepartmentManagement.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class DepartmentPasswordValidator implements ConstraintValidator<DepartmentPasswordValidation,String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        String regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).{10,}$";
        boolean flag = Pattern.matches(regexp, password);
        System.out.println(flag);
        return flag;
    }
}
