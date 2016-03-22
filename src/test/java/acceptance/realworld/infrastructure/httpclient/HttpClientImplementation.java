package acceptance.realworld.infrastructure.httpclient;

public class HttpClientImplementation implements HttpClient {
    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        return new HttpResponse(httpRequest.getBody());
    }
}
