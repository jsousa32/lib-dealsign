package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateRequest;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultWebhookCreateUseCase extends UseCase<WebhookCreateResponse, WebhookCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultWebhookCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/webhooks");
    }

    public static DefaultWebhookCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultWebhookCreateUseCase(aBearer, anUrl);
    }

    @Override
    public WebhookCreateResponse execute(final WebhookCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, WebhookCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o webhook."));
    }
}
