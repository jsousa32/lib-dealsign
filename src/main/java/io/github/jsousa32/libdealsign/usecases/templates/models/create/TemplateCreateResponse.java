package io.github.jsousa32.libdealsign.usecases.templates.models.create;

public class TemplateCreateResponse {

    private String uuid;

    private String companyUuid;

    private String name;

    private String path;

    private String folderUuid;

    public TemplateCreateResponse() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getCompanyUuid() {
        return companyUuid;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getFolderUuid() {
        return folderUuid;
    }
}
