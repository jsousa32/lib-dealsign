package io.github.jsousa32.libdealsign.core;

import io.github.jsousa32.libdealsign.usecases.document.DocumentService;
import io.github.jsousa32.libdealsign.usecases.envelopes.EnvelopeService;
import io.github.jsousa32.libdealsign.usecases.groups.GroupService;
import io.github.jsousa32.libdealsign.usecases.profiles.ProfileService;
import io.github.jsousa32.libdealsign.usecases.signatures.SignatureService;
import io.github.jsousa32.libdealsign.usecases.subgroups.SubgroupService;
import io.github.jsousa32.libdealsign.usecases.templates.TemplateService;
import io.github.jsousa32.libdealsign.usecases.webhooks.WebhookService;

public interface DealsignService {

    static DealsignService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultDealsignService.generate(aBearer, anUrl);
    }

    WebhookService webhooks();

    SubgroupService subgroups();

    GroupService groups();

    SignatureService signatures();

    EnvelopeService envelopes();

    DocumentService documents();

    TemplateService templates();

    ProfileService profiles();
}
