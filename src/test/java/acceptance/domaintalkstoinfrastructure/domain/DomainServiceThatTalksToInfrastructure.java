package acceptance.domaintalkstoinfrastructure.domain;

import acceptance.domaintalkstoinfrastructure.infrastructure.RepositoryImplementation;

@SuppressWarnings("unused")
public class DomainServiceThatTalksToInfrastructure {

    public void badMethod() {
        new RepositoryImplementation().doImportantThings();
    }
}
