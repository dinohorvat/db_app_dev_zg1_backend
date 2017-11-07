package database.application.development.repository.exceptions;

public class DBConnectionException extends RepositoryException {
    public DBConnectionException(Exception ex) {
        super(ex);
    }
}
