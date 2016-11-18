package org.naddeo.elm.parser;

import org.junit.Test;
import org.naddeo.elm.lang.Module;
import org.naddeo.elm.lang.ModuleTest;

import static junit.framework.TestCase.assertEquals;

public class ElmLangParserTest
{
    private final ElmLangParser parser = new ElmLangParser();

    @Test
    public void test_parseModule()
    {
        Module module = parser.parseModule(ModuleTest.ModuleTestData.HAPPY_PATH.getTest().getParserInput());
        assertEquals(ModuleTest.ModuleTestData.HAPPY_PATH.getTest().getPojoValue(), module);
    }
}