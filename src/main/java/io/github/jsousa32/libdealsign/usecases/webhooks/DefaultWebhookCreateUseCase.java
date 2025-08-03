package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateRequest;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

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
        return RequestUtils.post(this.bearer, this.url, anInput, WebhookCreateResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o webhook."));
    }
}
