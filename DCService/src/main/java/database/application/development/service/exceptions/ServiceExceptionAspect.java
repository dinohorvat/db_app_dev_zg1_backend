package database.application.development.service.exceptions;

import database.application.development.repository.exceptions.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@Aspect
@Component
public class ServiceExceptionAspect {

    @AfterThrowing(pointcut = "execution(* database.application.development.service.impl.*.*(..))", throwing = "ex")
    public void exceptionHandler(Exception ex) throws Exception {
        String exceptionMsg = MessageFormat.format("\n\tException: {0} \n\tReason: {1}",
                ex.getStackTrace()[0].toString(), ex.getLocalizedMessage());

        if (ex instanceof RepositoryException) {
            throw ex;
        }else {
            log.error(exceptionMsg);
            throw new ServiceException(ex.getLocalizedMessage());
        }
    }
}
