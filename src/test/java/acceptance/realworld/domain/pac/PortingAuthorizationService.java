package acceptance.realworld.domain.pac;

import acceptance.realworld.domain.MSISDN;

public interface PortingAuthorizationService {
    PortingAuthorizationResult requestPortingAuthorizationCode(MSISDN msisdn);
}
