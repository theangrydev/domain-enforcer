package acceptance.realworld.wiring;

import acceptance.realworld.application.PacRequestRepository;
import acceptance.realworld.infrastructure.httpclient.HttpClientFactory;
import acceptance.realworld.userinterface.httpserver.HttpServerBuilder;
import acceptance.realworld.domain.pac.PortingAuthorizationService;
import acceptance.realworld.infrastructure.httpclient.HttpClientFactoryImplementation;
import acceptance.realworld.userinterface.httpserver.HttpServerBuilderImplementation;
import acceptance.realworld.infrastructure.persistence.PretendPacRequestRepository;
import acceptance.realworld.infrastructure.thirdparty.ThirdPartyClient;

public class Wiring {

    public PacRequestRepository repository() {
        return new PretendPacRequestRepository();
    }

    public HttpServerBuilder httpServerBuilder() {
        return new HttpServerBuilderImplementation();
    }

    public PortingAuthorizationService portingAuthorizationService() {
        return new ThirdPartyClient(httpClientFactory());
    }

    private HttpClientFactory httpClientFactory() {
        return new HttpClientFactoryImplementation();
    }
}
