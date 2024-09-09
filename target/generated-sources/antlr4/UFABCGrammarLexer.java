// Generated from UFABCGrammar.g4 by ANTLR 4.4

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class UFABCGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, OP=14, OP_AT=15, OPREL=16, ID=17, 
		NUM=18, VIRG=19, PV=20, AP=21, FP=22, DP=23, TEXTO=24, WS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'"
	};
	public static final String[] ruleNames = {
		"T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", 
		"T__3", "T__2", "T__1", "T__0", "OP", "OP_AT", "OPREL", "ID", "NUM", "VIRG", 
		"PV", "AP", "FP", "DP", "TEXTO", "WS"
	};


	    private HashMap<String,Var> symbolTable = new HashMap<String, Var>();
	    private ArrayList<Var> currentDecl = new ArrayList<Var>();
	    private Types currentType;
	    private Types leftType=null, rightType=null;
	    private Program program = new Program();
	    private String strExpr = "";
	    private IfCommand currentIfCommand;
	    
	    private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
	    
	    
	    public void updateType(){
	    	for(Var v: currentDecl){
	    	   v.setType(currentType);
	    	   symbolTable.put(v.getId(), v);
	    	}
	    }
	    public void exibirVar(){
	        for (String id: symbolTable.keySet()){
	        	System.out.println(symbolTable.get(id));
	        }
	    }
	    
	    public Program getProgram(){
	    	return this.program;
	    	}
	    
	    public boolean isDeclared(String id){
	    	return symbolTable.get(id) != null;
	    }


	public UFABCGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "UFABCGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00c3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\5\21\u0097\n\21\3\22\3\22\7\22\u009b\n\22\f\22\16\22\u009e"+
		"\13\22\3\23\6\23\u00a1\n\23\r\23\16\23\u00a2\3\23\3\23\6\23\u00a7\n\23"+
		"\r\23\16\23\u00a8\5\23\u00ab\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\7\31\u00b9\n\31\f\31\16\31\u00bc\13\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\3\2\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\7\2\""+
		"\".\60\62;C\\c|\5\2\13\f\17\17\"\"\u00cb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3"+
		"\65\3\2\2\2\58\3\2\2\2\7?\3\2\2\2\tG\3\2\2\2\13M\3\2\2\2\rV\3\2\2\2\17"+
		"Z\3\2\2\2\21`\3\2\2\2\23h\3\2\2\2\25m\3\2\2\2\27t\3\2\2\2\31z\3\2\2\2"+
		"\33\u0082\3\2\2\2\35\u0087\3\2\2\2\37\u0089\3\2\2\2!\u0096\3\2\2\2#\u0098"+
		"\3\2\2\2%\u00a0\3\2\2\2\'\u00ac\3\2\2\2)\u00ae\3\2\2\2+\u00b0\3\2\2\2"+
		"-\u00b2\3\2\2\2/\u00b4\3\2\2\2\61\u00b6\3\2\2\2\63\u00bf\3\2\2\2\65\66"+
		"\7u\2\2\66\67\7g\2\2\67\4\3\2\2\289\7k\2\29:\7p\2\2:;\7k\2\2;<\7e\2\2"+
		"<=\7k\2\2=>\7q\2\2>\6\3\2\2\2?@\7h\2\2@A\7k\2\2AB\7o\2\2BC\7r\2\2CD\7"+
		"t\2\2DE\7q\2\2EF\7i\2\2F\b\3\2\2\2GH\7u\2\2HI\7g\2\2IJ\7p\2\2JK\7c\2\2"+
		"KL\7q\2\2L\n\3\2\2\2MN\7r\2\2NO\7t\2\2OP\7q\2\2PQ\7i\2\2QR\7t\2\2RS\7"+
		"c\2\2ST\7o\2\2TU\7c\2\2U\f\3\2\2\2VW\7h\2\2WX\7k\2\2XY\7o\2\2Y\16\3\2"+
		"\2\2Z[\7h\2\2[\\\7k\2\2\\]\7o\2\2]^\7u\2\2^_\7g\2\2_\20\3\2\2\2`a\7g\2"+
		"\2ab\7u\2\2bc\7e\2\2cd\7t\2\2de\7g\2\2ef\7x\2\2fg\7c\2\2g\22\3\2\2\2h"+
		"i\7v\2\2ij\7g\2\2jk\7z\2\2kl\7v\2\2l\24\3\2\2\2mn\7p\2\2no\7w\2\2op\7"+
		"o\2\2pq\7d\2\2qr\7g\2\2rs\7t\2\2s\26\3\2\2\2tu\7g\2\2uv\7p\2\2vw\7v\2"+
		"\2wx\7c\2\2xy\7q\2\2y\30\3\2\2\2z{\7f\2\2{|\7g\2\2|}\7e\2\2}~\7n\2\2~"+
		"\177\7c\2\2\177\u0080\7t\2\2\u0080\u0081\7g\2\2\u0081\32\3\2\2\2\u0082"+
		"\u0083\7n\2\2\u0083\u0084\7g\2\2\u0084\u0085\7k\2\2\u0085\u0086\7c\2\2"+
		"\u0086\34\3\2\2\2\u0087\u0088\t\2\2\2\u0088\36\3\2\2\2\u0089\u008a\7<"+
		"\2\2\u008a\u008b\7?\2\2\u008b \3\2\2\2\u008c\u0097\t\3\2\2\u008d\u008e"+
		"\7@\2\2\u008e\u0097\7?\2\2\u008f\u0090\7>\2\2\u0090\u0091\7?\2\2\u0091"+
		"\u0097\7\"\2\2\u0092\u0093\7>\2\2\u0093\u0097\7@\2\2\u0094\u0095\7?\2"+
		"\2\u0095\u0097\7?\2\2\u0096\u008c\3\2\2\2\u0096\u008d\3\2\2\2\u0096\u008f"+
		"\3\2\2\2\u0096\u0092\3\2\2\2\u0096\u0094\3\2\2\2\u0097\"\3\2\2\2\u0098"+
		"\u009c\t\4\2\2\u0099\u009b\t\5\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d$\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009f\u00a1\t\6\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00aa\3\2\2\2\u00a4\u00a6\7\60"+
		"\2\2\u00a5\u00a7\t\6\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a4\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab&\3\2\2\2\u00ac\u00ad\7.\2\2\u00ad(\3\2"+
		"\2\2\u00ae\u00af\7=\2\2\u00af*\3\2\2\2\u00b0\u00b1\7*\2\2\u00b1,\3\2\2"+
		"\2\u00b2\u00b3\7+\2\2\u00b3.\3\2\2\2\u00b4\u00b5\7<\2\2\u00b5\60\3\2\2"+
		"\2\u00b6\u00ba\7$\2\2\u00b7\u00b9\t\7\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc"+
		"\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bd\u00be\7$\2\2\u00be\62\3\2\2\2\u00bf\u00c0\t\b\2\2"+
		"\u00c0\u00c1\3\2\2\2\u00c1\u00c2\b\32\2\2\u00c2\64\3\2\2\2\13\2\u0096"+
		"\u009a\u009c\u00a2\u00a8\u00aa\u00b8\u00ba\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}