package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.update.WebhookUpdateRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultWebhookUpdateUseCase extends UnitUseCase<WebhookUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultWebhookUpdateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/webhooks");
    }

    public static DefaultWebhookUpdateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultWebhookUpdateUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final WebhookUpdateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        rest.exchange(this.url, HttpMethod.PATCH, httpEntity, Void.class);
    }
}
