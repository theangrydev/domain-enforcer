package io.github.theangrydev.domainenforcer;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class DomainEnforcerTest implements WithAssertions {

    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/test/java"));

    @Test
    public void shouldReportViolationWhenPackagesOutsideInfrastructureTalkToPackagesInsideInfrastructure() {
        List<String> violations = domainEnforcer.checkThatNobodyTalksTo("io.github.theangrydev.domainenforcer.examples.a.infrastructure");

        assertThat(violations).containsExactly("'io.github.theangrydev.domainenforcer.examples.a.domain.DomainServiceThatTalksToInfrastructure' talks to 'io.github.theangrydev.domainenforcer.examples.a.infrastructure.RepositoryImplementation' but nobody is supposed to talk to 'io.github.theangrydev.domainenforcer.examples.a.infrastructure'!");
    }

    @Test
    public void shouldFailFastIfAttemptingToCheckThatAPackageOnlyTalksToItselfAndThatPackageIsNotInTheSources() {
        assertThatThrownBy(() -> domainEnforcer.checkThatPackageOnlyTalksToItself("bad.package.name"))
                .hasMessage("Package 'bad.package.name' was not found");
    }

    @Test
    public void shouldReportViolationWhenDomainPackagesTalkToPackagesOutsideTheDomain() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("io.github.theangrydev.domainenforcer.examples.a.domain").apartFrom("java");

        assertThat(violations).containsExactly("'io.github.theangrydev.domainenforcer.examples.a.domain' is only supposed to talk to itself and 'java' but 'io.github.theangrydev.domainenforcer.examples.a.domain.DomainServiceThatTalksToInfrastructure' talks to 'io.github.theangrydev.domainenforcer.examples.a.infrastructure.RepositoryImplementation'!");
    }

    @Test
    public void shouldNotReportViolationWhenDomainPackagesTalkToStandardLibrary() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("io.github.theangrydev.domainenforcer.examples.b.domain").apartFrom("java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }
}
