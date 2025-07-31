package io.github.jsousa32.libdealsign.usecases.signatures;

public interface SignatureService {

    static SignatureService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSignatureService.generate(aBearer, anUrl);
    }
}
