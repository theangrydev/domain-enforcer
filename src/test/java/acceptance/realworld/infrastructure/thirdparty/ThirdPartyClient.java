package acceptance.realworld.infrastructure.thirdparty;

import acceptance.realworld.domain.MSISDN;
import acceptance.realworld.infrastructure.httpclient.HttpClientFactory;
import acceptance.realworld.infrastructure.httpclient.HttpRequest;
import acceptance.realworld.infrastructure.httpclient.HttpResponse;
import acceptance.realworld.domain.portingauthorization.FailedPortingAuthorizationResult;
import acceptance.realworld.domain.portingauthorization.PortingAuthorizationResult;
import acceptance.realworld.domain.portingauthorization.PortingAuthorizationService;
import acceptance.realworld.domain.portingauthorization.SuccessfulPortingAuthorizationResult;

public class ThirdPartyClient implements PortingAuthorizationService {

    private final HttpClientFactory httpClientFactory;

    public ThirdPartyClient(HttpClientFactory httpClientFactory) {
        this.httpClientFactory = httpClientFactory;
    }

    @Override
    public PortingAuthorizationResult requestPortingAuthorizationCode(MSISDN msisdn) {
        HttpRequest httpRequest = new HttpRequest(msisdn.getValue());
        HttpResponse httpResponse = httpClientFactory.httpClient().handle(httpRequest);

        ThirdPartyResponse thirdPartyResponse = new ThirdPartyResponse(httpResponse.getBody());

        if (thirdPartyResponse.isSuccess()) {
            String portingAuthorizationCode = thirdPartyResponse.extractPortingAuthorizationCode();
            return new SuccessfulPortingAuthorizationResult(portingAuthorizationCode);
        } else {
            return new FailedPortingAuthorizationResult(thirdPartyResponse.extractErrorCode());
        }
    }
}
