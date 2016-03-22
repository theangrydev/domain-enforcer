package io.github.theangrydev.domainenforcer;

import com.github.javaparser.ast.CompilationUnit;

import java.io.File;

public class FileCompilationUnit {

    private final File file;
    private final CompilationUnit compilationUnit;

    public FileCompilationUnit(File file, CompilationUnit compilationUnit) {
        this.file = file;
        this.compilationUnit = compilationUnit;
    }

    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public File getFile() {
        return file;
    }
}

