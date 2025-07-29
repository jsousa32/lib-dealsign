package io.github.jsousa32.libdealsign.usecases.groups.models.unlink_subgroups.link_subgroups;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Set;

public class GroupUnlinkSubgroupsRequest {

    private final String groupUuid;

    private final Set<String> subGroupsUuid;

    private GroupUnlinkSubgroupsRequest(
            final String aGroupdUuid,
            final Set<String> aSubGroupsUuid
    ) {
        this.groupUuid = aGroupdUuid;
        this.subGroupsUuid = aSubGroupsUuid;
        this.validate();
    }

    public static GroupUnlinkSubgroupsRequest generate(
            final String aGroupdUuid,
            final Set<String> aSubGroupsUuid
    ) {
        return new GroupUnlinkSubgroupsRequest(aGroupdUuid, aSubGroupsUuid);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getGroupUuid() == null || getGroupUuid().isEmpty()) {
            errors.add("groupUuid");
        }

        if (getSubGroupsUuid() == null || getSubGroupsUuid().isEmpty()) {
            errors.add("subGroupsUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getGroupUuid() {
        return groupUuid;
    }

    public Set<String> getSubGroupsUuid() {
        return subGroupsUuid;
    }
}
