package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.retrive.EnvelopeRetriveResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultEnvelopeRetriveUseCase extends UseCase<EnvelopeRetriveResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeRetriveUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes?uuid=");
    }

    public static DefaultEnvelopeRetriveUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultEnvelopeRetriveUseCase(aBearer, anUrl);
    }

    @Override
    public EnvelopeRetriveResponse execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        return Optional.of(rest.exchange(url, HttpMethod.GET, httpEntity, EnvelopeRetriveResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível buscar o envelope."));
    }
}
