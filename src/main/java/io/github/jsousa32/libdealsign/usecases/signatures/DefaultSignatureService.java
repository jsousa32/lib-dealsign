package io.github.jsousa32.libdealsign.usecases.signatures;

import java.util.Objects;

final class DefaultSignatureService implements SignatureService {

    private final String bearer;

    private final String url;

    private DefaultSignatureService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultSignatureService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignatureService(aBearer, anUrl);
    }
}
