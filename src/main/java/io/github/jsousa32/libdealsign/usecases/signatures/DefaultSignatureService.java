package io.github.jsousa32.libdealsign.usecases.signatures;

import io.github.jsousa32.libdealsign.usecases.signatures.models.create.SignatureCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signatures.models.create.SignatureCreateResponse;
import io.github.jsousa32.libdealsign.usecases.signatures.models.retrive.SignatureRetriveResponse;

import java.util.Objects;

final class DefaultSignatureService implements SignatureService {

    private final String bearer;

    private final String url;

    private DefaultSignatureService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultSignatureService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSignatureService(aBearer, anUrl);
    }

    @Override
    public SignatureCreateResponse create(final SignatureCreateRequest anInput) {
        return DefaultSignatureCreateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public SignatureRetriveResponse retrive(String anId) {
        return DefaultSginatureRetriveUseCase.generate(this.bearer, this.url).execute(anId);
    }

    @Override
    public void delete(final String anId) {
        DefaultSginatureDeleteUseCase.generate(this.bearer, this.url).execute(anId);
    }
}
