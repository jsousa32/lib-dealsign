package io.github.jsousa32.libdealsign.usecases.document.models.create;

import io.github.jsousa32.libdealsign.usecases.common_enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

public class DocumentCreateResponse {

    private String uuid;

    private LocalDateTime createdAt;

    private LocalDateTime closedAt;

    private String name;

    private String fantasyName;

    private String path;

    private Status status;

    private LocalDateTime deadline;

    private LocalDateTime deletedAt;

    private boolean remind;

    private String folderUuid;

    private Set<String> email;

    public DocumentCreateResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public String getName() {
        return name;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getPath() {
        return path;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public boolean isRemind() {
        return remind;
    }

    public String getFolderUuid() {
        return folderUuid;
    }

    public Set<String> getEmail() {
        return email;
    }
}
