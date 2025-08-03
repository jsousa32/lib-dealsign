package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultSignerRetriveUseCase extends UseCase<SignerResponse, String> {

    private final String bearer;

    private final String url;

    private DefaultSignerRetriveUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/signers/");
    }

    public static DefaultSignerRetriveUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultSignerRetriveUseCase(aBearer, anUrl);
    }

    @Override
    public SignerResponse execute(final String anId) {
        final var url = this.url.concat(anId);

        return RequestUtils.get(this.bearer, url, anId, SignerResponse.class)
        .orElseThrow(() -> DealsignException.generate("Não foi possível buscar o signatário"));
    }
}
