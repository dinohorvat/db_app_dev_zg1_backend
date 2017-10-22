package database.application.development.model.messages;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.util.Views;

public class Response<T> {
    @JsonView(Views.Response.class)
    private OutputHeader header;
    @JsonView(Views.Response.class)
    private T body;

    public Response() {
    }

    public Response(OutputHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public OutputHeader getHeader() {
        return header;
    }

    public void setHeader(OutputHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[')
                .append("header=")
                .append(header)
                .append(", body=")
                .append(body)
                .append(']');
        return sb.toString();
    }
}
