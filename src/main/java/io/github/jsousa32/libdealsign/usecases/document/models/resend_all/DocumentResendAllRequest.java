package io.github.jsousa32.libdealsign.usecases.document.models.resend_all;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

public class DocumentResendAllRequest {

    private final String documentUuid;

    private DocumentResendAllRequest(final String aDocumentUuid) {
        this.documentUuid = aDocumentUuid;
        this.validate();
    }

    public static DocumentResendAllRequest generate(final String aDocumentUuid) {
        return new DocumentResendAllRequest(aDocumentUuid);
    }

    private void validate() {
        if (getDocumentUuid() == null || getDocumentUuid().isEmpty()) {
            throw DealsignException.generate("O documentUuid nome é obrigatório.");
        }
    }

    public String getDocumentUuid() {
        return documentUuid;
    }
}
