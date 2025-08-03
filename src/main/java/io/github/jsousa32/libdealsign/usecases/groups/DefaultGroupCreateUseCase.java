package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultGroupCreateUseCase extends UseCase<GroupCreateResponse, GroupCreateRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupCreateUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups");
    }

    public static DefaultGroupCreateUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultGroupCreateUseCase(aBearer, anUrl);
    }

    @Override
    public GroupCreateResponse execute(final GroupCreateRequest anInput) {
        return RequestUtils.post(this.bearer, this.url, anInput, GroupCreateResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível criar um grupo"));
    }
}
