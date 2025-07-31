package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateResponse;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_document.GroupLinkDocumentRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_multiple_documents.GroupLinkMultipleDocumentsRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_subgroups.GroupLinkSubgroupsRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.unlink_subgroups.link_subgroups.GroupUnlinkSubgroupsRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.update.GroupUpdateRequest;

import java.util.Objects;

final class DefaultGroupService implements GroupService {

    private final String bearer;

    private final String url;

    private DefaultGroupService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = Objects.requireNonNull(aBearer, "O Bearer não pode ser nulo.");
        this.url = Objects.requireNonNull(anUrl, "A URL não pode ser nula.");
    }

    public static DefaultGroupService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultGroupService(aBearer, anUrl);
    }

    @Override
    public GroupCreateResponse create(final GroupCreateRequest anInput) {
        return DefaultGroupCreateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void update(final GroupUpdateRequest anInput) {
        DefaultGroupUpdateUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void linkDocument(final GroupLinkDocumentRequest anInput) {
        DefaultGroupLinkDocumentUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void linkMultipleDocuments(final GroupLinkMultipleDocumentsRequest anInput) {
        DefaultGroupLinkMultipleDocumentsUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void linkSubgroups(final GroupLinkSubgroupsRequest anInput) {
        DefaultGroupLinkSubgroupsUseCase.generate(this.bearer, this.url).execute(anInput);
    }

    @Override
    public void unlinkSubgroups(final GroupUnlinkSubgroupsRequest anInput) {
        DefaultGroupUnlinkSubgroupsUseCase.generate(this.bearer, this.url).execute(anInput);
    }
}
