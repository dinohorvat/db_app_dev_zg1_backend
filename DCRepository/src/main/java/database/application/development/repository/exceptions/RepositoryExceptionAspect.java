package database.application.development.repository.exceptions;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Aspect
@Component
@Slf4j
public class RepositoryExceptionAspect {
    @AfterThrowing(pointcut = "execution(* database.application.development.repository.impl.*.*(..)) || " +
            "execution(* database.application.development.repository.relations.impl.*.*(..)) || " +
            "execution(* database.application.development.repository.hst.impl.*.*(..)))", throwing = "ex")
    public void exceptionHandler(Exception ex) throws RepositoryException {

        StringBuilder builder = new StringBuilder();
        String exceptionMsg = MessageFormat.format("\n\tException: {0} \n\tReason: {1}",
                ex.getStackTrace()[0].toString(), ex.getLocalizedMessage());

        builder.append(exceptionMsg);


        if (ex instanceof QueryTimeoutException || ex instanceof CannotGetJdbcConnectionException) {
            builder.append("\n\tQuery has timed-out");

            log.error(builder.toString());

            throw new DBConnectionException(ex);
        }

        else if (ex instanceof MySQLIntegrityConstraintViolationException){
            String sqlState = ((MySQLIntegrityConstraintViolationException) ex).getSQLState();
            int errorCode = ((MySQLIntegrityConstraintViolationException) ex).getErrorCode();

            builder.append("\n\tSQL State Code:" + sqlState);
            builder.append("\n\tVendor Error Code:" + errorCode);

            log.error(builder.toString());
            throw new DatabaseConstraintException(ex);
        }

        else if(ex instanceof ConstraintViolationException) {
            String customLocalMsg = ((ConstraintViolationException) ex).getSQLException().getLocalizedMessage();
            String constraintName = ((ConstraintViolationException) ex).getConstraintName();
            String sqlState = ((ConstraintViolationException) ex).getSQLException().getSQLState();
            int errorCode = ((ConstraintViolationException) ex).getSQLException().getErrorCode();

            builder.append("\n\t Custom message: "+ customLocalMsg);
            builder.append("\n\t Constraint name: "+ constraintName);
            builder.append("\n\t SQL State: "+ sqlState);
            builder.append("\n\t Vendor Error Code: "+ errorCode);

            log.error(builder.toString());
            throw new DatabaseConstraintException(ex).setCustomLocalizedMsg(customLocalMsg);
        }

        else if (ex instanceof EmptyResultDataAccessException || ex instanceof StaleStateException) {

            log.error(builder.toString());
            throw new ResultSetException(ex);
        }

        else if (ex instanceof DuplicateKeyException) {

            log.error(builder.toString());
            throw new DatabaseConstraintException(ex);
        }

        else {
            log.error(builder.toString());
            throw new RepositoryException(ex);
        }
    }
}
