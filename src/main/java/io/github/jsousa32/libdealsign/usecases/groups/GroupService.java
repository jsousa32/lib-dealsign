package io.github.jsousa32.libdealsign.usecases.groups;

public interface GroupService {

    static GroupService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultGroupService.generate(aBearer, anUrl);
    }
}
