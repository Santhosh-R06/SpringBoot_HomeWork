package com.module2.DepartmentManagement.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentPrimeNumberValidator.class)
public @interface DepartmentPrimeNumberValidation {

    String message() default "Prime number only allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
