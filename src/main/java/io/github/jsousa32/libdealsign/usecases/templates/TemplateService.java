package io.github.jsousa32.libdealsign.usecases.templates;

public interface TemplateService {

    static TemplateService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultTemplateService.generate(aBearer, anUrl);
    }
}
