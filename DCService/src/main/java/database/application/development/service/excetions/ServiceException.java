package database.application.development.service.excetions;

public class ServiceException extends RuntimeException {
    public ServiceException (String exMsg){
        super(exMsg);
    }
}
