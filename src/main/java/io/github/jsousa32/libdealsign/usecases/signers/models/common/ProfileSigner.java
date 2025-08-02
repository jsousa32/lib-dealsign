package io.github.jsousa32.libdealsign.usecases.signers.models.common;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.usecases.common_enums.Receivings;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProfileSigner {

    private final String email;

    private String phoneNumber;

    private final Set<Authentication> authentications;

    private final Set<Receivings> receivings;

    private boolean principal;

    private ProfileSigner(
            final String anEmail,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        this.email = anEmail;
        this.authentications = anAuthentications;
        this.receivings = aReceivings;
        this.principal = true;
        this.validate();
    }

    public static ProfileSigner generate(
            final String anEmail,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        return new ProfileSigner(anEmail, anAuthentications, aReceivings);
    }

    public static ProfileSigner generate(
            final String anEmail
    ) {
        return new ProfileSigner(anEmail, Set.of(Authentication.EMAIL), Set.of(Receivings.EMAIL));
    }

    public ProfileSigner changePhoneNumber(final String anPhoneNumber) {
        this.phoneNumber = anPhoneNumber;
        return this;
    }

    public ProfileSigner changePrincipal(final boolean aPrincipal) {
        this.principal = aPrincipal;
        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

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
