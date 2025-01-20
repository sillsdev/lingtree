// --------------------------------------------------------------------------------------------
// Copyright (c) 2017-2024 SIL International
// This software is licensed under the LGPL, version 2.1 or later
// (http://www.gnu.org/licenses/lgpl-2.1.html)
//
// File: Description.g4
// Responsibility: Andy Black
// Last reviewed:
//
// <remarks>
// ANTLR v.4 grammar for LingTree descriptions
// </remarks>
// --------------------------------------------------------------------------------------------
grammar Description;

@header {
	package org.sil.lingtree.descriptionparser.antlr4generated;
}
description  : node EOF
             | EOF {notifyErrorListeners("missingOpeningParen");}
             | {notifyErrorListeners("missingOpeningParen");} content
             | node {notifyErrorListeners("contentAfterCompletedTree");} content
             | node {notifyErrorListeners("contentAfterCompletedTree");} node
             | {notifyErrorListeners("missingOpeningParen");} content node EOF
             | {notifyErrorListeners("missingOpeningParen");} type    node EOF
             | node closeParen {notifyErrorListeners("tooManyCloseParens");}
             ;

// we allow empty nodes that just have parens (hence, both type and content are optional)
node : openParen type? content? customFontInfo? node* closeParen
	 | openParen type? abbreviationWithText+ node* closeParen
     | openParen type? content? customFontInfo? node* closeParen
	 | openParen type? content? customFontInfo? node* closeParen {notifyErrorListeners("missingOpeningParen");} content
     | openParen type? content? customFontInfo? node*             {notifyErrorListeners("missingClosingParen");}
     |           type  content? customFontInfo?                   {notifyErrorListeners("missingOpeningParen");}
     ;

openParen : '('
          ;
          
closeParen : ')'
           ;

type : nodeType lineType
     | lineType nodeType
     | nodeType nodeType          {notifyErrorListeners("tooManyNodeTypes");}
     | lineType lineType          {notifyErrorListeners("tooManyLineTypes");}
     | nodeType lineType nodeType {notifyErrorListeners("tooManyNodeTypes");}
     | lineType nodeType lineType {notifyErrorListeners("tooManyLineTypes");}
     | lineType         
     | nodeType         
     ;

lineType : OMIT
         | TRIANGLE
         ;

nodeType : LEX
         | GLOSS
         | EMPTY
         ;
        
content : (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+ subscript superscript
        | (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+ superscript subscript
        | (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+ subscript
        | (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+ superscript
        | (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+
        | subscript superscript
        | superscript subscript
        | subscript
        | superscript
		;

subscript : SUBSCRIPT       (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+
		  | SUBSCRIPTITALIC (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+
		  | SUBSCRIPT       {notifyErrorListeners("missingContentAfterSubscript");}
		  | SUBSCRIPTITALIC {notifyErrorListeners("missingContentAfterSubscript");}
		  ;

superscript : SUPERSCRIPT       (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+
		    | SUPERSCRIPTITALIC (TEXT | TEXTWITHSPACES | BACKSLASH | SLASH)+
		    | SUPERSCRIPT       {notifyErrorListeners("missingContentAfterSuperscript");}
		    | SUPERSCRIPTITALIC {notifyErrorListeners("missingContentAfterSuperscript");}
		    ;

abbreviation : ABBREVIATIONBEGIN (TEXT | TEXTWITHSPACES)+ ABBREVIATIONEND
             | ABBREVIATIONBEGIN (TEXT | TEXTWITHSPACES)+ {notifyErrorListeners("missingAbbreviationEnd");}
             | ABBREVIATIONBEGIN ABBREVIATIONEND {notifyErrorListeners("missingContentAfterAbbreviationBegin");}
             ;

abbreviationWithText : (TEXT | TEXTWITHSPACES)+ customFontInfo? abbreviation customFontInfo? (TEXT | TEXTWITHSPACES)+ customFontInfo?
                     | abbreviation customFontInfo? (TEXT | TEXTWITHSPACES)+ customFontInfo?
                     | (TEXT | TEXTWITHSPACES)+ customFontInfo? abbreviation customFontInfo?
                     | abbreviation customFontInfo?
                     ;

customFontInfo : CUSTOMFONTBEGIN (TEXT | TEXTWITHSPACES)+ CUSTOMFONTEND
               | CUSTOMFONTBEGIN (TEXT | TEXTWITHSPACES)+ {notifyErrorListeners("missingCustomFontEnd");}
               | CUSTOMFONTBEGIN CUSTOMFONTEND {notifyErrorListeners("missingContentAfterCustomFontBegin");}
               ;

OMIT : '\\O';
TRIANGLE : '\\T';

LEX   : '\\L';
GLOSS : '\\G';
EMPTY : '\\E'; // empty element (like a trace or non-overt pronoun)

SUBSCRIPT : '/s' ;
SUBSCRIPTITALIC : '/_' ;
SUPERSCRIPT : '/S' ;
SUPERSCRIPTITALIC : '/^' ;

ABBREVIATIONBEGIN : '/a';
ABBREVIATIONEND : '/A';

CUSTOMFONTBEGIN : '/f';
CUSTOMFONTEND : '/F';

// Node text content, with exception of backslash or forward slash sequences.
// Those are handled via BACKSLASH and SLASH
// We need to do it this way because the lexer is a greedy parser and will always
// match the longest sequence (so we'll never see \O, \T, \L, \G, /s, or /S).
TEXT : (
	   [,.;:^!?@#$%&'"a-zA-Z0-9\u0080-\uFFFF+-]
     | [_*=<>]
     | '['
     | ']'
     | '{'
     | '}'
     | '\\('
     | '\\)'
//     | '/'
     | '~'
     | '`'
//     | '\\'
     | '|' 
     )+  ;

TEXTWITHSPACES : TEXT ' ' TEXT
               | TEXT ' ' TEXTWITHSPACES
               ;

// allow backslash for non-keyword items (\O, \T, \G, \L)
BACKSLASH : '\\' ~[OTGL];

// allow forward slash for non-keyword items )/s and /S)
SLASH : '/' ~[sS];

WS : [ \t\r\n]+ -> skip ; // skip tabs, newlines, but leave spaces (for inside of node text)
