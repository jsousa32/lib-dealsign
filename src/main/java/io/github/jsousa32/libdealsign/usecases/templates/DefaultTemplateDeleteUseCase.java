package io.github.jsousa32.libdealsign.usecases.templates;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultTemplateDeleteUseCase extends UnitUseCase<String> {

    private final String bearer;

    private final String url;

    private DefaultTemplateDeleteUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/template?templateUuid=");
    }

    public static DefaultTemplateDeleteUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultTemplateDeleteUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final String anId) {
        RequestUtils.delete(this.bearer, url);
    }
}
