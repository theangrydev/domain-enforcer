package acceptance.wiring.domain;

import acceptance.wiring.infrastructure.RepositoryImplementation;

public class Wiring {

    public static Repository repository() {
        return new RepositoryImplementation();
    }
}
