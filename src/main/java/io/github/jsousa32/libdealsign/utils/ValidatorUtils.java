package io.github.jsousa32.libdealsign.utils;

import java.util.regex.Pattern;

public final class ValidatorUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            Pattern.CASE_INSENSITIVE
    );

    private ValidatorUtils() {
    }

    public static boolean isValidUrl(final String anUrl) {
        if (anUrl == null || anUrl.isEmpty()) {
            return false;
        }

        return URL_PATTERN.matcher(anUrl).matches();
    }

    public static boolean isValidEmail(final String anEmail) {
        if (anEmail == null || anEmail.isEmpty()) {
            return false;
        }

        return EMAIL_PATTERN.matcher(anEmail).matches();
    }
}
