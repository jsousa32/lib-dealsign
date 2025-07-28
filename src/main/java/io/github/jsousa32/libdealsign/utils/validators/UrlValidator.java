package io.github.jsousa32.libdealsign.utils.validators;

import java.util.regex.Pattern;

public final class UrlValidator {

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$",
            Pattern.CASE_INSENSITIVE
    );

    private UrlValidator() {
    }

    public static boolean isValid(final String anUrl) {
        if (anUrl == null || anUrl.isEmpty()) {
            return false;
        }

        return URL_PATTERN.matcher(anUrl).matches();
    }
}
