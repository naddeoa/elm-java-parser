package org.naddeo.elm.lang;

import java.util.Collection;

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
public class ModuleDefinitionTest extends BaseGrammarTest
{

    @Parameterized.Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(ModuleDefinitionTestData.values());
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
        return ForcedStatement.MODULE_DEFINITION;
    }

    @RequiredArgsConstructor
    public enum ModuleDefinitionTestData implements TestData<ModuleDefinition>
    {

        HAPPY_PATH(TestObject.<ModuleDefinition>builder()
                .parserInput("module MyModule")
                .expectedClass(ModuleDefinition.class)
                .pojoValue(ModuleDefinition.builder()
                        .name("MyModule")
                        .exposes(Exposed.NOTHING)
                        .build())
                .build()),

        DOT_NAME(TestObject.<ModuleDefinition>builder()
                .parserInput("module MyModule.Submodule")
                .expectedClass(ModuleDefinition.class)
                .pojoValue(ModuleDefinition.builder()
                        .name("MyModule.Submodule")
                        .exposes(Exposed.NOTHING)
                        .build())
                .build()),

        EXPOSES(TestObject.<ModuleDefinition>builder()
                .parserInput("module MyModule.Submodule exposing (Html)")
                .expectedClass(ModuleDefinition.class)
                .pojoValue(ModuleDefinition.builder()
                        .name("MyModule.Submodule")
                        .exposes(Exposed.builder()
                                .export("Html")
                                .build())
                        .build())
                .build()),

        EXPOSES_MULTIPLE(TestObject.<ModuleDefinition>builder()
                .parserInput("module MyModule.Submodule exposing (Html, li, ul,div)")
                .expectedClass(ModuleDefinition.class)
                .pojoValue(ModuleDefinition.builder()
                        .name("MyModule.Submodule")
                        .exposes(Exposed.builder()
                                .export("Html")
                                .export("li")
                                .export("ul")
                                .export("div")
                                .build())
                        .build())
                .build()),

        NEW_LINES(TestObject.<ModuleDefinition>builder()
                .parserInput("module MyModule.Submodule \nexposing \n(\nHtml\n,li\n,ul\n,div\n)")
                .expectedClass(ModuleDefinition.class)
                .pojoValue(ModuleDefinition.builder()
                        .name("MyModule.Submodule")
                        .exposes(Exposed.builder()
                                .export("Html")
                                .export("li")
                                .export("ul")
                                .export("div")
                                .build())
            .build())
            .build()),;

        @Getter
        private final TestObject<ModuleDefinition> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


