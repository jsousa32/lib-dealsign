package io.github.jsousa32.libdealsign.usecases.signatures;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signatures.models.retrive.SignatureRetriveResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultSginatureRetriveUseCase extends UseCase<SignatureRetriveResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultSginatureRetriveUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signatures?signatureUuid=");
    }

    public static DefaultSginatureRetriveUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSginatureRetriveUseCase(aBearer, anUrl);
    }

    @Override
    public SignatureRetriveResponse execute(final String anId) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anId);

        final var httpEntity = HeadersUtils.generate(bearer);

        return Optional.of(rest.exchange(url, HttpMethod.GET, httpEntity, SignatureRetriveResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível buscar a assinatura."));
    }
}
