package com.module2.DepartmentManagement.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentPrimeNumberValidator implements ConstraintValidator<DepartmentPrimeNumberValidation, Integer> {
    @Override
    public boolean isValid(Integer n, ConstraintValidatorContext constraintValidatorContext) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0){
                //System.out.println("false");
                return false;}
        //System.out.println("prime true");
        return true;
    }
}
