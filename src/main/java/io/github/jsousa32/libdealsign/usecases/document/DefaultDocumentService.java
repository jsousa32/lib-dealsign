package io.github.jsousa32.libdealsign.usecases.document;

import io.github.jsousa32.libdealsign.usecases.document.models.resend.DocumentResendRequest;
import io.github.jsousa32.libdealsign.usecases.document.models.resend_all.DocumentResendAllRequest;
import io.github.jsousa32.libdealsign.usecases.document.models.retrive.DocumentRetriveResponse;
import io.github.jsousa32.libdealsign.usecases.document.models.retrive_url.DocumentRetriveUrlResponse;
import io.github.jsousa32.libdealsign.usecases.document.models.send.DocumentSendRequest;

import java.util.Objects;

final class DefaultDocumentService implements DocumentService {

    private final String bearer;

    private final String url;

    private DefaultDocumentService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultDocumentService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDocumentService(aBearer, anUrl);
    }

    @Override
    public DocumentRetriveUrlResponse retrieveUrl(final String anId) {
        return DefaultDocumentRetriveUrlUseCase.generate(this.bearer, this.url).execute(anId);
    }

    @Override
    public void sendDocument(final DocumentSendRequest anInput) {
        DefaultDocumentSendUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void resendAll(DocumentResendAllRequest anInput) {
        DefaultDocumentResendAllUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void resend(final DocumentResendRequest anInput) {
        DefaultDocumentResendUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public DocumentRetriveResponse retrive(final String anId) {
        return DefaultDocumentRetriveUseCase.generate(this.bearer, this.url).execute(anId);
    }
}
