package io.github.jsousa32.libdealsign.usecases.templates;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchRequest;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(url, HttpMethod.POST, httpEntity, TemplatePatchResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível realizar o patch do template."));
    }
}
