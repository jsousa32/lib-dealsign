package io.github.jsousa32.libdealsign.core;

import io.github.jsousa32.libdealsign.usecases.webhooks.WebhookService;

public interface DealsignService {

    static DealsignService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultDealsignService.generate(aBearer, anUrl);
    }

    WebhookService webhooks();
}
