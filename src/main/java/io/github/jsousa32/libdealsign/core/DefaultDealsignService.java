package io.github.jsousa32.libdealsign.core;

final class DefaultDealsignService implements DealsignService {

    private final String bearer;

    private final String url;

    private DefaultDealsignService(
            final String aBearer,
            final String anUrl
    ) {
        this.bearer = aBearer;
        this.url = anUrl;
    }

    public static DefaultDealsignService generate(
            final String aBearer,
            final String anUrl
    ) {
        return new DefaultDealsignService(aBearer, anUrl);
    }
}
