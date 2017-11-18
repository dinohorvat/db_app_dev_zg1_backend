package database.application.development.service;

import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;

public interface MailService {
    void sendMail(Request<ApplicationInputs> request);

    void sendMailToAll(Request<ApplicationInputs> request);
}
