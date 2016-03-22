package acceptance.realworld.infrastructure.httpclient;

public class HttpRequest {

    private final String body;

    public HttpRequest(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
