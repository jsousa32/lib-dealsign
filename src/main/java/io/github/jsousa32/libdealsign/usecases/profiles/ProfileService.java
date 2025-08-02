package io.github.jsousa32.libdealsign.usecases.profiles;

public interface ProfileService {

    static ProfileService builder(
            final String aBearer,
            final String anUrl
    ) {
        return DefaultProfileService.generate(aBearer, anUrl);
    }
}
