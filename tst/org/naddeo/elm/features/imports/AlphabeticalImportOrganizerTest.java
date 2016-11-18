package org.naddeo.elm.features.imports;

import java.util.Optional;

import org.junit.Test;
import org.naddeo.elm.lang.Exposed;
import org.naddeo.elm.lang.ImportStatement;
import org.naddeo.elm.lang.ImportStatements;
import org.naddeo.elm.lang.Module;
import org.naddeo.elm.lang.ModuleDefinition;

import static junit.framework.TestCase.assertEquals;

public class AlphabeticalImportOrganizerTest
{

    @Test
    public void test_organizeImports()
    {
        Module input = moduleTemplate.withImportStatements(ImportStatements.builder()
                .importStatement(importStatementTemplate.withName("First"))
                .importStatement(importStatementTemplate.withName("Second"))
                .importStatement(importStatementTemplate.withName("Third"))
                .importStatement(importStatementTemplate.withName("Fourth"))
                .importStatement(importStatementTemplate.withName("Fifth"))
                .build());

        Module expected = moduleTemplate.withImportStatements(ImportStatements.builder()
                .importStatement(importStatementTemplate.withName("Fifth"))
                .importStatement(importStatementTemplate.withName("First"))
                .importStatement(importStatementTemplate.withName("Fourth"))
                .importStatement(importStatementTemplate.withName("Second"))
                .importStatement(importStatementTemplate.withName("Third"))
                .build());

        Module actual = importOrganizer.organizeImports(input);

        assertEquals(expected, actual);
    }

    private final AlphabeticalImportOrganizer importOrganizer = new AlphabeticalImportOrganizer();

    private final ImportStatement importStatementTemplate = ImportStatement.builder()
            .name("__template__")
            .alias(Optional.empty())
            .exposed(Optional.empty())
            .build();

    private final Module moduleTemplate = Module.builder()
            .importStatements(ImportStatements.EMPTY)
            .moduleDefinition(ModuleDefinition.builder()
                    .name("MyModule")
                    .exposes(Exposed.NOTHING)
                    .build())
            .build();
}