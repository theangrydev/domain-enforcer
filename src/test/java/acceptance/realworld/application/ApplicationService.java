package acceptance.realworld.application;

import acceptance.realworld.domain.MSISDN;
import acceptance.realworld.domain.pac.PortingAuthorizationResult;
import acceptance.realworld.domain.pac.PortingAuthorizationService;

@SuppressWarnings("unused")
public class ApplicationService {

    private final PortingAuthorizationService portingAuthorizationService;
    private final PacRequestRepository pacRequestRepository;

    public ApplicationService(PortingAuthorizationService portingAuthorizationService, PacRequestRepository pacRequestRepository) {
        this.portingAuthorizationService = portingAuthorizationService;
        this.pacRequestRepository = pacRequestRepository;
    }

    public PortingAuthorizationResult requestPortingAuthorizationCode(MSISDN msisdn) {
        PortingAuthorizationResult portingAuthorizationResult = portingAuthorizationService.requestPortingAuthorizationCode(msisdn);
        pacRequestRepository.savePacRequest(msisdn, portingAuthorizationResult);
        return portingAuthorizationResult;
    }
}
