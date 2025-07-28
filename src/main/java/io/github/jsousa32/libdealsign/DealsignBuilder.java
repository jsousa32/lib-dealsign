package io.github.jsousa32.libdealsign;

import io.github.jsousa32.libdealsign.core.DealsignService;
import io.github.jsousa32.libdealsign.domain.Authentication;
import io.github.jsousa32.libdealsign.domain.AuthenticationRepository;
import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import io.github.jsousa32.libdealsign.utils.validators.EmailValidator;
import io.github.jsousa32.libdealsign.utils.validators.UrlValidator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class DealsignBuilder {

    private final String bearer;

    private final String url;

    private final String email;

    private final String uuid;

    private DealsignBuilder(
            final String anUrl,
            final String anEmail,
            final String anUuid
    ) {
        this.url = anUrl.endsWith("/") ? anUrl.substring(0, anUrl.length() - 1) : anUrl;
        this.email = anEmail;
        this.uuid = anUuid;
        this.validate();
        this.bearer = getBearerToken();
    }

    public static synchronized DealsignService getInstance(
            final String anUrl,
            final String anEmail,
            final String anUuid
    ) {
        final var builder = new DealsignBuilder(anUrl, anEmail, anUuid);
        return DealsignService.builder(builder.bearer, builder.url);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (!UrlValidator.isValid(this.url)) {
            errors.add("url");
        }

        if (!EmailValidator.isValid(this.email)) {
            errors.add("email");
        }

        if (this.uuid == null || this.uuid.isEmpty()) {
            errors.add("uuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    private String getBearerToken() {
        final var registerIntoDB = AuthenticationRepository.findById(this.uuid);

        if (registerIntoDB.isPresent()) {

            final var expiresAt = registerIntoDB.get().getExpiresAt();

            if (expiresAt.isAfter(LocalDateTime.now())) {
                return registerIntoDB.get().getBearer();
            }
        }

        return generateBearerTokenFromDealsign();
    }

    private String generateBearerTokenFromDealsign() {
        final var rest = RestTemplateUtils.getInstance();

        final var dealsignBody = DealsignAuthRequest.generate(this.email, this.uuid);
        final var finalUrl = this.url.concat("/tokens");

        final var response = Optional.ofNullable(rest.postForEntity(finalUrl, dealsignBody, DealsignAuthResponse.class).getBody())
                .orElseThrow(() -> DealsignException.generate("Não foi possível se autenticar ao Dealsign."));

        final var authentication = Authentication.generate(this.uuid, response.getBearer());

        try {
            return AuthenticationRepository.save(authentication).getBearer();
        } catch (Exception ex) {
            return response.getBearer();
        }
    }


    private static class DealsignAuthRequest {
        private final String email;

        private final String uuid;

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
