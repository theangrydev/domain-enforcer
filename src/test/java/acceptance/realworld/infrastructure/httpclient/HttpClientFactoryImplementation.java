package acceptance.realworld.infrastructure.httpclient;

import acceptance.realworld.domain.httpclient.HttpClient;
import acceptance.realworld.domain.httpclient.HttpClientFactory;

public class HttpClientFactoryImplementation implements HttpClientFactory {
    @Override
    public HttpClient httpClient() {
        return new HttpClientImplementation();
    }
}
