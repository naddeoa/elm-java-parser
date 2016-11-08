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
public class LiteralTest extends BaseGrammarTest
{

    @Parameterized.Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(LiteralTestData.values());
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
        return ForcedStatement.LITERAL;
    }

    @RequiredArgsConstructor
    public enum LiteralTestData implements TestData<Literal>
    {

        STR(TestObject.<Literal>builder()
                .parserInput("\"string type\"")
                .expectedClass(StringLiteral.class)
                .pojoValue(LITERAL_FACTORY.of("string type"))
                .build()),

        CHR(TestObject.<Literal>builder()
                .parserInput("\'a\'")
                .expectedClass(CharacterLiteral.class)
                .pojoValue(LITERAL_FACTORY.of('a'))
                .build()),

        FLOAT_NUM(TestObject.<Literal>builder()
                .parserInput("1.4")
                .expectedClass(FloatLiteral.class)
                .pojoValue(LITERAL_FACTORY.of(1.4))
                .build()),

        BOOLEAN(TestObject.<Literal>builder()
                .parserInput("True")
                .expectedClass(BooleanLiteral.class)
                .pojoValue(LITERAL_FACTORY.of(true))
                .build()),

        INT_NUM(TestObject.<Literal>builder()
                .parserInput("44")
                .expectedClass(IntegerLiteral.class)
                .pojoValue(LITERAL_FACTORY.of(44))
                .build());

        @Getter
        private final TestObject<Literal> test;

        @Override
        public String toString()
        {
            return testName();
        }
    }
}


