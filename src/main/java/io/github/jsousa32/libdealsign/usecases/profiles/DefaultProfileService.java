package io.github.jsousa32.libdealsign.usecases.profiles;

import java.util.Objects;

final class DefaultProfileService implements ProfileService {

    private final String bearer;

    private final String url;

    private DefaultProfileService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultProfileService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultProfileService(aBearer, anUrl);
    }

}
