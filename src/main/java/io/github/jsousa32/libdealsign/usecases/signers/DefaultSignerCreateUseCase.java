package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultSignerCreateUseCase extends UseCase<SignerCreateResponse, SignerCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSignerCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signers");
    }

    public static DefaultSignerCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignerCreateUseCase(aBearer, anUrl);
    }

    @Override
    public SignerCreateResponse execute(final SignerCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, SignerCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o signatário."));
    }
}
