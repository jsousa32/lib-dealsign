package io.github.jsousa32.libdealsign.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConverterUtils {

    private static final Logger log = LoggerFactory.getLogger(ConverterUtils.class);

    private static final ObjectMapper obj = new ObjectMapper();

    private ConverterUtils() {
    }

    public static String toString(final Object anInput) {
        try {
            return obj.writeValueAsString(anInput);
        } catch (final JsonProcessingException ex) {
            log.error("Não foi possível converter o objeto {}", ex.getMessage());
            throw DealsignException.generate("Não foi possível converter o objeto.");
        }
    }

    public static <T> T toClass(final String anInput, final Class<T> aResponseType) {
        try {
            return obj.readValue(anInput, aResponseType);
        } catch (final JsonProcessingException ex) {
            log.error("Não foi possível converter o objeto {}", ex.getMessage());
            throw DealsignException.generate("Não foi possível converter o objeto.");
        }
    }
}
