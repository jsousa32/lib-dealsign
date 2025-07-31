package io.github.jsousa32.libdealsign.usecases.groups;

import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.create.GroupCreateResponse;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_document.GroupLinkDocumentRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_multiple_documents.GroupLinkMultipleDocumentsRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.link_subgroups.GroupLinkSubgroupsRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.unlink_subgroups.link_subgroups.GroupUnlinkSubgroupsRequest;
import io.github.jsousa32.libdealsign.usecases.groups.models.update.GroupUpdateRequest;

public interface GroupService {

    static GroupService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultGroupService.generate(aBearer, anUrl);
    }

    GroupCreateResponse create(final GroupCreateRequest anInput);

    void update(final GroupUpdateRequest anInput);

    void linkDocument(final GroupLinkDocumentRequest anInput);

    void linkMultipleDocuments(final GroupLinkMultipleDocumentsRequest anInput);

    void linkSubgroups(final GroupLinkSubgroupsRequest anInput);

    void unlinkSubgroups(final GroupUnlinkSubgroupsRequest anInput);
}
