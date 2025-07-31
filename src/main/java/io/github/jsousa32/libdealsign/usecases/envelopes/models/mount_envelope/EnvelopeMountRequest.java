package io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class EnvelopeMountRequest {

    private static final Logger log = LoggerFactory.getLogger(EnvelopeMountRequest.class);

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
        return this;
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
    }

    private List<MultipartBodyBuilder> generateForm(final List<MultipartFile> aFiles) {
        final var files = new ArrayList<MultipartBodyBuilder>();

        for (int i = 0; i < aFiles.size(); i++) {

            var filename = "";

            try {
                final var resource = new ByteArrayResource(aFiles.get(i).getBytes()) {
                    @Override
                    public String getFilename() {
                        return filename;
                    }
                };

                final var builder = new MultipartBodyBuilder();

                builder.part("name", Optional.ofNullable(aFiles.get(i).getOriginalFilename()).orElse("Arquivo_".concat(String.valueOf(i))));
                builder.part("file", resource);

                files.add(builder);
            } catch (final IOException ex) {
                log.error("Não foi possível fazer a leitura do arquivo {}", ex.getMessage());
                throw DealsignException.generate("Não foi possível fazer a leitura do arquivo.");
            }
        }

        return files;
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
