package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.create.DocumentCreateRequest;
import io.github.jsousa32.libdealsign.usecases.document.models.create.DocumentCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultDocumentCreateUseCase extends UseCase<DocumentCreateResponse, DocumentCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultDocumentCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document");
    }

    public static DefaultDocumentCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDocumentCreateUseCase(aBearer, anUrl);
    }

    @Override
    public DocumentCreateResponse execute(final DocumentCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generateForm(bearer, anInput.getForm());

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, DocumentCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível criar o documento."));
    }
}
