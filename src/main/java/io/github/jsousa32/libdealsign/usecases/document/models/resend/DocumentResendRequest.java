package io.github.jsousa32.libdealsign.usecases.document.models.resend;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Set;

public class DocumentResendRequest {

    private final String documentUuid;

    private final String signerUuid;

    private final String profileUuid;

    private DocumentResendRequest(
            final String aDocumentUuid,
            final String aSignerUuid,
            final String aProfileUuid
    ) {
        this.documentUuid = aDocumentUuid;
        this.signerUuid = aSignerUuid;
        this.profileUuid = aProfileUuid;
        this.validate();
    }

    public static DocumentResendRequest generate(
            final String aDocumentUuid,
            final String aSignerUuid,
            final String aProfileUuid
    ) {
        return new DocumentResendRequest(aDocumentUuid, aSignerUuid, aProfileUuid);
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getDocumentUuid() == null || getDocumentUuid().isBlank()) {
            errors.add("documentUuid");
        }

        if (getSignerUuid() == null || getSignerUuid().isBlank()) {
            errors.add("signerUuid");
        }

        if (getProfileUuid() == null || getProfileUuid().isBlank()) {
            errors.add("profileUuid");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getDocumentUuid() {
        return documentUuid;
    }

    public String getSignerUuid() {
        return signerUuid;
    }

    public String getProfileUuid() {
        return profileUuid;
    }
}
