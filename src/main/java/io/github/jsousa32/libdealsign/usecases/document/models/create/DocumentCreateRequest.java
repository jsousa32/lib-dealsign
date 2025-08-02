package io.github.jsousa32.libdealsign.usecases.document.models.create;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;
import io.github.jsousa32.libdealsign.utils.GenerateFormUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DocumentCreateRequest {

    private static final Logger log = LoggerFactory.getLogger(DocumentCreateRequest.class);

    private MultipartBodyBuilder form;

    private final String name;

    private String folderUuid;

    private Set<String> interestPartiesEmails;

    private final MultipartFile file;

    private DocumentCreateRequest(
            final String aName,
            final MultipartFile aFile
    ) {
        this.name = aName;
        this.file = aFile;
        this.validate();
    }

    public static DocumentCreateRequest generate(
            final String aName,
            final MultipartFile aFile
    ) {
        return new DocumentCreateRequest(aName, aFile);
    }

    public DocumentCreateRequest changeFolderUuid(final String aFolderUuid) {
        this.folderUuid = aFolderUuid;
        this.form.part("folderUuid", this.folderUuid);
        return this;
    }

    public DocumentCreateRequest changeInterestPartiesEmails(final Set<String> aInterestPartiesEmails) {
        this.interestPartiesEmails = aInterestPartiesEmails;
        this.form.part("interestPartiesEmails", this.interestPartiesEmails);
        return this;
    }

    private MultipartBodyBuilder form() {
        try {
            final var form = GenerateFormUtils.generate(this.file);
            form.part("name", this.name);
            return form;
        } catch (IOException ex) {
            log.error("Não foi possível fazer a leitura do arquivo {}", ex.getMessage());
            throw DealsignException.generate("Não foi possível fazer a leitura do arquivo.");
        }
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getName() == null || getName().isBlank()) {
            errors.add("name");
        }

        if (getFile() == null) {
            errors.add("file");
        }

        ErrorUtils.checkIfHasAnyError(errors);

        this.form = form();
    }

    public MultipartBodyBuilder getForm() {
        return form;
    }

    public String getName() {
        return name;
    }

    public String getFolderUuid() {
        return folderUuid;
    }

    public Set<String> getInterestPartiesEmails() {
        return Optional.ofNullable(interestPartiesEmails).orElseGet(HashSet::new);
    }

    public MultipartFile getFile() {
        return file;
    }
}
