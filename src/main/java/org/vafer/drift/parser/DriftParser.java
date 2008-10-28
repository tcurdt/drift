// $ANTLR 3.1 /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g 2008-10-28 18:07:49

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

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.vafer.drift.model.MutableAttribute;
import org.vafer.drift.model.MutableObject;
import org.vafer.drift.model.MutableSlot;

public class DriftParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Identifier", "Number", "Letter", "Digit", "COMMENT", "LINE_COMMENT", "WS", "'object'", "'extends'", "'{'", "'}'", "';'", "'['", "']'", "'#'", "'migrate'", "'from'", "'and'", "','", "'optional'", "'required'"
    };
    public static final int T__12=12;
    public static final int Digit=7;
    public static final int T__23=23;
    public static final int Identifier=4;
    public static final int T__20=20;
    public static final int WS=10;
    public static final int T__13=13;
    public static final int T__21=21;
    public static final int COMMENT=8;
    public static final int T__19=19;
    public static final int T__14=14;
    public static final int LINE_COMMENT=9;
    public static final int T__11=11;
    public static final int T__22=22;
    public static final int Letter=6;
    public static final int T__17=17;
    public static final int EOF=-1;
    public static final int T__16=16;
    public static final int T__24=24;
    public static final int Number=5;
    public static final int T__18=18;
    public static final int T__15=15;

    // delegates
    // delegators


        public DriftParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public DriftParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return DriftParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g"; }


    	public final Map<String, MutableObject> objects = new HashMap<String, MutableObject>();
    	private MutableObject object;
    	private MutableAttribute attribute;
    	private MutableSlot prevSlot;
    	private MutableSlot slot;
    	
    	private String slotName;
    	private int slotNumber;



    // $ANTLR start "file"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:60:1: file : ( objectDeclaration )+ ;
    public final void file() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:61:5: ( ( objectDeclaration )+ )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:61:7: ( objectDeclaration )+
            {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:61:7: ( objectDeclaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:61:7: objectDeclaration
            	    {
            	    pushFollow(FOLLOW_objectDeclaration_in_file36);
            	    objectDeclaration();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "file"


    // $ANTLR start "objectDeclaration"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:64:1: objectDeclaration : 'object' objectName ( 'extends' parentList )? objectBody ;
    public final void objectDeclaration() throws RecognitionException {
        DriftParser.objectName_return objectName1 = null;


        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:65:5: ( 'object' objectName ( 'extends' parentList )? objectBody )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:65:7: 'object' objectName ( 'extends' parentList )? objectBody
            {
            match(input,11,FOLLOW_11_in_objectDeclaration54); 
            pushFollow(FOLLOW_objectName_in_objectDeclaration56);
            objectName1=objectName();

            state._fsp--;


                    object = new MutableObject();
                    object.setName((objectName1!=null?input.toString(objectName1.start,objectName1.stop):null));        
                    objects.put(object.getName(), object);
                  
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:71:7: ( 'extends' parentList )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:71:9: 'extends' parentList
                    {
                    match(input,12,FOLLOW_12_in_objectDeclaration74); 
                    pushFollow(FOLLOW_parentList_in_objectDeclaration76);
                    parentList();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_objectBody_in_objectDeclaration80);
            objectBody();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "objectDeclaration"

    public static class objectName_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "objectName"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:74:1: objectName : Identifier ;
    public final DriftParser.objectName_return objectName() throws RecognitionException {
        DriftParser.objectName_return retval = new DriftParser.objectName_return();
        retval.start = input.LT(1);

        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:75:5: ( Identifier )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:75:7: Identifier
            {
            match(input,Identifier,FOLLOW_Identifier_in_objectName97); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "objectName"


    // $ANTLR start "objectBody"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:78:1: objectBody : '{' ( attributeDeclaration )+ '}' ;
    public final void objectBody() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:79:5: ( '{' ( attributeDeclaration )+ '}' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:79:7: '{' ( attributeDeclaration )+ '}'
            {
            match(input,13,FOLLOW_13_in_objectBody114); 
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:79:11: ( attributeDeclaration )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==Identifier||(LA3_0>=23 && LA3_0<=24)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:79:11: attributeDeclaration
            	    {
            	    pushFollow(FOLLOW_attributeDeclaration_in_objectBody116);
            	    attributeDeclaration();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match(input,14,FOLLOW_14_in_objectBody119); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "objectBody"


    // $ANTLR start "attributeDeclaration"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:82:1: attributeDeclaration : ( modifierSpecifier )? typeSpecifier attributeName ( slot )? ( migrationList )? ';' ;
    public final void attributeDeclaration() throws RecognitionException {
        DriftParser.slot_return slot2 = null;

        DriftParser.attributeName_return attributeName3 = null;

        DriftParser.typeSpecifier_return typeSpecifier4 = null;

        DriftParser.modifierSpecifier_return modifierSpecifier5 = null;


        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:83:5: ( ( modifierSpecifier )? typeSpecifier attributeName ( slot )? ( migrationList )? ';' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:83:7: ( modifierSpecifier )? typeSpecifier attributeName ( slot )? ( migrationList )? ';'
            {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:83:7: ( modifierSpecifier )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=23 && LA4_0<=24)) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:83:7: modifierSpecifier
                    {
                    pushFollow(FOLLOW_modifierSpecifier_in_attributeDeclaration136);
                    modifierSpecifier5=modifierSpecifier();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_typeSpecifier_in_attributeDeclaration139);
            typeSpecifier4=typeSpecifier();

            state._fsp--;

            pushFollow(FOLLOW_attributeName_in_attributeDeclaration141);
            attributeName3=attributeName();

            state._fsp--;

            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:83:54: ( slot )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==16) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:83:54: slot
                    {
                    pushFollow(FOLLOW_slot_in_attributeDeclaration143);
                    slot2=slot();

                    state._fsp--;


                    }
                    break;

            }


                    if ((slot2!=null?input.toString(slot2.start,slot2.stop):null) == null) {
                      slotName = (attributeName3!=null?input.toString(attributeName3.start,attributeName3.stop):null);
                      slotNumber = slotName.hashCode();
                    }

                    attribute = object.addAttribute(slotNumber);

                    if (slotName == null) {
                        slotName = (attributeName3!=null?input.toString(attributeName3.start,attributeName3.stop):null);
                    }

                    attribute.setSlotName(slotName);
                    
                    attribute.setName((attributeName3!=null?input.toString(attributeName3.start,attributeName3.stop):null));
            	    attribute.setType((typeSpecifier4!=null?input.toString(typeSpecifier4.start,typeSpecifier4.stop):null));
                  
                    if ("required".equals((modifierSpecifier5!=null?input.toString(modifierSpecifier5.start,modifierSpecifier5.stop):null))) {
                      attribute.setRequired(true);
                    }

                    slot = attribute;
                  
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:107:9: ( migrationList )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==19) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:107:9: migrationList
                    {
                    pushFollow(FOLLOW_migrationList_in_attributeDeclaration162);
                    migrationList();

                    state._fsp--;


                    }
                    break;

            }

            match(input,15,FOLLOW_15_in_attributeDeclaration165); 
                    
                    slot = null;
                    prevSlot = null;
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "attributeDeclaration"

    public static class slot_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "slot"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:114:1: slot : '[' ( slotName | slotNumber ) ']' ;
    public final DriftParser.slot_return slot() throws RecognitionException {
        DriftParser.slot_return retval = new DriftParser.slot_return();
        retval.start = input.LT(1);

        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:115:5: ( '[' ( slotName | slotNumber ) ']' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:115:7: '[' ( slotName | slotNumber ) ']'
            {
            match(input,16,FOLLOW_16_in_slot190); 
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:115:11: ( slotName | slotNumber )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==Identifier) ) {
                alt7=1;
            }
            else if ( (LA7_0==18) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:115:13: slotName
                    {
                    pushFollow(FOLLOW_slotName_in_slot194);
                    slotName();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:115:24: slotNumber
                    {
                    pushFollow(FOLLOW_slotNumber_in_slot198);
                    slotNumber();

                    state._fsp--;


                    }
                    break;

            }

            match(input,17,FOLLOW_17_in_slot202); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "slot"


    // $ANTLR start "slotName"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:118:1: slotName : attributeName ;
    public final void slotName() throws RecognitionException {
        DriftParser.attributeName_return attributeName6 = null;


        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:119:5: ( attributeName )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:119:7: attributeName
            {
            pushFollow(FOLLOW_attributeName_in_slotName219);
            attributeName6=attributeName();

            state._fsp--;


                  	slotName = (attributeName6!=null?input.toString(attributeName6.start,attributeName6.stop):null);
                  	slotNumber = slotName.hashCode();
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "slotName"


    // $ANTLR start "slotNumber"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:126:1: slotNumber : '#' Number ;
    public final void slotNumber() throws RecognitionException {
        Token Number7=null;

        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:127:5: ( '#' Number )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:127:7: '#' Number
            {
            match(input,18,FOLLOW_18_in_slotNumber244); 
            Number7=(Token)match(input,Number,FOLLOW_Number_in_slotNumber246); 

                  	slotName = null;
                  	slotNumber = Integer.parseInt((Number7!=null?Number7.getText():null));
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "slotNumber"


    // $ANTLR start "migrationList"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:134:1: migrationList : 'migrate' ( 'from' migrationSlotList )+ ;
    public final void migrationList() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:135:5: ( 'migrate' ( 'from' migrationSlotList )+ )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:135:7: 'migrate' ( 'from' migrationSlotList )+
            {
            match(input,19,FOLLOW_19_in_migrationList271); 
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:135:17: ( 'from' migrationSlotList )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==20) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:135:18: 'from' migrationSlotList
            	    {
            	    match(input,20,FOLLOW_20_in_migrationList274); 
            	    pushFollow(FOLLOW_migrationSlotList_in_migrationList276);
            	    migrationSlotList();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "migrationList"


    // $ANTLR start "migrationSlotList"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:138:1: migrationSlotList : migrationSlot ( 'and' migrationSlot )* ;
    public final void migrationSlotList() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:139:5: ( migrationSlot ( 'and' migrationSlot )* )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:140:5: migrationSlot ( 'and' migrationSlot )*
            {

            	prevSlot = slot;
                
            pushFollow(FOLLOW_migrationSlot_in_migrationSlotList306);
            migrationSlot();

            state._fsp--;

            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:143:19: ( 'and' migrationSlot )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==21) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:143:20: 'and' migrationSlot
            	    {
            	    match(input,21,FOLLOW_21_in_migrationSlotList309); 
            	    pushFollow(FOLLOW_migrationSlot_in_migrationSlotList311);
            	    migrationSlot();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "migrationSlotList"


    // $ANTLR start "migrationSlot"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:146:1: migrationSlot : ( typeSpecifier )? slot ;
    public final void migrationSlot() throws RecognitionException {
        DriftParser.typeSpecifier_return typeSpecifier8 = null;


        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:147:5: ( ( typeSpecifier )? slot )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:147:7: ( typeSpecifier )? slot
            {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:147:7: ( typeSpecifier )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==Identifier) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:147:7: typeSpecifier
                    {
                    pushFollow(FOLLOW_typeSpecifier_in_migrationSlot330);
                    typeSpecifier8=typeSpecifier();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_slot_in_migrationSlot333);
            slot();

            state._fsp--;



                    slot = object.createSlot(slotNumber);

            	slot.setSlotName(slotName);

            	final String type = (typeSpecifier8!=null?input.toString(typeSpecifier8.start,typeSpecifier8.stop):null);
            	if (type == null) {
            	  slot.setType(prevSlot.getType());
            	} else {
            	  slot.setType(type);
            	}

                    prevSlot.addParent(slot);
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "migrationSlot"

    public static class attributeName_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "attributeName"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:165:1: attributeName : Identifier ;
    public final DriftParser.attributeName_return attributeName() throws RecognitionException {
        DriftParser.attributeName_return retval = new DriftParser.attributeName_return();
        retval.start = input.LT(1);

        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:166:5: ( Identifier )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:166:7: Identifier
            {
            match(input,Identifier,FOLLOW_Identifier_in_attributeName356); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attributeName"


    // $ANTLR start "parentList"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:169:1: parentList : parent ( ',' parent )* ;
    public final void parentList() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:170:5: ( parent ( ',' parent )* )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:170:7: parent ( ',' parent )*
            {
            pushFollow(FOLLOW_parent_in_parentList373);
            parent();

            state._fsp--;

            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:170:14: ( ',' parent )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:170:15: ',' parent
            	    {
            	    match(input,22,FOLLOW_22_in_parentList376); 
            	    pushFollow(FOLLOW_parent_in_parentList378);
            	    parent();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parentList"


    // $ANTLR start "parent"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:173:1: parent : objectName ;
    public final void parent() throws RecognitionException {
        DriftParser.objectName_return objectName9 = null;


        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:174:5: ( objectName )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:174:7: objectName
            {
            pushFollow(FOLLOW_objectName_in_parent397);
            objectName9=objectName();

            state._fsp--;


                    object.addParent((objectName9!=null?input.toString(objectName9.start,objectName9.stop):null));
                  

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parent"

    public static class modifierSpecifier_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "modifierSpecifier"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:180:1: modifierSpecifier : ( 'optional' | 'required' ) ;
    public final DriftParser.modifierSpecifier_return modifierSpecifier() throws RecognitionException {
        DriftParser.modifierSpecifier_return retval = new DriftParser.modifierSpecifier_return();
        retval.start = input.LT(1);

        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:181:5: ( ( 'optional' | 'required' ) )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:181:7: ( 'optional' | 'required' )
            {
            if ( (input.LA(1)>=23 && input.LA(1)<=24) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "modifierSpecifier"

    public static class typeSpecifier_return extends ParserRuleReturnScope {
    };

    // $ANTLR start "typeSpecifier"
    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:184:1: typeSpecifier : Identifier ;
    public final DriftParser.typeSpecifier_return typeSpecifier() throws RecognitionException {
        DriftParser.typeSpecifier_return retval = new DriftParser.typeSpecifier_return();
        retval.start = input.LT(1);

        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:185:5: ( Identifier )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:185:7: Identifier
            {
            match(input,Identifier,FOLLOW_Identifier_in_typeSpecifier445); 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeSpecifier"

    // Delegated rules


 

    public static final BitSet FOLLOW_objectDeclaration_in_file36 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_objectDeclaration54 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_objectName_in_objectDeclaration56 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_12_in_objectDeclaration74 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parentList_in_objectDeclaration76 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_objectBody_in_objectDeclaration80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_objectName97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_objectBody114 = new BitSet(new long[]{0x0000000001800010L});
    public static final BitSet FOLLOW_attributeDeclaration_in_objectBody116 = new BitSet(new long[]{0x0000000001804010L});
    public static final BitSet FOLLOW_14_in_objectBody119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifierSpecifier_in_attributeDeclaration136 = new BitSet(new long[]{0x0000000001804010L});
    public static final BitSet FOLLOW_typeSpecifier_in_attributeDeclaration139 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_attributeName_in_attributeDeclaration141 = new BitSet(new long[]{0x0000000000098000L});
    public static final BitSet FOLLOW_slot_in_attributeDeclaration143 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_migrationList_in_attributeDeclaration162 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_attributeDeclaration165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_slot190 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_slotName_in_slot194 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_slotNumber_in_slot198 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_slot202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributeName_in_slotName219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_slotNumber244 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_Number_in_slotNumber246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_migrationList271 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_migrationList274 = new BitSet(new long[]{0x0000000001814010L});
    public static final BitSet FOLLOW_migrationSlotList_in_migrationList276 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_migrationSlot_in_migrationSlotList306 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_migrationSlotList309 = new BitSet(new long[]{0x0000000001814010L});
    public static final BitSet FOLLOW_migrationSlot_in_migrationSlotList311 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_typeSpecifier_in_migrationSlot330 = new BitSet(new long[]{0x0000000001814010L});
    public static final BitSet FOLLOW_slot_in_migrationSlot333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_attributeName356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parent_in_parentList373 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_parentList376 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parent_in_parentList378 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_objectName_in_parent397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_modifierSpecifier422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_typeSpecifier445 = new BitSet(new long[]{0x0000000000000002L});

}