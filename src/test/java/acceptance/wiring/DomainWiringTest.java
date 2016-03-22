package acceptance.wiring;

import io.github.theangrydev.domainenforcer.DomainEnforcer;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class DomainWiringTest implements WithAssertions {

    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/test/java"));

    @Test
    public void shouldNotReportViolationWhenDomainWiringClassTalksToInfrastructure() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.standardlibraries.domain").apartFrom("java", "acceptance.wiring.wiring");

        assertThat(violations).describedAs("Violations").isEmpty();
    }
}
