package acceptance.realworld.domain.pac;

import java.util.Optional;

public class FailedPortingAuthorizationResult implements PortingAuthorizationResult {
    private final String errorCode;

    public FailedPortingAuthorizationResult(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public Optional<String> portingAuthorizationCode() {
        return Optional.empty();
    }

    @Override
    public Optional<String> errorCode() {
        return Optional.of(errorCode);
    }
}
