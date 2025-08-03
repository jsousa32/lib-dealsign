package io.github.jsousa32.libdealsign.usecases.templates;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.templates.models.create.TemplateCreateRequest;
import io.github.jsousa32.libdealsign.usecases.templates.models.create.TemplateCreateResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultTemplateCreateUseCase extends UseCase<TemplateCreateResponse, TemplateCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultTemplateCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/template");
    }

    public static DefaultTemplateCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultTemplateCreateUseCase(aBearer, anUrl);
    }

    @Override
    public TemplateCreateResponse execute(final TemplateCreateRequest anInput) {
        return RequestUtils.post(this.bearer, this.url, anInput, TemplateCreateResponse.class)
        .orElseThrow(() -> DealsignException.generate("Não foi possível criar um template"));
    }
}
