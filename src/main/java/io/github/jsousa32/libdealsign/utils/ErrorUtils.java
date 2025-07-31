package io.github.jsousa32.libdealsign.utils;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

import java.util.Set;

public final class ErrorUtils {

    private ErrorUtils() {
    }

    public static void checkIfHasAnyError(final Set<String> errors) {
        if (errors.isEmpty()) return;

        final var errorsInString = String.join(", ", errors);

        final var initialPhrase = errors.size() == 1 ? "O atributo " : "Os atributos ";

        final var finalPhrase = errors.size() == 1 ? " é obrigatório e/ou está inválido." : " são obrigatórios e/ou estão inválidos.";

        throw DealsignException.generate(initialPhrase.concat(errorsInString).concat(finalPhrase));
    }
}
