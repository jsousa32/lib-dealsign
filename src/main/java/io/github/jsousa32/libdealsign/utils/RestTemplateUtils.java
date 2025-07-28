package io.github.jsousa32.libdealsign.utils;


import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public final class RestTemplateUtils {

    private RestTemplateUtils() {
    }

    public static RestTemplate getInstance() {
        return new RestTemplate(new HttpRequest());
    }

    private static class HttpRequest implements ClientHttpRequestFactory {

        private final ClientHttpRequestFactory delegate;

        public HttpRequest() {
            this.delegate = resolveFactory();
        }

        private ClientHttpRequestFactory resolveFactory() {
            if (isPresent("org.apache.hc.client5.http.impl.classic.HttpClients") &&
                isPresent("org.springframework.http.client.HttpComponents5ClientHttpRequestFactory")) {
                try {
                    final var httpClients = Class.forName("org.apache.hc.client5.http.impl.classic.HttpClients");
                    final var httpClient5 = httpClients.getMethod("createDefault").invoke(null);
                    final var factory5 = Class.forName("org.springframework.http.client.HttpComponents5ClientHttpRequestFactory");
                    final var ctor = factory5.getConstructor(Class.forName("org.apache.hc.client5.http.impl.classic.CloseableHttpClient"));
                    return (ClientHttpRequestFactory) ctor.newInstance(httpClient5);
                } catch (Exception ex) {
                }
            }
            org.apache.http.impl.client.CloseableHttpClient httpClient4 =
                    org.apache.http.impl.client.HttpClients.createDefault();
            return new HttpComponentsClientHttpRequestFactory(httpClient4);
        }

        private boolean isPresent(final String aClass) {
            try {
                Class.forName(aClass);
                return true;
            } catch (ClassNotFoundException ex) {
                return false;
            }
        }

        @Override
        public ClientHttpRequest createRequest(
                final URI anUri,
                final HttpMethod method
        ) throws IOException {
            return delegate.createRequest(anUri, method);
        }
    }
}
