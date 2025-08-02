package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateResponse;

public interface SignerService {

    static SignerService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSignerService.generate(aBearer, anUrl);
    }

    SignerCreateResponse create(final SignerCreateRequest anInput);
}
