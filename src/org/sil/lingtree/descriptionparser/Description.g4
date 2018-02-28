// --------------------------------------------------------------------------------------------
// Copyright (c) 2017 SIL International
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
description  : SPACES? node EOF
             | EOF {notifyErrorListeners("missingOpeningParen");}
             | {notifyErrorListeners("missingOpeningParen");} content
             | node {notifyErrorListeners("contentAfterCompletedTree");} content
             | node {notifyErrorListeners("contentAfterCompletedTree");} node
             | {notifyErrorListeners("missingOpeningParen");} content node EOF
             | {notifyErrorListeners("missingOpeningParen");} type    node EOF
             | node closeParen {notifyErrorListeners("tooManyCloseParens");}
             ;

// we allow empty nodes that just have parens (hence, both type and content are optional)
node : openParen type? content? node* closeParen
	 | openParen type? content? node* closeParen {notifyErrorListeners("missingOpeningParen");} content
     | openParen type? content? node*             {notifyErrorListeners("missingClosingParen");}
     |           type  content?                   {notifyErrorListeners("missingOpeningParen");}
     ;

openParen : ' '* '(' ' '*
          ;
          
closeParen : ' '* ')' ' '* SPACES
           | ' '* ')'
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
        
content : (TEXT | BACKSLASH | SLASH)+ ' '* subscript superscript
        | (TEXT | BACKSLASH | SLASH)+ ' '* superscript subscript
        | (TEXT | BACKSLASH | SLASH)+ ' '* subscript
        | (TEXT | BACKSLASH | SLASH)+ ' '* superscript
        | (TEXT | BACKSLASH | SLASH)+ 
        | subscript superscript
        | superscript subscript
        | subscript
        | superscript
        ;

subscript : SUBSCRIPT (TEXT | BACKSLASH | SLASH)+
		  | SUBSCRIPTITALIC (TEXT | BACKSLASH | SLASH)+
		  ;

superscript : SUPERSCRIPT (TEXT | BACKSLASH | SLASH)+
		    | SUPERSCRIPTITALIC (TEXT | BACKSLASH | SLASH)+
		    ;

// Try to capture just a sequence of whitespace
SPACES: ' ' [ \t\n\r]+;

OMIT : '\\O'  ' '* ;
TRIANGLE : '\\T'  ' '*;

LEX : '\\L';
GLOSS : '\\G';
EMPTY : '\\E';  // empty element (like a trace or non-overt pronoun)

SUBSCRIPT : '/s' ;
SUBSCRIPTITALIC : '/_' ;
SUPERSCRIPT : '/S' ;
SUPERSCRIPTITALIC : '/^' ;

// Node text content, with exception of backslash or forward slash sequences.
// Those are handled via BACKSLASH and SLASH
// We need to do it this way because the lexer is a greedy parser and will always
// match the longest sequence (so we'll never see \O, \T, \L, \G, /s, or /S).
TEXT : (
	   [ ,.;:^!?@#$%&'"a-zA-Z0-9\u0080-\uFFFF+-]
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

// allow backslash for non-keyword items (\O, \T, \G, \L)
BACKSLASH : '\\' ~[OTGL];

// allow forward slash for non-keyword items )/s and /S)
SLASH : '/' ~[sS];

WS : [\t\r\n]+ -> skip ; // skip tabs, newlines, but leave spaces (for inside of node text)
