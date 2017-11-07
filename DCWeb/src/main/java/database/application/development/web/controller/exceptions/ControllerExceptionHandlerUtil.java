package database.application.development.web.controller.exceptions;

import database.application.development.model.messages.ExceptionResponse;
import database.application.development.repository.exceptions.DatabaseConstraintException;
import database.application.development.repository.exceptions.ResultSetException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class ControllerExceptionHandlerUtil {

    @ExceptionHandler(ResultSetException.class)
    protected ResponseEntity<ExceptionResponse> notFoundException(ResultSetException ex) {
        ExceptionResponse body = new ExceptionResponse("404", ex.getMessage());
        ResponseEntity<ExceptionResponse> response = new ResponseEntity(body, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(DatabaseConstraintException.class)
    protected ResponseEntity<ExceptionResponse> constraintException(DatabaseConstraintException ex) {
        ExceptionResponse body = new ExceptionResponse("400", ex.getCustomLocalizedMsg());
        ResponseEntity<ExceptionResponse> response = new ResponseEntity(body, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ExceptionResponse> missingBodyException(HttpMessageNotReadableException ex) {
        log.error("\n\tMalformed Request\n\t"+ex.getLocalizedMessage());
        ExceptionResponse body = new ExceptionResponse("400", ex.getLocalizedMessage());
        ResponseEntity<ExceptionResponse> response = new ResponseEntity(body, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Exception> genericException(Exception ex) {
        ex.setStackTrace(null);
        ResponseEntity<Exception> response = new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
