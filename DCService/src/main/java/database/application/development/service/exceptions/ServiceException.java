package database.application.development.service.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException (String exMsg){
        super(exMsg);
    }
}
