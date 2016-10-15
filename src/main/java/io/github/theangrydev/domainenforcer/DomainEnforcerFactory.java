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
import java.util.Set;
import java.util.stream.Stream;

import static io.github.theangrydev.domainenforcer.Import.anImport;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

final class DomainEnforcerFactory {

    private DomainEnforcerFactory() {
        // there should never be an instance
    }

    public static DomainEnforcer enforceSources(Path sourceDirectory) {
        JavaFileParser javaFileParser = new JavaFileParser();
        List<FileCompilationUnit> compilationUnits = javaFileParser.parseJavaFiles(sourceDirectory);

        Map<String, List<FileCompilationUnit>> compilationUnitsByPackage = compilationUnits.stream().collect(groupingBy(DomainEnforcerFactory::packageName));

        Map<String, Set<Import>> packageImports = compilationUnitsByPackage.entrySet().stream().collect(toMap(Map.Entry::getKey, entry -> packagesImported(entry.getValue())));

        return new DomainEnforcer(packageImports);
    }

    private static Set<Import> packagesImported(List<FileCompilationUnit> compilationUnits) {
        return compilationUnits.stream().flatMap(DomainEnforcerFactory::importedPackages).collect(toSet());
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
