package io.github.jsousa32.libdealsign.usecases.signatures;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
import org.springframework.http.HttpMethod;

final class DefaultSginatureDeleteUseCase extends UnitUseCase<String> {

    private final String bearer;

    private final String url;

    private DefaultSginatureDeleteUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signatures?signatureUuid=");
    }

    public static DefaultSginatureDeleteUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSginatureDeleteUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final String anId) {
        final var rest = RequestUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        rest.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
    }
}
