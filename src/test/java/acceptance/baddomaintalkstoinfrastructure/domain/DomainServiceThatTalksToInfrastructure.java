package acceptance.baddomaintalkstoinfrastructure.domain;

import acceptance.baddomaintalkstoinfrastructure.infrastructure.RepositoryImplementation;

@SuppressWarnings("unused")
public class DomainServiceThatTalksToInfrastructure {

    public void badMethod() {
        new RepositoryImplementation().doImportantThings();
    }
}
