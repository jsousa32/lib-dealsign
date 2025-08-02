package io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles;

import io.github.jsousa32.libdealsign.usecases.common_enums.Type;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SubgroupLinkProfilesRequest {

    private final String subGroupUuid;

    private final Set<RelationBody> relationBody;

    private final Set<Type> type;

    private SubgroupLinkProfilesRequest(
            final String aSubgroupUuid,
            final Set<RelationBody> aRelationBody,
            final Set<Type> type
    ) {
        this.subGroupUuid = aSubgroupUuid;
        this.relationBody = aRelationBody;
        this.type = type;
        this.validate();
    }

    public static SubgroupLinkProfilesRequest generate(
            final String aSubgroupUuid,
            final Set<RelationBody> aRelationBody,
            final Set<Type> type
    ) {
        return new SubgroupLinkProfilesRequest(aSubgroupUuid, aRelationBody, type);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getSubGroupUuid() == null || getSubGroupUuid().isEmpty()) {
            errors.add("subGroupUuid");
        }

        if(getRelationBody().isEmpty()) {
            errors.add("relationBody");
        }

        if(getType().isEmpty()) {
            errors.add("type");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getSubGroupUuid() {
        return subGroupUuid;
    }

    public Set<RelationBody> getRelationBody() {
        return Optional.ofNullable(this.relationBody).orElseGet(HashSet::new);
    }

    public Set<Type> getType() {
        return Optional.ofNullable(this.type).orElseGet(HashSet::new);
    }
}
