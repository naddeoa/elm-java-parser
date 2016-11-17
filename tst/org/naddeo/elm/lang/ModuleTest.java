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
                .parserInput(ModuleDefinitionTest.ModuleDefinitionTestData.HAPPY_PATH.getTest().getParserInput()
                        + " "
                        + ImportStatementsTest.ImportStatementsTestData.HAPPY_PATH.getTest().getParserInput())
                .expectedClass(Module.class)
                .pojoValue(Module.builder()
                        .moduleDefinition(ModuleDefinitionTest.ModuleDefinitionTestData.HAPPY_PATH.getTest().getPojoValue())
                        .importStatements(ImportStatementsTest.ImportStatementsTestData.HAPPY_PATH.getTest().getPojoValue())
                        .build())
                .build()),

        LOTS_OF_IMPORTS(TestObject.<Module>builder()
                .parserInput(ModuleDefinitionTest.ModuleDefinitionTestData.EXPOSES_MULTIPLE.getTest().getParserInput()
                        + " "
                        + ImportStatementsTest.ImportStatementsTestData.MANY.getTest().getParserInput())
                .expectedClass(Module.class)
                .pojoValue(Module.builder()
                        .moduleDefinition(ModuleDefinitionTest.ModuleDefinitionTestData.EXPOSES_MULTIPLE.getTest().getPojoValue())
                        .importStatements(ImportStatementsTest.ImportStatementsTestData.MANY.getTest().getPojoValue())
                        .build()).build()),;

        @Getter
        private final TestObject<Module> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


