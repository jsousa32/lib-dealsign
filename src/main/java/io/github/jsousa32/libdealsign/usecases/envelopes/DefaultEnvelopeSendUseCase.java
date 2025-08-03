package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.send_envelope.EnvelopeSendRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultEnvelopeSendUseCase extends UnitUseCase<EnvelopeSendRequest> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeSendUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes/send-envelope");
    }

    public static DefaultEnvelopeSendUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultEnvelopeSendUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final EnvelopeSendRequest anInput) {
        RequestUtils.post(this.bearer, this.url, anInput);
    }
}
