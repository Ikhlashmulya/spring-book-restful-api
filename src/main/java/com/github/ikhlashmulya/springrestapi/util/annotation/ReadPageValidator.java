package com.github.ikhlashmulya.springrestapi.util.annotation;

import com.github.ikhlashmulya.springrestapi.model.CreateBookRequest;
import com.github.ikhlashmulya.springrestapi.model.UpdateBookRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ReadPageValidator implements ConstraintValidator<ReadPageNotExceedPageCount, Object> {
    @Override
    public void initialize(ReadPageNotExceedPageCount constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof UpdateBookRequest updateBookRequest) {
            return updateBookRequest.getReadPage() <= updateBookRequest.getPageCount();
        }

        if (object instanceof CreateBookRequest createBookRequest) {
            return createBookRequest.getReadPage() <= createBookRequest.getPageCount();
        }

        return true;
    }
}
