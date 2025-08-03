package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.bind_envelope.EnvelopeBindRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultEnvelopeBindUseCase extends UnitUseCase<EnvelopeBindRequest> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeBindUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes/bind-envelope");
    }

    public static DefaultEnvelopeBindUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultEnvelopeBindUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final EnvelopeBindRequest anInput) {
        RequestUtils.post(this.bearer, this.url, anInput);
    }
}
