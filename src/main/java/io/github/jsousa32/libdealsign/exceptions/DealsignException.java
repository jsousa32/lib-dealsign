package io.github.jsousa32.libdealsign.exceptions;

public final class DealsignException extends NoStacktraceException {

    private DealsignException(final String aMessage) {
        super(aMessage);
    }

    public static DealsignException generate(final String aMessage) {
        return new DealsignException(aMessage);
    }
}