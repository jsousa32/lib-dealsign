package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.retrive_url.DocumentRetriveUrlResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultDocumentRetriveUrlUseCase extends UseCase<DocumentRetriveUrlResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultDocumentRetriveUrlUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document/url/");
    }

    public static DefaultDocumentRetriveUrlUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultDocumentRetriveUrlUseCase(aBearer, anUrl);
    }

    @Override
    public DocumentRetriveUrlResponse execute(final String anId) {
        return RequestUtils.post(this.bearer, url, null, DocumentRetriveUrlResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível buscar documento"));
    }
}
