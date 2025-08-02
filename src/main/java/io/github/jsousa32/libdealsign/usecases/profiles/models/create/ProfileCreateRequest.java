package io.github.jsousa32.libdealsign.usecases.profiles.models.create;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.usecases.common_enums.Receivings;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProfileCreateRequest {

    private final String signerUuid;

    private final String email;

    private String phoneNumber;

    private final Set<Authentication> authentications;

    private final Set<Receivings> receivings;

    private boolean principal;

    private ProfileCreateRequest(
            final String aProfileUuid,
            final String anEmail,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        this.signerUuid = aProfileUuid;
        this.email = anEmail;
        this.authentications = anAuthentications;
        this.receivings = aReceivings;
        this.principal = true;
        this.validate();
    }

    public static ProfileCreateRequest generate(
            final String aProfileUuid,
            final String anEmail,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        return new ProfileCreateRequest(aProfileUuid, anEmail, anAuthentications, aReceivings);
    }

    public static ProfileCreateRequest generate(
            final String aProfileUuid,
            final String anEmail
    ) {
        return new ProfileCreateRequest(aProfileUuid, anEmail, Set.of(Authentication.EMAIL), Set.of(Receivings.EMAIL));
    }

    public ProfileCreateRequest changePhoneNumber(final String aPhoneNumber) {
        this.phoneNumber = aPhoneNumber;
        return this;
    }

    public ProfileCreateRequest changePrincipal(final boolean aPrincipal) {
        this.principal = aPrincipal;
        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getSignerUuid() == null || getSignerUuid().isBlank()) {
            errors.add("signerUuid");
        }

        if (getEmail() == null || getEmail().isBlank()) {
            errors.add("email");
        }

        if (getAuthentications().isEmpty()) {
            errors.add("authentications");
        }

        if (getReceivings().isEmpty()) {
            errors.add("receivings");
        }

        ErrorUtils.checkIfHasAnyError(errors);
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
        return Optional.ofNullable(authentications).orElseGet(HashSet::new);
    }

    public Set<Receivings> getReceivings() {
        return Optional.ofNullable(receivings).orElseGet(HashSet::new);
    }

    public boolean isPrincipal() {
        return principal;
    }
}
