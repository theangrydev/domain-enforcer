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

import com.github.javaparser.ast.ImportDeclaration;

public final class Import {
    public final String importEntry;
    public final String unitName;

    private Import(String importEntry, String unitName) {
        this.importEntry = importEntry;
        this.unitName = unitName;
    }

    public static Import anImport(FileCompilationUnit compilationUnit, ImportDeclaration importDeclaration) {
        String packageImported = importDeclaration.getName().toString();
        String unitName = fullyQualifiedName(compilationUnit);
        return new Import(packageImported, unitName);
    }

    private static String fullyQualifiedName(FileCompilationUnit compilationUnit) {
        return compilationUnit.getCompilationUnit().getPackage().getName() + "." + compilationUnit.getFile().getName().replace(".java", "");
    }

    public boolean importLineStartsWith(String prefix) {
        return importEntry.startsWith(prefix);
    }
}
