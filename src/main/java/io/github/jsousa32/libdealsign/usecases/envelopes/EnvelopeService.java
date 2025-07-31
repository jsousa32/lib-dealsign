package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountResponse;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.retrive.EnvelopeRetriveResponse;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.send_envelope.EnvelopeSendRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.zip_url.EnvelopeZipUrlResponse;

public interface EnvelopeService {

    static EnvelopeService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultEnvelopeService.generate(aBearer, anUrl);
    }

    EnvelopeMountResponse create(final EnvelopeMountRequest anInput);

    EnvelopeRetriveResponse retrive(final String anId);

    void delete(final String anId);

    void sendEnvelope(final EnvelopeSendRequest anInput);

    EnvelopeZipUrlResponse url(final String anId);
}
