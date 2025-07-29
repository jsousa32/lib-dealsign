package io.github.jsousa32.libdealsign.usecases.subgroups.models.create;

import java.time.LocalDateTime;

public class SubgroupCreateResponse {

    private String uuid;

    private String name;

    private String companyUuid;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public SubgroupCreateResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
