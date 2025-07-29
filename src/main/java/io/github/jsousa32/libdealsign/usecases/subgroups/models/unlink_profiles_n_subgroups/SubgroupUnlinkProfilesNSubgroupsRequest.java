package io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles_n_subgroups;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SubgroupUnlinkProfilesNSubgroupsRequest {

    private final String profileUuid;

    private final Set<String> subGroupUuid;

    private SubgroupUnlinkProfilesNSubgroupsRequest(
            final String aProfileUuid,
            final Set<String> aSubgroupUuid
    ) {
        this.profileUuid = aProfileUuid;
        this.subGroupUuid = aSubgroupUuid;
        this.validate();
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getProfileUuid() == null || getProfileUuid().isEmpty()) {
            errors.add("profileUuid");
        }

        if (getSubGroupUuid().isEmpty()) {
            errors.add("subGroupUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getProfileUuid() {
        return profileUuid;
    }

    public Set<String> getSubGroupUuid() {
        return Optional.ofNullable(this.subGroupUuid).orElseGet(HashSet::new);
    }
}
