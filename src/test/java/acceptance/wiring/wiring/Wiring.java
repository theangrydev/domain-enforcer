package acceptance.wiring.wiring;

import acceptance.wiring.domain.Repository;
import acceptance.wiring.infrastructure.RepositoryImplementation;

public class Wiring {

    public Repository repository() {
        return new RepositoryImplementation();
    }
}
