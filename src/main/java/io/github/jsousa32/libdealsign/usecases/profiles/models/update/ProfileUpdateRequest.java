package io.github.jsousa32.libdealsign.usecases.profiles.models.update;

import io.github.jsousa32.libdealsign.usecases.profiles.models.common.Authentication;
import io.github.jsousa32.libdealsign.usecases.profiles.models.common.Receivings;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProfileUpdateRequest {

    private final String profileUuid;

    private final String email;

    private final String phoneNumber;

    private final Set<Authentication> authentications;

    private final Set<Receivings> receivings;

    private boolean principal;

    private ProfileUpdateRequest(
            final String aProfileUuid,
            final String anEmail,
            final String aPhoneNumber,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        this.profileUuid = aProfileUuid;
        this.email = anEmail;
        this.phoneNumber = aPhoneNumber;
        this.authentications = anAuthentications;
        this.receivings = aReceivings;
        this.principal = true;
        this.validate();
    }

    public static ProfileUpdateRequest generate(
            final String aProfileUuid,
            final String anEmail,
            final String aPhoneNumber,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        return new ProfileUpdateRequest(aProfileUuid, anEmail, aPhoneNumber, anAuthentications, aReceivings);
    }

    public ProfileUpdateRequest changePrincipal(final boolean aPrincipal) {
        this.principal = aPrincipal;
        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getProfileUuid() == null || getProfileUuid().isBlank()) {
            errors.add("profileUuid");
        }

        if (getEmail() == null || getEmail().isBlank()) {
            errors.add("email");
        }

        if (getPhoneNumber() == null || getPhoneNumber().isBlank()) {
            errors.add("phoneNumber");
        }

        if (getAuthentications().isEmpty()) {
            errors.add("authentications");
        }

        if (getReceivings().isEmpty()) {
            errors.add("receivings");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getProfileUuid() {
        return profileUuid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Authentication> getAuthentications() {
        return Optional.ofNullable(authentications).orElseGet(HashSet::new);
    }

    public Set<Receivings> getReceivings() {
        return Optional.ofNullable(receivings).orElseGet(HashSet::new);
    }

    public boolean isPrincipal() {
        return principal;
    }
}
