package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.retrive.DocumentRetriveResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultDocumentRetriveUseCase extends UseCase<DocumentRetriveResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultDocumentRetriveUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document/");
    }

    public static DefaultDocumentRetriveUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultDocumentRetriveUseCase(aBearer, anUrl);
    }

    @Override
    public DocumentRetriveResponse execute(final String anId) {
        return RequestUtils.post(this.bearer, url, null, DocumentRetriveResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível buscar documento"));
    }
}
