package acceptance.realworld.infrastructure.httpclient;

public class HttpClientFactoryImplementation implements HttpClientFactory {
    @Override
    public HttpClient httpClient() {
        return new HttpClientImplementation();
    }
}
