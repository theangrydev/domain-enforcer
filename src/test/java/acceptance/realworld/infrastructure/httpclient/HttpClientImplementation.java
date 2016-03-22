package acceptance.realworld.infrastructure.httpclient;

import acceptance.realworld.domain.httpclient.HttpClient;
import acceptance.realworld.domain.httpclient.HttpRequest;
import acceptance.realworld.domain.httpclient.HttpResponse;

public class HttpClientImplementation implements HttpClient {
    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        return new HttpResponse();
    }
}
