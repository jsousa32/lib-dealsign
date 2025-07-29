package io.github.jsousa32.libdealsign.usecases.groups.models.link_multiple_documents;

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
        return linkGroupAndDocuments;
    }
}
