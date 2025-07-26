package io.github.jsousa32.libdealsign.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public final class HeadersUtils {

    private final static String BEARER = "Bearer ";

    private HeadersUtils() {
    }

    public static <T> HttpEntity<T> generate(
            final String aBearer,
            final T body
    ) {
        final var headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION, BEARER.concat(aBearer));

        return new HttpEntity<>(body, headers);
    }
}
