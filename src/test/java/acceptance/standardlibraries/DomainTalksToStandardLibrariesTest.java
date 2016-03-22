package acceptance.standardlibraries;

import io.github.theangrydev.domainenforcer.DomainEnforcer;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class DomainTalksToStandardLibrariesTest implements WithAssertions {
    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/test/java"));

    @Test
    public void shouldNotReportViolationWhenDomainPackagesTalkToStandardLibrary() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.standardlibraries.domain").apartFrom("java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }
}
