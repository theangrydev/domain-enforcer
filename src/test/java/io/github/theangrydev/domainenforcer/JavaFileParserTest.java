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

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class JavaFileParserTest implements WithAssertions {

    @Test
    public void notFoundThrowsRuntimeExceptionWithIOExceptionCause() {
        JavaFileParser javaFileParser = new JavaFileParser();

        assertThatThrownBy(() -> javaFileParser.parseJavaFiles(Paths.get("/bad")))
                .hasCauseInstanceOf(IOException.class);
    }

    @Test
    public void unparsableThrowsRuntimeExceptionWithParseExceptionCause() {
        JavaFileParser javaFileParser = new JavaFileParser();

        assertThatThrownBy(() -> javaFileParser.parseJavaFiles(Paths.get("./src/test/resources")))
                .hasCauseInstanceOf(ParseException.class);
    }

    @Test
    public void shouldParseFilesInGivenDirectory() throws IOException {
        JavaFileParser javaFileParser = new JavaFileParser();
        List<FileCompilationUnit> javaFiles = javaFileParser.parseJavaFiles(Paths.get("./src/main/java"));

        assertThat(javaFiles)
                .extracting(FileCompilationUnit::getCompilationUnit)
                .flatExtracting(CompilationUnit::getTypes)
                .extracting(TypeDeclaration::getName)
                .contains("JavaFileParser");
    }
}