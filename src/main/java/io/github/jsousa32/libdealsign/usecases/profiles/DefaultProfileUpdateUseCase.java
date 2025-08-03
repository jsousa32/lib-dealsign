package io.github.jsousa32.libdealsign.usecases.profiles;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.profiles.models.update.ProfileUpdateRequest;
import io.github.jsousa32.libdealsign.usecases.profiles.models.update.ProfileUpdateResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;
import org.springframework.http.HttpMethod;

final class DefaultProfileUpdateUseCase extends UseCase<ProfileUpdateResponse, ProfileUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultProfileUpdateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/profiles/edit-profile/");
    }

    public static DefaultProfileUpdateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultProfileUpdateUseCase(aBearer, anUrl);
    }

    @Override
    public ProfileUpdateResponse execute(final ProfileUpdateRequest anInput) {
        final var url = this.url.concat(anInput.getProfileUuid());

        return RequestUtils.patch(url, anInput, this.bearer, ProfileUpdateResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível atualizar o perfil."));
    }
}
