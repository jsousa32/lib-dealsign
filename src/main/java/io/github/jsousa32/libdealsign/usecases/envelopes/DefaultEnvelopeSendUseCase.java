package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.NullaryUseCase;
import io.github.jsousa32.libdealsign.usecases.UnitUseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountResponse;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.send_envelope.EnvelopeSendRequest;
import io.github.jsousa32.libdealsign.utils.HeadersUtils;
import io.github.jsousa32.libdealsign.utils.RestTemplateUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

final class DefaultEnvelopeSendUseCase extends UnitUseCase<EnvelopeSendRequest> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeSendUseCase(
            final String anBearer,
            final String anUrl
    ) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes/send-envelope");
    }

    public static DefaultEnvelopeSendUseCase generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultEnvelopeSendUseCase(aBearer, anUrl);
    }

    @Override
    public void execute(final EnvelopeSendRequest anInput) {
        final var rest = RestTemplateUtils.getInstance();

        final var httpEntity = HeadersUtils.generate(bearer, anInput);

        rest.exchange(this.url, HttpMethod.POST, httpEntity, Void.class);
    }
}
