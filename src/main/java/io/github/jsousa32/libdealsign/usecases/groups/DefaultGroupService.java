package io.github.jsousa32.libdealsign.usecases.groups;

import java.util.Objects;

final class DefaultGroupService implements GroupService {

    private final String bearer;

    private final String url;

    private DefaultGroupService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultGroupService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultGroupService(aBearer, anUrl);
    }
}
