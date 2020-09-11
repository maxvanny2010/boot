package com.boot.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * ControllerUtils.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/5/2020
 */
public class ControllerUtils {

    static Map<String, String> getErrors(final BindingResult bindingResult) {
        final Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
