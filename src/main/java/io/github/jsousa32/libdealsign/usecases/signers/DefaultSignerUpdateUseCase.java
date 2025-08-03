package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultSignerUpdateUseCase extends UseCase<SignerResponse, SignerUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSignerUpdateUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signers");
    }

    public static DefaultSignerUpdateUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultSignerUpdateUseCase(aBearer, anUrl);
    }

    @Override
    public SignerResponse execute(final SignerUpdateRequest anInput) {
        return RequestUtils.patch(this.bearer, this.url, anInput, SignerResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível atualizar o signatário"));
    }
}
