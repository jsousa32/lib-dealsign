package io.github.jsousa32.libdealsign.usecases.subgroups;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.subgroups.models.create.SubgroupCreateResponse;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultSubgroupCreateUseCase extends UseCase<SubgroupCreateResponse, SubgroupCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultSubgroupCreateUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/subgroups");
    }

    public static DefaultSubgroupCreateUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultSubgroupCreateUseCase(aBearer, anUrl);
    }

    @Override
    public SubgroupCreateResponse execute(final SubgroupCreateRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        return Optional.of(rest.exchange(this.url, HttpMethod.POST, httpEntity, SubgroupCreateResponse.class))
                .map(ResponseEntity::getBody)
                .orElseThrow(() -> DealsignException.generate("Não foi possível cadastrar o subgroup."));
    }
}
