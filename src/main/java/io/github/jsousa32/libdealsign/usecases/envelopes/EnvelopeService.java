package io.github.jsousa32.libdealsign.usecases.envelopes;

public interface EnvelopeService {

    static EnvelopeService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultEnvelopeService.generate(aBearer, anUrl);
    }
}
