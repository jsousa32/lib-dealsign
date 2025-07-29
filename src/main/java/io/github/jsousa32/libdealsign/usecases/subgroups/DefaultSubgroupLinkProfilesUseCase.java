package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles.SubgroupLinkProfilesRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;

final class DefaultSubgroupLinkProfilesUseCase extends UnitUseCase<SubgroupLinkProfilesRequest> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupLinkProfilesUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups/link-profiles");
    }

    public static DefaultSubgroupLinkProfilesUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupLinkProfilesUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final SubgroupLinkProfilesRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        rest.exchange(this.url, HttpMethod.POST, httpEntity, Void.class);
    }
}
