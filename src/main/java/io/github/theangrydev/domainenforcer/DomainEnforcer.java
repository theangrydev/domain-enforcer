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
package io.github.theangrydev.domainenforcer;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

/**
 * Use this class to enforce rules between packages.
 */
public class DomainEnforcer {
    private final Map<String, Set<Import>> packageImportsByPackage;

    public DomainEnforcer(Map<String, Set<Import>> packageImportsByPackage) {
        this.packageImportsByPackage = packageImportsByPackage;
    }

    /**
     * Construct a {@link DomainEnforcer} that will enforce files in the given directory.
     *
     * @param sourceDirectory The directory to find your Java source files in
     * @return The {@link DomainEnforcer}
     */
    public static DomainEnforcer enforceSources(Path sourceDirectory) {
        return DomainEnforcerFactory.enforceSources(sourceDirectory);
    }

    /**
     * Check that no other packages talk to the given package.
     *
     * @param aPackage The package to enforce
     * @return A list of all the violations in a human readable format
     */
    public List<String> checkThatNobodyTalksTo(String aPackage) {
        return packageImportsByPackage.entrySet().stream()
                .filter(entry -> !entry.getKey().startsWith(aPackage))
                .flatMap(entry -> entry.getValue().stream().filter(packageImport -> packageImport.importLineStartsWith(aPackage)))
                .map(anImport -> format("'%s' talks to '%s'\nbut nobody is supposed to talk to '%s'!", anImport.unitName, anImport.importEntry, aPackage))
                .collect(toList());
    }

    /**
     * Start to build up a command that will enforce that only certain packages are allowed to talk to the given package.
     *
     * @param aPackage The package to enforce
     * @return The fluent command builder
     */
    public PackageOnlyTalksToItselfAssertion checkThatPackageOnlyTalksToItself(String aPackage) {
        if (!packageImportsByPackage.keySet().stream().anyMatch(entry -> entry.startsWith(aPackage))) {
            throw new IllegalArgumentException(format("Package '%s' was not found", aPackage));
        }
        return new PackageOnlyTalksToItselfAssertion(aPackage);
    }

    /**
     * Gathers all the violations about when packages are not supposed to talk to the package that is being enforced.
     */
    public class PackageOnlyTalksToItselfAssertion {
        private final String aPackage;

        public PackageOnlyTalksToItselfAssertion(String aPackage) {
            this.aPackage = aPackage;
        }

        /**
         * Enforces that only the given packages are allowed to talk to the package being enforced.
         *
         * @param excludedPackages The packages that are allowed to talk to the package being enforced
         * @return A list of all the violations in a human readable format
         */
        public List<String> apartFrom(String... excludedPackages) {
            return apartFrom(stream(excludedPackages).collect(toSet()));
        }

        private List<String> apartFrom(Set<String> excludedPackages) {
            return packageImportsByPackage.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith(aPackage))
                    .flatMap(entry -> entry.getValue().stream().filter(packageImport -> notExcluded(aPackage, excludedPackages, packageImport)))
                    .map(anImport -> format("'%s' is only supposed to talk to itself %s\nbut '%s' talks to '%s'!", aPackage, and(excludedPackages), anImport.unitName, anImport.importEntry))
                    .collect(toList());
        }

        private String and(Set<String> excludedPackages) {
            return excludedPackages.stream().sorted().collect(joining("' and '", "and '", "'"));
        }

        private boolean notExcluded(String aPackage, Set<String> excludedPackages, Import entry) {
            return !Stream.concat(Stream.of(aPackage), excludedPackages.stream()).anyMatch(entry.importEntry::startsWith);
        }
    }
}
