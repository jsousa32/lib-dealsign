package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_multiple_documents.GroupLinkMultipleDocumentsRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultGroupLinkMultipleDocumentsUseCase extends UnitUseCase<GroupLinkMultipleDocumentsRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupLinkMultipleDocumentsUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups/link-multiple-documents-by-envelope/");
    }

    public static DefaultGroupLinkMultipleDocumentsUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultGroupLinkMultipleDocumentsUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final GroupLinkMultipleDocumentsRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anInput.getEnvelopeUuid());

        final var httpEntity = HeadersUtils.generate(bearer);

        rest.exchange(url, HttpMethod.POST, httpEntity, Void.class);
    }
}
