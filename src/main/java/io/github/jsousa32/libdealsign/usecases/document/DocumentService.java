package io.github.jsousa32.libdealsign.usecases.document;

public interface DocumentService {

    static DocumentService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultDocumentService.generate(aBearer, anUrl);
    }
}
