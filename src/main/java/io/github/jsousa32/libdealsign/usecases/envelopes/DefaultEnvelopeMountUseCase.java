package io.github.jsousa32.libdealsign.usecases.envelopes;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;
import io.github.jsousa32.libdealsign.usecases.UseCase;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountRequest;
import io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope.EnvelopeMountResponse;
import io.github.jsousa32.libdealsign.utils.RequestUtils;

import java.util.Optional;

final class DefaultEnvelopeMountUseCase extends UseCase<EnvelopeMountResponse, EnvelopeMountRequest> {

    private final String bearer;

    private final String url;

    private DefaultEnvelopeMountUseCase(
            final String anBearer,
            final String anUrl) {
        this.bearer = anBearer;
        this.url = anUrl.concat("/envelopes/mount-envelope");
    }

    public static DefaultEnvelopeMountUseCase generate(
            final String aBearer,
            final String anUrl) {
        return new DefaultEnvelopeMountUseCase(aBearer, anUrl);
    }

    @Override
    public EnvelopeMountResponse execute(final EnvelopeMountRequest anInput) {
        return RequestUtils.post(this.bearer, this.url, anInput, EnvelopeMountResponse.class)
                .orElseThrow(() -> DealsignException.generate("Não foi possível montar o envelope"));
    }
}
