package io.github.jsousa32.libdealsign.usecases.profiles.models.update;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.usecases.common_enums.Receivings;

import java.util.Set;

public class ProfileUpdate {

    private String uuid;

    private String email;

    private String phoneNumber;

    private Set<Authentication> authentications;

    private Set<Receivings> receivings;

    private boolean principal;

    public ProfileUpdate() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Authentication> getAuthentications() {
        return authentications;
    }

    public Set<Receivings> getReceivings() {
        return receivings;
    }

    public boolean isPrincipal() {
        return principal;
    }
}
