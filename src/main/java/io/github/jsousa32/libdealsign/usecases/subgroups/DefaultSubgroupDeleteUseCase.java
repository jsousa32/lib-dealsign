package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultSubgroupDeleteUseCase extends UnitUseCase<String> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupDeleteUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups/");
    }

    public static DefaultSubgroupDeleteUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupDeleteUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        rest.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
    }
}
