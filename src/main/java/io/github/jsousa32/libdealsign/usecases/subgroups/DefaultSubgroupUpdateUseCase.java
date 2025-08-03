package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.update.SubgroupUpdateRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultSubgroupUpdateUseCase extends UnitUseCase<SubgroupUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupUpdateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups");
    }

    public static DefaultSubgroupUpdateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupUpdateUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final SubgroupUpdateRequest anInput) {
        RequestUtils.patch(this.bearer, this.url, anInput);
    }
}
