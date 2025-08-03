package io.github.jsousa32.libdealsign.usecases.profiles.models.create;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.*;

public class ProfileCreateRequest {

    private final String signerUuid;

    private final List<ProfileCreate> profiles;

    private ProfileCreateRequest(
            final String aSignerUuid,
            final List<ProfileCreate> aProfiles
    ) {
        this.signerUuid = aSignerUuid;
        this.profiles = aProfiles;
        this.validate();
    }

    public static ProfileCreateRequest generate(
            final String aSignerUuid,
            final List<ProfileCreate> aProfiles
    ) {
        return new ProfileCreateRequest(aSignerUuid, aProfiles);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getSignerUuid() == null || getSignerUuid().isBlank()) {
            errors.add("signerUuid");
        }

        if (getProfiles().isEmpty()) {
            errors.add("profiles");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getSignerUuid() {
        return signerUuid;
    }

    public List<ProfileCreate> getProfiles() {
        return Optional.ofNullable(profiles).orElseGet(ArrayList::new);
    }
}
