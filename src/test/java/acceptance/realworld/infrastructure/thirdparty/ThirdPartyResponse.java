package acceptance.realworld.infrastructure.thirdparty;

public class ThirdPartyResponse {
    private final String body;

    public ThirdPartyResponse(String body) {
        this.body = body;
    }

    public boolean isSuccess() {
        return body.contains("accepted");
    }

    public String extractPortingAuthorizationCode() {
        return body.split(":")[2];
    }

    public String extractErrorCode() {
        return body.split(":")[1];
    }
}
