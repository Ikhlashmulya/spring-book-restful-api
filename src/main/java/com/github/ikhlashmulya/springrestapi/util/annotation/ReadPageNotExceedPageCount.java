package com.github.ikhlashmulya.springrestapi.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReadPageValidator.class)
public @interface ReadPageNotExceedPageCount {
    String message() default "readPage tidak boleh lebih besar dari pageCount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
