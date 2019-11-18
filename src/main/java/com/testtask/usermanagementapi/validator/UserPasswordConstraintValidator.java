package com.testtask.usermanagementapi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserPasswordConstraintValidator implements ConstraintValidator<UserPasswordSymbolsConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(".*\\d+.*") && value.matches(".*[a-zA-Z]+.*");
    }
}
