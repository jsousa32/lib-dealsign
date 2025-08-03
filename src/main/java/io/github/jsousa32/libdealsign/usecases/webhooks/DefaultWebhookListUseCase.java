package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.NullaryUseCase;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.list.WebhookListResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
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
        final var response = new ParameterizedTypeReference<List<WebhookListResponse>>() {};

        return RequestUtils.get(this.bearer, this.url, response)
                .orElseThrow(() -> DealsignException.generate("Não foi possível listar os webhooks."));
    }
}
