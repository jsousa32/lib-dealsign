package io.github.jsousa32.libdealsign.usecases.subgroups.models.create;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

public class SubgroupCreateRequest {

    private final String name;

    private SubgroupCreateRequest(final String aName) {
        this.name = aName;
        this.validate();
    }

    public static SubgroupCreateRequest generate(final String aName) {
        return new SubgroupCreateRequest(aName);
    }

    private void validate() {
        if (getName() == null || getName().isEmpty()) {
            throw DealsignException.generate("O atributo nome é obrigatório.");
        }
    }

    public String getName() {
        return name;
    }
}
