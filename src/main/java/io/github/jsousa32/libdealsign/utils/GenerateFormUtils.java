package io.github.jsousa32.libdealsign.utils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public final class GenerateFormUtils {

    private GenerateFormUtils() {
    }

    public static MultipartBodyBuilder generate() {
        return new MultipartBodyBuilder();
    }

    public static MultipartBodyBuilder generate(final MultipartFile aFile) throws IOException {
        String filename = null;

        final var resource = new ByteArrayResource(aFile.getBytes()) {
            @Override
            public String getFilename() {
                return filename;
            }
        };

        final var builder = new MultipartBodyBuilder();

        builder.part("name", Optional
                .ofNullable(aFile.getOriginalFilename())
                .orElse("Arquivo_".concat(UUID.randomUUID().toString())));
        builder.part("file", resource);

        return builder;
    }
}
