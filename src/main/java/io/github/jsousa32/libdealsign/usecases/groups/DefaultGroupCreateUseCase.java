package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultGroupCreateUseCase extends UseCase<GroupCreateResponse, GroupCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups");
    }

    public static DefaultGroupCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultGroupCreateUseCase(aBearer, anUrl);
    }

    @Override
    public GroupCreateResponse execute(final GroupCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, GroupCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o grupo."));
    }
}
