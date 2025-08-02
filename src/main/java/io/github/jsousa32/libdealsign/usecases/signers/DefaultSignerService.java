package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateResponse;

import java.util.Objects;

final class DefaultSignerService implements SignerService {

    private final String bearer;

    private final String url;

    private DefaultSignerService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultSignerService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignerService(aBearer, anUrl);
    }

    @Override
    public SignerCreateResponse create(final SignerCreateRequest anInput) {
        return DefaultSignerCreateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public SignerUpdateResponse update(SignerUpdateRequest anInput) {
        return DefaultSignerUpdateUseCase.generate(this.bearer, this.url).execute(anInput);
    }
}
