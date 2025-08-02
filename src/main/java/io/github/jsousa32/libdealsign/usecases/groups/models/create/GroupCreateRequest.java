package io.github.jsousa32.libdealsign.usecases.groups.models.create;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

public class GroupCreateRequest {

    private final String name;

    private GroupCreateRequest(final String aName) {
        this.name = aName;
        this.validate();
    }

    public static GroupCreateRequest generate(final String aName) {
        return new GroupCreateRequest(aName);
    }

    private void validate() {
        if (getName() == null || getName().isBlank()) {
            throw DealsignException.generate("O atributo nome é obrigatório.");
        }
    }

    public String getName() {
        return name;
    }
}
