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


import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class DriftLexer extends Lexer {
    public static final int T__12=12;
    public static final int T__23=23;
    public static final int Digit=7;
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

    public DriftLexer() {;} 
    public DriftLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public DriftLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:22:7: ( 'object' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:22:9: 'object'
            {
            match("object"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:23:7: ( 'extends' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:23:9: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:24:7: ( '{' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:24:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:25:7: ( '}' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:25:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:26:7: ( ';' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:26:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:27:7: ( '[' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:27:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:28:7: ( ']' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:28:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:29:7: ( '#' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:29:9: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:30:7: ( 'migrate' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:30:9: 'migrate'
            {
            match("migrate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:31:7: ( 'from' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:31:9: 'from'
            {
            match("from"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:32:7: ( 'and' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:32:9: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:33:7: ( ',' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:33:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:34:7: ( 'optional' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:34:9: 'optional'
            {
            match("optional"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:35:7: ( 'required' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:35:9: 'required'
            {
            match("required"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:189:5: ( Letter ( Letter | Digit )* )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:189:7: Letter ( Letter | Digit )*
            {
            mLetter(); 
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:189:14: ( Letter | Digit )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='$'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')||(LA1_0>='\u00C0' && LA1_0<='\u00D6')||(LA1_0>='\u00D8' && LA1_0<='\u00F6')||(LA1_0>='\u00F8' && LA1_0<='\u1FFF')||(LA1_0>='\u3040' && LA1_0<='\u318F')||(LA1_0>='\u3300' && LA1_0<='\u337F')||(LA1_0>='\u3400' && LA1_0<='\u3D2D')||(LA1_0>='\u4E00' && LA1_0<='\u9FFF')||(LA1_0>='\uF900' && LA1_0<='\uFAFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u3040' && input.LA(1)<='\u318F')||(input.LA(1)>='\u3300' && input.LA(1)<='\u337F')||(input.LA(1)>='\u3400' && input.LA(1)<='\u3D2D')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "Number"
    public final void mNumber() throws RecognitionException {
        try {
            int _type = Number;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:193:5: ( Digit ( Digit )* )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:193:7: Digit ( Digit )*
            {
            mDigit(); 
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:193:13: ( Digit )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:193:14: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Number"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:198:5: ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' .. '\\u007a' | '\\u00c0' .. '\\u00d6' | '\\u00d8' .. '\\u00f6' | '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' | '\\u3040' .. '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' | '\\u4e00' .. '\\u9fff' | '\\uf900' .. '\\ufaff' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u3040' && input.LA(1)<='\u318F')||(input.LA(1)>='\u3300' && input.LA(1)<='\u337F')||(input.LA(1)>='\u3400' && input.LA(1)<='\u3D2D')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:216:5: ( '0' .. '9' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:216:7: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:220:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:220:7: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:220:12: ( options {greedy=false; } : . )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='*') ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1=='/') ) {
                        alt3=2;
                    }
                    else if ( ((LA3_1>='\u0000' && LA3_1<='.')||(LA3_1>='0' && LA3_1<='\uFFFE')) ) {
                        alt3=1;
                    }


                }
                else if ( ((LA3_0>='\u0000' && LA3_0<=')')||(LA3_0>='+' && LA3_0<='\uFFFE')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:220:40: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match("*/"); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:224:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:224:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:224:12: (~ ( '\\n' | '\\r' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\uFFFE')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:224:12: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:224:26: ( '\\r' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\r') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:224:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:227:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:227:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | Identifier | Number | COMMENT | LINE_COMMENT | WS )
        int alt6=19;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:10: T__11
                {
                mT__11(); 

                }
                break;
            case 2 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:16: T__12
                {
                mT__12(); 

                }
                break;
            case 3 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:22: T__13
                {
                mT__13(); 

                }
                break;
            case 4 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:28: T__14
                {
                mT__14(); 

                }
                break;
            case 5 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:34: T__15
                {
                mT__15(); 

                }
                break;
            case 6 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:40: T__16
                {
                mT__16(); 

                }
                break;
            case 7 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:46: T__17
                {
                mT__17(); 

                }
                break;
            case 8 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:52: T__18
                {
                mT__18(); 

                }
                break;
            case 9 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:58: T__19
                {
                mT__19(); 

                }
                break;
            case 10 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:64: T__20
                {
                mT__20(); 

                }
                break;
            case 11 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:70: T__21
                {
                mT__21(); 

                }
                break;
            case 12 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:76: T__22
                {
                mT__22(); 

                }
                break;
            case 13 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:82: T__23
                {
                mT__23(); 

                }
                break;
            case 14 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:88: T__24
                {
                mT__24(); 

                }
                break;
            case 15 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:94: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 16 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:105: Number
                {
                mNumber(); 

                }
                break;
            case 17 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:112: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 18 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:120: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 19 :
                // /Users/tcurdt/Development/drift/src/main/antlr/org/vafer/drift/Drift.g:1:133: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\2\16\6\uffff\3\16\1\uffff\1\16\4\uffff\7\16\2\uffff\5\16"+
        "\1\47\5\16\1\55\1\uffff\5\16\1\uffff\1\16\1\64\4\16\1\uffff\1\16"+
        "\1\72\1\73\1\16\1\75\2\uffff\1\76\2\uffff";
    static final String DFA6_eofS =
        "\77\uffff";
    static final String DFA6_minS =
        "\1\11\1\142\1\170\6\uffff\1\151\1\162\1\156\1\uffff\1\145\2\uffff"+
        "\1\52\1\uffff\1\152\2\164\1\147\1\157\1\144\1\161\2\uffff\1\145"+
        "\1\151\1\145\1\162\1\155\1\44\1\165\1\143\1\157\1\156\1\141\1\44"+
        "\1\uffff\1\151\1\164\1\156\1\144\1\164\1\uffff\1\162\1\44\1\141"+
        "\1\163\2\145\1\uffff\1\154\2\44\1\144\1\44\2\uffff\1\44\2\uffff";
    static final String DFA6_maxS =
        "\1\ufaff\1\160\1\170\6\uffff\1\151\1\162\1\156\1\uffff\1\145\2\uffff"+
        "\1\57\1\uffff\1\152\2\164\1\147\1\157\1\144\1\161\2\uffff\1\145"+
        "\1\151\1\145\1\162\1\155\1\ufaff\1\165\1\143\1\157\1\156\1\141\1"+
        "\ufaff\1\uffff\1\151\1\164\1\156\1\144\1\164\1\uffff\1\162\1\ufaff"+
        "\1\141\1\163\2\145\1\uffff\1\154\2\ufaff\1\144\1\ufaff\2\uffff\1"+
        "\ufaff\2\uffff";
    static final String DFA6_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\1\10\3\uffff\1\14\1\uffff\1\17\1\20"+
        "\1\uffff\1\23\7\uffff\1\21\1\22\14\uffff\1\13\5\uffff\1\12\6\uffff"+
        "\1\1\5\uffff\1\2\1\11\1\uffff\1\15\1\16";
    static final String DFA6_specialS =
        "\77\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\21\1\uffff\2\21\22\uffff\1\21\2\uffff\1\10\1\16\7\uffff\1"+
            "\14\2\uffff\1\20\12\17\1\uffff\1\5\5\uffff\32\16\1\6\1\uffff"+
            "\1\7\1\uffff\1\16\1\uffff\1\13\3\16\1\2\1\12\6\16\1\11\1\16"+
            "\1\1\2\16\1\15\10\16\1\3\1\uffff\1\4\102\uffff\27\16\1\uffff"+
            "\37\16\1\uffff\u1f08\16\u1040\uffff\u0150\16\u0170\uffff\u0080"+
            "\16\u0080\uffff\u092e\16\u10d2\uffff\u5200\16\u5900\uffff\u0200"+
            "\16",
            "\1\22\15\uffff\1\23",
            "\1\24",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\25",
            "\1\26",
            "\1\27",
            "",
            "\1\30",
            "",
            "",
            "\1\31\4\uffff\1\32",
            "",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "",
            "",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "",
            "\1\63",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "",
            "\1\71",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "\1\74",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "",
            "",
            "\1\16\13\uffff\12\16\7\uffff\32\16\4\uffff\1\16\1\uffff\32"+
            "\16\105\uffff\27\16\1\uffff\37\16\1\uffff\u1f08\16\u1040\uffff"+
            "\u0150\16\u0170\uffff\u0080\16\u0080\uffff\u092e\16\u10d2\uffff"+
            "\u5200\16\u5900\uffff\u0200\16",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | Identifier | Number | COMMENT | LINE_COMMENT | WS );";
        }
    }
 

}