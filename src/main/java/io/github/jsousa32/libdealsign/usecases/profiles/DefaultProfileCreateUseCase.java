package io.github.jsousa32.libdealsign.usecases.profiles;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.profiles.models.create.ProfileCreateRequest;
import io.github.jsousa32.libdealsign.usecases.profiles.models.create.ProfileCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultProfileCreateUseCase extends UseCase<ProfileCreateResponse, ProfileCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultProfileCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/profiles/add-profiles/");
    }

    public static DefaultProfileCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultProfileCreateUseCase(aBearer, anUrl);
    }

    @Override
    public ProfileCreateResponse execute(final ProfileCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var url = this.url.concat(anInput.getSignerUuid());

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(url, HttpMethod.PATCH, httpEntity, ProfileCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o perfil."));
    }
}
