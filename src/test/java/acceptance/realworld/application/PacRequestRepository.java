package acceptance.realworld.application;

import acceptance.realworld.domain.MSISDN;
import acceptance.realworld.domain.pac.PortingAuthorizationResult;

public interface PacRequestRepository {
    void savePacRequest(MSISDN msisdn, PortingAuthorizationResult portingAuthorizationResult);
}
