package io.github.theangrydev.domainenforcer;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
