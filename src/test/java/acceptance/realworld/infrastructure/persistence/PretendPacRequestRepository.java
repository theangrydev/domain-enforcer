package acceptance.realworld.infrastructure.persistence;

import acceptance.realworld.domain.MSISDN;
import acceptance.realworld.application.PacRequestRepository;
import acceptance.realworld.domain.portingauthorization.PortingAuthorizationResult;

public class PretendPacRequestRepository implements PacRequestRepository {

    @Override
    public void savePacRequest(MSISDN msisdn, PortingAuthorizationResult pacRequestResult) {
        System.out.println(String.format("Saving event about MSISDN=%s and response=%s", msisdn, pacRequestResult));
    }
}
