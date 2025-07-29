package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateResponse;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles.SubgroupLinkProfilesRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles.SubgroupUnlinkProfilesRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.unlink_profiles_n_subgroups.SubgroupUnlinkProfilesNSubgroupsRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.update.SubgroupUpdateRequest;

public interface SubgroupService {

    static SubgroupService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultSubgroupService.generate(aBearer, anUrl);
    }

    SubgroupCreateResponse create(final SubgroupCreateRequest anInput);

    void update(final SubgroupUpdateRequest anInput);

    void delete(final String anId);

    void linkProfiles(final SubgroupLinkProfilesRequest anInput);

    void unlinkProfiles(final SubgroupUnlinkProfilesRequest anInput);

    void unlinkProfilesNSubgroups(final SubgroupUnlinkProfilesNSubgroupsRequest anInput);
}
