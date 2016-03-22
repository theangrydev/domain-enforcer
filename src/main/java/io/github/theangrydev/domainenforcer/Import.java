package io.github.theangrydev.domainenforcer;

import com.github.javaparser.ast.ImportDeclaration;

public class Import {
    private final String importEntry;
    private final String unitName;

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

    public String unitName() {
        return unitName;
    }

    public String importEntry() {
        return importEntry;
    }
}
