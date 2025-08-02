package io.github.jsousa32.libdealsign.usecases.profiles;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultProfileDeleteUseCase extends UnitUseCase<String> {

    private final String bearer;

    private final String url;

    private DefaultProfileDeleteUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/profiles/");
    }

    public static DefaultProfileDeleteUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultProfileDeleteUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        rest.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
    }
}
