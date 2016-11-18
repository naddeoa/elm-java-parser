package org.naddeo.elm.features.imports;

import java.util.Optional;

import com.google.common.collect.ImmutableSet;

import org.junit.Test;
import org.naddeo.elm.lang.ElmPackage;
import org.naddeo.elm.lang.Exposed;
import org.naddeo.elm.lang.ImportStatement;
import org.naddeo.elm.lang.ImportStatements;
import org.naddeo.elm.lang.Module;
import org.naddeo.elm.lang.ModuleDefinition;
import org.naddeo.elm.lang.Project;

import static junit.framework.TestCase.assertEquals;

public class AlphabeticalModuleImportOrganizerTest
{

    // TODO so far so good. Next is to implement and test the STRIP_UNUSED option for imports
    // TODO I also need to make a Modules and Packages to conform to the pattern I've been going with: containers for collections


    @Test
    public void test_organizeImports()
    {
        Module input = Module.builder()
                .moduleDefinition(ModuleDefinition.builder()
                        .name("Module1")
                        .exposes(Exposed.NOTHING)
                        .build())
                .importStatements(ImportStatements.builder()
                        .importStatement(importStatementTemplate.withName("First"))
                        .importStatement(importStatementTemplate.withName("Second"))
                        .importStatement(importStatementTemplate.withName("Third"))
                        .importStatement(importStatementTemplate.withName("Fourth"))
                        .importStatement(importStatementTemplate.withName("Fifth"))
                        .build())
                .build();

        Module expected = Module.builder()
                .moduleDefinition(ModuleDefinition.builder()
                        .name("Module1")
                        .exposes(Exposed.NOTHING)
                        .build())
                .importStatements(ImportStatements.builder()
                        .importStatement(importStatementTemplate.withName("Fifth"))
                        .importStatement(importStatementTemplate.withName("First"))
                        .importStatement(importStatementTemplate.withName("Fourth"))
                        .importStatement(importStatementTemplate.withName("Second"))
                        .importStatement(importStatementTemplate.withName("Third"))
                        .build())
                .build();

        ElmPackage elmPackage = ElmPackage.builder()
                .module(input)
                .build();

        Project project = Project.builder()
                .elmPackage(elmPackage)
                .build();

        Context context = Context.builder()
                .project(project)
                .importOrganizationOptions(ImmutableSet.of(ImportOrganizationOption.ALPHABETICAL))
                .build();

        Module actual = importOrganizer.organizeImports(context, (module) -> module.equals(input));

        assertEquals(expected, actual);
    }

    private final AlphabeticalModuleImportOrganizer importOrganizer = new AlphabeticalModuleImportOrganizer();

    private final ImportStatement importStatementTemplate = ImportStatement.builder()
            .name("__template__")
            .alias(Optional.empty())
            .exposed(Optional.empty())
            .build();
}