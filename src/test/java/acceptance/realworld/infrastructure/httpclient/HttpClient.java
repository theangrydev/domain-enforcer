package acceptance.realworld.infrastructure.httpclient;

public interface HttpClient {
    HttpResponse handle(HttpRequest httpRequest);
}
