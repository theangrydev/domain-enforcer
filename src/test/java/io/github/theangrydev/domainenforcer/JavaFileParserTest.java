package io.github.theangrydev.domainenforcer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class JavaFileParserTest implements WithAssertions {

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