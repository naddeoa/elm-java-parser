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
CHAR_VALUE      = \'[^\'\\\\]\'
COMMENT         = --.*
TYPE            = [A-Z][_0-9A-Za-z]*
NAME            = [_A-Za-z][_0-9A-Za-z]*

%%

<YYINITIAL> {

    {COMMENT}               {}
    ","                     {}

    // These are special debug tokens that allow us to start from different parts
    // of the grammer. This allows us to write more modular tests on subsets of the langauge.
    "**DBG_VALUE"                   { return symbol(sym.DEBUG_VALUE); }
    "**DBG_LITERAL"                 { return symbol(sym.DEBUG_LITERAL); }

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
    "->"                            { return symbol(sym.R_ARROW); }
    "<|"                            { return symbol(sym.L_PIPE); }
    "|>"                            { return symbol(sym.R_PIPE); }
    "type"                          { return symbol(sym.TYPE); }

    "True"                          { return symbol(sym.BOOLEAN, true); }
    "False"                         { return symbol(sym.BOOLEAN, false); }
    {TYPE}                          { return symbol(sym.TYPE); }
    {INT_VALUE}                     { return symbol(sym.INT_NUM, Integer.parseInt(yytext())); }
    {FLOAT_VALUE}                   { return symbol(sym.FLOAT_NUM, Double.parseDouble(yytext())); }
    {CHAR_VALUE}                    { return symbol(sym.CHR); }
    {STRING_VALUE}                  { return symbol(sym.STR); }


    {LineTerminator}                { return symbol(sym.NLINE); }
    {WhiteSpace}                    {}
    [^]                             {}
}
