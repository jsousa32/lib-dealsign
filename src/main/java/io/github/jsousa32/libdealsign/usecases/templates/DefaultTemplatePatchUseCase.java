package io.github.jsousa32.libdealsign.usecases.templates;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchRequest;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
import org.springframework.http.HttpMethod;

final class DefaultTemplatePatchUseCase extends UseCase<TemplatePatchResponse, TemplatePatchRequest> {

    private final String bearer;

    private final String url;

    private DefaultTemplatePatchUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/template/patch");
    }

    public static DefaultTemplatePatchUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultTemplatePatchUseCase(aBearer, anUrl);
    }

    @Override
    public TemplatePatchResponse execute(final TemplatePatchRequest anInput) {
        return RequestUtils.post(this.bearer, this.url, anInput, TemplatePatchResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível realizar o patch do template."));
    }
}
