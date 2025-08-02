package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.usecases.document.models.resend.DocumentResendRequest;
import io.github.jsousa32.libdealsign.usecases.document.models.resend_all.DocumentResendAllRequest;
import io.github.jsousa32.libdealsign.usecases.document.models.retrive_url.DocumentRetriveUrlResponse;
import io.github.jsousa32.libdealsign.usecases.document.models.send.DocumentSendRequest;

public interface DocumentService {

    static DocumentService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultDocumentService.generate(aBearer, anUrl);
    }

    DocumentRetriveUrlResponse retrieveUrl(final String anId);

    void sendDocument(final DocumentSendRequest anInput);

    void resendAll(final DocumentResendAllRequest anInput);

    void resend(final DocumentResendRequest anInput);
}
