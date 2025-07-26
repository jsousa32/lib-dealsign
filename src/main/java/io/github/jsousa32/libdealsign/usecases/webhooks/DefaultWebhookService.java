package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateRequest;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateResponse;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.list.WebhookListResponse;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.update.WebhookUpdateRequest;

import java.util.List;
import java.util.Objects;

final class DefaultWebhookService implements WebhookService {

    private final String bearer;

    private final String url;

    private DefaultWebhookService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultWebhookService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultWebhookService(aBearer, anUrl);
    }

    @Override
    public WebhookCreateResponse create(final WebhookCreateRequest anInput) {
        return DefaultWebhookCreateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public List<WebhookListResponse> list() {
        return DefaultWebhookListUseCase.generate(this.bearer, this.url).execute();
    }

    @Override
    public void update(final WebhookUpdateRequest anInput) {
        DefaultWebhookUpdateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void delete(String anId) {
        DefaultWebhookDeleteUseCase.generate(this.bearer, this.url).execute(anId);
    }
}
