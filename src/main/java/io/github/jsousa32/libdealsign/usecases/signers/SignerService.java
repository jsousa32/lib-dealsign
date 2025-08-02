package io.github.jsousa32.libdealsign.usecases.signers;

public interface SignerService {

    static SignerService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSignerService.generate(aBearer, anUrl);
    }
}
