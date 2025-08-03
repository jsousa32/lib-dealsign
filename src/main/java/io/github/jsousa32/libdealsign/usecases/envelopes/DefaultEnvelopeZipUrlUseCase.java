package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.zip_url.EnvelopeZipUrlResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultEnvelopeZipUrlUseCase extends UseCase<EnvelopeZipUrlResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeZipUrlUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes/zip/url?uuid=");
    }

    public static DefaultEnvelopeZipUrlUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultEnvelopeZipUrlUseCase(aBearer, anUrl);
    }

    @Override
    public EnvelopeZipUrlResponse execute(final String anId) {
        final var url = this.url.concat(anId);

        return RequestUtils.get(this.bearer, url, null, EnvelopeZipUrlResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível realizar o patch do template."));

    }
}
