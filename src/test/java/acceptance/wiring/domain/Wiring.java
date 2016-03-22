package acceptance.wiring.domain;

import acceptance.wiring.infrastructure.RepositoryImplementation;

public class Wiring {

    public Repository repository() {
        return new RepositoryImplementation();
    }
}
