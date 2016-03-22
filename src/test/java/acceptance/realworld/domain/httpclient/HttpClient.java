package acceptance.realworld.domain.httpclient;

public interface HttpClient {
    HttpResponse handle(HttpRequest httpRequest);
}
