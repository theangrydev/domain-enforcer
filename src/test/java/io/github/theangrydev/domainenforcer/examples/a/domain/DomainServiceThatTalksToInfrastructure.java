package io.github.theangrydev.domainenforcer.examples.a.domain;

import io.github.theangrydev.domainenforcer.examples.a.infrastructure.RepositoryImplementation;

@SuppressWarnings("unused")
public class DomainServiceThatTalksToInfrastructure {

    public void badMethod() {
        new RepositoryImplementation().doImportantThings();
    }
}
