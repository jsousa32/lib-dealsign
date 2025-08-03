package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_subgroups.GroupLinkSubgroupsRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultGroupLinkSubgroupsUseCase extends UnitUseCase<GroupLinkSubgroupsRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupLinkSubgroupsUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups/link-subgroups");
    }

    public static DefaultGroupLinkSubgroupsUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultGroupLinkSubgroupsUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final GroupLinkSubgroupsRequest anInput) {
        RequestUtils.post(this.bearer, this.url, anInput);
    }
}
