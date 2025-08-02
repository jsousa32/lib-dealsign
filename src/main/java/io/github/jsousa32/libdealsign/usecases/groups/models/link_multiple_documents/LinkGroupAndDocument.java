package io.github.jsousa32.libdealsign.usecases.groups.models.link_multiple_documents;

import io.github.jsousa32.libdealsign.usecases.groups.models.common.Authentication;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class LinkGroupAndDocument {

    private final Set<String> documentsUuid;

    private final Set<String> groupsUuid;

    private boolean refusable;

    private final Set<Authentication> authentications;

    private LinkGroupAndDocument(
            final Set<String> aDocumentsUuid,
            final Set<String> aGroupsUuid,
            final boolean aRefusable,
            final Set<Authentication> anAuthentications
    ) {
        this.documentsUuid = aDocumentsUuid;
        this.groupsUuid = aGroupsUuid;
        this.refusable = aRefusable;
        this.authentications = anAuthentications;
        this.validate();
    }

    public static LinkGroupAndDocument generate(
            final Set<String> aDocumentsUuid,
            final Set<String> aGroupsUuid
    ) {
        return new LinkGroupAndDocument(aDocumentsUuid, aGroupsUuid, false, new HashSet<>());
    }

    public LinkGroupAndDocument changeRefusable(final boolean anRefusable) {
        this.refusable = anRefusable;
        return this;
    }

    public LinkGroupAndDocument addAuthentication(final Authentication anAuthentication) {
        if (anAuthentication != null) {
            getAuthentications().add(anAuthentication);
        }

        return this;
    }

    public LinkGroupAndDocument addAuthentication(final Set<Authentication> anAuthentication) {
        if (!anAuthentication.isEmpty()) {
            getAuthentications().addAll(anAuthentication);
        }

        return this;
    }

    public LinkGroupAndDocument removeAuthentication(final Authentication anAuthentication) {
        if (anAuthentication != null) {
            getAuthentications().remove(anAuthentication);
        }

        return this;
    }

    public LinkGroupAndDocument removeAuthentication(final Set<Authentication> anAuthentication) {
        if (!anAuthentication.isEmpty()) {
            getAuthentications().removeAll(anAuthentication);
        }

        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getDocumentsUuid().isEmpty()) {
            errors.add("documentsUuid");
        }

        if (getGroupsUuid().isEmpty()) {
            errors.add("groupUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public Set<String> getDocumentsUuid() {
        return Optional.ofNullable(this.documentsUuid).orElseGet(HashSet::new);
    }

    public Set<String> getGroupsUuid() {
        return Optional.ofNullable(this.groupsUuid).orElseGet(HashSet::new);
    }

    public boolean isRefusable() {
        return refusable;
    }

    public Set<Authentication> getAuthentications() {
        return Optional.ofNullable(this.authentications).orElseGet(HashSet::new);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final LinkGroupAndDocument that = (LinkGroupAndDocument) o;
        return Objects.equals(getDocumentsUuid(), that.getDocumentsUuid()) &&
               Objects.equals(getGroupsUuid(), that.getGroupsUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumentsUuid(), getGroupsUuid());
    }
}
