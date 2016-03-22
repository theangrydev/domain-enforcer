package io.github.theangrydev.domainenforcer;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;

public class DomainEnforcerTest implements WithAssertions {

    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/test/java"));

    @Test
    public void shouldFailFastIfAttemptingToCheckThatAPackageOnlyTalksToItselfAndThatPackageIsNotInTheSources() {
        assertThatThrownBy(() -> domainEnforcer.checkThatPackageOnlyTalksToItself("bad.package.name"))
                .hasMessage("Package 'bad.package.name' was not found");
    }
}
