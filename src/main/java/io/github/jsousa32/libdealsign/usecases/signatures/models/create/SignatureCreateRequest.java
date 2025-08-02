package io.github.jsousa32.libdealsign.usecases.signatures.models.create;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.usecases.common_enums.Type;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SignatureCreateRequest {

    private final String documentUuid;

    private final String signerUuid;

    private final String profileUuid;

    private final Set<Type> type;

    private final Boolean refusable;

    private final Set<Authentication> authentications;

    private final Integer sequence;

    private SignatureCreateRequest(
            final String aDocumentUuid,
            final String aSignerUuid,
            final String aProfileUuid,
            final Set<Type> aType,
            final Boolean aRefusable,
            final Set<Authentication> anAuthentications,
            final Integer aSequence
    ) {
        this.documentUuid = aDocumentUuid;
        this.signerUuid = aSignerUuid;
        this.profileUuid = aProfileUuid;
        this.type = aType;
        this.refusable = aRefusable;
        this.authentications = anAuthentications;
        this.sequence = aSequence;
        this.validate();
    }

    public static SignatureCreateRequest generate(
            final String aDocumentUuid,
            final String aSignerUuid,
            final String aProfileUuid,
            final Set<Type> aType,
            final Boolean aRefusable,
            final Set<Authentication> anAuthentications,
            final Integer aSequence
    ) {
        return new SignatureCreateRequest(
                aDocumentUuid,
                aSignerUuid,
                aProfileUuid,
                Optional.ofNullable(aType).orElse(Set.of(Type.SIGNER)),
                Optional.ofNullable(aRefusable).orElse(false),
                Optional.ofNullable(anAuthentications).orElse(Set.of(Authentication.EMAIL)),
                Optional.ofNullable(aSequence).orElse(0)
        );
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getDocumentUuid() == null || getDocumentUuid().isEmpty()) {
            errors.add("documentUuid");
        }

        if (getSignerUuid() == null || getSignerUuid().isEmpty()) {
            errors.add("signerUuid");
        }

        if (getProfileUuid() == null || getProfileUuid().isEmpty()) {
            errors.add("profileUuid");
        }

        if (getType().isEmpty()) {
            errors.add("type");
        }

        if (getRefusable() == null) {
            errors.add("refusable");
        }

        if (getAuthentications().isEmpty()) {
            errors.add("authentications");
        }

        if (getSequence() == null) {
            errors.add("sequence");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getDocumentUuid() {
        return documentUuid;
    }

    public String getSignerUuid() {
        return signerUuid;
    }

    public String getProfileUuid() {
        return profileUuid;
    }

    public Set<Type> getType() {
        return Optional.ofNullable(type).orElseGet(HashSet::new);
    }

    public Boolean getRefusable() {
        return refusable;
    }

    public Set<Authentication> getAuthentications() {
        return Optional.ofNullable(authentications).orElseGet(HashSet::new);
    }

    public Integer getSequence() {
        return sequence;
    }
}