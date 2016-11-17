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
public class ImportStatementsTest extends BaseGrammarTest
{

    @Parameterized.Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(ImportStatementsTestData.values());
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
        return ForcedStatement.IMPORT_STATEMENTS;
    }

    @RequiredArgsConstructor
    public enum ImportStatementsTestData implements TestData<ImportStatements>
    {

        HAPPY_PATH(TestObject.<ImportStatements>builder()
                .parserInput(ImportStatementTest.ImportStatementTestData.HAPPY_PATH.getTest().getParserInput() + "\n" + ImportStatementTest.ImportStatementTestData.ALL_WITH_NEWLINES.getTest().getParserInput())
                .expectedClass(ImportStatements.class)
                .pojoValue(ImportStatements.builder()
                        .importStatement(ImportStatementTest.ImportStatementTestData.HAPPY_PATH.getTest().getPojoValue())
                        .importStatement(ImportStatementTest.ImportStatementTestData.ALL_WITH_NEWLINES.getTest().getPojoValue())
                        .build())
                .build()),

        MANY(TestObject.<ImportStatements>builder()
                .parserInput(ImportStatementTest.ImportStatementTestData.HAPPY_PATH.getTest().getParserInput()
                        + "\n"
                        + ImportStatementTest.ImportStatementTestData.ALL_WITH_NEWLINES.getTest().getParserInput()
                        + "\n"
                        + ImportStatementTest.ImportStatementTestData.EXPOSING_WITHOUT_ALIAS.getTest().getParserInput()
                        + "\n"
                        + ImportStatementTest.ImportStatementTestData.EXPOSING.getTest().getParserInput())
                .expectedClass(ImportStatements.class)
                .pojoValue(ImportStatements.builder()
                        .importStatement(ImportStatementTest.ImportStatementTestData.HAPPY_PATH.getTest().getPojoValue())
                        .importStatement(ImportStatementTest.ImportStatementTestData.ALL_WITH_NEWLINES.getTest().getPojoValue())
                        .importStatement(ImportStatementTest.ImportStatementTestData.EXPOSING_WITHOUT_ALIAS.getTest().getPojoValue())
                        .importStatement(ImportStatementTest.ImportStatementTestData.EXPOSING.getTest().getPojoValue())
                        .build())
                .build()),
        ;

        @Getter
        private final TestObject<ImportStatements> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


