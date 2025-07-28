package io.github.jsousa32.libdealsign.domain;

import java.time.LocalDateTime;

public class Authentication {

    private final String id;

    private final String bearer;

    private final LocalDateTime expiresAt;

    private Authentication(
            final String anId,
            final String aBearer,
            final LocalDateTime aExpiresAt
    ) {
        this.id = anId;
        this.bearer = aBearer;
        this.expiresAt = aExpiresAt;
    }

    public static Authentication generate(
            final String anUuid,
            final String aBearer
    ) {
        return new Authentication(anUuid, aBearer, LocalDateTime.now().plusMinutes(55));
    }

    public static Authentication with(
            final String anUuid,
            final String aBearer,
            final LocalDateTime anExpriesAt
    ) {
        return new Authentication(anUuid, aBearer, anExpriesAt);
    }

    public String getId() {
        return id;
    }

    public String getBearer() {
        return bearer;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}
