package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.unlink_subgroups.link_subgroups.GroupUnlinkSubgroupsRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
import org.springframework.http.HttpMethod;

final class DefaultGroupUnlinkSubgroupsUseCase extends UnitUseCase<GroupUnlinkSubgroupsRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupUnlinkSubgroupsUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups/unlink-subgroup");
    }

    public static DefaultGroupUnlinkSubgroupsUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultGroupUnlinkSubgroupsUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final GroupUnlinkSubgroupsRequest anInput) {
        final var rest = RequestUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        rest.exchange(this.url, HttpMethod.POST, httpEntity, Void.class);
    }
}
