package com.demo.spring_security.validators;

import com.demo.spring_security.DTO.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<CustomValidMatchingPassword, Object> {
    @Override
    public void initialize(CustomValidMatchingPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) obj;
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}