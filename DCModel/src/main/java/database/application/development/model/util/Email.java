package database.application.development.model.util;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

/**
 * A class which represents an email to be sent to a customer.
 */
@AllArgsConstructor
public class Email {
    private String destinationEmail, subject, text;

    /**
     * Create a {@link SimpleMailMessage} to be sent by the MailService implementation class.
     *
     * @return The {@link SimpleMailMessage} to be sent.
     */
    public SimpleMailMessage createSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinationEmail);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
}
