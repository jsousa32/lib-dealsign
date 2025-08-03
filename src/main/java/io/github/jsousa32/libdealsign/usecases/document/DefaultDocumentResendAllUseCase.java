package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.document.models.resend_all.DocumentResendAllRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
import org.springframework.http.HttpMethod;

final class DefaultDocumentResendAllUseCase extends UnitUseCase<DocumentResendAllRequest> {

    private final String bearer;

    private final String url;

    private DefaultDocumentResendAllUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/document/resend-all");
    }

    public static DefaultDocumentResendAllUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDocumentResendAllUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final DocumentResendAllRequest anInput) {
        final var rest = RequestUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        rest.exchange(url, HttpMethod.POST, httpEntity, Void.class);
    }
}
