package database.application.development.service.impl;

import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.util.Email;
import database.application.development.repository.CustomerDao;
import database.application.development.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;
    private CustomerDao customerDao; // Will be used if sendAll and sendBulk methods are implemented

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, CustomerDao customerDao) {
        this.mailSender = mailSender;
        this.customerDao = customerDao;
    }

    @Override
    public void sendMail(Request<ApplicationInputs> request) {
        Email email = request.getBody().getEmail();
        mailSender.send(email.createSimpleMailMessage());
    }
}
