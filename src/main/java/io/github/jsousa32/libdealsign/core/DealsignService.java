package io.github.jsousa32.libdealsign.core;

public interface DealsignService {

    static DealsignService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultDealsignService.generate(aBearer, anUrl);
    }


}
