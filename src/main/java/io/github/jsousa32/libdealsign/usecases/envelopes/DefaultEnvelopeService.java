package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.usecases.envelopes.models.bind_envelope.EnvelopeBindRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountResponse;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.retrive.EnvelopeRetriveResponse;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.send_envelope.EnvelopeSendRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.zip_url.EnvelopeZipUrlResponse;

import java.util.Objects;

final class DefaultEnvelopeService implements EnvelopeService {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultEnvelopeService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultEnvelopeService(aBearer, anUrl);
    }

    @Override
    public EnvelopeMountResponse create(final EnvelopeMountRequest anInput) {
        return DefaultEnvelopeMountUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public EnvelopeRetriveResponse retrive(final String anId) {
        return DefaultEnvelopeRetriveUseCase.generate(this.bearer, this.url).execute(anId);
    }

    @Override
    public void delete(final String anId) {
        DefaultEnvelopeDeleteUseCase.generate(this.bearer, this.url).execute(anId);
    }

    @Override
    public void sendEnvelope(final EnvelopeSendRequest anInput) {
        DefaultEnvelopeSendUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public EnvelopeZipUrlResponse url(final String anId) {
        return DefaultEnvelopeZipUrlUseCase.generate(this.bearer, this.url).execute(anId);
    }

    @Override
    public void bindEnvelope(final EnvelopeBindRequest anInput) {
        DefaultEnvelopeBindUseCase.generate(this.bearer, this.url).execute(anInput);
    }
}
