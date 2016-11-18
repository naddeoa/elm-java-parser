package org.naddeo.elm.parser;

import java.io.StringReader;

import org.naddeo.elm.lang.Module;

public class ElmLangParser
{
    public Module parseModule(String module)
    {
        ElmParser p = new ElmParser(new ElmLexer(new StringReader(module)));
        try {
            return (Module) p.parse().value;
        } catch (Exception e) {
            throw new ParseException("Could not parse: " + module, e);
        }
    }
}
