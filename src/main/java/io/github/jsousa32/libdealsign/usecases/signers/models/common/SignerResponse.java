package io.github.jsousa32.libdealsign.usecases.signers.models.common;

import io.github.jsousa32.libdealsign.usecases.profiles.models.common.Profile;

import java.time.LocalDateTime;
import java.util.List;

public class SignerResponse {

    private String name;

    private LocalDateTime birthday;

    private String document;

    private List<Profile> profiles;

    public SignerResponse() {
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

    public List<Profile> getProfiles() {
        return profiles;
    }
}
