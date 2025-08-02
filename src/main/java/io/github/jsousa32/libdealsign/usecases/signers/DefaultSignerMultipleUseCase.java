package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.multiple.SignerMultipleRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

final class DefaultSignerMultipleUseCase extends UseCase<Set<SignerResponse>, SignerMultipleRequest> {

    private final String bearer;

    private final String url;

    private DefaultSignerMultipleUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signers/multiple");
    }

    public static DefaultSignerMultipleUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignerMultipleUseCase(aBearer, anUrl);
    }

    @Override
    public Set<SignerResponse> execute(final SignerMultipleRequest anInput) {
        final var body = new HashSet<>(anInput.getMultiples());

        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, body);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Set<SignerResponse>>() {
                }))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar os multiplos signatários."));
    }
}
