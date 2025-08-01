package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultWebhookDeleteUseCase extends UnitUseCase<String> {

    private final String bearer;

    private final String url;

    private DefaultWebhookDeleteUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/webhooks?webhookUuid=");
    }

    public static DefaultWebhookDeleteUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultWebhookDeleteUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        rest.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
    }
}
