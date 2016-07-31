/*
 * Copyright 2016 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of domain-enforcer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    public void infrastructureShouldOnlyTalkToItselfAndUserInterfaceAndApplicationAndDomainAndJava() {
        List<String> violations = domainEnforcer.checkThatPackageOnlyTalksToItself("acceptance.realworld.infrastructure").apartFrom("acceptance.realworld.userinterface", "acceptance.realworld.application", "acceptance.realworld.domain", "java");

        assertThat(violations).describedAs("Violations").isEmpty();
    }
}
