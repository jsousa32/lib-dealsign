package io.github.jsousa32.libdealsign.utils;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public final class RestTemplateUtils {

    private RestTemplateUtils() {
    }

    public static RestTemplate getInstance() {
        final var rest = new RestTemplate();
        enable(rest);
        return rest;
    }

    private static void enable(final RestTemplate rest) {
        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new PatchMethod());
        rest.setInterceptors(interceptors);
    }

    private static class PatchMethod implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(
                final HttpRequest aRequest,
                final byte[] aBody,
                final ClientHttpRequestExecution anExecution
        ) throws IOException {

            if (HttpMethod.PATCH.equals(aRequest.getMethod())) {
                final var headers = generateHeaders(aRequest);

                final var req = new HttpRequestWrapper(aRequest) {
                    @Override
                    public HttpMethod getMethod() {
                        return HttpMethod.POST;
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return headers;
                    }
                };

                return anExecution.execute(req, aBody);
            }

            return anExecution.execute(aRequest, aBody);
        }

        private static HttpHeaders generateHeaders(final HttpRequest aRequest) {
            final var headers = new HttpHeaders();
            headers.putAll(aRequest.getHeaders());
            headers.add("X-HTTP-Method-Override", "PATCH");
            return headers;
        }
    }

    private static class HttpRequestWrapper implements HttpRequest {
        private final HttpRequest request;

        public HttpRequestWrapper(final HttpRequest aRequest) {
            this.request = aRequest;
        }

        @Override
        public HttpMethod getMethod() {
            return request.getMethod();
        }

        @Override
        public String getMethodValue() {
            return request.getMethodValue();
        }

        @Override
        public URI getURI() {
            return request.getURI();
        }

        @Override
        public HttpHeaders getHeaders() {
            return request.getHeaders();
        }
    }
}
