package io.github.jsousa32.libdealsign.core;

import io.github.jsousa32.libdealsign.usecases.groups.GroupService;
import io.github.jsousa32.libdealsign.usecases.signatures.SignatureService;
import io.github.jsousa32.libdealsign.usecases.subgroups.SubgroupService;
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
}
