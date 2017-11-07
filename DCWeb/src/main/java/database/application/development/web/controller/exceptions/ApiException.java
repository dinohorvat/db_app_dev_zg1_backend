package database.application.development.web.controller.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String ex){
        super(ex);
    }
}
