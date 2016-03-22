package acceptance.realworld.infrastructure.httpclient;

public class HttpResponse {

    private final String body;

    public HttpResponse(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
