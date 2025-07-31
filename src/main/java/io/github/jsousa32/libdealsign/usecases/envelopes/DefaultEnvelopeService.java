package io.github.jsousa32.libdealsign.usecases.envelopes;

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
}
