package io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;
import io.github.jsousa32.libdealsign.utils.GenerateFormUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class EnvelopeMountRequest {

    private static final Logger log = LoggerFactory.getLogger(EnvelopeMountRequest.class);

    private MultipartBodyBuilder form;

    private final List<MultipartBodyBuilder> documents;

    private final String name;

    private String nodeUuid;

    private EnvelopeMountRequest(
            final List<MultipartFile> aDocuments,
            final String aName
    ) {
        this.documents = generateForm(aDocuments);
        this.name = aName;
        this.validate();
    }

    public static EnvelopeMountRequest generate(
            final List<MultipartFile> aFiles,
            final String aName
    ) {
        return new EnvelopeMountRequest(aFiles, aName);
    }

    public static EnvelopeMountRequest generate(
            final MultipartFile aFile,
            final String aName
    ) {
        return new EnvelopeMountRequest(List.of(aFile), aName);
    }

    public EnvelopeMountRequest addAndRemoveNodeUuid(final String aNodeUuid) {
        this.nodeUuid = aNodeUuid;
        this.form.part("nodeUuid", this.nodeUuid);
        return this;
    }

    private MultipartBodyBuilder form() {
        final var form = GenerateFormUtils.generate();
        form.part("name", this.name);
        form.part("documents", this.documents);
        return form;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getDocuments().isEmpty()) {
            errors.add("documents");
        }

        if (getName() == null || getName().isBlank()) {
            errors.add("name");
        }

        ErrorUtils.checkIfHasAnyError(errors);

        this.form = form();
    }

    private List<MultipartBodyBuilder> generateForm(final List<MultipartFile> aFiles) {
        final var files = new ArrayList<MultipartBodyBuilder>();

        for (MultipartFile aFile : aFiles) {

            try {

                files.add(GenerateFormUtils.generate(aFile));

            } catch (final IOException ex) {
                log.error("Não foi possível fazer a leitura do arquivo {}", ex.getMessage());
                throw DealsignException.generate("Não foi possível fazer a leitura do arquivo.");
            }
        }

        return files;
    }

    public MultipartBodyBuilder getForm() {
        return form;
    }

    public List<MultipartBodyBuilder> getDocuments() {
        return Optional.ofNullable(documents).orElseGet(ArrayList::new);
    }

    public String getName() {
        return name;
    }

    public String getNodeUuid() {
        return nodeUuid;
    }
}
