package io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SubgroupUnlinkProfilesRequest {

    private final String subgroupUuid;

    private final Set<String> profileUuid;

    private SubgroupUnlinkProfilesRequest(
            final String aSubgroupUuid,
            final Set<String> aProfileUuid
    ) {
        this.subgroupUuid = aSubgroupUuid;
        this.profileUuid = aProfileUuid;
        this.validate();
    }

    public static SubgroupUnlinkProfilesRequest generate(
            final String aSubgroupUuid,
            final Set<String> aProfileUuid
    ) {
        return new SubgroupUnlinkProfilesRequest(aSubgroupUuid, aProfileUuid);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getSubgroupUuid() == null || getSubgroupUuid().isEmpty()) {
            errors.add("subgroupUuid");
        }

        if (getProfileUuid().isEmpty()) {
            errors.add("profileUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getSubgroupUuid() {
        return subgroupUuid;
    }

    public Set<String> getProfileUuid() {
        return Optional.ofNullable(this.profileUuid).orElseGet(HashSet::new);
    }
}
