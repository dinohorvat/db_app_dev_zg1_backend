package database.application.development.web.controller.exceptions;

import database.application.development.repository.exceptions.RepositoryException;
import database.application.development.service.excetions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Aspect
@Component
@Slf4j
public class ApiExceptionAspect {

    @AfterThrowing(pointcut = "execution(* database.application.development.web.controller.*.*(..))", throwing = "ex")
    public void exceptionHandler(Exception ex) throws Exception {
        StringBuilder builder = new StringBuilder();
        String exceptionMsg = MessageFormat.format("\n\tException: {0} \n\tReason: {1}",
                ex.getStackTrace()[0].toString(), ex.getLocalizedMessage());

        builder.append(exceptionMsg);

        if (ex instanceof RepositoryException) {
            throw ex;
        }

        else if (ex instanceof ServiceException) {
            throw ex;
        }

        else if (ex instanceof NullPointerException) {
            builder.append("Null pointer exception");
            builder.append("\n\t Cause: " + ex.getCause().toString());
            log.error(builder.toString());
            throw new ApiException("Generic API Controller Exception - Null Results Returned");
        }

        else if (ex instanceof HttpMessageNotReadableException) {
            builder.append("Malformed request exception");
            log.error(builder.toString());
            throw new BadApiRequest("Malformed Request");
        }

        else {
            builder.append("Generic exception - Contact Administrator");
            log.error(builder.toString());
            throw new ApiException(ex.getMessage());
        }
    }
}
