grammar Drift;

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

    import java.util.HashMap;
    import java.util.Map;
    import java.lang.Integer;
    import org.vafer.drift.model.MutableObject;
    import org.vafer.drift.model.MutableAttribute;
    import org.vafer.drift.model.MutableSlot;
}

@members {
	public final Map<String, MutableObject> objects = new HashMap<String, MutableObject>();
	private MutableObject object;
	private MutableAttribute attribute;
	private MutableSlot prevSlot;
	private MutableSlot slot;
	
	private String slotName;
	private int slotNumber;
}


file
    : objectDeclaration+
    ;

objectDeclaration
    : 'object' objectName
      {
        object = new MutableObject();
        object.setName($objectName.text);        
        objects.put(object.getName(), object);
      }
      ( 'extends' parentList)? objectBody
    ;

objectName
    : Identifier
    ;

objectBody
    : '{' attributeDeclaration+ '}'
    ;

attributeDeclaration
    : modifierSpecifier? typeSpecifier attributeName slot?
      {
        if ($slot.text == null) {
          slotName = $attributeName.text;
          slotNumber = slotName.hashCode();
        }

        attribute = object.addAttribute(slotNumber);

        if (slotName == null) {
            slotName = $attributeName.text;
        }

        attribute.setSlotName(slotName);
        
        attribute.setName($attributeName.text);
	    attribute.setType($typeSpecifier.text);
      
        if ("required".equals($modifierSpecifier.text)) {
          attribute.setRequired(true);
        }

        slot = attribute;
      }
        migrationList? ';'
      {        
        slot = null;
        prevSlot = null;
      }
    ;

slot
    : '[' ( slotName | slotNumber ) ']'
    ;

slotName
    : attributeName
      {
      	slotName = $attributeName.text;
      	slotNumber = slotName.hashCode();
      }
    ;

slotNumber
    : '#' Number
      {
      	slotName = null;
      	slotNumber = Integer.parseInt($Number.text);
      }
    ;

migrationList
    : 'migrate' ('from' migrationSlotList)+
    ;

migrationSlotList
    : 
    {
	prevSlot = slot;
    }
    migrationSlot ('and' migrationSlot)*
    ;

migrationSlot
    : typeSpecifier? slot
    {

        slot = object.createSlot(slotNumber);

	slot.setSlotName(slotName);

	final String type = $typeSpecifier.text;
	if (type == null) {
	  slot.setType(prevSlot.getType());
	} else {
	  slot.setType(type);
	}

        prevSlot.addParent(slot);
    }
    ;

attributeName
    : Identifier
    ;

parentList
    : parent (',' parent)*
    ;

parent
    : objectName
      {
        object.addParent($objectName.text);
      }
    ;

modifierSpecifier
    : ('optional' | 'required')
    ;

typeSpecifier
    : Identifier
    ;

Identifier 
    : Letter (Letter|Digit)*
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
    : '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

