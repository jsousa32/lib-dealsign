package io.github.jsousa32.libdealsign.usecases.groups.models.link_subgroups;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GroupLinkSubgroupsRequest {

    private final String groupUuid;

    private final Set<String> subGroupsUuid;

    private GroupLinkSubgroupsRequest(
            final String aGroupdUuid,
            final Set<String> aSubGroupsUuid
    ) {
        this.groupUuid = aGroupdUuid;
        this.subGroupsUuid = aSubGroupsUuid;
        this.validate();
    }

    public static GroupLinkSubgroupsRequest generate(
            final String aGroupdUuid,
            final Set<String> aSubGroupsUuid
    ) {
        return new GroupLinkSubgroupsRequest(aGroupdUuid, aSubGroupsUuid);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getGroupUuid() == null || getGroupUuid().isBlank()) {
            errors.add("groupUuid");
        }

        if (getSubGroupsUuid().isEmpty()) {
            errors.add("subGroupsUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getGroupUuid() {
        return groupUuid;
    }

    public Set<String> getSubGroupsUuid() {
        return Optional.ofNullable(subGroupsUuid).orElseGet(HashSet::new);
    }
}
