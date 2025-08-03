package io.github.jsousa32.libdealsign.utils;


import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;


public final class RequestUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestUtils.class);

    private static final RestTemplate rest = new RestTemplate();

    private RequestUtils() {
    }

    public static RestTemplate getInstance() {
        return new RestTemplate();
    }

    public static <R> void post(
            final String aBearer,
            final String anUlr,
            final R anInput
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer, anInput);

        rest.exchange(anUlr, HttpMethod.POST, httpEntity, Void.class);
    }

    public static <T, R> Optional<T> post(
            final String aBearer,
            final String anUlr,
            final R anInput,
            final Class<T> aResponseType
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer, anInput);

        return Optional.of(rest.exchange(anUlr, HttpMethod.POST, httpEntity, aResponseType))
                .map(ResponseEntity::getBody);
    }

    public static <T, R> Optional<List<T>> post(
            final String aBearer,
            final String anUlr,
            final R anInput,
            final ParameterizedTypeReference<List<T>> responseType
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer, anInput);

        return Optional.of(rest.exchange(anUlr, HttpMethod.POST, httpEntity, responseType))
                .map(ResponseEntity::getBody);
    }

    public static <T, R> Optional<T> get(
            final String aBearer,
            final String anUlr,
            final R anInput,
            final Class<T> aResponseType
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer, anInput);

        return Optional.of(rest.exchange(anUlr, HttpMethod.GET, httpEntity, aResponseType))
                .map(ResponseEntity::getBody);
    }

    public static <T> Optional<List<T>> get(
            final String aBearer,
            final String anUlr,
            final ParameterizedTypeReference<List<T>> responseType
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer);

        return Optional.of(rest.exchange(anUlr, HttpMethod.GET, httpEntity, responseType))
                .map(ResponseEntity::getBody);
    }

    public static <T, R> Optional<List<T>> get(
            final String aBearer,
            final String anUlr,
            final R anInput,
            final ParameterizedTypeReference<List<T>> responseType
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer, anInput);

        return Optional.of(rest.exchange(anUlr, HttpMethod.GET, httpEntity, responseType))
                .map(ResponseEntity::getBody);
    }

    public static void delete(
            final String aBearer,
            final String anUlr
    ) {
        final var httpEntity = HeadersUtils.generate(aBearer);

        rest.exchange(anUlr, HttpMethod.DELETE, httpEntity, Void.class);
    }

    public static <T> Optional<T> patch(
            final String aBearer,
            final String anUlr,
            final Object anInput,
            final Class<T> aResponseType
    ) {
        final var req = HttpRequest
                .newBuilder()
                .uri(URI.create(anUlr))
                .method(HttpMethod.PATCH.name(), HttpRequest.BodyPublishers.ofString(ConverterUtils.toString(anInput)))
                .header("Authorization", "Bearer " + aBearer)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();

        try {
            final var res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
            return Optional.of(ConverterUtils.toClass(res.body(), aResponseType));
        } catch (IOException | InterruptedException ex) {
            log.error("Não foi possível realizar a requisição {}", ex.getMessage());
            throw DealsignException.generate("Não foi possível realizar a requisição.");
        }
    }


    public static void patch(
            final String aBearer,
            final String anUlr,
            final Object anInput
    ) {
        final var req = HttpRequest
                .newBuilder()
                .uri(URI.create(anUlr))
                .method(HttpMethod.PATCH.name(), HttpRequest.BodyPublishers.ofString(ConverterUtils.toString(anInput)))
                .header("Authorization", "Bearer " + aBearer)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();

        try {
            HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException ex) {
            log.error("Não foi possível realizar a requisição {}", ex.getMessage());
            throw DealsignException.generate("Não foi possível realizar a requisição.");
        }
    }
}
