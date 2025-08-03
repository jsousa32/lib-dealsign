package io.github.jsousa32.libdealsign.usecases.profiles.models.create;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.usecases.common_enums.Receivings;
import io.github.jsousa32.libdealsign.usecases.profiles.models.common.Profile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ProfileCreateResponse {

    private String uuid;

    private String signerUuid;

    private String email;

    private String phoneNumber;

    private Set<Authentication> authentications;

    private Set<Receivings> receivings;

    private boolean principal;

    public ProfileCreateResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getSignerUuid() {
        return signerUuid;
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
