package io.github.jsousa32.libdealsign.utils.validators;

import java.util.regex.Pattern;

public final class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private EmailValidator() {
    }

    public static boolean isValid(final String anEmail) {
        if (anEmail == null || anEmail.isEmpty()) {
            return false;
        }

        return EMAIL_PATTERN.matcher(anEmail).matches();
    }
}
