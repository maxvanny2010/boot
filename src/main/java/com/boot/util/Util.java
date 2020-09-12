package com.boot.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Util.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/12/2020
 */
public final class Util {
    private Util() {
    }

    public static String getPhoto(MultipartFile file) throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
        return Base64.getEncoder().encodeToString(bis.readAllBytes());
    }
}
