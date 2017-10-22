package database.application.development.model.messages;

public class Request<T> {

    private InputHeader header;
    private T body;

    public Request() {
    }

    public Request(InputHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public InputHeader getHeader() {
        return header;
    }

    public void setHeader(InputHeader header) {
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
