package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.multiple.SignerMultipleRequest;

import io.github.jsousa32.libdealsign.utils.RequestUtils;

import java.util.Set;

final class DefaultSignerMultipleUseCase extends UseCase<Set<SignerResponse>, SignerMultipleRequest> {

    private final String bearer;

    private final String url;

    private DefaultSignerMultipleUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signers/multiple");
    }

    public static DefaultSignerMultipleUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultSignerMultipleUseCase(aBearer, anUrl);
    }

    @Override
    public Set<SignerResponse> execute(final SignerMultipleRequest anInput) {
        return RequestUtils.post(this.bearer, url, anInput, SignerResponse.class)
         .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar múltiplos signatários."));
    }
}
