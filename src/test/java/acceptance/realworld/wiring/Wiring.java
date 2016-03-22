package acceptance.realworld.wiring;

import acceptance.realworld.domain.Repository;
import acceptance.realworld.domain.httpclient.HttpClientFactory;
import acceptance.realworld.domain.httpserver.HttpServerBuilder;
import acceptance.realworld.infrastructure.httpclient.HttpClientFactoryImplementation;
import acceptance.realworld.infrastructure.httpserver.HttpServerBuilderImplementation;
import acceptance.realworld.infrastructure.persistence.RepositoryImplementation;

public class Wiring {

    public Repository repository() {
        return new RepositoryImplementation();
    }

    public HttpClientFactory httpClientFactory() {
        return new HttpClientFactoryImplementation();
    }

    public HttpServerBuilder httpServerBuilder() {
        return new HttpServerBuilderImplementation();
    }
}
