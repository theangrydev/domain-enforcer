package acceptance.realworld.domain.portingauthorization;

import java.util.Optional;

public class SuccessfulPortingAuthorizationResult implements PortingAuthorizationResult {
    private final String portingAuthorizationCode;

    public SuccessfulPortingAuthorizationResult(String portingAuthorizationCode) {
        this.portingAuthorizationCode = portingAuthorizationCode;
    }

    @Override
    public Optional<String> portingAuthorizationCode() {
        return Optional.of(portingAuthorizationCode);
    }

    @Override
    public Optional<String> errorCode() {
        return Optional.empty();
    }
}
