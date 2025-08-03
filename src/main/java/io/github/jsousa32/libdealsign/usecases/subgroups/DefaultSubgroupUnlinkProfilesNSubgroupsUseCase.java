package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles_n_subgroups.SubgroupUnlinkProfilesNSubgroupsRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultSubgroupUnlinkProfilesNSubgroupsUseCase extends UnitUseCase<SubgroupUnlinkProfilesNSubgroupsRequest> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupUnlinkProfilesNSubgroupsUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups/unlink-profiles-n-subgroups");
    }

    public static DefaultSubgroupUnlinkProfilesNSubgroupsUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupUnlinkProfilesNSubgroupsUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final SubgroupUnlinkProfilesNSubgroupsRequest anInput) {
        RequestUtils.patch(this.bearer, this.url, anInput);
    }
}
