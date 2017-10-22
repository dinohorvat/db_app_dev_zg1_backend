package database.application.development.model.messages;

import database.application.development.model.domain.Company;
import lombok.Getter;

@Getter
public class ApplicationInputs {
    private int entityId;
    private Company company;

    public ApplicationInputs setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public ApplicationInputs setCompany(Company company) {
        this.company = company;
        return this;
    }
}
