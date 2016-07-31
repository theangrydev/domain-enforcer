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

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes") // failing fast is by design
public class JavaFileParser {

    public List<FileCompilationUnit> parseJavaFiles(Path path) {
        try {
            return Files.walk(path).filter(Files::isRegularFile).map(Path::toFile).map(this::parseJavaFile).collect(toList());
        } catch (IOException checkedException) {
            throw new RuntimeException(checkedException);
        }
    }

    private FileCompilationUnit parseJavaFile(File javaFile) {
        try {
            return new FileCompilationUnit(javaFile, JavaParser.parse(javaFile));
        } catch (ParseException | IOException checkedException) {
            throw new RuntimeException(checkedException);
        }
    }
}
