package io.github.jsousa32.libdealsign.utils;

import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public final class RestTemplateUtils {

    private RestTemplateUtils() {
    }

    public static RestTemplate getInstance() {
        final var http = HttpClients.createDefault();
        final var factory = new HttpComponentsClientHttpRequestFactory(http);
        return new RestTemplate(factory);
    }
}
