package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.resend.DocumentResendRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultDocumentResendUseCase extends UnitUseCase<DocumentResendRequest> {

    private final String bearer;

    private final String url;

    private DefaultDocumentResendUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document/resend");
    }

    public static DefaultDocumentResendUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDocumentResendUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final DocumentResendRequest anInput) {
        RequestUtils.post(this.bearer, this.url, anInput);
    }
}
