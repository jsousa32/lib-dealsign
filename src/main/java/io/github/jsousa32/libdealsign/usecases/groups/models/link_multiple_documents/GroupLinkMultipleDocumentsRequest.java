package io.github.jsousa32.libdealsign.usecases.groups.models.link_multiple_documents;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GroupLinkMultipleDocumentsRequest {

    private final String envelopeUuid;

    private final Set<LinkGroupAndDocument> linkGroupAndDocuments;

    private GroupLinkMultipleDocumentsRequest(
            final String anEnvelopeUuid,
            final Set<LinkGroupAndDocument> aLinkGroupAndDocuments
    ) {
        this.envelopeUuid = anEnvelopeUuid;
        this.linkGroupAndDocuments = aLinkGroupAndDocuments;
        this.validate();
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getEnvelopeUuid() == null || getEnvelopeUuid().isBlank()) {
            errors.add("envelopeUuid");
        }

        if (getLinkGroupAndDocuments().isEmpty()) {
            errors.add("linkGroupAndDocuments");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public static GroupLinkMultipleDocumentsRequest generate(
            final String anEnvelopeUuid,
            final Set<LinkGroupAndDocument> aLinkGroupAndDocuments
    ) {
        return new GroupLinkMultipleDocumentsRequest(anEnvelopeUuid, aLinkGroupAndDocuments);
    }

    public String getEnvelopeUuid() {
        return envelopeUuid;
    }

    public Set<LinkGroupAndDocument> getLinkGroupAndDocuments() {
        return Optional.ofNullable(linkGroupAndDocuments).orElseGet(HashSet::new);
    }
}
