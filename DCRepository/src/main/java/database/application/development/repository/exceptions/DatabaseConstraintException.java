package database.application.development.repository.exceptions;

import lombok.Getter;

@Getter
public class DatabaseConstraintException extends RepositoryException {

    private String customLocalizedMsg;
    public DatabaseConstraintException(Exception ex){
        super(ex.getLocalizedMessage());
    }

    public DatabaseConstraintException setCustomLocalizedMsg(String customLocalizedMsg) {
        this.customLocalizedMsg = customLocalizedMsg;
        return this;
    }
}
