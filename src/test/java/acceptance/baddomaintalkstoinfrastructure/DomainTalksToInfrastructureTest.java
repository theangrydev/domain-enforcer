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
