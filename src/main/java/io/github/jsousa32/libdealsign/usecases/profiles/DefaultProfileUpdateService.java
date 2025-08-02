package io.github.jsousa32.libdealsign.usecases.profiles;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.profiles.models.update.ProfileUpdateRequest;
import io.github.jsousa32.libdealsign.usecases.profiles.models.update.ProfileUpdateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultProfileUpdateService extends UseCase<ProfileUpdateResponse, ProfileUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultProfileUpdateService(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/profiles/edit-profile/");
    }

    public static DefaultProfileUpdateService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultProfileUpdateService(aBearer, anUrl);
    }

    @Override
    public ProfileUpdateResponse execute(final ProfileUpdateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anInput.getProfileUuid());

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, ProfileUpdateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o perfil."));
    }
}
