package io.github.jsousa32.libdealsign.usecases.subgroups.models.update;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SubgroupUpdateRequest {

    private final String uuid;

    private final String name;

    private final Set<String> profilesUuid;

    private SubgroupUpdateRequest(
            final String anUuid,
            final String aName,
            final Set<String> aProfilesUuid
    ) {
        this.uuid = anUuid;
        this.name = aName;
        this.profilesUuid = aProfilesUuid;
        this.validate();
    }

    public static SubgroupUpdateRequest generate(
            final String anUuid,
            final String aName
    ) {
        return new SubgroupUpdateRequest(anUuid, aName, new HashSet<>());
    }

    public SubgroupUpdateRequest addProfileUuid(final String aProfileUuid) {
        if (aProfileUuid == null || !aProfileUuid.isEmpty()) {
            getProfilesUuid().add(aProfileUuid);
        }

        return this;
    }

    public SubgroupUpdateRequest removeProfileUuid(final String aProfileUuid) {
        if (aProfileUuid == null || !aProfileUuid.isEmpty()) {
            getProfilesUuid().removeIf(p -> p.equals(aProfileUuid));
        }

        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getUuid() == null || getUuid().isEmpty()) {
            errors.add("uuid");
        }

        if (getName() == null || getName().isEmpty()) {
            errors.add("name");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Set<String> getProfilesUuid() {
        return Optional.ofNullable(this.profilesUuid).orElse(new HashSet<>());
    }
}
