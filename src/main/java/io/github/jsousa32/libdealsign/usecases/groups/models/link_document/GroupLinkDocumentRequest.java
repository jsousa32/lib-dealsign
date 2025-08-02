package io.github.jsousa32.libdealsign.usecases.groups.models.link_document;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GroupLinkDocumentRequest {

    private final String documentUuid;

    private final Set<String> groupsUuid;

    private final Set<Authentication> authentications;

    private GroupLinkDocumentRequest(
            final String aDocumentUuid,
            final Set<String> aGroupsUuid,
            final Set<Authentication> anAuthentications
    ) {
        this.documentUuid = aDocumentUuid;
        this.groupsUuid = aGroupsUuid;
        this.authentications = anAuthentications;
        this.validate();
    }

    public static GroupLinkDocumentRequest generate(
            final String documentUuid,
            final Set<String> groupsUuid
    ) {
        return new GroupLinkDocumentRequest(documentUuid, groupsUuid, new HashSet<>());
    }

    public GroupLinkDocumentRequest addAuthentication(final Authentication anAuthentication) {
        if (anAuthentication != null) {
            getAuthentications().add(anAuthentication);
        }

        return this;
    }

    public GroupLinkDocumentRequest addAuthentication(final Set<Authentication> anAuthentication) {
        if (!anAuthentication.isEmpty()) {
            getAuthentications().addAll(anAuthentication);
        }

        return this;
    }

    public GroupLinkDocumentRequest removeAuthentication(final Authentication anAuthentication) {
        if (anAuthentication != null) {
            getAuthentications().remove(anAuthentication);
        }

        return this;
    }

    public GroupLinkDocumentRequest removeAuthentication(final Set<Authentication> anAuthentication) {
        if (!anAuthentication.isEmpty()) {
            getAuthentications().removeAll(anAuthentication);
        }

        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getDocumentUuid() == null || getDocumentUuid().isBlank()) {
            errors.add("documentUuid");
        }

        if (getGroupsUuid().isEmpty()) {
            errors.add("groupsUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getDocumentUuid() {
        return documentUuid;
    }

    public Set<String> getGroupsUuid() {
        return groupsUuid;
    }

    public Set<Authentication> getAuthentications() {
        return Optional.ofNullable(this.authentications).orElseGet(HashSet::new);
    }
}
