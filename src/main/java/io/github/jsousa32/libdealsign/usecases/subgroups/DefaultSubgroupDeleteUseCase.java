package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

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
        RequestUtils.delete(this.bearer, this.url);
    }
}
