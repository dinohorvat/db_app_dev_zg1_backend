package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import database.application.development.model.messages.Response;
import database.application.development.web.controller.exceptions.ControllerExceptionHandlerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Serializer<T> extends ControllerExceptionHandlerUtil {

    private ObjectMapper mapper;

    public Serializer(ObjectMapper mapper) {
        this.mapper = mapper;

    }

    public ResponseEntity<String> serializeResponse(Response result, T classNote) throws JsonProcessingException {
        ObjectWriter writter = mapper.writerWithView(classNote.getClass());
        String jsonResponse = writter.writeValueAsString(result);

        ResponseEntity<String> response = new ResponseEntity<String>(jsonResponse, HttpStatus.OK);

        return response;
    }
}
