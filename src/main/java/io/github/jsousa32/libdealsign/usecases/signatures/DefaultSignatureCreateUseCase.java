package io.github.jsousa32.libdealsign.usecases.signatures;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signatures.models.create.SignatureCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signatures.models.create.SignatureCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultSignatureCreateUseCase extends UseCase<SignatureCreateResponse, SignatureCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSignatureCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signatures");
    }

    public static DefaultSignatureCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignatureCreateUseCase(aBearer, anUrl);
    }

    @Override
    public SignatureCreateResponse execute(final SignatureCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, SignatureCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível criar a assinatura."));
    }
}
