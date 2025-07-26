package io.github.jsousa32.libdealsign.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(body, headers);
    }

    public static HttpEntity<Object> generate(
            final String aBearer
    ) {
        final var headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION, BEARER.concat(aBearer));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(headers);
    }
}
