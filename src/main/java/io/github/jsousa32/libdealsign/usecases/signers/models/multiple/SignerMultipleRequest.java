package io.github.jsousa32.libdealsign.usecases.signers.models.multiple;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SignerMultipleRequest {

    private final Set<SignerMultiple> multiples;

    private SignerMultipleRequest(final Set<SignerMultiple> aMultiples) {
        this.multiples = aMultiples;
        this.validate();
    }

    public static SignerMultipleRequest generate(final Set<SignerMultiple> aMultiples) {
        return new SignerMultipleRequest(aMultiples);
    }

    public SignerMultipleRequest addSigner(final SignerMultiple aSignerMultiple) {
        this.multiples.add(aSignerMultiple);
        return this;
    }

    public SignerMultipleRequest removerSigner(final SignerMultiple aSignerMultiple) {
        this.multiples.remove(aSignerMultiple);
        return this;
    }

    private void validate() {
        if (getMultiples().isEmpty()) {
            throw DealsignException.generate("Deve a ver ao menos um signatário.");
        }
    }

    public Set<SignerMultiple> getMultiples() {
        return Optional.ofNullable(multiples).orElseGet(HashSet::new);
    }
}
