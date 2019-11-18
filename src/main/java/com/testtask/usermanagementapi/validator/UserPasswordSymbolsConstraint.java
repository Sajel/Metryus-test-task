package com.testtask.usermanagementapi.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy=UserPasswordConstraintValidator.class)
public @interface UserPasswordSymbolsConstraint {
    String message() default "{user.password.symbols}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
