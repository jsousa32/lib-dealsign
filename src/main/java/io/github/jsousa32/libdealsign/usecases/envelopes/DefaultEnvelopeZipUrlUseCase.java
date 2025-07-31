package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountResponse;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.zip_url.EnvelopeZipUrlResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultEnvelopeZipUrlUseCase extends UseCase<EnvelopeZipUrlResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeZipUrlUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes/zip/url?uuid=");
    }

    public static DefaultEnvelopeZipUrlUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultEnvelopeZipUrlUseCase(aBearer, anUrl);
    }

    @Override
    public EnvelopeZipUrlResponse execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        return Optional.of(rest.exchange(url, HttpMethod.POST, httpEntity, EnvelopeZipUrlResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível buscar a url do envelope."));
    }
}
