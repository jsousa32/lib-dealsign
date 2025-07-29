package io.github.jsousa32.libdealsign.usecases.groups.models.update;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GroupUpdateRequest {

    private final String uuid;

    private final String name;

    private final Set<String> subGroupsUuid;

    private GroupUpdateRequest(
            final String anUuid,
            final String aName,
            final Set<String> aProfilesUuid
    ) {
        this.uuid = anUuid;
        this.name = aName;
        this.subGroupsUuid = aProfilesUuid;
        this.validate();
    }

    public static GroupUpdateRequest generate(
            final String anUuid,
            final String aName
    ) {
        return new GroupUpdateRequest(anUuid, aName, new HashSet<>());
    }

    public GroupUpdateRequest addSubgroupUuid(final String aSubgroupUuid) {
        if (aSubgroupUuid == null || !aSubgroupUuid.isEmpty()) {
            getSubGroupsUuid().add(aSubgroupUuid);
        }

        return this;
    }

    public GroupUpdateRequest removeSubgroupUuid(final String aSubgroupUuid) {
        if (aSubgroupUuid == null || !aSubgroupUuid.isEmpty()) {
            getSubGroupsUuid().removeIf(p -> p.equals(aSubgroupUuid));
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

    public Set<String> getSubGroupsUuid() {
        return Optional.ofNullable(this.subGroupsUuid).orElse(new HashSet<>());
    }
}
