package org.naddeo.elm.lang;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.naddeo.elm.TestData;
import org.naddeo.elm.TestObject;
import org.naddeo.elm.parser.BaseGrammarTest;
import org.naddeo.elm.parser.ForcedStatement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RunWith(Parameterized.class)
public class ImportStatementTest extends BaseGrammarTest
{

    @Parameterized.Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(ImportStatementTestData.values());
    }

    private TestData currentTest;

    @Test
    public void test()
    {
        assertCanParse(currentTest);
    }

    @Override
    protected ForcedStatement getTestedRule()
    {
        return ForcedStatement.IMPORT_STMT;
    }

    @RequiredArgsConstructor
    public enum ImportStatementTestData implements TestData<ImportStatement>
    {

        HAPPY_PATH(TestObject.<ImportStatement>builder()
                .parserInput("import ModuleDefinition")
                .expectedClass(ImportStatement.class)
                .pojoValue(ImportStatement.builder()
                        .name("ModuleDefinition")
                        .alias(Optional.empty())
                        .exposed(Optional.empty())
                        .build())
                .build()),

        DOT_NAME(TestObject.<ImportStatement>builder()
                .parserInput("import ModuleDefinition.Submodule")
                .expectedClass(ImportStatement.class)
                .pojoValue(ImportStatement.builder()
                        .name("ModuleDefinition.Submodule")
                        .alias(Optional.empty())
                        .exposed(Optional.empty())
                        .build())
                .build()),

        ALIAS(TestObject.<ImportStatement>builder()
                .parserInput("import ModuleDefinition.Submodule as Submodule")
                .expectedClass(ImportStatement.class)
                .pojoValue(ImportStatement.builder()
                        .name("ModuleDefinition.Submodule")
                        .alias(Optional.of("Submodule"))
                        .exposed(Optional.empty())
                        .build())
                .build()),

        EXPOSING(TestObject.<ImportStatement>builder()
                .parserInput("import ModuleDefinition.Submodule as Submodule exposing (Html)")
                .expectedClass(ImportStatement.class)
                .pojoValue(ImportStatement.builder()
                        .name("ModuleDefinition.Submodule")
                        .alias(Optional.of("Submodule"))
                        .exposed(Optional.of(Exposed.builder()
                                .export("Html")
                                .build()))
                        .build())
                .build()),

        EXPOSING_WITHOUT_ALIAS(TestObject.<ImportStatement>builder()
                .parserInput("import ModuleDefinition.Submodule exposing (Html, li, ul)")
                .expectedClass(ImportStatement.class)
                .pojoValue(ImportStatement.builder()
                        .name("ModuleDefinition.Submodule")
                        .alias(Optional.empty())
                        .exposed(Optional.of(Exposed.builder()
                                .export("Html")
                                .export("li")
                                .export("ul")
                                .build()))
                        .build())
                .build()),

        ALL_WITH_NEWLINES(TestObject.<ImportStatement>builder()
                .parserInput("import ModuleDefinition.Submodule as Submodule \nexposing \n(Html\n,li\n,ul\n)")
                .expectedClass(ImportStatement.class)
                .pojoValue(ImportStatement.builder()
                        .name("ModuleDefinition.Submodule")
                        .alias(Optional.of("Submodule"))
                        .exposed(Optional.of(Exposed.builder()
                                .export("Html")
                                .export("li")
                                .export("ul")
                                .build()))
                        .build())
                .build()),
        ;

        @Getter
        private final TestObject<ImportStatement> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


