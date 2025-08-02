package io.github.jsousa32.libdealsign.usecases.profiles.models.update;

import java.time.LocalDateTime;
import java.util.List;

public class ProfileUpdateResponse {

    private String name;

    private LocalDateTime birthday;

    private String document;

    private List<Profile> profiles;

    public ProfileUpdateResponse() {
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
