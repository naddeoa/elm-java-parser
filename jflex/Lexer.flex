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
DOT_NAME        = [_A-Za-z][_0-9A-Za-z.]*
MODULE_NAME     = [A-Z][_0-9A-Za-z.]*

%state IMPORT
%state IMPORT_AS
%state IMPORT_AS_ALIAS
%state IMPORT_AS_ALIAS_EXPOSING

%state MODULE
%state MODULE_EXPOSING
%state MODULE_EXPOSING_MODULES

%%

{COMMENT}                       {}

// These are special debug tokens that allow us to start from different parts
// of the grammer. This allows us to write more modular tests on subsets of the langauge.
"**DBG_VALUE"                   { return symbol(sym.DEBUG_VALUE); }
"**DBG_LITERAL"                 { return symbol(sym.DEBUG_LITERAL); }
"**DBG_IMPORT_STMT"             { return symbol(sym.DEBUG_IMPORT_STMT); }
"**DBG_EXPOSED"                 { yybegin(IMPORT_AS_ALIAS_EXPOSING); return symbol(sym.DEBUG_EXPOSED); }
"**DBG_MODULE"                  { return symbol(sym.DEBUG_MODULE); }
//"type"                          { return symbol(sym.TYPE); } // should not return TYPE, its a type definition

"import"                        { yybegin(IMPORT); return symbol(sym.IMPORT); }
"module"                        { yybegin(MODULE); return symbol(sym.MODULE); }
":"                             { return symbol(sym.COLON); }
","                             { return symbol(sym.COMMA); }
"|"                             { return symbol(sym.BAR); }
"!"                             { return symbol(sym.EXCLAMATION); }
"("                             { return symbol(sym.L_PAREN); }
"="                             { return symbol(sym.EQUALS); }
"{"                             { return symbol(sym.L_BRACKET); }
"}"                             { return symbol(sym.R_BRACKET); }
"["                             { return symbol(sym.L_SQUARE_BRACKET); }
"]"                             { return symbol(sym.R_SQUARE_BRACKET); }
"$"                             { return symbol(sym.DOLLAR); }
"->"                            { return symbol(sym.R_ARROW); }
"<|"                            { return symbol(sym.L_PIPE); }
"|>"                            { return symbol(sym.R_PIPE); }

{INT_VALUE}                     { return symbol(sym.INT_NUM, Integer.parseInt(yytext())); }
{FLOAT_VALUE}                   { return symbol(sym.FLOAT_NUM, Double.parseDouble(yytext())); }
{CHAR_VALUE}                    { return symbol(sym.CHR); }
{STRING_VALUE}                  { return symbol(sym.STR); }
"True"                          { return symbol(sym.BOOLEAN, true); }
"False"                         { return symbol(sym.BOOLEAN, false); }

<YYINITIAL> {
    //{TYPE}                          { return symbol(sym.TYPE); }
    {LineTerminator}                { return symbol(sym.NLINE); }
}

<IMPORT> {
    {DOT_NAME}                      { yybegin(IMPORT_AS); return symbol(sym.NAME); }
    {LineTerminator}                { yybegin(YYINITIAL); }
}

<IMPORT_AS> {
    "as"                            { yybegin(IMPORT_AS_ALIAS); return symbol(sym.AS); }
    {LineTerminator}                { yybegin(YYINITIAL); }
}

<IMPORT_AS_ALIAS> {
    {NAME}                          { yybegin(IMPORT_AS_ALIAS_EXPOSING); return symbol(sym.NAME); }
    {LineTerminator}                {}
}

<IMPORT_AS_ALIAS_EXPOSING> {
    ")"                             { yybegin(YYINITIAL); return symbol(sym.R_PAREN); }
    {LineTerminator}                {}
}

<MODULE> {
    {MODULE_NAME}                   { yybegin(MODULE_EXPOSING); return symbol(sym.MODULE_NAME); }
    {LineTerminator}                { yybegin(YYINITIAL); }
}

<MODULE_EXPOSING> {
    "exposing"                      { yybegin(MODULE_EXPOSING); return symbol(sym.EXPOSING); }
    {LineTerminator}                { yybegin(YYINITIAL); }
}

<MODULE_EXPOSING_MODULES> {
    ")"                             { yybegin(YYINITIAL); return symbol(sym.R_PAREN); }
    {LineTerminator}                { }
}


"exposing"                          { return symbol(sym.EXPOSING); }
"as"                                { return symbol(sym.AS); }
{NAME}                              { return symbol(sym.NAME); }
")"                                 { return symbol(sym.R_PAREN); }
{WhiteSpace}                        {}
[^]                                 {}
