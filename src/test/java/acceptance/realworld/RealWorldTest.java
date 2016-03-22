package acceptance.realworld;

import io.github.theangrydev.domainenforcer.DomainEnforcer;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

public class RealWorldTest implements WithAssertions {

    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/test/java"));

    @Test
    public void domainShouldOnlyTalkToItselfAndJava() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.realworld.domain").apartFrom("java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }

    @Test
    public void applicationShouldOnlyTalkToItselfAndDomainAndJava() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.realworld.application").apartFrom("acceptance.realworld.domain", "java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }

    @Test
    public void userInterfaceShouldOnlyTalkToItselfAndWiringAndApplicationAndDomainAndJava() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.realworld.userinterface").apartFrom("acceptance.realworld.wiring", "acceptance.realworld.application", "acceptance.realworld.domain", "java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }

    @Test
    public void infrastructureShouldOnlyTalkToItselfAndApplicationAndDomainAndJava() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.realworld.infrastructure").apartFrom("acceptance.realworld.application", "acceptance.realworld.domain", "java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }
}
