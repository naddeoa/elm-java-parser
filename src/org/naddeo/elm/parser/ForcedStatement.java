package org.naddeo.elm.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ForcedStatement
{
    LITERAL("**DBG_LITERAL", "literal"),
    IMPORT_STMT("**DBG_IMPORT_STMT", "import_stmt"),
    EXPOSED("**DBG_EXPOSED", "exposed"),

    ;

    public final String token;
    public final String rule;

    public String create(String lang)
    {
        return this.token + " " + lang;
    }
}
