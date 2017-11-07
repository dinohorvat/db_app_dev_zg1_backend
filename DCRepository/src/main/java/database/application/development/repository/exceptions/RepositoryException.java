package database.application.development.repository.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException(Exception ex){
        super(ex);
    }

    public RepositoryException(String exMsg){
        super(exMsg);
    }
}
