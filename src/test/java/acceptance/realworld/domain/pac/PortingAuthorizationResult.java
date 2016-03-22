package acceptance.realworld.domain.pac;

import java.util.Optional;

public interface PortingAuthorizationResult {
    Optional<String> portingAuthorizationCode();
    Optional<String> errorCode();
}


