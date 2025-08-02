package io.github.jsousa32.libdealsign.usecases.templates;

import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchRequest;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchResponse;

import java.util.Objects;

final class DefaultTemplateService implements TemplateService {

    private final String bearer;

    private final String url;

    private DefaultTemplateService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultTemplateService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultTemplateService(aBearer, anUrl);
    }

    @Override
    public TemplatePatchResponse patch(final TemplatePatchRequest anInput) {
        return DefaultTemplatePatchUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void delete(final String anId) {
        DefaultTemplateDeleteUseCase.generate(this.bearer, this.url).execute(anId);
    }
}
