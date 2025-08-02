package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateRequest;

public interface SignerService {

    static SignerService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSignerService.generate(aBearer, anUrl);
    }

    SignerResponse create(final SignerCreateRequest anInput);

    SignerResponse update(final SignerUpdateRequest anInput);
}
