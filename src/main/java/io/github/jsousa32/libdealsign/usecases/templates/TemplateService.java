package io.github.jsousa32.libdealsign.usecases.templates;

import io.github.jsousa32.libdealsign.usecases.templates.models.create.TemplateCreateRequest;
import io.github.jsousa32.libdealsign.usecases.templates.models.create.TemplateCreateResponse;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchRequest;
import io.github.jsousa32.libdealsign.usecases.templates.models.patch.TemplatePatchResponse;

public interface TemplateService {

    static TemplateService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultTemplateService.generate(aBearer, anUrl);
    }

    TemplatePatchResponse patch(final TemplatePatchRequest anInput);

    void delete(final String anId);

    TemplateCreateResponse create(final TemplateCreateRequest anInput);
}
