grammar Drift;

options {
	output=AST;
}

tokens {
	MESSAGE;
	NAME;
	PARENTS;
	ATTRIBUTE;
	MODIFIER;
	TYPE;
	SLOT;
	MIGRATION;
}

@lexer::header {
/*
 * Copyright 2008 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
	package org.vafer.drift.parser;
}

@header {
/*
 * Copyright 2008 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
	package org.vafer.drift.parser;
}


file
	: messageDeclaration+
    	;

messageDeclaration
	: 'message' messageName ( 'extends' parentList)? messageBody
	-> ^(MESSAGE messageName ^(PARENTS parentList?) messageBody )
	;

messageName
	: Identifier
	;

messageBody
	: '{'! attributeDeclaration+ '}'!
    	;

attributeDeclaration
	: modifierSpecifier? typeSpecifier attributeName slot? migrationList? ';'
	-> ^(ATTRIBUTE attributeName ^(MODIFIER modifierSpecifier?) ^(TYPE typeSpecifier) ^(SLOT slot?) migrationList? )
    	;

slot
	: '['! ( slotName | slotNumber ) ']'!
	;

slotName
	: attributeName
	;

slotNumber
	: '#'! Number
	;

migrationList
	: 'migrate'! migration+
	;

migration
	: 'from' typeSpecifier? slot
	-> ^(MIGRATION ^(SLOT slot) ^(TYPE typeSpecifier?) )
	;

attributeName
	: Identifier
	;

parentList
    	: parent (','! parent)*
    	;

parent
    	: messageName
	;

modifierSpecifier
    	: ('optional' | 'required')
    	;

typeSpecifier
	: ('string' | 'double' | 'int8' | 'int16' | 'int32' )
	;

Identifier 
    	: Letter (Letter)*
    	;

Number
	: Digit (Digit)*
	;

fragment
Letter
    	:
	'\u0024' |
        '\u0041'..'\u005a' |
        '\u005f' |
        '\u0061'..'\u007a' |
        '\u00c0'..'\u00d6' |
        '\u00d8'..'\u00f6' |
        '\u00f8'..'\u00ff' |
        '\u0100'..'\u1fff' |
        '\u3040'..'\u318f' |
        '\u3300'..'\u337f' |
        '\u3400'..'\u3d2d' |
        '\u4e00'..'\u9fff' |
        '\uf900'..'\ufaff'
    	;

fragment
Digit
	: '0'..'9'
	;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

