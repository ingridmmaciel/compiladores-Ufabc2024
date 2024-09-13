// Generated from IsiLanguage.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class IsiLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, SOMA=19, SUB=20, DIV=21, MULT=22, OP_AT=23, OPREL=24, ID=25, 
		NUMERO=26, NUMERO_REAL=27, VIRG=28, PV=29, PO=30, AP=31, FP=32, AC=33, 
		FC=34, DP=35, TEXTO=36, STRING=37, WS=38;
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_tipo = 4, RULE_comando = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdAttrib = 8, RULE_cmdSe = 9, RULE_cmdEnquanto = 10, RULE_cmdFacaEnquanto = 11, 
		RULE_cmdPara = 12, RULE_expr = 13, RULE_termo = 14, RULE_expr_ad = 15, 
		RULE_termo_ad = 16, RULE_fator = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declara", "bloco", "declaravar", "tipo", "comando", "cmdLeitura", 
			"cmdEscrita", "cmdAttrib", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
			"cmdPara", "expr", "termo", "expr_ad", "termo_ad", "fator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'inteiro'", "'real'", 
			"'texto'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", 
			"'faca'", "'fimenquanto'", "'para'", "'++'", "'--'", "'fimpara'", "'+'", 
			"'-'", "'/'", "'*'", "':='", null, null, null, null, "','", "';'", "'.'", 
			"'('", "')'", "'{'", "'}'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "SOMA", "SUB", "DIV", "MULT", 
			"OP_AT", "OPREL", "ID", "NUMERO", "NUMERO_REAL", "VIRG", "PV", "PO", 
			"AP", "FP", "AC", "FC", "DP", "TEXTO", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private SymbolTable symbolTable = new SymbolTable();
	    private ArrayList<Var> currentDecl = new ArrayList<Var>();
	    private ArrayList<String> exTypeList = new ArrayList<String>();
	    private int currentType;
	    private Program program = new Program();
	    private String leftType = "";
	    private String rightType = "";
	    private String strExpr = "";
	    private String contExpr = "";
	    private String operacao;
	    private	String op_atual;
		private	String op_nova;
	    private IfCommand currentIfCommand;
	    private Stack<ArrayList<Command>> stack = new Stack<>();
	    private Stack<String> exprDecision = new Stack<String>();
	    private ArrayList<Command> listaVazia;
	    private ArrayList<Command> listT;
	    private ArrayList<Command> listF;
	    private ArrayList<Command> comList;
	    
	    
	    public void exibirVar() {
	    for (Symbol sym : symbolTable.getAll()) { 
	        System.out.println(sym);
	    	}
		}
		
		public String getTypeById(String id) {
			return symbolTable.getTypeById(id);
		}
		
	    public Program getProgram(){
	    	return this.program;
	    	}
	    
	    public boolean isDeclared(String id){
	    	return symbolTable.get(id) != null;
	    }
	    
	    public void checkUnused(String id) {
			Symbol sym = (Symbol) symbolTable.get(id);
			if ((sym.isInitialized() && !sym.isUsed()) || !(sym.isInitialized() && sym.isUsed())) {
		       	System.out.println("Warning - Variable " + sym.getId() + " was declared but not used."); 
			}	
		}
		
		public void checkInitialized(String id) {
	        if(!symbolTable.exists(id))
	            throw new IsiLanguageSemanticException("Símbolo "+id+" não inicializado.");
	    }
	    
	    public void setHasValue(String id) {
	        symbolTable.setHasValue(id);
	    }
	    
	    public void verificaAtribuicao(String id) {
			symbolTable.verificaAtribuicao(id);
		}
		
	    public void exprReset() {
			exTypeList = new ArrayList<String>();
		}
		
		public void typeAttrib(String leftType, String id, String expression) { 
			for (String type : exTypeList) {
				if (leftType != type) {
					throw new IsiLanguageSemanticException("Tipos incompatíveis entre " + leftType + " e " + type + "\n\t na sentenca " + id+" := " + expression);
				}
			}
		}
		
		public String getTypeIfValid(ArrayList<String> listTypes, String lado, String expressao) {
	    	if (listTypes.isEmpty()) {
	        	throw new IsiLanguageSemanticException("A lista de tipos está vazia para a expressão " + expressao);
	    	}
	    	String tipoBase = listTypes.get(0);

	    	// Verifica se todos os tipos são iguais ao tipo base
	    	for (String tipo : listTypes) {
	        	if (!tipo.equals(tipoBase)) {
	            	throw new IsiLanguageSemanticException("Elementos do lado " + lado + " possuem tipos incompatíveis.");
	        	}
	    	}
	    	// Se todos os tipos são iguais, retorna o tipo base
	   		return tipoBase;
		}
		
		public void stringType(String id) {
			symbolTable.stringType(id);
		}
	    
	    public void generateCode(){
			program.generateTarget();
		}

	public IsiLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public DeclaraContext declara() {
			return getRuleContext(DeclaraContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(T__0);
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				{
				setState(37);
				declara();
				setState(38);
				bloco();
				}
				}
				break;
			case T__6:
			case T__7:
			case T__8:
			case T__11:
			case T__12:
			case T__14:
			case ID:
				{
				setState(40);
				bloco();
				}
				break;
			case T__1:
				break;
			default:
				break;
			}
			setState(43);
			match(T__1);

			                program.setsymbolTable(symbolTable);
			                program.setCommandList(stack.pop());
			           	  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaraContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclaraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterDeclara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitDeclara(this);
		}
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46);
				declaravar();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

						  stack.push(new ArrayList<Command>());
						  
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				comando();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33600384L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public List<TerminalNode> VIRG() { return getTokens(IsiLanguageParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(IsiLanguageParser.VIRG, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__2);
			setState(58);
			tipo();
			setState(59);
			match(ID);

			      			String id_var = _input.LT(-1).getText();
			          		Symbol sym = new Var(id_var, null, currentType);
			          		if (!symbolTable.exists(id_var)){
				            	symbolTable.add(sym);	
				            }
				            else{
				                throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
				            } 
			      		
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(61);
				match(VIRG);
				setState(62);
				match(ID);
				 
				      			String id_vari = _input.LT(-1).getText();
				          		Symbol symb = new Var(id_vari, null, currentType);
				          		if (!symbolTable.exists(id_vari)){
					            	symbolTable.add(symb);	
					            }
					            else{
					               throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
					            }
				      		
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
			match(PO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tipo);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(T__3);
				 currentType = Var.NUMBER;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(T__4);
				 currentType = Var.REALNUMBER;
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				match(T__5);
				 currentType = Var.TEXT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdSeContext cmdSe() {
			return getRuleContext(CmdSeContext.class,0);
		}
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
		}
		public CmdFacaEnquantoContext cmdFacaEnquanto() {
			return getRuleContext(CmdFacaEnquantoContext.class,0);
		}
		public CmdParaContext cmdPara() {
			return getRuleContext(CmdParaContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comando);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				cmdAttrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				cmdLeitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				cmdEscrita();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(82);
				cmdSe();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(83);
				cmdEnquanto();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 6);
				{
				setState(84);
				cmdFacaEnquanto();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 7);
				{
				setState(85);
				cmdPara();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__6);
			setState(89);
			match(AP);
			setState(90);
			match(ID);

								checkInitialized(_input.LT(-1).getText());
			    				String ident = _input.LT(-1).getText();
			    			
			setState(92);
			match(FP);
			setState(93);
			match(PO);

			    				Var var = (Var)symbolTable.get(ident);
			              		Command cmdLeitura = new ReadCommand(ident, var);
			              		stack.peek().add(cmdLeitura);
								setHasValue(ident);
						 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__7);
			setState(97);
			match(AP);
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(98);
				match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(100);
				termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText, false); 
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(105);
			match(FP);
			setState(106);
			match(PO);
			 
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public TerminalNode SOMA() { return getToken(IsiLanguageParser.SOMA, 0); }
		public TerminalNode SUB() { return getToken(IsiLanguageParser.SUB, 0); }
		public TerminalNode MULT() { return getToken(IsiLanguageParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(IsiLanguageParser.DIV, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode STRING() { return getToken(IsiLanguageParser.STRING, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdAttrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(ID);
			 
			                   		String id = _input.LT(-1).getText();
			                   		checkInitialized(id);
			                   		leftType = getTypeById(id);
			                   		String id_dois = id;
			                   		exprReset();
			                 	
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7864320L) != 0)) {
				{
				setState(111);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7864320L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}


			                 		String op = _input.LT(-1).getText();
			                 		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
			                 			contExpr = id_dois + op;
			                 	}
			setState(115);
			match(OP_AT);
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMERO:
			case NUMERO_REAL:
			case AP:
			case TEXTO:
				{
				{
				setState(116);
				expr();
				setState(117);
				match(PO);

				                 			AttribCommand cmdAttrib = new AttribCommand(id_dois, contExpr);
											typeAttrib(leftType, id_dois, contExpr);
											setHasValue(id_dois);
											stack.peek().add(cmdAttrib);
									
				}
				}
				break;
			case STRING:
				{
				{
				setState(120);
				match(STRING);
					
										String str = _input.LT(-1).getText();
										stringType(id_dois);
										AttribCommand cmdAttrib = new AttribCommand(id_dois, str);
										stack.peek().add(cmdAttrib);
								
				setState(122);
				match(PO);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdSeContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<TerminalNode> AC() { return getTokens(IsiLanguageParser.AC); }
		public TerminalNode AC(int i) {
			return getToken(IsiLanguageParser.AC, i);
		}
		public List<TerminalNode> FC() { return getTokens(IsiLanguageParser.FC); }
		public TerminalNode FC(int i) {
			return getToken(IsiLanguageParser.FC, i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdSeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdSe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdSe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdSe(this);
		}
	}

	public final CmdSeContext cmdSe() throws RecognitionException {
		CmdSeContext _localctx = new CmdSeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__8);
			setState(126);
			match(AP);

									exprReset();
								
			setState(128);
			expr();

									exprDecision.push(contExpr);
									leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
								
			setState(130);
			match(OPREL);
			 
									operacao = _input.LT(-1).getText();
									op_atual = exprDecision.pop();
									op_nova = op_atual + operacao;
									exprDecision.push(op_nova);
									exprReset();
								
			setState(132);
			expr();

									op_atual = exprDecision.pop();
									op_nova = op_atual + contExpr;
									exprDecision.push(op_nova);
									rightType = getTypeIfValid(exTypeList, "direito", op_nova);
								
			setState(134);
			match(FP);

									if (rightType != leftType) { 
										throw new IsiLanguageSemanticException("Não é possível compará-los");
									}
								
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(136);
				match(T__9);
				setState(137);
				match(AC);
				 
				            			comList = new ArrayList<Command>(); 
				            			stack.push(comList);
				        			
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(139);
					comando();
					}
					}
					setState(142); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33600384L) != 0) );
				setState(144);
				match(FC);

				            				listT = stack.pop();
				            				String expreDecision = exprDecision.pop();
				            				listaVazia = new ArrayList<Command>();
				            				IfCommand ifCommand = new IfCommand(expreDecision, listT, listaVazia);
				            				stack.peek().add(ifCommand);
				        			
				setState(146);
				match(AC);
				 
										comList = new ArrayList<Command>(); 
				                      	stack.push(comList);
				                    
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(148);
					comando();
					}
					}
					setState(151); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33600384L) != 0) );
				setState(153);
				match(FC);

				                       listT = stack.pop();	
									   String expDeci = exprDecision.pop();
									   listaVazia = new ArrayList<Command>();
									   IfCommand cmdEntao = new IfCommand(expreDecision, listT, listaVazia);
				                   	   stack.peek().add(cmdEntao);
				                    
				}
			}

			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(158);
				match(T__10);
				setState(159);
				match(AC);

				                   	 	comList = new ArrayList<Command>();
				                   	 	stack.push(comList);
				                   	 
				{
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(161);
					comando();
					}
					}
					setState(164); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33600384L) != 0) );
				}
				setState(166);
				match(FC);

				                   		listF = stack.pop();
										int index = stack.peek().size() - 1; 
										stack.peek().remove(index); 
				                   		IfCommand cmdSeNao = new IfCommand(expreDecision, listT, listF);
				                   		stack.peek().add(cmdSeNao);
				                     
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEnquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdEnquanto(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__11);
			 
			                  stack.push(new ArrayList<Command>());
			                  strExpr = ""; 
			               	
			setState(173);
			match(AP);
			setState(174);
			expr();
			setState(175);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(177);
			expr();
			setState(178);
			match(FP);
			setState(179);
			match(T__12);
			setState(181); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(180);
				comando();
				}
				}
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33600384L) != 0) );
			setState(185);
			match(T__13);
			 
			                  WhileCommand WhileCommand = new WhileCommand(strExpr, stack.pop()); 
			                  stack.peek().add(WhileCommand);
			               	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdFacaEnquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdFacaEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdFacaEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdFacaEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdFacaEnquanto(this);
		}
	}

	public final CmdFacaEnquantoContext cmdFacaEnquanto() throws RecognitionException {
		CmdFacaEnquantoContext _localctx = new CmdFacaEnquantoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdFacaEnquanto);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__12);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(191); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(190);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(193); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(195);
			match(T__11);
			setState(196);
			match(AP);
			setState(197);
			expr();
			setState(198);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(200);
			expr();
			setState(201);
			match(FP);
			setState(202);
			match(PO);
			 
			                     	DoWhileCommand DoWhileCommand = new DoWhileCommand(strExpr, stack.pop()); 
			                     	stack.peek().add(DoWhileCommand); 
			                  	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdParaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> PO() { return getTokens(IsiLanguageParser.PO); }
		public TerminalNode PO(int i) {
			return getToken(IsiLanguageParser.PO, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdPara(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__14);
			setState(206);
			match(AP);
			setState(207);
			match(ID);
			setState(208);
			match(OP_AT);
			setState(209);
			expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(211);
			match(PO);
			setState(212);
			expr();
			setState(213);
			match(OPREL);
			setState(214);
			expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(216);
			match(PO);
			setState(217);
			match(ID);
			setState(218);
			_la = _input.LA(1);
			if ( !(_la==T__15 || _la==T__16) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(220);
			match(FP);
			setState(221);
			match(T__12);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(224); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(223);
				comando();
				}
				}
				setState(226); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33600384L) != 0) );
			setState(228);
			match(T__17);

			               ForCommand ForCommand = new ForCommand(initialization, condition, increment, stack.pop()); 
			               stack.peek().add(ForCommand);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_adContext expr_ad() {
			return getRuleContext(Expr_adContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			termo();
			setState(232);
			expr_ad();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_adContext termo_ad() {
			return getRuleContext(Termo_adContext.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			fator();
			setState(235);
			termo_ad();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_adContext extends ParserRuleContext {
		public TerminalNode SUB() { return getToken(IsiLanguageParser.SUB, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_adContext expr_ad() {
			return getRuleContext(Expr_adContext.class,0);
		}
		public TerminalNode SOMA() { return getToken(IsiLanguageParser.SOMA, 0); }
		public Expr_adContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_ad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExpr_ad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExpr_ad(this);
		}
	}

	public final Expr_adContext expr_ad() throws RecognitionException {
		Expr_adContext _localctx = new Expr_adContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB:
				{
				setState(237);
				match(SUB);
				 
								contExpr += '-';
								String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para subtração.");
				        		}
				        		exTypeList.add(tipo);
				        	
				setState(239);
				termo();
				setState(240);
				expr_ad();
				}
				break;
			case SOMA:
				{
				setState(242);
				match(SOMA);
				 
				           		contExpr += '+'; String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para subtração.");
				        		}
				        		exTypeList.add(tipo);
				        	
				setState(244);
				termo();
				setState(245);
				expr_ad();
				}
				break;
			case OPREL:
			case PO:
			case FP:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Termo_adContext extends ParserRuleContext {
		public TerminalNode DIV() { return getToken(IsiLanguageParser.DIV, 0); }
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_adContext termo_ad() {
			return getRuleContext(Termo_adContext.class,0);
		}
		public TerminalNode MULT() { return getToken(IsiLanguageParser.MULT, 0); }
		public Termo_adContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo_ad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTermo_ad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTermo_ad(this);
		}
	}

	public final Termo_adContext termo_ad() throws RecognitionException {
		Termo_adContext _localctx = new Termo_adContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_termo_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DIV:
				{
				setState(249);
				match(DIV);
				 
				        		contExpr += '/'; 
				        		String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para divisão.");
				        		}
				        		exTypeList.add(tipo);
				    		
				setState(251);
				fator();
				setState(252);
				termo_ad();
				}
				break;
			case MULT:
				{
				setState(254);
				match(MULT);
				 
				        		contExpr += '*'; 
				        		String tipo = getTypeById(_input.LT(-1).getText()); 
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) {  
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para multiplicação.");
				        		}
				        		exTypeList.add(tipo); 
				    		
				setState(256);
				fator();
				setState(257);
				termo_ad();
				}
				break;
			case SOMA:
			case SUB:
			case OPREL:
			case PO:
			case FP:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode NUMERO() { return getToken(IsiLanguageParser.NUMERO, 0); }
		public TerminalNode NUMERO_REAL() { return getToken(IsiLanguageParser.NUMERO_REAL, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fator);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				match(ID);
				 String id = _input.LT(-1).getText();
								 checkInitialized(id);
								 verificaAtribuicao(id);
								 String type = getTypeById(id);
								 contExpr += id; 
								 exTypeList.add(type);
							
				}
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(NUMERO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("NUMBER");
							
				}
				break;
			case NUMERO_REAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(265);
				match(NUMERO_REAL);
				contExpr += _input.LT(-1).getText();
									exTypeList.add("REALNUMBER");
							
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(267);
				match(TEXTO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("TEXT");
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(269);
				match(AP);
				 contExpr += _input.LT(-1).getText();
							
				setState(271);
				expr();
				setState(272);
				match(FP);
				 contExpr += _input.LT(-1).getText();
							
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u0116\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000*\b\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u00010\b\u0001\u000b\u0001"+
		"\f\u00011\u0001\u0002\u0001\u0002\u0004\u00026\b\u0002\u000b\u0002\f\u0002"+
		"7\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003A\b\u0003\n\u0003\f\u0003D\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004N\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005W\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007h\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\bq"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b|\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004"+
		"\t\u008d\b\t\u000b\t\f\t\u008e\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0004\t\u0096\b\t\u000b\t\f\t\u0097\u0001\t\u0001\t\u0001\t\u0003\t\u009d"+
		"\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004\t\u00a3\b\t\u000b\t\f\t\u00a4"+
		"\u0001\t\u0001\t\u0001\t\u0003\t\u00aa\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0004\n\u00b6\b\n\u000b"+
		"\n\f\n\u00b7\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0004\u000b\u00c0\b\u000b\u000b\u000b\f\u000b\u00c1\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0004\f\u00e1\b\f\u000b\f\f"+
		"\f\u00e2\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00f8\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u0104\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0114\b\u0011\u0001"+
		"\u0011\u0000\u0000\u0012\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"\u0000\u0002\u0001\u0000\u0013"+
		"\u0016\u0001\u0000\u0010\u0011\u0123\u0000$\u0001\u0000\u0000\u0000\u0002"+
		"/\u0001\u0000\u0000\u0000\u00043\u0001\u0000\u0000\u0000\u00069\u0001"+
		"\u0000\u0000\u0000\bM\u0001\u0000\u0000\u0000\nV\u0001\u0000\u0000\u0000"+
		"\fX\u0001\u0000\u0000\u0000\u000e`\u0001\u0000\u0000\u0000\u0010m\u0001"+
		"\u0000\u0000\u0000\u0012}\u0001\u0000\u0000\u0000\u0014\u00ab\u0001\u0000"+
		"\u0000\u0000\u0016\u00bc\u0001\u0000\u0000\u0000\u0018\u00cd\u0001\u0000"+
		"\u0000\u0000\u001a\u00e7\u0001\u0000\u0000\u0000\u001c\u00ea\u0001\u0000"+
		"\u0000\u0000\u001e\u00f7\u0001\u0000\u0000\u0000 \u0103\u0001\u0000\u0000"+
		"\u0000\"\u0113\u0001\u0000\u0000\u0000$)\u0005\u0001\u0000\u0000%&\u0003"+
		"\u0002\u0001\u0000&\'\u0003\u0004\u0002\u0000\'*\u0001\u0000\u0000\u0000"+
		"(*\u0003\u0004\u0002\u0000)%\u0001\u0000\u0000\u0000)(\u0001\u0000\u0000"+
		"\u0000)*\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0005\u0002"+
		"\u0000\u0000,-\u0006\u0000\uffff\uffff\u0000-\u0001\u0001\u0000\u0000"+
		"\u0000.0\u0003\u0006\u0003\u0000/.\u0001\u0000\u0000\u000001\u0001\u0000"+
		"\u0000\u00001/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u00002\u0003"+
		"\u0001\u0000\u0000\u000035\u0006\u0002\uffff\uffff\u000046\u0003\n\u0005"+
		"\u000054\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000075\u0001\u0000"+
		"\u0000\u000078\u0001\u0000\u0000\u00008\u0005\u0001\u0000\u0000\u0000"+
		"9:\u0005\u0003\u0000\u0000:;\u0003\b\u0004\u0000;<\u0005\u0019\u0000\u0000"+
		"<B\u0006\u0003\uffff\uffff\u0000=>\u0005\u001c\u0000\u0000>?\u0005\u0019"+
		"\u0000\u0000?A\u0006\u0003\uffff\uffff\u0000@=\u0001\u0000\u0000\u0000"+
		"AD\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000"+
		"\u0000CE\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000EF\u0005\u001e"+
		"\u0000\u0000F\u0007\u0001\u0000\u0000\u0000GH\u0005\u0004\u0000\u0000"+
		"HN\u0006\u0004\uffff\uffff\u0000IJ\u0005\u0005\u0000\u0000JN\u0006\u0004"+
		"\uffff\uffff\u0000KL\u0005\u0006\u0000\u0000LN\u0006\u0004\uffff\uffff"+
		"\u0000MG\u0001\u0000\u0000\u0000MI\u0001\u0000\u0000\u0000MK\u0001\u0000"+
		"\u0000\u0000N\t\u0001\u0000\u0000\u0000OW\u0003\u0010\b\u0000PW\u0003"+
		"\f\u0006\u0000QW\u0003\u000e\u0007\u0000RW\u0003\u0012\t\u0000SW\u0003"+
		"\u0014\n\u0000TW\u0003\u0016\u000b\u0000UW\u0003\u0018\f\u0000VO\u0001"+
		"\u0000\u0000\u0000VP\u0001\u0000\u0000\u0000VQ\u0001\u0000\u0000\u0000"+
		"VR\u0001\u0000\u0000\u0000VS\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000"+
		"\u0000VU\u0001\u0000\u0000\u0000W\u000b\u0001\u0000\u0000\u0000XY\u0005"+
		"\u0007\u0000\u0000YZ\u0005\u001f\u0000\u0000Z[\u0005\u0019\u0000\u0000"+
		"[\\\u0006\u0006\uffff\uffff\u0000\\]\u0005 \u0000\u0000]^\u0005\u001e"+
		"\u0000\u0000^_\u0006\u0006\uffff\uffff\u0000_\r\u0001\u0000\u0000\u0000"+
		"`a\u0005\b\u0000\u0000ag\u0005\u001f\u0000\u0000bc\u0005$\u0000\u0000"+
		"ch\u0006\u0007\uffff\uffff\u0000de\u0003\u001c\u000e\u0000ef\u0006\u0007"+
		"\uffff\uffff\u0000fh\u0001\u0000\u0000\u0000gb\u0001\u0000\u0000\u0000"+
		"gd\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0005 \u0000\u0000"+
		"jk\u0005\u001e\u0000\u0000kl\u0006\u0007\uffff\uffff\u0000l\u000f\u0001"+
		"\u0000\u0000\u0000mn\u0005\u0019\u0000\u0000np\u0006\b\uffff\uffff\u0000"+
		"oq\u0007\u0000\u0000\u0000po\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000"+
		"\u0000qr\u0001\u0000\u0000\u0000rs\u0006\b\uffff\uffff\u0000s{\u0005\u0017"+
		"\u0000\u0000tu\u0003\u001a\r\u0000uv\u0005\u001e\u0000\u0000vw\u0006\b"+
		"\uffff\uffff\u0000w|\u0001\u0000\u0000\u0000xy\u0005%\u0000\u0000yz\u0006"+
		"\b\uffff\uffff\u0000z|\u0005\u001e\u0000\u0000{t\u0001\u0000\u0000\u0000"+
		"{x\u0001\u0000\u0000\u0000|\u0011\u0001\u0000\u0000\u0000}~\u0005\t\u0000"+
		"\u0000~\u007f\u0005\u001f\u0000\u0000\u007f\u0080\u0006\t\uffff\uffff"+
		"\u0000\u0080\u0081\u0003\u001a\r\u0000\u0081\u0082\u0006\t\uffff\uffff"+
		"\u0000\u0082\u0083\u0005\u0018\u0000\u0000\u0083\u0084\u0006\t\uffff\uffff"+
		"\u0000\u0084\u0085\u0003\u001a\r\u0000\u0085\u0086\u0006\t\uffff\uffff"+
		"\u0000\u0086\u0087\u0005 \u0000\u0000\u0087\u009c\u0006\t\uffff\uffff"+
		"\u0000\u0088\u0089\u0005\n\u0000\u0000\u0089\u008a\u0005!\u0000\u0000"+
		"\u008a\u008c\u0006\t\uffff\uffff\u0000\u008b\u008d\u0003\n\u0005\u0000"+
		"\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000"+
		"\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000"+
		"\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0005\"\u0000\u0000\u0091"+
		"\u0092\u0006\t\uffff\uffff\u0000\u0092\u0093\u0005!\u0000\u0000\u0093"+
		"\u0095\u0006\t\uffff\uffff\u0000\u0094\u0096\u0003\n\u0005\u0000\u0095"+
		"\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097"+
		"\u0095\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0001\u0000\u0000\u0000\u0099\u009a\u0005\"\u0000\u0000\u009a\u009b"+
		"\u0006\t\uffff\uffff\u0000\u009b\u009d\u0001\u0000\u0000\u0000\u009c\u0088"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u00a9"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u000b\u0000\u0000\u009f\u00a0"+
		"\u0005!\u0000\u0000\u00a0\u00a2\u0006\t\uffff\uffff\u0000\u00a1\u00a3"+
		"\u0003\n\u0005\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005"+
		"\"\u0000\u0000\u00a7\u00a8\u0006\t\uffff\uffff\u0000\u00a8\u00aa\u0001"+
		"\u0000\u0000\u0000\u00a9\u009e\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001"+
		"\u0000\u0000\u0000\u00aa\u0013\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005"+
		"\f\u0000\u0000\u00ac\u00ad\u0006\n\uffff\uffff\u0000\u00ad\u00ae\u0005"+
		"\u001f\u0000\u0000\u00ae\u00af\u0003\u001a\r\u0000\u00af\u00b0\u0005\u0018"+
		"\u0000\u0000\u00b0\u00b1\u0006\n\uffff\uffff\u0000\u00b1\u00b2\u0003\u001a"+
		"\r\u0000\u00b2\u00b3\u0005 \u0000\u0000\u00b3\u00b5\u0005\r\u0000\u0000"+
		"\u00b4\u00b6\u0003\n\u0005\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0005\u000e\u0000\u0000\u00ba\u00bb\u0006\n\uffff\uffff\u0000\u00bb"+
		"\u0015\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\r\u0000\u0000\u00bd\u00bf"+
		"\u0006\u000b\uffff\uffff\u0000\u00be\u00c0\u0003\n\u0005\u0000\u00bf\u00be"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00bf"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005\f\u0000\u0000\u00c4\u00c5\u0005"+
		"\u001f\u0000\u0000\u00c5\u00c6\u0003\u001a\r\u0000\u00c6\u00c7\u0005\u0018"+
		"\u0000\u0000\u00c7\u00c8\u0006\u000b\uffff\uffff\u0000\u00c8\u00c9\u0003"+
		"\u001a\r\u0000\u00c9\u00ca\u0005 \u0000\u0000\u00ca\u00cb\u0005\u001e"+
		"\u0000\u0000\u00cb\u00cc\u0006\u000b\uffff\uffff\u0000\u00cc\u0017\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0005\u000f\u0000\u0000\u00ce\u00cf\u0005"+
		"\u001f\u0000\u0000\u00cf\u00d0\u0005\u0019\u0000\u0000\u00d0\u00d1\u0005"+
		"\u0017\u0000\u0000\u00d1\u00d2\u0003\u001a\r\u0000\u00d2\u00d3\u0006\f"+
		"\uffff\uffff\u0000\u00d3\u00d4\u0005\u001e\u0000\u0000\u00d4\u00d5\u0003"+
		"\u001a\r\u0000\u00d5\u00d6\u0005\u0018\u0000\u0000\u00d6\u00d7\u0003\u001a"+
		"\r\u0000\u00d7\u00d8\u0006\f\uffff\uffff\u0000\u00d8\u00d9\u0005\u001e"+
		"\u0000\u0000\u00d9\u00da\u0005\u0019\u0000\u0000\u00da\u00db\u0007\u0001"+
		"\u0000\u0000\u00db\u00dc\u0006\f\uffff\uffff\u0000\u00dc\u00dd\u0005 "+
		"\u0000\u0000\u00dd\u00de\u0005\r\u0000\u0000\u00de\u00e0\u0006\f\uffff"+
		"\uffff\u0000\u00df\u00e1\u0003\n\u0005\u0000\u00e0\u00df\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e4\u00e5\u0005\u0012\u0000\u0000\u00e5\u00e6\u0006\f\uffff\uffff"+
		"\u0000\u00e6\u0019\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003\u001c\u000e"+
		"\u0000\u00e8\u00e9\u0003\u001e\u000f\u0000\u00e9\u001b\u0001\u0000\u0000"+
		"\u0000\u00ea\u00eb\u0003\"\u0011\u0000\u00eb\u00ec\u0003 \u0010\u0000"+
		"\u00ec\u001d\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u0014\u0000\u0000"+
		"\u00ee\u00ef\u0006\u000f\uffff\uffff\u0000\u00ef\u00f0\u0003\u001c\u000e"+
		"\u0000\u00f0\u00f1\u0003\u001e\u000f\u0000\u00f1\u00f8\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0005\u0013\u0000\u0000\u00f3\u00f4\u0006\u000f\uffff"+
		"\uffff\u0000\u00f4\u00f5\u0003\u001c\u000e\u0000\u00f5\u00f6\u0003\u001e"+
		"\u000f\u0000\u00f6\u00f8\u0001\u0000\u0000\u0000\u00f7\u00ed\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f2\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000"+
		"\u0000\u0000\u00f8\u001f\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0015"+
		"\u0000\u0000\u00fa\u00fb\u0006\u0010\uffff\uffff\u0000\u00fb\u00fc\u0003"+
		"\"\u0011\u0000\u00fc\u00fd\u0003 \u0010\u0000\u00fd\u0104\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0005\u0016\u0000\u0000\u00ff\u0100\u0006\u0010"+
		"\uffff\uffff\u0000\u0100\u0101\u0003\"\u0011\u0000\u0101\u0102\u0003 "+
		"\u0010\u0000\u0102\u0104\u0001\u0000\u0000\u0000\u0103\u00f9\u0001\u0000"+
		"\u0000\u0000\u0103\u00fe\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000"+
		"\u0000\u0000\u0104!\u0001\u0000\u0000\u0000\u0105\u0106\u0005\u0019\u0000"+
		"\u0000\u0106\u0114\u0006\u0011\uffff\uffff\u0000\u0107\u0108\u0005\u001a"+
		"\u0000\u0000\u0108\u0114\u0006\u0011\uffff\uffff\u0000\u0109\u010a\u0005"+
		"\u001b\u0000\u0000\u010a\u0114\u0006\u0011\uffff\uffff\u0000\u010b\u010c"+
		"\u0005$\u0000\u0000\u010c\u0114\u0006\u0011\uffff\uffff\u0000\u010d\u010e"+
		"\u0005\u001f\u0000\u0000\u010e\u010f\u0006\u0011\uffff\uffff\u0000\u010f"+
		"\u0110\u0003\u001a\r\u0000\u0110\u0111\u0005 \u0000\u0000\u0111\u0112"+
		"\u0006\u0011\uffff\uffff\u0000\u0112\u0114\u0001\u0000\u0000\u0000\u0113"+
		"\u0105\u0001\u0000\u0000\u0000\u0113\u0107\u0001\u0000\u0000\u0000\u0113"+
		"\u0109\u0001\u0000\u0000\u0000\u0113\u010b\u0001\u0000\u0000\u0000\u0113"+
		"\u010d\u0001\u0000\u0000\u0000\u0114#\u0001\u0000\u0000\u0000\u0014)1"+
		"7BMVgp{\u008e\u0097\u009c\u00a4\u00a9\u00b7\u00c1\u00e2\u00f7\u0103\u0113";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}