package io.github.jsousa32.libdealsign.core;

import io.github.jsousa32.libdealsign.usecases.document.DocumentService;
import io.github.jsousa32.libdealsign.usecases.envelopes.EnvelopeService;
import io.github.jsousa32.libdealsign.usecases.groups.GroupService;
import io.github.jsousa32.libdealsign.usecases.signatures.SignatureService;
import io.github.jsousa32.libdealsign.usecases.subgroups.SubgroupService;
import io.github.jsousa32.libdealsign.usecases.webhooks.WebhookService;

final class DefaultDealsignService implements DealsignService {

    private final String bearer;

    private final String url;

    private DefaultDealsignService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = aBearer;
        this.url = anUrl;
    }

    public static DefaultDealsignService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDealsignService(aBearer, anUrl);
    }

    @Override
    public WebhookService webhooks() {
        return WebhookService.builder(this.bearer, this.url);
    }

    @Override
    public SubgroupService subgroups() {
        return SubgroupService.builder(this.bearer, this.url);
    }

    @Override
    public GroupService groups() {
        return GroupService.builder(this.bearer, this.url);
    }

    @Override
    public SignatureService signatures() {
        return SignatureService.builder(this.bearer, this.url);
    }

    @Override
    public EnvelopeService envelopes() {
        return EnvelopeService.builder(this.bearer, this.url);
    }

    @Override
    public DocumentService documents() {
        return DocumentService.builder(this.bearer, this.url);
    }
}
