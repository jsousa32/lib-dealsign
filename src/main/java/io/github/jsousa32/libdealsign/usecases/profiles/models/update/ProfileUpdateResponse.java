package io.github.jsousa32.libdealsign.usecases.profiles.models.update;

import java.util.List;

public class ProfileUpdateResponse {

    private String uuid;

    private String name;

    private String birthday;

    private String document;

    private List<ProfileUpdate> profiles;

    public ProfileUpdateResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDocument() {
        return document;
    }

    public List<ProfileUpdate> getProfiles() {
        return profiles;
    }
}
