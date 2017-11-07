package database.application.development.web.controller.exceptions;

public class BadApiRequest extends ApiException {
    public BadApiRequest(String ex) {
        super(ex);
    }
}
