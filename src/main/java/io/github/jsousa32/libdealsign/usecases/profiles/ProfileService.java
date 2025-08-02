package io.github.jsousa32.libdealsign.usecases.profiles;

import io.github.jsousa32.libdealsign.usecases.profiles.models.update.ProfileUpdateRequest;
import io.github.jsousa32.libdealsign.usecases.profiles.models.update.ProfileUpdateResponse;

public interface ProfileService {

    static ProfileService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultProfileService.generate(aBearer, anUrl);
    }

    ProfileUpdateResponse update(final ProfileUpdateRequest anInput);
}
