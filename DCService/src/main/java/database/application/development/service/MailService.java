package database.application.development.service;

import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;

public interface MailService {
    void sendMail(Request<ApplicationInputs> request);

    // TODO: 11/13/2017 Add sendBulkMail and sendToAll methods, if time permits.
    /*
    It looks like there's an issue with using Hibernate's session.createQuery() method.
    Apparently, Hibernate moved the Query class to a different package and Spring Boot has problems
    with it. Using session.createQuery() will return a NoSuchMethodException.

    If we can fix that, sending a broadcast email to all customers should be simple enough.
     */
}
