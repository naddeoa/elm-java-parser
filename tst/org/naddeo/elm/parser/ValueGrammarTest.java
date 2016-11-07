package org.naddeo.elm.parser;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.naddeo.elm.parser.test.data.ValueInput;
import org.naddeo.elm.parser.test.data.TestData;

import java.util.Collection;

@AllArgsConstructor
@RunWith(Parameterized.class)
public class ValueGrammarTest extends BaseGrammarTest {

    @Parameters(name = "TestData: {0}")
    public static Collection<TestData[]> data()
    {
        return parameterize(ValueInput.values());
    }

    private TestData currentTest;

    @Test
    public void test()
    {
        assertCanParse(currentTest);
    }

    @Override
    ForcedStatement getTestedRule()
    {
        return ForcedStatement.VALUE;
    }
}
