package io.github.jsousa32.libdealsign.usecases.templates;

import java.util.Objects;

final class DefaultTemplateService implements  TemplateService {

    private final String bearer;

    private final String url;

    private DefaultTemplateService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultTemplateService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultTemplateService(aBearer, anUrl);
    }
}
