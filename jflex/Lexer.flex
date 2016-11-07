package org.naddeo.elm.parser;

import java_cup.runtime.*;

%%

%class ElmLexer

%unicode
%cup
%line
%column

%{

	private Symbol symbol(int type){
		return symbol(type, yytext());
	}

	private Symbol symbol(int type, Object value){
		return new Symbol(type, yyline, yycolumn, value);
	}

%}

LineTerminator 	= \r|\n|\r\n
WhiteSpace    	= {LineTerminator} | [ \t\f]

INT_VALUE       = -?[0-9]+
FLOAT_VALUE     = {INT_VALUE}\.[0-9]*
STRING_VALUE    = \"[^\"\\\\]*\"
COMMENT         = --.*
NAME            = [_A-Za-z][_0-9A-Za-z]*

%%

<YYINITIAL> {

    {COMMENT}               {}
    ","                     {}

    // These are special debug tokens that allow us to start from different parts
    // of the grammer. This allows us to write more modular tests on subsets of the langauge.
    "**DBG_VALUE"                   { return symbol(sym.DEBUG_VALUE); }

    ":"                             { return symbol(sym.COLON); }
    "|"                             { return symbol(sym.BAR); }
    "!"                             { return symbol(sym.EXCLAMATION); }
    "("                             { return symbol(sym.L_PAREN); }
    ")"                             { return symbol(sym.R_PAREN); }
    "="                             { return symbol(sym.EQUALS); }
    "{"                             { return symbol(sym.L_BRACKET); }
    "}"                             { return symbol(sym.R_BRACKET); }
    "["                             { return symbol(sym.L_SQUARE_BRACKET); }
    "]"                             { return symbol(sym.R_SQUARE_BRACKET); }
    "$"                             { return symbol(sym.DOLLAR); }
    "true"                          { return symbol(sym.BOOLEAN_VALUE, Boolean.parseBoolean(yytext())); }
    "false"                         { return symbol(sym.BOOLEAN_VALUE, Boolean.parseBoolean(yytext())); }
    "type"                          { return symbol(sym.TYPE); }

    {NAME}                          { return symbol(sym.NAME); }
    {INT_VALUE}                     { return symbol(sym.INT_VALUE, Integer.parseInt(yytext())); }
    {FLOAT_VALUE}                   { return symbol(sym.FLOAT_VALUE, Double.parseDouble(yytext())); }
    {STRING_VALUE}                  { return symbol(sym.STRING_VALUE); }


    {WhiteSpace}                    {}
    [^]                             {}
}
