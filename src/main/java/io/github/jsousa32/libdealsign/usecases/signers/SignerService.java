package io.github.jsousa32.libdealsign.usecases.signers;

import io.github.jsousa32.libdealsign.usecases.signers.models.common.SignerResponse;
import io.github.jsousa32.libdealsign.usecases.signers.models.create.SignerCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.multiple.SignerMultipleRequest;
import io.github.jsousa32.libdealsign.usecases.signers.models.update.SignerUpdateRequest;

import java.util.Set;

public interface SignerService {

    static SignerService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSignerService.generate(aBearer, anUrl);
    }

    SignerResponse create(final SignerCreateRequest anInput);

    SignerResponse update(final SignerUpdateRequest anInput);

    SignerResponse retrive(final String anId);

    Set<SignerResponse> multiples(final SignerMultipleRequest anInput);
}
