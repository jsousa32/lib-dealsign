package io.github.jsousa32.libdealsign.usecases.signatures.models.create;

import io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles.Type;

import java.time.LocalDateTime;
import java.util.Set;

public class SignatureCreateResponse {

    private String uuid;

    private String documentUuid;

    private String signerUuid;

    private String profileUuid;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;

    private Set<Type> type;

    private Boolean refusable;

    private Integer sequence;

    public SignatureCreateResponse() {
    }

    public String getUuid() {
        return uuid;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public Set<Type> getType() {
        return type;
    }

    public Boolean getRefusable() {
        return refusable;
    }

    public Integer getSequence() {
        return sequence;
    }
}