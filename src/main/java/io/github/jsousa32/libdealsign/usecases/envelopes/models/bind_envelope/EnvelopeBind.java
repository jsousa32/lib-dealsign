package io.github.jsousa32.libdealsign.usecases.envelopes.models.bind_envelope;

import io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles.Type;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EnvelopeBind {

    private final String signerUuid;

    private final String profileUuid;

    private final Boolean refusable;

    private final Set<Type> type;

    private final Set<String> documentsUuid;

    private EnvelopeBind(
            final String aSignerUuid,
            final String aProfileUuid,
            final Boolean aRefusable,
            final Set<Type> aType,
            final Set<String> aDocumentsUuid
    ) {
        this.signerUuid = aSignerUuid;
        this.profileUuid = aProfileUuid;
        this.refusable = aRefusable;
        this.type = aType;
        this.documentsUuid = aDocumentsUuid;
        this.validate();
    }

    public static EnvelopeBind generate(
            final String aSignerUuid,
            final String aProfileUuid,
            final Boolean aRefusable,
            final Set<Type> aType,
            final Set<String> aDocumentsUuid
    ) {
        return new EnvelopeBind(aSignerUuid, aProfileUuid, aRefusable, aType, aDocumentsUuid);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getSignerUuid() == null || getSignerUuid().isEmpty()) {
            errors.add("signerUuid");
        }

        if (getProfileUuid() == null || getProfileUuid().isEmpty()) {
            errors.add("profileUuid");
        }

        if (getRefusable() == null) {
            errors.add("refusable");
        }

        if (getType().isEmpty()) {
            errors.add("type");
        }

        if (getDocumentsUuid().isEmpty()) {
            errors.add("documentsUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getSignerUuid() {
        return signerUuid;
    }

    public String getProfileUuid() {
        return profileUuid;
    }

    public Boolean getRefusable() {
        return refusable;
    }

    public Set<Type> getType() {
        return Optional.ofNullable(type).orElseGet(HashSet::new);
    }

    public Set<String> getDocumentsUuid() {
        return Optional.ofNullable(documentsUuid).orElseGet(HashSet::new);
        ;
    }
}
