package com.testtask.usermanagementapi.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserPasswordConstraintValidatorTest {

    UserPasswordConstraintValidator validator = new UserPasswordConstraintValidator();

    @Test
    void noDigitsNoLetters() {
        assertThat(validator.isValid("--.", null))
                .isFalse();
    }

    @Test
    void onlyDigits() {
        assertThat(validator.isValid("321", null))
                .isFalse();
    }

    @Test
    void onlyLetters() {
        assertThat(validator.isValid("fgdsgfdgfd", null))
                .isFalse();
    }

    @Test
    void lettersAndDigits() {
        assertThat(validator.isValid("1a.", null))
                .isTrue();
    }
}