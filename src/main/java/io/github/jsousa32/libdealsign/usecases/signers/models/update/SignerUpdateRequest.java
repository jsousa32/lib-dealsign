package io.github.jsousa32.libdealsign.usecases.signers.models.update;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.signers.models.common.ProfileSigner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SignerUpdateRequest {

    private String name;

    private LocalDateTime birthday;

    private String document;

    private final Set<ProfileSigner> profiles;

    private SignerUpdateRequest(final Set<ProfileSigner> aProfiles) {
        this.profiles = aProfiles;
        this.validate();
    }

    public static SignerUpdateRequest generate(final Set<ProfileSigner> aProfiles) {
        return new SignerUpdateRequest(aProfiles);
    }

    public SignerUpdateRequest changeName(final String aName) {
        this.name = aName;
        return this;
    }

    public SignerUpdateRequest changeBirthday(final LocalDateTime aBirthday) {
        this.birthday = aBirthday;
        return this;
    }

    public SignerUpdateRequest changeDocument(final String aDocument) {
        this.document = aDocument;
        return this;
    }

    public SignerUpdateRequest addProfiles(final Set<ProfileSigner> aProfiles) {
        this.profiles.addAll(aProfiles);
        return this;
    }

    public SignerUpdateRequest addProfiles(final ProfileSigner aProfile) {
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
