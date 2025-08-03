package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
final class DefaultSignerCreateUseCase extends UseCase<SignerResponse, SignerCreateRequest> {

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
    public SignerResponse execute(final SignerCreateRequest anInput) {
        return RequestUtils.post(this.bearer, this.url, anInput, SignerResponse.class)
            .orElseThrow(() -> DealsignException.generate("Não foi possível criar um signatário"));
    }
}
