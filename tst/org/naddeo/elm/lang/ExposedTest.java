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
public class ExposedTest extends BaseGrammarTest
{

    @Parameterized.Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(ExposedTestData.values());
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
        return ForcedStatement.EXPOSED;
    }

    @RequiredArgsConstructor
    public enum ExposedTestData implements TestData<Exposed>
    {

        SINGLE(TestObject.<Exposed>builder()
                .parserInput("Html")
                .expectedClass(Exposed.class)
                .pojoValue(Exposed.builder().export("Html").build())
                .build()),

        MULTIPLE(TestObject.<Exposed>builder()
                .parserInput("Html, li, span, div")
                .expectedClass(Exposed.class)
                .pojoValue(Exposed.builder()
                        .export("Html")
                        .export("li")
                        .export("span")
                        .export("div")
                        .build())
                .build()),

        NEW_LINE(TestObject.<Exposed>builder()
                .parserInput("Html\n ,li\n ,span\n ,div")
                .expectedClass(Exposed.class)
                .pojoValue(Exposed.builder()
                        .export("Html")
                        .export("li")
                        .export("span")
                        .export("div")
                        .build())
                .build()),

        ;

        @Getter
        private final TestObject<Exposed> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


