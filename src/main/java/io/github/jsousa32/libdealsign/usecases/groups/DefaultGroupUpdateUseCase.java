package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.groups.models.update.GroupUpdateRequest;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

final class DefaultGroupUpdateUseCase extends UnitUseCase<GroupUpdateRequest> {

    private final String bearer;

    private final String url;

    private DefaultGroupUpdateUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/groups");
    }

    public static DefaultGroupUpdateUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultGroupUpdateUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final GroupUpdateRequest anInput) {
        RequestUtils.patch(this.bearer, this.url, anInput);
    }
}
