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
public class ModuleTest extends BaseGrammarTest
{

    @Parameterized.Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(ModuleTestData.values());
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
        return ForcedStatement.MODULE;
    }

    @RequiredArgsConstructor
    public enum ModuleTestData implements TestData<Module>
    {

        HAPPY_PATH(TestObject.<Module>builder()
                .parserInput("module MyModule")
                .expectedClass(Module.class)
                .pojoValue(Module.builder()
                        .name("MyModule")
                        .exposes(Exposed.NOTHING)
                        .build())
                .build()),

        DOT_NAME(TestObject.<Module>builder()
                .parserInput("module MyModule.Submodule")
                .expectedClass(Module.class)
                .pojoValue(Module.builder()
                        .name("MyModule.Submodule")
                        .exposes(Exposed.NOTHING)
                        .build())
                .build()),

        EXPOSES(TestObject.<Module>builder()
                .parserInput("module MyModule.Submodule exposing (Html)")
                .expectedClass(Module.class)
                .pojoValue(Module.builder()
                        .name("MyModule.Submodule")
                        .exposes(Exposed.builder()
                                .export("Html")
                                .build())
                        .build())
                .build()),

        EXPOSES_MULTIPLE(TestObject.<Module>builder()
                .parserInput("module MyModule.Submodule exposing (Html, li, ul,div)")
                .expectedClass(Module.class)
                .pojoValue(Module.builder()
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
        private final TestObject<Module> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


