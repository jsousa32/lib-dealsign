package io.github.jsousa32.libdealsign.usecases.webhooks;

import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateRequest;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.create.WebhookCreateResponse;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.list.WebhookListResponse;
import io.github.jsousa32.libdealsign.usecases.webhooks.models.update.WebhookUpdateRequest;

import java.util.List;

public interface WebhookService {

    static WebhookService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultWebhookService.generate(aBearer, anUrl);
    }

    WebhookCreateResponse create(final WebhookCreateRequest anInput);

    List<WebhookListResponse> list();

    void update(final WebhookUpdateRequest anInput);

    void delete(final String anId);
}
