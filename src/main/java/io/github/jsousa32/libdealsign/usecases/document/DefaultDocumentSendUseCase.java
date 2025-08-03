package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.send.DocumentSendRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultDocumentSendUseCase extends UnitUseCase<DocumentSendRequest> {

    private final String bearer;

    private final String url;

    private DefaultDocumentSendUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document/send");
    }

    public static DefaultDocumentSendUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultDocumentSendUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final DocumentSendRequest anInput) {
        RequestUtils.post(this.bearer, this.url, anInput);
    }
}
