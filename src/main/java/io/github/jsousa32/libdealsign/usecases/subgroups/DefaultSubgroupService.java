package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateResponse;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles.SubgroupLinkProfilesRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles.SubgroupUnlinkProfilesRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles_n_subgroups.SubgroupUnlinkProfilesNSubgroupsRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.update.SubgroupUpdateRequest;

import java.util.Objects;

final class DefaultSubgroupService implements SubgroupService {

    private final String bearer;

    private final String url;

    private DefaultSubgroupService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static SubgroupService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupService(aBearer, anUrl);
    }

    @Override
    public SubgroupCreateResponse create(final SubgroupCreateRequest anInput) {
        return DefaultSubgroupCreateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void update(final SubgroupUpdateRequest anInput) {
        DefaultSubgroupUpdateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void delete(final String anId) {
        DefaultSubgroupDeleteUseCase.generate(this.bearer, this.url).execute(anId);
    }

    @Override
    public void linkProfiles(final SubgroupLinkProfilesRequest anInput) {
        DefaultSubgroupLinkProfilesUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void unlinkProfiles(final SubgroupUnlinkProfilesRequest anInput) {
        DefaultSubgroupUnlinkProfilesUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void unlinkProfilesNSubgroups(final SubgroupUnlinkProfilesNSubgroupsRequest anInput) {
        DefaultSubgroupUnlinkProfilesNSubgroupsUseCase.generate(this.bearer, this.url).execute(anInput);
    }
}
