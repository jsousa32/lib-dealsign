package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.NullaryUseCase;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.list.WebhookListResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

final class DefaultWebhookListUseCase extends NullaryUseCase<List<WebhookListResponse>> {

    private final String bearer;

    private final String url;

    private DefaultWebhookListUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/webhooks");
    }

    public static DefaultWebhookListUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultWebhookListUseCase(aBearer, anUrl);
    }

    @Override
    public List<WebhookListResponse> execute() {
        final var rest = RestTemplateUtils.getInstance();

        final var response = new ParameterizedTypeReference<List<WebhookListResponse>>() {
        };

        final var httpEntity = HeadersUtils.generate(bearer);

        return Optional.of(rest.exchange(this.url, HttpMethod.GET, httpEntity, response))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível listar os webhooks."));
    }
}
