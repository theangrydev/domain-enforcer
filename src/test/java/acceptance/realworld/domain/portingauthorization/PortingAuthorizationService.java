package acceptance.realworld.domain.portingauthorization;

import acceptance.realworld.domain.MSISDN;

public interface PortingAuthorizationService {
    PortingAuthorizationResult requestPortingAuthorizationCode(MSISDN msisdn);
}
