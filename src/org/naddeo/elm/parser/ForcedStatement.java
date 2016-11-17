package org.naddeo.elm.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ForcedStatement
{
    LITERAL("**DBG_LITERAL", "literal"),
    IMPORT_STMT("**DBG_IMPORT_STMT", "import_stmt"),
    IMPORT_STATEMENTS("**DBG_IMPORT_STMTS", "import_stmts"),
    EXPOSED("**DBG_EXPOSED", "exposed"),
    MODULE_DEFINITION("**DBG_MODULE_DEFINITION", "module_def"),
    MODULE("**DBG_MODULE", "module"),
    ;

    public final String token;
    public final String rule;

    public String create(String lang)
    {
        return this.token + " " + lang;
    }
}
