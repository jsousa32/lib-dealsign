package io.github.jsousa32.libdealsign.usecases.webhooks.models.create;

import io.github.jsousa32.libdealsign.usecases.webhooks.common.Events;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;
import io.github.jsousa32.libdealsign.utils.ValidatorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class WebhookCreateRequest {

    private final String url;

    private final Set<Events> events;

    private WebhookCreateRequest(
            final String anUrl,
            final Set<Events> anEvents
    ) {
        this.url = anUrl;
        this.events = anEvents;
        this.validate();
    }

    public static WebhookCreateRequest generate(
            final String anUrl,
            final Set<Events> anEvents
    ) {
        return new WebhookCreateRequest(anUrl, anEvents);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (!ValidatorUtils.isValidUrl(getUrl())) {
            errors.add("url");
        }

        if (getEvents().isEmpty()) {
            errors.add("events");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getUrl() {
        return url;
    }

    public Set<Events> getEvents() {
        return Optional.ofNullable(this.events).orElse(new HashSet<>());
    }
}
