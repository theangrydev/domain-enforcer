package acceptance.realworld.domain.portingauthorization;

import java.util.Optional;

public interface PortingAuthorizationResult {
    Optional<String> portingAuthorizationCode();
    Optional<String> errorCode();
}


