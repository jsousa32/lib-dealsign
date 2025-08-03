package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles.SubgroupUnlinkProfilesRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultSubgroupUnlinkProfilesUseCase extends UnitUseCase<SubgroupUnlinkProfilesRequest> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupUnlinkProfilesUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups/unlink-profiles");
    }

    public static DefaultSubgroupUnlinkProfilesUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupUnlinkProfilesUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final SubgroupUnlinkProfilesRequest anInput) {
        RequestUtils.patch(this.bearer, this.url, anInput);
    }
}
