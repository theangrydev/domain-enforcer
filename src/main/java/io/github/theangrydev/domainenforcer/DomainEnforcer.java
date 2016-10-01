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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.theangrydev.domainenforcer.Import.anImport;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

@SuppressWarnings("PMD.TooManyMethods") //TODO: refactor
public class DomainEnforcer {
    private final Map<String, Set<Import>> packageImportsByPackage;

    public DomainEnforcer(Map<String, Set<Import>> packageImportsByPackage) {
        this.packageImportsByPackage = packageImportsByPackage;
    }

    public static DomainEnforcer enforceSources(Path sourceDirectory) {
        JavaFileParser javaFileParser = new JavaFileParser();
        List<FileCompilationUnit> compilationUnits = javaFileParser.parseJavaFiles(sourceDirectory);

        Map<String, List<FileCompilationUnit>> compilationUnitsByPackage = compilationUnits.stream().collect(groupingBy(DomainEnforcer::packageName));

        Map<String, Set<Import>> packageImports = compilationUnitsByPackage.entrySet().stream().collect(toMap(Entry::getKey, entry -> packagesImported(entry.getValue())));

        return new DomainEnforcer(packageImports);
    }

    public List<String> checkThatNobodyTalksTo(String aPackage) {
        return packageImportsByPackage.entrySet().stream()
                .filter(entry -> !entry.getKey().startsWith(aPackage))
                .flatMap(entry -> entry.getValue().stream().filter(packageImport -> packageImport.importLineStartsWith(aPackage)))
                .map(anImport -> format("'%s' talks to '%s'\nbut nobody is supposed to talk to '%s'!", anImport.unitName, anImport.importEntry, aPackage))
                .collect(toList());
    }

    public PackageOnlyTalksToItselfCommandBuilder checkThatPackageOnlyTalksToItself(String aPackage) {
        if (!packageImportsByPackage.keySet().stream().anyMatch(entry -> entry.startsWith(aPackage))) {
            throw new IllegalArgumentException(format("Package '%s' was not found", aPackage));
        }
        return new PackageOnlyTalksToItselfCommandBuilder(aPackage);
    }

    public class PackageOnlyTalksToItselfCommandBuilder {
        private final String aPackage;

        public PackageOnlyTalksToItselfCommandBuilder(String aPackage) {
            this.aPackage = aPackage;
        }

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

    private static Set<Import> packagesImported(List<FileCompilationUnit> compilationUnits) {
        return compilationUnits.stream().flatMap(DomainEnforcer::importedPackages).collect(toSet());
    }

    private static Stream<Import> importedPackages(FileCompilationUnit compilationUnit) {
        return adaptNull(compilationUnit.getCompilationUnit().getImports()).stream().map(importDeclaration -> anImport(compilationUnit, importDeclaration));
    }

    private static <T> List<T> adaptNull(List<T> listThatMightBeNull) {
        if (listThatMightBeNull == null) {
            return Collections.emptyList();
        }
        return listThatMightBeNull;
    }

    private static String packageName(FileCompilationUnit unit) {
        return unit.getCompilationUnit().getPackage().getName().toString();
    }
}
