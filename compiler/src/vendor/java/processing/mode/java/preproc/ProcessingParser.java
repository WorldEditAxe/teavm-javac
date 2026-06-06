// Generated from /tmp/processing-preproc-grammar/Processing.g4 by ANTLR 4.7.2
package processing.mode.java.preproc;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ProcessingParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, HexColorLiteral=2, WS=3, COMMENT=4, LINE_COMMENT=5, CHAR_LITERAL=6, 
		ABSTRACT=7, ASSERT=8, BOOLEAN=9, BREAK=10, BYTE=11, CASE=12, CATCH=13, 
		CHAR=14, CLASS=15, CONST=16, CONTINUE=17, DEFAULT=18, DO=19, DOUBLE=20, 
		ELSE=21, ENUM=22, EXTENDS=23, FINAL=24, FINALLY=25, FLOAT=26, FOR=27, 
		IF=28, GOTO=29, IMPLEMENTS=30, IMPORT=31, INSTANCEOF=32, INT=33, INTERFACE=34, 
		LONG=35, NATIVE=36, NEW=37, PACKAGE=38, PRIVATE=39, PROTECTED=40, PUBLIC=41, 
		RETURN=42, SHORT=43, STATIC=44, STRICTFP=45, SUPER=46, SWITCH=47, SYNCHRONIZED=48, 
		THIS=49, THROW=50, THROWS=51, TRANSIENT=52, TRY=53, VOID=54, VOLATILE=55, 
		WHILE=56, MODULE=57, OPEN=58, REQUIRES=59, EXPORTS=60, OPENS=61, TO=62, 
		USES=63, PROVIDES=64, WITH=65, TRANSITIVE=66, VAR=67, YIELD=68, RECORD=69, 
		SEALED=70, PERMITS=71, NON_SEALED=72, DECIMAL_LITERAL=73, HEX_LITERAL=74, 
		OCT_LITERAL=75, BINARY_LITERAL=76, FLOAT_LITERAL=77, HEX_FLOAT_LITERAL=78, 
		BOOL_LITERAL=79, STRING_LITERAL=80, MULTI_STRING_LIT=81, TEXT_BLOCK=82, 
		NULL_LITERAL=83, LPAREN=84, RPAREN=85, LBRACE=86, RBRACE=87, LBRACK=88, 
		RBRACK=89, SEMI=90, COMMA=91, DOT=92, ASSIGN=93, GT=94, LT=95, BANG=96, 
		TILDE=97, QUESTION=98, COLON=99, EQUAL=100, LE=101, GE=102, NOTEQUAL=103, 
		AND=104, OR=105, INC=106, DEC=107, ADD=108, SUB=109, MUL=110, DIV=111, 
		BITAND=112, BITOR=113, CARET=114, MOD=115, ADD_ASSIGN=116, SUB_ASSIGN=117, 
		MUL_ASSIGN=118, DIV_ASSIGN=119, AND_ASSIGN=120, OR_ASSIGN=121, XOR_ASSIGN=122, 
		MOD_ASSIGN=123, LSHIFT_ASSIGN=124, RSHIFT_ASSIGN=125, URSHIFT_ASSIGN=126, 
		ARROW=127, COLONCOLON=128, AT=129, ELLIPSIS=130, IDENTIFIER=131;
	public static final int
		RULE_processingSketch = 0, RULE_javaProcessingSketch = 1, RULE_staticProcessingSketch = 2, 
		RULE_activeProcessingSketch = 3, RULE_warnMixedModes = 4, RULE_variableDeclaratorId = 5, 
		RULE_warnTypeAsVariableName = 6, RULE_methodCall = 7, RULE_functionWithPrimitiveTypeName = 8, 
		RULE_primitiveType = 9, RULE_colorPrimitiveType = 10, RULE_qualifiedName = 11, 
		RULE_literal = 12, RULE_hexColorLiteral = 13, RULE_compilationUnit = 14, 
		RULE_packageDeclaration = 15, RULE_importDeclaration = 16, RULE_typeDeclaration = 17, 
		RULE_modifier = 18, RULE_classOrInterfaceModifier = 19, RULE_variableModifier = 20, 
		RULE_classDeclaration = 21, RULE_typeParameters = 22, RULE_typeParameter = 23, 
		RULE_typeBound = 24, RULE_enumDeclaration = 25, RULE_enumConstants = 26, 
		RULE_enumConstant = 27, RULE_enumBodyDeclarations = 28, RULE_interfaceDeclaration = 29, 
		RULE_classBody = 30, RULE_interfaceBody = 31, RULE_classBodyDeclaration = 32, 
		RULE_memberDeclaration = 33, RULE_methodDeclaration = 34, RULE_methodBody = 35, 
		RULE_typeTypeOrVoid = 36, RULE_genericMethodDeclaration = 37, RULE_genericConstructorDeclaration = 38, 
		RULE_constructorDeclaration = 39, RULE_compactConstructorDeclaration = 40, 
		RULE_fieldDeclaration = 41, RULE_interfaceBodyDeclaration = 42, RULE_interfaceMemberDeclaration = 43, 
		RULE_constDeclaration = 44, RULE_constantDeclarator = 45, RULE_interfaceMethodDeclaration = 46, 
		RULE_interfaceMethodModifier = 47, RULE_genericInterfaceMethodDeclaration = 48, 
		RULE_interfaceCommonBodyDeclaration = 49, RULE_variableDeclarators = 50, 
		RULE_variableDeclarator = 51, RULE_variableInitializer = 52, RULE_arrayInitializer = 53, 
		RULE_classOrInterfaceType = 54, RULE_typeArgument = 55, RULE_qualifiedNameList = 56, 
		RULE_formalParameters = 57, RULE_receiverParameter = 58, RULE_formalParameterList = 59, 
		RULE_formalParameter = 60, RULE_lastFormalParameter = 61, RULE_lambdaLVTIList = 62, 
		RULE_lambdaLVTIParameter = 63, RULE_baseStringLiteral = 64, RULE_multilineStringLiteral = 65, 
		RULE_stringLiteral = 66, RULE_integerLiteral = 67, RULE_floatLiteral = 68, 
		RULE_altAnnotationQualifiedName = 69, RULE_annotation = 70, RULE_elementValuePairs = 71, 
		RULE_elementValuePair = 72, RULE_elementValue = 73, RULE_elementValueArrayInitializer = 74, 
		RULE_annotationTypeDeclaration = 75, RULE_annotationTypeBody = 76, RULE_annotationTypeElementDeclaration = 77, 
		RULE_annotationTypeElementRest = 78, RULE_annotationMethodOrConstantRest = 79, 
		RULE_annotationMethodRest = 80, RULE_annotationConstantRest = 81, RULE_defaultValue = 82, 
		RULE_moduleDeclaration = 83, RULE_moduleBody = 84, RULE_moduleDirective = 85, 
		RULE_requiresModifier = 86, RULE_recordDeclaration = 87, RULE_recordHeader = 88, 
		RULE_recordComponentList = 89, RULE_recordComponent = 90, RULE_recordBody = 91, 
		RULE_block = 92, RULE_blockStatement = 93, RULE_localVariableDeclaration = 94, 
		RULE_identifier = 95, RULE_typeIdentifier = 96, RULE_localTypeDeclaration = 97, 
		RULE_statement = 98, RULE_catchClause = 99, RULE_catchType = 100, RULE_finallyBlock = 101, 
		RULE_resourceSpecification = 102, RULE_resources = 103, RULE_resource = 104, 
		RULE_switchBlockStatementGroup = 105, RULE_switchLabel = 106, RULE_forControl = 107, 
		RULE_forInit = 108, RULE_enhancedForControl = 109, RULE_parExpression = 110, 
		RULE_expressionList = 111, RULE_expression = 112, RULE_pattern = 113, 
		RULE_lambdaExpression = 114, RULE_lambdaParameters = 115, RULE_lambdaBody = 116, 
		RULE_primary = 117, RULE_switchExpression = 118, RULE_switchLabeledRule = 119, 
		RULE_guardedPattern = 120, RULE_switchRuleOutcome = 121, RULE_classType = 122, 
		RULE_creator = 123, RULE_createdName = 124, RULE_innerCreator = 125, RULE_arrayCreatorRest = 126, 
		RULE_classCreatorRest = 127, RULE_explicitGenericInvocation = 128, RULE_typeArgumentsOrDiamond = 129, 
		RULE_nonWildcardTypeArgumentsOrDiamond = 130, RULE_nonWildcardTypeArguments = 131, 
		RULE_typeList = 132, RULE_typeType = 133, RULE_typeArguments = 134, RULE_superSuffix = 135, 
		RULE_explicitGenericInvocationSuffix = 136, RULE_arguments = 137;
	private static String[] makeRuleNames() {
		return new String[] {
			"processingSketch", "javaProcessingSketch", "staticProcessingSketch", 
			"activeProcessingSketch", "warnMixedModes", "variableDeclaratorId", "warnTypeAsVariableName", 
			"methodCall", "functionWithPrimitiveTypeName", "primitiveType", "colorPrimitiveType", 
			"qualifiedName", "literal", "hexColorLiteral", "compilationUnit", "packageDeclaration", 
			"importDeclaration", "typeDeclaration", "modifier", "classOrInterfaceModifier", 
			"variableModifier", "classDeclaration", "typeParameters", "typeParameter", 
			"typeBound", "enumDeclaration", "enumConstants", "enumConstant", "enumBodyDeclarations", 
			"interfaceDeclaration", "classBody", "interfaceBody", "classBodyDeclaration", 
			"memberDeclaration", "methodDeclaration", "methodBody", "typeTypeOrVoid", 
			"genericMethodDeclaration", "genericConstructorDeclaration", "constructorDeclaration", 
			"compactConstructorDeclaration", "fieldDeclaration", "interfaceBodyDeclaration", 
			"interfaceMemberDeclaration", "constDeclaration", "constantDeclarator", 
			"interfaceMethodDeclaration", "interfaceMethodModifier", "genericInterfaceMethodDeclaration", 
			"interfaceCommonBodyDeclaration", "variableDeclarators", "variableDeclarator", 
			"variableInitializer", "arrayInitializer", "classOrInterfaceType", "typeArgument", 
			"qualifiedNameList", "formalParameters", "receiverParameter", "formalParameterList", 
			"formalParameter", "lastFormalParameter", "lambdaLVTIList", "lambdaLVTIParameter", 
			"baseStringLiteral", "multilineStringLiteral", "stringLiteral", "integerLiteral", 
			"floatLiteral", "altAnnotationQualifiedName", "annotation", "elementValuePairs", 
			"elementValuePair", "elementValue", "elementValueArrayInitializer", "annotationTypeDeclaration", 
			"annotationTypeBody", "annotationTypeElementDeclaration", "annotationTypeElementRest", 
			"annotationMethodOrConstantRest", "annotationMethodRest", "annotationConstantRest", 
			"defaultValue", "moduleDeclaration", "moduleBody", "moduleDirective", 
			"requiresModifier", "recordDeclaration", "recordHeader", "recordComponentList", 
			"recordComponent", "recordBody", "block", "blockStatement", "localVariableDeclaration", 
			"identifier", "typeIdentifier", "localTypeDeclaration", "statement", 
			"catchClause", "catchType", "finallyBlock", "resourceSpecification", 
			"resources", "resource", "switchBlockStatementGroup", "switchLabel", 
			"forControl", "forInit", "enhancedForControl", "parExpression", "expressionList", 
			"expression", "pattern", "lambdaExpression", "lambdaParameters", "lambdaBody", 
			"primary", "switchExpression", "switchLabeledRule", "guardedPattern", 
			"switchRuleOutcome", "classType", "creator", "createdName", "innerCreator", 
			"arrayCreatorRest", "classCreatorRest", "explicitGenericInvocation", 
			"typeArgumentsOrDiamond", "nonWildcardTypeArgumentsOrDiamond", "nonWildcardTypeArguments", 
			"typeList", "typeType", "typeArguments", "superSuffix", "explicitGenericInvocationSuffix", 
			"arguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'color'", null, null, null, null, null, "'abstract'", "'assert'", 
			"'boolean'", "'break'", "'byte'", "'case'", "'catch'", "'char'", "'class'", 
			"'const'", "'continue'", "'default'", "'do'", "'double'", "'else'", "'enum'", 
			"'extends'", "'final'", "'finally'", "'float'", "'for'", "'if'", "'goto'", 
			"'implements'", "'import'", "'instanceof'", "'int'", "'interface'", "'long'", 
			"'native'", "'new'", "'package'", "'private'", "'protected'", "'public'", 
			"'return'", "'short'", "'static'", "'strictfp'", "'super'", "'switch'", 
			"'synchronized'", "'this'", "'throw'", "'throws'", "'transient'", "'try'", 
			"'void'", "'volatile'", "'while'", "'module'", "'open'", "'requires'", 
			"'exports'", "'opens'", "'to'", "'uses'", "'provides'", "'with'", "'transitive'", 
			"'var'", "'yield'", "'record'", "'sealed'", "'permits'", "'non-sealed'", 
			null, null, null, null, null, null, null, null, null, null, "'null'", 
			"'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", "'='", 
			"'>'", "'<'", "'!'", "'~'", "'?'", "':'", "'=='", "'<='", "'>='", "'!='", 
			"'&&'", "'||'", "'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'&'", "'|'", 
			"'^'", "'%'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", 
			"'%='", "'<<='", "'>>='", "'>>>='", "'->'", "'::'", "'@'", "'...'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "HexColorLiteral", "WS", "COMMENT", "LINE_COMMENT", "CHAR_LITERAL", 
			"ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", "CHAR", 
			"CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", "ENUM", 
			"EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", "IF", "GOTO", "IMPLEMENTS", 
			"IMPORT", "INSTANCEOF", "INT", "INTERFACE", "LONG", "NATIVE", "NEW", 
			"PACKAGE", "PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SHORT", "STATIC", 
			"STRICTFP", "SUPER", "SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", 
			"TRANSIENT", "TRY", "VOID", "VOLATILE", "WHILE", "MODULE", "OPEN", "REQUIRES", 
			"EXPORTS", "OPENS", "TO", "USES", "PROVIDES", "WITH", "TRANSITIVE", "VAR", 
			"YIELD", "RECORD", "SEALED", "PERMITS", "NON_SEALED", "DECIMAL_LITERAL", 
			"HEX_LITERAL", "OCT_LITERAL", "BINARY_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", 
			"BOOL_LITERAL", "STRING_LITERAL", "MULTI_STRING_LIT", "TEXT_BLOCK", "NULL_LITERAL", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", 
			"DOT", "ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", "COLON", "EQUAL", 
			"LE", "GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", "SUB", "MUL", 
			"DIV", "BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", "SUB_ASSIGN", 
			"MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", 
			"MOD_ASSIGN", "LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "ARROW", 
			"COLONCOLON", "AT", "ELLIPSIS", "IDENTIFIER"
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
	public String getGrammarFileName() { return "Processing.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ProcessingParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProcessingSketchContext extends ParserRuleContext {
		public StaticProcessingSketchContext staticProcessingSketch() {
			return getRuleContext(StaticProcessingSketchContext.class,0);
		}
		public JavaProcessingSketchContext javaProcessingSketch() {
			return getRuleContext(JavaProcessingSketchContext.class,0);
		}
		public ActiveProcessingSketchContext activeProcessingSketch() {
			return getRuleContext(ActiveProcessingSketchContext.class,0);
		}
		public ProcessingSketchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_processingSketch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterProcessingSketch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitProcessingSketch(this);
		}
	}

	public final ProcessingSketchContext processingSketch() throws RecognitionException {
		ProcessingSketchContext _localctx = new ProcessingSketchContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_processingSketch);
		try {
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				staticProcessingSketch();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				javaProcessingSketch();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(278);
				activeProcessingSketch();
				}
				break;
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

	public static class JavaProcessingSketchContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ProcessingParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public JavaProcessingSketchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_javaProcessingSketch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterJavaProcessingSketch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitJavaProcessingSketch(this);
		}
	}

	public final JavaProcessingSketchContext javaProcessingSketch() throws RecognitionException {
		JavaProcessingSketchContext _localctx = new JavaProcessingSketchContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_javaProcessingSketch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(281);
				packageDeclaration();
				}
				break;
			}
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(284);
				importDeclaration();
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(291); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(290);
				typeDeclaration();
				}
				}
				setState(293); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 7)) & ~0x3f) == 0 && ((1L << (_la - 7)) & ((1L << (ABSTRACT - 7)) | (1L << (CLASS - 7)) | (1L << (ENUM - 7)) | (1L << (FINAL - 7)) | (1L << (INTERFACE - 7)) | (1L << (PRIVATE - 7)) | (1L << (PROTECTED - 7)) | (1L << (PUBLIC - 7)) | (1L << (STATIC - 7)) | (1L << (STRICTFP - 7)) | (1L << (MODULE - 7)) | (1L << (OPEN - 7)) | (1L << (REQUIRES - 7)) | (1L << (EXPORTS - 7)) | (1L << (OPENS - 7)) | (1L << (TO - 7)) | (1L << (USES - 7)) | (1L << (PROVIDES - 7)) | (1L << (WITH - 7)) | (1L << (TRANSITIVE - 7)) | (1L << (VAR - 7)) | (1L << (YIELD - 7)) | (1L << (RECORD - 7)) | (1L << (SEALED - 7)))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (PERMITS - 71)) | (1L << (NON_SEALED - 71)) | (1L << (AT - 71)) | (1L << (IDENTIFIER - 71)))) != 0) );
			setState(295);
			match(EOF);
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

	public static class StaticProcessingSketchContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ProcessingParser.EOF, 0); }
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public StaticProcessingSketchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticProcessingSketch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterStaticProcessingSketch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitStaticProcessingSketch(this);
		}
	}

	public final StaticProcessingSketchContext staticProcessingSketch() throws RecognitionException {
		StaticProcessingSketchContext _localctx = new StaticProcessingSketchContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_staticProcessingSketch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << IMPORT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(300);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(297);
					importDeclaration();
					}
					break;
				case 2:
					{
					setState(298);
					blockStatement();
					}
					break;
				case 3:
					{
					setState(299);
					typeDeclaration();
					}
					break;
				}
				}
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(305);
			match(EOF);
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

	public static class ActiveProcessingSketchContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ProcessingParser.EOF, 0); }
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public ActiveProcessingSketchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activeProcessingSketch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterActiveProcessingSketch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitActiveProcessingSketch(this);
		}
	}

	public final ActiveProcessingSketchContext activeProcessingSketch() throws RecognitionException {
		ActiveProcessingSketchContext _localctx = new ActiveProcessingSketchContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_activeProcessingSketch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << IMPORT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(309);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(307);
					importDeclaration();
					}
					break;
				case T__0:
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOID:
				case VOLATILE:
				case MODULE:
				case OPEN:
				case REQUIRES:
				case EXPORTS:
				case OPENS:
				case TO:
				case USES:
				case PROVIDES:
				case WITH:
				case TRANSITIVE:
				case VAR:
				case YIELD:
				case RECORD:
				case SEALED:
				case PERMITS:
				case NON_SEALED:
				case LBRACE:
				case SEMI:
				case LT:
				case AT:
				case IDENTIFIER:
					{
					setState(308);
					classBodyDeclaration();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(314);
			match(EOF);
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

	public static class WarnMixedModesContext extends ParserRuleContext {
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public WarnMixedModesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_warnMixedModes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterWarnMixedModes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitWarnMixedModes(this);
		}
	}

	public final WarnMixedModesContext warnMixedModes() throws RecognitionException {
		WarnMixedModesContext _localctx = new WarnMixedModesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_warnMixedModes);
		int _la;
		try {
			int _alt;
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(319);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
						case 1:
							{
							setState(316);
							importDeclaration();
							}
							break;
						case 2:
							{
							setState(317);
							classBodyDeclaration();
							}
							break;
						case 3:
							{
							setState(318);
							blockStatement();
							}
							break;
						}
						} 
					}
					setState(323);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(324);
				blockStatement();
				setState(325);
				classBodyDeclaration();
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << IMPORT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRANSIENT) | (1L << TRY) | (1L << VOID) | (1L << VOLATILE) | (1L << WHILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(329);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(326);
						importDeclaration();
						}
						break;
					case 2:
						{
						setState(327);
						classBodyDeclaration();
						}
						break;
					case 3:
						{
						setState(328);
						blockStatement();
						}
						break;
					}
					}
					setState(333);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(337);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
						case 1:
							{
							setState(334);
							importDeclaration();
							}
							break;
						case 2:
							{
							setState(335);
							classBodyDeclaration();
							}
							break;
						case 3:
							{
							setState(336);
							blockStatement();
							}
							break;
						}
						} 
					}
					setState(341);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				setState(342);
				classBodyDeclaration();
				setState(343);
				blockStatement();
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << IMPORT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRANSIENT) | (1L << TRY) | (1L << VOID) | (1L << VOLATILE) | (1L << WHILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(347);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						setState(344);
						importDeclaration();
						}
						break;
					case 2:
						{
						setState(345);
						classBodyDeclaration();
						}
						break;
					case 3:
						{
						setState(346);
						blockStatement();
						}
						break;
					}
					}
					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
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

	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public WarnTypeAsVariableNameContext warnTypeAsVariableName() {
			return getRuleContext(WarnTypeAsVariableNameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ProcessingParser.IDENTIFIER, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitVariableDeclaratorId(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableDeclaratorId);
		int _la;
		try {
			setState(363);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(354);
				warnTypeAsVariableName();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				match(IDENTIFIER);
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACK) {
					{
					{
					setState(356);
					match(LBRACK);
					setState(357);
					match(RBRACK);
					}
					}
					setState(362);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class WarnTypeAsVariableNameContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType;
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public WarnTypeAsVariableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_warnTypeAsVariableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterWarnTypeAsVariableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitWarnTypeAsVariableName(this);
		}
	}

	public final WarnTypeAsVariableNameContext warnTypeAsVariableName() throws RecognitionException {
		WarnTypeAsVariableNameContext _localctx = new WarnTypeAsVariableNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_warnTypeAsVariableName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			((WarnTypeAsVariableNameContext)_localctx).primitiveType = primitiveType();
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(366);
				match(LBRACK);
				setState(367);
				match(RBRACK);
				}
				}
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        notifyErrorListeners("Type names are not allowed as variable names: "+(((WarnTypeAsVariableNameContext)_localctx).primitiveType!=null?_input.getText(((WarnTypeAsVariableNameContext)_localctx).primitiveType.start,((WarnTypeAsVariableNameContext)_localctx).primitiveType.stop):null));
			      
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

	public static class MethodCallContext extends ParserRuleContext {
		public FunctionWithPrimitiveTypeNameContext functionWithPrimitiveTypeName() {
			return getRuleContext(FunctionWithPrimitiveTypeNameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ProcessingParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode THIS() { return getToken(ProcessingParser.THIS, 0); }
		public TerminalNode SUPER() { return getToken(ProcessingParser.SUPER, 0); }
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMethodCall(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_methodCall);
		int _la;
		try {
			setState(394);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case FLOAT:
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				functionWithPrimitiveTypeName();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				match(IDENTIFIER);
				setState(377);
				match(LPAREN);
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(378);
					expressionList();
					}
				}

				setState(381);
				match(RPAREN);
				}
				break;
			case THIS:
				enterOuterAlt(_localctx, 3);
				{
				setState(382);
				match(THIS);
				setState(383);
				match(LPAREN);
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(384);
					expressionList();
					}
				}

				setState(387);
				match(RPAREN);
				}
				break;
			case SUPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(388);
				match(SUPER);
				setState(389);
				match(LPAREN);
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(390);
					expressionList();
					}
				}

				setState(393);
				match(RPAREN);
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

	public static class FunctionWithPrimitiveTypeNameContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public TerminalNode BOOLEAN() { return getToken(ProcessingParser.BOOLEAN, 0); }
		public TerminalNode BYTE() { return getToken(ProcessingParser.BYTE, 0); }
		public TerminalNode CHAR() { return getToken(ProcessingParser.CHAR, 0); }
		public TerminalNode FLOAT() { return getToken(ProcessingParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(ProcessingParser.INT, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FunctionWithPrimitiveTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionWithPrimitiveTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFunctionWithPrimitiveTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFunctionWithPrimitiveTypeName(this);
		}
	}

	public final FunctionWithPrimitiveTypeNameContext functionWithPrimitiveTypeName() throws RecognitionException {
		FunctionWithPrimitiveTypeNameContext _localctx = new FunctionWithPrimitiveTypeNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionWithPrimitiveTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << FLOAT) | (1L << INT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(397);
			match(LPAREN);
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(398);
				expressionList();
				}
			}

			setState(401);
			match(RPAREN);
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

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(ProcessingParser.BOOLEAN, 0); }
		public TerminalNode CHAR() { return getToken(ProcessingParser.CHAR, 0); }
		public TerminalNode BYTE() { return getToken(ProcessingParser.BYTE, 0); }
		public TerminalNode SHORT() { return getToken(ProcessingParser.SHORT, 0); }
		public TerminalNode INT() { return getToken(ProcessingParser.INT, 0); }
		public TerminalNode LONG() { return getToken(ProcessingParser.LONG, 0); }
		public TerminalNode FLOAT() { return getToken(ProcessingParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(ProcessingParser.DOUBLE, 0); }
		public ColorPrimitiveTypeContext colorPrimitiveType() {
			return getRuleContext(ColorPrimitiveTypeContext.class,0);
		}
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitPrimitiveType(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_primitiveType);
		try {
			setState(412);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				match(BOOLEAN);
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				match(CHAR);
				}
				break;
			case BYTE:
				enterOuterAlt(_localctx, 3);
				{
				setState(405);
				match(BYTE);
				}
				break;
			case SHORT:
				enterOuterAlt(_localctx, 4);
				{
				setState(406);
				match(SHORT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 5);
				{
				setState(407);
				match(INT);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 6);
				{
				setState(408);
				match(LONG);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 7);
				{
				setState(409);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 8);
				{
				setState(410);
				match(DOUBLE);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 9);
				{
				setState(411);
				colorPrimitiveType();
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

	public static class ColorPrimitiveTypeContext extends ParserRuleContext {
		public ColorPrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colorPrimitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterColorPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitColorPrimitiveType(this);
		}
	}

	public final ColorPrimitiveTypeContext colorPrimitiveType() throws RecognitionException {
		ColorPrimitiveTypeContext _localctx = new ColorPrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_colorPrimitiveType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			match(T__0);
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

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(ProcessingParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(ProcessingParser.IDENTIFIER, i);
		}
		public List<ColorPrimitiveTypeContext> colorPrimitiveType() {
			return getRuleContexts(ColorPrimitiveTypeContext.class);
		}
		public ColorPrimitiveTypeContext colorPrimitiveType(int i) {
			return getRuleContext(ColorPrimitiveTypeContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(ProcessingParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(ProcessingParser.DOT, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitQualifiedName(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(416);
				match(IDENTIFIER);
				}
				break;
			case T__0:
				{
				setState(417);
				colorPrimitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(427);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(420);
					match(DOT);
					setState(423);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case IDENTIFIER:
						{
						setState(421);
						match(IDENTIFIER);
						}
						break;
					case T__0:
						{
						setState(422);
						colorPrimitiveType();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					} 
				}
				setState(429);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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

	public static class LiteralContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class,0);
		}
		public TerminalNode CHAR_LITERAL() { return getToken(ProcessingParser.CHAR_LITERAL, 0); }
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public TerminalNode BOOL_LITERAL() { return getToken(ProcessingParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(ProcessingParser.NULL_LITERAL, 0); }
		public HexColorLiteralContext hexColorLiteral() {
			return getRuleContext(HexColorLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_literal);
		try {
			setState(437);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(430);
				integerLiteral();
				}
				break;
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				floatLiteral();
				}
				break;
			case CHAR_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(432);
				match(CHAR_LITERAL);
				}
				break;
			case STRING_LITERAL:
			case MULTI_STRING_LIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(433);
				stringLiteral();
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(434);
				match(BOOL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(435);
				match(NULL_LITERAL);
				}
				break;
			case HexColorLiteral:
				enterOuterAlt(_localctx, 7);
				{
				setState(436);
				hexColorLiteral();
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

	public static class HexColorLiteralContext extends ParserRuleContext {
		public TerminalNode HexColorLiteral() { return getToken(ProcessingParser.HexColorLiteral, 0); }
		public HexColorLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexColorLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterHexColorLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitHexColorLiteral(this);
		}
	}

	public final HexColorLiteralContext hexColorLiteral() throws RecognitionException {
		HexColorLiteralContext _localctx = new HexColorLiteralContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_hexColorLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			match(HexColorLiteral);
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

	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ProcessingParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(ProcessingParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(ProcessingParser.SEMI, i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public ModuleDeclarationContext moduleDeclaration() {
			return getRuleContext(ModuleDeclarationContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_compilationUnit);
		int _la;
		try {
			int _alt;
			setState(462);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(442);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(441);
					packageDeclaration();
					}
					break;
				}
				setState(448);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(446);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case IMPORT:
							{
							setState(444);
							importDeclaration();
							}
							break;
						case SEMI:
							{
							setState(445);
							match(SEMI);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(450);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 7)) & ~0x3f) == 0 && ((1L << (_la - 7)) & ((1L << (ABSTRACT - 7)) | (1L << (CLASS - 7)) | (1L << (ENUM - 7)) | (1L << (FINAL - 7)) | (1L << (INTERFACE - 7)) | (1L << (PRIVATE - 7)) | (1L << (PROTECTED - 7)) | (1L << (PUBLIC - 7)) | (1L << (STATIC - 7)) | (1L << (STRICTFP - 7)) | (1L << (MODULE - 7)) | (1L << (OPEN - 7)) | (1L << (REQUIRES - 7)) | (1L << (EXPORTS - 7)) | (1L << (OPENS - 7)) | (1L << (TO - 7)) | (1L << (USES - 7)) | (1L << (PROVIDES - 7)) | (1L << (WITH - 7)) | (1L << (TRANSITIVE - 7)) | (1L << (VAR - 7)) | (1L << (YIELD - 7)) | (1L << (RECORD - 7)) | (1L << (SEALED - 7)))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (PERMITS - 71)) | (1L << (NON_SEALED - 71)) | (1L << (SEMI - 71)) | (1L << (AT - 71)) | (1L << (IDENTIFIER - 71)))) != 0)) {
					{
					setState(453);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ABSTRACT:
					case CLASS:
					case ENUM:
					case FINAL:
					case INTERFACE:
					case PRIVATE:
					case PROTECTED:
					case PUBLIC:
					case STATIC:
					case STRICTFP:
					case MODULE:
					case OPEN:
					case REQUIRES:
					case EXPORTS:
					case OPENS:
					case TO:
					case USES:
					case PROVIDES:
					case WITH:
					case TRANSITIVE:
					case VAR:
					case YIELD:
					case RECORD:
					case SEALED:
					case PERMITS:
					case NON_SEALED:
					case AT:
					case IDENTIFIER:
						{
						setState(451);
						typeDeclaration();
						}
						break;
					case SEMI:
						{
						setState(452);
						match(SEMI);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(457);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(458);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(459);
				moduleDeclaration();
				setState(460);
				match(EOF);
				}
				break;
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

	public static class PackageDeclarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(ProcessingParser.PACKAGE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitPackageDeclaration(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_packageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(464);
				annotation();
				}
				}
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(470);
			match(PACKAGE);
			setState(471);
			qualifiedName();
			setState(472);
			match(SEMI);
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

	public static class ImportDeclarationContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(ProcessingParser.IMPORT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public TerminalNode STATIC() { return getToken(ProcessingParser.STATIC, 0); }
		public TerminalNode DOT() { return getToken(ProcessingParser.DOT, 0); }
		public TerminalNode MUL() { return getToken(ProcessingParser.MUL, 0); }
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitImportDeclaration(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			match(IMPORT);
			setState(476);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(475);
				match(STATIC);
				}
			}

			setState(478);
			qualifiedName();
			setState(481);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(479);
				match(DOT);
				setState(480);
				match(MUL);
				}
			}

			setState(483);
			match(SEMI);
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

	public static class TypeDeclarationContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeDeclaration(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(485);
					classOrInterfaceModifier();
					}
					} 
				}
				setState(490);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			setState(496);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(491);
				classDeclaration();
				}
				break;
			case ENUM:
				{
				setState(492);
				enumDeclaration();
				}
				break;
			case INTERFACE:
				{
				setState(493);
				interfaceDeclaration();
				}
				break;
			case AT:
				{
				setState(494);
				annotationTypeDeclaration();
				}
				break;
			case RECORD:
				{
				setState(495);
				recordDeclaration();
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

	public static class ModifierContext extends ParserRuleContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier() {
			return getRuleContext(ClassOrInterfaceModifierContext.class,0);
		}
		public TerminalNode NATIVE() { return getToken(ProcessingParser.NATIVE, 0); }
		public TerminalNode SYNCHRONIZED() { return getToken(ProcessingParser.SYNCHRONIZED, 0); }
		public TerminalNode TRANSIENT() { return getToken(ProcessingParser.TRANSIENT, 0); }
		public TerminalNode VOLATILE() { return getToken(ProcessingParser.VOLATILE, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitModifier(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_modifier);
		try {
			setState(503);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case FINAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case NON_SEALED:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(498);
				classOrInterfaceModifier();
				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(499);
				match(NATIVE);
				}
				break;
			case SYNCHRONIZED:
				enterOuterAlt(_localctx, 3);
				{
				setState(500);
				match(SYNCHRONIZED);
				}
				break;
			case TRANSIENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(501);
				match(TRANSIENT);
				}
				break;
			case VOLATILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(502);
				match(VOLATILE);
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

	public static class ClassOrInterfaceModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(ProcessingParser.PUBLIC, 0); }
		public TerminalNode PROTECTED() { return getToken(ProcessingParser.PROTECTED, 0); }
		public TerminalNode PRIVATE() { return getToken(ProcessingParser.PRIVATE, 0); }
		public TerminalNode STATIC() { return getToken(ProcessingParser.STATIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(ProcessingParser.ABSTRACT, 0); }
		public TerminalNode FINAL() { return getToken(ProcessingParser.FINAL, 0); }
		public TerminalNode STRICTFP() { return getToken(ProcessingParser.STRICTFP, 0); }
		public TerminalNode SEALED() { return getToken(ProcessingParser.SEALED, 0); }
		public TerminalNode NON_SEALED() { return getToken(ProcessingParser.NON_SEALED, 0); }
		public ClassOrInterfaceModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassOrInterfaceModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassOrInterfaceModifier(this);
		}
	}

	public final ClassOrInterfaceModifierContext classOrInterfaceModifier() throws RecognitionException {
		ClassOrInterfaceModifierContext _localctx = new ClassOrInterfaceModifierContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_classOrInterfaceModifier);
		try {
			setState(515);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(505);
				annotation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(506);
				match(PUBLIC);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(507);
				match(PROTECTED);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(508);
				match(PRIVATE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(509);
				match(STATIC);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(510);
				match(ABSTRACT);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(511);
				match(FINAL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(512);
				match(STRICTFP);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(513);
				match(SEALED);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(514);
				match(NON_SEALED);
				}
				break;
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

	public static class VariableModifierContext extends ParserRuleContext {
		public TerminalNode FINAL() { return getToken(ProcessingParser.FINAL, 0); }
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public VariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitVariableModifier(this);
		}
	}

	public final VariableModifierContext variableModifier() throws RecognitionException {
		VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_variableModifier);
		try {
			setState(519);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(517);
				match(FINAL);
				}
				break;
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(518);
				annotation();
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

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(ProcessingParser.CLASS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(ProcessingParser.EXTENDS, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(ProcessingParser.IMPLEMENTS, 0); }
		public List<TypeListContext> typeList() {
			return getRuleContexts(TypeListContext.class);
		}
		public TypeListContext typeList(int i) {
			return getRuleContext(TypeListContext.class,i);
		}
		public TerminalNode PERMITS() { return getToken(ProcessingParser.PERMITS, 0); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			match(CLASS);
			setState(522);
			identifier();
			setState(524);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(523);
				typeParameters();
				}
			}

			setState(528);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(526);
				match(EXTENDS);
				setState(527);
				typeType();
				}
			}

			setState(532);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(530);
				match(IMPLEMENTS);
				setState(531);
				typeList();
				}
			}

			setState(536);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERMITS) {
				{
				setState(534);
				match(PERMITS);
				setState(535);
				typeList();
				}
			}

			setState(538);
			classBody();
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

	public static class TypeParametersContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(ProcessingParser.LT, 0); }
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(ProcessingParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeParameters(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
			match(LT);
			setState(541);
			typeParameter();
			setState(546);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(542);
				match(COMMA);
				setState(543);
				typeParameter();
				}
				}
				setState(548);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(549);
			match(GT);
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

	public static class TypeParameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(ProcessingParser.EXTENDS, 0); }
		public TypeBoundContext typeBound() {
			return getRuleContext(TypeBoundContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeParameter(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeParameter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(554);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(551);
					annotation();
					}
					} 
				}
				setState(556);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(557);
			identifier();
			setState(566);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(558);
				match(EXTENDS);
				setState(562);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(559);
						annotation();
						}
						} 
					}
					setState(564);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				}
				setState(565);
				typeBound();
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

	public static class TypeBoundContext extends ParserRuleContext {
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public List<TerminalNode> BITAND() { return getTokens(ProcessingParser.BITAND); }
		public TerminalNode BITAND(int i) {
			return getToken(ProcessingParser.BITAND, i);
		}
		public TypeBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeBound(this);
		}
	}

	public final TypeBoundContext typeBound() throws RecognitionException {
		TypeBoundContext _localctx = new TypeBoundContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_typeBound);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568);
			typeType();
			setState(573);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(569);
				match(BITAND);
				setState(570);
				typeType();
				}
				}
				setState(575);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class EnumDeclarationContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(ProcessingParser.ENUM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public TerminalNode IMPLEMENTS() { return getToken(ProcessingParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public EnumConstantsContext enumConstants() {
			return getRuleContext(EnumConstantsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ProcessingParser.COMMA, 0); }
		public EnumBodyDeclarationsContext enumBodyDeclarations() {
			return getRuleContext(EnumBodyDeclarationsContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitEnumDeclaration(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576);
			match(ENUM);
			setState(577);
			identifier();
			setState(580);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(578);
				match(IMPLEMENTS);
				setState(579);
				typeList();
				}
			}

			setState(582);
			match(LBRACE);
			setState(584);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(583);
				enumConstants();
				}
			}

			setState(587);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(586);
				match(COMMA);
				}
			}

			setState(590);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(589);
				enumBodyDeclarations();
				}
			}

			setState(592);
			match(RBRACE);
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

	public static class EnumConstantsContext extends ParserRuleContext {
		public List<EnumConstantContext> enumConstant() {
			return getRuleContexts(EnumConstantContext.class);
		}
		public EnumConstantContext enumConstant(int i) {
			return getRuleContext(EnumConstantContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public EnumConstantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterEnumConstants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitEnumConstants(this);
		}
	}

	public final EnumConstantsContext enumConstants() throws RecognitionException {
		EnumConstantsContext _localctx = new EnumConstantsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_enumConstants);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(594);
			enumConstant();
			setState(599);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(595);
					match(COMMA);
					setState(596);
					enumConstant();
					}
					} 
				}
				setState(601);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
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

	public static class EnumConstantContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public EnumConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterEnumConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitEnumConstant(this);
		}
	}

	public final EnumConstantContext enumConstant() throws RecognitionException {
		EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_enumConstant);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(602);
					annotation();
					}
					} 
				}
				setState(607);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			setState(608);
			identifier();
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(609);
				arguments();
				}
			}

			setState(613);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(612);
				classBody();
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

	public static class EnumBodyDeclarationsContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public EnumBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterEnumBodyDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitEnumBodyDeclarations(this);
		}
	}

	public final EnumBodyDeclarationsContext enumBodyDeclarations() throws RecognitionException {
		EnumBodyDeclarationsContext _localctx = new EnumBodyDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_enumBodyDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615);
			match(SEMI);
			setState(619);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(616);
				classBodyDeclaration();
				}
				}
				setState(621);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(ProcessingParser.INTERFACE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(ProcessingParser.EXTENDS, 0); }
		public List<TypeListContext> typeList() {
			return getRuleContexts(TypeListContext.class);
		}
		public TypeListContext typeList(int i) {
			return getRuleContext(TypeListContext.class,i);
		}
		public TerminalNode PERMITS() { return getToken(ProcessingParser.PERMITS, 0); }
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceDeclaration(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622);
			match(INTERFACE);
			setState(623);
			identifier();
			setState(625);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(624);
				typeParameters();
				}
			}

			setState(629);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(627);
				match(EXTENDS);
				setState(628);
				typeList();
				}
			}

			setState(633);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PERMITS) {
				{
				setState(631);
				match(PERMITS);
				setState(632);
				typeList();
				}
			}

			setState(635);
			interfaceBody();
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

	public static class ClassBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			match(LBRACE);
			setState(641);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(638);
				classBodyDeclaration();
				}
				}
				setState(643);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(644);
			match(RBRACE);
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

	public static class InterfaceBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<InterfaceBodyDeclarationContext> interfaceBodyDeclaration() {
			return getRuleContexts(InterfaceBodyDeclarationContext.class);
		}
		public InterfaceBodyDeclarationContext interfaceBodyDeclaration(int i) {
			return getRuleContext(InterfaceBodyDeclarationContext.class,i);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceBody(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(646);
			match(LBRACE);
			setState(650);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DEFAULT) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(647);
				interfaceBodyDeclaration();
				}
				}
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(653);
			match(RBRACE);
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

	public static class ClassBodyDeclarationContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode STATIC() { return getToken(ProcessingParser.STATIC, 0); }
		public MemberDeclarationContext memberDeclaration() {
			return getRuleContext(MemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassBodyDeclaration(this);
		}
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_classBodyDeclaration);
		int _la;
		try {
			int _alt;
			setState(667);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(655);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(657);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(656);
					match(STATIC);
					}
				}

				setState(659);
				block();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(663);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(660);
						modifier();
						}
						} 
					}
					setState(665);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
				}
				setState(666);
				memberDeclaration();
				}
				break;
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

	public static class MemberDeclarationContext extends ParserRuleContext {
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext genericMethodDeclaration() {
			return getRuleContext(GenericMethodDeclarationContext.class,0);
		}
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext genericConstructorDeclaration() {
			return getRuleContext(GenericConstructorDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public MemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMemberDeclaration(this);
		}
	}

	public final MemberDeclarationContext memberDeclaration() throws RecognitionException {
		MemberDeclarationContext _localctx = new MemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_memberDeclaration);
		try {
			setState(679);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(669);
				recordDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(670);
				methodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(671);
				genericMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(672);
				fieldDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(673);
				constructorDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(674);
				genericConstructorDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(675);
				interfaceDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(676);
				annotationTypeDeclaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(677);
				classDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(678);
				enumDeclaration();
				}
				break;
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

	public static class MethodDeclarationContext extends ParserRuleContext {
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public TerminalNode THROWS() { return getToken(ProcessingParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_methodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(681);
			typeTypeOrVoid();
			setState(682);
			identifier();
			setState(683);
			formalParameters();
			setState(688);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(684);
				match(LBRACK);
				setState(685);
				match(RBRACK);
				}
				}
				setState(690);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(693);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(691);
				match(THROWS);
				setState(692);
				qualifiedNameList();
				}
			}

			setState(695);
			methodBody();
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

	public static class MethodBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMethodBody(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_methodBody);
		try {
			setState(699);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(697);
				block();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(698);
				match(SEMI);
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

	public static class TypeTypeOrVoidContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(ProcessingParser.VOID, 0); }
		public TypeTypeOrVoidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTypeOrVoid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeTypeOrVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeTypeOrVoid(this);
		}
	}

	public final TypeTypeOrVoidContext typeTypeOrVoid() throws RecognitionException {
		TypeTypeOrVoidContext _localctx = new TypeTypeOrVoidContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_typeTypeOrVoid);
		try {
			setState(703);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(701);
				typeType();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(702);
				match(VOID);
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

	public static class GenericMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterGenericMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitGenericMethodDeclaration(this);
		}
	}

	public final GenericMethodDeclarationContext genericMethodDeclaration() throws RecognitionException {
		GenericMethodDeclarationContext _localctx = new GenericMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_genericMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(705);
			typeParameters();
			setState(706);
			methodDeclaration();
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

	public static class GenericConstructorDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterGenericConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitGenericConstructorDeclaration(this);
		}
	}

	public final GenericConstructorDeclarationContext genericConstructorDeclaration() throws RecognitionException {
		GenericConstructorDeclarationContext _localctx = new GenericConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_genericConstructorDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(708);
			typeParameters();
			setState(709);
			constructorDeclaration();
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

	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public BlockContext constructorBody;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode THROWS() { return getToken(ProcessingParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitConstructorDeclaration(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711);
			identifier();
			setState(712);
			formalParameters();
			setState(715);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(713);
				match(THROWS);
				setState(714);
				qualifiedNameList();
				}
			}

			setState(717);
			((ConstructorDeclarationContext)_localctx).constructorBody = block();
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

	public static class CompactConstructorDeclarationContext extends ParserRuleContext {
		public BlockContext constructorBody;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public CompactConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compactConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCompactConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCompactConstructorDeclaration(this);
		}
	}

	public final CompactConstructorDeclarationContext compactConstructorDeclaration() throws RecognitionException {
		CompactConstructorDeclarationContext _localctx = new CompactConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_compactConstructorDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(722);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(719);
					modifier();
					}
					} 
				}
				setState(724);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			}
			setState(725);
			identifier();
			setState(726);
			((CompactConstructorDeclarationContext)_localctx).constructorBody = block();
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

	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFieldDeclaration(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_fieldDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			typeType();
			setState(729);
			variableDeclarators();
			setState(730);
			match(SEMI);
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

	public static class InterfaceBodyDeclarationContext extends ParserRuleContext {
		public InterfaceMemberDeclarationContext interfaceMemberDeclaration() {
			return getRuleContext(InterfaceMemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public InterfaceBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceBodyDeclaration(this);
		}
	}

	public final InterfaceBodyDeclarationContext interfaceBodyDeclaration() throws RecognitionException {
		InterfaceBodyDeclarationContext _localctx = new InterfaceBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_interfaceBodyDeclaration);
		try {
			int _alt;
			setState(740);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DEFAULT:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOID:
			case VOLATILE:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case NON_SEALED:
			case LT:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(735);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(732);
						modifier();
						}
						} 
					}
					setState(737);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				}
				setState(738);
				interfaceMemberDeclaration();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(739);
				match(SEMI);
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

	public static class InterfaceMemberDeclarationContext extends ParserRuleContext {
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public ConstDeclarationContext constDeclaration() {
			return getRuleContext(ConstDeclarationContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() {
			return getRuleContext(GenericInterfaceMethodDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceMemberDeclaration(this);
		}
	}

	public final InterfaceMemberDeclarationContext interfaceMemberDeclaration() throws RecognitionException {
		InterfaceMemberDeclarationContext _localctx = new InterfaceMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_interfaceMemberDeclaration);
		try {
			setState(750);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(742);
				recordDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(743);
				constDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(744);
				interfaceMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(745);
				genericInterfaceMethodDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(746);
				interfaceDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(747);
				annotationTypeDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(748);
				classDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(749);
				enumDeclaration();
				}
				break;
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

	public static class ConstDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public List<ConstantDeclaratorContext> constantDeclarator() {
			return getRuleContexts(ConstantDeclaratorContext.class);
		}
		public ConstantDeclaratorContext constantDeclarator(int i) {
			return getRuleContext(ConstantDeclaratorContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public ConstDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterConstDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitConstDeclaration(this);
		}
	}

	public final ConstDeclarationContext constDeclaration() throws RecognitionException {
		ConstDeclarationContext _localctx = new ConstDeclarationContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_constDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(752);
			typeType();
			setState(753);
			constantDeclarator();
			setState(758);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(754);
				match(COMMA);
				setState(755);
				constantDeclarator();
				}
				}
				setState(760);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(761);
			match(SEMI);
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

	public static class ConstantDeclaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ProcessingParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public ConstantDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterConstantDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitConstantDeclarator(this);
		}
	}

	public final ConstantDeclaratorContext constantDeclarator() throws RecognitionException {
		ConstantDeclaratorContext _localctx = new ConstantDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_constantDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(763);
			identifier();
			setState(768);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(764);
				match(LBRACK);
				setState(765);
				match(RBRACK);
				}
				}
				setState(770);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(771);
			match(ASSIGN);
			setState(772);
			variableInitializer();
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

	public static class InterfaceMethodDeclarationContext extends ParserRuleContext {
		public InterfaceCommonBodyDeclarationContext interfaceCommonBodyDeclaration() {
			return getRuleContext(InterfaceCommonBodyDeclarationContext.class,0);
		}
		public List<InterfaceMethodModifierContext> interfaceMethodModifier() {
			return getRuleContexts(InterfaceMethodModifierContext.class);
		}
		public InterfaceMethodModifierContext interfaceMethodModifier(int i) {
			return getRuleContext(InterfaceMethodModifierContext.class,i);
		}
		public InterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceMethodDeclaration(this);
		}
	}

	public final InterfaceMethodDeclarationContext interfaceMethodDeclaration() throws RecognitionException {
		InterfaceMethodDeclarationContext _localctx = new InterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_interfaceMethodDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(777);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(774);
					interfaceMethodModifier();
					}
					} 
				}
				setState(779);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(780);
			interfaceCommonBodyDeclaration();
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

	public static class InterfaceMethodModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(ProcessingParser.PUBLIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(ProcessingParser.ABSTRACT, 0); }
		public TerminalNode DEFAULT() { return getToken(ProcessingParser.DEFAULT, 0); }
		public TerminalNode STATIC() { return getToken(ProcessingParser.STATIC, 0); }
		public TerminalNode STRICTFP() { return getToken(ProcessingParser.STRICTFP, 0); }
		public InterfaceMethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceMethodModifier(this);
		}
	}

	public final InterfaceMethodModifierContext interfaceMethodModifier() throws RecognitionException {
		InterfaceMethodModifierContext _localctx = new InterfaceMethodModifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_interfaceMethodModifier);
		try {
			setState(788);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(782);
				annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(783);
				match(PUBLIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 3);
				{
				setState(784);
				match(ABSTRACT);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 4);
				{
				setState(785);
				match(DEFAULT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(786);
				match(STATIC);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 6);
				{
				setState(787);
				match(STRICTFP);
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

	public static class GenericInterfaceMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public InterfaceCommonBodyDeclarationContext interfaceCommonBodyDeclaration() {
			return getRuleContext(InterfaceCommonBodyDeclarationContext.class,0);
		}
		public List<InterfaceMethodModifierContext> interfaceMethodModifier() {
			return getRuleContexts(InterfaceMethodModifierContext.class);
		}
		public InterfaceMethodModifierContext interfaceMethodModifier(int i) {
			return getRuleContext(InterfaceMethodModifierContext.class,i);
		}
		public GenericInterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericInterfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitGenericInterfaceMethodDeclaration(this);
		}
	}

	public final GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() throws RecognitionException {
		GenericInterfaceMethodDeclarationContext _localctx = new GenericInterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_genericInterfaceMethodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(793);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 7)) & ~0x3f) == 0 && ((1L << (_la - 7)) & ((1L << (ABSTRACT - 7)) | (1L << (DEFAULT - 7)) | (1L << (PUBLIC - 7)) | (1L << (STATIC - 7)) | (1L << (STRICTFP - 7)) | (1L << (MODULE - 7)) | (1L << (OPEN - 7)) | (1L << (REQUIRES - 7)) | (1L << (EXPORTS - 7)) | (1L << (OPENS - 7)) | (1L << (TO - 7)) | (1L << (USES - 7)) | (1L << (PROVIDES - 7)) | (1L << (WITH - 7)) | (1L << (TRANSITIVE - 7)) | (1L << (VAR - 7)) | (1L << (YIELD - 7)) | (1L << (RECORD - 7)) | (1L << (SEALED - 7)))) != 0) || ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (PERMITS - 71)) | (1L << (AT - 71)) | (1L << (IDENTIFIER - 71)))) != 0)) {
				{
				{
				setState(790);
				interfaceMethodModifier();
				}
				}
				setState(795);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(796);
			typeParameters();
			setState(797);
			interfaceCommonBodyDeclaration();
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

	public static class InterfaceCommonBodyDeclarationContext extends ParserRuleContext {
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public TerminalNode THROWS() { return getToken(ProcessingParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public InterfaceCommonBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceCommonBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInterfaceCommonBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInterfaceCommonBodyDeclaration(this);
		}
	}

	public final InterfaceCommonBodyDeclarationContext interfaceCommonBodyDeclaration() throws RecognitionException {
		InterfaceCommonBodyDeclarationContext _localctx = new InterfaceCommonBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_interfaceCommonBodyDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(802);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(799);
					annotation();
					}
					} 
				}
				setState(804);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(805);
			typeTypeOrVoid();
			setState(806);
			identifier();
			setState(807);
			formalParameters();
			setState(812);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(808);
				match(LBRACK);
				setState(809);
				match(RBRACK);
				}
				}
				setState(814);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(817);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(815);
				match(THROWS);
				setState(816);
				qualifiedNameList();
				}
			}

			setState(819);
			methodBody();
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

	public static class VariableDeclaratorsContext extends ParserRuleContext {
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public VariableDeclaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterVariableDeclarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitVariableDeclarators(this);
		}
	}

	public final VariableDeclaratorsContext variableDeclarators() throws RecognitionException {
		VariableDeclaratorsContext _localctx = new VariableDeclaratorsContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_variableDeclarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(821);
			variableDeclarator();
			setState(826);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(822);
				match(COMMA);
				setState(823);
				variableDeclarator();
				}
				}
				setState(828);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class VariableDeclaratorContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ProcessingParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitVariableDeclarator(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_variableDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(829);
			variableDeclaratorId();
			setState(832);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(830);
				match(ASSIGN);
				setState(831);
				variableInitializer();
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

	public static class VariableInitializerContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitVariableInitializer(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_variableInitializer);
		try {
			setState(836);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(834);
				arrayInitializer();
				}
				break;
			case T__0:
			case HexColorLiteral:
			case CHAR_LITERAL:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case SWITCH:
			case THIS:
			case VOID:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case STRING_LITERAL:
			case MULTI_STRING_LIT:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(835);
				expression(0);
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

	public static class ArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitArrayInitializer(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_arrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(838);
			match(LBRACE);
			setState(850);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(839);
				variableInitializer();
				setState(844);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(840);
						match(COMMA);
						setState(841);
						variableInitializer();
						}
						} 
					}
					setState(846);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
				}
				setState(848);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(847);
					match(COMMA);
					}
				}

				}
			}

			setState(852);
			match(RBRACE);
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

	public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(ProcessingParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(ProcessingParser.DOT, i);
		}
		public List<TypeArgumentsContext> typeArguments() {
			return getRuleContexts(TypeArgumentsContext.class);
		}
		public TypeArgumentsContext typeArguments(int i) {
			return getRuleContext(TypeArgumentsContext.class,i);
		}
		public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassOrInterfaceType(this);
		}
	}

	public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
		ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_classOrInterfaceType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(862);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(854);
					identifier();
					setState(856);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(855);
						typeArguments();
						}
					}

					setState(858);
					match(DOT);
					}
					} 
				}
				setState(864);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			}
			setState(865);
			typeIdentifier();
			setState(867);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
			case 1:
				{
				setState(866);
				typeArguments();
				}
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

	public static class TypeArgumentContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(ProcessingParser.QUESTION, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(ProcessingParser.EXTENDS, 0); }
		public TerminalNode SUPER() { return getToken(ProcessingParser.SUPER, 0); }
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeArgument(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_typeArgument);
		int _la;
		try {
			setState(881);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(869);
				typeType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(873);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					{
					setState(870);
					annotation();
					}
					}
					setState(875);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(876);
				match(QUESTION);
				setState(879);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS || _la==SUPER) {
					{
					setState(877);
					_la = _input.LA(1);
					if ( !(_la==EXTENDS || _la==SUPER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(878);
					typeType();
					}
				}

				}
				break;
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

	public static class QualifiedNameListContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public QualifiedNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterQualifiedNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitQualifiedNameList(this);
		}
	}

	public final QualifiedNameListContext qualifiedNameList() throws RecognitionException {
		QualifiedNameListContext _localctx = new QualifiedNameListContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_qualifiedNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(883);
			qualifiedName();
			setState(888);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(884);
				match(COMMA);
				setState(885);
				qualifiedName();
				}
				}
				setState(890);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class FormalParametersContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public ReceiverParameterContext receiverParameter() {
			return getRuleContext(ReceiverParameterContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ProcessingParser.COMMA, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(891);
			match(LPAREN);
			setState(903);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				{
				setState(893);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(892);
					receiverParameter();
					}
				}

				}
				break;
			case 2:
				{
				setState(895);
				receiverParameter();
				setState(898);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(896);
					match(COMMA);
					setState(897);
					formalParameterList();
					}
				}

				}
				break;
			case 3:
				{
				setState(901);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(900);
					formalParameterList();
					}
				}

				}
				break;
			}
			setState(905);
			match(RPAREN);
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

	public static class ReceiverParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode THIS() { return getToken(ProcessingParser.THIS, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(ProcessingParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(ProcessingParser.DOT, i);
		}
		public ReceiverParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterReceiverParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitReceiverParameter(this);
		}
	}

	public final ReceiverParameterContext receiverParameter() throws RecognitionException {
		ReceiverParameterContext _localctx = new ReceiverParameterContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_receiverParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(907);
			typeType();
			setState(913);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(908);
				identifier();
				setState(909);
				match(DOT);
				}
				}
				setState(915);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(916);
			match(THIS);
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

	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public LastFormalParameterContext lastFormalParameter() {
			return getRuleContext(LastFormalParameterContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_formalParameterList);
		int _la;
		try {
			int _alt;
			setState(931);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(918);
				formalParameter();
				setState(923);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(919);
						match(COMMA);
						setState(920);
						formalParameter();
						}
						} 
					}
					setState(925);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				}
				setState(928);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(926);
					match(COMMA);
					setState(927);
					lastFormalParameter();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(930);
				lastFormalParameter();
				}
				break;
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

	public static class FormalParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_formalParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(936);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,109,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(933);
					variableModifier();
					}
					} 
				}
				setState(938);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,109,_ctx);
			}
			setState(939);
			typeType();
			setState(940);
			variableDeclaratorId();
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

	public static class LastFormalParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode ELLIPSIS() { return getToken(ProcessingParser.ELLIPSIS, 0); }
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public LastFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLastFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLastFormalParameter(this);
		}
	}

	public final LastFormalParameterContext lastFormalParameter() throws RecognitionException {
		LastFormalParameterContext _localctx = new LastFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_lastFormalParameter);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(945);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,110,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(942);
					variableModifier();
					}
					} 
				}
				setState(947);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,110,_ctx);
			}
			setState(948);
			typeType();
			setState(952);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(949);
				annotation();
				}
				}
				setState(954);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(955);
			match(ELLIPSIS);
			setState(956);
			variableDeclaratorId();
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

	public static class LambdaLVTIListContext extends ParserRuleContext {
		public List<LambdaLVTIParameterContext> lambdaLVTIParameter() {
			return getRuleContexts(LambdaLVTIParameterContext.class);
		}
		public LambdaLVTIParameterContext lambdaLVTIParameter(int i) {
			return getRuleContext(LambdaLVTIParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public LambdaLVTIListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaLVTIList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLambdaLVTIList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLambdaLVTIList(this);
		}
	}

	public final LambdaLVTIListContext lambdaLVTIList() throws RecognitionException {
		LambdaLVTIListContext _localctx = new LambdaLVTIListContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_lambdaLVTIList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(958);
			lambdaLVTIParameter();
			setState(963);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(959);
				match(COMMA);
				setState(960);
				lambdaLVTIParameter();
				}
				}
				setState(965);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class LambdaLVTIParameterContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(ProcessingParser.VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LambdaLVTIParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaLVTIParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLambdaLVTIParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLambdaLVTIParameter(this);
		}
	}

	public final LambdaLVTIParameterContext lambdaLVTIParameter() throws RecognitionException {
		LambdaLVTIParameterContext _localctx = new LambdaLVTIParameterContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_lambdaLVTIParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(969);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(966);
					variableModifier();
					}
					} 
				}
				setState(971);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			}
			setState(972);
			match(VAR);
			setState(973);
			identifier();
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

	public static class BaseStringLiteralContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(ProcessingParser.STRING_LITERAL, 0); }
		public BaseStringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseStringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterBaseStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitBaseStringLiteral(this);
		}
	}

	public final BaseStringLiteralContext baseStringLiteral() throws RecognitionException {
		BaseStringLiteralContext _localctx = new BaseStringLiteralContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_baseStringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(975);
			match(STRING_LITERAL);
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

	public static class MultilineStringLiteralContext extends ParserRuleContext {
		public TerminalNode MULTI_STRING_LIT() { return getToken(ProcessingParser.MULTI_STRING_LIT, 0); }
		public MultilineStringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multilineStringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMultilineStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMultilineStringLiteral(this);
		}
	}

	public final MultilineStringLiteralContext multilineStringLiteral() throws RecognitionException {
		MultilineStringLiteralContext _localctx = new MultilineStringLiteralContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_multilineStringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(977);
			match(MULTI_STRING_LIT);
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

	public static class StringLiteralContext extends ParserRuleContext {
		public BaseStringLiteralContext baseStringLiteral() {
			return getRuleContext(BaseStringLiteralContext.class,0);
		}
		public MultilineStringLiteralContext multilineStringLiteral() {
			return getRuleContext(MultilineStringLiteralContext.class,0);
		}
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_stringLiteral);
		try {
			setState(981);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(979);
				baseStringLiteral();
				}
				break;
			case MULTI_STRING_LIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(980);
				multilineStringLiteral();
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

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(ProcessingParser.DECIMAL_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(ProcessingParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(ProcessingParser.OCT_LITERAL, 0); }
		public TerminalNode BINARY_LITERAL() { return getToken(ProcessingParser.BINARY_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitIntegerLiteral(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(983);
			_la = _input.LA(1);
			if ( !(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (DECIMAL_LITERAL - 73)) | (1L << (HEX_LITERAL - 73)) | (1L << (OCT_LITERAL - 73)) | (1L << (BINARY_LITERAL - 73)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class FloatLiteralContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(ProcessingParser.FLOAT_LITERAL, 0); }
		public TerminalNode HEX_FLOAT_LITERAL() { return getToken(ProcessingParser.HEX_FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFloatLiteral(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_floatLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(985);
			_la = _input.LA(1);
			if ( !(_la==FLOAT_LITERAL || _la==HEX_FLOAT_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class AltAnnotationQualifiedNameContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(ProcessingParser.AT, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(ProcessingParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(ProcessingParser.DOT, i);
		}
		public AltAnnotationQualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_altAnnotationQualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAltAnnotationQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAltAnnotationQualifiedName(this);
		}
	}

	public final AltAnnotationQualifiedNameContext altAnnotationQualifiedName() throws RecognitionException {
		AltAnnotationQualifiedNameContext _localctx = new AltAnnotationQualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_altAnnotationQualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(992);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(987);
				identifier();
				setState(988);
				match(DOT);
				}
				}
				setState(994);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(995);
			match(AT);
			setState(996);
			identifier();
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

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(ProcessingParser.AT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public AltAnnotationQualifiedNameContext altAnnotationQualifiedName() {
			return getRuleContext(AltAnnotationQualifiedNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public ElementValuePairsContext elementValuePairs() {
			return getRuleContext(ElementValuePairsContext.class,0);
		}
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1001);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				{
				setState(998);
				match(AT);
				setState(999);
				qualifiedName();
				}
				break;
			case 2:
				{
				setState(1000);
				altAnnotationQualifiedName();
				}
				break;
			}
			setState(1009);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(1003);
				match(LPAREN);
				setState(1006);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1004);
					elementValuePairs();
					}
					break;
				case 2:
					{
					setState(1005);
					elementValue();
					}
					break;
				}
				setState(1008);
				match(RPAREN);
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

	public static class ElementValuePairsContext extends ParserRuleContext {
		public List<ElementValuePairContext> elementValuePair() {
			return getRuleContexts(ElementValuePairContext.class);
		}
		public ElementValuePairContext elementValuePair(int i) {
			return getRuleContext(ElementValuePairContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public ElementValuePairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterElementValuePairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitElementValuePairs(this);
		}
	}

	public final ElementValuePairsContext elementValuePairs() throws RecognitionException {
		ElementValuePairsContext _localctx = new ElementValuePairsContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_elementValuePairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1011);
			elementValuePair();
			setState(1016);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1012);
				match(COMMA);
				setState(1013);
				elementValuePair();
				}
				}
				setState(1018);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ElementValuePairContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ProcessingParser.ASSIGN, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterElementValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitElementValuePair(this);
		}
	}

	public final ElementValuePairContext elementValuePair() throws RecognitionException {
		ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_elementValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1019);
			identifier();
			setState(1020);
			match(ASSIGN);
			setState(1021);
			elementValue();
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

	public static class ElementValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ElementValueArrayInitializerContext elementValueArrayInitializer() {
			return getRuleContext(ElementValueArrayInitializerContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitElementValue(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_elementValue);
		try {
			setState(1026);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1023);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1024);
				annotation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1025);
				elementValueArrayInitializer();
				}
				break;
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

	public static class ElementValueArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<ElementValueContext> elementValue() {
			return getRuleContexts(ElementValueContext.class);
		}
		public ElementValueContext elementValue(int i) {
			return getRuleContext(ElementValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueArrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterElementValueArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitElementValueArrayInitializer(this);
		}
	}

	public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
		ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_elementValueArrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1028);
			match(LBRACE);
			setState(1037);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1029);
				elementValue();
				setState(1034);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,121,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1030);
						match(COMMA);
						setState(1031);
						elementValue();
						}
						} 
					}
					setState(1036);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,121,_ctx);
				}
				}
			}

			setState(1040);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1039);
				match(COMMA);
				}
			}

			setState(1042);
			match(RBRACE);
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

	public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(ProcessingParser.AT, 0); }
		public TerminalNode INTERFACE() { return getToken(ProcessingParser.INTERFACE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AnnotationTypeBodyContext annotationTypeBody() {
			return getRuleContext(AnnotationTypeBodyContext.class,0);
		}
		public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationTypeDeclaration(this);
		}
	}

	public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
		AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_annotationTypeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1044);
			match(AT);
			setState(1045);
			match(INTERFACE);
			setState(1046);
			identifier();
			setState(1047);
			annotationTypeBody();
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

	public static class AnnotationTypeBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<AnnotationTypeElementDeclarationContext> annotationTypeElementDeclaration() {
			return getRuleContexts(AnnotationTypeElementDeclarationContext.class);
		}
		public AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration(int i) {
			return getRuleContext(AnnotationTypeElementDeclarationContext.class,i);
		}
		public AnnotationTypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationTypeBody(this);
		}
	}

	public final AnnotationTypeBodyContext annotationTypeBody() throws RecognitionException {
		AnnotationTypeBodyContext _localctx = new AnnotationTypeBodyContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_annotationTypeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1049);
			match(LBRACE);
			setState(1053);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (SEMI - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(1050);
				annotationTypeElementDeclaration();
				}
				}
				setState(1055);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1056);
			match(RBRACE);
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

	public static class AnnotationTypeElementDeclarationContext extends ParserRuleContext {
		public AnnotationTypeElementRestContext annotationTypeElementRest() {
			return getRuleContext(AnnotationTypeElementRestContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public AnnotationTypeElementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationTypeElementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationTypeElementDeclaration(this);
		}
	}

	public final AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() throws RecognitionException {
		AnnotationTypeElementDeclarationContext _localctx = new AnnotationTypeElementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_annotationTypeElementDeclaration);
		try {
			int _alt;
			setState(1066);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case NON_SEALED:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1061);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1058);
						modifier();
						}
						} 
					}
					setState(1063);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
				}
				setState(1064);
				annotationTypeElementRest();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(1065);
				match(SEMI);
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

	public static class AnnotationTypeElementRestContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() {
			return getRuleContext(AnnotationMethodOrConstantRestContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public AnnotationTypeElementRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationTypeElementRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationTypeElementRest(this);
		}
	}

	public final AnnotationTypeElementRestContext annotationTypeElementRest() throws RecognitionException {
		AnnotationTypeElementRestContext _localctx = new AnnotationTypeElementRestContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_annotationTypeElementRest);
		try {
			setState(1092);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,132,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1068);
				typeType();
				setState(1069);
				annotationMethodOrConstantRest();
				setState(1070);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1072);
				classDeclaration();
				setState(1074);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
				case 1:
					{
					setState(1073);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1076);
				interfaceDeclaration();
				setState(1078);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,128,_ctx) ) {
				case 1:
					{
					setState(1077);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1080);
				enumDeclaration();
				setState(1082);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
				case 1:
					{
					setState(1081);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1084);
				annotationTypeDeclaration();
				setState(1086);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
				case 1:
					{
					setState(1085);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1088);
				recordDeclaration();
				setState(1090);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
				case 1:
					{
					setState(1089);
					match(SEMI);
					}
					break;
				}
				}
				break;
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

	public static class AnnotationMethodOrConstantRestContext extends ParserRuleContext {
		public AnnotationMethodRestContext annotationMethodRest() {
			return getRuleContext(AnnotationMethodRestContext.class,0);
		}
		public AnnotationConstantRestContext annotationConstantRest() {
			return getRuleContext(AnnotationConstantRestContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodOrConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationMethodOrConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationMethodOrConstantRest(this);
		}
	}

	public final AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() throws RecognitionException {
		AnnotationMethodOrConstantRestContext _localctx = new AnnotationMethodOrConstantRestContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_annotationMethodOrConstantRest);
		try {
			setState(1096);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1094);
				annotationMethodRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1095);
				annotationConstantRest();
				}
				break;
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

	public static class AnnotationMethodRestContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public AnnotationMethodRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationMethodRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationMethodRest(this);
		}
	}

	public final AnnotationMethodRestContext annotationMethodRest() throws RecognitionException {
		AnnotationMethodRestContext _localctx = new AnnotationMethodRestContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_annotationMethodRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1098);
			identifier();
			setState(1099);
			match(LPAREN);
			setState(1100);
			match(RPAREN);
			setState(1102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1101);
				defaultValue();
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

	public static class AnnotationConstantRestContext extends ParserRuleContext {
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public AnnotationConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterAnnotationConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitAnnotationConstantRest(this);
		}
	}

	public final AnnotationConstantRestContext annotationConstantRest() throws RecognitionException {
		AnnotationConstantRestContext _localctx = new AnnotationConstantRestContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_annotationConstantRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1104);
			variableDeclarators();
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

	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(ProcessingParser.DEFAULT, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1106);
			match(DEFAULT);
			setState(1107);
			elementValue();
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

	public static class ModuleDeclarationContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(ProcessingParser.MODULE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ModuleBodyContext moduleBody() {
			return getRuleContext(ModuleBodyContext.class,0);
		}
		public TerminalNode OPEN() { return getToken(ProcessingParser.OPEN, 0); }
		public ModuleDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterModuleDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitModuleDeclaration(this);
		}
	}

	public final ModuleDeclarationContext moduleDeclaration() throws RecognitionException {
		ModuleDeclarationContext _localctx = new ModuleDeclarationContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_moduleDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN) {
				{
				setState(1109);
				match(OPEN);
				}
			}

			setState(1112);
			match(MODULE);
			setState(1113);
			qualifiedName();
			setState(1114);
			moduleBody();
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

	public static class ModuleBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<ModuleDirectiveContext> moduleDirective() {
			return getRuleContexts(ModuleDirectiveContext.class);
		}
		public ModuleDirectiveContext moduleDirective(int i) {
			return getRuleContext(ModuleDirectiveContext.class,i);
		}
		public ModuleBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterModuleBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitModuleBody(this);
		}
	}

	public final ModuleBodyContext moduleBody() throws RecognitionException {
		ModuleBodyContext _localctx = new ModuleBodyContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_moduleBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1116);
			match(LBRACE);
			setState(1120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & ((1L << (REQUIRES - 59)) | (1L << (EXPORTS - 59)) | (1L << (OPENS - 59)) | (1L << (USES - 59)) | (1L << (PROVIDES - 59)))) != 0)) {
				{
				{
				setState(1117);
				moduleDirective();
				}
				}
				setState(1122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1123);
			match(RBRACE);
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

	public static class ModuleDirectiveContext extends ParserRuleContext {
		public TerminalNode REQUIRES() { return getToken(ProcessingParser.REQUIRES, 0); }
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public List<RequiresModifierContext> requiresModifier() {
			return getRuleContexts(RequiresModifierContext.class);
		}
		public RequiresModifierContext requiresModifier(int i) {
			return getRuleContext(RequiresModifierContext.class,i);
		}
		public TerminalNode EXPORTS() { return getToken(ProcessingParser.EXPORTS, 0); }
		public TerminalNode TO() { return getToken(ProcessingParser.TO, 0); }
		public TerminalNode OPENS() { return getToken(ProcessingParser.OPENS, 0); }
		public TerminalNode USES() { return getToken(ProcessingParser.USES, 0); }
		public TerminalNode PROVIDES() { return getToken(ProcessingParser.PROVIDES, 0); }
		public TerminalNode WITH() { return getToken(ProcessingParser.WITH, 0); }
		public ModuleDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterModuleDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitModuleDirective(this);
		}
	}

	public final ModuleDirectiveContext moduleDirective() throws RecognitionException {
		ModuleDirectiveContext _localctx = new ModuleDirectiveContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_moduleDirective);
		int _la;
		try {
			setState(1161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIRES:
				enterOuterAlt(_localctx, 1);
				{
				setState(1125);
				match(REQUIRES);
				setState(1129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STATIC || _la==TRANSITIVE) {
					{
					{
					setState(1126);
					requiresModifier();
					}
					}
					setState(1131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1132);
				qualifiedName();
				setState(1133);
				match(SEMI);
				}
				break;
			case EXPORTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(1135);
				match(EXPORTS);
				setState(1136);
				qualifiedName();
				setState(1139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1137);
					match(TO);
					setState(1138);
					qualifiedName();
					}
				}

				setState(1141);
				match(SEMI);
				}
				break;
			case OPENS:
				enterOuterAlt(_localctx, 3);
				{
				setState(1143);
				match(OPENS);
				setState(1144);
				qualifiedName();
				setState(1147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1145);
					match(TO);
					setState(1146);
					qualifiedName();
					}
				}

				setState(1149);
				match(SEMI);
				}
				break;
			case USES:
				enterOuterAlt(_localctx, 4);
				{
				setState(1151);
				match(USES);
				setState(1152);
				qualifiedName();
				setState(1153);
				match(SEMI);
				}
				break;
			case PROVIDES:
				enterOuterAlt(_localctx, 5);
				{
				setState(1155);
				match(PROVIDES);
				setState(1156);
				qualifiedName();
				setState(1157);
				match(WITH);
				setState(1158);
				qualifiedName();
				setState(1159);
				match(SEMI);
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

	public static class RequiresModifierContext extends ParserRuleContext {
		public TerminalNode TRANSITIVE() { return getToken(ProcessingParser.TRANSITIVE, 0); }
		public TerminalNode STATIC() { return getToken(ProcessingParser.STATIC, 0); }
		public RequiresModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requiresModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterRequiresModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitRequiresModifier(this);
		}
	}

	public final RequiresModifierContext requiresModifier() throws RecognitionException {
		RequiresModifierContext _localctx = new RequiresModifierContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_requiresModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1163);
			_la = _input.LA(1);
			if ( !(_la==STATIC || _la==TRANSITIVE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class RecordDeclarationContext extends ParserRuleContext {
		public TerminalNode RECORD() { return getToken(ProcessingParser.RECORD, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public RecordHeaderContext recordHeader() {
			return getRuleContext(RecordHeaderContext.class,0);
		}
		public RecordBodyContext recordBody() {
			return getRuleContext(RecordBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(ProcessingParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public RecordDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterRecordDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitRecordDeclaration(this);
		}
	}

	public final RecordDeclarationContext recordDeclaration() throws RecognitionException {
		RecordDeclarationContext _localctx = new RecordDeclarationContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_recordDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1165);
			match(RECORD);
			setState(1166);
			identifier();
			setState(1168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1167);
				typeParameters();
				}
			}

			setState(1170);
			recordHeader();
			setState(1173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1171);
				match(IMPLEMENTS);
				setState(1172);
				typeList();
				}
			}

			setState(1175);
			recordBody();
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

	public static class RecordHeaderContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public RecordComponentListContext recordComponentList() {
			return getRuleContext(RecordComponentListContext.class,0);
		}
		public RecordHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterRecordHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitRecordHeader(this);
		}
	}

	public final RecordHeaderContext recordHeader() throws RecognitionException {
		RecordHeaderContext _localctx = new RecordHeaderContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_recordHeader);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1177);
			match(LPAREN);
			setState(1179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1178);
				recordComponentList();
				}
			}

			setState(1181);
			match(RPAREN);
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

	public static class RecordComponentListContext extends ParserRuleContext {
		public List<RecordComponentContext> recordComponent() {
			return getRuleContexts(RecordComponentContext.class);
		}
		public RecordComponentContext recordComponent(int i) {
			return getRuleContext(RecordComponentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public RecordComponentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordComponentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterRecordComponentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitRecordComponentList(this);
		}
	}

	public final RecordComponentListContext recordComponentList() throws RecognitionException {
		RecordComponentListContext _localctx = new RecordComponentListContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_recordComponentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1183);
			recordComponent();
			setState(1188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1184);
				match(COMMA);
				setState(1185);
				recordComponent();
				}
				}
				setState(1190);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class RecordComponentContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public RecordComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordComponent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterRecordComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitRecordComponent(this);
		}
	}

	public final RecordComponentContext recordComponent() throws RecognitionException {
		RecordComponentContext _localctx = new RecordComponentContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_recordComponent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1191);
			typeType();
			setState(1192);
			identifier();
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

	public static class RecordBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<CompactConstructorDeclarationContext> compactConstructorDeclaration() {
			return getRuleContexts(CompactConstructorDeclarationContext.class);
		}
		public CompactConstructorDeclarationContext compactConstructorDeclaration(int i) {
			return getRuleContext(CompactConstructorDeclarationContext.class,i);
		}
		public RecordBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterRecordBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitRecordBody(this);
		}
	}

	public final RecordBodyContext recordBody() throws RecognitionException {
		RecordBodyContext _localctx = new RecordBodyContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_recordBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1194);
			match(LBRACE);
			setState(1199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1197);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,145,_ctx) ) {
				case 1:
					{
					setState(1195);
					classBodyDeclaration();
					}
					break;
				case 2:
					{
					setState(1196);
					compactConstructorDeclaration();
					}
					break;
				}
				}
				setState(1201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1202);
			match(RBRACE);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1204);
			match(LBRACE);
			setState(1208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				{
				setState(1205);
				blockStatement();
				}
				}
				setState(1210);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1211);
			match(RBRACE);
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

	public static class BlockStatementContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public LocalTypeDeclarationContext localTypeDeclaration() {
			return getRuleContext(LocalTypeDeclarationContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitBlockStatement(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_blockStatement);
		try {
			setState(1218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,148,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1213);
				localVariableDeclaration();
				setState(1214);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1216);
				localTypeDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1217);
				statement();
				}
				break;
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

	public static class LocalVariableDeclarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(ProcessingParser.VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ProcessingParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLocalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLocalVariableDeclaration(this);
		}
	}

	public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
		LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_localVariableDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1223);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,149,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1220);
					variableModifier();
					}
					} 
				}
				setState(1225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,149,_ctx);
			}
			setState(1234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,150,_ctx) ) {
			case 1:
				{
				setState(1226);
				match(VAR);
				setState(1227);
				identifier();
				setState(1228);
				match(ASSIGN);
				setState(1229);
				expression(0);
				}
				break;
			case 2:
				{
				setState(1231);
				typeType();
				setState(1232);
				variableDeclarators();
				}
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProcessingParser.IDENTIFIER, 0); }
		public TerminalNode MODULE() { return getToken(ProcessingParser.MODULE, 0); }
		public TerminalNode OPEN() { return getToken(ProcessingParser.OPEN, 0); }
		public TerminalNode REQUIRES() { return getToken(ProcessingParser.REQUIRES, 0); }
		public TerminalNode EXPORTS() { return getToken(ProcessingParser.EXPORTS, 0); }
		public TerminalNode OPENS() { return getToken(ProcessingParser.OPENS, 0); }
		public TerminalNode TO() { return getToken(ProcessingParser.TO, 0); }
		public TerminalNode USES() { return getToken(ProcessingParser.USES, 0); }
		public TerminalNode PROVIDES() { return getToken(ProcessingParser.PROVIDES, 0); }
		public TerminalNode WITH() { return getToken(ProcessingParser.WITH, 0); }
		public TerminalNode TRANSITIVE() { return getToken(ProcessingParser.TRANSITIVE, 0); }
		public TerminalNode YIELD() { return getToken(ProcessingParser.YIELD, 0); }
		public TerminalNode SEALED() { return getToken(ProcessingParser.SEALED, 0); }
		public TerminalNode PERMITS() { return getToken(ProcessingParser.PERMITS, 0); }
		public TerminalNode RECORD() { return getToken(ProcessingParser.RECORD, 0); }
		public TerminalNode VAR() { return getToken(ProcessingParser.VAR, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1236);
			_la = _input.LA(1);
			if ( !(((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class TypeIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ProcessingParser.IDENTIFIER, 0); }
		public TerminalNode MODULE() { return getToken(ProcessingParser.MODULE, 0); }
		public TerminalNode OPEN() { return getToken(ProcessingParser.OPEN, 0); }
		public TerminalNode REQUIRES() { return getToken(ProcessingParser.REQUIRES, 0); }
		public TerminalNode EXPORTS() { return getToken(ProcessingParser.EXPORTS, 0); }
		public TerminalNode OPENS() { return getToken(ProcessingParser.OPENS, 0); }
		public TerminalNode TO() { return getToken(ProcessingParser.TO, 0); }
		public TerminalNode USES() { return getToken(ProcessingParser.USES, 0); }
		public TerminalNode PROVIDES() { return getToken(ProcessingParser.PROVIDES, 0); }
		public TerminalNode WITH() { return getToken(ProcessingParser.WITH, 0); }
		public TerminalNode TRANSITIVE() { return getToken(ProcessingParser.TRANSITIVE, 0); }
		public TerminalNode SEALED() { return getToken(ProcessingParser.SEALED, 0); }
		public TerminalNode PERMITS() { return getToken(ProcessingParser.PERMITS, 0); }
		public TerminalNode RECORD() { return getToken(ProcessingParser.RECORD, 0); }
		public TypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeIdentifier(this);
		}
	}

	public final TypeIdentifierContext typeIdentifier() throws RecognitionException {
		TypeIdentifierContext _localctx = new TypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_typeIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1238);
			_la = _input.LA(1);
			if ( !(((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class LocalTypeDeclarationContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public RecordDeclarationContext recordDeclaration() {
			return getRuleContext(RecordDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public LocalTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLocalTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLocalTypeDeclaration(this);
		}
	}

	public final LocalTypeDeclarationContext localTypeDeclaration() throws RecognitionException {
		LocalTypeDeclarationContext _localctx = new LocalTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_localTypeDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,151,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1240);
					classOrInterfaceModifier();
					}
					} 
				}
				setState(1245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,151,_ctx);
			}
			setState(1249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(1246);
				classDeclaration();
				}
				break;
			case INTERFACE:
				{
				setState(1247);
				interfaceDeclaration();
				}
				break;
			case RECORD:
				{
				setState(1248);
				recordDeclaration();
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

	public static class StatementContext extends ParserRuleContext {
		public BlockContext blockLabel;
		public ExpressionContext statementExpression;
		public IdentifierContext identifierLabel;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(ProcessingParser.ASSERT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public TerminalNode COLON() { return getToken(ProcessingParser.COLON, 0); }
		public TerminalNode IF() { return getToken(ProcessingParser.IF, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(ProcessingParser.ELSE, 0); }
		public TerminalNode FOR() { return getToken(ProcessingParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public ForControlContext forControl() {
			return getRuleContext(ForControlContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public TerminalNode WHILE() { return getToken(ProcessingParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(ProcessingParser.DO, 0); }
		public TerminalNode TRY() { return getToken(ProcessingParser.TRY, 0); }
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public ResourceSpecificationContext resourceSpecification() {
			return getRuleContext(ResourceSpecificationContext.class,0);
		}
		public TerminalNode SWITCH() { return getToken(ProcessingParser.SWITCH, 0); }
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public TerminalNode SYNCHRONIZED() { return getToken(ProcessingParser.SYNCHRONIZED, 0); }
		public TerminalNode RETURN() { return getToken(ProcessingParser.RETURN, 0); }
		public TerminalNode THROW() { return getToken(ProcessingParser.THROW, 0); }
		public TerminalNode BREAK() { return getToken(ProcessingParser.BREAK, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode CONTINUE() { return getToken(ProcessingParser.CONTINUE, 0); }
		public TerminalNode YIELD() { return getToken(ProcessingParser.YIELD, 0); }
		public SwitchExpressionContext switchExpression() {
			return getRuleContext(SwitchExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(1364);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1251);
				((StatementContext)_localctx).blockLabel = block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1252);
				match(ASSERT);
				setState(1253);
				expression(0);
				setState(1256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1254);
					match(COLON);
					setState(1255);
					expression(0);
					}
				}

				setState(1258);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1260);
				match(IF);
				setState(1261);
				parExpression();
				setState(1262);
				statement();
				setState(1265);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,154,_ctx) ) {
				case 1:
					{
					setState(1263);
					match(ELSE);
					setState(1264);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1267);
				match(FOR);
				setState(1268);
				match(LPAREN);
				setState(1269);
				forControl();
				setState(1270);
				match(RPAREN);
				setState(1271);
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1273);
				match(WHILE);
				setState(1274);
				parExpression();
				setState(1275);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1277);
				match(DO);
				setState(1278);
				statement();
				setState(1279);
				match(WHILE);
				setState(1280);
				parExpression();
				setState(1281);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1283);
				match(TRY);
				setState(1284);
				block();
				setState(1294);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(1286); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(1285);
						catchClause();
						}
						}
						setState(1288); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==CATCH );
					setState(1291);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(1290);
						finallyBlock();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(1293);
					finallyBlock();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1296);
				match(TRY);
				setState(1297);
				resourceSpecification();
				setState(1298);
				block();
				setState(1302);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(1299);
					catchClause();
					}
					}
					setState(1304);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1306);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(1305);
					finallyBlock();
					}
				}

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1308);
				match(SWITCH);
				setState(1309);
				parExpression();
				setState(1310);
				match(LBRACE);
				setState(1314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,160,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1311);
						switchBlockStatementGroup();
						}
						} 
					}
					setState(1316);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,160,_ctx);
				}
				setState(1320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(1317);
					switchLabel();
					}
					}
					setState(1322);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1323);
				match(RBRACE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1325);
				match(SYNCHRONIZED);
				setState(1326);
				parExpression();
				setState(1327);
				block();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1329);
				match(RETURN);
				setState(1331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1330);
					expression(0);
					}
				}

				setState(1333);
				match(SEMI);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1334);
				match(THROW);
				setState(1335);
				expression(0);
				setState(1336);
				match(SEMI);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1338);
				match(BREAK);
				setState(1340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==IDENTIFIER) {
					{
					setState(1339);
					identifier();
					}
				}

				setState(1342);
				match(SEMI);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1343);
				match(CONTINUE);
				setState(1345);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==IDENTIFIER) {
					{
					setState(1344);
					identifier();
					}
				}

				setState(1347);
				match(SEMI);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1348);
				match(YIELD);
				setState(1349);
				expression(0);
				setState(1350);
				match(SEMI);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1352);
				match(SEMI);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1353);
				((StatementContext)_localctx).statementExpression = expression(0);
				setState(1354);
				match(SEMI);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(1356);
				switchExpression();
				setState(1358);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,165,_ctx) ) {
				case 1:
					{
					setState(1357);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(1360);
				((StatementContext)_localctx).identifierLabel = identifier();
				setState(1361);
				match(COLON);
				setState(1362);
				statement();
				}
				break;
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

	public static class CatchClauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(ProcessingParser.CATCH, 0); }
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public CatchTypeContext catchType() {
			return getRuleContext(CatchTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCatchClause(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_catchClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1366);
			match(CATCH);
			setState(1367);
			match(LPAREN);
			setState(1371);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,167,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1368);
					variableModifier();
					}
					} 
				}
				setState(1373);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,167,_ctx);
			}
			setState(1374);
			catchType();
			setState(1375);
			identifier();
			setState(1376);
			match(RPAREN);
			setState(1377);
			block();
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

	public static class CatchTypeContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<TerminalNode> BITOR() { return getTokens(ProcessingParser.BITOR); }
		public TerminalNode BITOR(int i) {
			return getToken(ProcessingParser.BITOR, i);
		}
		public CatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCatchType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCatchType(this);
		}
	}

	public final CatchTypeContext catchType() throws RecognitionException {
		CatchTypeContext _localctx = new CatchTypeContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_catchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1379);
			qualifiedName();
			setState(1384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(1380);
				match(BITOR);
				setState(1381);
				qualifiedName();
				}
				}
				setState(1386);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class FinallyBlockContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(ProcessingParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterFinallyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitFinallyBlock(this);
		}
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1387);
			match(FINALLY);
			setState(1388);
			block();
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

	public static class ResourceSpecificationContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public ResourcesContext resources() {
			return getRuleContext(ResourcesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(ProcessingParser.SEMI, 0); }
		public ResourceSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterResourceSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitResourceSpecification(this);
		}
	}

	public final ResourceSpecificationContext resourceSpecification() throws RecognitionException {
		ResourceSpecificationContext _localctx = new ResourceSpecificationContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_resourceSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1390);
			match(LPAREN);
			setState(1391);
			resources();
			setState(1393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1392);
				match(SEMI);
				}
			}

			setState(1395);
			match(RPAREN);
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

	public static class ResourcesContext extends ParserRuleContext {
		public List<ResourceContext> resource() {
			return getRuleContexts(ResourceContext.class);
		}
		public ResourceContext resource(int i) {
			return getRuleContext(ResourceContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(ProcessingParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(ProcessingParser.SEMI, i);
		}
		public ResourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resources; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterResources(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitResources(this);
		}
	}

	public final ResourcesContext resources() throws RecognitionException {
		ResourcesContext _localctx = new ResourcesContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_resources);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1397);
			resource();
			setState(1402);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,170,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1398);
					match(SEMI);
					setState(1399);
					resource();
					}
					} 
				}
				setState(1404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,170,_ctx);
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

	public static class ResourceContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(ProcessingParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode VAR() { return getToken(ProcessingParser.VAR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_resource);
		try {
			int _alt;
			setState(1422);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,173,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1408);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,171,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1405);
						variableModifier();
						}
						} 
					}
					setState(1410);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,171,_ctx);
				}
				setState(1416);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,172,_ctx) ) {
				case 1:
					{
					setState(1411);
					classOrInterfaceType();
					setState(1412);
					variableDeclaratorId();
					}
					break;
				case 2:
					{
					setState(1414);
					match(VAR);
					setState(1415);
					identifier();
					}
					break;
				}
				setState(1418);
				match(ASSIGN);
				setState(1419);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1421);
				qualifiedName();
				}
				break;
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

	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSwitchBlockStatementGroup(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_switchBlockStatementGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1425); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1424);
				switchLabel();
				}
				}
				setState(1427); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE || _la==DEFAULT );
			setState(1430); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1429);
				blockStatement();
				}
				}
				setState(1432); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER );
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

	public static class SwitchLabelContext extends ParserRuleContext {
		public ExpressionContext constantExpression;
		public Token enumConstantName;
		public IdentifierContext varName;
		public TerminalNode CASE() { return getToken(ProcessingParser.CASE, 0); }
		public TerminalNode COLON() { return getToken(ProcessingParser.COLON, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ProcessingParser.IDENTIFIER, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(ProcessingParser.DEFAULT, 0); }
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSwitchLabel(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_switchLabel);
		try {
			setState(1445);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1434);
				match(CASE);
				setState(1440);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,176,_ctx) ) {
				case 1:
					{
					setState(1435);
					((SwitchLabelContext)_localctx).constantExpression = expression(0);
					}
					break;
				case 2:
					{
					setState(1436);
					((SwitchLabelContext)_localctx).enumConstantName = match(IDENTIFIER);
					}
					break;
				case 3:
					{
					setState(1437);
					typeType();
					setState(1438);
					((SwitchLabelContext)_localctx).varName = identifier();
					}
					break;
				}
				setState(1442);
				match(COLON);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1443);
				match(DEFAULT);
				setState(1444);
				match(COLON);
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

	public static class ForControlContext extends ParserRuleContext {
		public ExpressionListContext forUpdate;
		public EnhancedForControlContext enhancedForControl() {
			return getRuleContext(EnhancedForControlContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(ProcessingParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(ProcessingParser.SEMI, i);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitForControl(this);
		}
	}

	public final ForControlContext forControl() throws RecognitionException {
		ForControlContext _localctx = new ForControlContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_forControl);
		int _la;
		try {
			setState(1459);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1447);
				enhancedForControl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1449);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1448);
					forInit();
					}
				}

				setState(1451);
				match(SEMI);
				setState(1453);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1452);
					expression(0);
					}
				}

				setState(1455);
				match(SEMI);
				setState(1457);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1456);
					((ForControlContext)_localctx).forUpdate = expressionList();
					}
				}

				}
				break;
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

	public static class ForInitContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitForInit(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_forInit);
		try {
			setState(1463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,182,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1461);
				localVariableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1462);
				expressionList();
				}
				break;
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

	public static class EnhancedForControlContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ProcessingParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode VAR() { return getToken(ProcessingParser.VAR, 0); }
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public EnhancedForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterEnhancedForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitEnhancedForControl(this);
		}
	}

	public final EnhancedForControlContext enhancedForControl() throws RecognitionException {
		EnhancedForControlContext _localctx = new EnhancedForControlContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_enhancedForControl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1468);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1465);
					variableModifier();
					}
					} 
				}
				setState(1470);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
			}
			setState(1473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,184,_ctx) ) {
			case 1:
				{
				setState(1471);
				typeType();
				}
				break;
			case 2:
				{
				setState(1472);
				match(VAR);
				}
				break;
			}
			setState(1475);
			variableDeclaratorId();
			setState(1476);
			match(COLON);
			setState(1477);
			expression(0);
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

	public static class ParExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitParExpression(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1479);
			match(LPAREN);
			setState(1480);
			expression(0);
			setState(1481);
			match(RPAREN);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1483);
			expression(0);
			setState(1488);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1484);
				match(COMMA);
				setState(1485);
				expression(0);
				}
				}
				setState(1490);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TernaryExpressionContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(ProcessingParser.COLON, 0); }
		public TerminalNode QUESTION() { return getToken(ProcessingParser.QUESTION, 0); }
		public TernaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTernaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTernaryExpression(this);
		}
	}
	public static class InstanceOfOperatorExpressionContext extends ExpressionContext {
		public Token bop;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INSTANCEOF() { return getToken(ProcessingParser.INSTANCEOF, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public InstanceOfOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInstanceOfOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInstanceOfOperatorExpression(this);
		}
	}
	public static class UnaryOperatorExpressionContext extends ExpressionContext {
		public Token prefix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ADD() { return getToken(ProcessingParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ProcessingParser.SUB, 0); }
		public TerminalNode INC() { return getToken(ProcessingParser.INC, 0); }
		public TerminalNode DEC() { return getToken(ProcessingParser.DEC, 0); }
		public TerminalNode TILDE() { return getToken(ProcessingParser.TILDE, 0); }
		public TerminalNode BANG() { return getToken(ProcessingParser.BANG, 0); }
		public UnaryOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterUnaryOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitUnaryOperatorExpression(this);
		}
	}
	public static class PrimaryExpressionContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitPrimaryExpression(this);
		}
	}
	public static class ObjectCreationExpressionContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(ProcessingParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public ObjectCreationExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterObjectCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitObjectCreationExpression(this);
		}
	}
	public static class ExpressionLambdaContext extends ExpressionContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public ExpressionLambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterExpressionLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitExpressionLambda(this);
		}
	}
	public static class PostIncrementDecrementOperatorExpressionContext extends ExpressionContext {
		public Token postfix;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode INC() { return getToken(ProcessingParser.INC, 0); }
		public TerminalNode DEC() { return getToken(ProcessingParser.DEC, 0); }
		public PostIncrementDecrementOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterPostIncrementDecrementOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitPostIncrementDecrementOperatorExpression(this);
		}
	}
	public static class MemberReferenceExpressionContext extends ExpressionContext {
		public Token bop;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ProcessingParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public TerminalNode THIS() { return getToken(ProcessingParser.THIS, 0); }
		public TerminalNode NEW() { return getToken(ProcessingParser.NEW, 0); }
		public InnerCreatorContext innerCreator() {
			return getRuleContext(InnerCreatorContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(ProcessingParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext explicitGenericInvocation() {
			return getRuleContext(ExplicitGenericInvocationContext.class,0);
		}
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public MemberReferenceExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMemberReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMemberReferenceExpression(this);
		}
	}
	public static class ExpressionSwitchContext extends ExpressionContext {
		public SwitchExpressionContext switchExpression() {
			return getRuleContext(SwitchExpressionContext.class,0);
		}
		public ExpressionSwitchContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterExpressionSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitExpressionSwitch(this);
		}
	}
	public static class BinaryOperatorExpressionContext extends ExpressionContext {
		public Token bop;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(ProcessingParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(ProcessingParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ProcessingParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(ProcessingParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ProcessingParser.SUB, 0); }
		public List<TerminalNode> LT() { return getTokens(ProcessingParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(ProcessingParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(ProcessingParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(ProcessingParser.GT, i);
		}
		public TerminalNode LE() { return getToken(ProcessingParser.LE, 0); }
		public TerminalNode GE() { return getToken(ProcessingParser.GE, 0); }
		public TerminalNode EQUAL() { return getToken(ProcessingParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(ProcessingParser.NOTEQUAL, 0); }
		public TerminalNode BITAND() { return getToken(ProcessingParser.BITAND, 0); }
		public TerminalNode CARET() { return getToken(ProcessingParser.CARET, 0); }
		public TerminalNode BITOR() { return getToken(ProcessingParser.BITOR, 0); }
		public TerminalNode AND() { return getToken(ProcessingParser.AND, 0); }
		public TerminalNode OR() { return getToken(ProcessingParser.OR, 0); }
		public TerminalNode ASSIGN() { return getToken(ProcessingParser.ASSIGN, 0); }
		public TerminalNode ADD_ASSIGN() { return getToken(ProcessingParser.ADD_ASSIGN, 0); }
		public TerminalNode SUB_ASSIGN() { return getToken(ProcessingParser.SUB_ASSIGN, 0); }
		public TerminalNode MUL_ASSIGN() { return getToken(ProcessingParser.MUL_ASSIGN, 0); }
		public TerminalNode DIV_ASSIGN() { return getToken(ProcessingParser.DIV_ASSIGN, 0); }
		public TerminalNode AND_ASSIGN() { return getToken(ProcessingParser.AND_ASSIGN, 0); }
		public TerminalNode OR_ASSIGN() { return getToken(ProcessingParser.OR_ASSIGN, 0); }
		public TerminalNode XOR_ASSIGN() { return getToken(ProcessingParser.XOR_ASSIGN, 0); }
		public TerminalNode RSHIFT_ASSIGN() { return getToken(ProcessingParser.RSHIFT_ASSIGN, 0); }
		public TerminalNode URSHIFT_ASSIGN() { return getToken(ProcessingParser.URSHIFT_ASSIGN, 0); }
		public TerminalNode LSHIFT_ASSIGN() { return getToken(ProcessingParser.LSHIFT_ASSIGN, 0); }
		public TerminalNode MOD_ASSIGN() { return getToken(ProcessingParser.MOD_ASSIGN, 0); }
		public BinaryOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterBinaryOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitBinaryOperatorExpression(this);
		}
	}
	public static class MethodCallExpressionContext extends ExpressionContext {
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MethodCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMethodCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMethodCallExpression(this);
		}
	}
	public static class MethodReferenceExpressionContext extends ExpressionContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode COLONCOLON() { return getToken(ProcessingParser.COLONCOLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode NEW() { return getToken(ProcessingParser.NEW, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MethodReferenceExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterMethodReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitMethodReferenceExpression(this);
		}
	}
	public static class SquareBracketExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(ProcessingParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(ProcessingParser.RBRACK, 0); }
		public SquareBracketExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSquareBracketExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSquareBracketExpression(this);
		}
	}
	public static class CastExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> BITAND() { return getTokens(ProcessingParser.BITAND); }
		public TerminalNode BITAND(int i) {
			return getToken(ProcessingParser.BITAND, i);
		}
		public CastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCastExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 224;
		enterRecursionRule(_localctx, 224, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1534);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,191,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(1492);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new MethodCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1493);
				methodCall();
				}
				break;
			case 3:
				{
				_localctx = new MethodReferenceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1494);
				typeType();
				setState(1495);
				match(COLONCOLON);
				setState(1501);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MODULE:
				case OPEN:
				case REQUIRES:
				case EXPORTS:
				case OPENS:
				case TO:
				case USES:
				case PROVIDES:
				case WITH:
				case TRANSITIVE:
				case VAR:
				case YIELD:
				case RECORD:
				case SEALED:
				case PERMITS:
				case LT:
				case IDENTIFIER:
					{
					setState(1497);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1496);
						typeArguments();
						}
					}

					setState(1499);
					identifier();
					}
					break;
				case NEW:
					{
					setState(1500);
					match(NEW);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 4:
				{
				_localctx = new MethodReferenceExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1503);
				classType();
				setState(1504);
				match(COLONCOLON);
				setState(1506);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1505);
					typeArguments();
					}
				}

				setState(1508);
				match(NEW);
				}
				break;
			case 5:
				{
				_localctx = new ExpressionSwitchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1510);
				switchExpression();
				}
				break;
			case 6:
				{
				_localctx = new UnaryOperatorExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1511);
				((UnaryOperatorExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 96)) & ~0x3f) == 0 && ((1L << (_la - 96)) & ((1L << (BANG - 96)) | (1L << (TILDE - 96)) | (1L << (INC - 96)) | (1L << (DEC - 96)) | (1L << (ADD - 96)) | (1L << (SUB - 96)))) != 0)) ) {
					((UnaryOperatorExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1512);
				expression(17);
				}
				break;
			case 7:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1513);
				match(LPAREN);
				setState(1517);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,189,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1514);
						annotation();
						}
						} 
					}
					setState(1519);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,189,_ctx);
				}
				setState(1520);
				typeType();
				setState(1525);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BITAND) {
					{
					{
					setState(1521);
					match(BITAND);
					setState(1522);
					typeType();
					}
					}
					setState(1527);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1528);
				match(RPAREN);
				setState(1529);
				expression(16);
				}
				break;
			case 8:
				{
				_localctx = new ObjectCreationExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1531);
				match(NEW);
				setState(1532);
				creator();
				}
				break;
			case 9:
				{
				_localctx = new ExpressionLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1533);
				lambdaExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1619);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1617);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,197,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1536);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(1537);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 110)) & ~0x3f) == 0 && ((1L << (_la - 110)) & ((1L << (MUL - 110)) | (1L << (DIV - 110)) | (1L << (MOD - 110)))) != 0)) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1538);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1539);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1540);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1541);
						expression(14);
						}
						break;
					case 3:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1542);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1550);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,192,_ctx) ) {
						case 1:
							{
							setState(1543);
							match(LT);
							setState(1544);
							match(LT);
							}
							break;
						case 2:
							{
							setState(1545);
							match(GT);
							setState(1546);
							match(GT);
							setState(1547);
							match(GT);
							}
							break;
						case 3:
							{
							setState(1548);
							match(GT);
							setState(1549);
							match(GT);
							}
							break;
						}
						setState(1552);
						expression(13);
						}
						break;
					case 4:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1553);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1554);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (GT - 94)) | (1L << (LT - 94)) | (1L << (LE - 94)) | (1L << (GE - 94)))) != 0)) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1555);
						expression(12);
						}
						break;
					case 5:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1556);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1557);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1558);
						expression(10);
						}
						break;
					case 6:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1559);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1560);
						((BinaryOperatorExpressionContext)_localctx).bop = match(BITAND);
						setState(1561);
						expression(9);
						}
						break;
					case 7:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1562);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1563);
						((BinaryOperatorExpressionContext)_localctx).bop = match(CARET);
						setState(1564);
						expression(8);
						}
						break;
					case 8:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1565);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1566);
						((BinaryOperatorExpressionContext)_localctx).bop = match(BITOR);
						setState(1567);
						expression(7);
						}
						break;
					case 9:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1568);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1569);
						((BinaryOperatorExpressionContext)_localctx).bop = match(AND);
						setState(1570);
						expression(6);
						}
						break;
					case 10:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1571);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1572);
						((BinaryOperatorExpressionContext)_localctx).bop = match(OR);
						setState(1573);
						expression(5);
						}
						break;
					case 11:
						{
						_localctx = new TernaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1574);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1575);
						((TernaryExpressionContext)_localctx).bop = match(QUESTION);
						setState(1576);
						expression(0);
						setState(1577);
						match(COLON);
						setState(1578);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new BinaryOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1580);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1581);
						((BinaryOperatorExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 93)) & ~0x3f) == 0 && ((1L << (_la - 93)) & ((1L << (ASSIGN - 93)) | (1L << (ADD_ASSIGN - 93)) | (1L << (SUB_ASSIGN - 93)) | (1L << (MUL_ASSIGN - 93)) | (1L << (DIV_ASSIGN - 93)) | (1L << (AND_ASSIGN - 93)) | (1L << (OR_ASSIGN - 93)) | (1L << (XOR_ASSIGN - 93)) | (1L << (MOD_ASSIGN - 93)) | (1L << (LSHIFT_ASSIGN - 93)) | (1L << (RSHIFT_ASSIGN - 93)) | (1L << (URSHIFT_ASSIGN - 93)))) != 0)) ) {
							((BinaryOperatorExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1582);
						expression(2);
						}
						break;
					case 13:
						{
						_localctx = new SquareBracketExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1583);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1584);
						match(LBRACK);
						setState(1585);
						expression(0);
						setState(1586);
						match(RBRACK);
						}
						break;
					case 14:
						{
						_localctx = new MemberReferenceExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1588);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(1589);
						((MemberReferenceExpressionContext)_localctx).bop = match(DOT);
						setState(1601);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,194,_ctx) ) {
						case 1:
							{
							setState(1590);
							identifier();
							}
							break;
						case 2:
							{
							setState(1591);
							methodCall();
							}
							break;
						case 3:
							{
							setState(1592);
							match(THIS);
							}
							break;
						case 4:
							{
							setState(1593);
							match(NEW);
							setState(1595);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==LT) {
								{
								setState(1594);
								nonWildcardTypeArguments();
								}
							}

							setState(1597);
							innerCreator();
							}
							break;
						case 5:
							{
							setState(1598);
							match(SUPER);
							setState(1599);
							superSuffix();
							}
							break;
						case 6:
							{
							setState(1600);
							explicitGenericInvocation();
							}
							break;
						}
						}
						break;
					case 15:
						{
						_localctx = new MethodReferenceExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1603);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(1604);
						match(COLONCOLON);
						setState(1606);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==LT) {
							{
							setState(1605);
							typeArguments();
							}
						}

						setState(1608);
						identifier();
						}
						break;
					case 16:
						{
						_localctx = new PostIncrementDecrementOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1609);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(1610);
						((PostIncrementDecrementOperatorExpressionContext)_localctx).postfix = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
							((PostIncrementDecrementOperatorExpressionContext)_localctx).postfix = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 17:
						{
						_localctx = new InstanceOfOperatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1611);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1612);
						((InstanceOfOperatorExpressionContext)_localctx).bop = match(INSTANCEOF);
						setState(1615);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,196,_ctx) ) {
						case 1:
							{
							setState(1613);
							typeType();
							}
							break;
						case 2:
							{
							setState(1614);
							pattern();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(1621);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitPattern(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_pattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1625);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,199,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1622);
					variableModifier();
					}
					} 
				}
				setState(1627);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,199,_ctx);
			}
			setState(1628);
			typeType();
			setState(1632);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1629);
					annotation();
					}
					} 
				}
				setState(1634);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
			}
			setState(1635);
			identifier();
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

	public static class LambdaExpressionContext extends ParserRuleContext {
		public LambdaParametersContext lambdaParameters() {
			return getRuleContext(LambdaParametersContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(ProcessingParser.ARROW, 0); }
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLambdaExpression(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1637);
			lambdaParameters();
			setState(1638);
			match(ARROW);
			setState(1639);
			lambdaBody();
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

	public static class LambdaParametersContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public LambdaLVTIListContext lambdaLVTIList() {
			return getRuleContext(LambdaLVTIListContext.class,0);
		}
		public LambdaParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLambdaParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLambdaParameters(this);
		}
	}

	public final LambdaParametersContext lambdaParameters() throws RecognitionException {
		LambdaParametersContext _localctx = new LambdaParametersContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_lambdaParameters);
		int _la;
		try {
			setState(1663);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,204,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1641);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1642);
				match(LPAREN);
				setState(1644);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1643);
					formalParameterList();
					}
				}

				setState(1646);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1647);
				match(LPAREN);
				setState(1648);
				identifier();
				setState(1653);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1649);
					match(COMMA);
					setState(1650);
					identifier();
					}
					}
					setState(1655);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1656);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1658);
				match(LPAREN);
				setState(1660);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & ((1L << (FINAL - 24)) | (1L << (MODULE - 24)) | (1L << (OPEN - 24)) | (1L << (REQUIRES - 24)) | (1L << (EXPORTS - 24)) | (1L << (OPENS - 24)) | (1L << (TO - 24)) | (1L << (USES - 24)) | (1L << (PROVIDES - 24)) | (1L << (WITH - 24)) | (1L << (TRANSITIVE - 24)) | (1L << (VAR - 24)) | (1L << (YIELD - 24)) | (1L << (RECORD - 24)) | (1L << (SEALED - 24)) | (1L << (PERMITS - 24)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1659);
					lambdaLVTIList();
					}
				}

				setState(1662);
				match(RPAREN);
				}
				break;
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

	public static class LambdaBodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LambdaBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterLambdaBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitLambdaBody(this);
		}
	}

	public final LambdaBodyContext lambdaBody() throws RecognitionException {
		LambdaBodyContext _localctx = new LambdaBodyContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_lambdaBody);
		try {
			setState(1667);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case HexColorLiteral:
			case CHAR_LITERAL:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case SWITCH:
			case THIS:
			case VOID:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case STRING_LITERAL:
			case MULTI_STRING_LIT:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1665);
				expression(0);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1666);
				block();
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

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public TerminalNode THIS() { return getToken(ProcessingParser.THIS, 0); }
		public TerminalNode SUPER() { return getToken(ProcessingParser.SUPER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ProcessingParser.DOT, 0); }
		public TerminalNode CLASS() { return getToken(ProcessingParser.CLASS, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_primary);
		try {
			setState(1687);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,207,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1669);
				match(LPAREN);
				setState(1670);
				expression(0);
				setState(1671);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1673);
				match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1674);
				match(SUPER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1675);
				literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1676);
				identifier();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1677);
				typeTypeOrVoid();
				setState(1678);
				match(DOT);
				setState(1679);
				match(CLASS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1681);
				nonWildcardTypeArguments();
				setState(1685);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SUPER:
				case MODULE:
				case OPEN:
				case REQUIRES:
				case EXPORTS:
				case OPENS:
				case TO:
				case USES:
				case PROVIDES:
				case WITH:
				case TRANSITIVE:
				case VAR:
				case YIELD:
				case RECORD:
				case SEALED:
				case PERMITS:
				case IDENTIFIER:
					{
					setState(1682);
					explicitGenericInvocationSuffix();
					}
					break;
				case THIS:
					{
					setState(1683);
					match(THIS);
					setState(1684);
					arguments();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
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

	public static class SwitchExpressionContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(ProcessingParser.SWITCH, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(ProcessingParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ProcessingParser.RBRACE, 0); }
		public List<SwitchLabeledRuleContext> switchLabeledRule() {
			return getRuleContexts(SwitchLabeledRuleContext.class);
		}
		public SwitchLabeledRuleContext switchLabeledRule(int i) {
			return getRuleContext(SwitchLabeledRuleContext.class,i);
		}
		public SwitchExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSwitchExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSwitchExpression(this);
		}
	}

	public final SwitchExpressionContext switchExpression() throws RecognitionException {
		SwitchExpressionContext _localctx = new SwitchExpressionContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_switchExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1689);
			match(SWITCH);
			setState(1690);
			parExpression();
			setState(1691);
			match(LBRACE);
			setState(1695);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE || _la==DEFAULT) {
				{
				{
				setState(1692);
				switchLabeledRule();
				}
				}
				setState(1697);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1698);
			match(RBRACE);
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

	public static class SwitchLabeledRuleContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(ProcessingParser.CASE, 0); }
		public SwitchRuleOutcomeContext switchRuleOutcome() {
			return getRuleContext(SwitchRuleOutcomeContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(ProcessingParser.ARROW, 0); }
		public TerminalNode COLON() { return getToken(ProcessingParser.COLON, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode NULL_LITERAL() { return getToken(ProcessingParser.NULL_LITERAL, 0); }
		public GuardedPatternContext guardedPattern() {
			return getRuleContext(GuardedPatternContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(ProcessingParser.DEFAULT, 0); }
		public SwitchLabeledRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabeledRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSwitchLabeledRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSwitchLabeledRule(this);
		}
	}

	public final SwitchLabeledRuleContext switchLabeledRule() throws RecognitionException {
		SwitchLabeledRuleContext _localctx = new SwitchLabeledRuleContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_switchLabeledRule);
		int _la;
		try {
			setState(1711);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1700);
				match(CASE);
				setState(1704);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,209,_ctx) ) {
				case 1:
					{
					setState(1701);
					expressionList();
					}
					break;
				case 2:
					{
					setState(1702);
					match(NULL_LITERAL);
					}
					break;
				case 3:
					{
					setState(1703);
					guardedPattern(0);
					}
					break;
				}
				setState(1706);
				_la = _input.LA(1);
				if ( !(_la==COLON || _la==ARROW) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1707);
				switchRuleOutcome();
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1708);
				match(DEFAULT);
				setState(1709);
				_la = _input.LA(1);
				if ( !(_la==COLON || _la==ARROW) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1710);
				switchRuleOutcome();
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

	public static class GuardedPatternContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public GuardedPatternContext guardedPattern() {
			return getRuleContext(GuardedPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(ProcessingParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(ProcessingParser.AND, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GuardedPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guardedPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterGuardedPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitGuardedPattern(this);
		}
	}

	public final GuardedPatternContext guardedPattern() throws RecognitionException {
		return guardedPattern(0);
	}

	private GuardedPatternContext guardedPattern(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		GuardedPatternContext _localctx = new GuardedPatternContext(_ctx, _parentState);
		GuardedPatternContext _prevctx = _localctx;
		int _startState = 240;
		enterRecursionRule(_localctx, 240, RULE_guardedPattern, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1739);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(1714);
				match(LPAREN);
				setState(1715);
				guardedPattern(0);
				setState(1716);
				match(RPAREN);
				}
				break;
			case T__0:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FINAL:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case AT:
			case IDENTIFIER:
				{
				setState(1721);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,211,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1718);
						variableModifier();
						}
						} 
					}
					setState(1723);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,211,_ctx);
				}
				setState(1724);
				typeType();
				setState(1728);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,212,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1725);
						annotation();
						}
						} 
					}
					setState(1730);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,212,_ctx);
				}
				setState(1731);
				identifier();
				setState(1736);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,213,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1732);
						match(AND);
						setState(1733);
						expression(0);
						}
						} 
					}
					setState(1738);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,213,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(1746);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,215,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new GuardedPatternContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_guardedPattern);
					setState(1741);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(1742);
					match(AND);
					setState(1743);
					expression(0);
					}
					} 
				}
				setState(1748);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,215,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SwitchRuleOutcomeContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchRuleOutcomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchRuleOutcome; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSwitchRuleOutcome(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSwitchRuleOutcome(this);
		}
	}

	public final SwitchRuleOutcomeContext switchRuleOutcome() throws RecognitionException {
		SwitchRuleOutcomeContext _localctx = new SwitchRuleOutcomeContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_switchRuleOutcome);
		int _la;
		try {
			setState(1756);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,217,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1749);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1753);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (NON_SEALED - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LBRACE - 64)) | (1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					{
					setState(1750);
					blockStatement();
					}
					}
					setState(1755);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
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

	public static class ClassTypeContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ProcessingParser.DOT, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassType(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_classType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1761);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,218,_ctx) ) {
			case 1:
				{
				setState(1758);
				classOrInterfaceType();
				setState(1759);
				match(DOT);
				}
				break;
			}
			setState(1766);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,219,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1763);
					annotation();
					}
					} 
				}
				setState(1768);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,219,_ctx);
			}
			setState(1769);
			identifier();
			setState(1771);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1770);
				typeArguments();
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

	public static class CreatorContext extends ParserRuleContext {
		public CreatedNameContext createdName() {
			return getRuleContext(CreatedNameContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ArrayCreatorRestContext arrayCreatorRest() {
			return getRuleContext(ArrayCreatorRestContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCreator(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_creator);
		int _la;
		try {
			setState(1782);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,222,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1774);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1773);
					nonWildcardTypeArguments();
					}
				}

				setState(1776);
				createdName();
				setState(1777);
				classCreatorRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1779);
				createdName();
				setState(1780);
				arrayCreatorRest();
				}
				break;
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

	public static class CreatedNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TypeArgumentsOrDiamondContext> typeArgumentsOrDiamond() {
			return getRuleContexts(TypeArgumentsOrDiamondContext.class);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond(int i) {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(ProcessingParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(ProcessingParser.DOT, i);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public CreatedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createdName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterCreatedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitCreatedName(this);
		}
	}

	public final CreatedNameContext createdName() throws RecognitionException {
		CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_createdName);
		int _la;
		try {
			setState(1799);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1784);
				identifier();
				setState(1786);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1785);
					typeArgumentsOrDiamond();
					}
				}

				setState(1795);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(1788);
					match(DOT);
					setState(1789);
					identifier();
					setState(1791);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1790);
						typeArgumentsOrDiamond();
						}
					}

					}
					}
					setState(1797);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__0:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1798);
				primitiveType();
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

	public static class InnerCreatorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() {
			return getRuleContext(NonWildcardTypeArgumentsOrDiamondContext.class,0);
		}
		public InnerCreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerCreator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterInnerCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitInnerCreator(this);
		}
	}

	public final InnerCreatorContext innerCreator() throws RecognitionException {
		InnerCreatorContext _localctx = new InnerCreatorContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_innerCreator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1801);
			identifier();
			setState(1803);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1802);
				nonWildcardTypeArgumentsOrDiamond();
				}
			}

			setState(1805);
			classCreatorRest();
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

	public static class ArrayCreatorRestContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterArrayCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitArrayCreatorRest(this);
		}
	}

	public final ArrayCreatorRestContext arrayCreatorRest() throws RecognitionException {
		ArrayCreatorRestContext _localctx = new ArrayCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_arrayCreatorRest);
		int _la;
		try {
			int _alt;
			setState(1829);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,231,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1809); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1807);
					match(LBRACK);
					setState(1808);
					match(RBRACK);
					}
					}
					setState(1811); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACK );
				setState(1813);
				arrayInitializer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1818); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1814);
						match(LBRACK);
						setState(1815);
						expression(0);
						setState(1816);
						match(RBRACK);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1820); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,229,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(1826);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1822);
						match(LBRACK);
						setState(1823);
						match(RBRACK);
						}
						} 
					}
					setState(1828);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
				}
				}
				break;
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

	public static class ClassCreatorRestContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterClassCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitClassCreatorRest(this);
		}
	}

	public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
		ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_classCreatorRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1831);
			arguments();
			setState(1833);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,232,_ctx) ) {
			case 1:
				{
				setState(1832);
				classBody();
				}
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

	public static class ExplicitGenericInvocationContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterExplicitGenericInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitExplicitGenericInvocation(this);
		}
	}

	public final ExplicitGenericInvocationContext explicitGenericInvocation() throws RecognitionException {
		ExplicitGenericInvocationContext _localctx = new ExplicitGenericInvocationContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_explicitGenericInvocation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1835);
			nonWildcardTypeArguments();
			setState(1836);
			explicitGenericInvocationSuffix();
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

	public static class TypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(ProcessingParser.LT, 0); }
		public TerminalNode GT() { return getToken(ProcessingParser.GT, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public TypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeArgumentsOrDiamond(this);
		}
	}

	public final TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() throws RecognitionException {
		TypeArgumentsOrDiamondContext _localctx = new TypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_typeArgumentsOrDiamond);
		try {
			setState(1841);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,233,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1838);
				match(LT);
				setState(1839);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1840);
				typeArguments();
				}
				break;
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

	public static class NonWildcardTypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(ProcessingParser.LT, 0); }
		public TerminalNode GT() { return getToken(ProcessingParser.GT, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitNonWildcardTypeArgumentsOrDiamond(this);
		}
	}

	public final NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() throws RecognitionException {
		NonWildcardTypeArgumentsOrDiamondContext _localctx = new NonWildcardTypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_nonWildcardTypeArgumentsOrDiamond);
		try {
			setState(1846);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,234,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1843);
				match(LT);
				setState(1844);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1845);
				nonWildcardTypeArguments();
				}
				break;
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

	public static class NonWildcardTypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(ProcessingParser.LT, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode GT() { return getToken(ProcessingParser.GT, 0); }
		public NonWildcardTypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterNonWildcardTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitNonWildcardTypeArguments(this);
		}
	}

	public final NonWildcardTypeArgumentsContext nonWildcardTypeArguments() throws RecognitionException {
		NonWildcardTypeArgumentsContext _localctx = new NonWildcardTypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_nonWildcardTypeArguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1848);
			match(LT);
			setState(1849);
			typeList();
			setState(1850);
			match(GT);
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

	public static class TypeListContext extends ParserRuleContext {
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeList(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1852);
			typeType();
			setState(1857);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1853);
				match(COMMA);
				setState(1854);
				typeType();
				}
				}
				setState(1859);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class TypeTypeContext extends ParserRuleContext {
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ProcessingParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ProcessingParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ProcessingParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ProcessingParser.RBRACK, i);
		}
		public TypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeType(this);
		}
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_typeType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1863);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,236,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1860);
					annotation();
					}
					} 
				}
				setState(1865);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,236,_ctx);
			}
			setState(1868);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case IDENTIFIER:
				{
				setState(1866);
				classOrInterfaceType();
				}
				break;
			case T__0:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				{
				setState(1867);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1880);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,239,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1873);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (MODULE - 57)) | (1L << (OPEN - 57)) | (1L << (REQUIRES - 57)) | (1L << (EXPORTS - 57)) | (1L << (OPENS - 57)) | (1L << (TO - 57)) | (1L << (USES - 57)) | (1L << (PROVIDES - 57)) | (1L << (WITH - 57)) | (1L << (TRANSITIVE - 57)) | (1L << (VAR - 57)) | (1L << (YIELD - 57)) | (1L << (RECORD - 57)) | (1L << (SEALED - 57)) | (1L << (PERMITS - 57)))) != 0) || _la==AT || _la==IDENTIFIER) {
						{
						{
						setState(1870);
						annotation();
						}
						}
						setState(1875);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1876);
					match(LBRACK);
					setState(1877);
					match(RBRACK);
					}
					} 
				}
				setState(1882);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,239,_ctx);
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

	public static class TypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(ProcessingParser.LT, 0); }
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public TerminalNode GT() { return getToken(ProcessingParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ProcessingParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ProcessingParser.COMMA, i);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitTypeArguments(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_typeArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1883);
			match(LT);
			setState(1884);
			typeArgument();
			setState(1889);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1885);
				match(COMMA);
				setState(1886);
				typeArgument();
				}
				}
				setState(1891);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1892);
			match(GT);
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

	public static class SuperSuffixContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ProcessingParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public SuperSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterSuperSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitSuperSuffix(this);
		}
	}

	public final SuperSuffixContext superSuffix() throws RecognitionException {
		SuperSuffixContext _localctx = new SuperSuffixContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_superSuffix);
		int _la;
		try {
			setState(1903);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1894);
				arguments();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1895);
				match(DOT);
				setState(1897);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1896);
					typeArguments();
					}
				}

				setState(1899);
				identifier();
				setState(1901);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,242,_ctx) ) {
				case 1:
					{
					setState(1900);
					arguments();
					}
					break;
				}
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

	public static class ExplicitGenericInvocationSuffixContext extends ParserRuleContext {
		public TerminalNode SUPER() { return getToken(ProcessingParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocationSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterExplicitGenericInvocationSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitExplicitGenericInvocationSuffix(this);
		}
	}

	public final ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() throws RecognitionException {
		ExplicitGenericInvocationSuffixContext _localctx = new ExplicitGenericInvocationSuffixContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_explicitGenericInvocationSuffix);
		try {
			setState(1910);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1905);
				match(SUPER);
				setState(1906);
				superSuffix();
				}
				break;
			case MODULE:
			case OPEN:
			case REQUIRES:
			case EXPORTS:
			case OPENS:
			case TO:
			case USES:
			case PROVIDES:
			case WITH:
			case TRANSITIVE:
			case VAR:
			case YIELD:
			case RECORD:
			case SEALED:
			case PERMITS:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1907);
				identifier();
				setState(1908);
				arguments();
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

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ProcessingParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ProcessingParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ProcessingListener ) ((ProcessingListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1912);
			match(LPAREN);
			setState(1914);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << HexColorLiteral) | (1L << CHAR_LITERAL) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << SWITCH) | (1L << THIS) | (1L << VOID) | (1L << MODULE) | (1L << OPEN) | (1L << REQUIRES) | (1L << EXPORTS) | (1L << OPENS) | (1L << TO) | (1L << USES))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PROVIDES - 64)) | (1L << (WITH - 64)) | (1L << (TRANSITIVE - 64)) | (1L << (VAR - 64)) | (1L << (YIELD - 64)) | (1L << (RECORD - 64)) | (1L << (SEALED - 64)) | (1L << (PERMITS - 64)) | (1L << (DECIMAL_LITERAL - 64)) | (1L << (HEX_LITERAL - 64)) | (1L << (OCT_LITERAL - 64)) | (1L << (BINARY_LITERAL - 64)) | (1L << (FLOAT_LITERAL - 64)) | (1L << (HEX_FLOAT_LITERAL - 64)) | (1L << (BOOL_LITERAL - 64)) | (1L << (STRING_LITERAL - 64)) | (1L << (MULTI_STRING_LIT - 64)) | (1L << (NULL_LITERAL - 64)) | (1L << (LPAREN - 64)) | (1L << (LT - 64)) | (1L << (BANG - 64)) | (1L << (TILDE - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (ADD - 64)) | (1L << (SUB - 64)))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(1913);
				expressionList();
				}
			}

			setState(1916);
			match(RPAREN);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 112:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 120:
			return guardedPattern_sempred((GuardedPatternContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 25);
		case 13:
			return precpred(_ctx, 24);
		case 14:
			return precpred(_ctx, 22);
		case 15:
			return precpred(_ctx, 18);
		case 16:
			return precpred(_ctx, 10);
		}
		return true;
	}
	private boolean guardedPattern_sempred(GuardedPatternContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0085\u0781\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089"+
		"\4\u008a\t\u008a\4\u008b\t\u008b\3\2\3\2\3\2\5\2\u011a\n\2\3\3\5\3\u011d"+
		"\n\3\3\3\7\3\u0120\n\3\f\3\16\3\u0123\13\3\3\3\6\3\u0126\n\3\r\3\16\3"+
		"\u0127\3\3\3\3\3\4\3\4\3\4\7\4\u012f\n\4\f\4\16\4\u0132\13\4\3\4\3\4\3"+
		"\5\3\5\7\5\u0138\n\5\f\5\16\5\u013b\13\5\3\5\3\5\3\6\3\6\3\6\7\6\u0142"+
		"\n\6\f\6\16\6\u0145\13\6\3\6\3\6\3\6\3\6\3\6\7\6\u014c\n\6\f\6\16\6\u014f"+
		"\13\6\3\6\3\6\3\6\7\6\u0154\n\6\f\6\16\6\u0157\13\6\3\6\3\6\3\6\3\6\3"+
		"\6\7\6\u015e\n\6\f\6\16\6\u0161\13\6\5\6\u0163\n\6\3\7\3\7\3\7\3\7\7\7"+
		"\u0169\n\7\f\7\16\7\u016c\13\7\5\7\u016e\n\7\3\b\3\b\3\b\7\b\u0173\n\b"+
		"\f\b\16\b\u0176\13\b\3\b\3\b\3\t\3\t\3\t\3\t\5\t\u017e\n\t\3\t\3\t\3\t"+
		"\3\t\5\t\u0184\n\t\3\t\3\t\3\t\3\t\5\t\u018a\n\t\3\t\5\t\u018d\n\t\3\n"+
		"\3\n\3\n\5\n\u0192\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u019f\n\13\3\f\3\f\3\r\3\r\5\r\u01a5\n\r\3\r\3\r\3\r\5\r\u01aa"+
		"\n\r\7\r\u01ac\n\r\f\r\16\r\u01af\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\5\16\u01b8\n\16\3\17\3\17\3\20\5\20\u01bd\n\20\3\20\3\20\7\20\u01c1"+
		"\n\20\f\20\16\20\u01c4\13\20\3\20\3\20\7\20\u01c8\n\20\f\20\16\20\u01cb"+
		"\13\20\3\20\3\20\3\20\3\20\5\20\u01d1\n\20\3\21\7\21\u01d4\n\21\f\21\16"+
		"\21\u01d7\13\21\3\21\3\21\3\21\3\21\3\22\3\22\5\22\u01df\n\22\3\22\3\22"+
		"\3\22\5\22\u01e4\n\22\3\22\3\22\3\23\7\23\u01e9\n\23\f\23\16\23\u01ec"+
		"\13\23\3\23\3\23\3\23\3\23\3\23\5\23\u01f3\n\23\3\24\3\24\3\24\3\24\3"+
		"\24\5\24\u01fa\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\5\25\u0206\n\25\3\26\3\26\5\26\u020a\n\26\3\27\3\27\3\27\5\27\u020f\n"+
		"\27\3\27\3\27\5\27\u0213\n\27\3\27\3\27\5\27\u0217\n\27\3\27\3\27\5\27"+
		"\u021b\n\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u0223\n\30\f\30\16\30\u0226"+
		"\13\30\3\30\3\30\3\31\7\31\u022b\n\31\f\31\16\31\u022e\13\31\3\31\3\31"+
		"\3\31\7\31\u0233\n\31\f\31\16\31\u0236\13\31\3\31\5\31\u0239\n\31\3\32"+
		"\3\32\3\32\7\32\u023e\n\32\f\32\16\32\u0241\13\32\3\33\3\33\3\33\3\33"+
		"\5\33\u0247\n\33\3\33\3\33\5\33\u024b\n\33\3\33\5\33\u024e\n\33\3\33\5"+
		"\33\u0251\n\33\3\33\3\33\3\34\3\34\3\34\7\34\u0258\n\34\f\34\16\34\u025b"+
		"\13\34\3\35\7\35\u025e\n\35\f\35\16\35\u0261\13\35\3\35\3\35\5\35\u0265"+
		"\n\35\3\35\5\35\u0268\n\35\3\36\3\36\7\36\u026c\n\36\f\36\16\36\u026f"+
		"\13\36\3\37\3\37\3\37\5\37\u0274\n\37\3\37\3\37\5\37\u0278\n\37\3\37\3"+
		"\37\5\37\u027c\n\37\3\37\3\37\3 \3 \7 \u0282\n \f \16 \u0285\13 \3 \3"+
		" \3!\3!\7!\u028b\n!\f!\16!\u028e\13!\3!\3!\3\"\3\"\5\"\u0294\n\"\3\"\3"+
		"\"\7\"\u0298\n\"\f\"\16\"\u029b\13\"\3\"\5\"\u029e\n\"\3#\3#\3#\3#\3#"+
		"\3#\3#\3#\3#\3#\5#\u02aa\n#\3$\3$\3$\3$\3$\7$\u02b1\n$\f$\16$\u02b4\13"+
		"$\3$\3$\5$\u02b8\n$\3$\3$\3%\3%\5%\u02be\n%\3&\3&\5&\u02c2\n&\3\'\3\'"+
		"\3\'\3(\3(\3(\3)\3)\3)\3)\5)\u02ce\n)\3)\3)\3*\7*\u02d3\n*\f*\16*\u02d6"+
		"\13*\3*\3*\3*\3+\3+\3+\3+\3,\7,\u02e0\n,\f,\16,\u02e3\13,\3,\3,\5,\u02e7"+
		"\n,\3-\3-\3-\3-\3-\3-\3-\3-\5-\u02f1\n-\3.\3.\3.\3.\7.\u02f7\n.\f.\16"+
		".\u02fa\13.\3.\3.\3/\3/\3/\7/\u0301\n/\f/\16/\u0304\13/\3/\3/\3/\3\60"+
		"\7\60\u030a\n\60\f\60\16\60\u030d\13\60\3\60\3\60\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\5\61\u0317\n\61\3\62\7\62\u031a\n\62\f\62\16\62\u031d\13\62"+
		"\3\62\3\62\3\62\3\63\7\63\u0323\n\63\f\63\16\63\u0326\13\63\3\63\3\63"+
		"\3\63\3\63\3\63\7\63\u032d\n\63\f\63\16\63\u0330\13\63\3\63\3\63\5\63"+
		"\u0334\n\63\3\63\3\63\3\64\3\64\3\64\7\64\u033b\n\64\f\64\16\64\u033e"+
		"\13\64\3\65\3\65\3\65\5\65\u0343\n\65\3\66\3\66\5\66\u0347\n\66\3\67\3"+
		"\67\3\67\3\67\7\67\u034d\n\67\f\67\16\67\u0350\13\67\3\67\5\67\u0353\n"+
		"\67\5\67\u0355\n\67\3\67\3\67\38\38\58\u035b\n8\38\38\78\u035f\n8\f8\16"+
		"8\u0362\138\38\38\58\u0366\n8\39\39\79\u036a\n9\f9\169\u036d\139\39\3"+
		"9\39\59\u0372\n9\59\u0374\n9\3:\3:\3:\7:\u0379\n:\f:\16:\u037c\13:\3;"+
		"\3;\5;\u0380\n;\3;\3;\3;\5;\u0385\n;\3;\5;\u0388\n;\5;\u038a\n;\3;\3;"+
		"\3<\3<\3<\3<\7<\u0392\n<\f<\16<\u0395\13<\3<\3<\3=\3=\3=\7=\u039c\n=\f"+
		"=\16=\u039f\13=\3=\3=\5=\u03a3\n=\3=\5=\u03a6\n=\3>\7>\u03a9\n>\f>\16"+
		">\u03ac\13>\3>\3>\3>\3?\7?\u03b2\n?\f?\16?\u03b5\13?\3?\3?\7?\u03b9\n"+
		"?\f?\16?\u03bc\13?\3?\3?\3?\3@\3@\3@\7@\u03c4\n@\f@\16@\u03c7\13@\3A\7"+
		"A\u03ca\nA\fA\16A\u03cd\13A\3A\3A\3A\3B\3B\3C\3C\3D\3D\5D\u03d8\nD\3E"+
		"\3E\3F\3F\3G\3G\3G\7G\u03e1\nG\fG\16G\u03e4\13G\3G\3G\3G\3H\3H\3H\5H\u03ec"+
		"\nH\3H\3H\3H\5H\u03f1\nH\3H\5H\u03f4\nH\3I\3I\3I\7I\u03f9\nI\fI\16I\u03fc"+
		"\13I\3J\3J\3J\3J\3K\3K\3K\5K\u0405\nK\3L\3L\3L\3L\7L\u040b\nL\fL\16L\u040e"+
		"\13L\5L\u0410\nL\3L\5L\u0413\nL\3L\3L\3M\3M\3M\3M\3M\3N\3N\7N\u041e\n"+
		"N\fN\16N\u0421\13N\3N\3N\3O\7O\u0426\nO\fO\16O\u0429\13O\3O\3O\5O\u042d"+
		"\nO\3P\3P\3P\3P\3P\3P\5P\u0435\nP\3P\3P\5P\u0439\nP\3P\3P\5P\u043d\nP"+
		"\3P\3P\5P\u0441\nP\3P\3P\5P\u0445\nP\5P\u0447\nP\3Q\3Q\5Q\u044b\nQ\3R"+
		"\3R\3R\3R\5R\u0451\nR\3S\3S\3T\3T\3T\3U\5U\u0459\nU\3U\3U\3U\3U\3V\3V"+
		"\7V\u0461\nV\fV\16V\u0464\13V\3V\3V\3W\3W\7W\u046a\nW\fW\16W\u046d\13"+
		"W\3W\3W\3W\3W\3W\3W\3W\5W\u0476\nW\3W\3W\3W\3W\3W\3W\5W\u047e\nW\3W\3"+
		"W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\5W\u048c\nW\3X\3X\3Y\3Y\3Y\5Y\u0493\n"+
		"Y\3Y\3Y\3Y\5Y\u0498\nY\3Y\3Y\3Z\3Z\5Z\u049e\nZ\3Z\3Z\3[\3[\3[\7[\u04a5"+
		"\n[\f[\16[\u04a8\13[\3\\\3\\\3\\\3]\3]\3]\7]\u04b0\n]\f]\16]\u04b3\13"+
		"]\3]\3]\3^\3^\7^\u04b9\n^\f^\16^\u04bc\13^\3^\3^\3_\3_\3_\3_\3_\5_\u04c5"+
		"\n_\3`\7`\u04c8\n`\f`\16`\u04cb\13`\3`\3`\3`\3`\3`\3`\3`\3`\5`\u04d5\n"+
		"`\3a\3a\3b\3b\3c\7c\u04dc\nc\fc\16c\u04df\13c\3c\3c\3c\5c\u04e4\nc\3d"+
		"\3d\3d\3d\3d\5d\u04eb\nd\3d\3d\3d\3d\3d\3d\3d\5d\u04f4\nd\3d\3d\3d\3d"+
		"\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\6d\u0509\nd\rd\16d\u050a"+
		"\3d\5d\u050e\nd\3d\5d\u0511\nd\3d\3d\3d\3d\7d\u0517\nd\fd\16d\u051a\13"+
		"d\3d\5d\u051d\nd\3d\3d\3d\3d\7d\u0523\nd\fd\16d\u0526\13d\3d\7d\u0529"+
		"\nd\fd\16d\u052c\13d\3d\3d\3d\3d\3d\3d\3d\3d\5d\u0536\nd\3d\3d\3d\3d\3"+
		"d\3d\3d\5d\u053f\nd\3d\3d\3d\5d\u0544\nd\3d\3d\3d\3d\3d\3d\3d\3d\3d\3"+
		"d\3d\5d\u0551\nd\3d\3d\3d\3d\5d\u0557\nd\3e\3e\3e\7e\u055c\ne\fe\16e\u055f"+
		"\13e\3e\3e\3e\3e\3e\3f\3f\3f\7f\u0569\nf\ff\16f\u056c\13f\3g\3g\3g\3h"+
		"\3h\3h\5h\u0574\nh\3h\3h\3i\3i\3i\7i\u057b\ni\fi\16i\u057e\13i\3j\7j\u0581"+
		"\nj\fj\16j\u0584\13j\3j\3j\3j\3j\3j\5j\u058b\nj\3j\3j\3j\3j\5j\u0591\n"+
		"j\3k\6k\u0594\nk\rk\16k\u0595\3k\6k\u0599\nk\rk\16k\u059a\3l\3l\3l\3l"+
		"\3l\3l\5l\u05a3\nl\3l\3l\3l\5l\u05a8\nl\3m\3m\5m\u05ac\nm\3m\3m\5m\u05b0"+
		"\nm\3m\3m\5m\u05b4\nm\5m\u05b6\nm\3n\3n\5n\u05ba\nn\3o\7o\u05bd\no\fo"+
		"\16o\u05c0\13o\3o\3o\5o\u05c4\no\3o\3o\3o\3o\3p\3p\3p\3p\3q\3q\3q\7q\u05d1"+
		"\nq\fq\16q\u05d4\13q\3r\3r\3r\3r\3r\3r\5r\u05dc\nr\3r\3r\5r\u05e0\nr\3"+
		"r\3r\3r\5r\u05e5\nr\3r\3r\3r\3r\3r\3r\3r\7r\u05ee\nr\fr\16r\u05f1\13r"+
		"\3r\3r\3r\7r\u05f6\nr\fr\16r\u05f9\13r\3r\3r\3r\3r\3r\3r\5r\u0601\nr\3"+
		"r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\5r\u0611\nr\3r\3r\3r\3r\3r\3"+
		"r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3"+
		"r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\5r\u063e\nr\3r\3r\3r\3r\5"+
		"r\u0644\nr\3r\3r\3r\5r\u0649\nr\3r\3r\3r\3r\3r\3r\3r\5r\u0652\nr\7r\u0654"+
		"\nr\fr\16r\u0657\13r\3s\7s\u065a\ns\fs\16s\u065d\13s\3s\3s\7s\u0661\n"+
		"s\fs\16s\u0664\13s\3s\3s\3t\3t\3t\3t\3u\3u\3u\5u\u066f\nu\3u\3u\3u\3u"+
		"\3u\7u\u0676\nu\fu\16u\u0679\13u\3u\3u\3u\3u\5u\u067f\nu\3u\5u\u0682\n"+
		"u\3v\3v\5v\u0686\nv\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\5"+
		"w\u0698\nw\5w\u069a\nw\3x\3x\3x\3x\7x\u06a0\nx\fx\16x\u06a3\13x\3x\3x"+
		"\3y\3y\3y\3y\5y\u06ab\ny\3y\3y\3y\3y\3y\5y\u06b2\ny\3z\3z\3z\3z\3z\3z"+
		"\7z\u06ba\nz\fz\16z\u06bd\13z\3z\3z\7z\u06c1\nz\fz\16z\u06c4\13z\3z\3"+
		"z\3z\7z\u06c9\nz\fz\16z\u06cc\13z\5z\u06ce\nz\3z\3z\3z\7z\u06d3\nz\fz"+
		"\16z\u06d6\13z\3{\3{\7{\u06da\n{\f{\16{\u06dd\13{\5{\u06df\n{\3|\3|\3"+
		"|\5|\u06e4\n|\3|\7|\u06e7\n|\f|\16|\u06ea\13|\3|\3|\5|\u06ee\n|\3}\5}"+
		"\u06f1\n}\3}\3}\3}\3}\3}\3}\5}\u06f9\n}\3~\3~\5~\u06fd\n~\3~\3~\3~\5~"+
		"\u0702\n~\7~\u0704\n~\f~\16~\u0707\13~\3~\5~\u070a\n~\3\177\3\177\5\177"+
		"\u070e\n\177\3\177\3\177\3\u0080\3\u0080\6\u0080\u0714\n\u0080\r\u0080"+
		"\16\u0080\u0715\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\6\u0080\u071d"+
		"\n\u0080\r\u0080\16\u0080\u071e\3\u0080\3\u0080\7\u0080\u0723\n\u0080"+
		"\f\u0080\16\u0080\u0726\13\u0080\5\u0080\u0728\n\u0080\3\u0081\3\u0081"+
		"\5\u0081\u072c\n\u0081\3\u0082\3\u0082\3\u0082\3\u0083\3\u0083\3\u0083"+
		"\5\u0083\u0734\n\u0083\3\u0084\3\u0084\3\u0084\5\u0084\u0739\n\u0084\3"+
		"\u0085\3\u0085\3\u0085\3\u0085\3\u0086\3\u0086\3\u0086\7\u0086\u0742\n"+
		"\u0086\f\u0086\16\u0086\u0745\13\u0086\3\u0087\7\u0087\u0748\n\u0087\f"+
		"\u0087\16\u0087\u074b\13\u0087\3\u0087\3\u0087\5\u0087\u074f\n\u0087\3"+
		"\u0087\7\u0087\u0752\n\u0087\f\u0087\16\u0087\u0755\13\u0087\3\u0087\3"+
		"\u0087\7\u0087\u0759\n\u0087\f\u0087\16\u0087\u075c\13\u0087\3\u0088\3"+
		"\u0088\3\u0088\3\u0088\7\u0088\u0762\n\u0088\f\u0088\16\u0088\u0765\13"+
		"\u0088\3\u0088\3\u0088\3\u0089\3\u0089\3\u0089\5\u0089\u076c\n\u0089\3"+
		"\u0089\3\u0089\5\u0089\u0770\n\u0089\5\u0089\u0772\n\u0089\3\u008a\3\u008a"+
		"\3\u008a\3\u008a\3\u008a\5\u008a\u0779\n\u008a\3\u008b\3\u008b\5\u008b"+
		"\u077d\n\u008b\3\u008b\3\u008b\3\u008b\2\4\u00e2\u00f2\u008c\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\"+
		"^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090"+
		"\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8"+
		"\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0"+
		"\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8"+
		"\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee\u00f0"+
		"\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106\u0108"+
		"\u010a\u010c\u010e\u0110\u0112\u0114\2\21\b\2\3\3\13\13\r\r\20\20\34\34"+
		"##\4\2\31\31\60\60\3\2KN\3\2OP\4\2..DD\4\2;I\u0085\u0085\5\2;DGI\u0085"+
		"\u0085\4\2bclo\4\2pquu\3\2no\4\2`agh\4\2ffii\4\2__v\u0080\3\2lm\4\2ee"+
		"\u0081\u0081\2\u0862\2\u0119\3\2\2\2\4\u011c\3\2\2\2\6\u0130\3\2\2\2\b"+
		"\u0139\3\2\2\2\n\u0162\3\2\2\2\f\u016d\3\2\2\2\16\u016f\3\2\2\2\20\u018c"+
		"\3\2\2\2\22\u018e\3\2\2\2\24\u019e\3\2\2\2\26\u01a0\3\2\2\2\30\u01a4\3"+
		"\2\2\2\32\u01b7\3\2\2\2\34\u01b9\3\2\2\2\36\u01d0\3\2\2\2 \u01d5\3\2\2"+
		"\2\"\u01dc\3\2\2\2$\u01ea\3\2\2\2&\u01f9\3\2\2\2(\u0205\3\2\2\2*\u0209"+
		"\3\2\2\2,\u020b\3\2\2\2.\u021e\3\2\2\2\60\u022c\3\2\2\2\62\u023a\3\2\2"+
		"\2\64\u0242\3\2\2\2\66\u0254\3\2\2\28\u025f\3\2\2\2:\u0269\3\2\2\2<\u0270"+
		"\3\2\2\2>\u027f\3\2\2\2@\u0288\3\2\2\2B\u029d\3\2\2\2D\u02a9\3\2\2\2F"+
		"\u02ab\3\2\2\2H\u02bd\3\2\2\2J\u02c1\3\2\2\2L\u02c3\3\2\2\2N\u02c6\3\2"+
		"\2\2P\u02c9\3\2\2\2R\u02d4\3\2\2\2T\u02da\3\2\2\2V\u02e6\3\2\2\2X\u02f0"+
		"\3\2\2\2Z\u02f2\3\2\2\2\\\u02fd\3\2\2\2^\u030b\3\2\2\2`\u0316\3\2\2\2"+
		"b\u031b\3\2\2\2d\u0324\3\2\2\2f\u0337\3\2\2\2h\u033f\3\2\2\2j\u0346\3"+
		"\2\2\2l\u0348\3\2\2\2n\u0360\3\2\2\2p\u0373\3\2\2\2r\u0375\3\2\2\2t\u037d"+
		"\3\2\2\2v\u038d\3\2\2\2x\u03a5\3\2\2\2z\u03aa\3\2\2\2|\u03b3\3\2\2\2~"+
		"\u03c0\3\2\2\2\u0080\u03cb\3\2\2\2\u0082\u03d1\3\2\2\2\u0084\u03d3\3\2"+
		"\2\2\u0086\u03d7\3\2\2\2\u0088\u03d9\3\2\2\2\u008a\u03db\3\2\2\2\u008c"+
		"\u03e2\3\2\2\2\u008e\u03eb\3\2\2\2\u0090\u03f5\3\2\2\2\u0092\u03fd\3\2"+
		"\2\2\u0094\u0404\3\2\2\2\u0096\u0406\3\2\2\2\u0098\u0416\3\2\2\2\u009a"+
		"\u041b\3\2\2\2\u009c\u042c\3\2\2\2\u009e\u0446\3\2\2\2\u00a0\u044a\3\2"+
		"\2\2\u00a2\u044c\3\2\2\2\u00a4\u0452\3\2\2\2\u00a6\u0454\3\2\2\2\u00a8"+
		"\u0458\3\2\2\2\u00aa\u045e\3\2\2\2\u00ac\u048b\3\2\2\2\u00ae\u048d\3\2"+
		"\2\2\u00b0\u048f\3\2\2\2\u00b2\u049b\3\2\2\2\u00b4\u04a1\3\2\2\2\u00b6"+
		"\u04a9\3\2\2\2\u00b8\u04ac\3\2\2\2\u00ba\u04b6\3\2\2\2\u00bc\u04c4\3\2"+
		"\2\2\u00be\u04c9\3\2\2\2\u00c0\u04d6\3\2\2\2\u00c2\u04d8\3\2\2\2\u00c4"+
		"\u04dd\3\2\2\2\u00c6\u0556\3\2\2\2\u00c8\u0558\3\2\2\2\u00ca\u0565\3\2"+
		"\2\2\u00cc\u056d\3\2\2\2\u00ce\u0570\3\2\2\2\u00d0\u0577\3\2\2\2\u00d2"+
		"\u0590\3\2\2\2\u00d4\u0593\3\2\2\2\u00d6\u05a7\3\2\2\2\u00d8\u05b5\3\2"+
		"\2\2\u00da\u05b9\3\2\2\2\u00dc\u05be\3\2\2\2\u00de\u05c9\3\2\2\2\u00e0"+
		"\u05cd\3\2\2\2\u00e2\u0600\3\2\2\2\u00e4\u065b\3\2\2\2\u00e6\u0667\3\2"+
		"\2\2\u00e8\u0681\3\2\2\2\u00ea\u0685\3\2\2\2\u00ec\u0699\3\2\2\2\u00ee"+
		"\u069b\3\2\2\2\u00f0\u06b1\3\2\2\2\u00f2\u06cd\3\2\2\2\u00f4\u06de\3\2"+
		"\2\2\u00f6\u06e3\3\2\2\2\u00f8\u06f8\3\2\2\2\u00fa\u0709\3\2\2\2\u00fc"+
		"\u070b\3\2\2\2\u00fe\u0727\3\2\2\2\u0100\u0729\3\2\2\2\u0102\u072d\3\2"+
		"\2\2\u0104\u0733\3\2\2\2\u0106\u0738\3\2\2\2\u0108\u073a\3\2\2\2\u010a"+
		"\u073e\3\2\2\2\u010c\u0749\3\2\2\2\u010e\u075d\3\2\2\2\u0110\u0771\3\2"+
		"\2\2\u0112\u0778\3\2\2\2\u0114\u077a\3\2\2\2\u0116\u011a\5\6\4\2\u0117"+
		"\u011a\5\4\3\2\u0118\u011a\5\b\5\2\u0119\u0116\3\2\2\2\u0119\u0117\3\2"+
		"\2\2\u0119\u0118\3\2\2\2\u011a\3\3\2\2\2\u011b\u011d\5 \21\2\u011c\u011b"+
		"\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u0121\3\2\2\2\u011e\u0120\5\"\22\2"+
		"\u011f\u011e\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122"+
		"\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0126\5$\23\2\u0125"+
		"\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2"+
		"\2\2\u0128\u0129\3\2\2\2\u0129\u012a\7\2\2\3\u012a\5\3\2\2\2\u012b\u012f"+
		"\5\"\22\2\u012c\u012f\5\u00bc_\2\u012d\u012f\5$\23\2\u012e\u012b\3\2\2"+
		"\2\u012e\u012c\3\2\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e"+
		"\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133"+
		"\u0134\7\2\2\3\u0134\7\3\2\2\2\u0135\u0138\5\"\22\2\u0136\u0138\5B\"\2"+
		"\u0137\u0135\3\2\2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137"+
		"\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c"+
		"\u013d\7\2\2\3\u013d\t\3\2\2\2\u013e\u0142\5\"\22\2\u013f\u0142\5B\"\2"+
		"\u0140\u0142\5\u00bc_\2\u0141\u013e\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2"+
		"\2\2\u0144\u0146\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0147\5\u00bc_\2\u0147"+
		"\u014d\5B\"\2\u0148\u014c\5\"\22\2\u0149\u014c\5B\"\2\u014a\u014c\5\u00bc"+
		"_\2\u014b\u0148\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014a\3\2\2\2\u014c"+
		"\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0163\3\2"+
		"\2\2\u014f\u014d\3\2\2\2\u0150\u0154\5\"\22\2\u0151\u0154\5B\"\2\u0152"+
		"\u0154\5\u00bc_\2\u0153\u0150\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0152"+
		"\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156"+
		"\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u0159\5B\"\2\u0159\u015f\5\u00bc"+
		"_\2\u015a\u015e\5\"\22\2\u015b\u015e\5B\"\2\u015c\u015e\5\u00bc_\2\u015d"+
		"\u015a\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015c\3\2\2\2\u015e\u0161\3\2"+
		"\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0163\3\2\2\2\u0161"+
		"\u015f\3\2\2\2\u0162\u0143\3\2\2\2\u0162\u0155\3\2\2\2\u0163\13\3\2\2"+
		"\2\u0164\u016e\5\16\b\2\u0165\u016a\7\u0085\2\2\u0166\u0167\7Z\2\2\u0167"+
		"\u0169\7[\2\2\u0168\u0166\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2"+
		"\2\2\u016a\u016b\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016d"+
		"\u0164\3\2\2\2\u016d\u0165\3\2\2\2\u016e\r\3\2\2\2\u016f\u0174\5\24\13"+
		"\2\u0170\u0171\7Z\2\2\u0171\u0173\7[\2\2\u0172\u0170\3\2\2\2\u0173\u0176"+
		"\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176"+
		"\u0174\3\2\2\2\u0177\u0178\b\b\1\2\u0178\17\3\2\2\2\u0179\u018d\5\22\n"+
		"\2\u017a\u017b\7\u0085\2\2\u017b\u017d\7V\2\2\u017c\u017e\5\u00e0q\2\u017d"+
		"\u017c\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u018d\7W"+
		"\2\2\u0180\u0181\7\63\2\2\u0181\u0183\7V\2\2\u0182\u0184\5\u00e0q\2\u0183"+
		"\u0182\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u018d\7W"+
		"\2\2\u0186\u0187\7\60\2\2\u0187\u0189\7V\2\2\u0188\u018a\5\u00e0q\2\u0189"+
		"\u0188\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d\7W"+
		"\2\2\u018c\u0179\3\2\2\2\u018c\u017a\3\2\2\2\u018c\u0180\3\2\2\2\u018c"+
		"\u0186\3\2\2\2\u018d\21\3\2\2\2\u018e\u018f\t\2\2\2\u018f\u0191\7V\2\2"+
		"\u0190\u0192\5\u00e0q\2\u0191\u0190\3\2\2\2\u0191\u0192\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193\u0194\7W\2\2\u0194\23\3\2\2\2\u0195\u019f\7\13\2"+
		"\2\u0196\u019f\7\20\2\2\u0197\u019f\7\r\2\2\u0198\u019f\7-\2\2\u0199\u019f"+
		"\7#\2\2\u019a\u019f\7%\2\2\u019b\u019f\7\34\2\2\u019c\u019f\7\26\2\2\u019d"+
		"\u019f\5\26\f\2\u019e\u0195\3\2\2\2\u019e\u0196\3\2\2\2\u019e\u0197\3"+
		"\2\2\2\u019e\u0198\3\2\2\2\u019e\u0199\3\2\2\2\u019e\u019a\3\2\2\2\u019e"+
		"\u019b\3\2\2\2\u019e\u019c\3\2\2\2\u019e\u019d\3\2\2\2\u019f\25\3\2\2"+
		"\2\u01a0\u01a1\7\3\2\2\u01a1\27\3\2\2\2\u01a2\u01a5\7\u0085\2\2\u01a3"+
		"\u01a5\5\26\f\2\u01a4\u01a2\3\2\2\2\u01a4\u01a3\3\2\2\2\u01a5\u01ad\3"+
		"\2\2\2\u01a6\u01a9\7^\2\2\u01a7\u01aa\7\u0085\2\2\u01a8\u01aa\5\26\f\2"+
		"\u01a9\u01a7\3\2\2\2\u01a9\u01a8\3\2\2\2\u01aa\u01ac\3\2\2\2\u01ab\u01a6"+
		"\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae"+
		"\31\3\2\2\2\u01af\u01ad\3\2\2\2\u01b0\u01b8\5\u0088E\2\u01b1\u01b8\5\u008a"+
		"F\2\u01b2\u01b8\7\b\2\2\u01b3\u01b8\5\u0086D\2\u01b4\u01b8\7Q\2\2\u01b5"+
		"\u01b8\7U\2\2\u01b6\u01b8\5\34\17\2\u01b7\u01b0\3\2\2\2\u01b7\u01b1\3"+
		"\2\2\2\u01b7\u01b2\3\2\2\2\u01b7\u01b3\3\2\2\2\u01b7\u01b4\3\2\2\2\u01b7"+
		"\u01b5\3\2\2\2\u01b7\u01b6\3\2\2\2\u01b8\33\3\2\2\2\u01b9\u01ba\7\4\2"+
		"\2\u01ba\35\3\2\2\2\u01bb\u01bd\5 \21\2\u01bc\u01bb\3\2\2\2\u01bc\u01bd"+
		"\3\2\2\2\u01bd\u01c2\3\2\2\2\u01be\u01c1\5\"\22\2\u01bf\u01c1\7\\\2\2"+
		"\u01c0\u01be\3\2\2\2\u01c0\u01bf\3\2\2\2\u01c1\u01c4\3\2\2\2\u01c2\u01c0"+
		"\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c9\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5"+
		"\u01c8\5$\23\2\u01c6\u01c8\7\\\2\2\u01c7\u01c5\3\2\2\2\u01c7\u01c6\3\2"+
		"\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca"+
		"\u01cc\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01d1\7\2\2\3\u01cd\u01ce\5\u00a8"+
		"U\2\u01ce\u01cf\7\2\2\3\u01cf\u01d1\3\2\2\2\u01d0\u01bc\3\2\2\2\u01d0"+
		"\u01cd\3\2\2\2\u01d1\37\3\2\2\2\u01d2\u01d4\5\u008eH\2\u01d3\u01d2\3\2"+
		"\2\2\u01d4\u01d7\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6"+
		"\u01d8\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d8\u01d9\7(\2\2\u01d9\u01da\5\30"+
		"\r\2\u01da\u01db\7\\\2\2\u01db!\3\2\2\2\u01dc\u01de\7!\2\2\u01dd\u01df"+
		"\7.\2\2\u01de\u01dd\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0"+
		"\u01e3\5\30\r\2\u01e1\u01e2\7^\2\2\u01e2\u01e4\7p\2\2\u01e3\u01e1\3\2"+
		"\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\7\\\2\2\u01e6"+
		"#\3\2\2\2\u01e7\u01e9\5(\25\2\u01e8\u01e7\3\2\2\2\u01e9\u01ec\3\2\2\2"+
		"\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01f2\3\2\2\2\u01ec\u01ea"+
		"\3\2\2\2\u01ed\u01f3\5,\27\2\u01ee\u01f3\5\64\33\2\u01ef\u01f3\5<\37\2"+
		"\u01f0\u01f3\5\u0098M\2\u01f1\u01f3\5\u00b0Y\2\u01f2\u01ed\3\2\2\2\u01f2"+
		"\u01ee\3\2\2\2\u01f2\u01ef\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f2\u01f1\3\2"+
		"\2\2\u01f3%\3\2\2\2\u01f4\u01fa\5(\25\2\u01f5\u01fa\7&\2\2\u01f6\u01fa"+
		"\7\62\2\2\u01f7\u01fa\7\66\2\2\u01f8\u01fa\79\2\2\u01f9\u01f4\3\2\2\2"+
		"\u01f9\u01f5\3\2\2\2\u01f9\u01f6\3\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01f8"+
		"\3\2\2\2\u01fa\'\3\2\2\2\u01fb\u0206\5\u008eH\2\u01fc\u0206\7+\2\2\u01fd"+
		"\u0206\7*\2\2\u01fe\u0206\7)\2\2\u01ff\u0206\7.\2\2\u0200\u0206\7\t\2"+
		"\2\u0201\u0206\7\32\2\2\u0202\u0206\7/\2\2\u0203\u0206\7H\2\2\u0204\u0206"+
		"\7J\2\2\u0205\u01fb\3\2\2\2\u0205\u01fc\3\2\2\2\u0205\u01fd\3\2\2\2\u0205"+
		"\u01fe\3\2\2\2\u0205\u01ff\3\2\2\2\u0205\u0200\3\2\2\2\u0205\u0201\3\2"+
		"\2\2\u0205\u0202\3\2\2\2\u0205\u0203\3\2\2\2\u0205\u0204\3\2\2\2\u0206"+
		")\3\2\2\2\u0207\u020a\7\32\2\2\u0208\u020a\5\u008eH\2\u0209\u0207\3\2"+
		"\2\2\u0209\u0208\3\2\2\2\u020a+\3\2\2\2\u020b\u020c\7\21\2\2\u020c\u020e"+
		"\5\u00c0a\2\u020d\u020f\5.\30\2\u020e\u020d\3\2\2\2\u020e\u020f\3\2\2"+
		"\2\u020f\u0212\3\2\2\2\u0210\u0211\7\31\2\2\u0211\u0213\5\u010c\u0087"+
		"\2\u0212\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0216\3\2\2\2\u0214\u0215"+
		"\7 \2\2\u0215\u0217\5\u010a\u0086\2\u0216\u0214\3\2\2\2\u0216\u0217\3"+
		"\2\2\2\u0217\u021a\3\2\2\2\u0218\u0219\7I\2\2\u0219\u021b\5\u010a\u0086"+
		"\2\u021a\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d"+
		"\5> \2\u021d-\3\2\2\2\u021e\u021f\7a\2\2\u021f\u0224\5\60\31\2\u0220\u0221"+
		"\7]\2\2\u0221\u0223\5\60\31\2\u0222\u0220\3\2\2\2\u0223\u0226\3\2\2\2"+
		"\u0224\u0222\3\2\2\2\u0224\u0225\3\2\2\2\u0225\u0227\3\2\2\2\u0226\u0224"+
		"\3\2\2\2\u0227\u0228\7`\2\2\u0228/\3\2\2\2\u0229\u022b\5\u008eH\2\u022a"+
		"\u0229\3\2\2\2\u022b\u022e\3\2\2\2\u022c\u022a\3\2\2\2\u022c\u022d\3\2"+
		"\2\2\u022d\u022f\3\2\2\2\u022e\u022c\3\2\2\2\u022f\u0238\5\u00c0a\2\u0230"+
		"\u0234\7\31\2\2\u0231\u0233\5\u008eH\2\u0232\u0231\3\2\2\2\u0233\u0236"+
		"\3\2\2\2\u0234\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0237\3\2\2\2\u0236"+
		"\u0234\3\2\2\2\u0237\u0239\5\62\32\2\u0238\u0230\3\2\2\2\u0238\u0239\3"+
		"\2\2\2\u0239\61\3\2\2\2\u023a\u023f\5\u010c\u0087\2\u023b\u023c\7r\2\2"+
		"\u023c\u023e\5\u010c\u0087\2\u023d\u023b\3\2\2\2\u023e\u0241\3\2\2\2\u023f"+
		"\u023d\3\2\2\2\u023f\u0240\3\2\2\2\u0240\63\3\2\2\2\u0241\u023f\3\2\2"+
		"\2\u0242\u0243\7\30\2\2\u0243\u0246\5\u00c0a\2\u0244\u0245\7 \2\2\u0245"+
		"\u0247\5\u010a\u0086\2\u0246\u0244\3\2\2\2\u0246\u0247\3\2\2\2\u0247\u0248"+
		"\3\2\2\2\u0248\u024a\7X\2\2\u0249\u024b\5\66\34\2\u024a\u0249\3\2\2\2"+
		"\u024a\u024b\3\2\2\2\u024b\u024d\3\2\2\2\u024c\u024e\7]\2\2\u024d\u024c"+
		"\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u0250\3\2\2\2\u024f\u0251\5:\36\2\u0250"+
		"\u024f\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0252\3\2\2\2\u0252\u0253\7Y"+
		"\2\2\u0253\65\3\2\2\2\u0254\u0259\58\35\2\u0255\u0256\7]\2\2\u0256\u0258"+
		"\58\35\2\u0257\u0255\3\2\2\2\u0258\u025b\3\2\2\2\u0259\u0257\3\2\2\2\u0259"+
		"\u025a\3\2\2\2\u025a\67\3\2\2\2\u025b\u0259\3\2\2\2\u025c\u025e\5\u008e"+
		"H\2\u025d\u025c\3\2\2\2\u025e\u0261\3\2\2\2\u025f\u025d\3\2\2\2\u025f"+
		"\u0260\3\2\2\2\u0260\u0262\3\2\2\2\u0261\u025f\3\2\2\2\u0262\u0264\5\u00c0"+
		"a\2\u0263\u0265\5\u0114\u008b\2\u0264\u0263\3\2\2\2\u0264\u0265\3\2\2"+
		"\2\u0265\u0267\3\2\2\2\u0266\u0268\5> \2\u0267\u0266\3\2\2\2\u0267\u0268"+
		"\3\2\2\2\u02689\3\2\2\2\u0269\u026d\7\\\2\2\u026a\u026c\5B\"\2\u026b\u026a"+
		"\3\2\2\2\u026c\u026f\3\2\2\2\u026d\u026b\3\2\2\2\u026d\u026e\3\2\2\2\u026e"+
		";\3\2\2\2\u026f\u026d\3\2\2\2\u0270\u0271\7$\2\2\u0271\u0273\5\u00c0a"+
		"\2\u0272\u0274\5.\30\2\u0273\u0272\3\2\2\2\u0273\u0274\3\2\2\2\u0274\u0277"+
		"\3\2\2\2\u0275\u0276\7\31\2\2\u0276\u0278\5\u010a\u0086\2\u0277\u0275"+
		"\3\2\2\2\u0277\u0278\3\2\2\2\u0278\u027b\3\2\2\2\u0279\u027a\7I\2\2\u027a"+
		"\u027c\5\u010a\u0086\2\u027b\u0279\3\2\2\2\u027b\u027c\3\2\2\2\u027c\u027d"+
		"\3\2\2\2\u027d\u027e\5@!\2\u027e=\3\2\2\2\u027f\u0283\7X\2\2\u0280\u0282"+
		"\5B\"\2\u0281\u0280\3\2\2\2\u0282\u0285\3\2\2\2\u0283\u0281\3\2\2\2\u0283"+
		"\u0284\3\2\2\2\u0284\u0286\3\2\2\2\u0285\u0283\3\2\2\2\u0286\u0287\7Y"+
		"\2\2\u0287?\3\2\2\2\u0288\u028c\7X\2\2\u0289\u028b\5V,\2\u028a\u0289\3"+
		"\2\2\2\u028b\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d"+
		"\u028f\3\2\2\2\u028e\u028c\3\2\2\2\u028f\u0290\7Y\2\2\u0290A\3\2\2\2\u0291"+
		"\u029e\7\\\2\2\u0292\u0294\7.\2\2\u0293\u0292\3\2\2\2\u0293\u0294\3\2"+
		"\2\2\u0294\u0295\3\2\2\2\u0295\u029e\5\u00ba^\2\u0296\u0298\5&\24\2\u0297"+
		"\u0296\3\2\2\2\u0298\u029b\3\2\2\2\u0299\u0297\3\2\2\2\u0299\u029a\3\2"+
		"\2\2\u029a\u029c\3\2\2\2\u029b\u0299\3\2\2\2\u029c\u029e\5D#\2\u029d\u0291"+
		"\3\2\2\2\u029d\u0293\3\2\2\2\u029d\u0299\3\2\2\2\u029eC\3\2\2\2\u029f"+
		"\u02aa\5\u00b0Y\2\u02a0\u02aa\5F$\2\u02a1\u02aa\5L\'\2\u02a2\u02aa\5T"+
		"+\2\u02a3\u02aa\5P)\2\u02a4\u02aa\5N(\2\u02a5\u02aa\5<\37\2\u02a6\u02aa"+
		"\5\u0098M\2\u02a7\u02aa\5,\27\2\u02a8\u02aa\5\64\33\2\u02a9\u029f\3\2"+
		"\2\2\u02a9\u02a0\3\2\2\2\u02a9\u02a1\3\2\2\2\u02a9\u02a2\3\2\2\2\u02a9"+
		"\u02a3\3\2\2\2\u02a9\u02a4\3\2\2\2\u02a9\u02a5\3\2\2\2\u02a9\u02a6\3\2"+
		"\2\2\u02a9\u02a7\3\2\2\2\u02a9\u02a8\3\2\2\2\u02aaE\3\2\2\2\u02ab\u02ac"+
		"\5J&\2\u02ac\u02ad\5\u00c0a\2\u02ad\u02b2\5t;\2\u02ae\u02af\7Z\2\2\u02af"+
		"\u02b1\7[\2\2\u02b0\u02ae\3\2\2\2\u02b1\u02b4\3\2\2\2\u02b2\u02b0\3\2"+
		"\2\2\u02b2\u02b3\3\2\2\2\u02b3\u02b7\3\2\2\2\u02b4\u02b2\3\2\2\2\u02b5"+
		"\u02b6\7\65\2\2\u02b6\u02b8\5r:\2\u02b7\u02b5\3\2\2\2\u02b7\u02b8\3\2"+
		"\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02ba\5H%\2\u02baG\3\2\2\2\u02bb\u02be"+
		"\5\u00ba^\2\u02bc\u02be\7\\\2\2\u02bd\u02bb\3\2\2\2\u02bd\u02bc\3\2\2"+
		"\2\u02beI\3\2\2\2\u02bf\u02c2\5\u010c\u0087\2\u02c0\u02c2\78\2\2\u02c1"+
		"\u02bf\3\2\2\2\u02c1\u02c0\3\2\2\2\u02c2K\3\2\2\2\u02c3\u02c4\5.\30\2"+
		"\u02c4\u02c5\5F$\2\u02c5M\3\2\2\2\u02c6\u02c7\5.\30\2\u02c7\u02c8\5P)"+
		"\2\u02c8O\3\2\2\2\u02c9\u02ca\5\u00c0a\2\u02ca\u02cd\5t;\2\u02cb\u02cc"+
		"\7\65\2\2\u02cc\u02ce\5r:\2\u02cd\u02cb\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce"+
		"\u02cf\3\2\2\2\u02cf\u02d0\5\u00ba^\2\u02d0Q\3\2\2\2\u02d1\u02d3\5&\24"+
		"\2\u02d2\u02d1\3\2\2\2\u02d3\u02d6\3\2\2\2\u02d4\u02d2\3\2\2\2\u02d4\u02d5"+
		"\3\2\2\2\u02d5\u02d7\3\2\2\2\u02d6\u02d4\3\2\2\2\u02d7\u02d8\5\u00c0a"+
		"\2\u02d8\u02d9\5\u00ba^\2\u02d9S\3\2\2\2\u02da\u02db\5\u010c\u0087\2\u02db"+
		"\u02dc\5f\64\2\u02dc\u02dd\7\\\2\2\u02ddU\3\2\2\2\u02de\u02e0\5&\24\2"+
		"\u02df\u02de\3\2\2\2\u02e0\u02e3\3\2\2\2\u02e1\u02df\3\2\2\2\u02e1\u02e2"+
		"\3\2\2\2\u02e2\u02e4\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e4\u02e7\5X-\2\u02e5"+
		"\u02e7\7\\\2\2\u02e6\u02e1\3\2\2\2\u02e6\u02e5\3\2\2\2\u02e7W\3\2\2\2"+
		"\u02e8\u02f1\5\u00b0Y\2\u02e9\u02f1\5Z.\2\u02ea\u02f1\5^\60\2\u02eb\u02f1"+
		"\5b\62\2\u02ec\u02f1\5<\37\2\u02ed\u02f1\5\u0098M\2\u02ee\u02f1\5,\27"+
		"\2\u02ef\u02f1\5\64\33\2\u02f0\u02e8\3\2\2\2\u02f0\u02e9\3\2\2\2\u02f0"+
		"\u02ea\3\2\2\2\u02f0\u02eb\3\2\2\2\u02f0\u02ec\3\2\2\2\u02f0\u02ed\3\2"+
		"\2\2\u02f0\u02ee\3\2\2\2\u02f0\u02ef\3\2\2\2\u02f1Y\3\2\2\2\u02f2\u02f3"+
		"\5\u010c\u0087\2\u02f3\u02f8\5\\/\2\u02f4\u02f5\7]\2\2\u02f5\u02f7\5\\"+
		"/\2\u02f6\u02f4\3\2\2\2\u02f7\u02fa\3\2\2\2\u02f8\u02f6\3\2\2\2\u02f8"+
		"\u02f9\3\2\2\2\u02f9\u02fb\3\2\2\2\u02fa\u02f8\3\2\2\2\u02fb\u02fc\7\\"+
		"\2\2\u02fc[\3\2\2\2\u02fd\u0302\5\u00c0a\2\u02fe\u02ff\7Z\2\2\u02ff\u0301"+
		"\7[\2\2\u0300\u02fe\3\2\2\2\u0301\u0304\3\2\2\2\u0302\u0300\3\2\2\2\u0302"+
		"\u0303\3\2\2\2\u0303\u0305\3\2\2\2\u0304\u0302\3\2\2\2\u0305\u0306\7_"+
		"\2\2\u0306\u0307\5j\66\2\u0307]\3\2\2\2\u0308\u030a\5`\61\2\u0309\u0308"+
		"\3\2\2\2\u030a\u030d\3\2\2\2\u030b\u0309\3\2\2\2\u030b\u030c\3\2\2\2\u030c"+
		"\u030e\3\2\2\2\u030d\u030b\3\2\2\2\u030e\u030f\5d\63\2\u030f_\3\2\2\2"+
		"\u0310\u0317\5\u008eH\2\u0311\u0317\7+\2\2\u0312\u0317\7\t\2\2\u0313\u0317"+
		"\7\24\2\2\u0314\u0317\7.\2\2\u0315\u0317\7/\2\2\u0316\u0310\3\2\2\2\u0316"+
		"\u0311\3\2\2\2\u0316\u0312\3\2\2\2\u0316\u0313\3\2\2\2\u0316\u0314\3\2"+
		"\2\2\u0316\u0315\3\2\2\2\u0317a\3\2\2\2\u0318\u031a\5`\61\2\u0319\u0318"+
		"\3\2\2\2\u031a\u031d\3\2\2\2\u031b\u0319\3\2\2\2\u031b\u031c\3\2\2\2\u031c"+
		"\u031e\3\2\2\2\u031d\u031b\3\2\2\2\u031e\u031f\5.\30\2\u031f\u0320\5d"+
		"\63\2\u0320c\3\2\2\2\u0321\u0323\5\u008eH\2\u0322\u0321\3\2\2\2\u0323"+
		"\u0326\3\2\2\2\u0324\u0322\3\2\2\2\u0324\u0325\3\2\2\2\u0325\u0327\3\2"+
		"\2\2\u0326\u0324\3\2\2\2\u0327\u0328\5J&\2\u0328\u0329\5\u00c0a\2\u0329"+
		"\u032e\5t;\2\u032a\u032b\7Z\2\2\u032b\u032d\7[\2\2\u032c\u032a\3\2\2\2"+
		"\u032d\u0330\3\2\2\2\u032e\u032c\3\2\2\2\u032e\u032f\3\2\2\2\u032f\u0333"+
		"\3\2\2\2\u0330\u032e\3\2\2\2\u0331\u0332\7\65\2\2\u0332\u0334\5r:\2\u0333"+
		"\u0331\3\2\2\2\u0333\u0334\3\2\2\2\u0334\u0335\3\2\2\2\u0335\u0336\5H"+
		"%\2\u0336e\3\2\2\2\u0337\u033c\5h\65\2\u0338\u0339\7]\2\2\u0339\u033b"+
		"\5h\65\2\u033a\u0338\3\2\2\2\u033b\u033e\3\2\2\2\u033c\u033a\3\2\2\2\u033c"+
		"\u033d\3\2\2\2\u033dg\3\2\2\2\u033e\u033c\3\2\2\2\u033f\u0342\5\f\7\2"+
		"\u0340\u0341\7_\2\2\u0341\u0343\5j\66\2\u0342\u0340\3\2\2\2\u0342\u0343"+
		"\3\2\2\2\u0343i\3\2\2\2\u0344\u0347\5l\67\2\u0345\u0347\5\u00e2r\2\u0346"+
		"\u0344\3\2\2\2\u0346\u0345\3\2\2\2\u0347k\3\2\2\2\u0348\u0354\7X\2\2\u0349"+
		"\u034e\5j\66\2\u034a\u034b\7]\2\2\u034b\u034d\5j\66\2\u034c\u034a\3\2"+
		"\2\2\u034d\u0350\3\2\2\2\u034e\u034c\3\2\2\2\u034e\u034f\3\2\2\2\u034f"+
		"\u0352\3\2\2\2\u0350\u034e\3\2\2\2\u0351\u0353\7]\2\2\u0352\u0351\3\2"+
		"\2\2\u0352\u0353\3\2\2\2\u0353\u0355\3\2\2\2\u0354\u0349\3\2\2\2\u0354"+
		"\u0355\3\2\2\2\u0355\u0356\3\2\2\2\u0356\u0357\7Y\2\2\u0357m\3\2\2\2\u0358"+
		"\u035a\5\u00c0a\2\u0359\u035b\5\u010e\u0088\2\u035a\u0359\3\2\2\2\u035a"+
		"\u035b\3\2\2\2\u035b\u035c\3\2\2\2\u035c\u035d\7^\2\2\u035d\u035f\3\2"+
		"\2\2\u035e\u0358\3\2\2\2\u035f\u0362\3\2\2\2\u0360\u035e\3\2\2\2\u0360"+
		"\u0361\3\2\2\2\u0361\u0363\3\2\2\2\u0362\u0360\3\2\2\2\u0363\u0365\5\u00c2"+
		"b\2\u0364\u0366\5\u010e\u0088\2\u0365\u0364\3\2\2\2\u0365\u0366\3\2\2"+
		"\2\u0366o\3\2\2\2\u0367\u0374\5\u010c\u0087\2\u0368\u036a\5\u008eH\2\u0369"+
		"\u0368\3\2\2\2\u036a\u036d\3\2\2\2\u036b\u0369\3\2\2\2\u036b\u036c\3\2"+
		"\2\2\u036c\u036e\3\2\2\2\u036d\u036b\3\2\2\2\u036e\u0371\7d\2\2\u036f"+
		"\u0370\t\3\2\2\u0370\u0372\5\u010c\u0087\2\u0371\u036f\3\2\2\2\u0371\u0372"+
		"\3\2\2\2\u0372\u0374\3\2\2\2\u0373\u0367\3\2\2\2\u0373\u036b\3\2\2\2\u0374"+
		"q\3\2\2\2\u0375\u037a\5\30\r\2\u0376\u0377\7]\2\2\u0377\u0379\5\30\r\2"+
		"\u0378\u0376\3\2\2\2\u0379\u037c\3\2\2\2\u037a\u0378\3\2\2\2\u037a\u037b"+
		"\3\2\2\2\u037bs\3\2\2\2\u037c\u037a\3\2\2\2\u037d\u0389\7V\2\2\u037e\u0380"+
		"\5v<\2\u037f\u037e\3\2\2\2\u037f\u0380\3\2\2\2\u0380\u038a\3\2\2\2\u0381"+
		"\u0384\5v<\2\u0382\u0383\7]\2\2\u0383\u0385\5x=\2\u0384\u0382\3\2\2\2"+
		"\u0384\u0385\3\2\2\2\u0385\u038a\3\2\2\2\u0386\u0388\5x=\2\u0387\u0386"+
		"\3\2\2\2\u0387\u0388\3\2\2\2\u0388\u038a\3\2\2\2\u0389\u037f\3\2\2\2\u0389"+
		"\u0381\3\2\2\2\u0389\u0387\3\2\2\2\u038a\u038b\3\2\2\2\u038b\u038c\7W"+
		"\2\2\u038cu\3\2\2\2\u038d\u0393\5\u010c\u0087\2\u038e\u038f\5\u00c0a\2"+
		"\u038f\u0390\7^\2\2\u0390\u0392\3\2\2\2\u0391\u038e\3\2\2\2\u0392\u0395"+
		"\3\2\2\2\u0393\u0391\3\2\2\2\u0393\u0394\3\2\2\2\u0394\u0396\3\2\2\2\u0395"+
		"\u0393\3\2\2\2\u0396\u0397\7\63\2\2\u0397w\3\2\2\2\u0398\u039d\5z>\2\u0399"+
		"\u039a\7]\2\2\u039a\u039c\5z>\2\u039b\u0399\3\2\2\2\u039c\u039f\3\2\2"+
		"\2\u039d\u039b\3\2\2\2\u039d\u039e\3\2\2\2\u039e\u03a2\3\2\2\2\u039f\u039d"+
		"\3\2\2\2\u03a0\u03a1\7]\2\2\u03a1\u03a3\5|?\2\u03a2\u03a0\3\2\2\2\u03a2"+
		"\u03a3\3\2\2\2\u03a3\u03a6\3\2\2\2\u03a4\u03a6\5|?\2\u03a5\u0398\3\2\2"+
		"\2\u03a5\u03a4\3\2\2\2\u03a6y\3\2\2\2\u03a7\u03a9\5*\26\2\u03a8\u03a7"+
		"\3\2\2\2\u03a9\u03ac\3\2\2\2\u03aa\u03a8\3\2\2\2\u03aa\u03ab\3\2\2\2\u03ab"+
		"\u03ad\3\2\2\2\u03ac\u03aa\3\2\2\2\u03ad\u03ae\5\u010c\u0087\2\u03ae\u03af"+
		"\5\f\7\2\u03af{\3\2\2\2\u03b0\u03b2\5*\26\2\u03b1\u03b0\3\2\2\2\u03b2"+
		"\u03b5\3\2\2\2\u03b3\u03b1\3\2\2\2\u03b3\u03b4\3\2\2\2\u03b4\u03b6\3\2"+
		"\2\2\u03b5\u03b3\3\2\2\2\u03b6\u03ba\5\u010c\u0087\2\u03b7\u03b9\5\u008e"+
		"H\2\u03b8\u03b7\3\2\2\2\u03b9\u03bc\3\2\2\2\u03ba\u03b8\3\2\2\2\u03ba"+
		"\u03bb\3\2\2\2\u03bb\u03bd\3\2\2\2\u03bc\u03ba\3\2\2\2\u03bd\u03be\7\u0084"+
		"\2\2\u03be\u03bf\5\f\7\2\u03bf}\3\2\2\2\u03c0\u03c5\5\u0080A\2\u03c1\u03c2"+
		"\7]\2\2\u03c2\u03c4\5\u0080A\2\u03c3\u03c1\3\2\2\2\u03c4\u03c7\3\2\2\2"+
		"\u03c5\u03c3\3\2\2\2\u03c5\u03c6\3\2\2\2\u03c6\177\3\2\2\2\u03c7\u03c5"+
		"\3\2\2\2\u03c8\u03ca\5*\26\2\u03c9\u03c8\3\2\2\2\u03ca\u03cd\3\2\2\2\u03cb"+
		"\u03c9\3\2\2\2\u03cb\u03cc\3\2\2\2\u03cc\u03ce\3\2\2\2\u03cd\u03cb\3\2"+
		"\2\2\u03ce\u03cf\7E\2\2\u03cf\u03d0\5\u00c0a\2\u03d0\u0081\3\2\2\2\u03d1"+
		"\u03d2\7R\2\2\u03d2\u0083\3\2\2\2\u03d3\u03d4\7S\2\2\u03d4\u0085\3\2\2"+
		"\2\u03d5\u03d8\5\u0082B\2\u03d6\u03d8\5\u0084C\2\u03d7\u03d5\3\2\2\2\u03d7"+
		"\u03d6\3\2\2\2\u03d8\u0087\3\2\2\2\u03d9\u03da\t\4\2\2\u03da\u0089\3\2"+
		"\2\2\u03db\u03dc\t\5\2\2\u03dc\u008b\3\2\2\2\u03dd\u03de\5\u00c0a\2\u03de"+
		"\u03df\7^\2\2\u03df\u03e1\3\2\2\2\u03e0\u03dd\3\2\2\2\u03e1\u03e4\3\2"+
		"\2\2\u03e2\u03e0\3\2\2\2\u03e2\u03e3\3\2\2\2\u03e3\u03e5\3\2\2\2\u03e4"+
		"\u03e2\3\2\2\2\u03e5\u03e6\7\u0083\2\2\u03e6\u03e7\5\u00c0a\2\u03e7\u008d"+
		"\3\2\2\2\u03e8\u03e9\7\u0083\2\2\u03e9\u03ec\5\30\r\2\u03ea\u03ec\5\u008c"+
		"G\2\u03eb\u03e8\3\2\2\2\u03eb\u03ea\3\2\2\2\u03ec\u03f3\3\2\2\2\u03ed"+
		"\u03f0\7V\2\2\u03ee\u03f1\5\u0090I\2\u03ef\u03f1\5\u0094K\2\u03f0\u03ee"+
		"\3\2\2\2\u03f0\u03ef\3\2\2\2\u03f0\u03f1\3\2\2\2\u03f1\u03f2\3\2\2\2\u03f2"+
		"\u03f4\7W\2\2\u03f3\u03ed\3\2\2\2\u03f3\u03f4\3\2\2\2\u03f4\u008f\3\2"+
		"\2\2\u03f5\u03fa\5\u0092J\2\u03f6\u03f7\7]\2\2\u03f7\u03f9\5\u0092J\2"+
		"\u03f8\u03f6\3\2\2\2\u03f9\u03fc\3\2\2\2\u03fa\u03f8\3\2\2\2\u03fa\u03fb"+
		"\3\2\2\2\u03fb\u0091\3\2\2\2\u03fc\u03fa\3\2\2\2\u03fd\u03fe\5\u00c0a"+
		"\2\u03fe\u03ff\7_\2\2\u03ff\u0400\5\u0094K\2\u0400\u0093\3\2\2\2\u0401"+
		"\u0405\5\u00e2r\2\u0402\u0405\5\u008eH\2\u0403\u0405\5\u0096L\2\u0404"+
		"\u0401\3\2\2\2\u0404\u0402\3\2\2\2\u0404\u0403\3\2\2\2\u0405\u0095\3\2"+
		"\2\2\u0406\u040f\7X\2\2\u0407\u040c\5\u0094K\2\u0408\u0409\7]\2\2\u0409"+
		"\u040b\5\u0094K\2\u040a\u0408\3\2\2\2\u040b\u040e\3\2\2\2\u040c\u040a"+
		"\3\2\2\2\u040c\u040d\3\2\2\2\u040d\u0410\3\2\2\2\u040e\u040c\3\2\2\2\u040f"+
		"\u0407\3\2\2\2\u040f\u0410\3\2\2\2\u0410\u0412\3\2\2\2\u0411\u0413\7]"+
		"\2\2\u0412\u0411\3\2\2\2\u0412\u0413\3\2\2\2\u0413\u0414\3\2\2\2\u0414"+
		"\u0415\7Y\2\2\u0415\u0097\3\2\2\2\u0416\u0417\7\u0083\2\2\u0417\u0418"+
		"\7$\2\2\u0418\u0419\5\u00c0a\2\u0419\u041a\5\u009aN\2\u041a\u0099\3\2"+
		"\2\2\u041b\u041f\7X\2\2\u041c\u041e\5\u009cO\2\u041d\u041c\3\2\2\2\u041e"+
		"\u0421\3\2\2\2\u041f\u041d\3\2\2\2\u041f\u0420\3\2\2\2\u0420\u0422\3\2"+
		"\2\2\u0421\u041f\3\2\2\2\u0422\u0423\7Y\2\2\u0423\u009b\3\2\2\2\u0424"+
		"\u0426\5&\24\2\u0425\u0424\3\2\2\2\u0426\u0429\3\2\2\2\u0427\u0425\3\2"+
		"\2\2\u0427\u0428\3\2\2\2\u0428\u042a\3\2\2\2\u0429\u0427\3\2\2\2\u042a"+
		"\u042d\5\u009eP\2\u042b\u042d\7\\\2\2\u042c\u0427\3\2\2\2\u042c\u042b"+
		"\3\2\2\2\u042d\u009d\3\2\2\2\u042e\u042f\5\u010c\u0087\2\u042f\u0430\5"+
		"\u00a0Q\2\u0430\u0431\7\\\2\2\u0431\u0447\3\2\2\2\u0432\u0434\5,\27\2"+
		"\u0433\u0435\7\\\2\2\u0434\u0433\3\2\2\2\u0434\u0435\3\2\2\2\u0435\u0447"+
		"\3\2\2\2\u0436\u0438\5<\37\2\u0437\u0439\7\\\2\2\u0438\u0437\3\2\2\2\u0438"+
		"\u0439\3\2\2\2\u0439\u0447\3\2\2\2\u043a\u043c\5\64\33\2\u043b\u043d\7"+
		"\\\2\2\u043c\u043b\3\2\2\2\u043c\u043d\3\2\2\2\u043d\u0447\3\2\2\2\u043e"+
		"\u0440\5\u0098M\2\u043f\u0441\7\\\2\2\u0440\u043f\3\2\2\2\u0440\u0441"+
		"\3\2\2\2\u0441\u0447\3\2\2\2\u0442\u0444\5\u00b0Y\2\u0443\u0445\7\\\2"+
		"\2\u0444\u0443\3\2\2\2\u0444\u0445\3\2\2\2\u0445\u0447\3\2\2\2\u0446\u042e"+
		"\3\2\2\2\u0446\u0432\3\2\2\2\u0446\u0436\3\2\2\2\u0446\u043a\3\2\2\2\u0446"+
		"\u043e\3\2\2\2\u0446\u0442\3\2\2\2\u0447\u009f\3\2\2\2\u0448\u044b\5\u00a2"+
		"R\2\u0449\u044b\5\u00a4S\2\u044a\u0448\3\2\2\2\u044a\u0449\3\2\2\2\u044b"+
		"\u00a1\3\2\2\2\u044c\u044d\5\u00c0a\2\u044d\u044e\7V\2\2\u044e\u0450\7"+
		"W\2\2\u044f\u0451\5\u00a6T\2\u0450\u044f\3\2\2\2\u0450\u0451\3\2\2\2\u0451"+
		"\u00a3\3\2\2\2\u0452\u0453\5f\64\2\u0453\u00a5\3\2\2\2\u0454\u0455\7\24"+
		"\2\2\u0455\u0456\5\u0094K\2\u0456\u00a7\3\2\2\2\u0457\u0459\7<\2\2\u0458"+
		"\u0457\3\2\2\2\u0458\u0459\3\2\2\2\u0459\u045a\3\2\2\2\u045a\u045b\7;"+
		"\2\2\u045b\u045c\5\30\r\2\u045c\u045d\5\u00aaV\2\u045d\u00a9\3\2\2\2\u045e"+
		"\u0462\7X\2\2\u045f\u0461\5\u00acW\2\u0460\u045f\3\2\2\2\u0461\u0464\3"+
		"\2\2\2\u0462\u0460\3\2\2\2\u0462\u0463\3\2\2\2\u0463\u0465\3\2\2\2\u0464"+
		"\u0462\3\2\2\2\u0465\u0466\7Y\2\2\u0466\u00ab\3\2\2\2\u0467\u046b\7=\2"+
		"\2\u0468\u046a\5\u00aeX\2\u0469\u0468\3\2\2\2\u046a\u046d\3\2\2\2\u046b"+
		"\u0469\3\2\2\2\u046b\u046c\3\2\2\2\u046c\u046e\3\2\2\2\u046d\u046b\3\2"+
		"\2\2\u046e\u046f\5\30\r\2\u046f\u0470\7\\\2\2\u0470\u048c\3\2\2\2\u0471"+
		"\u0472\7>\2\2\u0472\u0475\5\30\r\2\u0473\u0474\7@\2\2\u0474\u0476\5\30"+
		"\r\2\u0475\u0473\3\2\2\2\u0475\u0476\3\2\2\2\u0476\u0477\3\2\2\2\u0477"+
		"\u0478\7\\\2\2\u0478\u048c\3\2\2\2\u0479\u047a\7?\2\2\u047a\u047d\5\30"+
		"\r\2\u047b\u047c\7@\2\2\u047c\u047e\5\30\r\2\u047d\u047b\3\2\2\2\u047d"+
		"\u047e\3\2\2\2\u047e\u047f\3\2\2\2\u047f\u0480\7\\\2\2\u0480\u048c\3\2"+
		"\2\2\u0481\u0482\7A\2\2\u0482\u0483\5\30\r\2\u0483\u0484\7\\\2\2\u0484"+
		"\u048c\3\2\2\2\u0485\u0486\7B\2\2\u0486\u0487\5\30\r\2\u0487\u0488\7C"+
		"\2\2\u0488\u0489\5\30\r\2\u0489\u048a\7\\\2\2\u048a\u048c\3\2\2\2\u048b"+
		"\u0467\3\2\2\2\u048b\u0471\3\2\2\2\u048b\u0479\3\2\2\2\u048b\u0481\3\2"+
		"\2\2\u048b\u0485\3\2\2\2\u048c\u00ad\3\2\2\2\u048d\u048e\t\6\2\2\u048e"+
		"\u00af\3\2\2\2\u048f\u0490\7G\2\2\u0490\u0492\5\u00c0a\2\u0491\u0493\5"+
		".\30\2\u0492\u0491\3\2\2\2\u0492\u0493\3\2\2\2\u0493\u0494\3\2\2\2\u0494"+
		"\u0497\5\u00b2Z\2\u0495\u0496\7 \2\2\u0496\u0498\5\u010a\u0086\2\u0497"+
		"\u0495\3\2\2\2\u0497\u0498\3\2\2\2\u0498\u0499\3\2\2\2\u0499\u049a\5\u00b8"+
		"]\2\u049a\u00b1\3\2\2\2\u049b\u049d\7V\2\2\u049c\u049e\5\u00b4[\2\u049d"+
		"\u049c\3\2\2\2\u049d\u049e\3\2\2\2\u049e\u049f\3\2\2\2\u049f\u04a0\7W"+
		"\2\2\u04a0\u00b3\3\2\2\2\u04a1\u04a6\5\u00b6\\\2\u04a2\u04a3\7]\2\2\u04a3"+
		"\u04a5\5\u00b6\\\2\u04a4\u04a2\3\2\2\2\u04a5\u04a8\3\2\2\2\u04a6\u04a4"+
		"\3\2\2\2\u04a6\u04a7\3\2\2\2\u04a7\u00b5\3\2\2\2\u04a8\u04a6\3\2\2\2\u04a9"+
		"\u04aa\5\u010c\u0087\2\u04aa\u04ab\5\u00c0a\2\u04ab\u00b7\3\2\2\2\u04ac"+
		"\u04b1\7X\2\2\u04ad\u04b0\5B\"\2\u04ae\u04b0\5R*\2\u04af\u04ad\3\2\2\2"+
		"\u04af\u04ae\3\2\2\2\u04b0\u04b3\3\2\2\2\u04b1\u04af\3\2\2\2\u04b1\u04b2"+
		"\3\2\2\2\u04b2\u04b4\3\2\2\2\u04b3\u04b1\3\2\2\2\u04b4\u04b5\7Y\2\2\u04b5"+
		"\u00b9\3\2\2\2\u04b6\u04ba\7X\2\2\u04b7\u04b9\5\u00bc_\2\u04b8\u04b7\3"+
		"\2\2\2\u04b9\u04bc\3\2\2\2\u04ba\u04b8\3\2\2\2\u04ba\u04bb\3\2\2\2\u04bb"+
		"\u04bd\3\2\2\2\u04bc\u04ba\3\2\2\2\u04bd\u04be\7Y\2\2\u04be\u00bb\3\2"+
		"\2\2\u04bf\u04c0\5\u00be`\2\u04c0\u04c1\7\\\2\2\u04c1\u04c5\3\2\2\2\u04c2"+
		"\u04c5\5\u00c4c\2\u04c3\u04c5\5\u00c6d\2\u04c4\u04bf\3\2\2\2\u04c4\u04c2"+
		"\3\2\2\2\u04c4\u04c3\3\2\2\2\u04c5\u00bd\3\2\2\2\u04c6\u04c8\5*\26\2\u04c7"+
		"\u04c6\3\2\2\2\u04c8\u04cb\3\2\2\2\u04c9\u04c7\3\2\2\2\u04c9\u04ca\3\2"+
		"\2\2\u04ca\u04d4\3\2\2\2\u04cb\u04c9\3\2\2\2\u04cc\u04cd\7E\2\2\u04cd"+
		"\u04ce\5\u00c0a\2\u04ce\u04cf\7_\2\2\u04cf\u04d0\5\u00e2r\2\u04d0\u04d5"+
		"\3\2\2\2\u04d1\u04d2\5\u010c\u0087\2\u04d2\u04d3\5f\64\2\u04d3\u04d5\3"+
		"\2\2\2\u04d4\u04cc\3\2\2\2\u04d4\u04d1\3\2\2\2\u04d5\u00bf\3\2\2\2\u04d6"+
		"\u04d7\t\7\2\2\u04d7\u00c1\3\2\2\2\u04d8\u04d9\t\b\2\2\u04d9\u00c3\3\2"+
		"\2\2\u04da\u04dc\5(\25\2\u04db\u04da\3\2\2\2\u04dc\u04df\3\2\2\2\u04dd"+
		"\u04db\3\2\2\2\u04dd\u04de\3\2\2\2\u04de\u04e3\3\2\2\2\u04df\u04dd\3\2"+
		"\2\2\u04e0\u04e4\5,\27\2\u04e1\u04e4\5<\37\2\u04e2\u04e4\5\u00b0Y\2\u04e3"+
		"\u04e0\3\2\2\2\u04e3\u04e1\3\2\2\2\u04e3\u04e2\3\2\2\2\u04e4\u00c5\3\2"+
		"\2\2\u04e5\u0557\5\u00ba^\2\u04e6\u04e7\7\n\2\2\u04e7\u04ea\5\u00e2r\2"+
		"\u04e8\u04e9\7e\2\2\u04e9\u04eb\5\u00e2r\2\u04ea\u04e8\3\2\2\2\u04ea\u04eb"+
		"\3\2\2\2\u04eb\u04ec\3\2\2\2\u04ec\u04ed\7\\\2\2\u04ed\u0557\3\2\2\2\u04ee"+
		"\u04ef\7\36\2\2\u04ef\u04f0\5\u00dep\2\u04f0\u04f3\5\u00c6d\2\u04f1\u04f2"+
		"\7\27\2\2\u04f2\u04f4\5\u00c6d\2\u04f3\u04f1\3\2\2\2\u04f3\u04f4\3\2\2"+
		"\2\u04f4\u0557\3\2\2\2\u04f5\u04f6\7\35\2\2\u04f6\u04f7\7V\2\2\u04f7\u04f8"+
		"\5\u00d8m\2\u04f8\u04f9\7W\2\2\u04f9\u04fa\5\u00c6d\2\u04fa\u0557\3\2"+
		"\2\2\u04fb\u04fc\7:\2\2\u04fc\u04fd\5\u00dep\2\u04fd\u04fe\5\u00c6d\2"+
		"\u04fe\u0557\3\2\2\2\u04ff\u0500\7\25\2\2\u0500\u0501\5\u00c6d\2\u0501"+
		"\u0502\7:\2\2\u0502\u0503\5\u00dep\2\u0503\u0504\7\\\2\2\u0504\u0557\3"+
		"\2\2\2\u0505\u0506\7\67\2\2\u0506\u0510\5\u00ba^\2\u0507\u0509\5\u00c8"+
		"e\2\u0508\u0507\3\2\2\2\u0509\u050a\3\2\2\2\u050a\u0508\3\2\2\2\u050a"+
		"\u050b\3\2\2\2\u050b\u050d\3\2\2\2\u050c\u050e\5\u00ccg\2\u050d\u050c"+
		"\3\2\2\2\u050d\u050e\3\2\2\2\u050e\u0511\3\2\2\2\u050f\u0511\5\u00ccg"+
		"\2\u0510\u0508\3\2\2\2\u0510\u050f\3\2\2\2\u0511\u0557\3\2\2\2\u0512\u0513"+
		"\7\67\2\2\u0513\u0514\5\u00ceh\2\u0514\u0518\5\u00ba^\2\u0515\u0517\5"+
		"\u00c8e\2\u0516\u0515\3\2\2\2\u0517\u051a\3\2\2\2\u0518\u0516\3\2\2\2"+
		"\u0518\u0519\3\2\2\2\u0519\u051c\3\2\2\2\u051a\u0518\3\2\2\2\u051b\u051d"+
		"\5\u00ccg\2\u051c\u051b\3\2\2\2\u051c\u051d\3\2\2\2\u051d\u0557\3\2\2"+
		"\2\u051e\u051f\7\61\2\2\u051f\u0520\5\u00dep\2\u0520\u0524\7X\2\2\u0521"+
		"\u0523\5\u00d4k\2\u0522\u0521\3\2\2\2\u0523\u0526\3\2\2\2\u0524\u0522"+
		"\3\2\2\2\u0524\u0525\3\2\2\2\u0525\u052a\3\2\2\2\u0526\u0524\3\2\2\2\u0527"+
		"\u0529\5\u00d6l\2\u0528\u0527\3\2\2\2\u0529\u052c\3\2\2\2\u052a\u0528"+
		"\3\2\2\2\u052a\u052b\3\2\2\2\u052b\u052d\3\2\2\2\u052c\u052a\3\2\2\2\u052d"+
		"\u052e\7Y\2\2\u052e\u0557\3\2\2\2\u052f\u0530\7\62\2\2\u0530\u0531\5\u00de"+
		"p\2\u0531\u0532\5\u00ba^\2\u0532\u0557\3\2\2\2\u0533\u0535\7,\2\2\u0534"+
		"\u0536\5\u00e2r\2\u0535\u0534\3\2\2\2\u0535\u0536\3\2\2\2\u0536\u0537"+
		"\3\2\2\2\u0537\u0557\7\\\2\2\u0538\u0539\7\64\2\2\u0539\u053a\5\u00e2"+
		"r\2\u053a\u053b\7\\\2\2\u053b\u0557\3\2\2\2\u053c\u053e\7\f\2\2\u053d"+
		"\u053f\5\u00c0a\2\u053e\u053d\3\2\2\2\u053e\u053f\3\2\2\2\u053f\u0540"+
		"\3\2\2\2\u0540\u0557\7\\\2\2\u0541\u0543\7\23\2\2\u0542\u0544\5\u00c0"+
		"a\2\u0543\u0542\3\2\2\2\u0543\u0544\3\2\2\2\u0544\u0545\3\2\2\2\u0545"+
		"\u0557\7\\\2\2\u0546\u0547\7F\2\2\u0547\u0548\5\u00e2r\2\u0548\u0549\7"+
		"\\\2\2\u0549\u0557\3\2\2\2\u054a\u0557\7\\\2\2\u054b\u054c\5\u00e2r\2"+
		"\u054c\u054d\7\\\2\2\u054d\u0557\3\2\2\2\u054e\u0550\5\u00eex\2\u054f"+
		"\u0551\7\\\2\2\u0550\u054f\3\2\2\2\u0550\u0551\3\2\2\2\u0551\u0557\3\2"+
		"\2\2\u0552\u0553\5\u00c0a\2\u0553\u0554\7e\2\2\u0554\u0555\5\u00c6d\2"+
		"\u0555\u0557\3\2\2\2\u0556\u04e5\3\2\2\2\u0556\u04e6\3\2\2\2\u0556\u04ee"+
		"\3\2\2\2\u0556\u04f5\3\2\2\2\u0556\u04fb\3\2\2\2\u0556\u04ff\3\2\2\2\u0556"+
		"\u0505\3\2\2\2\u0556\u0512\3\2\2\2\u0556\u051e\3\2\2\2\u0556\u052f\3\2"+
		"\2\2\u0556\u0533\3\2\2\2\u0556\u0538\3\2\2\2\u0556\u053c\3\2\2\2\u0556"+
		"\u0541\3\2\2\2\u0556\u0546\3\2\2\2\u0556\u054a\3\2\2\2\u0556\u054b\3\2"+
		"\2\2\u0556\u054e\3\2\2\2\u0556\u0552\3\2\2\2\u0557\u00c7\3\2\2\2\u0558"+
		"\u0559\7\17\2\2\u0559\u055d\7V\2\2\u055a\u055c\5*\26\2\u055b\u055a\3\2"+
		"\2\2\u055c\u055f\3\2\2\2\u055d\u055b\3\2\2\2\u055d\u055e\3\2\2\2\u055e"+
		"\u0560\3\2\2\2\u055f\u055d\3\2\2\2\u0560\u0561\5\u00caf\2\u0561\u0562"+
		"\5\u00c0a\2\u0562\u0563\7W\2\2\u0563\u0564\5\u00ba^\2\u0564\u00c9\3\2"+
		"\2\2\u0565\u056a\5\30\r\2\u0566\u0567\7s\2\2\u0567\u0569\5\30\r\2\u0568"+
		"\u0566\3\2\2\2\u0569\u056c\3\2\2\2\u056a\u0568\3\2\2\2\u056a\u056b\3\2"+
		"\2\2\u056b\u00cb\3\2\2\2\u056c\u056a\3\2\2\2\u056d\u056e\7\33\2\2\u056e"+
		"\u056f\5\u00ba^\2\u056f\u00cd\3\2\2\2\u0570\u0571\7V\2\2\u0571\u0573\5"+
		"\u00d0i\2\u0572\u0574\7\\\2\2\u0573\u0572\3\2\2\2\u0573\u0574\3\2\2\2"+
		"\u0574\u0575\3\2\2\2\u0575\u0576\7W\2\2\u0576\u00cf\3\2\2\2\u0577\u057c"+
		"\5\u00d2j\2\u0578\u0579\7\\\2\2\u0579\u057b\5\u00d2j\2\u057a\u0578\3\2"+
		"\2\2\u057b\u057e\3\2\2\2\u057c\u057a\3\2\2\2\u057c\u057d\3\2\2\2\u057d"+
		"\u00d1\3\2\2\2\u057e\u057c\3\2\2\2\u057f\u0581\5*\26\2\u0580\u057f\3\2"+
		"\2\2\u0581\u0584\3\2\2\2\u0582\u0580\3\2\2\2\u0582\u0583\3\2\2\2\u0583"+
		"\u058a\3\2\2\2\u0584\u0582\3\2\2\2\u0585\u0586\5n8\2\u0586\u0587\5\f\7"+
		"\2\u0587\u058b\3\2\2\2\u0588\u0589\7E\2\2\u0589\u058b\5\u00c0a\2\u058a"+
		"\u0585\3\2\2\2\u058a\u0588\3\2\2\2\u058b\u058c\3\2\2\2\u058c\u058d\7_"+
		"\2\2\u058d\u058e\5\u00e2r\2\u058e\u0591\3\2\2\2\u058f\u0591\5\30\r\2\u0590"+
		"\u0582\3\2\2\2\u0590\u058f\3\2\2\2\u0591\u00d3\3\2\2\2\u0592\u0594\5\u00d6"+
		"l\2\u0593\u0592\3\2\2\2\u0594\u0595\3\2\2\2\u0595\u0593\3\2\2\2\u0595"+
		"\u0596\3\2\2\2\u0596\u0598\3\2\2\2\u0597\u0599\5\u00bc_\2\u0598\u0597"+
		"\3\2\2\2\u0599\u059a\3\2\2\2\u059a\u0598\3\2\2\2\u059a\u059b\3\2\2\2\u059b"+
		"\u00d5\3\2\2\2\u059c\u05a2\7\16\2\2\u059d\u05a3\5\u00e2r\2\u059e\u05a3"+
		"\7\u0085\2\2\u059f\u05a0\5\u010c\u0087\2\u05a0\u05a1\5\u00c0a\2\u05a1"+
		"\u05a3\3\2\2\2\u05a2\u059d\3\2\2\2\u05a2\u059e\3\2\2\2\u05a2\u059f\3\2"+
		"\2\2\u05a3\u05a4\3\2\2\2\u05a4\u05a8\7e\2\2\u05a5\u05a6\7\24\2\2\u05a6"+
		"\u05a8\7e\2\2\u05a7\u059c\3\2\2\2\u05a7\u05a5\3\2\2\2\u05a8\u00d7\3\2"+
		"\2\2\u05a9\u05b6\5\u00dco\2\u05aa\u05ac\5\u00dan\2\u05ab\u05aa\3\2\2\2"+
		"\u05ab\u05ac\3\2\2\2\u05ac\u05ad\3\2\2\2\u05ad\u05af\7\\\2\2\u05ae\u05b0"+
		"\5\u00e2r\2\u05af\u05ae\3\2\2\2\u05af\u05b0\3\2\2\2\u05b0\u05b1\3\2\2"+
		"\2\u05b1\u05b3\7\\\2\2\u05b2\u05b4\5\u00e0q\2\u05b3\u05b2\3\2\2\2\u05b3"+
		"\u05b4\3\2\2\2\u05b4\u05b6\3\2\2\2\u05b5\u05a9\3\2\2\2\u05b5\u05ab\3\2"+
		"\2\2\u05b6\u00d9\3\2\2\2\u05b7\u05ba\5\u00be`\2\u05b8\u05ba\5\u00e0q\2"+
		"\u05b9\u05b7\3\2\2\2\u05b9\u05b8\3\2\2\2\u05ba\u00db\3\2\2\2\u05bb\u05bd"+
		"\5*\26\2\u05bc\u05bb\3\2\2\2\u05bd\u05c0\3\2\2\2\u05be\u05bc\3\2\2\2\u05be"+
		"\u05bf\3\2\2\2\u05bf\u05c3\3\2\2\2\u05c0\u05be\3\2\2\2\u05c1\u05c4\5\u010c"+
		"\u0087\2\u05c2\u05c4\7E\2\2\u05c3\u05c1\3\2\2\2\u05c3\u05c2\3\2\2\2\u05c4"+
		"\u05c5\3\2\2\2\u05c5\u05c6\5\f\7\2\u05c6\u05c7\7e\2\2\u05c7\u05c8\5\u00e2"+
		"r\2\u05c8\u00dd\3\2\2\2\u05c9\u05ca\7V\2\2\u05ca\u05cb\5\u00e2r\2\u05cb"+
		"\u05cc\7W\2\2\u05cc\u00df\3\2\2\2\u05cd\u05d2\5\u00e2r\2\u05ce\u05cf\7"+
		"]\2\2\u05cf\u05d1\5\u00e2r\2\u05d0\u05ce\3\2\2\2\u05d1\u05d4\3\2\2\2\u05d2"+
		"\u05d0\3\2\2\2\u05d2\u05d3\3\2\2\2\u05d3\u00e1\3\2\2\2\u05d4\u05d2\3\2"+
		"\2\2\u05d5\u05d6\br\1\2\u05d6\u0601\5\u00ecw\2\u05d7\u0601\5\20\t\2\u05d8"+
		"\u05d9\5\u010c\u0087\2\u05d9\u05df\7\u0082\2\2\u05da\u05dc\5\u010e\u0088"+
		"\2\u05db\u05da\3\2\2\2\u05db\u05dc\3\2\2\2\u05dc\u05dd\3\2\2\2\u05dd\u05e0"+
		"\5\u00c0a\2\u05de\u05e0\7\'\2\2\u05df\u05db\3\2\2\2\u05df\u05de\3\2\2"+
		"\2\u05e0\u0601\3\2\2\2\u05e1\u05e2\5\u00f6|\2\u05e2\u05e4\7\u0082\2\2"+
		"\u05e3\u05e5\5\u010e\u0088\2\u05e4\u05e3\3\2\2\2\u05e4\u05e5\3\2\2\2\u05e5"+
		"\u05e6\3\2\2\2\u05e6\u05e7\7\'\2\2\u05e7\u0601\3\2\2\2\u05e8\u0601\5\u00ee"+
		"x\2\u05e9\u05ea\t\t\2\2\u05ea\u0601\5\u00e2r\23\u05eb\u05ef\7V\2\2\u05ec"+
		"\u05ee\5\u008eH\2\u05ed\u05ec\3\2\2\2\u05ee\u05f1\3\2\2\2\u05ef\u05ed"+
		"\3\2\2\2\u05ef\u05f0\3\2\2\2\u05f0\u05f2\3\2\2\2\u05f1\u05ef\3\2\2\2\u05f2"+
		"\u05f7\5\u010c\u0087\2\u05f3\u05f4\7r\2\2\u05f4\u05f6\5\u010c\u0087\2"+
		"\u05f5\u05f3\3\2\2\2\u05f6\u05f9\3\2\2\2\u05f7\u05f5\3\2\2\2\u05f7\u05f8"+
		"\3\2\2\2\u05f8\u05fa\3\2\2\2\u05f9\u05f7\3\2\2\2\u05fa\u05fb\7W\2\2\u05fb"+
		"\u05fc\5\u00e2r\22\u05fc\u0601\3\2\2\2\u05fd\u05fe\7\'\2\2\u05fe\u0601"+
		"\5\u00f8}\2\u05ff\u0601\5\u00e6t\2\u0600\u05d5\3\2\2\2\u0600\u05d7\3\2"+
		"\2\2\u0600\u05d8\3\2\2\2\u0600\u05e1\3\2\2\2\u0600\u05e8\3\2\2\2\u0600"+
		"\u05e9\3\2\2\2\u0600\u05eb\3\2\2\2\u0600\u05fd\3\2\2\2\u0600\u05ff\3\2"+
		"\2\2\u0601\u0655\3\2\2\2\u0602\u0603\f\20\2\2\u0603\u0604\t\n\2\2\u0604"+
		"\u0654\5\u00e2r\21\u0605\u0606\f\17\2\2\u0606\u0607\t\13\2\2\u0607\u0654"+
		"\5\u00e2r\20\u0608\u0610\f\16\2\2\u0609\u060a\7a\2\2\u060a\u0611\7a\2"+
		"\2\u060b\u060c\7`\2\2\u060c\u060d\7`\2\2\u060d\u0611\7`\2\2\u060e\u060f"+
		"\7`\2\2\u060f\u0611\7`\2\2\u0610\u0609\3\2\2\2\u0610\u060b\3\2\2\2\u0610"+
		"\u060e\3\2\2\2\u0611\u0612\3\2\2\2\u0612\u0654\5\u00e2r\17\u0613\u0614"+
		"\f\r\2\2\u0614\u0615\t\f\2\2\u0615\u0654\5\u00e2r\16\u0616\u0617\f\13"+
		"\2\2\u0617\u0618\t\r\2\2\u0618\u0654\5\u00e2r\f\u0619\u061a\f\n\2\2\u061a"+
		"\u061b\7r\2\2\u061b\u0654\5\u00e2r\13\u061c\u061d\f\t\2\2\u061d\u061e"+
		"\7t\2\2\u061e\u0654\5\u00e2r\n\u061f\u0620\f\b\2\2\u0620\u0621\7s\2\2"+
		"\u0621\u0654\5\u00e2r\t\u0622\u0623\f\7\2\2\u0623\u0624\7j\2\2\u0624\u0654"+
		"\5\u00e2r\b\u0625\u0626\f\6\2\2\u0626\u0627\7k\2\2\u0627\u0654\5\u00e2"+
		"r\7\u0628\u0629\f\5\2\2\u0629\u062a\7d\2\2\u062a\u062b\5\u00e2r\2\u062b"+
		"\u062c\7e\2\2\u062c\u062d\5\u00e2r\5\u062d\u0654\3\2\2\2\u062e\u062f\f"+
		"\4\2\2\u062f\u0630\t\16\2\2\u0630\u0654\5\u00e2r\4\u0631\u0632\f\33\2"+
		"\2\u0632\u0633\7Z\2\2\u0633\u0634\5\u00e2r\2\u0634\u0635\7[\2\2\u0635"+
		"\u0654\3\2\2\2\u0636\u0637\f\32\2\2\u0637\u0643\7^\2\2\u0638\u0644\5\u00c0"+
		"a\2\u0639\u0644\5\20\t\2\u063a\u0644\7\63\2\2\u063b\u063d\7\'\2\2\u063c"+
		"\u063e\5\u0108\u0085\2\u063d\u063c\3\2\2\2\u063d\u063e\3\2\2\2\u063e\u063f"+
		"\3\2\2\2\u063f\u0644\5\u00fc\177\2\u0640\u0641\7\60\2\2\u0641\u0644\5"+
		"\u0110\u0089\2\u0642\u0644\5\u0102\u0082\2\u0643\u0638\3\2\2\2\u0643\u0639"+
		"\3\2\2\2\u0643\u063a\3\2\2\2\u0643\u063b\3\2\2\2\u0643\u0640\3\2\2\2\u0643"+
		"\u0642\3\2\2\2\u0644\u0654\3\2\2\2\u0645\u0646\f\30\2\2\u0646\u0648\7"+
		"\u0082\2\2\u0647\u0649\5\u010e\u0088\2\u0648\u0647\3\2\2\2\u0648\u0649"+
		"\3\2\2\2\u0649\u064a\3\2\2\2\u064a\u0654\5\u00c0a\2\u064b\u064c\f\24\2"+
		"\2\u064c\u0654\t\17\2\2\u064d\u064e\f\f\2\2\u064e\u0651\7\"\2\2\u064f"+
		"\u0652\5\u010c\u0087\2\u0650\u0652\5\u00e4s\2\u0651\u064f\3\2\2\2\u0651"+
		"\u0650\3\2\2\2\u0652\u0654\3\2\2\2\u0653\u0602\3\2\2\2\u0653\u0605\3\2"+
		"\2\2\u0653\u0608\3\2\2\2\u0653\u0613\3\2\2\2\u0653\u0616\3\2\2\2\u0653"+
		"\u0619\3\2\2\2\u0653\u061c\3\2\2\2\u0653\u061f\3\2\2\2\u0653\u0622\3\2"+
		"\2\2\u0653\u0625\3\2\2\2\u0653\u0628\3\2\2\2\u0653\u062e\3\2\2\2\u0653"+
		"\u0631\3\2\2\2\u0653\u0636\3\2\2\2\u0653\u0645\3\2\2\2\u0653\u064b\3\2"+
		"\2\2\u0653\u064d\3\2\2\2\u0654\u0657\3\2\2\2\u0655\u0653\3\2\2\2\u0655"+
		"\u0656\3\2\2\2\u0656\u00e3\3\2\2\2\u0657\u0655\3\2\2\2\u0658\u065a\5*"+
		"\26\2\u0659\u0658\3\2\2\2\u065a\u065d\3\2\2\2\u065b\u0659\3\2\2\2\u065b"+
		"\u065c\3\2\2\2\u065c\u065e\3\2\2\2\u065d\u065b\3\2\2\2\u065e\u0662\5\u010c"+
		"\u0087\2\u065f\u0661\5\u008eH\2\u0660\u065f\3\2\2\2\u0661\u0664\3\2\2"+
		"\2\u0662\u0660\3\2\2\2\u0662\u0663\3\2\2\2\u0663\u0665\3\2\2\2\u0664\u0662"+
		"\3\2\2\2\u0665\u0666\5\u00c0a\2\u0666\u00e5\3\2\2\2\u0667\u0668\5\u00e8"+
		"u\2\u0668\u0669\7\u0081\2\2\u0669\u066a\5\u00eav\2\u066a\u00e7\3\2\2\2"+
		"\u066b\u0682\5\u00c0a\2\u066c\u066e\7V\2\2\u066d\u066f\5x=\2\u066e\u066d"+
		"\3\2\2\2\u066e\u066f\3\2\2\2\u066f\u0670\3\2\2\2\u0670\u0682\7W\2\2\u0671"+
		"\u0672\7V\2\2\u0672\u0677\5\u00c0a\2\u0673\u0674\7]\2\2\u0674\u0676\5"+
		"\u00c0a\2\u0675\u0673\3\2\2\2\u0676\u0679\3\2\2\2\u0677\u0675\3\2\2\2"+
		"\u0677\u0678\3\2\2\2\u0678\u067a\3\2\2\2\u0679\u0677\3\2\2\2\u067a\u067b"+
		"\7W\2\2\u067b\u0682\3\2\2\2\u067c\u067e\7V\2\2\u067d\u067f\5~@\2\u067e"+
		"\u067d\3\2\2\2\u067e\u067f\3\2\2\2\u067f\u0680\3\2\2\2\u0680\u0682\7W"+
		"\2\2\u0681\u066b\3\2\2\2\u0681\u066c\3\2\2\2\u0681\u0671\3\2\2\2\u0681"+
		"\u067c\3\2\2\2\u0682\u00e9\3\2\2\2\u0683\u0686\5\u00e2r\2\u0684\u0686"+
		"\5\u00ba^\2\u0685\u0683\3\2\2\2\u0685\u0684\3\2\2\2\u0686\u00eb\3\2\2"+
		"\2\u0687\u0688\7V\2\2\u0688\u0689\5\u00e2r\2\u0689\u068a\7W\2\2\u068a"+
		"\u069a\3\2\2\2\u068b\u069a\7\63\2\2\u068c\u069a\7\60\2\2\u068d\u069a\5"+
		"\32\16\2\u068e\u069a\5\u00c0a\2\u068f\u0690\5J&\2\u0690\u0691\7^\2\2\u0691"+
		"\u0692\7\21\2\2\u0692\u069a\3\2\2\2\u0693\u0697\5\u0108\u0085\2\u0694"+
		"\u0698\5\u0112\u008a\2\u0695\u0696\7\63\2\2\u0696\u0698\5\u0114\u008b"+
		"\2\u0697\u0694\3\2\2\2\u0697\u0695\3\2\2\2\u0698\u069a\3\2\2\2\u0699\u0687"+
		"\3\2\2\2\u0699\u068b\3\2\2\2\u0699\u068c\3\2\2\2\u0699\u068d\3\2\2\2\u0699"+
		"\u068e\3\2\2\2\u0699\u068f\3\2\2\2\u0699\u0693\3\2\2\2\u069a\u00ed\3\2"+
		"\2\2\u069b\u069c\7\61\2\2\u069c\u069d\5\u00dep\2\u069d\u06a1\7X\2\2\u069e"+
		"\u06a0\5\u00f0y\2\u069f\u069e\3\2\2\2\u06a0\u06a3\3\2\2\2\u06a1\u069f"+
		"\3\2\2\2\u06a1\u06a2\3\2\2\2\u06a2\u06a4\3\2\2\2\u06a3\u06a1\3\2\2\2\u06a4"+
		"\u06a5\7Y\2\2\u06a5\u00ef\3\2\2\2\u06a6\u06aa\7\16\2\2\u06a7\u06ab\5\u00e0"+
		"q\2\u06a8\u06ab\7U\2\2\u06a9\u06ab\5\u00f2z\2\u06aa\u06a7\3\2\2\2\u06aa"+
		"\u06a8\3\2\2\2\u06aa\u06a9\3\2\2\2\u06ab\u06ac\3\2\2\2\u06ac\u06ad\t\20"+
		"\2\2\u06ad\u06b2\5\u00f4{\2\u06ae\u06af\7\24\2\2\u06af\u06b0\t\20\2\2"+
		"\u06b0\u06b2\5\u00f4{\2\u06b1\u06a6\3\2\2\2\u06b1\u06ae\3\2\2\2\u06b2"+
		"\u00f1\3\2\2\2\u06b3\u06b4\bz\1\2\u06b4\u06b5\7V\2\2\u06b5\u06b6\5\u00f2"+
		"z\2\u06b6\u06b7\7W\2\2\u06b7\u06ce\3\2\2\2\u06b8\u06ba\5*\26\2\u06b9\u06b8"+
		"\3\2\2\2\u06ba\u06bd\3\2\2\2\u06bb\u06b9\3\2\2\2\u06bb\u06bc\3\2\2\2\u06bc"+
		"\u06be\3\2\2\2\u06bd\u06bb\3\2\2\2\u06be\u06c2\5\u010c\u0087\2\u06bf\u06c1"+
		"\5\u008eH\2\u06c0\u06bf\3\2\2\2\u06c1\u06c4\3\2\2\2\u06c2\u06c0\3\2\2"+
		"\2\u06c2\u06c3\3\2\2\2\u06c3\u06c5\3\2\2\2\u06c4\u06c2\3\2\2\2\u06c5\u06ca"+
		"\5\u00c0a\2\u06c6\u06c7\7j\2\2\u06c7\u06c9\5\u00e2r\2\u06c8\u06c6\3\2"+
		"\2\2\u06c9\u06cc\3\2\2\2\u06ca\u06c8\3\2\2\2\u06ca\u06cb\3\2\2\2\u06cb"+
		"\u06ce\3\2\2\2\u06cc\u06ca\3\2\2\2\u06cd\u06b3\3\2\2\2\u06cd\u06bb\3\2"+
		"\2\2\u06ce\u06d4\3\2\2\2\u06cf\u06d0\f\3\2\2\u06d0\u06d1\7j\2\2\u06d1"+
		"\u06d3\5\u00e2r\2\u06d2\u06cf\3\2\2\2\u06d3\u06d6\3\2\2\2\u06d4\u06d2"+
		"\3\2\2\2\u06d4\u06d5\3\2\2\2\u06d5\u00f3\3\2\2\2\u06d6\u06d4\3\2\2\2\u06d7"+
		"\u06df\5\u00ba^\2\u06d8\u06da\5\u00bc_\2\u06d9\u06d8\3\2\2\2\u06da\u06dd"+
		"\3\2\2\2\u06db\u06d9\3\2\2\2\u06db\u06dc\3\2\2\2\u06dc\u06df\3\2\2\2\u06dd"+
		"\u06db\3\2\2\2\u06de\u06d7\3\2\2\2\u06de\u06db\3\2\2\2\u06df\u00f5\3\2"+
		"\2\2\u06e0\u06e1\5n8\2\u06e1\u06e2\7^\2\2\u06e2\u06e4\3\2\2\2\u06e3\u06e0"+
		"\3\2\2\2\u06e3\u06e4\3\2\2\2\u06e4\u06e8\3\2\2\2\u06e5\u06e7\5\u008eH"+
		"\2\u06e6\u06e5\3\2\2\2\u06e7\u06ea\3\2\2\2\u06e8\u06e6\3\2\2\2\u06e8\u06e9"+
		"\3\2\2\2\u06e9\u06eb\3\2\2\2\u06ea\u06e8\3\2\2\2\u06eb\u06ed\5\u00c0a"+
		"\2\u06ec\u06ee\5\u010e\u0088\2\u06ed\u06ec\3\2\2\2\u06ed\u06ee\3\2\2\2"+
		"\u06ee\u00f7\3\2\2\2\u06ef\u06f1\5\u0108\u0085\2\u06f0\u06ef\3\2\2\2\u06f0"+
		"\u06f1\3\2\2\2\u06f1\u06f2\3\2\2\2\u06f2\u06f3\5\u00fa~\2\u06f3\u06f4"+
		"\5\u0100\u0081\2\u06f4\u06f9\3\2\2\2\u06f5\u06f6\5\u00fa~\2\u06f6\u06f7"+
		"\5\u00fe\u0080\2\u06f7\u06f9\3\2\2\2\u06f8\u06f0\3\2\2\2\u06f8\u06f5\3"+
		"\2\2\2\u06f9\u00f9\3\2\2\2\u06fa\u06fc\5\u00c0a\2\u06fb\u06fd\5\u0104"+
		"\u0083\2\u06fc\u06fb\3\2\2\2\u06fc\u06fd\3\2\2\2\u06fd\u0705\3\2\2\2\u06fe"+
		"\u06ff\7^\2\2\u06ff\u0701\5\u00c0a\2\u0700\u0702\5\u0104\u0083\2\u0701"+
		"\u0700\3\2\2\2\u0701\u0702\3\2\2\2\u0702\u0704\3\2\2\2\u0703\u06fe\3\2"+
		"\2\2\u0704\u0707\3\2\2\2\u0705\u0703\3\2\2\2\u0705\u0706\3\2\2\2\u0706"+
		"\u070a\3\2\2\2\u0707\u0705\3\2\2\2\u0708\u070a\5\24\13\2\u0709\u06fa\3"+
		"\2\2\2\u0709\u0708\3\2\2\2\u070a\u00fb\3\2\2\2\u070b\u070d\5\u00c0a\2"+
		"\u070c\u070e\5\u0106\u0084\2\u070d\u070c\3\2\2\2\u070d\u070e\3\2\2\2\u070e"+
		"\u070f\3\2\2\2\u070f\u0710\5\u0100\u0081\2\u0710\u00fd\3\2\2\2\u0711\u0712"+
		"\7Z\2\2\u0712\u0714\7[\2\2\u0713\u0711\3\2\2\2\u0714\u0715\3\2\2\2\u0715"+
		"\u0713\3\2\2\2\u0715\u0716\3\2\2\2\u0716\u0717\3\2\2\2\u0717\u0728\5l"+
		"\67\2\u0718\u0719\7Z\2\2\u0719\u071a\5\u00e2r\2\u071a\u071b\7[\2\2\u071b"+
		"\u071d\3\2\2\2\u071c\u0718\3\2\2\2\u071d\u071e\3\2\2\2\u071e\u071c\3\2"+
		"\2\2\u071e\u071f\3\2\2\2\u071f\u0724\3\2\2\2\u0720\u0721\7Z\2\2\u0721"+
		"\u0723\7[\2\2\u0722\u0720\3\2\2\2\u0723\u0726\3\2\2\2\u0724\u0722\3\2"+
		"\2\2\u0724\u0725\3\2\2\2\u0725\u0728\3\2\2\2\u0726\u0724\3\2\2\2\u0727"+
		"\u0713\3\2\2\2\u0727\u071c\3\2\2\2\u0728\u00ff\3\2\2\2\u0729\u072b\5\u0114"+
		"\u008b\2\u072a\u072c\5> \2\u072b\u072a\3\2\2\2\u072b\u072c\3\2\2\2\u072c"+
		"\u0101\3\2\2\2\u072d\u072e\5\u0108\u0085\2\u072e\u072f\5\u0112\u008a\2"+
		"\u072f\u0103\3\2\2\2\u0730\u0731\7a\2\2\u0731\u0734\7`\2\2\u0732\u0734"+
		"\5\u010e\u0088\2\u0733\u0730\3\2\2\2\u0733\u0732\3\2\2\2\u0734\u0105\3"+
		"\2\2\2\u0735\u0736\7a\2\2\u0736\u0739\7`\2\2\u0737\u0739\5\u0108\u0085"+
		"\2\u0738\u0735\3\2\2\2\u0738\u0737\3\2\2\2\u0739\u0107\3\2\2\2\u073a\u073b"+
		"\7a\2\2\u073b\u073c\5\u010a\u0086\2\u073c\u073d\7`\2\2\u073d\u0109\3\2"+
		"\2\2\u073e\u0743\5\u010c\u0087\2\u073f\u0740\7]\2\2\u0740\u0742\5\u010c"+
		"\u0087\2\u0741\u073f\3\2\2\2\u0742\u0745\3\2\2\2\u0743\u0741\3\2\2\2\u0743"+
		"\u0744\3\2\2\2\u0744\u010b\3\2\2\2\u0745\u0743\3\2\2\2\u0746\u0748\5\u008e"+
		"H\2\u0747\u0746\3\2\2\2\u0748\u074b\3\2\2\2\u0749\u0747\3\2\2\2\u0749"+
		"\u074a\3\2\2\2\u074a\u074e\3\2\2\2\u074b\u0749\3\2\2\2\u074c\u074f\5n"+
		"8\2\u074d\u074f\5\24\13\2\u074e\u074c\3\2\2\2\u074e\u074d\3\2\2\2\u074f"+
		"\u075a\3\2\2\2\u0750\u0752\5\u008eH\2\u0751\u0750\3\2\2\2\u0752\u0755"+
		"\3\2\2\2\u0753\u0751\3\2\2\2\u0753\u0754\3\2\2\2\u0754\u0756\3\2\2\2\u0755"+
		"\u0753\3\2\2\2\u0756\u0757\7Z\2\2\u0757\u0759\7[\2\2\u0758\u0753\3\2\2"+
		"\2\u0759\u075c\3\2\2\2\u075a\u0758\3\2\2\2\u075a\u075b\3\2\2\2\u075b\u010d"+
		"\3\2\2\2\u075c\u075a\3\2\2\2\u075d\u075e\7a\2\2\u075e\u0763\5p9\2\u075f"+
		"\u0760\7]\2\2\u0760\u0762\5p9\2\u0761\u075f\3\2\2\2\u0762\u0765\3\2\2"+
		"\2\u0763\u0761\3\2\2\2\u0763\u0764\3\2\2\2\u0764\u0766\3\2\2\2\u0765\u0763"+
		"\3\2\2\2\u0766\u0767\7`\2\2\u0767\u010f\3\2\2\2\u0768\u0772\5\u0114\u008b"+
		"\2\u0769\u076b\7^\2\2\u076a\u076c\5\u010e\u0088\2\u076b\u076a\3\2\2\2"+
		"\u076b\u076c\3\2\2\2\u076c\u076d\3\2\2\2\u076d\u076f\5\u00c0a\2\u076e"+
		"\u0770\5\u0114\u008b\2\u076f\u076e\3\2\2\2\u076f\u0770\3\2\2\2\u0770\u0772"+
		"\3\2\2\2\u0771\u0768\3\2\2\2\u0771\u0769\3\2\2\2\u0772\u0111\3\2\2\2\u0773"+
		"\u0774\7\60\2\2\u0774\u0779\5\u0110\u0089\2\u0775\u0776\5\u00c0a\2\u0776"+
		"\u0777\5\u0114\u008b\2\u0777\u0779\3\2\2\2\u0778\u0773\3\2\2\2\u0778\u0775"+
		"\3\2\2\2\u0779\u0113\3\2\2\2\u077a\u077c\7V\2\2\u077b\u077d\5\u00e0q\2"+
		"\u077c\u077b\3\2\2\2\u077c\u077d\3\2\2\2\u077d\u077e\3\2\2\2\u077e\u077f"+
		"\7W\2\2\u077f\u0115\3\2\2\2\u00f8\u0119\u011c\u0121\u0127\u012e\u0130"+
		"\u0137\u0139\u0141\u0143\u014b\u014d\u0153\u0155\u015d\u015f\u0162\u016a"+
		"\u016d\u0174\u017d\u0183\u0189\u018c\u0191\u019e\u01a4\u01a9\u01ad\u01b7"+
		"\u01bc\u01c0\u01c2\u01c7\u01c9\u01d0\u01d5\u01de\u01e3\u01ea\u01f2\u01f9"+
		"\u0205\u0209\u020e\u0212\u0216\u021a\u0224\u022c\u0234\u0238\u023f\u0246"+
		"\u024a\u024d\u0250\u0259\u025f\u0264\u0267\u026d\u0273\u0277\u027b\u0283"+
		"\u028c\u0293\u0299\u029d\u02a9\u02b2\u02b7\u02bd\u02c1\u02cd\u02d4\u02e1"+
		"\u02e6\u02f0\u02f8\u0302\u030b\u0316\u031b\u0324\u032e\u0333\u033c\u0342"+
		"\u0346\u034e\u0352\u0354\u035a\u0360\u0365\u036b\u0371\u0373\u037a\u037f"+
		"\u0384\u0387\u0389\u0393\u039d\u03a2\u03a5\u03aa\u03b3\u03ba\u03c5\u03cb"+
		"\u03d7\u03e2\u03eb\u03f0\u03f3\u03fa\u0404\u040c\u040f\u0412\u041f\u0427"+
		"\u042c\u0434\u0438\u043c\u0440\u0444\u0446\u044a\u0450\u0458\u0462\u046b"+
		"\u0475\u047d\u048b\u0492\u0497\u049d\u04a6\u04af\u04b1\u04ba\u04c4\u04c9"+
		"\u04d4\u04dd\u04e3\u04ea\u04f3\u050a\u050d\u0510\u0518\u051c\u0524\u052a"+
		"\u0535\u053e\u0543\u0550\u0556\u055d\u056a\u0573\u057c\u0582\u058a\u0590"+
		"\u0595\u059a\u05a2\u05a7\u05ab\u05af\u05b3\u05b5\u05b9\u05be\u05c3\u05d2"+
		"\u05db\u05df\u05e4\u05ef\u05f7\u0600\u0610\u063d\u0643\u0648\u0651\u0653"+
		"\u0655\u065b\u0662\u066e\u0677\u067e\u0681\u0685\u0697\u0699\u06a1\u06aa"+
		"\u06b1\u06bb\u06c2\u06ca\u06cd\u06d4\u06db\u06de\u06e3\u06e8\u06ed\u06f0"+
		"\u06f8\u06fc\u0701\u0705\u0709\u070d\u0715\u071e\u0724\u0727\u072b\u0733"+
		"\u0738\u0743\u0749\u074e\u0753\u075a\u0763\u076b\u076f\u0771\u0778\u077c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}