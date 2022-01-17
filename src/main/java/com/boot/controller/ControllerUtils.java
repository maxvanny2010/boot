package com.boot.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

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

    public static String savePhoto(final MultipartFile file) throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        return Base64.getEncoder().encodeToString(bis.readAllBytes());
    }
}
