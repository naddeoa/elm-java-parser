package org.naddeo.elm.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ForcedStatement
{
    LITERAL("**DBG_LITERAL", "literal");

    public final String token;
    public final String rule;

    public String create(String lang)
    {
        return this.token + " " + lang;
    }
}
