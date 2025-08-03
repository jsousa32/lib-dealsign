package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;


final class DefaultSubgroupCreateUseCase extends UseCase<SubgroupCreateResponse, SubgroupCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups");
    }

    public static DefaultSubgroupCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupCreateUseCase(aBearer, anUrl);
    }

    @Override
    public SubgroupCreateResponse execute(final SubgroupCreateRequest anInput) {
        return RequestUtils.post(this.bearer, this.url, anInput, SubgroupCreateResponse.class)
        .orElseThrow(() -> DealsignException.generate("Não foi possível criar um sub-grupo"));
    }
}
