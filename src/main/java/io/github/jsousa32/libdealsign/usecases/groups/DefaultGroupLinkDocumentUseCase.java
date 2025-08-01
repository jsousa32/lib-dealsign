package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_document.GroupLinkDocumentRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultGroupLinkDocumentUseCase extends UnitUseCase<GroupLinkDocumentRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupLinkDocumentUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups/link-document");
    }

    public static DefaultGroupLinkDocumentUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultGroupLinkDocumentUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final GroupLinkDocumentRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        rest.exchange(this.url, HttpMethod.POST, httpEntity, Void.class);
    }
}
