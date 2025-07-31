package io.github.jsousa32.libdealsign.usecases.envelopes.models.send_envelope;

import io.github.jsousa32.libdealsign.utils.ErrorUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class EnvelopeSendRequest {

    private final String envelopeUuid;

    private final LocalDateTime deadline;

    private Boolean remind;

    private String customMessage;

    private EnvelopeSendRequest(
            final String anEnvelopeUuid,
            final LocalDateTime aDeadline
    ) {
        this.envelopeUuid = anEnvelopeUuid;
        this.deadline = aDeadline;
        this.validate();
    }

    public static EnvelopeSendRequest generate(
            final String anEnvelopeUuid,
            final LocalDateTime aDeadline
    ) {
        return new EnvelopeSendRequest(anEnvelopeUuid, aDeadline);
    }

    public EnvelopeSendRequest changeRemind(final Boolean aRemind) {
        this.remind = aRemind;
        return this;
    }

    public EnvelopeSendRequest changeCustomMessage(final String aCustomMessage) {
        this.customMessage = aCustomMessage;
        return this;
    }

    private void validate() {
        final Set<String> errors = new HashSet<>();

        if (getEnvelopeUuid() == null || getEnvelopeUuid().isBlank()) {
            errors.add("envelopeUuid");
        }

        if (getDeadline() == null || getDeadline().isBefore(LocalDateTime.now())) {
            errors.add("deadline");
        }

        ErrorUtils.checkIfHasAnyError(errors);
    }

    public String getEnvelopeUuid() {
        return envelopeUuid;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Boolean getRemind() {
        return remind;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
