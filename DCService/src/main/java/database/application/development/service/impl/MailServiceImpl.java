package database.application.development.service.impl;

import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.util.Email;
import database.application.development.repository.CustomerDao;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MailServiceImpl extends ORMConfig implements MailService {

    private JavaMailSender mailSender;
    private CustomerDao customerDao;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, CustomerDao customerDao) {
        super();
        this.mailSender = mailSender;
        this.customerDao = customerDao;
    }

    @Override
    public void sendMail(Request<ApplicationInputs> request) {
        Email email = request.getBody().getEmail();
        mailSender.send(email.createSimpleMailMessage());
    }

    /**
     * Fetch all customer emails from the DB and send an identical email to each.
     *
     * @param request The {@link Request} containing the {@link Email} object to be sent.
     */
    @Override
    public void sendMailToAll(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        List<String> customerEmails = customerDao.getAllCustomerEmails(session);
        Email broadcastEmail = request.getBody().getEmail();
        for (String customerEmail : customerEmails) {
            broadcastEmail.setDestinationEmail(customerEmail);
            mailSender.send(broadcastEmail.createSimpleMailMessage());
        }
        session.getTransaction().commit();
        session.close();
    }
}
