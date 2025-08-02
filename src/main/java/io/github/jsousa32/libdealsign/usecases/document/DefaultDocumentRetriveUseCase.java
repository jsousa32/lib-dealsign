package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.retrive.DocumentRetriveResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class DefaultDocumentRetriveUseCase extends UseCase<DocumentRetriveResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultDocumentRetriveUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document/");
    }

    public static DefaultDocumentRetriveUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDocumentRetriveUseCase(aBearer, anUrl);
    }

    @Override
    public DocumentRetriveResponse execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        return Optional.of(rest.exchange(url, HttpMethod.GET, httpEntity, DocumentRetriveResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível buscar os detalhes do documento."));
    }
}
