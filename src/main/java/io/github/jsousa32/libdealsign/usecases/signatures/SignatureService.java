package io.github.jsousa32.libdealsign.usecases.signatures;

import io.github.jsousa32.libdealsign.usecases.signatures.models.create.SignatureCreateRequest;
import io.github.jsousa32.libdealsign.usecases.signatures.models.create.SignatureCreateResponse;

public interface SignatureService {

    static SignatureService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSignatureService.generate(aBearer, anUrl);
    }

    SignatureCreateResponse create(final SignatureCreateRequest anInput);
}
