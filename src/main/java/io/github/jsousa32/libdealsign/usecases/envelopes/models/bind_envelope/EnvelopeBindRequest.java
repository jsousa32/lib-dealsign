package io.github.jsousa32.libdealsign.usecases.envelopes.models.bind_envelope;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EnvelopeBindRequest {

    public final String envelopeUuid;

    private final Set<EnvelopeBind> binds;

    private EnvelopeBindRequest(
            final String anEnvelopeUuid,
            final Set<EnvelopeBind> aBinds
    ) {
        this.envelopeUuid = anEnvelopeUuid;
        this.binds = aBinds;
        this.validate();
    }

    public static EnvelopeBindRequest generate(
            final String anEnvelopeUuid,
            final Set<EnvelopeBind> aBinds
    ) {
        return new EnvelopeBindRequest(anEnvelopeUuid, aBinds);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getEnvelopeUuid() == null || !getEnvelopeUuid().isBlank()) {
            errors.add("envelopeUuid");
        }

        if (getBinds().isEmpty()) {
            errors.add("binds");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getEnvelopeUuid() {
        return envelopeUuid;
    }

    public Set<EnvelopeBind> getBinds() {
        return Optional.ofNullable(binds).orElseGet(HashSet::new);
    }
}
