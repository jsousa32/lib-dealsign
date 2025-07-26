package io.github.jsousa32.libdealsign;

import io.github.jsousa32.libdealsign.core.DealsignService;
import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class DealsignBuilder {

    private static DealsignBuilder instance;

    private final String bearer;

    private final LocalDateTime expriesAt;

    private final String url;

    private final String email;

    private final String uuid;

    private DealsignBuilder(
            final String anUrl,
            final String anEmail,
            final String anUuid
    ) {
        this.url = anUrl;
        this.email = anEmail;
        this.uuid = anUuid;
        this.validate();
        this.expriesAt = LocalDateTime.now().plusMinutes(55);
        this.bearer = getBearerTokenFromDealsign();
    }

    public static synchronized DealsignBuilder getInstance(
            final String anUrl,
            final String anEmail,
            final String anUuid
    ) {
        if (instance == null) {
            return instance = new DealsignBuilder(anUrl, anEmail, anUuid);
        }

        if (LocalDateTime.now().isAfter(instance.expriesAt) && instance.uuid.equals(anUuid)) {
            return instance = new DealsignBuilder(anUrl, anEmail, anUuid);
        }

        if (LocalDateTime.now().isAfter(instance.expriesAt)) {
            return instance = new DealsignBuilder(anUrl, anEmail, anUuid);
        }

        return instance;
    }

    public DealsignService build() {
        return DealsignService.builder(this.bearer, this.url);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (this.url == null || this.url.isEmpty()) {
            errors.add("url");
        }

        if (this.email == null || this.email.isEmpty()) {
            errors.add("email");
        }

        if (this.uuid == null || this.uuid.isEmpty()) {
            errors.add("uuid");
        }

        if (!errors.isEmpty()) {
            final var errorsInString = String.join(", ", errors);
            throw DealsignException.generate("Os atributos ".concat(errorsInString).concat(" são obrigatórios."));
        }
    }

    private String getBearerTokenFromDealsign() {
        final var rest = new RestTemplate();

        final var dealsignBody = DealsignAuthRequest.generate(this.email, this.uuid);
        final var finalUrl = this.url.endsWith("/") ? this.url.concat("tokens") : this.url.concat("/tokens");

        return Optional.ofNullable(rest.postForEntity(finalUrl, dealsignBody, DealsignAuthResponse.class).getBody())
                .map(DealsignAuthResponse::getBearer)
                .orElseThrow(() -> new RuntimeException("Cannot request to Dealsign"));
    }


    private static class DealsignAuthRequest {
        private String email;

        private String uuid;

        public DealsignAuthRequest() {
        }

        private DealsignAuthRequest(
                final String anEmail,
                final String anUuid
        ) {
            this.email = anEmail;
            this.uuid = anUuid;
        }

        public static DealsignAuthRequest generate(
                final String anEmail,
                final String anUuid
        ) {
            return new DealsignAuthRequest(anEmail, anUuid);
        }

        public String getEmail() {
            return email;
        }

        public String getUuid() {
            return uuid;
        }
    }

    private static class DealsignAuthResponse {
        private String bearer;

        public DealsignAuthResponse() {
        }

        public String getBearer() {
            return bearer;
        }
    }
}
