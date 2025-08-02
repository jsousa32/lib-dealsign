package io.github.jsousa32.libdealsign.usecases.signers.models.multiple;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.ProfileSigner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SignerMultiple {

    private String name;

    private LocalDateTime birthday;

    private String document;

    private final Set<ProfileSigner> profiles;

    private SignerMultiple(final Set<ProfileSigner> aProfiles) {
        this.profiles = aProfiles;
        this.validate();
    }

    public static SignerMultiple generate(final Set<ProfileSigner> aProfiles) {
        return new SignerMultiple(aProfiles);
    }

    public SignerMultiple changeName(final String aName) {
        this.name = aName;
        return this;
    }

    public SignerMultiple changeBirthday(final LocalDateTime aBirthday) {
        this.birthday = aBirthday;
        return this;
    }

    public SignerMultiple changeDocument(final String aDocument) {
        this.document = aDocument;
        return this;
    }

    public SignerMultiple addProfiles(final Set<ProfileSigner> aProfiles) {
        this.profiles.addAll(aProfiles);
        return this;
    }

    public SignerMultiple addProfiles(final ProfileSigner aProfile) {
        this.profiles.add(aProfile);
        return this;
    }

    private void validate() {
        if (getProfiles().isEmpty()) {
            throw DealsignException.generate("O signatário necessitar ter ao menos um perfil.");
        }
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public String getDocument() {
        return document;
    }

    public Set<ProfileSigner> getProfiles() {
        return Optional.ofNullable(profiles).orElseGet(HashSet::new);
    }
}
