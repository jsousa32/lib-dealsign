package io.github.jsousa32.libdealsign.usecases.document;

import java.util.Objects;

final class DefaultDocumentService implements DocumentService {

    private final String bearer;

    private final String url;

    private DefaultDocumentService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultDocumentService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDocumentService(aBearer, anUrl);
    }
}
