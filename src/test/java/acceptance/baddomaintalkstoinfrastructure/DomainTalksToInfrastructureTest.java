package acceptance.baddomaintalkstoinfrastructure;

import io.github.theangrydev.domainenforcer.DomainEnforcer;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class DomainTalksToInfrastructureTest implements WithAssertions {
    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/test/java"));

    @Test
    public void shouldReportViolationWhenPackagesOutsideInfrastructureTalkToPackagesInsideInfrastructure() {
        List<String> violations = domainEnforcer.checkThatNobodyTalksTo("acceptance.baddomaintalkstoinfrastructure.infrastructure");

        assertThat(violations).containsExactly("'acceptance.baddomaintalkstoinfrastructure.domain.DomainServiceThatTalksToInfrastructure' talks to 'acceptance.baddomaintalkstoinfrastructure.infrastructure.RepositoryImplementation' but nobody is supposed to talk to 'acceptance.baddomaintalkstoinfrastructure.infrastructure'!");
    }

    @Test
    public void shouldReportViolationWhenDomainPackagesTalkToPackagesOutsideTheDomain() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.baddomaintalkstoinfrastructure.domain").apartFrom("java");

        assertThat(violations).containsExactly("'acceptance.baddomaintalkstoinfrastructure.domain' is only supposed to talk to itself and 'java' but 'acceptance.baddomaintalkstoinfrastructure.domain.DomainServiceThatTalksToInfrastructure' talks to 'acceptance.baddomaintalkstoinfrastructure.infrastructure.RepositoryImplementation'!");
    }
}
