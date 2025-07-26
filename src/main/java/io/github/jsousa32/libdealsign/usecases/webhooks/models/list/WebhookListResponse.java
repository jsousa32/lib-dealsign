package io.github.jsousa32.libdealsign.usecases.webhooks.models.list;

import io.github.jsousa32.libdealsign.usecases.webhooks.common.Events;

import java.util.Set;

public class WebhookListResponse {

    private String uuid;

    private String companyUuid;

    private String url;

    private Set<Events> events;

    public WebhookListResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public String getUrl() {
        return url;
    }

    public Set<Events> getEvents() {
        return events;
    }
}
