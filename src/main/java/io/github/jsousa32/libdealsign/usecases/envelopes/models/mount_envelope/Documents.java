package io.github.jsousa32.libdealsign.usecases.envelopes.models.mount_envelope;

import io.github.jsousa32.libdealsign.usecases.envelopes.models.common.Status;

public class Documents {

    private String uuid;

    private String name;

    private String key;

    private String path;

    private Status status;

    public Documents() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getPath() {
        return path;
    }

    public Status getStatus() {
        return status;
    }
}
