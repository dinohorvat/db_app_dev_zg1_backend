package database.application.development.repository.exceptions;

public class ResultSetException extends RepositoryException {

    public ResultSetException(Exception ex){
        super(ex.getMessage());
    }
}
