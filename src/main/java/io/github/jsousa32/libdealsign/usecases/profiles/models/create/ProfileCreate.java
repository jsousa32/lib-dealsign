package io.github.jsousa32.libdealsign.usecases.profiles.models.create;

import io.github.jsousa32.libdealsign.usecases.common_enums.Authentication;
import io.github.jsousa32.libdealsign.usecases.common_enums.Receivings;
import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProfileCreate {

    private final String email;

    private String phoneNumber;

    private final Set<Authentication> authentications;

    private final Set<Receivings> receivings;

    private boolean principal;

    private ProfileCreate(
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

    public static ProfileCreate generate(
            final String anEmail,
            final Set<Authentication> anAuthentications,
            final Set<Receivings> aReceivings
    ) {
        return new ProfileCreate(anEmail, anAuthentications, aReceivings);
    }

    public static ProfileCreate generate(
            final String anEmail
    ) {
        return new ProfileCreate(anEmail, Set.of(Authentication.EMAIL), Set.of(Receivings.EMAIL));
    }

    public ProfileCreate changePhoneNumber(final String aPhoneNumber) {
        this.phoneNumber = aPhoneNumber;
        return this;
    }

    public ProfileCreate changePrincipal(final boolean aPrincipal) {
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
