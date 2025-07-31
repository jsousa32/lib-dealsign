package io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope;

import io.github.jsousa32.libdealsign.usecases.envelopes.models.common.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnvelopeMountResponse {

    private String uuid;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private Status status;

    private String nodeUuid;

    private String path;

    private List<Documents> documents;

    public EnvelopeMountResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Status getStatus() {
        return status;
    }

    public String getNodeUuid() {
        return nodeUuid;
    }

    public String getPath() {
        return path;
    }

    public List<Documents> getDocuments() {
        return Optional.ofNullable(documents).orElseGet(ArrayList::new);
    }
}
