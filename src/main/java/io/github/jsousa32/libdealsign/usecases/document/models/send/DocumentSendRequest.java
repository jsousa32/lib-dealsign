package io.github.jsousa32.libdealsign.usecases.document.models.send;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

import java.time.LocalDateTime;

public class DocumentSendRequest {

    public final String documentUuid;

    private LocalDateTime deadline;

    private boolean remind;

    private String customMessage;

    private DocumentSendRequest(final String aDocumentUuid) {
        this.documentUuid = aDocumentUuid;
        this.deadline = LocalDateTime.now().plusDays(7);
        this.remind = false;
        this.validate();
    }

    public static DocumentSendRequest generate(final String aDocumentUuid) {
        return new DocumentSendRequest(aDocumentUuid);
    }

    public DocumentSendRequest changeDeadline(final LocalDateTime aDeadline) {
        this.deadline = aDeadline;
        return this;
    }

    public DocumentSendRequest changeRemind(final boolean aRemind) {
        this.remind = aRemind;
        return this;
    }

    public DocumentSendRequest changeCustomMessage(final String aCustomMessage) {
        this.customMessage = aCustomMessage;
        return this;
    }

    private void validate() {
        if (getDocumentUuid() == null || getDocumentUuid().isBlank()) {
            throw DealsignException.generate("O atributo documentUuid é obrigatório.");
        }
    }

    public String getDocumentUuid() {
        return documentUuid;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public boolean isRemind() {
        return remind;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
