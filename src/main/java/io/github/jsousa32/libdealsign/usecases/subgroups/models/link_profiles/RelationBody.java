package io.github.jsousa32.libdealsign.usecases.subgroups.models.link_profiles;

public class RelationBody {

    private final String profileUuid;

    private final String signerUuid;

    private RelationBody(
            final String aProfileUuid,
            final String aSignerUuid
    ) {
        this.profileUuid = aProfileUuid;
        this.signerUuid = aSignerUuid;
    }

    public static RelationBody generate(
            final String aProfileUuid,
            final String aSignerUuid
    ) {
        return new RelationBody(aProfileUuid, aSignerUuid);
    }
}
