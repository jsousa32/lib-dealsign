package io.github.jsousa32.libdealsign.usecases.webhooks.models.update;

import io.github.jsousa32.libdealsign.usecases.common_enums.Events;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;
import io.github.jsousa32.libdealsign.utils.validators.UrlValidator;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class WebhookUpdateRequest {

    private final String webhookUuid;

    private final String url;

    private final Set<Events> events;

    private WebhookUpdateRequest(
            final String aWebhookUuid,
            final String anUrl,
            final Set<Events> anEvents
    ) {
        this.webhookUuid = aWebhookUuid;
        this.url = anUrl;
        this.events = anEvents;
        this.validate();
    }

    public static WebhookUpdateRequest generate(
            final String aWebhookUuid,
            final String anUrl,
            final Set<Events> anEvents
    ) {
        return new WebhookUpdateRequest(aWebhookUuid, anUrl, anEvents);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getWebhookUuid() == null || getWebhookUuid().isEmpty()) {
            errors.add("uuid");
        }

        if (!UrlValidator.isValid(getUrl())) {
            errors.add("url");
        }

        if (getEvents().isEmpty()) {
            errors.add("events");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getWebhookUuid() {
        return webhookUuid;
    }

    public String getUrl() {
        return url;
    }

    public Set<Events> getEvents() {
        return Optional.ofNullable(this.events).orElse(new HashSet<>());
    }
}
