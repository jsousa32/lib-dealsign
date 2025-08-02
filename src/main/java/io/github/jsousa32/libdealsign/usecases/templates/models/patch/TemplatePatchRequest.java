package io.github.jsousa32.libdealsign.usecases.templates.models.patch;

import io.github.jsousa32.libdealsign.exceptions.DealsignException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TemplatePatchRequest {

    private final String templateUuid;

    private final Map<String, String> variables;

    private String folderUuid;

    private String fantasyName;

    private String email;

    private TemplatePatchRequest(
            final String aTemplateUuid,
            final Map<String, String> aVariables
    ) {
        this.templateUuid = aTemplateUuid;
        this.variables = aVariables;
        this.validate();
    }

    public static TemplatePatchRequest generate(
            final String aTemplateUuid,
            final Map<String, String> aVariables
    ) {
        return new TemplatePatchRequest(aTemplateUuid, aVariables);
    }

    public TemplatePatchRequest changeFolderUuid(final String aFolderUuid) {
        this.folderUuid = aFolderUuid;
        return this;
    }

    public TemplatePatchRequest changeFantasyName(final String aFantasyName) {
        this.fantasyName = aFantasyName;
        return this;
    }

    public TemplatePatchRequest changeEmail(final String aEmail) {
        this.email = aEmail;
        return this;
    }

    private void validate() {
        if (getTemplateUuid() == null || getTemplateUuid().isBlank()) {
            throw DealsignException.generate("O atributo templateUuid é obrigatório");
        }
    }

    public String getTemplateUuid() {
        return templateUuid;
    }

    public Map<String, String> getVariables() {
        return Optional.ofNullable(variables).orElseGet(HashMap::new);
    }

    public String getFolderUuid() {
        return folderUuid;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getEmail() {
        return email;
    }
}
