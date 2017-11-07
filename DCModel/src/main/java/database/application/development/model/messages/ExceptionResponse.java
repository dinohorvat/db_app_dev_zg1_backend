package database.application.development.model.messages;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
public class ExceptionResponse {

    private String code;
    private String message;
    private String messageId;
    private String messageTimestamp;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.messageId = UUID.randomUUID().toString();
        this.messageTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}