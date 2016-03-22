package acceptance.realworld.application;

import acceptance.realworld.domain.Repository;
import acceptance.realworld.domain.httpclient.HttpClientFactory;
import acceptance.realworld.domain.httpclient.HttpRequest;
import acceptance.realworld.domain.httpclient.HttpResponse;

@SuppressWarnings("unused")
public class ApplicationService {

    private final HttpClientFactory httpClientFactory;
    private final Repository repository;

    public ApplicationService(HttpClientFactory httpClientFactory, Repository repository) {
        this.httpClientFactory = httpClientFactory;
        this.repository = repository;
    }

    public void doSomething() {
        HttpResponse response = httpClientFactory.httpClient().handle(new HttpRequest());
        repository.doImportantThings();
    }
}
