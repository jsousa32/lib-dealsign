package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultSignerUpdateUseCase extends UseCase<SignerUpdateResponse, SignerUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSignerUpdateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signers");
    }

    public static DefaultSignerUpdateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignerUpdateUseCase(aBearer, anUrl);
    }

    @Override
    public SignerUpdateResponse execute(final SignerUpdateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, SignerUpdateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível atualizar o signatário."));
    }
}
