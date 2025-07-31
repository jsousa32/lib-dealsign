package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountResponse;

public interface EnvelopeService {

    static EnvelopeService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultEnvelopeService.generate(aBearer, anUrl);
    }

    EnvelopeMountResponse create(final EnvelopeMountRequest anInput);
}
