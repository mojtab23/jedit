// Generated from /home/danson/src/jedit/plugins/Beauty/src/beauty/parsers/java/antlr/Java8.g4 by ANTLR 4.4

package beauty.parsers.java.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Java8Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, ABSTRACT=2, ASSERT=3, BOOLEAN=4, BREAK=5, BYTE=6, CASE=7, CATCH=8, 
		CHAR=9, CLASS=10, CONST=11, CONTINUE=12, DEFAULT=13, DO=14, DOUBLE=15, 
		ELSE=16, ENUM=17, EXTENDS=18, FINAL=19, FINALLY=20, FLOAT=21, FOR=22, 
		IF=23, GOTO=24, IMPLEMENTS=25, IMPORT=26, INSTANCEOF=27, INT=28, INTERFACE=29, 
		LONG=30, NATIVE=31, NEW=32, PACKAGE=33, PRIVATE=34, PROTECTED=35, PUBLIC=36, 
		RETURN=37, SHORT=38, STATIC=39, STRICTFP=40, SUPER=41, SWITCH=42, SYNCHRONIZED=43, 
		THIS=44, THROW=45, THROWS=46, TRANSIENT=47, TRY=48, VOID=49, VOLATILE=50, 
		WHILE=51, IntegerLiteral=52, FloatingPointLiteral=53, BooleanLiteral=54, 
		CharacterLiteral=55, StringLiteral=56, NullLiteral=57, LPAREN=58, RPAREN=59, 
		LBRACE=60, RBRACE=61, LBRACK=62, RBRACK=63, SEMI=64, COMMA=65, DOT=66, 
		ASSIGN=67, GT=68, LT=69, BANG=70, TILDE=71, QUESTION=72, COLON=73, EQUAL=74, 
		LE=75, GE=76, NOTEQUAL=77, AND=78, OR=79, INC=80, DEC=81, ADD=82, SUB=83, 
		MUL=84, DIV=85, BITAND=86, BITOR=87, CARET=88, MOD=89, ARROW=90, COLONCOLON=91, 
		ADD_ASSIGN=92, SUB_ASSIGN=93, MUL_ASSIGN=94, DIV_ASSIGN=95, AND_ASSIGN=96, 
		OR_ASSIGN=97, XOR_ASSIGN=98, MOD_ASSIGN=99, LSHIFT_ASSIGN=100, RSHIFT_ASSIGN=101, 
		URSHIFT_ASSIGN=102, Identifier=103, AT=104, ELLIPSIS=105, WS=106, DOC_COMMENT=107, 
		COMMENT=108, LINE_COMMENT=109;
	public static final String[] tokenNames = {
		"<INVALID>", "'[]'", "'abstract'", "'assert'", "'boolean'", "'break'", 
		"'byte'", "'case'", "'catch'", "'char'", "'class'", "'const'", "'continue'", 
		"'default'", "'do'", "'double'", "'else'", "'enum'", "'extends'", "'final'", 
		"'finally'", "'float'", "'for'", "'if'", "'goto'", "'implements'", "'import'", 
		"'instanceof'", "'int'", "'interface'", "'long'", "'native'", "'new'", 
		"'package'", "'private'", "'protected'", "'public'", "'return'", "'short'", 
		"'static'", "'strictfp'", "'super'", "'switch'", "'synchronized'", "'this'", 
		"'throw'", "'throws'", "'transient'", "'try'", "'void'", "'volatile'", 
		"'while'", "IntegerLiteral", "FloatingPointLiteral", "BooleanLiteral", 
		"CharacterLiteral", "StringLiteral", "'null'", "'('", "')'", "'{'", "'}'", 
		"'['", "']'", "';'", "','", "'.'", "'='", "'>'", "'<'", "'!'", "'~'", 
		"'?'", "':'", "'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'++'", 
		"'--'", "'+'", "'-'", "'*'", "'/'", "'&'", "'|'", "'^'", "'%'", "'->'", 
		"'::'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", 
		"'<<='", "'>>='", "'>>>='", "Identifier", "'@'", "'...'", "WS", "DOC_COMMENT", 
		"COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_literal = 0, RULE_type = 1, RULE_primitiveType = 2, RULE_numericType = 3, 
		RULE_integralType = 4, RULE_floatingPointType = 5, RULE_referenceType = 6, 
		RULE_classOrInterfaceType = 7, RULE_classType = 8, RULE_classType_lf_classOrInterfaceType = 9, 
		RULE_classType_lfno_classOrInterfaceType = 10, RULE_interfaceType = 11, 
		RULE_interfaceType_lf_classOrInterfaceType = 12, RULE_interfaceType_lfno_classOrInterfaceType = 13, 
		RULE_typeVariable = 14, RULE_arrayType = 15, RULE_dims = 16, RULE_typeParameter = 17, 
		RULE_typeParameterModifier = 18, RULE_typeBound = 19, RULE_additionalBound = 20, 
		RULE_typeArguments = 21, RULE_typeArgumentList = 22, RULE_typeArgument = 23, 
		RULE_wildcard = 24, RULE_wildcardBounds = 25, RULE_packageName = 26, RULE_typeName = 27, 
		RULE_packageOrTypeName = 28, RULE_expressionName = 29, RULE_methodName = 30, 
		RULE_ambiguousName = 31, RULE_compilationUnit = 32, RULE_packageDeclaration = 33, 
		RULE_packageModifier = 34, RULE_importDeclaration = 35, RULE_singleTypeImportDeclaration = 36, 
		RULE_typeImportOnDemandDeclaration = 37, RULE_singleStaticImportDeclaration = 38, 
		RULE_staticImportOnDemandDeclaration = 39, RULE_typeDeclaration = 40, 
		RULE_classDeclaration = 41, RULE_normalClassDeclaration = 42, RULE_classModifiers = 43, 
		RULE_classModifier = 44, RULE_typeParameters = 45, RULE_typeParameterList = 46, 
		RULE_superclass = 47, RULE_superinterfaces = 48, RULE_interfaceTypeList = 49, 
		RULE_classBody = 50, RULE_classBodyDeclaration = 51, RULE_classMemberDeclaration = 52, 
		RULE_fieldDeclaration = 53, RULE_fieldModifiers = 54, RULE_fieldModifier = 55, 
		RULE_variableDeclaratorList = 56, RULE_variableDeclarator = 57, RULE_variableDeclaratorId = 58, 
		RULE_variableInitializer = 59, RULE_unannType = 60, RULE_unannPrimitiveType = 61, 
		RULE_unannReferenceType = 62, RULE_unannClassOrInterfaceType = 63, RULE_unannClassType = 64, 
		RULE_unannClassType_lf_unannClassOrInterfaceType = 65, RULE_unannClassType_lfno_unannClassOrInterfaceType = 66, 
		RULE_unannInterfaceType = 67, RULE_unannInterfaceType_lf_unannClassOrInterfaceType = 68, 
		RULE_unannInterfaceType_lfno_unannClassOrInterfaceType = 69, RULE_unannTypeVariable = 70, 
		RULE_unannArrayType = 71, RULE_methodDeclaration = 72, RULE_methodModifiers = 73, 
		RULE_methodModifier = 74, RULE_methodHeader = 75, RULE_result = 76, RULE_methodDeclarator = 77, 
		RULE_formalParameterList = 78, RULE_formalParameters = 79, RULE_formalParameter = 80, 
		RULE_variableModifier = 81, RULE_lastFormalParameter = 82, RULE_receiverParameter = 83, 
		RULE_throws_ = 84, RULE_exceptionTypeList = 85, RULE_exceptionType = 86, 
		RULE_methodBody = 87, RULE_instanceInitializer = 88, RULE_staticInitializer = 89, 
		RULE_constructorDeclaration = 90, RULE_constructorModifiers = 91, RULE_constructorModifier = 92, 
		RULE_constructorDeclarator = 93, RULE_simpleTypeName = 94, RULE_constructorBody = 95, 
		RULE_explicitConstructorInvocation = 96, RULE_enumDeclaration = 97, RULE_enumBody = 98, 
		RULE_enumConstantList = 99, RULE_enumConstant = 100, RULE_enumConstantModifier = 101, 
		RULE_enumBodyDeclarations = 102, RULE_interfaceDeclaration = 103, RULE_normalInterfaceDeclaration = 104, 
		RULE_interfaceModifier = 105, RULE_extendsInterfaces = 106, RULE_interfaceBody = 107, 
		RULE_interfaceMemberDeclaration = 108, RULE_constantDeclaration = 109, 
		RULE_constantModifier = 110, RULE_interfaceMethodDeclaration = 111, RULE_interfaceMethodModifier = 112, 
		RULE_annotationTypeDeclaration = 113, RULE_annotationTypeBody = 114, RULE_annotationTypeMemberDeclaration = 115, 
		RULE_annotationTypeElementDeclaration = 116, RULE_annotationTypeElementModifier = 117, 
		RULE_defaultValue = 118, RULE_annotation = 119, RULE_annotationIdentifier = 120, 
		RULE_annotationDim = 121, RULE_normalAnnotation = 122, RULE_elementValuePairList = 123, 
		RULE_elementValuePair = 124, RULE_elementValue = 125, RULE_elementValueArrayInitializer = 126, 
		RULE_elementValueList = 127, RULE_markerAnnotation = 128, RULE_singleElementAnnotation = 129, 
		RULE_arrayInitializer = 130, RULE_variableInitializerList = 131, RULE_block = 132, 
		RULE_blockStatements = 133, RULE_blockStatement = 134, RULE_localVariableDeclarationStatement = 135, 
		RULE_localVariableDeclaration = 136, RULE_statement = 137, RULE_statementNoShortIf = 138, 
		RULE_statementWithoutTrailingSubstatement = 139, RULE_emptyStatement = 140, 
		RULE_labeledStatement = 141, RULE_labeledStatementNoShortIf = 142, RULE_expressionStatement = 143, 
		RULE_statementExpression = 144, RULE_ifThenStatement = 145, RULE_ifThenElseStatement = 146, 
		RULE_ifThenElseStatementNoShortIf = 147, RULE_assertStatement = 148, RULE_switchStatement = 149, 
		RULE_switchBlock = 150, RULE_switchBlockStatementGroup = 151, RULE_switchLabels = 152, 
		RULE_switchLabel = 153, RULE_enumConstantName = 154, RULE_whileStatement = 155, 
		RULE_whileStatementNoShortIf = 156, RULE_doStatement = 157, RULE_forStatement = 158, 
		RULE_forStatementNoShortIf = 159, RULE_basicForStatement = 160, RULE_basicForStatementNoShortIf = 161, 
		RULE_forInit = 162, RULE_forUpdate = 163, RULE_statementExpressionList = 164, 
		RULE_enhancedForStatement = 165, RULE_enhancedForStatementNoShortIf = 166, 
		RULE_breakStatement = 167, RULE_continueStatement = 168, RULE_returnStatement = 169, 
		RULE_throwStatement = 170, RULE_synchronizedStatement = 171, RULE_tryStatement = 172, 
		RULE_catches = 173, RULE_catchClause = 174, RULE_catchFormalParameter = 175, 
		RULE_catchType = 176, RULE_finally_ = 177, RULE_tryWithResourcesStatement = 178, 
		RULE_resourceSpecification = 179, RULE_resourceList = 180, RULE_resource = 181, 
		RULE_primary = 182, RULE_primaryNoNewArray = 183, RULE_primaryNoNewArray_lf_arrayAccess = 184, 
		RULE_primaryNoNewArray_lfno_arrayAccess = 185, RULE_primaryNoNewArray_lf_primary = 186, 
		RULE_primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary = 187, RULE_primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary = 188, 
		RULE_primaryNoNewArray_lfno_primary = 189, RULE_primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary = 190, 
		RULE_primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary = 191, 
		RULE_classInstanceCreationExpression = 192, RULE_classInstanceCreationExpression_lf_primary = 193, 
		RULE_classInstanceCreationExpression_lfno_primary = 194, RULE_typeArgumentsOrDiamond = 195, 
		RULE_fieldAccess = 196, RULE_fieldAccess_lf_primary = 197, RULE_fieldAccess_lfno_primary = 198, 
		RULE_arrayAccess = 199, RULE_arrayAccess_lf_primary = 200, RULE_arrayAccess_lfno_primary = 201, 
		RULE_methodInvocation = 202, RULE_methodInvocation_lf_primary = 203, RULE_methodInvocation_lfno_primary = 204, 
		RULE_argumentList = 205, RULE_methodReference = 206, RULE_methodReference_lf_primary = 207, 
		RULE_methodReference_lfno_primary = 208, RULE_arrayCreationExpression = 209, 
		RULE_dimExprs = 210, RULE_dimExpr = 211, RULE_constantExpression = 212, 
		RULE_expression = 213, RULE_lambdaExpression = 214, RULE_lambdaParameters = 215, 
		RULE_inferredFormalParameterList = 216, RULE_lambdaBody = 217, RULE_assignmentExpression = 218, 
		RULE_assignment = 219, RULE_leftHandSide = 220, RULE_assignmentOperator = 221, 
		RULE_additiveOperator = 222, RULE_relationalOperator = 223, RULE_multiplicativeOperator = 224, 
		RULE_squareBrackets = 225, RULE_conditionalExpression = 226, RULE_conditionalOrExpression = 227, 
		RULE_conditionalAndExpression = 228, RULE_inclusiveOrExpression = 229, 
		RULE_exclusiveOrExpression = 230, RULE_andExpression = 231, RULE_equalityExpression = 232, 
		RULE_relationalExpression = 233, RULE_shiftExpression = 234, RULE_shiftOperator = 235, 
		RULE_additiveExpression = 236, RULE_multiplicativeExpression = 237, RULE_unaryExpression = 238, 
		RULE_preIncrementExpression = 239, RULE_preDecrementExpression = 240, 
		RULE_unaryExpressionNotPlusMinus = 241, RULE_postfixExpression = 242, 
		RULE_postIncrementExpression = 243, RULE_postIncrementExpression_lf_postfixExpression = 244, 
		RULE_postDecrementExpression = 245, RULE_postDecrementExpression_lf_postfixExpression = 246, 
		RULE_castExpression = 247;
	public static final String[] ruleNames = {
		"literal", "type", "primitiveType", "numericType", "integralType", "floatingPointType", 
		"referenceType", "classOrInterfaceType", "classType", "classType_lf_classOrInterfaceType", 
		"classType_lfno_classOrInterfaceType", "interfaceType", "interfaceType_lf_classOrInterfaceType", 
		"interfaceType_lfno_classOrInterfaceType", "typeVariable", "arrayType", 
		"dims", "typeParameter", "typeParameterModifier", "typeBound", "additionalBound", 
		"typeArguments", "typeArgumentList", "typeArgument", "wildcard", "wildcardBounds", 
		"packageName", "typeName", "packageOrTypeName", "expressionName", "methodName", 
		"ambiguousName", "compilationUnit", "packageDeclaration", "packageModifier", 
		"importDeclaration", "singleTypeImportDeclaration", "typeImportOnDemandDeclaration", 
		"singleStaticImportDeclaration", "staticImportOnDemandDeclaration", "typeDeclaration", 
		"classDeclaration", "normalClassDeclaration", "classModifiers", "classModifier", 
		"typeParameters", "typeParameterList", "superclass", "superinterfaces", 
		"interfaceTypeList", "classBody", "classBodyDeclaration", "classMemberDeclaration", 
		"fieldDeclaration", "fieldModifiers", "fieldModifier", "variableDeclaratorList", 
		"variableDeclarator", "variableDeclaratorId", "variableInitializer", "unannType", 
		"unannPrimitiveType", "unannReferenceType", "unannClassOrInterfaceType", 
		"unannClassType", "unannClassType_lf_unannClassOrInterfaceType", "unannClassType_lfno_unannClassOrInterfaceType", 
		"unannInterfaceType", "unannInterfaceType_lf_unannClassOrInterfaceType", 
		"unannInterfaceType_lfno_unannClassOrInterfaceType", "unannTypeVariable", 
		"unannArrayType", "methodDeclaration", "methodModifiers", "methodModifier", 
		"methodHeader", "result", "methodDeclarator", "formalParameterList", "formalParameters", 
		"formalParameter", "variableModifier", "lastFormalParameter", "receiverParameter", 
		"throws_", "exceptionTypeList", "exceptionType", "methodBody", "instanceInitializer", 
		"staticInitializer", "constructorDeclaration", "constructorModifiers", 
		"constructorModifier", "constructorDeclarator", "simpleTypeName", "constructorBody", 
		"explicitConstructorInvocation", "enumDeclaration", "enumBody", "enumConstantList", 
		"enumConstant", "enumConstantModifier", "enumBodyDeclarations", "interfaceDeclaration", 
		"normalInterfaceDeclaration", "interfaceModifier", "extendsInterfaces", 
		"interfaceBody", "interfaceMemberDeclaration", "constantDeclaration", 
		"constantModifier", "interfaceMethodDeclaration", "interfaceMethodModifier", 
		"annotationTypeDeclaration", "annotationTypeBody", "annotationTypeMemberDeclaration", 
		"annotationTypeElementDeclaration", "annotationTypeElementModifier", "defaultValue", 
		"annotation", "annotationIdentifier", "annotationDim", "normalAnnotation", 
		"elementValuePairList", "elementValuePair", "elementValue", "elementValueArrayInitializer", 
		"elementValueList", "markerAnnotation", "singleElementAnnotation", "arrayInitializer", 
		"variableInitializerList", "block", "blockStatements", "blockStatement", 
		"localVariableDeclarationStatement", "localVariableDeclaration", "statement", 
		"statementNoShortIf", "statementWithoutTrailingSubstatement", "emptyStatement", 
		"labeledStatement", "labeledStatementNoShortIf", "expressionStatement", 
		"statementExpression", "ifThenStatement", "ifThenElseStatement", "ifThenElseStatementNoShortIf", 
		"assertStatement", "switchStatement", "switchBlock", "switchBlockStatementGroup", 
		"switchLabels", "switchLabel", "enumConstantName", "whileStatement", "whileStatementNoShortIf", 
		"doStatement", "forStatement", "forStatementNoShortIf", "basicForStatement", 
		"basicForStatementNoShortIf", "forInit", "forUpdate", "statementExpressionList", 
		"enhancedForStatement", "enhancedForStatementNoShortIf", "breakStatement", 
		"continueStatement", "returnStatement", "throwStatement", "synchronizedStatement", 
		"tryStatement", "catches", "catchClause", "catchFormalParameter", "catchType", 
		"finally_", "tryWithResourcesStatement", "resourceSpecification", "resourceList", 
		"resource", "primary", "primaryNoNewArray", "primaryNoNewArray_lf_arrayAccess", 
		"primaryNoNewArray_lfno_arrayAccess", "primaryNoNewArray_lf_primary", 
		"primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary", "primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary", 
		"primaryNoNewArray_lfno_primary", "primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary", 
		"primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary", "classInstanceCreationExpression", 
		"classInstanceCreationExpression_lf_primary", "classInstanceCreationExpression_lfno_primary", 
		"typeArgumentsOrDiamond", "fieldAccess", "fieldAccess_lf_primary", "fieldAccess_lfno_primary", 
		"arrayAccess", "arrayAccess_lf_primary", "arrayAccess_lfno_primary", "methodInvocation", 
		"methodInvocation_lf_primary", "methodInvocation_lfno_primary", "argumentList", 
		"methodReference", "methodReference_lf_primary", "methodReference_lfno_primary", 
		"arrayCreationExpression", "dimExprs", "dimExpr", "constantExpression", 
		"expression", "lambdaExpression", "lambdaParameters", "inferredFormalParameterList", 
		"lambdaBody", "assignmentExpression", "assignment", "leftHandSide", "assignmentOperator", 
		"additiveOperator", "relationalOperator", "multiplicativeOperator", "squareBrackets", 
		"conditionalExpression", "conditionalOrExpression", "conditionalAndExpression", 
		"inclusiveOrExpression", "exclusiveOrExpression", "andExpression", "equalityExpression", 
		"relationalExpression", "shiftExpression", "shiftOperator", "additiveExpression", 
		"multiplicativeExpression", "unaryExpression", "preIncrementExpression", 
		"preDecrementExpression", "unaryExpressionNotPlusMinus", "postfixExpression", 
		"postIncrementExpression", "postIncrementExpression_lf_postfixExpression", 
		"postDecrementExpression", "postDecrementExpression_lf_postfixExpression", 
		"castExpression"
	};

	@Override
	public String getGrammarFileName() { return "Java8.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Java8Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NullLiteral() { return getToken(Java8Parser.NullLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(Java8Parser.CharacterLiteral, 0); }
		public TerminalNode IntegerLiteral() { return getToken(Java8Parser.IntegerLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(Java8Parser.StringLiteral, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(Java8Parser.FloatingPointLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(Java8Parser.BooleanLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class TypeContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type);
		try {
			setState(500);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(498); primitiveType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(499); referenceType();
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

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public NumericTypeContext numericType() {
			return getRuleContext(NumericTypeContext.class,0);
		}
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimitiveType(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_primitiveType);
		int _la;
		try {
			setState(516);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(505);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(502); annotation();
					}
					}
					setState(507);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(508); numericType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(509); annotation();
					}
					}
					setState(514);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(515); match(BOOLEAN);
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

	public static class NumericTypeContext extends ParserRuleContext {
		public FloatingPointTypeContext floatingPointType() {
			return getRuleContext(FloatingPointTypeContext.class,0);
		}
		public IntegralTypeContext integralType() {
			return getRuleContext(IntegralTypeContext.class,0);
		}
		public NumericTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterNumericType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitNumericType(this);
		}
	}

	public final NumericTypeContext numericType() throws RecognitionException {
		NumericTypeContext _localctx = new NumericTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_numericType);
		try {
			setState(520);
			switch (_input.LA(1)) {
			case BYTE:
			case CHAR:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(518); integralType();
				}
				break;
			case DOUBLE:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(519); floatingPointType();
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

	public static class IntegralTypeContext extends ParserRuleContext {
		public IntegralTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integralType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterIntegralType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitIntegralType(this);
		}
	}

	public final IntegralTypeContext integralType() throws RecognitionException {
		IntegralTypeContext _localctx = new IntegralTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_integralType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTE) | (1L << CHAR) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class FloatingPointTypeContext extends ParserRuleContext {
		public FloatingPointTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatingPointType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFloatingPointType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFloatingPointType(this);
		}
	}

	public final FloatingPointTypeContext floatingPointType() throws RecognitionException {
		FloatingPointTypeContext _localctx = new FloatingPointTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_floatingPointType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524);
			_la = _input.LA(1);
			if ( !(_la==DOUBLE || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class ReferenceTypeContext extends ParserRuleContext {
		public TypeVariableContext typeVariable() {
			return getRuleContext(TypeVariableContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public ReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterReferenceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitReferenceType(this);
		}
	}

	public final ReferenceTypeContext referenceType() throws RecognitionException {
		ReferenceTypeContext _localctx = new ReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_referenceType);
		try {
			setState(529);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(526); classOrInterfaceType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(527); typeVariable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(528); arrayType();
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

	public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
		public List<InterfaceType_lf_classOrInterfaceTypeContext> interfaceType_lf_classOrInterfaceType() {
			return getRuleContexts(InterfaceType_lf_classOrInterfaceTypeContext.class);
		}
		public InterfaceType_lfno_classOrInterfaceTypeContext interfaceType_lfno_classOrInterfaceType() {
			return getRuleContext(InterfaceType_lfno_classOrInterfaceTypeContext.class,0);
		}
		public InterfaceType_lf_classOrInterfaceTypeContext interfaceType_lf_classOrInterfaceType(int i) {
			return getRuleContext(InterfaceType_lf_classOrInterfaceTypeContext.class,i);
		}
		public ClassType_lf_classOrInterfaceTypeContext classType_lf_classOrInterfaceType(int i) {
			return getRuleContext(ClassType_lf_classOrInterfaceTypeContext.class,i);
		}
		public List<ClassType_lf_classOrInterfaceTypeContext> classType_lf_classOrInterfaceType() {
			return getRuleContexts(ClassType_lf_classOrInterfaceTypeContext.class);
		}
		public ClassType_lfno_classOrInterfaceTypeContext classType_lfno_classOrInterfaceType() {
			return getRuleContext(ClassType_lfno_classOrInterfaceTypeContext.class,0);
		}
		public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassOrInterfaceType(this);
		}
	}

	public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
		ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classOrInterfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(531); classType_lfno_classOrInterfaceType();
				}
				break;
			case 2:
				{
				setState(532); interfaceType_lfno_classOrInterfaceType();
				}
				break;
			}
			setState(539);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(537);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(535); classType_lf_classOrInterfaceType();
						}
						break;
					case 2:
						{
						setState(536); interfaceType_lf_classOrInterfaceType();
						}
						break;
					}
					} 
				}
				setState(541);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class ClassTypeContext extends ParserRuleContext {
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassType(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_classType);
		int _la;
		try {
			setState(552);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(542); annotationIdentifier();
				setState(544);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(543); typeArguments();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(546); classOrInterfaceType();
				setState(547); match(DOT);
				setState(548); annotationIdentifier();
				setState(550);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(549); typeArguments();
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

	public static class ClassType_lf_classOrInterfaceTypeContext extends ParserRuleContext {
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassType_lf_classOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType_lf_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassType_lf_classOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassType_lf_classOrInterfaceType(this);
		}
	}

	public final ClassType_lf_classOrInterfaceTypeContext classType_lf_classOrInterfaceType() throws RecognitionException {
		ClassType_lf_classOrInterfaceTypeContext _localctx = new ClassType_lf_classOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classType_lf_classOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554); match(DOT);
			setState(555); annotationIdentifier();
			setState(557);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(556); typeArguments();
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

	public static class ClassType_lfno_classOrInterfaceTypeContext extends ParserRuleContext {
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassType_lfno_classOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType_lfno_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassType_lfno_classOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassType_lfno_classOrInterfaceType(this);
		}
	}

	public final ClassType_lfno_classOrInterfaceTypeContext classType_lfno_classOrInterfaceType() throws RecognitionException {
		ClassType_lfno_classOrInterfaceTypeContext _localctx = new ClassType_lfno_classOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classType_lfno_classOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559); annotationIdentifier();
			setState(561);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(560); typeArguments();
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

	public static class InterfaceTypeContext extends ParserRuleContext {
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public InterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceType(this);
		}
	}

	public final InterfaceTypeContext interfaceType() throws RecognitionException {
		InterfaceTypeContext _localctx = new InterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_interfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563); classType();
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

	public static class InterfaceType_lf_classOrInterfaceTypeContext extends ParserRuleContext {
		public ClassType_lf_classOrInterfaceTypeContext classType_lf_classOrInterfaceType() {
			return getRuleContext(ClassType_lf_classOrInterfaceTypeContext.class,0);
		}
		public InterfaceType_lf_classOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceType_lf_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceType_lf_classOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceType_lf_classOrInterfaceType(this);
		}
	}

	public final InterfaceType_lf_classOrInterfaceTypeContext interfaceType_lf_classOrInterfaceType() throws RecognitionException {
		InterfaceType_lf_classOrInterfaceTypeContext _localctx = new InterfaceType_lf_classOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_interfaceType_lf_classOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565); classType_lf_classOrInterfaceType();
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

	public static class InterfaceType_lfno_classOrInterfaceTypeContext extends ParserRuleContext {
		public ClassType_lfno_classOrInterfaceTypeContext classType_lfno_classOrInterfaceType() {
			return getRuleContext(ClassType_lfno_classOrInterfaceTypeContext.class,0);
		}
		public InterfaceType_lfno_classOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceType_lfno_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceType_lfno_classOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceType_lfno_classOrInterfaceType(this);
		}
	}

	public final InterfaceType_lfno_classOrInterfaceTypeContext interfaceType_lfno_classOrInterfaceType() throws RecognitionException {
		InterfaceType_lfno_classOrInterfaceTypeContext _localctx = new InterfaceType_lfno_classOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_interfaceType_lfno_classOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(567); classType_lfno_classOrInterfaceType();
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

	public static class TypeVariableContext extends ParserRuleContext {
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public TypeVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeVariable(this);
		}
	}

	public final TypeVariableContext typeVariable() throws RecognitionException {
		TypeVariableContext _localctx = new TypeVariableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typeVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(569); annotationIdentifier();
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

	public static class ArrayTypeContext extends ParserRuleContext {
		public TypeVariableContext typeVariable() {
			return getRuleContext(TypeVariableContext.class,0);
		}
		public DimsContext dims() {
			return getRuleContext(DimsContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArrayType(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arrayType);
		try {
			setState(580);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(571); primitiveType();
				setState(572); dims();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(574); classOrInterfaceType();
				setState(575); dims();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(577); typeVariable();
				setState(578); dims();
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

	public static class DimsContext extends ParserRuleContext {
		public List<AnnotationDimContext> annotationDim() {
			return getRuleContexts(AnnotationDimContext.class);
		}
		public AnnotationDimContext annotationDim(int i) {
			return getRuleContext(AnnotationDimContext.class,i);
		}
		public DimsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dims; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterDims(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitDims(this);
		}
	}

	public final DimsContext dims() throws RecognitionException {
		DimsContext _localctx = new DimsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dims);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(582); annotationDim();
			setState(586);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(583); annotationDim();
					}
					} 
				}
				setState(588);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class TypeParameterContext extends ParserRuleContext {
		public List<TypeParameterModifierContext> typeParameterModifier() {
			return getRuleContexts(TypeParameterModifierContext.class);
		}
		public TypeParameterModifierContext typeParameterModifier(int i) {
			return getRuleContext(TypeParameterModifierContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeBoundContext typeBound() {
			return getRuleContext(TypeBoundContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeParameter(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(592);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(589); typeParameterModifier();
				}
				}
				setState(594);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(595); match(Identifier);
			setState(597);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(596); typeBound();
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

	public static class TypeParameterModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TypeParameterModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeParameterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeParameterModifier(this);
		}
	}

	public final TypeParameterModifierContext typeParameterModifier() throws RecognitionException {
		TypeParameterModifierContext _localctx = new TypeParameterModifierContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeParameterModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599); annotation();
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
		public List<AdditionalBoundContext> additionalBound() {
			return getRuleContexts(AdditionalBoundContext.class);
		}
		public TypeVariableContext typeVariable() {
			return getRuleContext(TypeVariableContext.class,0);
		}
		public AdditionalBoundContext additionalBound(int i) {
			return getRuleContext(AdditionalBoundContext.class,i);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public TypeBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeBound(this);
		}
	}

	public final TypeBoundContext typeBound() throws RecognitionException {
		TypeBoundContext _localctx = new TypeBoundContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_typeBound);
		int _la;
		try {
			setState(611);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(601); match(EXTENDS);
				setState(602); typeVariable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(603); match(EXTENDS);
				setState(604); classOrInterfaceType();
				setState(608);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BITAND) {
					{
					{
					setState(605); additionalBound();
					}
					}
					setState(610);
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

	public static class AdditionalBoundContext extends ParserRuleContext {
		public InterfaceTypeContext interfaceType() {
			return getRuleContext(InterfaceTypeContext.class,0);
		}
		public AdditionalBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additionalBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAdditionalBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAdditionalBound(this);
		}
	}

	public final AdditionalBoundContext additionalBound() throws RecognitionException {
		AdditionalBoundContext _localctx = new AdditionalBoundContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_additionalBound);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(613); match(BITAND);
			setState(614); interfaceType();
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
		public TypeArgumentListContext typeArgumentList() {
			return getRuleContext(TypeArgumentListContext.class,0);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeArguments(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeArguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616); match(LT);
			setState(617); typeArgumentList();
			setState(618); match(GT);
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

	public static class TypeArgumentListContext extends ParserRuleContext {
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public TypeArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeArgumentList(this);
		}
	}

	public final TypeArgumentListContext typeArgumentList() throws RecognitionException {
		TypeArgumentListContext _localctx = new TypeArgumentListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_typeArgumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(620); typeArgument();
			setState(625);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(621); match(COMMA);
				setState(622); typeArgument();
				}
				}
				setState(627);
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

	public static class TypeArgumentContext extends ParserRuleContext {
		public WildcardContext wildcard() {
			return getRuleContext(WildcardContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeArgument(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeArgument);
		try {
			setState(630);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(628); referenceType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(629); wildcard();
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

	public static class WildcardContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public WildcardBoundsContext wildcardBounds() {
			return getRuleContext(WildcardBoundsContext.class,0);
		}
		public WildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitWildcard(this);
		}
	}

	public final WildcardContext wildcard() throws RecognitionException {
		WildcardContext _localctx = new WildcardContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_wildcard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(635);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(632); annotation();
				}
				}
				setState(637);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(638); match(QUESTION);
			setState(640);
			_la = _input.LA(1);
			if (_la==EXTENDS || _la==SUPER) {
				{
				setState(639); wildcardBounds();
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

	public static class WildcardBoundsContext extends ParserRuleContext {
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public WildcardBoundsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcardBounds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterWildcardBounds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitWildcardBounds(this);
		}
	}

	public final WildcardBoundsContext wildcardBounds() throws RecognitionException {
		WildcardBoundsContext _localctx = new WildcardBoundsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_wildcardBounds);
		try {
			setState(646);
			switch (_input.LA(1)) {
			case EXTENDS:
				enterOuterAlt(_localctx, 1);
				{
				setState(642); match(EXTENDS);
				setState(643); referenceType();
				}
				break;
			case SUPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(644); match(SUPER);
				setState(645); referenceType();
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

	public static class PackageNameContext extends ParserRuleContext {
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPackageName(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		return packageName(0);
	}

	private PackageNameContext packageName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PackageNameContext _localctx = new PackageNameContext(_ctx, _parentState);
		PackageNameContext _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_packageName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(649); match(Identifier);
			}
			_ctx.stop = _input.LT(-1);
			setState(656);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PackageNameContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_packageName);
					setState(651);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(652); match(DOT);
					setState(653); match(Identifier);
					}
					} 
				}
				setState(658);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public PackageOrTypeNameContext packageOrTypeName() {
			return getRuleContext(PackageOrTypeNameContext.class,0);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeName(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_typeName);
		try {
			setState(664);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(659); match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(660); packageOrTypeName(0);
				setState(661); match(DOT);
				setState(662); match(Identifier);
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

	public static class PackageOrTypeNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public PackageOrTypeNameContext packageOrTypeName() {
			return getRuleContext(PackageOrTypeNameContext.class,0);
		}
		public PackageOrTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageOrTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPackageOrTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPackageOrTypeName(this);
		}
	}

	public final PackageOrTypeNameContext packageOrTypeName() throws RecognitionException {
		return packageOrTypeName(0);
	}

	private PackageOrTypeNameContext packageOrTypeName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PackageOrTypeNameContext _localctx = new PackageOrTypeNameContext(_ctx, _parentState);
		PackageOrTypeNameContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_packageOrTypeName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(667); match(Identifier);
			}
			_ctx.stop = _input.LT(-1);
			setState(674);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PackageOrTypeNameContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_packageOrTypeName);
					setState(669);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(670); match(DOT);
					setState(671); match(Identifier);
					}
					} 
				}
				setState(676);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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

	public static class ExpressionNameContext extends ParserRuleContext {
		public AmbiguousNameContext ambiguousName() {
			return getRuleContext(AmbiguousNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public ExpressionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExpressionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExpressionName(this);
		}
	}

	public final ExpressionNameContext expressionName() throws RecognitionException {
		ExpressionNameContext _localctx = new ExpressionNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expressionName);
		try {
			setState(682);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(677); match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(678); ambiguousName(0);
				setState(679); match(DOT);
				setState(680); match(Identifier);
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

	public static class MethodNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public MethodNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodName(this);
		}
	}

	public final MethodNameContext methodName() throws RecognitionException {
		MethodNameContext _localctx = new MethodNameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(684); match(Identifier);
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

	public static class AmbiguousNameContext extends ParserRuleContext {
		public AmbiguousNameContext ambiguousName() {
			return getRuleContext(AmbiguousNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public AmbiguousNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ambiguousName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAmbiguousName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAmbiguousName(this);
		}
	}

	public final AmbiguousNameContext ambiguousName() throws RecognitionException {
		return ambiguousName(0);
	}

	private AmbiguousNameContext ambiguousName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AmbiguousNameContext _localctx = new AmbiguousNameContext(_ctx, _parentState);
		AmbiguousNameContext _prevctx = _localctx;
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_ambiguousName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(687); match(Identifier);
			}
			_ctx.stop = _input.LT(-1);
			setState(694);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AmbiguousNameContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_ambiguousName);
					setState(689);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(690); match(DOT);
					setState(691); match(Identifier);
					}
					} 
				}
				setState(696);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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

	public static class CompilationUnitContext extends ParserRuleContext {
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public TerminalNode EOF() { return getToken(Java8Parser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
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
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(697); packageDeclaration();
				}
				break;
			}
			setState(703);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(700); importDeclaration();
				}
				}
				setState(705);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(709);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << CLASS) | (1L << ENUM) | (1L << FINAL) | (1L << INTERFACE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==SEMI || _la==AT) {
				{
				{
				setState(706); typeDeclaration();
				}
				}
				setState(711);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(712); match(EOF);
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
		public PackageModifierContext packageModifier(int i) {
			return getRuleContext(PackageModifierContext.class,i);
		}
		public List<PackageModifierContext> packageModifier() {
			return getRuleContexts(PackageModifierContext.class);
		}
		public TerminalNode Identifier(int i) {
			return getToken(Java8Parser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(Java8Parser.Identifier); }
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPackageDeclaration(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_packageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(717);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(714); packageModifier();
				}
				}
				setState(719);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(720); match(PACKAGE);
			setState(721); match(Identifier);
			setState(726);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(722); match(DOT);
				setState(723); match(Identifier);
				}
				}
				setState(728);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(729); match(SEMI);
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

	public static class PackageModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public PackageModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPackageModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPackageModifier(this);
		}
	}

	public final PackageModifierContext packageModifier() throws RecognitionException {
		PackageModifierContext _localctx = new PackageModifierContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_packageModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731); annotation();
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
		public StaticImportOnDemandDeclarationContext staticImportOnDemandDeclaration() {
			return getRuleContext(StaticImportOnDemandDeclarationContext.class,0);
		}
		public SingleTypeImportDeclarationContext singleTypeImportDeclaration() {
			return getRuleContext(SingleTypeImportDeclarationContext.class,0);
		}
		public SingleStaticImportDeclarationContext singleStaticImportDeclaration() {
			return getRuleContext(SingleStaticImportDeclarationContext.class,0);
		}
		public TypeImportOnDemandDeclarationContext typeImportOnDemandDeclaration() {
			return getRuleContext(TypeImportOnDemandDeclarationContext.class,0);
		}
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitImportDeclaration(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_importDeclaration);
		try {
			setState(737);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(733); singleTypeImportDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(734); typeImportOnDemandDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(735); singleStaticImportDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(736); staticImportOnDemandDeclaration();
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

	public static class SingleTypeImportDeclarationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public SingleTypeImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleTypeImportDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSingleTypeImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSingleTypeImportDeclaration(this);
		}
	}

	public final SingleTypeImportDeclarationContext singleTypeImportDeclaration() throws RecognitionException {
		SingleTypeImportDeclarationContext _localctx = new SingleTypeImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_singleTypeImportDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(739); match(IMPORT);
			setState(740); typeName();
			setState(741); match(SEMI);
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

	public static class TypeImportOnDemandDeclarationContext extends ParserRuleContext {
		public PackageOrTypeNameContext packageOrTypeName() {
			return getRuleContext(PackageOrTypeNameContext.class,0);
		}
		public TypeImportOnDemandDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeImportOnDemandDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeImportOnDemandDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeImportOnDemandDeclaration(this);
		}
	}

	public final TypeImportOnDemandDeclarationContext typeImportOnDemandDeclaration() throws RecognitionException {
		TypeImportOnDemandDeclarationContext _localctx = new TypeImportOnDemandDeclarationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_typeImportOnDemandDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743); match(IMPORT);
			setState(744); packageOrTypeName(0);
			setState(745); match(DOT);
			setState(746); match(MUL);
			setState(747); match(SEMI);
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

	public static class SingleStaticImportDeclarationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public SingleStaticImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleStaticImportDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSingleStaticImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSingleStaticImportDeclaration(this);
		}
	}

	public final SingleStaticImportDeclarationContext singleStaticImportDeclaration() throws RecognitionException {
		SingleStaticImportDeclarationContext _localctx = new SingleStaticImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_singleStaticImportDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(749); match(IMPORT);
			setState(750); match(STATIC);
			setState(751); typeName();
			setState(752); match(DOT);
			setState(753); match(Identifier);
			setState(754); match(SEMI);
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

	public static class StaticImportOnDemandDeclarationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public StaticImportOnDemandDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticImportOnDemandDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStaticImportOnDemandDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStaticImportOnDemandDeclaration(this);
		}
	}

	public final StaticImportOnDemandDeclarationContext staticImportOnDemandDeclaration() throws RecognitionException {
		StaticImportOnDemandDeclarationContext _localctx = new StaticImportOnDemandDeclarationContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_staticImportOnDemandDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(756); match(IMPORT);
			setState(757); match(STATIC);
			setState(758); typeName();
			setState(759); match(DOT);
			setState(760); match(MUL);
			setState(761); match(SEMI);
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
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeDeclaration(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_typeDeclaration);
		try {
			setState(766);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(763); classDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(764); interfaceDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(765); match(SEMI);
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

	public static class ClassDeclarationContext extends ParserRuleContext {
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public NormalClassDeclarationContext normalClassDeclaration() {
			return getRuleContext(NormalClassDeclarationContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_classDeclaration);
		try {
			setState(770);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(768); normalClassDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(769); enumDeclaration();
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

	public static class NormalClassDeclarationContext extends ParserRuleContext {
		public SuperclassContext superclass() {
			return getRuleContext(SuperclassContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ClassModifiersContext classModifiers() {
			return getRuleContext(ClassModifiersContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public SuperinterfacesContext superinterfaces() {
			return getRuleContext(SuperinterfacesContext.class,0);
		}
		public NormalClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalClassDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterNormalClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitNormalClassDeclaration(this);
		}
	}

	public final NormalClassDeclarationContext normalClassDeclaration() throws RecognitionException {
		NormalClassDeclarationContext _localctx = new NormalClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_normalClassDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772); classModifiers();
			setState(773); match(CLASS);
			setState(774); match(Identifier);
			setState(776);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(775); typeParameters();
				}
			}

			setState(779);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(778); superclass();
				}
			}

			setState(782);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(781); superinterfaces();
				}
			}

			setState(784); classBody();
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

	public static class ClassModifiersContext extends ParserRuleContext {
		public List<ClassModifierContext> classModifier() {
			return getRuleContexts(ClassModifierContext.class);
		}
		public ClassModifierContext classModifier(int i) {
			return getRuleContext(ClassModifierContext.class,i);
		}
		public ClassModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassModifiers(this);
		}
	}

	public final ClassModifiersContext classModifiers() throws RecognitionException {
		ClassModifiersContext _localctx = new ClassModifiersContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_classModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(789);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
				{
				{
				setState(786); classModifier();
				}
				}
				setState(791);
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

	public static class ClassModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ClassModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassModifier(this);
		}
	}

	public final ClassModifierContext classModifier() throws RecognitionException {
		ClassModifierContext _localctx = new ClassModifierContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_classModifier);
		try {
			setState(800);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(792); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(793); match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(794); match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(795); match(PRIVATE);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 5);
				{
				setState(796); match(ABSTRACT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 6);
				{
				setState(797); match(STATIC);
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(798); match(FINAL);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 8);
				{
				setState(799); match(STRICTFP);
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

	public static class TypeParametersContext extends ParserRuleContext {
		public TypeParameterListContext typeParameterList() {
			return getRuleContext(TypeParameterListContext.class,0);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeParameters(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_typeParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(802); match(LT);
			setState(803); typeParameterList();
			setState(804); match(GT);
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

	public static class TypeParameterListContext extends ParserRuleContext {
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public TypeParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeParameterList(this);
		}
	}

	public final TypeParameterListContext typeParameterList() throws RecognitionException {
		TypeParameterListContext _localctx = new TypeParameterListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_typeParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806); typeParameter();
			setState(811);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(807); match(COMMA);
				setState(808); typeParameter();
				}
				}
				setState(813);
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

	public static class SuperclassContext extends ParserRuleContext {
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public SuperclassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superclass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSuperclass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSuperclass(this);
		}
	}

	public final SuperclassContext superclass() throws RecognitionException {
		SuperclassContext _localctx = new SuperclassContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_superclass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(814); match(EXTENDS);
			setState(815); classType();
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

	public static class SuperinterfacesContext extends ParserRuleContext {
		public InterfaceTypeListContext interfaceTypeList() {
			return getRuleContext(InterfaceTypeListContext.class,0);
		}
		public SuperinterfacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superinterfaces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSuperinterfaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSuperinterfaces(this);
		}
	}

	public final SuperinterfacesContext superinterfaces() throws RecognitionException {
		SuperinterfacesContext _localctx = new SuperinterfacesContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_superinterfaces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(817); match(IMPLEMENTS);
			setState(818); interfaceTypeList();
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

	public static class InterfaceTypeListContext extends ParserRuleContext {
		public List<InterfaceTypeContext> interfaceType() {
			return getRuleContexts(InterfaceTypeContext.class);
		}
		public InterfaceTypeContext interfaceType(int i) {
			return getRuleContext(InterfaceTypeContext.class,i);
		}
		public InterfaceTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceTypeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceTypeList(this);
		}
	}

	public final InterfaceTypeListContext interfaceTypeList() throws RecognitionException {
		InterfaceTypeListContext _localctx = new InterfaceTypeListContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_interfaceTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(820); interfaceType();
			setState(825);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(821); match(COMMA);
				setState(822); interfaceType();
				}
				}
				setState(827);
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

	public static class ClassBodyContext extends ParserRuleContext {
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(828); match(LBRACE);
			setState(832);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				{
				setState(829); classBodyDeclaration();
				}
				}
				setState(834);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(835); match(RBRACE);
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
		public StaticInitializerContext staticInitializer() {
			return getRuleContext(StaticInitializerContext.class,0);
		}
		public InstanceInitializerContext instanceInitializer() {
			return getRuleContext(InstanceInitializerContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public ClassMemberDeclarationContext classMemberDeclaration() {
			return getRuleContext(ClassMemberDeclarationContext.class,0);
		}
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassBodyDeclaration(this);
		}
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_classBodyDeclaration);
		try {
			setState(841);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(837); classMemberDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(838); instanceInitializer();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(839); staticInitializer();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(840); constructorDeclaration();
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

	public static class ClassMemberDeclarationContext extends ParserRuleContext {
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public ClassMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassMemberDeclaration(this);
		}
	}

	public final ClassMemberDeclarationContext classMemberDeclaration() throws RecognitionException {
		ClassMemberDeclarationContext _localctx = new ClassMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_classMemberDeclaration);
		try {
			setState(848);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(843); fieldDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(844); methodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(845); classDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(846); interfaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(847); match(SEMI);
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

	public static class FieldDeclarationContext extends ParserRuleContext {
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableDeclaratorListContext variableDeclaratorList() {
			return getRuleContext(VariableDeclaratorListContext.class,0);
		}
		public FieldModifiersContext fieldModifiers() {
			return getRuleContext(FieldModifiersContext.class,0);
		}
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFieldDeclaration(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_fieldDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(850); fieldModifiers();
			setState(851); unannType();
			setState(852); variableDeclaratorList();
			setState(853); match(SEMI);
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

	public static class FieldModifiersContext extends ParserRuleContext {
		public List<FieldModifierContext> fieldModifier() {
			return getRuleContexts(FieldModifierContext.class);
		}
		public FieldModifierContext fieldModifier(int i) {
			return getRuleContext(FieldModifierContext.class,i);
		}
		public FieldModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFieldModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFieldModifiers(this);
		}
	}

	public final FieldModifiersContext fieldModifiers() throws RecognitionException {
		FieldModifiersContext _localctx = new FieldModifiersContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_fieldModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(858);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
				{
				{
				setState(855); fieldModifier();
				}
				}
				setState(860);
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

	public static class FieldModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public FieldModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFieldModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFieldModifier(this);
		}
	}

	public final FieldModifierContext fieldModifier() throws RecognitionException {
		FieldModifierContext _localctx = new FieldModifierContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_fieldModifier);
		try {
			setState(869);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(861); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(862); match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(863); match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(864); match(PRIVATE);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(865); match(STATIC);
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(866); match(FINAL);
				}
				break;
			case TRANSIENT:
				enterOuterAlt(_localctx, 7);
				{
				setState(867); match(TRANSIENT);
				}
				break;
			case VOLATILE:
				enterOuterAlt(_localctx, 8);
				{
				setState(868); match(VOLATILE);
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

	public static class VariableDeclaratorListContext extends ParserRuleContext {
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterVariableDeclaratorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitVariableDeclaratorList(this);
		}
	}

	public final VariableDeclaratorListContext variableDeclaratorList() throws RecognitionException {
		VariableDeclaratorListContext _localctx = new VariableDeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_variableDeclaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(871); variableDeclarator();
			setState(876);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(872); match(COMMA);
				setState(873); variableDeclarator();
				}
				}
				setState(878);
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
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitVariableDeclarator(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_variableDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(879); variableDeclaratorId();
			setState(882);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(880); match(ASSIGN);
				setState(881); variableInitializer();
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

	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public DimsContext dims() {
			return getRuleContext(DimsContext.class,0);
		}
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitVariableDeclaratorId(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_variableDeclaratorId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(884); match(Identifier);
			setState(886);
			_la = _input.LA(1);
			if (_la==T__0 || _la==AT) {
				{
				setState(885); dims();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitVariableInitializer(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_variableInitializer);
		try {
			setState(890);
			switch (_input.LA(1)) {
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
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(888); expression();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(889); arrayInitializer();
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

	public static class UnannTypeContext extends ParserRuleContext {
		public UnannPrimitiveTypeContext unannPrimitiveType() {
			return getRuleContext(UnannPrimitiveTypeContext.class,0);
		}
		public UnannReferenceTypeContext unannReferenceType() {
			return getRuleContext(UnannReferenceTypeContext.class,0);
		}
		public UnannTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannType(this);
		}
	}

	public final UnannTypeContext unannType() throws RecognitionException {
		UnannTypeContext _localctx = new UnannTypeContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_unannType);
		try {
			setState(894);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(892); unannPrimitiveType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(893); unannReferenceType();
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

	public static class UnannPrimitiveTypeContext extends ParserRuleContext {
		public NumericTypeContext numericType() {
			return getRuleContext(NumericTypeContext.class,0);
		}
		public UnannPrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannPrimitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannPrimitiveType(this);
		}
	}

	public final UnannPrimitiveTypeContext unannPrimitiveType() throws RecognitionException {
		UnannPrimitiveTypeContext _localctx = new UnannPrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_unannPrimitiveType);
		try {
			setState(898);
			switch (_input.LA(1)) {
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(896); numericType();
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(897); match(BOOLEAN);
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

	public static class UnannReferenceTypeContext extends ParserRuleContext {
		public UnannArrayTypeContext unannArrayType() {
			return getRuleContext(UnannArrayTypeContext.class,0);
		}
		public UnannClassOrInterfaceTypeContext unannClassOrInterfaceType() {
			return getRuleContext(UnannClassOrInterfaceTypeContext.class,0);
		}
		public UnannTypeVariableContext unannTypeVariable() {
			return getRuleContext(UnannTypeVariableContext.class,0);
		}
		public UnannReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannReferenceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannReferenceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannReferenceType(this);
		}
	}

	public final UnannReferenceTypeContext unannReferenceType() throws RecognitionException {
		UnannReferenceTypeContext _localctx = new UnannReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_unannReferenceType);
		try {
			setState(903);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(900); unannClassOrInterfaceType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(901); unannTypeVariable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(902); unannArrayType();
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

	public static class UnannClassOrInterfaceTypeContext extends ParserRuleContext {
		public UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext unannInterfaceType_lfno_unannClassOrInterfaceType() {
			return getRuleContext(UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext.class,0);
		}
		public UnannClassType_lf_unannClassOrInterfaceTypeContext unannClassType_lf_unannClassOrInterfaceType(int i) {
			return getRuleContext(UnannClassType_lf_unannClassOrInterfaceTypeContext.class,i);
		}
		public List<UnannInterfaceType_lf_unannClassOrInterfaceTypeContext> unannInterfaceType_lf_unannClassOrInterfaceType() {
			return getRuleContexts(UnannInterfaceType_lf_unannClassOrInterfaceTypeContext.class);
		}
		public List<UnannClassType_lf_unannClassOrInterfaceTypeContext> unannClassType_lf_unannClassOrInterfaceType() {
			return getRuleContexts(UnannClassType_lf_unannClassOrInterfaceTypeContext.class);
		}
		public UnannClassType_lfno_unannClassOrInterfaceTypeContext unannClassType_lfno_unannClassOrInterfaceType() {
			return getRuleContext(UnannClassType_lfno_unannClassOrInterfaceTypeContext.class,0);
		}
		public UnannInterfaceType_lf_unannClassOrInterfaceTypeContext unannInterfaceType_lf_unannClassOrInterfaceType(int i) {
			return getRuleContext(UnannInterfaceType_lf_unannClassOrInterfaceTypeContext.class,i);
		}
		public UnannClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannClassOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannClassOrInterfaceType(this);
		}
	}

	public final UnannClassOrInterfaceTypeContext unannClassOrInterfaceType() throws RecognitionException {
		UnannClassOrInterfaceTypeContext _localctx = new UnannClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_unannClassOrInterfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(907);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(905); unannClassType_lfno_unannClassOrInterfaceType();
				}
				break;
			case 2:
				{
				setState(906); unannInterfaceType_lfno_unannClassOrInterfaceType();
				}
				break;
			}
			setState(913);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(911);
					switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
					case 1:
						{
						setState(909); unannClassType_lf_unannClassOrInterfaceType();
						}
						break;
					case 2:
						{
						setState(910); unannInterfaceType_lf_unannClassOrInterfaceType();
						}
						break;
					}
					} 
				}
				setState(915);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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

	public static class UnannClassTypeContext extends ParserRuleContext {
		public UnannClassOrInterfaceTypeContext unannClassOrInterfaceType() {
			return getRuleContext(UnannClassOrInterfaceTypeContext.class,0);
		}
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public UnannClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannClassType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannClassType(this);
		}
	}

	public final UnannClassTypeContext unannClassType() throws RecognitionException {
		UnannClassTypeContext _localctx = new UnannClassTypeContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_unannClassType);
		int _la;
		try {
			setState(926);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(916); match(Identifier);
				setState(918);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(917); typeArguments();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(920); unannClassOrInterfaceType();
				setState(921); match(DOT);
				setState(922); annotationIdentifier();
				setState(924);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(923); typeArguments();
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

	public static class UnannClassType_lf_unannClassOrInterfaceTypeContext extends ParserRuleContext {
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public UnannClassType_lf_unannClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannClassType_lf_unannClassOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannClassType_lf_unannClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannClassType_lf_unannClassOrInterfaceType(this);
		}
	}

	public final UnannClassType_lf_unannClassOrInterfaceTypeContext unannClassType_lf_unannClassOrInterfaceType() throws RecognitionException {
		UnannClassType_lf_unannClassOrInterfaceTypeContext _localctx = new UnannClassType_lf_unannClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_unannClassType_lf_unannClassOrInterfaceType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(928); match(DOT);
			setState(929); annotationIdentifier();
			setState(931);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(930); typeArguments();
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

	public static class UnannClassType_lfno_unannClassOrInterfaceTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public UnannClassType_lfno_unannClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannClassType_lfno_unannClassOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannClassType_lfno_unannClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannClassType_lfno_unannClassOrInterfaceType(this);
		}
	}

	public final UnannClassType_lfno_unannClassOrInterfaceTypeContext unannClassType_lfno_unannClassOrInterfaceType() throws RecognitionException {
		UnannClassType_lfno_unannClassOrInterfaceTypeContext _localctx = new UnannClassType_lfno_unannClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_unannClassType_lfno_unannClassOrInterfaceType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(933); match(Identifier);
			setState(935);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(934); typeArguments();
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

	public static class UnannInterfaceTypeContext extends ParserRuleContext {
		public UnannClassTypeContext unannClassType() {
			return getRuleContext(UnannClassTypeContext.class,0);
		}
		public UnannInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannInterfaceType(this);
		}
	}

	public final UnannInterfaceTypeContext unannInterfaceType() throws RecognitionException {
		UnannInterfaceTypeContext _localctx = new UnannInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_unannInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937); unannClassType();
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

	public static class UnannInterfaceType_lf_unannClassOrInterfaceTypeContext extends ParserRuleContext {
		public UnannClassType_lf_unannClassOrInterfaceTypeContext unannClassType_lf_unannClassOrInterfaceType() {
			return getRuleContext(UnannClassType_lf_unannClassOrInterfaceTypeContext.class,0);
		}
		public UnannInterfaceType_lf_unannClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannInterfaceType_lf_unannClassOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannInterfaceType_lf_unannClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannInterfaceType_lf_unannClassOrInterfaceType(this);
		}
	}

	public final UnannInterfaceType_lf_unannClassOrInterfaceTypeContext unannInterfaceType_lf_unannClassOrInterfaceType() throws RecognitionException {
		UnannInterfaceType_lf_unannClassOrInterfaceTypeContext _localctx = new UnannInterfaceType_lf_unannClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_unannInterfaceType_lf_unannClassOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(939); unannClassType_lf_unannClassOrInterfaceType();
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

	public static class UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext extends ParserRuleContext {
		public UnannClassType_lfno_unannClassOrInterfaceTypeContext unannClassType_lfno_unannClassOrInterfaceType() {
			return getRuleContext(UnannClassType_lfno_unannClassOrInterfaceTypeContext.class,0);
		}
		public UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannInterfaceType_lfno_unannClassOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannInterfaceType_lfno_unannClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannInterfaceType_lfno_unannClassOrInterfaceType(this);
		}
	}

	public final UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext unannInterfaceType_lfno_unannClassOrInterfaceType() throws RecognitionException {
		UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext _localctx = new UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_unannInterfaceType_lfno_unannClassOrInterfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(941); unannClassType_lfno_unannClassOrInterfaceType();
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

	public static class UnannTypeVariableContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public UnannTypeVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannTypeVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannTypeVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannTypeVariable(this);
		}
	}

	public final UnannTypeVariableContext unannTypeVariable() throws RecognitionException {
		UnannTypeVariableContext _localctx = new UnannTypeVariableContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_unannTypeVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(943); match(Identifier);
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

	public static class UnannArrayTypeContext extends ParserRuleContext {
		public UnannPrimitiveTypeContext unannPrimitiveType() {
			return getRuleContext(UnannPrimitiveTypeContext.class,0);
		}
		public UnannClassOrInterfaceTypeContext unannClassOrInterfaceType() {
			return getRuleContext(UnannClassOrInterfaceTypeContext.class,0);
		}
		public UnannTypeVariableContext unannTypeVariable() {
			return getRuleContext(UnannTypeVariableContext.class,0);
		}
		public DimsContext dims() {
			return getRuleContext(DimsContext.class,0);
		}
		public UnannArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unannArrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnannArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnannArrayType(this);
		}
	}

	public final UnannArrayTypeContext unannArrayType() throws RecognitionException {
		UnannArrayTypeContext _localctx = new UnannArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_unannArrayType);
		try {
			setState(954);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(945); unannPrimitiveType();
				setState(946); dims();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(948); unannClassOrInterfaceType();
				setState(949); dims();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(951); unannTypeVariable();
				setState(952); dims();
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
		public MethodHeaderContext methodHeader() {
			return getRuleContext(MethodHeaderContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public MethodModifiersContext methodModifiers() {
			return getRuleContext(MethodModifiersContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_methodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(956); methodModifiers();
			setState(957); methodHeader();
			setState(958); methodBody();
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

	public static class MethodModifiersContext extends ParserRuleContext {
		public List<MethodModifierContext> methodModifier() {
			return getRuleContexts(MethodModifierContext.class);
		}
		public MethodModifierContext methodModifier(int i) {
			return getRuleContext(MethodModifierContext.class,i);
		}
		public MethodModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodModifiers(this);
		}
	}

	public final MethodModifiersContext methodModifiers() throws RecognitionException {
		MethodModifiersContext _localctx = new MethodModifiersContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_methodModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(963);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED))) != 0) || _la==AT) {
				{
				{
				setState(960); methodModifier();
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

	public static class MethodModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public MethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodModifier(this);
		}
	}

	public final MethodModifierContext methodModifier() throws RecognitionException {
		MethodModifierContext _localctx = new MethodModifierContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_methodModifier);
		try {
			setState(976);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(966); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(967); match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(968); match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(969); match(PRIVATE);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 5);
				{
				setState(970); match(ABSTRACT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 6);
				{
				setState(971); match(STATIC);
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(972); match(FINAL);
				}
				break;
			case SYNCHRONIZED:
				enterOuterAlt(_localctx, 8);
				{
				setState(973); match(SYNCHRONIZED);
				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 9);
				{
				setState(974); match(NATIVE);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 10);
				{
				setState(975); match(STRICTFP);
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

	public static class MethodHeaderContext extends ParserRuleContext {
		public Throws_Context throws_() {
			return getRuleContext(Throws_Context.class,0);
		}
		public ResultContext result() {
			return getRuleContext(ResultContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public MethodDeclaratorContext methodDeclarator() {
			return getRuleContext(MethodDeclaratorContext.class,0);
		}
		public MethodHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodHeader(this);
		}
	}

	public final MethodHeaderContext methodHeader() throws RecognitionException {
		MethodHeaderContext _localctx = new MethodHeaderContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_methodHeader);
		int _la;
		try {
			setState(995);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case VOID:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(978); result();
				setState(979); methodDeclarator();
				setState(981);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(980); throws_();
					}
				}

				}
				break;
			case LT:
				enterOuterAlt(_localctx, 2);
				{
				setState(983); typeParameters();
				setState(987);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(984); annotation();
					}
					}
					setState(989);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(990); result();
				setState(991); methodDeclarator();
				setState(993);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(992); throws_();
					}
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

	public static class ResultContext extends ParserRuleContext {
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public ResultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterResult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitResult(this);
		}
	}

	public final ResultContext result() throws RecognitionException {
		ResultContext _localctx = new ResultContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_result);
		try {
			setState(999);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(997); unannType();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(998); match(VOID);
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

	public static class MethodDeclaratorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public DimsContext dims() {
			return getRuleContext(DimsContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public MethodDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodDeclarator(this);
		}
	}

	public final MethodDeclaratorContext methodDeclarator() throws RecognitionException {
		MethodDeclaratorContext _localctx = new MethodDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_methodDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1001); match(Identifier);
			setState(1002); match(LPAREN);
			setState(1004);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0) || _la==Identifier || _la==AT) {
				{
				setState(1003); formalParameterList();
				}
			}

			setState(1006); match(RPAREN);
			setState(1008);
			_la = _input.LA(1);
			if (_la==T__0 || _la==AT) {
				{
				setState(1007); dims();
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

	public static class FormalParameterListContext extends ParserRuleContext {
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
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
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_formalParameterList);
		try {
			setState(1015);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1010); formalParameters();
				setState(1011); match(COMMA);
				setState(1012); lastFormalParameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1014); lastFormalParameter();
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

	public static class FormalParametersContext extends ParserRuleContext {
		public ReceiverParameterContext receiverParameter() {
			return getRuleContext(ReceiverParameterContext.class,0);
		}
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_formalParameters);
		try {
			int _alt;
			setState(1033);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1017); formalParameter();
				setState(1022);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1018); match(COMMA);
						setState(1019); formalParameter();
						}
						} 
					}
					setState(1024);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1025); receiverParameter();
				setState(1030);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1026); match(COMMA);
						setState(1027); formalParameter();
						}
						} 
					}
					setState(1032);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
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

	public static class FormalParameterContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_formalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1038);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1035); variableModifier();
				}
				}
				setState(1040);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1041); unannType();
			setState(1042); variableDeclaratorId();
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
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public VariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitVariableModifier(this);
		}
	}

	public final VariableModifierContext variableModifier() throws RecognitionException {
		VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_variableModifier);
		try {
			setState(1046);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1044); annotation();
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1045); match(FINAL);
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

	public static class LastFormalParameterContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public FormalParameterContext formalParameter() {
			return getRuleContext(FormalParameterContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public LastFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLastFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLastFormalParameter(this);
		}
	}

	public final LastFormalParameterContext lastFormalParameter() throws RecognitionException {
		LastFormalParameterContext _localctx = new LastFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_lastFormalParameter);
		int _la;
		try {
			setState(1065);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1051);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FINAL || _la==AT) {
					{
					{
					setState(1048); variableModifier();
					}
					}
					setState(1053);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1054); unannType();
				setState(1058);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AT) {
					{
					{
					setState(1055); annotation();
					}
					}
					setState(1060);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1061); match(ELLIPSIS);
				setState(1062); variableDeclaratorId();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1064); formalParameter();
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

	public static class ReceiverParameterContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public ReceiverParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterReceiverParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitReceiverParameter(this);
		}
	}

	public final ReceiverParameterContext receiverParameter() throws RecognitionException {
		ReceiverParameterContext _localctx = new ReceiverParameterContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_receiverParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1070);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1067); annotation();
				}
				}
				setState(1072);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1073); unannType();
			setState(1076);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1074); match(Identifier);
				setState(1075); match(DOT);
				}
			}

			setState(1078); match(THIS);
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

	public static class Throws_Context extends ParserRuleContext {
		public ExceptionTypeListContext exceptionTypeList() {
			return getRuleContext(ExceptionTypeListContext.class,0);
		}
		public Throws_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throws_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterThrows_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitThrows_(this);
		}
	}

	public final Throws_Context throws_() throws RecognitionException {
		Throws_Context _localctx = new Throws_Context(_ctx, getState());
		enterRule(_localctx, 168, RULE_throws_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1080); match(THROWS);
			setState(1081); exceptionTypeList();
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

	public static class ExceptionTypeListContext extends ParserRuleContext {
		public ExceptionTypeContext exceptionType(int i) {
			return getRuleContext(ExceptionTypeContext.class,i);
		}
		public List<ExceptionTypeContext> exceptionType() {
			return getRuleContexts(ExceptionTypeContext.class);
		}
		public ExceptionTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exceptionTypeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExceptionTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExceptionTypeList(this);
		}
	}

	public final ExceptionTypeListContext exceptionTypeList() throws RecognitionException {
		ExceptionTypeListContext _localctx = new ExceptionTypeListContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_exceptionTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1083); exceptionType();
			setState(1088);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1084); match(COMMA);
				setState(1085); exceptionType();
				}
				}
				setState(1090);
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

	public static class ExceptionTypeContext extends ParserRuleContext {
		public TypeVariableContext typeVariable() {
			return getRuleContext(TypeVariableContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public ExceptionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exceptionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExceptionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExceptionType(this);
		}
	}

	public final ExceptionTypeContext exceptionType() throws RecognitionException {
		ExceptionTypeContext _localctx = new ExceptionTypeContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_exceptionType);
		try {
			setState(1093);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1091); classType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1092); typeVariable();
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

	public static class MethodBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodBody(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_methodBody);
		try {
			setState(1097);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1095); block();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(1096); match(SEMI);
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

	public static class InstanceInitializerContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public InstanceInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instanceInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInstanceInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInstanceInitializer(this);
		}
	}

	public final InstanceInitializerContext instanceInitializer() throws RecognitionException {
		InstanceInitializerContext _localctx = new InstanceInitializerContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_instanceInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1099); block();
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

	public static class StaticInitializerContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StaticInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStaticInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStaticInitializer(this);
		}
	}

	public final StaticInitializerContext staticInitializer() throws RecognitionException {
		StaticInitializerContext _localctx = new StaticInitializerContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_staticInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1101); match(STATIC);
			setState(1102); block();
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
		public Throws_Context throws_() {
			return getRuleContext(Throws_Context.class,0);
		}
		public ConstructorDeclaratorContext constructorDeclarator() {
			return getRuleContext(ConstructorDeclaratorContext.class,0);
		}
		public ConstructorBodyContext constructorBody() {
			return getRuleContext(ConstructorBodyContext.class,0);
		}
		public ConstructorModifiersContext constructorModifiers() {
			return getRuleContext(ConstructorModifiersContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstructorDeclaration(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1104); constructorModifiers();
			setState(1105); constructorDeclarator();
			setState(1107);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1106); throws_();
				}
			}

			setState(1109); constructorBody();
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

	public static class ConstructorModifiersContext extends ParserRuleContext {
		public List<ConstructorModifierContext> constructorModifier() {
			return getRuleContexts(ConstructorModifierContext.class);
		}
		public ConstructorModifierContext constructorModifier(int i) {
			return getRuleContext(ConstructorModifierContext.class,i);
		}
		public ConstructorModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstructorModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstructorModifiers(this);
		}
	}

	public final ConstructorModifiersContext constructorModifiers() throws RecognitionException {
		ConstructorModifiersContext _localctx = new ConstructorModifiersContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_constructorModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC))) != 0) || _la==AT) {
				{
				{
				setState(1111); constructorModifier();
				}
				}
				setState(1116);
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

	public static class ConstructorModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ConstructorModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstructorModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstructorModifier(this);
		}
	}

	public final ConstructorModifierContext constructorModifier() throws RecognitionException {
		ConstructorModifierContext _localctx = new ConstructorModifierContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_constructorModifier);
		try {
			setState(1121);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1117); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1118); match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(1119); match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(1120); match(PRIVATE);
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

	public static class ConstructorDeclaratorContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public SimpleTypeNameContext simpleTypeName() {
			return getRuleContext(SimpleTypeNameContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public ConstructorDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstructorDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstructorDeclarator(this);
		}
	}

	public final ConstructorDeclaratorContext constructorDeclarator() throws RecognitionException {
		ConstructorDeclaratorContext _localctx = new ConstructorDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_constructorDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1124);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1123); typeParameters();
				}
			}

			setState(1126); simpleTypeName();
			setState(1127); match(LPAREN);
			setState(1129);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0) || _la==Identifier || _la==AT) {
				{
				setState(1128); formalParameterList();
				}
			}

			setState(1131); match(RPAREN);
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

	public static class SimpleTypeNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public SimpleTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSimpleTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSimpleTypeName(this);
		}
	}

	public final SimpleTypeNameContext simpleTypeName() throws RecognitionException {
		SimpleTypeNameContext _localctx = new SimpleTypeNameContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_simpleTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1133); match(Identifier);
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

	public static class ConstructorBodyContext extends ParserRuleContext {
		public ExplicitConstructorInvocationContext explicitConstructorInvocation() {
			return getRuleContext(ExplicitConstructorInvocationContext.class,0);
		}
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public ConstructorBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstructorBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstructorBody(this);
		}
	}

	public final ConstructorBodyContext constructorBody() throws RecognitionException {
		ConstructorBodyContext _localctx = new ConstructorBodyContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_constructorBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1135); match(LBRACE);
			setState(1137);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				{
				setState(1136); explicitConstructorInvocation();
				}
				break;
			}
			setState(1140);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				setState(1139); blockStatements();
				}
			}

			setState(1142); match(RBRACE);
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

	public static class ExplicitConstructorInvocationContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public ExplicitConstructorInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitConstructorInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExplicitConstructorInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExplicitConstructorInvocation(this);
		}
	}

	public final ExplicitConstructorInvocationContext explicitConstructorInvocation() throws RecognitionException {
		ExplicitConstructorInvocationContext _localctx = new ExplicitConstructorInvocationContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_explicitConstructorInvocation);
		int _la;
		try {
			setState(1190);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1145);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1144); typeArguments();
					}
				}

				setState(1147); match(THIS);
				setState(1148); match(LPAREN);
				setState(1150);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(1149); argumentList();
					}
				}

				setState(1152); match(RPAREN);
				setState(1153); match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1155);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1154); typeArguments();
					}
				}

				setState(1157); match(SUPER);
				setState(1158); match(LPAREN);
				setState(1160);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(1159); argumentList();
					}
				}

				setState(1162); match(RPAREN);
				setState(1163); match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1164); expressionName();
				setState(1165); match(DOT);
				setState(1167);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1166); typeArguments();
					}
				}

				setState(1169); match(SUPER);
				setState(1170); match(LPAREN);
				setState(1172);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(1171); argumentList();
					}
				}

				setState(1174); match(RPAREN);
				setState(1175); match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1177); primary();
				setState(1178); match(DOT);
				setState(1180);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1179); typeArguments();
					}
				}

				setState(1182); match(SUPER);
				setState(1183); match(LPAREN);
				setState(1185);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(1184); argumentList();
					}
				}

				setState(1187); match(RPAREN);
				setState(1188); match(SEMI);
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

	public static class EnumDeclarationContext extends ParserRuleContext {
		public ClassModifiersContext classModifiers() {
			return getRuleContext(ClassModifiersContext.class,0);
		}
		public EnumBodyContext enumBody() {
			return getRuleContext(EnumBodyContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public SuperinterfacesContext superinterfaces() {
			return getRuleContext(SuperinterfacesContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumDeclaration(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1192); classModifiers();
			setState(1193); match(ENUM);
			setState(1194); match(Identifier);
			setState(1196);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1195); superinterfaces();
				}
			}

			setState(1198); enumBody();
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

	public static class EnumBodyContext extends ParserRuleContext {
		public EnumConstantListContext enumConstantList() {
			return getRuleContext(EnumConstantListContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Java8Parser.COMMA, 0); }
		public EnumBodyDeclarationsContext enumBodyDeclarations() {
			return getRuleContext(EnumBodyDeclarationsContext.class,0);
		}
		public EnumBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumBody(this);
		}
	}

	public final EnumBodyContext enumBody() throws RecognitionException {
		EnumBodyContext _localctx = new EnumBodyContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_enumBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1200); match(LBRACE);
			setState(1202);
			_la = _input.LA(1);
			if (_la==Identifier || _la==AT) {
				{
				setState(1201); enumConstantList();
				}
			}

			setState(1205);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1204); match(COMMA);
				}
			}

			setState(1208);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1207); enumBodyDeclarations();
				}
			}

			setState(1210); match(RBRACE);
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

	public static class EnumConstantListContext extends ParserRuleContext {
		public List<EnumConstantContext> enumConstant() {
			return getRuleContexts(EnumConstantContext.class);
		}
		public EnumConstantContext enumConstant(int i) {
			return getRuleContext(EnumConstantContext.class,i);
		}
		public EnumConstantListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstantList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumConstantList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumConstantList(this);
		}
	}

	public final EnumConstantListContext enumConstantList() throws RecognitionException {
		EnumConstantListContext _localctx = new EnumConstantListContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_enumConstantList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1212); enumConstant();
			setState(1217);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,109,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1213); match(COMMA);
					setState(1214); enumConstant();
					}
					} 
				}
				setState(1219);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,109,_ctx);
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
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public EnumConstantModifierContext enumConstantModifier(int i) {
			return getRuleContext(EnumConstantModifierContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public List<EnumConstantModifierContext> enumConstantModifier() {
			return getRuleContexts(EnumConstantModifierContext.class);
		}
		public EnumConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumConstant(this);
		}
	}

	public final EnumConstantContext enumConstant() throws RecognitionException {
		EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_enumConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1220); enumConstantModifier();
				}
				}
				setState(1225);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1226); match(Identifier);
			setState(1232);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(1227); match(LPAREN);
				setState(1229);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(1228); argumentList();
					}
				}

				setState(1231); match(RPAREN);
				}
			}

			setState(1235);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(1234); classBody();
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

	public static class EnumConstantModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public EnumConstantModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstantModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumConstantModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumConstantModifier(this);
		}
	}

	public final EnumConstantModifierContext enumConstantModifier() throws RecognitionException {
		EnumConstantModifierContext _localctx = new EnumConstantModifierContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_enumConstantModifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1237); annotation();
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
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public EnumBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumBodyDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumBodyDeclarations(this);
		}
	}

	public final EnumBodyDeclarationsContext enumBodyDeclarations() throws RecognitionException {
		EnumBodyDeclarationsContext _localctx = new EnumBodyDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_enumBodyDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1239); match(SEMI);
			setState(1243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				{
				setState(1240); classBodyDeclaration();
				}
				}
				setState(1245);
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
		public NormalInterfaceDeclarationContext normalInterfaceDeclaration() {
			return getRuleContext(NormalInterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceDeclaration(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_interfaceDeclaration);
		try {
			setState(1248);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1246); normalInterfaceDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1247); annotationTypeDeclaration();
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

	public static class NormalInterfaceDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ExtendsInterfacesContext extendsInterfaces() {
			return getRuleContext(ExtendsInterfacesContext.class,0);
		}
		public InterfaceModifierContext interfaceModifier(int i) {
			return getRuleContext(InterfaceModifierContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public List<InterfaceModifierContext> interfaceModifier() {
			return getRuleContexts(InterfaceModifierContext.class);
		}
		public NormalInterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalInterfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterNormalInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitNormalInterfaceDeclaration(this);
		}
	}

	public final NormalInterfaceDeclarationContext normalInterfaceDeclaration() throws RecognitionException {
		NormalInterfaceDeclarationContext _localctx = new NormalInterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_normalInterfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
				{
				{
				setState(1250); interfaceModifier();
				}
				}
				setState(1255);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1256); match(INTERFACE);
			setState(1257); match(Identifier);
			setState(1259);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1258); typeParameters();
				}
			}

			setState(1262);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1261); extendsInterfaces();
				}
			}

			setState(1264); interfaceBody();
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

	public static class InterfaceModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public InterfaceModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceModifier(this);
		}
	}

	public final InterfaceModifierContext interfaceModifier() throws RecognitionException {
		InterfaceModifierContext _localctx = new InterfaceModifierContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_interfaceModifier);
		try {
			setState(1273);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1266); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1267); match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(1268); match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(1269); match(PRIVATE);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 5);
				{
				setState(1270); match(ABSTRACT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 6);
				{
				setState(1271); match(STATIC);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 7);
				{
				setState(1272); match(STRICTFP);
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

	public static class ExtendsInterfacesContext extends ParserRuleContext {
		public InterfaceTypeListContext interfaceTypeList() {
			return getRuleContext(InterfaceTypeListContext.class,0);
		}
		public ExtendsInterfacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendsInterfaces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExtendsInterfaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExtendsInterfaces(this);
		}
	}

	public final ExtendsInterfacesContext extendsInterfaces() throws RecognitionException {
		ExtendsInterfacesContext _localctx = new ExtendsInterfacesContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_extendsInterfaces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1275); match(EXTENDS);
			setState(1276); interfaceTypeList();
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
		public InterfaceMemberDeclarationContext interfaceMemberDeclaration(int i) {
			return getRuleContext(InterfaceMemberDeclarationContext.class,i);
		}
		public List<InterfaceMemberDeclarationContext> interfaceMemberDeclaration() {
			return getRuleContexts(InterfaceMemberDeclarationContext.class);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceBody(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1278); match(LBRACE);
			setState(1282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DEFAULT) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << VOID))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (LT - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				{
				setState(1279); interfaceMemberDeclaration();
				}
				}
				setState(1284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1285); match(RBRACE);
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
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public ConstantDeclarationContext constantDeclaration() {
			return getRuleContext(ConstantDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceMemberDeclaration(this);
		}
	}

	public final InterfaceMemberDeclarationContext interfaceMemberDeclaration() throws RecognitionException {
		InterfaceMemberDeclarationContext _localctx = new InterfaceMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_interfaceMemberDeclaration);
		try {
			setState(1292);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1287); constantDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1288); interfaceMethodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1289); classDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1290); interfaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1291); match(SEMI);
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

	public static class ConstantDeclarationContext extends ParserRuleContext {
		public List<ConstantModifierContext> constantModifier() {
			return getRuleContexts(ConstantModifierContext.class);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableDeclaratorListContext variableDeclaratorList() {
			return getRuleContext(VariableDeclaratorListContext.class,0);
		}
		public ConstantModifierContext constantModifier(int i) {
			return getRuleContext(ConstantModifierContext.class,i);
		}
		public ConstantDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstantDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstantDeclaration(this);
		}
	}

	public final ConstantDeclarationContext constantDeclaration() throws RecognitionException {
		ConstantDeclarationContext _localctx = new ConstantDeclarationContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_constantDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FINAL) | (1L << PUBLIC) | (1L << STATIC))) != 0) || _la==AT) {
				{
				{
				setState(1294); constantModifier();
				}
				}
				setState(1299);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1300); unannType();
			setState(1301); variableDeclaratorList();
			setState(1302); match(SEMI);
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

	public static class ConstantModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ConstantModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstantModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstantModifier(this);
		}
	}

	public final ConstantModifierContext constantModifier() throws RecognitionException {
		ConstantModifierContext _localctx = new ConstantModifierContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_constantModifier);
		try {
			setState(1308);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1304); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1305); match(PUBLIC);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 3);
				{
				setState(1306); match(STATIC);
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(1307); match(FINAL);
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

	public static class InterfaceMethodDeclarationContext extends ParserRuleContext {
		public List<InterfaceMethodModifierContext> interfaceMethodModifier() {
			return getRuleContexts(InterfaceMethodModifierContext.class);
		}
		public MethodHeaderContext methodHeader() {
			return getRuleContext(MethodHeaderContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
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
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceMethodDeclaration(this);
		}
	}

	public final InterfaceMethodDeclarationContext interfaceMethodDeclaration() throws RecognitionException {
		InterfaceMethodDeclarationContext _localctx = new InterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_interfaceMethodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << DEFAULT) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
				{
				{
				setState(1310); interfaceMethodModifier();
				}
				}
				setState(1315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1316); methodHeader();
			setState(1317); methodBody();
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
		public InterfaceMethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInterfaceMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInterfaceMethodModifier(this);
		}
	}

	public final InterfaceMethodModifierContext interfaceMethodModifier() throws RecognitionException {
		InterfaceMethodModifierContext _localctx = new InterfaceMethodModifierContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_interfaceMethodModifier);
		try {
			setState(1325);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1319); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1320); match(PUBLIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1321); match(ABSTRACT);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 4);
				{
				setState(1322); match(DEFAULT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(1323); match(STATIC);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 6);
				{
				setState(1324); match(STRICTFP);
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

	public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
		public AnnotationTypeBodyContext annotationTypeBody() {
			return getRuleContext(AnnotationTypeBodyContext.class,0);
		}
		public InterfaceModifierContext interfaceModifier(int i) {
			return getRuleContext(InterfaceModifierContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public List<InterfaceModifierContext> interfaceModifier() {
			return getRuleContexts(InterfaceModifierContext.class);
		}
		public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationTypeDeclaration(this);
		}
	}

	public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
		AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_annotationTypeDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1330);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,126,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1327); interfaceModifier();
					}
					} 
				}
				setState(1332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,126,_ctx);
			}
			setState(1333); match(AT);
			setState(1334); match(INTERFACE);
			setState(1335); match(Identifier);
			setState(1336); annotationTypeBody();
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
		public List<AnnotationTypeMemberDeclarationContext> annotationTypeMemberDeclaration() {
			return getRuleContexts(AnnotationTypeMemberDeclarationContext.class);
		}
		public AnnotationTypeMemberDeclarationContext annotationTypeMemberDeclaration(int i) {
			return getRuleContext(AnnotationTypeMemberDeclarationContext.class,i);
		}
		public AnnotationTypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationTypeBody(this);
		}
	}

	public final AnnotationTypeBodyContext annotationTypeBody() throws RecognitionException {
		AnnotationTypeBodyContext _localctx = new AnnotationTypeBodyContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_annotationTypeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1338); match(LBRACE);
			setState(1342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				{
				setState(1339); annotationTypeMemberDeclaration();
				}
				}
				setState(1344);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1345); match(RBRACE);
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

	public static class AnnotationTypeMemberDeclarationContext extends ParserRuleContext {
		public ConstantDeclarationContext constantDeclaration() {
			return getRuleContext(ConstantDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() {
			return getRuleContext(AnnotationTypeElementDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public AnnotationTypeMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationTypeMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationTypeMemberDeclaration(this);
		}
	}

	public final AnnotationTypeMemberDeclarationContext annotationTypeMemberDeclaration() throws RecognitionException {
		AnnotationTypeMemberDeclarationContext _localctx = new AnnotationTypeMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_annotationTypeMemberDeclaration);
		try {
			setState(1352);
			switch ( getInterpreter().adaptivePredict(_input,128,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1347); annotationTypeElementDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1348); constantDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1349); classDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1350); interfaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1351); match(SEMI);
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

	public static class AnnotationTypeElementDeclarationContext extends ParserRuleContext {
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public List<AnnotationTypeElementModifierContext> annotationTypeElementModifier() {
			return getRuleContexts(AnnotationTypeElementModifierContext.class);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public AnnotationTypeElementModifierContext annotationTypeElementModifier(int i) {
			return getRuleContext(AnnotationTypeElementModifierContext.class,i);
		}
		public DimsContext dims() {
			return getRuleContext(DimsContext.class,0);
		}
		public AnnotationTypeElementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationTypeElementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationTypeElementDeclaration(this);
		}
	}

	public final AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() throws RecognitionException {
		AnnotationTypeElementDeclarationContext _localctx = new AnnotationTypeElementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_annotationTypeElementDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ABSTRACT || _la==PUBLIC || _la==AT) {
				{
				{
				setState(1354); annotationTypeElementModifier();
				}
				}
				setState(1359);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1360); unannType();
			setState(1361); match(Identifier);
			setState(1362); match(LPAREN);
			setState(1363); match(RPAREN);
			setState(1365);
			_la = _input.LA(1);
			if (_la==T__0 || _la==AT) {
				{
				setState(1364); dims();
				}
			}

			setState(1368);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1367); defaultValue();
				}
			}

			setState(1370); match(SEMI);
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

	public static class AnnotationTypeElementModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public AnnotationTypeElementModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationTypeElementModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationTypeElementModifier(this);
		}
	}

	public final AnnotationTypeElementModifierContext annotationTypeElementModifier() throws RecognitionException {
		AnnotationTypeElementModifierContext _localctx = new AnnotationTypeElementModifierContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_annotationTypeElementModifier);
		try {
			setState(1375);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1372); annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1373); match(PUBLIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1374); match(ABSTRACT);
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

	public static class DefaultValueContext extends ParserRuleContext {
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1377); match(DEFAULT);
			setState(1378); elementValue();
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
		public MarkerAnnotationContext markerAnnotation() {
			return getRuleContext(MarkerAnnotationContext.class,0);
		}
		public NormalAnnotationContext normalAnnotation() {
			return getRuleContext(NormalAnnotationContext.class,0);
		}
		public SingleElementAnnotationContext singleElementAnnotation() {
			return getRuleContext(SingleElementAnnotationContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_annotation);
		try {
			setState(1383);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1380); normalAnnotation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1381); markerAnnotation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1382); singleElementAnnotation();
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

	public static class AnnotationIdentifierContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationIdentifier(this);
		}
	}

	public final AnnotationIdentifierContext annotationIdentifier() throws RecognitionException {
		AnnotationIdentifierContext _localctx = new AnnotationIdentifierContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_annotationIdentifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1385); annotation();
				}
				}
				setState(1390);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1391); match(Identifier);
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

	public static class AnnotationDimContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public SquareBracketsContext squareBrackets() {
			return getRuleContext(SquareBracketsContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationDimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationDim; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAnnotationDim(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAnnotationDim(this);
		}
	}

	public final AnnotationDimContext annotationDim() throws RecognitionException {
		AnnotationDimContext _localctx = new AnnotationDimContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_annotationDim);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1393); annotation();
				}
				}
				setState(1398);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1399); squareBrackets();
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

	public static class NormalAnnotationContext extends ParserRuleContext {
		public ElementValuePairListContext elementValuePairList() {
			return getRuleContext(ElementValuePairListContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public NormalAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterNormalAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitNormalAnnotation(this);
		}
	}

	public final NormalAnnotationContext normalAnnotation() throws RecognitionException {
		NormalAnnotationContext _localctx = new NormalAnnotationContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_normalAnnotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1401); match(AT);
			setState(1402); typeName();
			setState(1403); match(LPAREN);
			setState(1405);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1404); elementValuePairList();
				}
			}

			setState(1407); match(RPAREN);
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

	public static class ElementValuePairListContext extends ParserRuleContext {
		public List<ElementValuePairContext> elementValuePair() {
			return getRuleContexts(ElementValuePairContext.class);
		}
		public ElementValuePairContext elementValuePair(int i) {
			return getRuleContext(ElementValuePairContext.class,i);
		}
		public ElementValuePairListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePairList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterElementValuePairList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitElementValuePairList(this);
		}
	}

	public final ElementValuePairListContext elementValuePairList() throws RecognitionException {
		ElementValuePairListContext _localctx = new ElementValuePairListContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_elementValuePairList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1409); elementValuePair();
			setState(1414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1410); match(COMMA);
				setState(1411); elementValuePair();
				}
				}
				setState(1416);
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
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterElementValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitElementValuePair(this);
		}
	}

	public final ElementValuePairContext elementValuePair() throws RecognitionException {
		ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_elementValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1417); match(Identifier);
			setState(1418); match(ASSIGN);
			setState(1419); elementValue();
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
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ElementValueArrayInitializerContext elementValueArrayInitializer() {
			return getRuleContext(ElementValueArrayInitializerContext.class,0);
		}
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitElementValue(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_elementValue);
		try {
			setState(1424);
			switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1421); conditionalExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1422); elementValueArrayInitializer();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1423); annotation();
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
		public TerminalNode COMMA() { return getToken(Java8Parser.COMMA, 0); }
		public ElementValueListContext elementValueList() {
			return getRuleContext(ElementValueListContext.class,0);
		}
		public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueArrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterElementValueArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitElementValueArrayInitializer(this);
		}
	}

	public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
		ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_elementValueArrayInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1426); match(LBRACE);
			setState(1428);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(1427); elementValueList();
				}
			}

			setState(1431);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1430); match(COMMA);
				}
			}

			setState(1433); match(RBRACE);
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

	public static class ElementValueListContext extends ParserRuleContext {
		public ElementValueContext elementValue(int i) {
			return getRuleContext(ElementValueContext.class,i);
		}
		public List<ElementValueContext> elementValue() {
			return getRuleContexts(ElementValueContext.class);
		}
		public ElementValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterElementValueList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitElementValueList(this);
		}
	}

	public final ElementValueListContext elementValueList() throws RecognitionException {
		ElementValueListContext _localctx = new ElementValueListContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_elementValueList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1435); elementValue();
			setState(1440);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,141,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1436); match(COMMA);
					setState(1437); elementValue();
					}
					} 
				}
				setState(1442);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,141,_ctx);
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

	public static class MarkerAnnotationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public MarkerAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_markerAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMarkerAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMarkerAnnotation(this);
		}
	}

	public final MarkerAnnotationContext markerAnnotation() throws RecognitionException {
		MarkerAnnotationContext _localctx = new MarkerAnnotationContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_markerAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1443); match(AT);
			setState(1444); typeName();
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

	public static class SingleElementAnnotationContext extends ParserRuleContext {
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public SingleElementAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleElementAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSingleElementAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSingleElementAnnotation(this);
		}
	}

	public final SingleElementAnnotationContext singleElementAnnotation() throws RecognitionException {
		SingleElementAnnotationContext _localctx = new SingleElementAnnotationContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_singleElementAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1446); match(AT);
			setState(1447); typeName();
			setState(1448); match(LPAREN);
			setState(1449); elementValue();
			setState(1450); match(RPAREN);
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
		public VariableInitializerListContext variableInitializerList() {
			return getRuleContext(VariableInitializerListContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(Java8Parser.COMMA, 0); }
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArrayInitializer(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_arrayInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1452); match(LBRACE);
			setState(1454);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(1453); variableInitializerList();
				}
			}

			setState(1457);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1456); match(COMMA);
				}
			}

			setState(1459); match(RBRACE);
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

	public static class VariableInitializerListContext extends ParserRuleContext {
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializerList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterVariableInitializerList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitVariableInitializerList(this);
		}
	}

	public final VariableInitializerListContext variableInitializerList() throws RecognitionException {
		VariableInitializerListContext _localctx = new VariableInitializerListContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_variableInitializerList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1461); variableInitializer();
			setState(1466);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,144,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1462); match(COMMA);
					setState(1463); variableInitializer();
					}
					} 
				}
				setState(1468);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,144,_ctx);
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

	public static class BlockContext extends ParserRuleContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1469); match(LBRACE);
			setState(1471);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				setState(1470); blockStatements();
				}
			}

			setState(1473); match(RBRACE);
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

	public static class BlockStatementsContext extends ParserRuleContext {
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterBlockStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitBlockStatements(this);
		}
	}

	public final BlockStatementsContext blockStatements() throws RecognitionException {
		BlockStatementsContext _localctx = new BlockStatementsContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_blockStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1475); blockStatement();
			setState(1479);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SEMI - 64)) | (1L << (INC - 64)) | (1L << (DEC - 64)) | (1L << (Identifier - 64)) | (1L << (AT - 64)))) != 0)) {
				{
				{
				setState(1476); blockStatement();
				}
				}
				setState(1481);
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

	public static class BlockStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public LocalVariableDeclarationStatementContext localVariableDeclarationStatement() {
			return getRuleContext(LocalVariableDeclarationStatementContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitBlockStatement(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_blockStatement);
		try {
			setState(1485);
			switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1482); localVariableDeclarationStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1483); classDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1484); statement();
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

	public static class LocalVariableDeclarationStatementContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public LocalVariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLocalVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLocalVariableDeclarationStatement(this);
		}
	}

	public final LocalVariableDeclarationStatementContext localVariableDeclarationStatement() throws RecognitionException {
		LocalVariableDeclarationStatementContext _localctx = new LocalVariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_localVariableDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1487); localVariableDeclaration();
			setState(1488); match(SEMI);
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
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public VariableDeclaratorListContext variableDeclaratorList() {
			return getRuleContext(VariableDeclaratorListContext.class,0);
		}
		public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLocalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLocalVariableDeclaration(this);
		}
	}

	public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
		LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_localVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1493);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1490); variableModifier();
				}
				}
				setState(1495);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1496); unannType();
			setState(1497); variableDeclaratorList();
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
		public StatementWithoutTrailingSubstatementContext statementWithoutTrailingSubstatement() {
			return getRuleContext(StatementWithoutTrailingSubstatementContext.class,0);
		}
		public LabeledStatementContext labeledStatement() {
			return getRuleContext(LabeledStatementContext.class,0);
		}
		public IfThenElseStatementContext ifThenElseStatement() {
			return getRuleContext(IfThenElseStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public IfThenStatementContext ifThenStatement() {
			return getRuleContext(IfThenStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_statement);
		try {
			setState(1505);
			switch ( getInterpreter().adaptivePredict(_input,149,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1499); statementWithoutTrailingSubstatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1500); labeledStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1501); ifThenStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1502); ifThenElseStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1503); whileStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1504); forStatement();
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

	public static class StatementNoShortIfContext extends ParserRuleContext {
		public WhileStatementNoShortIfContext whileStatementNoShortIf() {
			return getRuleContext(WhileStatementNoShortIfContext.class,0);
		}
		public ForStatementNoShortIfContext forStatementNoShortIf() {
			return getRuleContext(ForStatementNoShortIfContext.class,0);
		}
		public StatementWithoutTrailingSubstatementContext statementWithoutTrailingSubstatement() {
			return getRuleContext(StatementWithoutTrailingSubstatementContext.class,0);
		}
		public IfThenElseStatementNoShortIfContext ifThenElseStatementNoShortIf() {
			return getRuleContext(IfThenElseStatementNoShortIfContext.class,0);
		}
		public LabeledStatementNoShortIfContext labeledStatementNoShortIf() {
			return getRuleContext(LabeledStatementNoShortIfContext.class,0);
		}
		public StatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStatementNoShortIf(this);
		}
	}

	public final StatementNoShortIfContext statementNoShortIf() throws RecognitionException {
		StatementNoShortIfContext _localctx = new StatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_statementNoShortIf);
		try {
			setState(1512);
			switch ( getInterpreter().adaptivePredict(_input,150,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1507); statementWithoutTrailingSubstatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1508); labeledStatementNoShortIf();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1509); ifThenElseStatementNoShortIf();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1510); whileStatementNoShortIf();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1511); forStatementNoShortIf();
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

	public static class StatementWithoutTrailingSubstatementContext extends ParserRuleContext {
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public TryStatementContext tryStatement() {
			return getRuleContext(TryStatementContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public SynchronizedStatementContext synchronizedStatement() {
			return getRuleContext(SynchronizedStatementContext.class,0);
		}
		public DoStatementContext doStatement() {
			return getRuleContext(DoStatementContext.class,0);
		}
		public AssertStatementContext assertStatement() {
			return getRuleContext(AssertStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public StatementWithoutTrailingSubstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementWithoutTrailingSubstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStatementWithoutTrailingSubstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStatementWithoutTrailingSubstatement(this);
		}
	}

	public final StatementWithoutTrailingSubstatementContext statementWithoutTrailingSubstatement() throws RecognitionException {
		StatementWithoutTrailingSubstatementContext _localctx = new StatementWithoutTrailingSubstatementContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_statementWithoutTrailingSubstatement);
		try {
			setState(1526);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1514); block();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(1515); emptyStatement();
				}
				break;
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
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case INC:
			case DEC:
			case Identifier:
			case AT:
				enterOuterAlt(_localctx, 3);
				{
				setState(1516); expressionStatement();
				}
				break;
			case ASSERT:
				enterOuterAlt(_localctx, 4);
				{
				setState(1517); assertStatement();
				}
				break;
			case SWITCH:
				enterOuterAlt(_localctx, 5);
				{
				setState(1518); switchStatement();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 6);
				{
				setState(1519); doStatement();
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 7);
				{
				setState(1520); breakStatement();
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 8);
				{
				setState(1521); continueStatement();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 9);
				{
				setState(1522); returnStatement();
				}
				break;
			case SYNCHRONIZED:
				enterOuterAlt(_localctx, 10);
				{
				setState(1523); synchronizedStatement();
				}
				break;
			case THROW:
				enterOuterAlt(_localctx, 11);
				{
				setState(1524); throwStatement();
				}
				break;
			case TRY:
				enterOuterAlt(_localctx, 12);
				{
				setState(1525); tryStatement();
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

	public static class EmptyStatementContext extends ParserRuleContext {
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEmptyStatement(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1528); match(SEMI);
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

	public static class LabeledStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public LabeledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLabeledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLabeledStatement(this);
		}
	}

	public final LabeledStatementContext labeledStatement() throws RecognitionException {
		LabeledStatementContext _localctx = new LabeledStatementContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_labeledStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1530); match(Identifier);
			setState(1531); match(COLON);
			setState(1532); statement();
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

	public static class LabeledStatementNoShortIfContext extends ParserRuleContext {
		public StatementNoShortIfContext statementNoShortIf() {
			return getRuleContext(StatementNoShortIfContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public LabeledStatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledStatementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLabeledStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLabeledStatementNoShortIf(this);
		}
	}

	public final LabeledStatementNoShortIfContext labeledStatementNoShortIf() throws RecognitionException {
		LabeledStatementNoShortIfContext _localctx = new LabeledStatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_labeledStatementNoShortIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1534); match(Identifier);
			setState(1535); match(COLON);
			setState(1536); statementNoShortIf();
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

	public static class ExpressionStatementContext extends ParserRuleContext {
		public StatementExpressionContext statementExpression() {
			return getRuleContext(StatementExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 286, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1538); statementExpression();
			setState(1539); match(SEMI);
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

	public static class StatementExpressionContext extends ParserRuleContext {
		public PostIncrementExpressionContext postIncrementExpression() {
			return getRuleContext(PostIncrementExpressionContext.class,0);
		}
		public MethodInvocationContext methodInvocation() {
			return getRuleContext(MethodInvocationContext.class,0);
		}
		public PreIncrementExpressionContext preIncrementExpression() {
			return getRuleContext(PreIncrementExpressionContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public PreDecrementExpressionContext preDecrementExpression() {
			return getRuleContext(PreDecrementExpressionContext.class,0);
		}
		public ClassInstanceCreationExpressionContext classInstanceCreationExpression() {
			return getRuleContext(ClassInstanceCreationExpressionContext.class,0);
		}
		public PostDecrementExpressionContext postDecrementExpression() {
			return getRuleContext(PostDecrementExpressionContext.class,0);
		}
		public StatementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStatementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStatementExpression(this);
		}
	}

	public final StatementExpressionContext statementExpression() throws RecognitionException {
		StatementExpressionContext _localctx = new StatementExpressionContext(_ctx, getState());
		enterRule(_localctx, 288, RULE_statementExpression);
		try {
			setState(1548);
			switch ( getInterpreter().adaptivePredict(_input,152,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1541); assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1542); preIncrementExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1543); preDecrementExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1544); postIncrementExpression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1545); postDecrementExpression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1546); methodInvocation();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1547); classInstanceCreationExpression();
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

	public static class IfThenStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfThenStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThenStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterIfThenStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitIfThenStatement(this);
		}
	}

	public final IfThenStatementContext ifThenStatement() throws RecognitionException {
		IfThenStatementContext _localctx = new IfThenStatementContext(_ctx, getState());
		enterRule(_localctx, 290, RULE_ifThenStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1550); match(IF);
			setState(1551); match(LPAREN);
			setState(1552); expression();
			setState(1553); match(RPAREN);
			setState(1554); statement();
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

	public static class IfThenElseStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementNoShortIfContext statementNoShortIf() {
			return getRuleContext(StatementNoShortIfContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfThenElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThenElseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterIfThenElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitIfThenElseStatement(this);
		}
	}

	public final IfThenElseStatementContext ifThenElseStatement() throws RecognitionException {
		IfThenElseStatementContext _localctx = new IfThenElseStatementContext(_ctx, getState());
		enterRule(_localctx, 292, RULE_ifThenElseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1556); match(IF);
			setState(1557); match(LPAREN);
			setState(1558); expression();
			setState(1559); match(RPAREN);
			setState(1560); statementNoShortIf();
			setState(1561); match(ELSE);
			setState(1562); statement();
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

	public static class IfThenElseStatementNoShortIfContext extends ParserRuleContext {
		public StatementNoShortIfContext statementNoShortIf(int i) {
			return getRuleContext(StatementNoShortIfContext.class,i);
		}
		public List<StatementNoShortIfContext> statementNoShortIf() {
			return getRuleContexts(StatementNoShortIfContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfThenElseStatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThenElseStatementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterIfThenElseStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitIfThenElseStatementNoShortIf(this);
		}
	}

	public final IfThenElseStatementNoShortIfContext ifThenElseStatementNoShortIf() throws RecognitionException {
		IfThenElseStatementNoShortIfContext _localctx = new IfThenElseStatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 294, RULE_ifThenElseStatementNoShortIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1564); match(IF);
			setState(1565); match(LPAREN);
			setState(1566); expression();
			setState(1567); match(RPAREN);
			setState(1568); statementNoShortIf();
			setState(1569); match(ELSE);
			setState(1570); statementNoShortIf();
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

	public static class AssertStatementContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public AssertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAssertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAssertStatement(this);
		}
	}

	public final AssertStatementContext assertStatement() throws RecognitionException {
		AssertStatementContext _localctx = new AssertStatementContext(_ctx, getState());
		enterRule(_localctx, 296, RULE_assertStatement);
		try {
			setState(1582);
			switch ( getInterpreter().adaptivePredict(_input,153,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1572); match(ASSERT);
				setState(1573); expression();
				setState(1574); match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1576); match(ASSERT);
				setState(1577); expression();
				setState(1578); match(COLON);
				setState(1579); expression();
				setState(1580); match(SEMI);
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

	public static class SwitchStatementContext extends ParserRuleContext {
		public SwitchBlockContext switchBlock() {
			return getRuleContext(SwitchBlockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSwitchStatement(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 298, RULE_switchStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1584); match(SWITCH);
			setState(1585); match(LPAREN);
			setState(1586); expression();
			setState(1587); match(RPAREN);
			setState(1588); switchBlock();
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

	public static class SwitchBlockContext extends ParserRuleContext {
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSwitchBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSwitchBlock(this);
		}
	}

	public final SwitchBlockContext switchBlock() throws RecognitionException {
		SwitchBlockContext _localctx = new SwitchBlockContext(_ctx, getState());
		enterRule(_localctx, 300, RULE_switchBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1590); match(LBRACE);
			setState(1594);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,154,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1591); switchBlockStatementGroup();
					}
					} 
				}
				setState(1596);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,154,_ctx);
			}
			setState(1600);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE || _la==DEFAULT) {
				{
				{
				setState(1597); switchLabel();
				}
				}
				setState(1602);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1603); match(RBRACE);
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
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public SwitchLabelsContext switchLabels() {
			return getRuleContext(SwitchLabelsContext.class,0);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSwitchBlockStatementGroup(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 302, RULE_switchBlockStatementGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1605); switchLabels();
			setState(1606); blockStatements();
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

	public static class SwitchLabelsContext extends ParserRuleContext {
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabels; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSwitchLabels(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSwitchLabels(this);
		}
	}

	public final SwitchLabelsContext switchLabels() throws RecognitionException {
		SwitchLabelsContext _localctx = new SwitchLabelsContext(_ctx, getState());
		enterRule(_localctx, 304, RULE_switchLabels);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1608); switchLabel();
			setState(1612);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE || _la==DEFAULT) {
				{
				{
				setState(1609); switchLabel();
				}
				}
				setState(1614);
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

	public static class SwitchLabelContext extends ParserRuleContext {
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public EnumConstantNameContext enumConstantName() {
			return getRuleContext(EnumConstantNameContext.class,0);
		}
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSwitchLabel(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 306, RULE_switchLabel);
		try {
			setState(1625);
			switch ( getInterpreter().adaptivePredict(_input,157,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1615); match(CASE);
				setState(1616); constantExpression();
				setState(1617); match(COLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1619); match(CASE);
				setState(1620); enumConstantName();
				setState(1621); match(COLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1623); match(DEFAULT);
				setState(1624); match(COLON);
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

	public static class EnumConstantNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public EnumConstantNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstantName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnumConstantName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnumConstantName(this);
		}
	}

	public final EnumConstantNameContext enumConstantName() throws RecognitionException {
		EnumConstantNameContext _localctx = new EnumConstantNameContext(_ctx, getState());
		enterRule(_localctx, 308, RULE_enumConstantName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1627); match(Identifier);
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

	public static class WhileStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 310, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1629); match(WHILE);
			setState(1630); match(LPAREN);
			setState(1631); expression();
			setState(1632); match(RPAREN);
			setState(1633); statement();
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

	public static class WhileStatementNoShortIfContext extends ParserRuleContext {
		public StatementNoShortIfContext statementNoShortIf() {
			return getRuleContext(StatementNoShortIfContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhileStatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterWhileStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitWhileStatementNoShortIf(this);
		}
	}

	public final WhileStatementNoShortIfContext whileStatementNoShortIf() throws RecognitionException {
		WhileStatementNoShortIfContext _localctx = new WhileStatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 312, RULE_whileStatementNoShortIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1635); match(WHILE);
			setState(1636); match(LPAREN);
			setState(1637); expression();
			setState(1638); match(RPAREN);
			setState(1639); statementNoShortIf();
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

	public static class DoStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitDoStatement(this);
		}
	}

	public final DoStatementContext doStatement() throws RecognitionException {
		DoStatementContext _localctx = new DoStatementContext(_ctx, getState());
		enterRule(_localctx, 314, RULE_doStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1641); match(DO);
			setState(1642); statement();
			setState(1643); match(WHILE);
			setState(1644); match(LPAREN);
			setState(1645); expression();
			setState(1646); match(RPAREN);
			setState(1647); match(SEMI);
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

	public static class ForStatementContext extends ParserRuleContext {
		public EnhancedForStatementContext enhancedForStatement() {
			return getRuleContext(EnhancedForStatementContext.class,0);
		}
		public BasicForStatementContext basicForStatement() {
			return getRuleContext(BasicForStatementContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 316, RULE_forStatement);
		try {
			setState(1651);
			switch ( getInterpreter().adaptivePredict(_input,158,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1649); basicForStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1650); enhancedForStatement();
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

	public static class ForStatementNoShortIfContext extends ParserRuleContext {
		public BasicForStatementNoShortIfContext basicForStatementNoShortIf() {
			return getRuleContext(BasicForStatementNoShortIfContext.class,0);
		}
		public EnhancedForStatementNoShortIfContext enhancedForStatementNoShortIf() {
			return getRuleContext(EnhancedForStatementNoShortIfContext.class,0);
		}
		public ForStatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterForStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitForStatementNoShortIf(this);
		}
	}

	public final ForStatementNoShortIfContext forStatementNoShortIf() throws RecognitionException {
		ForStatementNoShortIfContext _localctx = new ForStatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 318, RULE_forStatementNoShortIf);
		try {
			setState(1655);
			switch ( getInterpreter().adaptivePredict(_input,159,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1653); basicForStatementNoShortIf();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1654); enhancedForStatementNoShortIf();
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

	public static class BasicForStatementContext extends ParserRuleContext {
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public BasicForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicForStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterBasicForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitBasicForStatement(this);
		}
	}

	public final BasicForStatementContext basicForStatement() throws RecognitionException {
		BasicForStatementContext _localctx = new BasicForStatementContext(_ctx, getState());
		enterRule(_localctx, 320, RULE_basicForStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1657); match(FOR);
			setState(1658); match(LPAREN);
			setState(1660);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (INC - 80)) | (1L << (DEC - 80)) | (1L << (Identifier - 80)) | (1L << (AT - 80)))) != 0)) {
				{
				setState(1659); forInit();
				}
			}

			setState(1662); match(SEMI);
			setState(1664);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(1663); expression();
				}
			}

			setState(1666); match(SEMI);
			setState(1668);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (INC - 80)) | (1L << (DEC - 80)) | (1L << (Identifier - 80)) | (1L << (AT - 80)))) != 0)) {
				{
				setState(1667); forUpdate();
				}
			}

			setState(1670); match(RPAREN);
			setState(1671); statement();
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

	public static class BasicForStatementNoShortIfContext extends ParserRuleContext {
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public StatementNoShortIfContext statementNoShortIf() {
			return getRuleContext(StatementNoShortIfContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public BasicForStatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicForStatementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterBasicForStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitBasicForStatementNoShortIf(this);
		}
	}

	public final BasicForStatementNoShortIfContext basicForStatementNoShortIf() throws RecognitionException {
		BasicForStatementNoShortIfContext _localctx = new BasicForStatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 322, RULE_basicForStatementNoShortIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1673); match(FOR);
			setState(1674); match(LPAREN);
			setState(1676);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (INC - 80)) | (1L << (DEC - 80)) | (1L << (Identifier - 80)) | (1L << (AT - 80)))) != 0)) {
				{
				setState(1675); forInit();
				}
			}

			setState(1678); match(SEMI);
			setState(1680);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(1679); expression();
				}
			}

			setState(1682); match(SEMI);
			setState(1684);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (INC - 80)) | (1L << (DEC - 80)) | (1L << (Identifier - 80)) | (1L << (AT - 80)))) != 0)) {
				{
				setState(1683); forUpdate();
				}
			}

			setState(1686); match(RPAREN);
			setState(1687); statementNoShortIf();
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
		public StatementExpressionListContext statementExpressionList() {
			return getRuleContext(StatementExpressionListContext.class,0);
		}
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitForInit(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 324, RULE_forInit);
		try {
			setState(1691);
			switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1689); statementExpressionList();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1690); localVariableDeclaration();
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

	public static class ForUpdateContext extends ParserRuleContext {
		public StatementExpressionListContext statementExpressionList() {
			return getRuleContext(StatementExpressionListContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitForUpdate(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 326, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1693); statementExpressionList();
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

	public static class StatementExpressionListContext extends ParserRuleContext {
		public List<StatementExpressionContext> statementExpression() {
			return getRuleContexts(StatementExpressionContext.class);
		}
		public StatementExpressionContext statementExpression(int i) {
			return getRuleContext(StatementExpressionContext.class,i);
		}
		public StatementExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementExpressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterStatementExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitStatementExpressionList(this);
		}
	}

	public final StatementExpressionListContext statementExpressionList() throws RecognitionException {
		StatementExpressionListContext _localctx = new StatementExpressionListContext(_ctx, getState());
		enterRule(_localctx, 328, RULE_statementExpressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1695); statementExpression();
			setState(1700);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1696); match(COMMA);
				setState(1697); statementExpression();
				}
				}
				setState(1702);
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

	public static class EnhancedForStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public EnhancedForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnhancedForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnhancedForStatement(this);
		}
	}

	public final EnhancedForStatementContext enhancedForStatement() throws RecognitionException {
		EnhancedForStatementContext _localctx = new EnhancedForStatementContext(_ctx, getState());
		enterRule(_localctx, 330, RULE_enhancedForStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1703); match(FOR);
			setState(1704); match(LPAREN);
			setState(1708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1705); variableModifier();
				}
				}
				setState(1710);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1711); unannType();
			setState(1712); variableDeclaratorId();
			setState(1713); match(COLON);
			setState(1714); expression();
			setState(1715); match(RPAREN);
			setState(1716); statement();
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

	public static class EnhancedForStatementNoShortIfContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public StatementNoShortIfContext statementNoShortIf() {
			return getRuleContext(StatementNoShortIfContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public EnhancedForStatementNoShortIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForStatementNoShortIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEnhancedForStatementNoShortIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEnhancedForStatementNoShortIf(this);
		}
	}

	public final EnhancedForStatementNoShortIfContext enhancedForStatementNoShortIf() throws RecognitionException {
		EnhancedForStatementNoShortIfContext _localctx = new EnhancedForStatementNoShortIfContext(_ctx, getState());
		enterRule(_localctx, 332, RULE_enhancedForStatementNoShortIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1718); match(FOR);
			setState(1719); match(LPAREN);
			setState(1723);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1720); variableModifier();
				}
				}
				setState(1725);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1726); unannType();
			setState(1727); variableDeclaratorId();
			setState(1728); match(COLON);
			setState(1729); expression();
			setState(1730); match(RPAREN);
			setState(1731); statementNoShortIf();
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

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 334, RULE_breakStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1733); match(BREAK);
			setState(1735);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1734); match(Identifier);
				}
			}

			setState(1737); match(SEMI);
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

	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 336, RULE_continueStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1739); match(CONTINUE);
			setState(1741);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1740); match(Identifier);
				}
			}

			setState(1743); match(SEMI);
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 338, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1745); match(RETURN);
			setState(1747);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(1746); expression();
				}
			}

			setState(1749); match(SEMI);
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

	public static class ThrowStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitThrowStatement(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 340, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1751); match(THROW);
			setState(1752); expression();
			setState(1753); match(SEMI);
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

	public static class SynchronizedStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SynchronizedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synchronizedStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSynchronizedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSynchronizedStatement(this);
		}
	}

	public final SynchronizedStatementContext synchronizedStatement() throws RecognitionException {
		SynchronizedStatementContext _localctx = new SynchronizedStatementContext(_ctx, getState());
		enterRule(_localctx, 342, RULE_synchronizedStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1755); match(SYNCHRONIZED);
			setState(1756); match(LPAREN);
			setState(1757); expression();
			setState(1758); match(RPAREN);
			setState(1759); block();
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

	public static class TryStatementContext extends ParserRuleContext {
		public CatchesContext catches() {
			return getRuleContext(CatchesContext.class,0);
		}
		public Finally_Context finally_() {
			return getRuleContext(Finally_Context.class,0);
		}
		public TryWithResourcesStatementContext tryWithResourcesStatement() {
			return getRuleContext(TryWithResourcesStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TryStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTryStatement(this);
		}
	}

	public final TryStatementContext tryStatement() throws RecognitionException {
		TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
		enterRule(_localctx, 344, RULE_tryStatement);
		int _la;
		try {
			setState(1773);
			switch ( getInterpreter().adaptivePredict(_input,174,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1761); match(TRY);
				setState(1762); block();
				setState(1763); catches();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1765); match(TRY);
				setState(1766); block();
				setState(1768);
				_la = _input.LA(1);
				if (_la==CATCH) {
					{
					setState(1767); catches();
					}
				}

				setState(1770); finally_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1772); tryWithResourcesStatement();
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

	public static class CatchesContext extends ParserRuleContext {
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catches; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterCatches(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitCatches(this);
		}
	}

	public final CatchesContext catches() throws RecognitionException {
		CatchesContext _localctx = new CatchesContext(_ctx, getState());
		enterRule(_localctx, 346, RULE_catches);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1775); catchClause();
			setState(1779);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CATCH) {
				{
				{
				setState(1776); catchClause();
				}
				}
				setState(1781);
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

	public static class CatchClauseContext extends ParserRuleContext {
		public CatchFormalParameterContext catchFormalParameter() {
			return getRuleContext(CatchFormalParameterContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitCatchClause(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 348, RULE_catchClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1782); match(CATCH);
			setState(1783); match(LPAREN);
			setState(1784); catchFormalParameter();
			setState(1785); match(RPAREN);
			setState(1786); block();
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

	public static class CatchFormalParameterContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public CatchTypeContext catchType() {
			return getRuleContext(CatchTypeContext.class,0);
		}
		public CatchFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterCatchFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitCatchFormalParameter(this);
		}
	}

	public final CatchFormalParameterContext catchFormalParameter() throws RecognitionException {
		CatchFormalParameterContext _localctx = new CatchFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 350, RULE_catchFormalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1791);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1788); variableModifier();
				}
				}
				setState(1793);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1794); catchType();
			setState(1795); variableDeclaratorId();
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
		public UnannClassTypeContext unannClassType() {
			return getRuleContext(UnannClassTypeContext.class,0);
		}
		public List<ClassTypeContext> classType() {
			return getRuleContexts(ClassTypeContext.class);
		}
		public ClassTypeContext classType(int i) {
			return getRuleContext(ClassTypeContext.class,i);
		}
		public CatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterCatchType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitCatchType(this);
		}
	}

	public final CatchTypeContext catchType() throws RecognitionException {
		CatchTypeContext _localctx = new CatchTypeContext(_ctx, getState());
		enterRule(_localctx, 352, RULE_catchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1797); unannClassType();
			setState(1802);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(1798); match(BITOR);
				setState(1799); classType();
				}
				}
				setState(1804);
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

	public static class Finally_Context extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Finally_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finally_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFinally_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFinally_(this);
		}
	}

	public final Finally_Context finally_() throws RecognitionException {
		Finally_Context _localctx = new Finally_Context(_ctx, getState());
		enterRule(_localctx, 354, RULE_finally_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1805); match(FINALLY);
			setState(1806); block();
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

	public static class TryWithResourcesStatementContext extends ParserRuleContext {
		public CatchesContext catches() {
			return getRuleContext(CatchesContext.class,0);
		}
		public ResourceSpecificationContext resourceSpecification() {
			return getRuleContext(ResourceSpecificationContext.class,0);
		}
		public Finally_Context finally_() {
			return getRuleContext(Finally_Context.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TryWithResourcesStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryWithResourcesStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTryWithResourcesStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTryWithResourcesStatement(this);
		}
	}

	public final TryWithResourcesStatementContext tryWithResourcesStatement() throws RecognitionException {
		TryWithResourcesStatementContext _localctx = new TryWithResourcesStatementContext(_ctx, getState());
		enterRule(_localctx, 356, RULE_tryWithResourcesStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1808); match(TRY);
			setState(1809); resourceSpecification();
			setState(1810); block();
			setState(1812);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(1811); catches();
				}
			}

			setState(1815);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(1814); finally_();
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

	public static class ResourceSpecificationContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(Java8Parser.SEMI, 0); }
		public ResourceListContext resourceList() {
			return getRuleContext(ResourceListContext.class,0);
		}
		public ResourceSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterResourceSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitResourceSpecification(this);
		}
	}

	public final ResourceSpecificationContext resourceSpecification() throws RecognitionException {
		ResourceSpecificationContext _localctx = new ResourceSpecificationContext(_ctx, getState());
		enterRule(_localctx, 358, RULE_resourceSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1817); match(LPAREN);
			setState(1818); resourceList();
			setState(1820);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1819); match(SEMI);
				}
			}

			setState(1822); match(RPAREN);
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

	public static class ResourceListContext extends ParserRuleContext {
		public List<ResourceContext> resource() {
			return getRuleContexts(ResourceContext.class);
		}
		public ResourceContext resource(int i) {
			return getRuleContext(ResourceContext.class,i);
		}
		public ResourceListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterResourceList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitResourceList(this);
		}
	}

	public final ResourceListContext resourceList() throws RecognitionException {
		ResourceListContext _localctx = new ResourceListContext(_ctx, getState());
		enterRule(_localctx, 360, RULE_resourceList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1824); resource();
			setState(1829);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,181,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1825); match(SEMI);
					setState(1826); resource();
					}
					} 
				}
				setState(1831);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,181,_ctx);
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
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public UnannTypeContext unannType() {
			return getRuleContext(UnannTypeContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 362, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1835);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1832); variableModifier();
				}
				}
				setState(1837);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1838); unannType();
			setState(1839); variableDeclaratorId();
			setState(1840); match(ASSIGN);
			setState(1841); expression();
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
		public PrimaryNoNewArray_lf_primaryContext primaryNoNewArray_lf_primary(int i) {
			return getRuleContext(PrimaryNoNewArray_lf_primaryContext.class,i);
		}
		public List<PrimaryNoNewArray_lf_primaryContext> primaryNoNewArray_lf_primary() {
			return getRuleContexts(PrimaryNoNewArray_lf_primaryContext.class);
		}
		public PrimaryNoNewArray_lfno_primaryContext primaryNoNewArray_lfno_primary() {
			return getRuleContext(PrimaryNoNewArray_lfno_primaryContext.class,0);
		}
		public ArrayCreationExpressionContext arrayCreationExpression() {
			return getRuleContext(ArrayCreationExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 364, RULE_primary);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1845);
			switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
			case 1:
				{
				setState(1843); primaryNoNewArray_lfno_primary();
				}
				break;
			case 2:
				{
				setState(1844); arrayCreationExpression();
				}
				break;
			}
			setState(1850);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,184,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1847); primaryNoNewArray_lf_primary();
					}
					} 
				}
				setState(1852);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,184,_ctx);
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

	public static class PrimaryNoNewArrayContext extends ParserRuleContext {
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public MethodInvocationContext methodInvocation() {
			return getRuleContext(MethodInvocationContext.class,0);
		}
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public List<SquareBracketsContext> squareBrackets() {
			return getRuleContexts(SquareBracketsContext.class);
		}
		public MethodReferenceContext methodReference() {
			return getRuleContext(MethodReferenceContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SquareBracketsContext squareBrackets(int i) {
			return getRuleContext(SquareBracketsContext.class,i);
		}
		public ClassInstanceCreationExpressionContext classInstanceCreationExpression() {
			return getRuleContext(ClassInstanceCreationExpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public PrimaryNoNewArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray(this);
		}
	}

	public final PrimaryNoNewArrayContext primaryNoNewArray() throws RecognitionException {
		PrimaryNoNewArrayContext _localctx = new PrimaryNoNewArrayContext(_ctx, getState());
		enterRule(_localctx, 366, RULE_primaryNoNewArray);
		int _la;
		try {
			setState(1881);
			switch ( getInterpreter().adaptivePredict(_input,186,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1853); literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1854); typeName();
				setState(1858);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(1855); squareBrackets();
					}
					}
					setState(1860);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1861); match(DOT);
				setState(1862); match(CLASS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1864); match(VOID);
				setState(1865); match(DOT);
				setState(1866); match(CLASS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1867); match(THIS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1868); typeName();
				setState(1869); match(DOT);
				setState(1870); match(THIS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1872); match(LPAREN);
				setState(1873); expression();
				setState(1874); match(RPAREN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1876); classInstanceCreationExpression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1877); fieldAccess();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1878); arrayAccess();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1879); methodInvocation();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1880); methodReference();
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

	public static class PrimaryNoNewArray_lf_arrayAccessContext extends ParserRuleContext {
		public PrimaryNoNewArray_lf_arrayAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lf_arrayAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lf_arrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lf_arrayAccess(this);
		}
	}

	public final PrimaryNoNewArray_lf_arrayAccessContext primaryNoNewArray_lf_arrayAccess() throws RecognitionException {
		PrimaryNoNewArray_lf_arrayAccessContext _localctx = new PrimaryNoNewArray_lf_arrayAccessContext(_ctx, getState());
		enterRule(_localctx, 368, RULE_primaryNoNewArray_lf_arrayAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
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

	public static class PrimaryNoNewArray_lfno_arrayAccessContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public MethodInvocationContext methodInvocation() {
			return getRuleContext(MethodInvocationContext.class,0);
		}
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public List<SquareBracketsContext> squareBrackets() {
			return getRuleContexts(SquareBracketsContext.class);
		}
		public MethodReferenceContext methodReference() {
			return getRuleContext(MethodReferenceContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SquareBracketsContext squareBrackets(int i) {
			return getRuleContext(SquareBracketsContext.class,i);
		}
		public ClassInstanceCreationExpressionContext classInstanceCreationExpression() {
			return getRuleContext(ClassInstanceCreationExpressionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public PrimaryNoNewArray_lfno_arrayAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lfno_arrayAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lfno_arrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lfno_arrayAccess(this);
		}
	}

	public final PrimaryNoNewArray_lfno_arrayAccessContext primaryNoNewArray_lfno_arrayAccess() throws RecognitionException {
		PrimaryNoNewArray_lfno_arrayAccessContext _localctx = new PrimaryNoNewArray_lfno_arrayAccessContext(_ctx, getState());
		enterRule(_localctx, 370, RULE_primaryNoNewArray_lfno_arrayAccess);
		int _la;
		try {
			setState(1912);
			switch ( getInterpreter().adaptivePredict(_input,188,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1885); literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1886); typeName();
				setState(1890);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(1887); squareBrackets();
					}
					}
					setState(1892);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1893); match(DOT);
				setState(1894); match(CLASS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1896); match(VOID);
				setState(1897); match(DOT);
				setState(1898); match(CLASS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1899); match(THIS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1900); typeName();
				setState(1901); match(DOT);
				setState(1902); match(THIS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1904); match(LPAREN);
				setState(1905); expression();
				setState(1906); match(RPAREN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1908); classInstanceCreationExpression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1909); fieldAccess();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1910); methodInvocation();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1911); methodReference();
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

	public static class PrimaryNoNewArray_lf_primaryContext extends ParserRuleContext {
		public MethodInvocation_lf_primaryContext methodInvocation_lf_primary() {
			return getRuleContext(MethodInvocation_lf_primaryContext.class,0);
		}
		public ArrayAccess_lf_primaryContext arrayAccess_lf_primary() {
			return getRuleContext(ArrayAccess_lf_primaryContext.class,0);
		}
		public FieldAccess_lf_primaryContext fieldAccess_lf_primary() {
			return getRuleContext(FieldAccess_lf_primaryContext.class,0);
		}
		public ClassInstanceCreationExpression_lf_primaryContext classInstanceCreationExpression_lf_primary() {
			return getRuleContext(ClassInstanceCreationExpression_lf_primaryContext.class,0);
		}
		public MethodReference_lf_primaryContext methodReference_lf_primary() {
			return getRuleContext(MethodReference_lf_primaryContext.class,0);
		}
		public PrimaryNoNewArray_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lf_primary(this);
		}
	}

	public final PrimaryNoNewArray_lf_primaryContext primaryNoNewArray_lf_primary() throws RecognitionException {
		PrimaryNoNewArray_lf_primaryContext _localctx = new PrimaryNoNewArray_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 372, RULE_primaryNoNewArray_lf_primary);
		try {
			setState(1919);
			switch ( getInterpreter().adaptivePredict(_input,189,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1914); classInstanceCreationExpression_lf_primary();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1915); fieldAccess_lf_primary();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1916); arrayAccess_lf_primary();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1917); methodInvocation_lf_primary();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1918); methodReference_lf_primary();
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

	public static class PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext extends ParserRuleContext {
		public PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(this);
		}
	}

	public final PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary() throws RecognitionException {
		PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext _localctx = new PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 374, RULE_primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary);
		try {
			enterOuterAlt(_localctx, 1);
			{
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

	public static class PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext extends ParserRuleContext {
		public MethodInvocation_lf_primaryContext methodInvocation_lf_primary() {
			return getRuleContext(MethodInvocation_lf_primaryContext.class,0);
		}
		public FieldAccess_lf_primaryContext fieldAccess_lf_primary() {
			return getRuleContext(FieldAccess_lf_primaryContext.class,0);
		}
		public ClassInstanceCreationExpression_lf_primaryContext classInstanceCreationExpression_lf_primary() {
			return getRuleContext(ClassInstanceCreationExpression_lf_primaryContext.class,0);
		}
		public MethodReference_lf_primaryContext methodReference_lf_primary() {
			return getRuleContext(MethodReference_lf_primaryContext.class,0);
		}
		public PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(this);
		}
	}

	public final PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary() throws RecognitionException {
		PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext _localctx = new PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 376, RULE_primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary);
		try {
			setState(1927);
			switch ( getInterpreter().adaptivePredict(_input,190,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1923); classInstanceCreationExpression_lf_primary();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1924); fieldAccess_lf_primary();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1925); methodInvocation_lf_primary();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1926); methodReference_lf_primary();
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

	public static class PrimaryNoNewArray_lfno_primaryContext extends ParserRuleContext {
		public ArrayAccess_lfno_primaryContext arrayAccess_lfno_primary() {
			return getRuleContext(ArrayAccess_lfno_primaryContext.class,0);
		}
		public UnannPrimitiveTypeContext unannPrimitiveType() {
			return getRuleContext(UnannPrimitiveTypeContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public FieldAccess_lfno_primaryContext fieldAccess_lfno_primary() {
			return getRuleContext(FieldAccess_lfno_primaryContext.class,0);
		}
		public MethodReference_lfno_primaryContext methodReference_lfno_primary() {
			return getRuleContext(MethodReference_lfno_primaryContext.class,0);
		}
		public List<SquareBracketsContext> squareBrackets() {
			return getRuleContexts(SquareBracketsContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MethodInvocation_lfno_primaryContext methodInvocation_lfno_primary() {
			return getRuleContext(MethodInvocation_lfno_primaryContext.class,0);
		}
		public SquareBracketsContext squareBrackets(int i) {
			return getRuleContext(SquareBracketsContext.class,i);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ClassInstanceCreationExpression_lfno_primaryContext classInstanceCreationExpression_lfno_primary() {
			return getRuleContext(ClassInstanceCreationExpression_lfno_primaryContext.class,0);
		}
		public PrimaryNoNewArray_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lfno_primary(this);
		}
	}

	public final PrimaryNoNewArray_lfno_primaryContext primaryNoNewArray_lfno_primary() throws RecognitionException {
		PrimaryNoNewArray_lfno_primaryContext _localctx = new PrimaryNoNewArray_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 378, RULE_primaryNoNewArray_lfno_primary);
		int _la;
		try {
			setState(1967);
			switch ( getInterpreter().adaptivePredict(_input,193,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1929); literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1930); typeName();
				setState(1934);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(1931); squareBrackets();
					}
					}
					setState(1936);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1937); match(DOT);
				setState(1938); match(CLASS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1940); unannPrimitiveType();
				setState(1944);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(1941); squareBrackets();
					}
					}
					setState(1946);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1947); match(DOT);
				setState(1948); match(CLASS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1950); match(VOID);
				setState(1951); match(DOT);
				setState(1952); match(CLASS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1953); match(THIS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1954); typeName();
				setState(1955); match(DOT);
				setState(1956); match(THIS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1958); match(LPAREN);
				setState(1959); expression();
				setState(1960); match(RPAREN);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1962); classInstanceCreationExpression_lfno_primary();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1963); fieldAccess_lfno_primary();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1964); arrayAccess_lfno_primary();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1965); methodInvocation_lfno_primary();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1966); methodReference_lfno_primary();
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

	public static class PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext extends ParserRuleContext {
		public PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(this);
		}
	}

	public final PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary() throws RecognitionException {
		PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext _localctx = new PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 380, RULE_primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary);
		try {
			enterOuterAlt(_localctx, 1);
			{
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

	public static class PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext extends ParserRuleContext {
		public UnannPrimitiveTypeContext unannPrimitiveType() {
			return getRuleContext(UnannPrimitiveTypeContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public FieldAccess_lfno_primaryContext fieldAccess_lfno_primary() {
			return getRuleContext(FieldAccess_lfno_primaryContext.class,0);
		}
		public MethodReference_lfno_primaryContext methodReference_lfno_primary() {
			return getRuleContext(MethodReference_lfno_primaryContext.class,0);
		}
		public List<SquareBracketsContext> squareBrackets() {
			return getRuleContexts(SquareBracketsContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MethodInvocation_lfno_primaryContext methodInvocation_lfno_primary() {
			return getRuleContext(MethodInvocation_lfno_primaryContext.class,0);
		}
		public SquareBracketsContext squareBrackets(int i) {
			return getRuleContext(SquareBracketsContext.class,i);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ClassInstanceCreationExpression_lfno_primaryContext classInstanceCreationExpression_lfno_primary() {
			return getRuleContext(ClassInstanceCreationExpression_lfno_primaryContext.class,0);
		}
		public PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(this);
		}
	}

	public final PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary() throws RecognitionException {
		PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext _localctx = new PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 382, RULE_primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary);
		int _la;
		try {
			setState(2008);
			switch ( getInterpreter().adaptivePredict(_input,196,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1971); literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1972); typeName();
				setState(1976);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(1973); squareBrackets();
					}
					}
					setState(1978);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1979); match(DOT);
				setState(1980); match(CLASS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1982); unannPrimitiveType();
				setState(1986);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(1983); squareBrackets();
					}
					}
					setState(1988);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1989); match(DOT);
				setState(1990); match(CLASS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1992); match(VOID);
				setState(1993); match(DOT);
				setState(1994); match(CLASS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1995); match(THIS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1996); typeName();
				setState(1997); match(DOT);
				setState(1998); match(THIS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2000); match(LPAREN);
				setState(2001); expression();
				setState(2002); match(RPAREN);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(2004); classInstanceCreationExpression_lfno_primary();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(2005); fieldAccess_lfno_primary();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(2006); methodInvocation_lfno_primary();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(2007); methodReference_lfno_primary();
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

	public static class ClassInstanceCreationExpressionContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AnnotationIdentifierContext annotationIdentifier(int i) {
			return getRuleContext(AnnotationIdentifierContext.class,i);
		}
		public List<AnnotationIdentifierContext> annotationIdentifier() {
			return getRuleContexts(AnnotationIdentifierContext.class);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public ClassInstanceCreationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classInstanceCreationExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassInstanceCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassInstanceCreationExpression(this);
		}
	}

	public final ClassInstanceCreationExpressionContext classInstanceCreationExpression() throws RecognitionException {
		ClassInstanceCreationExpressionContext _localctx = new ClassInstanceCreationExpressionContext(_ctx, getState());
		enterRule(_localctx, 384, RULE_classInstanceCreationExpression);
		int _la;
		try {
			setState(2069);
			switch ( getInterpreter().adaptivePredict(_input,210,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2010); match(NEW);
				setState(2012);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2011); typeArguments();
					}
				}

				setState(2014); annotationIdentifier();
				setState(2019);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(2015); match(DOT);
					setState(2016); annotationIdentifier();
					}
					}
					setState(2021);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2023);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2022); typeArgumentsOrDiamond();
					}
				}

				setState(2025); match(LPAREN);
				setState(2027);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2026); argumentList();
					}
				}

				setState(2029); match(RPAREN);
				setState(2031);
				_la = _input.LA(1);
				if (_la==LBRACE) {
					{
					setState(2030); classBody();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2033); expressionName();
				setState(2034); match(DOT);
				setState(2035); match(NEW);
				setState(2037);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2036); typeArguments();
					}
				}

				setState(2039); annotationIdentifier();
				setState(2041);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2040); typeArgumentsOrDiamond();
					}
				}

				setState(2043); match(LPAREN);
				setState(2045);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2044); argumentList();
					}
				}

				setState(2047); match(RPAREN);
				setState(2049);
				_la = _input.LA(1);
				if (_la==LBRACE) {
					{
					setState(2048); classBody();
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2051); primary();
				setState(2052); match(DOT);
				setState(2053); match(NEW);
				setState(2055);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2054); typeArguments();
					}
				}

				setState(2057); annotationIdentifier();
				setState(2059);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2058); typeArgumentsOrDiamond();
					}
				}

				setState(2061); match(LPAREN);
				setState(2063);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2062); argumentList();
					}
				}

				setState(2065); match(RPAREN);
				setState(2067);
				_la = _input.LA(1);
				if (_la==LBRACE) {
					{
					setState(2066); classBody();
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

	public static class ClassInstanceCreationExpression_lf_primaryContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public AnnotationIdentifierContext annotationIdentifier() {
			return getRuleContext(AnnotationIdentifierContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassInstanceCreationExpression_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classInstanceCreationExpression_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassInstanceCreationExpression_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassInstanceCreationExpression_lf_primary(this);
		}
	}

	public final ClassInstanceCreationExpression_lf_primaryContext classInstanceCreationExpression_lf_primary() throws RecognitionException {
		ClassInstanceCreationExpression_lf_primaryContext _localctx = new ClassInstanceCreationExpression_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 386, RULE_classInstanceCreationExpression_lf_primary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2071); match(DOT);
			setState(2072); match(NEW);
			setState(2074);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2073); typeArguments();
				}
			}

			setState(2076); annotationIdentifier();
			setState(2078);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2077); typeArgumentsOrDiamond();
				}
			}

			setState(2080); match(LPAREN);
			setState(2082);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(2081); argumentList();
				}
			}

			setState(2084); match(RPAREN);
			setState(2086);
			switch ( getInterpreter().adaptivePredict(_input,214,_ctx) ) {
			case 1:
				{
				setState(2085); classBody();
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

	public static class ClassInstanceCreationExpression_lfno_primaryContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public AnnotationIdentifierContext annotationIdentifier(int i) {
			return getRuleContext(AnnotationIdentifierContext.class,i);
		}
		public List<AnnotationIdentifierContext> annotationIdentifier() {
			return getRuleContexts(AnnotationIdentifierContext.class);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public ClassInstanceCreationExpression_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classInstanceCreationExpression_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterClassInstanceCreationExpression_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitClassInstanceCreationExpression_lfno_primary(this);
		}
	}

	public final ClassInstanceCreationExpression_lfno_primaryContext classInstanceCreationExpression_lfno_primary() throws RecognitionException {
		ClassInstanceCreationExpression_lfno_primaryContext _localctx = new ClassInstanceCreationExpression_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 388, RULE_classInstanceCreationExpression_lfno_primary);
		int _la;
		try {
			setState(2129);
			switch (_input.LA(1)) {
			case NEW:
				enterOuterAlt(_localctx, 1);
				{
				setState(2088); match(NEW);
				setState(2090);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2089); typeArguments();
					}
				}

				setState(2092); annotationIdentifier();
				setState(2097);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(2093); match(DOT);
					setState(2094); annotationIdentifier();
					}
					}
					setState(2099);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2101);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2100); typeArgumentsOrDiamond();
					}
				}

				setState(2103); match(LPAREN);
				setState(2105);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2104); argumentList();
					}
				}

				setState(2107); match(RPAREN);
				setState(2109);
				switch ( getInterpreter().adaptivePredict(_input,219,_ctx) ) {
				case 1:
					{
					setState(2108); classBody();
					}
					break;
				}
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(2111); expressionName();
				setState(2112); match(DOT);
				setState(2113); match(NEW);
				setState(2115);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2114); typeArguments();
					}
				}

				setState(2117); annotationIdentifier();
				setState(2119);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2118); typeArgumentsOrDiamond();
					}
				}

				setState(2121); match(LPAREN);
				setState(2123);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2122); argumentList();
					}
				}

				setState(2125); match(RPAREN);
				setState(2127);
				switch ( getInterpreter().adaptivePredict(_input,223,_ctx) ) {
				case 1:
					{
					setState(2126); classBody();
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

	public static class TypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public TypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitTypeArgumentsOrDiamond(this);
		}
	}

	public final TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() throws RecognitionException {
		TypeArgumentsOrDiamondContext _localctx = new TypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 390, RULE_typeArgumentsOrDiamond);
		try {
			setState(2134);
			switch ( getInterpreter().adaptivePredict(_input,225,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2131); typeArguments();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2132); match(LT);
				setState(2133); match(GT);
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

	public static class FieldAccessContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public FieldAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFieldAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFieldAccess(this);
		}
	}

	public final FieldAccessContext fieldAccess() throws RecognitionException {
		FieldAccessContext _localctx = new FieldAccessContext(_ctx, getState());
		enterRule(_localctx, 392, RULE_fieldAccess);
		try {
			setState(2149);
			switch ( getInterpreter().adaptivePredict(_input,226,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2136); primary();
				setState(2137); match(DOT);
				setState(2138); match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2140); match(SUPER);
				setState(2141); match(DOT);
				setState(2142); match(Identifier);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2143); typeName();
				setState(2144); match(DOT);
				setState(2145); match(SUPER);
				setState(2146); match(DOT);
				setState(2147); match(Identifier);
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

	public static class FieldAccess_lf_primaryContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public FieldAccess_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldAccess_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFieldAccess_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFieldAccess_lf_primary(this);
		}
	}

	public final FieldAccess_lf_primaryContext fieldAccess_lf_primary() throws RecognitionException {
		FieldAccess_lf_primaryContext _localctx = new FieldAccess_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 394, RULE_fieldAccess_lf_primary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2151); match(DOT);
			setState(2152); match(Identifier);
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

	public static class FieldAccess_lfno_primaryContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public FieldAccess_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldAccess_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterFieldAccess_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitFieldAccess_lfno_primary(this);
		}
	}

	public final FieldAccess_lfno_primaryContext fieldAccess_lfno_primary() throws RecognitionException {
		FieldAccess_lfno_primaryContext _localctx = new FieldAccess_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 396, RULE_fieldAccess_lfno_primary);
		try {
			setState(2163);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(2154); match(SUPER);
				setState(2155); match(DOT);
				setState(2156); match(Identifier);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(2157); typeName();
				setState(2158); match(DOT);
				setState(2159); match(SUPER);
				setState(2160); match(DOT);
				setState(2161); match(Identifier);
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

	public static class ArrayAccessContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<PrimaryNoNewArray_lf_arrayAccessContext> primaryNoNewArray_lf_arrayAccess() {
			return getRuleContexts(PrimaryNoNewArray_lf_arrayAccessContext.class);
		}
		public PrimaryNoNewArray_lf_arrayAccessContext primaryNoNewArray_lf_arrayAccess(int i) {
			return getRuleContext(PrimaryNoNewArray_lf_arrayAccessContext.class,i);
		}
		public PrimaryNoNewArray_lfno_arrayAccessContext primaryNoNewArray_lfno_arrayAccess() {
			return getRuleContext(PrimaryNoNewArray_lfno_arrayAccessContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public ArrayAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArrayAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArrayAccess(this);
		}
	}

	public final ArrayAccessContext arrayAccess() throws RecognitionException {
		ArrayAccessContext _localctx = new ArrayAccessContext(_ctx, getState());
		enterRule(_localctx, 398, RULE_arrayAccess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2175);
			switch ( getInterpreter().adaptivePredict(_input,228,_ctx) ) {
			case 1:
				{
				setState(2165); expressionName();
				setState(2166); match(LBRACK);
				setState(2167); expression();
				setState(2168); match(RBRACK);
				}
				break;
			case 2:
				{
				setState(2170); primaryNoNewArray_lfno_arrayAccess();
				setState(2171); match(LBRACK);
				setState(2172); expression();
				setState(2173); match(RBRACK);
				}
				break;
			}
			setState(2184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(2177); primaryNoNewArray_lf_arrayAccess();
				setState(2178); match(LBRACK);
				setState(2179); expression();
				setState(2180); match(RBRACK);
				}
				}
				setState(2186);
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

	public static class ArrayAccess_lf_primaryContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(int i) {
			return getRuleContext(PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext.class,i);
		}
		public List<PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext> primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary() {
			return getRuleContexts(PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext.class);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary() {
			return getRuleContext(PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext.class,0);
		}
		public ArrayAccess_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAccess_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArrayAccess_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArrayAccess_lf_primary(this);
		}
	}

	public final ArrayAccess_lf_primaryContext arrayAccess_lf_primary() throws RecognitionException {
		ArrayAccess_lf_primaryContext _localctx = new ArrayAccess_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 400, RULE_arrayAccess_lf_primary);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2187); primaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary();
			setState(2188); match(LBRACK);
			setState(2189); expression();
			setState(2190); match(RBRACK);
			}
			setState(2199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2192); primaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary();
					setState(2193); match(LBRACK);
					setState(2194); expression();
					setState(2195); match(RBRACK);
					}
					} 
				}
				setState(2201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
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

	public static class ArrayAccess_lfno_primaryContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(int i) {
			return getRuleContext(PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext.class,i);
		}
		public List<PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext> primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary() {
			return getRuleContexts(PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext.class);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary() {
			return getRuleContext(PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public ArrayAccess_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayAccess_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArrayAccess_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArrayAccess_lfno_primary(this);
		}
	}

	public final ArrayAccess_lfno_primaryContext arrayAccess_lfno_primary() throws RecognitionException {
		ArrayAccess_lfno_primaryContext _localctx = new ArrayAccess_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 402, RULE_arrayAccess_lfno_primary);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2212);
			switch ( getInterpreter().adaptivePredict(_input,231,_ctx) ) {
			case 1:
				{
				setState(2202); expressionName();
				setState(2203); match(LBRACK);
				setState(2204); expression();
				setState(2205); match(RBRACK);
				}
				break;
			case 2:
				{
				setState(2207); primaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary();
				setState(2208); match(LBRACK);
				setState(2209); expression();
				setState(2210); match(RBRACK);
				}
				break;
			}
			setState(2221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,232,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2214); primaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary();
					setState(2215); match(LBRACK);
					setState(2216); expression();
					setState(2217); match(RBRACK);
					}
					} 
				}
				setState(2223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,232,_ctx);
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

	public static class MethodInvocationContext extends ParserRuleContext {
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public MethodInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodInvocation(this);
		}
	}

	public final MethodInvocationContext methodInvocation() throws RecognitionException {
		MethodInvocationContext _localctx = new MethodInvocationContext(_ctx, getState());
		enterRule(_localctx, 404, RULE_methodInvocation);
		int _la;
		try {
			setState(2292);
			switch ( getInterpreter().adaptivePredict(_input,244,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2224); methodName();
				setState(2225); match(LPAREN);
				setState(2227);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2226); argumentList();
					}
				}

				setState(2229); match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2231); typeName();
				setState(2232); match(DOT);
				setState(2234);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2233); typeArguments();
					}
				}

				setState(2236); match(Identifier);
				setState(2237); match(LPAREN);
				setState(2239);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2238); argumentList();
					}
				}

				setState(2241); match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2243); expressionName();
				setState(2244); match(DOT);
				setState(2246);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2245); typeArguments();
					}
				}

				setState(2248); match(Identifier);
				setState(2249); match(LPAREN);
				setState(2251);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2250); argumentList();
					}
				}

				setState(2253); match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2255); primary();
				setState(2256); match(DOT);
				setState(2258);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2257); typeArguments();
					}
				}

				setState(2260); match(Identifier);
				setState(2261); match(LPAREN);
				setState(2263);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2262); argumentList();
					}
				}

				setState(2265); match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2267); match(SUPER);
				setState(2268); match(DOT);
				setState(2270);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2269); typeArguments();
					}
				}

				setState(2272); match(Identifier);
				setState(2273); match(LPAREN);
				setState(2275);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2274); argumentList();
					}
				}

				setState(2277); match(RPAREN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2278); typeName();
				setState(2279); match(DOT);
				setState(2280); match(SUPER);
				setState(2281); match(DOT);
				setState(2283);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2282); typeArguments();
					}
				}

				setState(2285); match(Identifier);
				setState(2286); match(LPAREN);
				setState(2288);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2287); argumentList();
					}
				}

				setState(2290); match(RPAREN);
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

	public static class MethodInvocation_lf_primaryContext extends ParserRuleContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public MethodInvocation_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodInvocation_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodInvocation_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodInvocation_lf_primary(this);
		}
	}

	public final MethodInvocation_lf_primaryContext methodInvocation_lf_primary() throws RecognitionException {
		MethodInvocation_lf_primaryContext _localctx = new MethodInvocation_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 406, RULE_methodInvocation_lf_primary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2294); match(DOT);
			setState(2296);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2295); typeArguments();
				}
			}

			setState(2298); match(Identifier);
			setState(2299); match(LPAREN);
			setState(2301);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
				{
				setState(2300); argumentList();
				}
			}

			setState(2303); match(RPAREN);
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

	public static class MethodInvocation_lfno_primaryContext extends ParserRuleContext {
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(Java8Parser.SUPER, 0); }
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public MethodInvocation_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodInvocation_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodInvocation_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodInvocation_lfno_primary(this);
		}
	}

	public final MethodInvocation_lfno_primaryContext methodInvocation_lfno_primary() throws RecognitionException {
		MethodInvocation_lfno_primaryContext _localctx = new MethodInvocation_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 408, RULE_methodInvocation_lfno_primary);
		int _la;
		try {
			setState(2361);
			switch ( getInterpreter().adaptivePredict(_input,256,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2305); methodName();
				setState(2306); match(LPAREN);
				setState(2308);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2307); argumentList();
					}
				}

				setState(2310); match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2312); typeName();
				setState(2313); match(DOT);
				setState(2315);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2314); typeArguments();
					}
				}

				setState(2317); match(Identifier);
				setState(2318); match(LPAREN);
				setState(2320);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2319); argumentList();
					}
				}

				setState(2322); match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2324); expressionName();
				setState(2325); match(DOT);
				setState(2327);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2326); typeArguments();
					}
				}

				setState(2329); match(Identifier);
				setState(2330); match(LPAREN);
				setState(2332);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2331); argumentList();
					}
				}

				setState(2334); match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2336); match(SUPER);
				setState(2337); match(DOT);
				setState(2339);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2338); typeArguments();
					}
				}

				setState(2341); match(Identifier);
				setState(2342); match(LPAREN);
				setState(2344);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2343); argumentList();
					}
				}

				setState(2346); match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2347); typeName();
				setState(2348); match(DOT);
				setState(2349); match(SUPER);
				setState(2350); match(DOT);
				setState(2352);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2351); typeArguments();
					}
				}

				setState(2354); match(Identifier);
				setState(2355); match(LPAREN);
				setState(2357);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (BANG - 70)) | (1L << (TILDE - 70)) | (1L << (INC - 70)) | (1L << (DEC - 70)) | (1L << (ADD - 70)) | (1L << (SUB - 70)) | (1L << (Identifier - 70)) | (1L << (AT - 70)))) != 0)) {
					{
					setState(2356); argumentList();
					}
				}

				setState(2359); match(RPAREN);
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

	public static class ArgumentListContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArgumentList(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 410, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2363); expression();
			setState(2368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2364); match(COMMA);
				setState(2365); expression();
				}
				}
				setState(2370);
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

	public static class MethodReferenceContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public MethodReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodReference(this);
		}
	}

	public final MethodReferenceContext methodReference() throws RecognitionException {
		MethodReferenceContext _localctx = new MethodReferenceContext(_ctx, getState());
		enterRule(_localctx, 412, RULE_methodReference);
		int _la;
		try {
			setState(2418);
			switch ( getInterpreter().adaptivePredict(_input,264,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2371); expressionName();
				setState(2372); match(COLONCOLON);
				setState(2374);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2373); typeArguments();
					}
				}

				setState(2376); match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2378); referenceType();
				setState(2379); match(COLONCOLON);
				setState(2381);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2380); typeArguments();
					}
				}

				setState(2383); match(Identifier);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2385); primary();
				setState(2386); match(COLONCOLON);
				setState(2388);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2387); typeArguments();
					}
				}

				setState(2390); match(Identifier);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2392); match(SUPER);
				setState(2393); match(COLONCOLON);
				setState(2395);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2394); typeArguments();
					}
				}

				setState(2397); match(Identifier);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2398); typeName();
				setState(2399); match(DOT);
				setState(2400); match(SUPER);
				setState(2401); match(COLONCOLON);
				setState(2403);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2402); typeArguments();
					}
				}

				setState(2405); match(Identifier);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2407); classType();
				setState(2408); match(COLONCOLON);
				setState(2410);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2409); typeArguments();
					}
				}

				setState(2412); match(NEW);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2414); arrayType();
				setState(2415); match(COLONCOLON);
				setState(2416); match(NEW);
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

	public static class MethodReference_lf_primaryContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public MethodReference_lf_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodReference_lf_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodReference_lf_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodReference_lf_primary(this);
		}
	}

	public final MethodReference_lf_primaryContext methodReference_lf_primary() throws RecognitionException {
		MethodReference_lf_primaryContext _localctx = new MethodReference_lf_primaryContext(_ctx, getState());
		enterRule(_localctx, 414, RULE_methodReference_lf_primary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2420); match(COLONCOLON);
			setState(2422);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2421); typeArguments();
				}
			}

			setState(2424); match(Identifier);
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

	public static class MethodReference_lfno_primaryContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public MethodReference_lfno_primaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodReference_lfno_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMethodReference_lfno_primary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMethodReference_lfno_primary(this);
		}
	}

	public final MethodReference_lfno_primaryContext methodReference_lfno_primary() throws RecognitionException {
		MethodReference_lfno_primaryContext _localctx = new MethodReference_lfno_primaryContext(_ctx, getState());
		enterRule(_localctx, 416, RULE_methodReference_lfno_primary);
		int _la;
		try {
			setState(2466);
			switch ( getInterpreter().adaptivePredict(_input,271,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2426); expressionName();
				setState(2427); match(COLONCOLON);
				setState(2429);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2428); typeArguments();
					}
				}

				setState(2431); match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2433); referenceType();
				setState(2434); match(COLONCOLON);
				setState(2436);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2435); typeArguments();
					}
				}

				setState(2438); match(Identifier);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2440); match(SUPER);
				setState(2441); match(COLONCOLON);
				setState(2443);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2442); typeArguments();
					}
				}

				setState(2445); match(Identifier);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2446); typeName();
				setState(2447); match(DOT);
				setState(2448); match(SUPER);
				setState(2449); match(COLONCOLON);
				setState(2451);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2450); typeArguments();
					}
				}

				setState(2453); match(Identifier);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2455); classType();
				setState(2456); match(COLONCOLON);
				setState(2458);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2457); typeArguments();
					}
				}

				setState(2460); match(NEW);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2462); arrayType();
				setState(2463); match(COLONCOLON);
				setState(2464); match(NEW);
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

	public static class ArrayCreationExpressionContext extends ParserRuleContext {
		public DimsContext dims() {
			return getRuleContext(DimsContext.class,0);
		}
		public DimExprsContext dimExprs() {
			return getRuleContext(DimExprsContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public ArrayCreationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreationExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterArrayCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitArrayCreationExpression(this);
		}
	}

	public final ArrayCreationExpressionContext arrayCreationExpression() throws RecognitionException {
		ArrayCreationExpressionContext _localctx = new ArrayCreationExpressionContext(_ctx, getState());
		enterRule(_localctx, 418, RULE_arrayCreationExpression);
		try {
			setState(2490);
			switch ( getInterpreter().adaptivePredict(_input,274,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2468); match(NEW);
				setState(2469); primitiveType();
				setState(2470); dimExprs();
				setState(2472);
				switch ( getInterpreter().adaptivePredict(_input,272,_ctx) ) {
				case 1:
					{
					setState(2471); dims();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2474); match(NEW);
				setState(2475); classOrInterfaceType();
				setState(2476); dimExprs();
				setState(2478);
				switch ( getInterpreter().adaptivePredict(_input,273,_ctx) ) {
				case 1:
					{
					setState(2477); dims();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2480); match(NEW);
				setState(2481); primitiveType();
				setState(2482); dims();
				setState(2483); arrayInitializer();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2485); match(NEW);
				setState(2486); classOrInterfaceType();
				setState(2487); dims();
				setState(2488); arrayInitializer();
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

	public static class DimExprsContext extends ParserRuleContext {
		public DimExprContext dimExpr(int i) {
			return getRuleContext(DimExprContext.class,i);
		}
		public List<DimExprContext> dimExpr() {
			return getRuleContexts(DimExprContext.class);
		}
		public DimExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimExprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterDimExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitDimExprs(this);
		}
	}

	public final DimExprsContext dimExprs() throws RecognitionException {
		DimExprsContext _localctx = new DimExprsContext(_ctx, getState());
		enterRule(_localctx, 420, RULE_dimExprs);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2492); dimExpr();
			setState(2496);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,275,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2493); dimExpr();
					}
					} 
				}
				setState(2498);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,275,_ctx);
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

	public static class DimExprContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DimExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterDimExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitDimExpr(this);
		}
	}

	public final DimExprContext dimExpr() throws RecognitionException {
		DimExprContext _localctx = new DimExprContext(_ctx, getState());
		enterRule(_localctx, 422, RULE_dimExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2502);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(2499); annotation();
				}
				}
				setState(2504);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2505); match(LBRACK);
			setState(2506); expression();
			setState(2507); match(RBRACK);
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

	public static class ConstantExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConstantExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConstantExpression(this);
		}
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 424, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2509); expression();
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
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 426, RULE_expression);
		try {
			setState(2513);
			switch ( getInterpreter().adaptivePredict(_input,277,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2511); lambdaExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2512); assignmentExpression();
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

	public static class LambdaExpressionContext extends ParserRuleContext {
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public LambdaParametersContext lambdaParameters() {
			return getRuleContext(LambdaParametersContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLambdaExpression(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 428, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2515); lambdaParameters();
			setState(2516); match(ARROW);
			setState(2517); lambdaBody();
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
		public InferredFormalParameterListContext inferredFormalParameterList() {
			return getRuleContext(InferredFormalParameterListContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Java8Parser.Identifier, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public LambdaParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLambdaParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLambdaParameters(this);
		}
	}

	public final LambdaParametersContext lambdaParameters() throws RecognitionException {
		LambdaParametersContext _localctx = new LambdaParametersContext(_ctx, getState());
		enterRule(_localctx, 430, RULE_lambdaParameters);
		int _la;
		try {
			setState(2529);
			switch ( getInterpreter().adaptivePredict(_input,279,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2519); match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2520); match(LPAREN);
				setState(2522);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0) || _la==Identifier || _la==AT) {
					{
					setState(2521); formalParameterList();
					}
				}

				setState(2524); match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2525); match(LPAREN);
				setState(2526); inferredFormalParameterList();
				setState(2527); match(RPAREN);
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

	public static class InferredFormalParameterListContext extends ParserRuleContext {
		public TerminalNode Identifier(int i) {
			return getToken(Java8Parser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(Java8Parser.Identifier); }
		public InferredFormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inferredFormalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInferredFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInferredFormalParameterList(this);
		}
	}

	public final InferredFormalParameterListContext inferredFormalParameterList() throws RecognitionException {
		InferredFormalParameterListContext _localctx = new InferredFormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 432, RULE_inferredFormalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2531); match(Identifier);
			setState(2536);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2532); match(COMMA);
				setState(2533); match(Identifier);
				}
				}
				setState(2538);
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
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLambdaBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLambdaBody(this);
		}
	}

	public final LambdaBodyContext lambdaBody() throws RecognitionException {
		LambdaBodyContext _localctx = new LambdaBodyContext(_ctx, getState());
		enterRule(_localctx, 434, RULE_lambdaBody);
		try {
			setState(2541);
			switch (_input.LA(1)) {
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
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(2539); expression();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(2540); block();
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

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAssignmentExpression(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 436, RULE_assignmentExpression);
		try {
			setState(2545);
			switch ( getInterpreter().adaptivePredict(_input,282,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2543); conditionalExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2544); assignment();
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

	public static class AssignmentContext extends ParserRuleContext {
		public LeftHandSideContext leftHandSide() {
			return getRuleContext(LeftHandSideContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 438, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2547); leftHandSide();
			setState(2548); assignmentOperator();
			setState(2549); expression();
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

	public static class LeftHandSideContext extends ParserRuleContext {
		public ArrayAccessContext arrayAccess() {
			return getRuleContext(ArrayAccessContext.class,0);
		}
		public FieldAccessContext fieldAccess() {
			return getRuleContext(FieldAccessContext.class,0);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public LeftHandSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftHandSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterLeftHandSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitLeftHandSide(this);
		}
	}

	public final LeftHandSideContext leftHandSide() throws RecognitionException {
		LeftHandSideContext _localctx = new LeftHandSideContext(_ctx, getState());
		enterRule(_localctx, 440, RULE_leftHandSide);
		try {
			setState(2554);
			switch ( getInterpreter().adaptivePredict(_input,283,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2551); expressionName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2552); fieldAccess();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2553); arrayAccess();
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

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAssignmentOperator(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 442, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2556);
			_la = _input.LA(1);
			if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (ASSIGN - 67)) | (1L << (ADD_ASSIGN - 67)) | (1L << (SUB_ASSIGN - 67)) | (1L << (MUL_ASSIGN - 67)) | (1L << (DIV_ASSIGN - 67)) | (1L << (AND_ASSIGN - 67)) | (1L << (OR_ASSIGN - 67)) | (1L << (XOR_ASSIGN - 67)) | (1L << (MOD_ASSIGN - 67)) | (1L << (LSHIFT_ASSIGN - 67)) | (1L << (RSHIFT_ASSIGN - 67)) | (1L << (URSHIFT_ASSIGN - 67)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class AdditiveOperatorContext extends ParserRuleContext {
		public AdditiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAdditiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAdditiveOperator(this);
		}
	}

	public final AdditiveOperatorContext additiveOperator() throws RecognitionException {
		AdditiveOperatorContext _localctx = new AdditiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 444, RULE_additiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2558);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==SUB) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class RelationalOperatorContext extends ParserRuleContext {
		public RelationalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterRelationalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitRelationalOperator(this);
		}
	}

	public final RelationalOperatorContext relationalOperator() throws RecognitionException {
		RelationalOperatorContext _localctx = new RelationalOperatorContext(_ctx, getState());
		enterRule(_localctx, 446, RULE_relationalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2560);
			_la = _input.LA(1);
			if ( !(((((_la - 27)) & ~0x3f) == 0 && ((1L << (_la - 27)) & ((1L << (INSTANCEOF - 27)) | (1L << (GT - 27)) | (1L << (LT - 27)) | (1L << (LE - 27)) | (1L << (GE - 27)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class MultiplicativeOperatorContext extends ParserRuleContext {
		public MultiplicativeOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMultiplicativeOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMultiplicativeOperator(this);
		}
	}

	public final MultiplicativeOperatorContext multiplicativeOperator() throws RecognitionException {
		MultiplicativeOperatorContext _localctx = new MultiplicativeOperatorContext(_ctx, getState());
		enterRule(_localctx, 448, RULE_multiplicativeOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2562);
			_la = _input.LA(1);
			if ( !(((((_la - 84)) & ~0x3f) == 0 && ((1L << (_la - 84)) & ((1L << (MUL - 84)) | (1L << (DIV - 84)) | (1L << (MOD - 84)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class SquareBracketsContext extends ParserRuleContext {
		public SquareBracketsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareBrackets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterSquareBrackets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitSquareBrackets(this);
		}
	}

	public final SquareBracketsContext squareBrackets() throws RecognitionException {
		SquareBracketsContext _localctx = new SquareBracketsContext(_ctx, getState());
		enterRule(_localctx, 450, RULE_squareBrackets);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2564); match(T__0);
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

	public static class ConditionalExpressionContext extends ParserRuleContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public ConditionalOrExpressionContext conditionalOrExpression() {
			return getRuleContext(ConditionalOrExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionalExpressionContext conditionalExpression() {
			return getRuleContext(ConditionalExpressionContext.class,0);
		}
		public ConditionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConditionalExpression(this);
		}
	}

	public final ConditionalExpressionContext conditionalExpression() throws RecognitionException {
		ConditionalExpressionContext _localctx = new ConditionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 452, RULE_conditionalExpression);
		try {
			setState(2579);
			switch ( getInterpreter().adaptivePredict(_input,284,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2566); conditionalOrExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2567); conditionalOrExpression(0);
				setState(2568); match(QUESTION);
				setState(2569); expression();
				setState(2570); match(COLON);
				setState(2571); conditionalExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2573); conditionalOrExpression(0);
				setState(2574); match(QUESTION);
				setState(2575); expression();
				setState(2576); match(COLON);
				setState(2577); lambdaExpression();
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

	public static class ConditionalOrExpressionContext extends ParserRuleContext {
		public ConditionalOrExpressionContext conditionalOrExpression() {
			return getRuleContext(ConditionalOrExpressionContext.class,0);
		}
		public ConditionalAndExpressionContext conditionalAndExpression() {
			return getRuleContext(ConditionalAndExpressionContext.class,0);
		}
		public ConditionalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConditionalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConditionalOrExpression(this);
		}
	}

	public final ConditionalOrExpressionContext conditionalOrExpression() throws RecognitionException {
		return conditionalOrExpression(0);
	}

	private ConditionalOrExpressionContext conditionalOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionalOrExpressionContext _localctx = new ConditionalOrExpressionContext(_ctx, _parentState);
		ConditionalOrExpressionContext _prevctx = _localctx;
		int _startState = 454;
		enterRecursionRule(_localctx, 454, RULE_conditionalOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2582); conditionalAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2589);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,285,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionalOrExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_conditionalOrExpression);
					setState(2584);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(2585); match(OR);
					setState(2586); conditionalAndExpression(0);
					}
					} 
				}
				setState(2591);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,285,_ctx);
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

	public static class ConditionalAndExpressionContext extends ParserRuleContext {
		public ConditionalAndExpressionContext conditionalAndExpression() {
			return getRuleContext(ConditionalAndExpressionContext.class,0);
		}
		public InclusiveOrExpressionContext inclusiveOrExpression() {
			return getRuleContext(InclusiveOrExpressionContext.class,0);
		}
		public ConditionalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterConditionalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitConditionalAndExpression(this);
		}
	}

	public final ConditionalAndExpressionContext conditionalAndExpression() throws RecognitionException {
		return conditionalAndExpression(0);
	}

	private ConditionalAndExpressionContext conditionalAndExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionalAndExpressionContext _localctx = new ConditionalAndExpressionContext(_ctx, _parentState);
		ConditionalAndExpressionContext _prevctx = _localctx;
		int _startState = 456;
		enterRecursionRule(_localctx, 456, RULE_conditionalAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2593); inclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2600);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,286,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionalAndExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_conditionalAndExpression);
					setState(2595);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(2596); match(AND);
					setState(2597); inclusiveOrExpression(0);
					}
					} 
				}
				setState(2602);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,286,_ctx);
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

	public static class InclusiveOrExpressionContext extends ParserRuleContext {
		public ExclusiveOrExpressionContext exclusiveOrExpression() {
			return getRuleContext(ExclusiveOrExpressionContext.class,0);
		}
		public InclusiveOrExpressionContext inclusiveOrExpression() {
			return getRuleContext(InclusiveOrExpressionContext.class,0);
		}
		public InclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterInclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitInclusiveOrExpression(this);
		}
	}

	public final InclusiveOrExpressionContext inclusiveOrExpression() throws RecognitionException {
		return inclusiveOrExpression(0);
	}

	private InclusiveOrExpressionContext inclusiveOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InclusiveOrExpressionContext _localctx = new InclusiveOrExpressionContext(_ctx, _parentState);
		InclusiveOrExpressionContext _prevctx = _localctx;
		int _startState = 458;
		enterRecursionRule(_localctx, 458, RULE_inclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2604); exclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2611);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,287,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InclusiveOrExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_inclusiveOrExpression);
					setState(2606);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(2607); match(BITOR);
					setState(2608); exclusiveOrExpression(0);
					}
					} 
				}
				setState(2613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,287,_ctx);
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

	public static class ExclusiveOrExpressionContext extends ParserRuleContext {
		public ExclusiveOrExpressionContext exclusiveOrExpression() {
			return getRuleContext(ExclusiveOrExpressionContext.class,0);
		}
		public AndExpressionContext andExpression() {
			return getRuleContext(AndExpressionContext.class,0);
		}
		public ExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterExclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitExclusiveOrExpression(this);
		}
	}

	public final ExclusiveOrExpressionContext exclusiveOrExpression() throws RecognitionException {
		return exclusiveOrExpression(0);
	}

	private ExclusiveOrExpressionContext exclusiveOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExclusiveOrExpressionContext _localctx = new ExclusiveOrExpressionContext(_ctx, _parentState);
		ExclusiveOrExpressionContext _prevctx = _localctx;
		int _startState = 460;
		enterRecursionRule(_localctx, 460, RULE_exclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2615); andExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2622);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,288,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExclusiveOrExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exclusiveOrExpression);
					setState(2617);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(2618); match(CARET);
					setState(2619); andExpression(0);
					}
					} 
				}
				setState(2624);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,288,_ctx);
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

	public static class AndExpressionContext extends ParserRuleContext {
		public AndExpressionContext andExpression() {
			return getRuleContext(AndExpressionContext.class,0);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAndExpression(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		return andExpression(0);
	}

	private AndExpressionContext andExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, _parentState);
		AndExpressionContext _prevctx = _localctx;
		int _startState = 462;
		enterRecursionRule(_localctx, 462, RULE_andExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2626); equalityExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2633);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,289,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_andExpression);
					setState(2628);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(2629); match(BITAND);
					setState(2630); equalityExpression(0);
					}
					} 
				}
				setState(2635);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,289,_ctx);
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

	public static class EqualityExpressionContext extends ParserRuleContext {
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitEqualityExpression(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		return equalityExpression(0);
	}

	private EqualityExpressionContext equalityExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, _parentState);
		EqualityExpressionContext _prevctx = _localctx;
		int _startState = 464;
		enterRecursionRule(_localctx, 464, RULE_equalityExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2637); relationalExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2647);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,291,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(2645);
					switch ( getInterpreter().adaptivePredict(_input,290,_ctx) ) {
					case 1:
						{
						_localctx = new EqualityExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(2639);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(2640); match(EQUAL);
						setState(2641); relationalExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new EqualityExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(2642);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(2643); match(NOTEQUAL);
						setState(2644); relationalExpression(0);
						}
						break;
					}
					} 
				}
				setState(2649);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,291,_ctx);
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

	public static class RelationalExpressionContext extends ParserRuleContext {
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public ShiftExpressionContext shiftExpression() {
			return getRuleContext(ShiftExpressionContext.class,0);
		}
		public RelationalOperatorContext relationalOperator() {
			return getRuleContext(RelationalOperatorContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitRelationalExpression(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		return relationalExpression(0);
	}

	private RelationalExpressionContext relationalExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, _parentState);
		RelationalExpressionContext _prevctx = _localctx;
		int _startState = 466;
		enterRecursionRule(_localctx, 466, RULE_relationalExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2651); shiftExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2675);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,293,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(2673);
					switch ( getInterpreter().adaptivePredict(_input,292,_ctx) ) {
					case 1:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(2653);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(2654); relationalOperator();
						setState(2655); shiftExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(2657);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(2658); relationalOperator();
						setState(2659); shiftExpression(0);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(2661);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(2662); relationalOperator();
						setState(2663); shiftExpression(0);
						}
						break;
					case 4:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(2665);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(2666); relationalOperator();
						setState(2667); shiftExpression(0);
						}
						break;
					case 5:
						{
						_localctx = new RelationalExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationalExpression);
						setState(2669);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(2670); relationalOperator();
						setState(2671); referenceType();
						}
						break;
					}
					} 
				}
				setState(2677);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,293,_ctx);
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

	public static class ShiftExpressionContext extends ParserRuleContext {
		public ShiftExpressionContext shiftExpression() {
			return getRuleContext(ShiftExpressionContext.class,0);
		}
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public ShiftOperatorContext shiftOperator() {
			return getRuleContext(ShiftOperatorContext.class,0);
		}
		public ShiftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitShiftExpression(this);
		}
	}

	public final ShiftExpressionContext shiftExpression() throws RecognitionException {
		return shiftExpression(0);
	}

	private ShiftExpressionContext shiftExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ShiftExpressionContext _localctx = new ShiftExpressionContext(_ctx, _parentState);
		ShiftExpressionContext _prevctx = _localctx;
		int _startState = 468;
		enterRecursionRule(_localctx, 468, RULE_shiftExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2679); additiveExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2695);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,295,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(2693);
					switch ( getInterpreter().adaptivePredict(_input,294,_ctx) ) {
					case 1:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(2681);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(2682); shiftOperator();
						setState(2683); additiveExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(2685);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(2686); shiftOperator();
						setState(2687); additiveExpression(0);
						}
						break;
					case 3:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(2689);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(2690); shiftOperator();
						setState(2691); additiveExpression(0);
						}
						break;
					}
					} 
				}
				setState(2697);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,295,_ctx);
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

	public static class ShiftOperatorContext extends ParserRuleContext {
		public ShiftOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterShiftOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitShiftOperator(this);
		}
	}

	public final ShiftOperatorContext shiftOperator() throws RecognitionException {
		ShiftOperatorContext _localctx = new ShiftOperatorContext(_ctx, getState());
		enterRule(_localctx, 470, RULE_shiftOperator);
		try {
			setState(2705);
			switch ( getInterpreter().adaptivePredict(_input,296,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2698); match(LT);
				setState(2699); match(LT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2700); match(GT);
				setState(2701); match(GT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2702); match(GT);
				setState(2703); match(GT);
				setState(2704); match(GT);
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

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public AdditiveOperatorContext additiveOperator() {
			return getRuleContext(AdditiveOperatorContext.class,0);
		}
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		return additiveExpression(0);
	}

	private AdditiveExpressionContext additiveExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, _parentState);
		AdditiveExpressionContext _prevctx = _localctx;
		int _startState = 472;
		enterRecursionRule(_localctx, 472, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2708); multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(2720);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,298,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(2718);
					switch ( getInterpreter().adaptivePredict(_input,297,_ctx) ) {
					case 1:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(2710);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(2711); additiveOperator();
						setState(2712); multiplicativeExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(2714);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(2715); additiveOperator();
						setState(2716); multiplicativeExpression(0);
						}
						break;
					}
					} 
				}
				setState(2722);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,298,_ctx);
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

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public MultiplicativeOperatorContext multiplicativeOperator() {
			return getRuleContext(MultiplicativeOperatorContext.class,0);
		}
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		return multiplicativeExpression(0);
	}

	private MultiplicativeExpressionContext multiplicativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, _parentState);
		MultiplicativeExpressionContext _prevctx = _localctx;
		int _startState = 474;
		enterRecursionRule(_localctx, 474, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2724); unaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(2740);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,300,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(2738);
					switch ( getInterpreter().adaptivePredict(_input,299,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(2726);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(2727); multiplicativeOperator();
						setState(2728); unaryExpression();
						}
						break;
					case 2:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(2730);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(2731); multiplicativeOperator();
						setState(2732); unaryExpression();
						}
						break;
					case 3:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(2734);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(2735); multiplicativeOperator();
						setState(2736); unaryExpression();
						}
						break;
					}
					} 
				}
				setState(2742);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,300,_ctx);
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

	public static class UnaryExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PreIncrementExpressionContext preIncrementExpression() {
			return getRuleContext(PreIncrementExpressionContext.class,0);
		}
		public AdditiveOperatorContext additiveOperator() {
			return getRuleContext(AdditiveOperatorContext.class,0);
		}
		public PreDecrementExpressionContext preDecrementExpression() {
			return getRuleContext(PreDecrementExpressionContext.class,0);
		}
		public UnaryExpressionNotPlusMinusContext unaryExpressionNotPlusMinus() {
			return getRuleContext(UnaryExpressionNotPlusMinusContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnaryExpression(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 476, RULE_unaryExpression);
		try {
			setState(2752);
			switch ( getInterpreter().adaptivePredict(_input,301,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2743); preIncrementExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2744); preDecrementExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2745); additiveOperator();
				setState(2746); unaryExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2748); additiveOperator();
				setState(2749); unaryExpression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2751); unaryExpressionNotPlusMinus();
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

	public static class PreIncrementExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PreIncrementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preIncrementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPreIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPreIncrementExpression(this);
		}
	}

	public final PreIncrementExpressionContext preIncrementExpression() throws RecognitionException {
		PreIncrementExpressionContext _localctx = new PreIncrementExpressionContext(_ctx, getState());
		enterRule(_localctx, 478, RULE_preIncrementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2754); match(INC);
			setState(2755); unaryExpression();
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

	public static class PreDecrementExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PreDecrementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preDecrementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPreDecrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPreDecrementExpression(this);
		}
	}

	public final PreDecrementExpressionContext preDecrementExpression() throws RecognitionException {
		PreDecrementExpressionContext _localctx = new PreDecrementExpressionContext(_ctx, getState());
		enterRule(_localctx, 480, RULE_preDecrementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2757); match(DEC);
			setState(2758); unaryExpression();
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

	public static class UnaryExpressionNotPlusMinusContext extends ParserRuleContext {
		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public UnaryExpressionNotPlusMinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpressionNotPlusMinus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterUnaryExpressionNotPlusMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitUnaryExpressionNotPlusMinus(this);
		}
	}

	public final UnaryExpressionNotPlusMinusContext unaryExpressionNotPlusMinus() throws RecognitionException {
		UnaryExpressionNotPlusMinusContext _localctx = new UnaryExpressionNotPlusMinusContext(_ctx, getState());
		enterRule(_localctx, 482, RULE_unaryExpressionNotPlusMinus);
		try {
			setState(2766);
			switch ( getInterpreter().adaptivePredict(_input,302,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2760); postfixExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2761); match(TILDE);
				setState(2762); unaryExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2763); match(BANG);
				setState(2764); unaryExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2765); castExpression();
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

	public static class PostfixExpressionContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PostDecrementExpression_lf_postfixExpressionContext postDecrementExpression_lf_postfixExpression(int i) {
			return getRuleContext(PostDecrementExpression_lf_postfixExpressionContext.class,i);
		}
		public List<PostDecrementExpression_lf_postfixExpressionContext> postDecrementExpression_lf_postfixExpression() {
			return getRuleContexts(PostDecrementExpression_lf_postfixExpressionContext.class);
		}
		public List<PostIncrementExpression_lf_postfixExpressionContext> postIncrementExpression_lf_postfixExpression() {
			return getRuleContexts(PostIncrementExpression_lf_postfixExpressionContext.class);
		}
		public PostIncrementExpression_lf_postfixExpressionContext postIncrementExpression_lf_postfixExpression(int i) {
			return getRuleContext(PostIncrementExpression_lf_postfixExpressionContext.class,i);
		}
		public ExpressionNameContext expressionName() {
			return getRuleContext(ExpressionNameContext.class,0);
		}
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPostfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPostfixExpression(this);
		}
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 484, RULE_postfixExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2770);
			switch ( getInterpreter().adaptivePredict(_input,303,_ctx) ) {
			case 1:
				{
				setState(2768); primary();
				}
				break;
			case 2:
				{
				setState(2769); expressionName();
				}
				break;
			}
			setState(2776);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,305,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(2774);
					switch (_input.LA(1)) {
					case INC:
						{
						setState(2772); postIncrementExpression_lf_postfixExpression();
						}
						break;
					case DEC:
						{
						setState(2773); postDecrementExpression_lf_postfixExpression();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(2778);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,305,_ctx);
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

	public static class PostIncrementExpressionContext extends ParserRuleContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public PostIncrementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postIncrementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPostIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPostIncrementExpression(this);
		}
	}

	public final PostIncrementExpressionContext postIncrementExpression() throws RecognitionException {
		PostIncrementExpressionContext _localctx = new PostIncrementExpressionContext(_ctx, getState());
		enterRule(_localctx, 486, RULE_postIncrementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2779); postfixExpression();
			setState(2780); match(INC);
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

	public static class PostIncrementExpression_lf_postfixExpressionContext extends ParserRuleContext {
		public PostIncrementExpression_lf_postfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postIncrementExpression_lf_postfixExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPostIncrementExpression_lf_postfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPostIncrementExpression_lf_postfixExpression(this);
		}
	}

	public final PostIncrementExpression_lf_postfixExpressionContext postIncrementExpression_lf_postfixExpression() throws RecognitionException {
		PostIncrementExpression_lf_postfixExpressionContext _localctx = new PostIncrementExpression_lf_postfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 488, RULE_postIncrementExpression_lf_postfixExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2782); match(INC);
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

	public static class PostDecrementExpressionContext extends ParserRuleContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public PostDecrementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postDecrementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPostDecrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPostDecrementExpression(this);
		}
	}

	public final PostDecrementExpressionContext postDecrementExpression() throws RecognitionException {
		PostDecrementExpressionContext _localctx = new PostDecrementExpressionContext(_ctx, getState());
		enterRule(_localctx, 490, RULE_postDecrementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2784); postfixExpression();
			setState(2785); match(DEC);
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

	public static class PostDecrementExpression_lf_postfixExpressionContext extends ParserRuleContext {
		public PostDecrementExpression_lf_postfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postDecrementExpression_lf_postfixExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterPostDecrementExpression_lf_postfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitPostDecrementExpression_lf_postfixExpression(this);
		}
	}

	public final PostDecrementExpression_lf_postfixExpressionContext postDecrementExpression_lf_postfixExpression() throws RecognitionException {
		PostDecrementExpression_lf_postfixExpressionContext _localctx = new PostDecrementExpression_lf_postfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 492, RULE_postDecrementExpression_lf_postfixExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2787); match(DEC);
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

	public static class CastExpressionContext extends ParserRuleContext {
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public List<AdditionalBoundContext> additionalBound() {
			return getRuleContexts(AdditionalBoundContext.class);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public AdditionalBoundContext additionalBound(int i) {
			return getRuleContext(AdditionalBoundContext.class,i);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public UnaryExpressionNotPlusMinusContext unaryExpressionNotPlusMinus() {
			return getRuleContext(UnaryExpressionNotPlusMinusContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public CastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).enterCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Java8Listener ) ((Java8Listener)listener).exitCastExpression(this);
		}
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 494, RULE_castExpression);
		int _la;
		try {
			setState(2816);
			switch ( getInterpreter().adaptivePredict(_input,308,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2789); match(LPAREN);
				setState(2790); primitiveType();
				setState(2791); match(RPAREN);
				setState(2792); unaryExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2794); match(LPAREN);
				setState(2795); referenceType();
				setState(2799);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BITAND) {
					{
					{
					setState(2796); additionalBound();
					}
					}
					setState(2801);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2802); match(RPAREN);
				setState(2803); unaryExpressionNotPlusMinus();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2805); match(LPAREN);
				setState(2806); referenceType();
				setState(2810);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==BITAND) {
					{
					{
					setState(2807); additionalBound();
					}
					}
					setState(2812);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2813); match(RPAREN);
				setState(2814); lambdaExpression();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26: return packageName_sempred((PackageNameContext)_localctx, predIndex);
		case 28: return packageOrTypeName_sempred((PackageOrTypeNameContext)_localctx, predIndex);
		case 31: return ambiguousName_sempred((AmbiguousNameContext)_localctx, predIndex);
		case 227: return conditionalOrExpression_sempred((ConditionalOrExpressionContext)_localctx, predIndex);
		case 228: return conditionalAndExpression_sempred((ConditionalAndExpressionContext)_localctx, predIndex);
		case 229: return inclusiveOrExpression_sempred((InclusiveOrExpressionContext)_localctx, predIndex);
		case 230: return exclusiveOrExpression_sempred((ExclusiveOrExpressionContext)_localctx, predIndex);
		case 231: return andExpression_sempred((AndExpressionContext)_localctx, predIndex);
		case 232: return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 233: return relationalExpression_sempred((RelationalExpressionContext)_localctx, predIndex);
		case 234: return shiftExpression_sempred((ShiftExpressionContext)_localctx, predIndex);
		case 236: return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 237: return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean andExpression_sempred(AndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean exclusiveOrExpression_sempred(ExclusiveOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean inclusiveOrExpression_sempred(InclusiveOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean equalityExpression_sempred(EqualityExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8: return precpred(_ctx, 2);
		case 9: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean packageName_sempred(PackageNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean ambiguousName_sempred(AmbiguousNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean relationalExpression_sempred(RelationalExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10: return precpred(_ctx, 5);
		case 11: return precpred(_ctx, 4);
		case 12: return precpred(_ctx, 3);
		case 13: return precpred(_ctx, 2);
		case 14: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean conditionalAndExpression_sempred(ConditionalAndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean packageOrTypeName_sempred(PackageOrTypeNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean shiftExpression_sempred(ShiftExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17: return precpred(_ctx, 1);
		case 16: return precpred(_ctx, 2);
		case 15: return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean conditionalOrExpression_sempred(ConditionalOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean additiveExpression_sempred(AdditiveExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 19: return precpred(_ctx, 1);
		case 18: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean multiplicativeExpression_sempred(MultiplicativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 21: return precpred(_ctx, 2);
		case 20: return precpred(_ctx, 3);
		case 22: return precpred(_ctx, 1);
		}
		return true;
	}

	private static final int _serializedATNSegments = 2;
	private static final String _serializedATNSegment0 =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3o\u0b05\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
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
		"\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d\4\u008e"+
		"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092\t\u0092"+
		"\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096\4\u0097"+
		"\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b\t\u009b"+
		"\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f\4\u00a0"+
		"\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4\t\u00a4"+
		"\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8\4\u00a9"+
		"\t\u00a9\4\u00aa\t\u00aa\4\u00ab\t\u00ab\4\u00ac\t\u00ac\4\u00ad\t\u00ad"+
		"\4\u00ae\t\u00ae\4\u00af\t\u00af\4\u00b0\t\u00b0\4\u00b1\t\u00b1\4\u00b2"+
		"\t\u00b2\4\u00b3\t\u00b3\4\u00b4\t\u00b4\4\u00b5\t\u00b5\4\u00b6\t\u00b6"+
		"\4\u00b7\t\u00b7\4\u00b8\t\u00b8\4\u00b9\t\u00b9\4\u00ba\t\u00ba\4\u00bb"+
		"\t\u00bb\4\u00bc\t\u00bc\4\u00bd\t\u00bd\4\u00be\t\u00be\4\u00bf\t\u00bf"+
		"\4\u00c0\t\u00c0\4\u00c1\t\u00c1\4\u00c2\t\u00c2\4\u00c3\t\u00c3\4\u00c4"+
		"\t\u00c4\4\u00c5\t\u00c5\4\u00c6\t\u00c6\4\u00c7\t\u00c7\4\u00c8\t\u00c8"+
		"\4\u00c9\t\u00c9\4\u00ca\t\u00ca\4\u00cb\t\u00cb\4\u00cc\t\u00cc\4\u00cd"+
		"\t\u00cd\4\u00ce\t\u00ce\4\u00cf\t\u00cf\4\u00d0\t\u00d0\4\u00d1\t\u00d1"+
		"\4\u00d2\t\u00d2\4\u00d3\t\u00d3\4\u00d4\t\u00d4\4\u00d5\t\u00d5\4\u00d6"+
		"\t\u00d6\4\u00d7\t\u00d7\4\u00d8\t\u00d8\4\u00d9\t\u00d9\4\u00da\t\u00da"+
		"\4\u00db\t\u00db\4\u00dc\t\u00dc\4\u00dd\t\u00dd\4\u00de\t\u00de\4\u00df"+
		"\t\u00df\4\u00e0\t\u00e0\4\u00e1\t\u00e1\4\u00e2\t\u00e2\4\u00e3\t\u00e3"+
		"\4\u00e4\t\u00e4\4\u00e5\t\u00e5\4\u00e6\t\u00e6\4\u00e7\t\u00e7\4\u00e8"+
		"\t\u00e8\4\u00e9\t\u00e9\4\u00ea\t\u00ea\4\u00eb\t\u00eb\4\u00ec\t\u00ec"+
		"\4\u00ed\t\u00ed\4\u00ee\t\u00ee\4\u00ef\t\u00ef\4\u00f0\t\u00f0\4\u00f1"+
		"\t\u00f1\4\u00f2\t\u00f2\4\u00f3\t\u00f3\4\u00f4\t\u00f4\4\u00f5\t\u00f5"+
		"\4\u00f6\t\u00f6\4\u00f7\t\u00f7\4\u00f8\t\u00f8\4\u00f9\t\u00f9\3\2\3"+
		"\2\3\3\3\3\5\3\u01f7\n\3\3\4\7\4\u01fa\n\4\f\4\16\4\u01fd\13\4\3\4\3\4"+
		"\7\4\u0201\n\4\f\4\16\4\u0204\13\4\3\4\5\4\u0207\n\4\3\5\3\5\5\5\u020b"+
		"\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\5\b\u0214\n\b\3\t\3\t\5\t\u0218\n\t\3"+
		"\t\3\t\7\t\u021c\n\t\f\t\16\t\u021f\13\t\3\n\3\n\5\n\u0223\n\n\3\n\3\n"+
		"\3\n\3\n\5\n\u0229\n\n\5\n\u022b\n\n\3\13\3\13\3\13\5\13\u0230\n\13\3"+
		"\f\3\f\5\f\u0234\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0247\n\21\3\22\3\22\7\22\u024b"+
		"\n\22\f\22\16\22\u024e\13\22\3\23\7\23\u0251\n\23\f\23\16\23\u0254\13"+
		"\23\3\23\3\23\5\23\u0258\n\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u0261\n\25\f\25\16\25\u0264\13\25\5\25\u0266\n\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\7\30\u0272\n\30\f\30\16\30\u0275\13\30"+
		"\3\31\3\31\5\31\u0279\n\31\3\32\7\32\u027c\n\32\f\32\16\32\u027f\13\32"+
		"\3\32\3\32\5\32\u0283\n\32\3\33\3\33\3\33\3\33\5\33\u0289\n\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\7\34\u0291\n\34\f\34\16\34\u0294\13\34\3\35\3"+
		"\35\3\35\3\35\3\35\5\35\u029b\n\35\3\36\3\36\3\36\3\36\3\36\3\36\7\36"+
		"\u02a3\n\36\f\36\16\36\u02a6\13\36\3\37\3\37\3\37\3\37\3\37\5\37\u02ad"+
		"\n\37\3 \3 \3!\3!\3!\3!\3!\3!\7!\u02b7\n!\f!\16!\u02ba\13!\3\"\5\"\u02bd"+
		"\n\"\3\"\7\"\u02c0\n\"\f\"\16\"\u02c3\13\"\3\"\7\"\u02c6\n\"\f\"\16\""+
		"\u02c9\13\"\3\"\3\"\3#\7#\u02ce\n#\f#\16#\u02d1\13#\3#\3#\3#\3#\7#\u02d7"+
		"\n#\f#\16#\u02da\13#\3#\3#\3$\3$\3%\3%\3%\3%\5%\u02e4\n%\3&\3&\3&\3&\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3*\3"+
		"*\3*\5*\u0301\n*\3+\3+\5+\u0305\n+\3,\3,\3,\3,\5,\u030b\n,\3,\5,\u030e"+
		"\n,\3,\5,\u0311\n,\3,\3,\3-\7-\u0316\n-\f-\16-\u0319\13-\3.\3.\3.\3.\3"+
		".\3.\3.\3.\5.\u0323\n.\3/\3/\3/\3/\3\60\3\60\3\60\7\60\u032c\n\60\f\60"+
		"\16\60\u032f\13\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\7\63\u033a"+
		"\n\63\f\63\16\63\u033d\13\63\3\64\3\64\7\64\u0341\n\64\f\64\16\64\u0344"+
		"\13\64\3\64\3\64\3\65\3\65\3\65\3\65\5\65\u034c\n\65\3\66\3\66\3\66\3"+
		"\66\3\66\5\66\u0353\n\66\3\67\3\67\3\67\3\67\3\67\38\78\u035b\n8\f8\16"+
		"8\u035e\138\39\39\39\39\39\39\39\39\59\u0368\n9\3:\3:\3:\7:\u036d\n:\f"+
		":\16:\u0370\13:\3;\3;\3;\5;\u0375\n;\3<\3<\5<\u0379\n<\3=\3=\5=\u037d"+
		"\n=\3>\3>\5>\u0381\n>\3?\3?\5?\u0385\n?\3@\3@\3@\5@\u038a\n@\3A\3A\5A"+
		"\u038e\nA\3A\3A\7A\u0392\nA\fA\16A\u0395\13A\3B\3B\5B\u0399\nB\3B\3B\3"+
		"B\3B\5B\u039f\nB\5B\u03a1\nB\3C\3C\3C\5C\u03a6\nC\3D\3D\5D\u03aa\nD\3"+
		"E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3I\3I\3I\3I\3I\3I\3I\5I\u03bd\nI\3J\3J\3"+
		"J\3J\3K\7K\u03c4\nK\fK\16K\u03c7\13K\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\5L"+
		"\u03d3\nL\3M\3M\3M\5M\u03d8\nM\3M\3M\7M\u03dc\nM\fM\16M\u03df\13M\3M\3"+
		"M\3M\5M\u03e4\nM\5M\u03e6\nM\3N\3N\5N\u03ea\nN\3O\3O\3O\5O\u03ef\nO\3"+
		"O\3O\5O\u03f3\nO\3P\3P\3P\3P\3P\5P\u03fa\nP\3Q\3Q\3Q\7Q\u03ff\nQ\fQ\16"+
		"Q\u0402\13Q\3Q\3Q\3Q\7Q\u0407\nQ\fQ\16Q\u040a\13Q\5Q\u040c\nQ\3R\7R\u040f"+
		"\nR\fR\16R\u0412\13R\3R\3R\3R\3S\3S\5S\u0419\nS\3T\7T\u041c\nT\fT\16T"+
		"\u041f\13T\3T\3T\7T\u0423\nT\fT\16T\u0426\13T\3T\3T\3T\3T\5T\u042c\nT"+
		"\3U\7U\u042f\nU\fU\16U\u0432\13U\3U\3U\3U\5U\u0437\nU\3U\3U\3V\3V\3V\3"+
		"W\3W\3W\7W\u0441\nW\fW\16W\u0444\13W\3X\3X\5X\u0448\nX\3Y\3Y\5Y\u044c"+
		"\nY\3Z\3Z\3[\3[\3[\3\\\3\\\3\\\5\\\u0456\n\\\3\\\3\\\3]\7]\u045b\n]\f"+
		"]\16]\u045e\13]\3^\3^\3^\3^\5^\u0464\n^\3_\5_\u0467\n_\3_\3_\3_\5_\u046c"+
		"\n_\3_\3_\3`\3`\3a\3a\5a\u0474\na\3a\5a\u0477\na\3a\3a\3b\5b\u047c\nb"+
		"\3b\3b\3b\5b\u0481\nb\3b\3b\3b\5b\u0486\nb\3b\3b\3b\5b\u048b\nb\3b\3b"+
		"\3b\3b\3b\5b\u0492\nb\3b\3b\3b\5b\u0497\nb\3b\3b\3b\3b\3b\3b\5b\u049f"+
		"\nb\3b\3b\3b\5b\u04a4\nb\3b\3b\3b\5b\u04a9\nb\3c\3c\3c\3c\5c\u04af\nc"+
		"\3c\3c\3d\3d\5d\u04b5\nd\3d\5d\u04b8\nd\3d\5d\u04bb\nd\3d\3d\3e\3e\3e"+
		"\7e\u04c2\ne\fe\16e\u04c5\13e\3f\7f\u04c8\nf\ff\16f\u04cb\13f\3f\3f\3"+
		"f\5f\u04d0\nf\3f\5f\u04d3\nf\3f\5f\u04d6\nf\3g\3g\3h\3h\7h\u04dc\nh\f"+
		"h\16h\u04df\13h\3i\3i\5i\u04e3\ni\3j\7j\u04e6\nj\fj\16j\u04e9\13j\3j\3"+
		"j\3j\5j\u04ee\nj\3j\5j\u04f1\nj\3j\3j\3k\3k\3k\3k\3k\3k\3k\5k\u04fc\n"+
		"k\3l\3l\3l\3m\3m\7m\u0503\nm\fm\16m\u0506\13m\3m\3m\3n\3n\3n\3n\3n\5n"+
		"\u050f\nn\3o\7o\u0512\no\fo\16o\u0515\13o\3o\3o\3o\3o\3p\3p\3p\3p\5p\u051f"+
		"\np\3q\7q\u0522\nq\fq\16q\u0525\13q\3q\3q\3q\3r\3r\3r\3r\3r\3r\5r\u0530"+
		"\nr\3s\7s\u0533\ns\fs\16s\u0536\13s\3s\3s\3s\3s\3s\3t\3t\7t\u053f\nt\f"+
		"t\16t\u0542\13t\3t\3t\3u\3u\3u\3u\3u\5u\u054b\nu\3v\7v\u054e\nv\fv\16"+
		"v\u0551\13v\3v\3v\3v\3v\3v\5v\u0558\nv\3v\5v\u055b\nv\3v\3v\3w\3w\3w\5"+
		"w\u0562\nw\3x\3x\3x\3y\3y\3y\5y\u056a\ny\3z\7z\u056d\nz\fz\16z\u0570\13"+
		"z\3z\3z\3{\7{\u0575\n{\f{\16{\u0578\13{\3{\3{\3|\3|\3|\3|\5|\u0580\n|"+
		"\3|\3|\3}\3}\3}\7}\u0587\n}\f}\16}\u058a\13}\3~\3~\3~\3~\3\177\3\177\3"+
		"\177\5\177\u0593\n\177\3\u0080\3\u0080\5\u0080\u0597\n\u0080\3\u0080\5"+
		"\u0080\u059a\n\u0080\3\u0080\3\u0080\3\u0081\3\u0081\3\u0081\7\u0081\u05a1"+
		"\n\u0081\f\u0081\16\u0081\u05a4\13\u0081\3\u0082\3\u0082\3\u0082\3\u0083"+
		"\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0084\3\u0084\5\u0084\u05b1"+
		"\n\u0084\3\u0084\5\u0084\u05b4\n\u0084\3\u0084\3\u0084\3\u0085\3\u0085"+
		"\3\u0085\7\u0085\u05bb\n\u0085\f\u0085\16\u0085\u05be\13\u0085\3\u0086"+
		"\3\u0086\5\u0086\u05c2\n\u0086\3\u0086\3\u0086\3\u0087\3\u0087\7\u0087"+
		"\u05c8\n\u0087\f\u0087\16\u0087\u05cb\13\u0087\3\u0088\3\u0088\3\u0088"+
		"\5\u0088\u05d0\n\u0088\3\u0089\3\u0089\3\u0089\3\u008a\7\u008a\u05d6\n"+
		"\u008a\f\u008a\16\u008a\u05d9\13\u008a\3\u008a\3\u008a\3\u008a\3\u008b"+
		"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\5\u008b\u05e4\n\u008b\3\u008c"+
		"\3\u008c\3\u008c\3\u008c\3\u008c\5\u008c\u05eb\n\u008c\3\u008d\3\u008d"+
		"\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d"+
		"\3\u008d\5\u008d\u05f9\n\u008d\3\u008e\3\u008e\3\u008f\3\u008f\3\u008f"+
		"\3\u008f\3\u0090\3\u0090\3\u0090\3\u0090\3\u0091\3\u0091\3\u0091\3\u0092"+
		"\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\5\u0092\u060f\n\u0092"+
		"\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095\3\u0095"+
		"\3\u0095\3\u0095\3\u0095\3\u0095\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096"+
		"\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\5\u0096\u0631\n\u0096\3\u0097"+
		"\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0098\3\u0098\7\u0098\u063b"+
		"\n\u0098\f\u0098\16\u0098\u063e\13\u0098\3\u0098\7\u0098\u0641\n\u0098"+
		"\f\u0098\16\u0098\u0644\13\u0098\3\u0098\3\u0098\3\u0099\3\u0099\3\u0099"+
		"\3\u009a\3\u009a\7\u009a\u064d\n\u009a\f\u009a\16\u009a\u0650\13\u009a"+
		"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b"+
		"\3\u009b\5\u009b\u065c\n\u009b\3\u009c\3\u009c\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\3\u009d\3\u009d\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e"+
		"\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u00a0"+
		"\3\u00a0\5\u00a0\u0676\n\u00a0\3\u00a1\3\u00a1\5\u00a1\u067a\n\u00a1\3"+
		"\u00a2\3\u00a2\3\u00a2\5\u00a2\u067f\n\u00a2\3\u00a2\3\u00a2\5\u00a2\u0683"+
		"\n\u00a2\3\u00a2\3\u00a2\5\u00a2\u0687\n\u00a2\3\u00a2\3\u00a2\3\u00a2"+
		"\3\u00a3\3\u00a3\3\u00a3\5\u00a3\u068f\n\u00a3\3\u00a3\3\u00a3\5\u00a3"+
		"\u0693\n\u00a3\3\u00a3\3\u00a3\5\u00a3\u0697\n\u00a3\3\u00a3\3\u00a3\3"+
		"\u00a3\3\u00a4\3\u00a4\5\u00a4\u069e\n\u00a4\3\u00a5\3\u00a5\3\u00a6\3"+
		"\u00a6\3\u00a6\7\u00a6\u06a5\n\u00a6\f\u00a6\16\u00a6\u06a8\13\u00a6\3"+
		"\u00a7\3\u00a7\3\u00a7\7\u00a7\u06ad\n\u00a7\f\u00a7\16\u00a7\u06b0\13"+
		"\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a8"+
		"\3\u00a8\3\u00a8\7\u00a8\u06bc\n\u00a8\f\u00a8\16\u00a8\u06bf\13\u00a8"+
		"\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a9\3\u00a9"+
		"\5\u00a9\u06ca\n\u00a9\3\u00a9\3\u00a9\3\u00aa\3\u00aa\5\u00aa\u06d0\n"+
		"\u00aa\3\u00aa\3\u00aa\3\u00ab\3\u00ab\5\u00ab\u06d6\n\u00ab\3\u00ab\3"+
		"\u00ab\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ad\3\u00ad\3\u00ad\3\u00ad"+
		"\3\u00ad\3\u00ad\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae"+
		"\5\u00ae\u06eb\n\u00ae\3\u00ae\3\u00ae\3\u00ae\5\u00ae\u06f0\n\u00ae\3"+
		"\u00af\3\u00af\7\u00af\u06f4\n\u00af\f\u00af\16\u00af\u06f7\13\u00af\3"+
		"\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b1\7\u00b1\u0700\n"+
		"\u00b1\f\u00b1\16\u00b1\u0703\13\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b2"+
		"\3\u00b2\3\u00b2\7\u00b2\u070b\n\u00b2\f\u00b2\16\u00b2\u070e\13\u00b2"+
		"\3\u00b3\3\u00b3\3\u00b3\3\u00b4\3\u00b4\3\u00b4\3\u00b4\5\u00b4\u0717"+
		"\n\u00b4\3\u00b4\5\u00b4\u071a\n\u00b4\3\u00b5\3\u00b5\3\u00b5\5\u00b5"+
		"\u071f\n\u00b5\3\u00b5\3\u00b5\3\u00b6\3\u00b6\3\u00b6\7\u00b6\u0726\n"+
		"\u00b6\f\u00b6\16\u00b6\u0729\13\u00b6\3\u00b7\7\u00b7\u072c\n\u00b7\f"+
		"\u00b7\16\u00b7\u072f\13\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7"+
		"\3\u00b8\3\u00b8\5\u00b8\u0738\n\u00b8\3\u00b8\7\u00b8\u073b\n\u00b8\f"+
		"\u00b8\16\u00b8\u073e\13\u00b8\3\u00b9\3\u00b9\3\u00b9\7\u00b9\u0743\n"+
		"\u00b9\f\u00b9\16\u00b9\u0746\13\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9"+
		"\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9"+
		"\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\5\u00b9\u075c"+
		"\n\u00b9\3\u00ba\3\u00ba\3\u00bb\3\u00bb\3\u00bb\7\u00bb\u0763\n\u00bb"+
		"\f\u00bb\16\u00bb\u0766\13\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb"+
		"\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb"+
		"\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\5\u00bb\u077b\n\u00bb\3\u00bc"+
		"\3\u00bc\3\u00bc\3\u00bc\3\u00bc\5\u00bc\u0782\n\u00bc\3\u00bd\3\u00bd"+
		"\3\u00be\3\u00be\3\u00be\3\u00be\5\u00be\u078a\n\u00be\3\u00bf\3\u00bf"+
		"\3\u00bf\7\u00bf\u078f\n\u00bf\f\u00bf\16\u00bf\u0792\13\u00bf\3\u00bf"+
		"\3\u00bf\3\u00bf\3\u00bf\3\u00bf\7\u00bf\u0799\n\u00bf\f\u00bf\16\u00bf"+
		"\u079c\13\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf"+
		"\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf"+
		"\3\u00bf\3\u00bf\3\u00bf\3\u00bf\5\u00bf\u07b2\n\u00bf\3\u00c0\3\u00c0"+
		"\3\u00c1\3\u00c1\3\u00c1\7\u00c1\u07b9\n\u00c1\f\u00c1\16\u00c1\u07bc"+
		"\13\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\7\u00c1\u07c3\n\u00c1"+
		"\f\u00c1\16\u00c1\u07c6\13\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1"+
		"\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1"+
		"\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\5\u00c1\u07db\n\u00c1\3\u00c2"+
		"\3\u00c2\5\u00c2\u07df\n\u00c2\3\u00c2\3\u00c2\3\u00c2\7\u00c2\u07e4\n"+
		"\u00c2\f\u00c2\16\u00c2\u07e7\13\u00c2\3\u00c2\5\u00c2\u07ea\n\u00c2\3"+
		"\u00c2\3\u00c2\5\u00c2\u07ee\n\u00c2\3\u00c2\3\u00c2\5\u00c2\u07f2\n\u00c2"+
		"\3\u00c2\3\u00c2\3\u00c2\3\u00c2\5\u00c2\u07f8\n\u00c2\3\u00c2\3\u00c2"+
		"\5\u00c2\u07fc\n\u00c2\3\u00c2\3\u00c2\5\u00c2\u0800\n\u00c2\3\u00c2\3"+
		"\u00c2\5\u00c2\u0804\n\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\5\u00c2\u080a"+
		"\n\u00c2\3\u00c2\3\u00c2\5\u00c2\u080e\n\u00c2\3\u00c2\3\u00c2\5\u00c2"+
		"\u0812\n\u00c2\3\u00c2\3\u00c2\5\u00c2\u0816\n\u00c2\5\u00c2\u0818\n\u00c2"+
		"\3\u00c3\3\u00c3\3\u00c3\5\u00c3\u081d\n\u00c3\3\u00c3\3\u00c3\5\u00c3"+
		"\u0821\n\u00c3\3\u00c3\3\u00c3\5\u00c3\u0825\n\u00c3\3\u00c3\3\u00c3\5"+
		"\u00c3\u0829\n\u00c3\3\u00c4\3\u00c4\5\u00c4\u082d\n\u00c4\3\u00c4\3\u00c4"+
		"\3\u00c4\7\u00c4\u0832\n\u00c4\f\u00c4\16\u00c4\u0835\13\u00c4\3\u00c4"+
		"\5\u00c4\u0838\n\u00c4\3\u00c4\3\u00c4\5\u00c4\u083c\n\u00c4\3\u00c4\3"+
		"\u00c4\5\u00c4\u0840\n\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\5\u00c4\u0846"+
		"\n\u00c4\3\u00c4\3\u00c4\5\u00c4\u084a\n\u00c4\3\u00c4\3\u00c4\5\u00c4"+
		"\u084e\n\u00c4\3\u00c4\3\u00c4\5\u00c4\u0852\n\u00c4\5\u00c4\u0854\n\u00c4"+
		"\3\u00c5\3\u00c5\3\u00c5\5\u00c5\u0859\n\u00c5\3\u00c6\3\u00c6\3\u00c6"+
		"\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c6"+
		"\3\u00c6\5\u00c6\u0868\n\u00c6\3\u00c7\3\u00c7\3\u00c7\3\u00c8\3\u00c8"+
		"\3\u00c8\3\u00c8\3\u00c8\3\u00c8\3\u00c8\3\u00c8\3\u00c8\5\u00c8\u0876"+
		"\n\u00c8\3\u00c9\3\u00c9\3\u00c9\3\u00c9\3\u00c9\3\u00c9\3\u00c9\3\u00c9"+
		"\3\u00c9\3\u00c9\5\u00c9\u0882\n\u00c9\3\u00c9\3\u00c9\3\u00c9\3\u00c9"+
		"\3\u00c9\7\u00c9\u0889\n\u00c9\f\u00c9\16\u00c9\u088c\13\u00c9\3\u00ca"+
		"\3\u00ca\3\u00ca\3\u00ca\3\u00ca\3\u00ca\3\u00ca\3\u00ca\3\u00ca\3\u00ca"+
		"\7\u00ca\u0898\n\u00ca\f\u00ca\16\u00ca\u089b\13\u00ca\3\u00cb\3\u00cb"+
		"\3\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cb\5\u00cb"+
		"\u08a7\n\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cb\7\u00cb\u08ae\n"+
		"\u00cb\f\u00cb\16\u00cb\u08b1\13\u00cb\3\u00cc\3\u00cc\3\u00cc\5\u00cc"+
		"\u08b6\n\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u08bd\n"+
		"\u00cc\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u08c2\n\u00cc\3\u00cc\3\u00cc\3"+
		"\u00cc\3\u00cc\3\u00cc\5\u00cc\u08c9\n\u00cc\3\u00cc\3\u00cc\3\u00cc\5"+
		"\u00cc\u08ce\n\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u08d5"+
		"\n\u00cc\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u08da\n\u00cc\3\u00cc\3\u00cc"+
		"\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u08e1\n\u00cc\3\u00cc\3\u00cc\3\u00cc"+
		"\5\u00cc\u08e6\n\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc\3\u00cc"+
		"\5\u00cc\u08ee\n\u00cc\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u08f3\n\u00cc\3"+
		"\u00cc\3\u00cc\5\u00cc\u08f7\n\u00cc\3\u00cd\3\u00cd\5\u00cd\u08fb\n\u00cd"+
		"\3\u00cd\3\u00cd\3\u00cd\5\u00cd\u0900\n\u00cd\3\u00cd\3\u00cd\3\u00ce"+
		"\3\u00ce\3\u00ce\5\u00ce\u0907\n\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce"+
		"\3\u00ce\5\u00ce\u090e\n\u00ce\3\u00ce\3\u00ce\3\u00ce\5\u00ce\u0913\n"+
		"\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce\5\u00ce\u091a\n\u00ce\3"+
		"\u00ce\3\u00ce\3\u00ce\5\u00ce\u091f\n\u00ce\3\u00ce\3\u00ce\3\u00ce\3"+
		"\u00ce\3\u00ce\5\u00ce\u0926\n\u00ce\3\u00ce\3\u00ce\3\u00ce\5\u00ce\u092b"+
		"\n\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce\5\u00ce\u0933"+
		"\n\u00ce\3\u00ce\3\u00ce\3\u00ce\5\u00ce\u0938\n\u00ce\3\u00ce\3\u00ce"+
		"\5\u00ce\u093c\n\u00ce\3\u00cf\3\u00cf\3\u00cf\7\u00cf\u0941\n\u00cf\f"+
		"\u00cf\16\u00cf\u0944\13\u00cf\3\u00d0\3\u00d0\3\u00d0\5\u00d0\u0949\n"+
		"\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\5\u00d0\u0950\n\u00d0\3"+
		"\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\5\u00d0\u0957\n\u00d0\3\u00d0\3"+
		"\u00d0\3\u00d0\3\u00d0\3\u00d0\5\u00d0\u095e\n\u00d0\3\u00d0\3\u00d0\3"+
		"\u00d0\3\u00d0\3\u00d0\3\u00d0\5\u00d0\u0966\n\u00d0\3\u00d0\3\u00d0\3"+
		"\u00d0\3\u00d0\3\u00d0\5\u00d0\u096d\n\u00d0\3\u00d0\3\u00d0\3\u00d0\3"+
		"\u00d0\3\u00d0\3\u00d0\5\u00d0\u0975\n\u00d0\3\u00d1\3\u00d1\5\u00d1\u0979"+
		"\n\u00d1\3\u00d1\3\u00d1\3\u00d2\3\u00d2\3\u00d2\5\u00d2\u0980\n\u00d2"+
		"\3\u00d2\3\u00d2\3\u00d2\3\u00d2\3\u00d2\5\u00d2\u0987\n\u00d2\3\u00d2"+
		"\3\u00d2\3\u00d2\3\u00d2\3\u00d2\5\u00d2\u098e\n\u00d2\3\u00d2\3\u00d2"+
		"\3\u00d2\3\u00d2\3\u00d2\3\u00d2\5\u00d2\u0996\n\u00d2\3\u00d2\3\u00d2"+
		"\3\u00d2\3\u00d2\3\u00d2\5\u00d2\u099d\n\u00d2\3\u00d2\3\u00d2\3\u00d2"+
		"\3\u00d2\3\u00d2\3\u00d2\5\u00d2\u09a5\n\u00d2\3\u00d3\3\u00d3\3\u00d3"+
		"\3\u00d3\5\u00d3\u09ab\n\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\5\u00d3"+
		"\u09b1\n\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3"+
		"\3\u00d3\3\u00d3\3\u00d3\5\u00d3\u09bd\n\u00d3\3\u00d4\3\u00d4\7\u00d4"+
		"\u09c1\n\u00d4\f\u00d4\16\u00d4\u09c4\13\u00d4\3\u00d5\7\u00d5\u09c7\n"+
		"\u00d5\f\u00d5\16\u00d5\u09ca\13\u00d5\3\u00d5\3\u00d5\3\u00d5\3\u00d5"+
		"\3\u00d6\3\u00d6\3\u00d7\3\u00d7\5\u00d7\u09d4\n\u00d7\3\u00d8\3\u00d8"+
		"\3\u00d8\3\u00d8\3\u00d9\3\u00d9\3\u00d9\5\u00d9\u09dd\n\u00d9\3\u00d9"+
		"\3\u00d9\3\u00d9\3\u00d9\3\u00d9\5\u00d9\u09e4\n\u00d9\3\u00da\3\u00da"+
		"\3\u00da\7\u00da\u09e9\n\u00da\f\u00da\16\u00da\u09ec\13\u00da\3\u00db"+
		"\3\u00db\5\u00db\u09f0\n\u00db\3\u00dc\3\u00dc\5\u00dc\u09f4\n\u00dc\3"+
		"\u00dd\3\u00dd\3\u00dd\3\u00dd\3\u00de\3\u00de\3\u00de\5\u00de\u09fd\n"+
		"\u00de\3\u00df\3\u00df\3\u00e0\3\u00e0\3\u00e1\3\u00e1\3\u00e2\3\u00e2"+
		"\3\u00e3\3\u00e3\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4"+
		"\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\5\u00e4\u0a16\n\u00e4"+
		"\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e5\7\u00e5\u0a1e\n\u00e5"+
		"\f\u00e5\16\u00e5\u0a21\13\u00e5\3\u00e6\3\u00e6\3\u00e6\3\u00e6\3\u00e6"+
		"\3\u00e6\7\u00e6\u0a29\n\u00e6\f\u00e6\16\u00e6\u0a2c\13\u00e6\3\u00e7"+
		"\3\u00e7\3\u00e7\3\u00e7\3\u00e7\3\u00e7\7\u00e7\u0a34\n\u00e7\f\u00e7"+
		"\16\u00e7\u0a37\13\u00e7\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8"+
		"\7\u00e8\u0a3f\n\u00e8\f\u00e8\16\u00e8\u0a42\13\u00e8\3\u00e9\3\u00e9"+
		"\3\u00e9\3\u00e9\3\u00e9\3\u00e9\7\u00e9\u0a4a\n\u00e9\f\u00e9\16\u00e9"+
		"\u0a4d\13\u00e9\3\u00ea\3\u00ea\3\u00ea\3\u00ea\3\u00ea\3\u00ea\3\u00ea"+
		"\3\u00ea\3\u00ea\7\u00ea\u0a58\n\u00ea\f\u00ea\16\u00ea\u0a5b\13\u00ea"+
		"\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb"+
		"\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb"+
		"\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00eb\7\u00eb\u0a74\n\u00eb\f\u00eb"+
		"\16\u00eb\u0a77\13\u00eb\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec"+
		"\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec"+
		"\7\u00ec\u0a88\n\u00ec\f\u00ec\16\u00ec\u0a8b\13\u00ec\3\u00ed\3\u00ed"+
		"\3\u00ed\3\u00ed\3\u00ed\3\u00ed\3\u00ed\5\u00ed\u0a94\n\u00ed\3\u00ee"+
		"\3\u00ee\3\u00ee\3\u00ee\3\u00ee\3\u00ee\3\u00ee\3\u00ee\3\u00ee\3\u00ee"+
		"\3\u00ee\7\u00ee\u0aa1\n\u00ee\f\u00ee\16\u00ee\u0aa4\13\u00ee\3\u00ef"+
		"\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef"+
		"\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef\7\u00ef\u0ab5\n\u00ef\f\u00ef"+
		"\16\u00ef\u0ab8\13\u00ef\3\u00f0\3\u00f0\3\u00f0\3\u00f0\3\u00f0\3\u00f0"+
		"\3\u00f0\3\u00f0\3\u00f0\5\u00f0\u0ac3\n\u00f0\3\u00f1\3\u00f1\3\u00f1"+
		"\3\u00f2\3\u00f2\3\u00f2\3\u00f3\3\u00f3\3\u00f3\3\u00f3\3\u00f3\3\u00f3"+
		"\5\u00f3\u0ad1\n\u00f3\3\u00f4\3\u00f4\5\u00f4\u0ad5\n\u00f4\3\u00f4\3"+
		"\u00f4\7\u00f4\u0ad9\n\u00f4\f\u00f4\16\u00f4\u0adc\13\u00f4\3\u00f5\3"+
		"\u00f5\3\u00f5\3\u00f6\3\u00f6\3\u00f7\3\u00f7\3\u00f7\3\u00f8\3\u00f8"+
		"\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9\7\u00f9"+
		"\u0af0\n\u00f9\f\u00f9\16\u00f9\u0af3\13\u00f9\3\u00f9\3\u00f9\3\u00f9"+
		"\3\u00f9\3\u00f9\3\u00f9\7\u00f9\u0afb\n\u00f9\f\u00f9\16\u00f9\u0afe"+
		"\13\u00f9\3\u00f9\3\u00f9\3\u00f9\5\u00f9\u0b03\n\u00f9\3\u00f9\2\17\66"+
		":@\u01c8\u01ca\u01cc\u01ce\u01d0\u01d2\u01d4\u01d6\u01da\u01dc\u00fa\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e"+
		"\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6"+
		"\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be"+
		"\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6"+
		"\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee"+
		"\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106"+
		"\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118\u011a\u011c\u011e"+
		"\u0120\u0122\u0124\u0126\u0128\u012a\u012c\u012e\u0130\u0132\u0134\u0136"+
		"\u0138\u013a\u013c\u013e\u0140\u0142\u0144\u0146\u0148\u014a\u014c\u014e"+
		"\u0150\u0152\u0154\u0156\u0158\u015a\u015c\u015e\u0160\u0162\u0164\u0166"+
		"\u0168\u016a\u016c\u016e\u0170\u0172\u0174\u0176\u0178\u017a\u017c\u017e"+
		"\u0180\u0182\u0184\u0186\u0188\u018a\u018c\u018e\u0190\u0192\u0194\u0196"+
		"\u0198\u019a\u019c\u019e\u01a0\u01a2\u01a4\u01a6\u01a8\u01aa\u01ac\u01ae"+
		"\u01b0\u01b2\u01b4\u01b6\u01b8\u01ba\u01bc\u01be\u01c0\u01c2\u01c4\u01c6"+
		"\u01c8\u01ca\u01cc\u01ce\u01d0\u01d2\u01d4\u01d6\u01d8\u01da\u01dc\u01de"+
		"\u01e0\u01e2\u01e4\u01e6\u01e8\u01ea\u01ec\u01ee\u01f0\2\t\3\2\66;\7\2"+
		"\b\b\13\13\36\36  ((\4\2\21\21\27\27\4\2EE^h\3\2TU\5\2\35\35FGMN\4\2V"+
		"W[[\u0bde\2\u01f2\3\2\2\2\4\u01f6\3\2\2\2\6\u0206\3\2\2\2\b\u020a\3\2"+
		"\2\2\n\u020c\3\2\2\2\f\u020e\3\2\2\2\16\u0213\3\2\2\2\20\u0217\3\2\2\2"+
		"\22\u022a\3\2\2\2\24\u022c\3\2\2\2\26\u0231\3\2\2\2\30\u0235\3\2\2\2\32"+
		"\u0237\3\2\2\2\34\u0239\3\2\2\2\36\u023b\3\2\2\2 \u0246\3\2\2\2\"\u0248"+
		"\3\2\2\2$\u0252\3\2\2\2&\u0259\3\2\2\2(\u0265\3\2\2\2*\u0267\3\2\2\2,"+
		"\u026a\3\2\2\2.\u026e\3\2\2\2\60\u0278\3\2\2\2\62\u027d\3\2\2\2\64\u0288"+
		"\3\2\2\2\66\u028a\3\2\2\28\u029a\3\2\2\2:\u029c\3\2\2\2<\u02ac\3\2\2\2"+
		">\u02ae\3\2\2\2@\u02b0\3\2\2\2B\u02bc\3\2\2\2D\u02cf\3\2\2\2F\u02dd\3"+
		"\2\2\2H\u02e3\3\2\2\2J\u02e5\3\2\2\2L\u02e9\3\2\2\2N\u02ef\3\2\2\2P\u02f6"+
		"\3\2\2\2R\u0300\3\2\2\2T\u0304\3\2\2\2V\u0306\3\2\2\2X\u0317\3\2\2\2Z"+
		"\u0322\3\2\2\2\\\u0324\3\2\2\2^\u0328\3\2\2\2`\u0330\3\2\2\2b\u0333\3"+
		"\2\2\2d\u0336\3\2\2\2f\u033e\3\2\2\2h\u034b\3\2\2\2j\u0352\3\2\2\2l\u0354"+
		"\3\2\2\2n\u035c\3\2\2\2p\u0367\3\2\2\2r\u0369\3\2\2\2t\u0371\3\2\2\2v"+
		"\u0376\3\2\2\2x\u037c\3\2\2\2z\u0380\3\2\2\2|\u0384\3\2\2\2~\u0389\3\2"+
		"\2\2\u0080\u038d\3\2\2\2\u0082\u03a0\3\2\2\2\u0084\u03a2\3\2\2\2\u0086"+
		"\u03a7\3\2\2\2\u0088\u03ab\3\2\2\2\u008a\u03ad\3\2\2\2\u008c\u03af\3\2"+
		"\2\2\u008e\u03b1\3\2\2\2\u0090\u03bc\3\2\2\2\u0092\u03be\3\2\2\2\u0094"+
		"\u03c5\3\2\2\2\u0096\u03d2\3\2\2\2\u0098\u03e5\3\2\2\2\u009a\u03e9\3\2"+
		"\2\2\u009c\u03eb\3\2\2\2\u009e\u03f9\3\2\2\2\u00a0\u040b\3\2\2\2\u00a2"+
		"\u0410\3\2\2\2\u00a4\u0418\3\2\2\2\u00a6\u042b\3\2\2\2\u00a8\u0430\3\2"+
		"\2\2\u00aa\u043a\3\2\2\2\u00ac\u043d\3\2\2\2\u00ae\u0447\3\2\2\2\u00b0"+
		"\u044b\3\2\2\2\u00b2\u044d\3\2\2\2\u00b4\u044f\3\2\2\2\u00b6\u0452\3\2"+
		"\2\2\u00b8\u045c\3\2\2\2\u00ba\u0463\3\2\2\2\u00bc\u0466\3\2\2\2\u00be"+
		"\u046f\3\2\2\2\u00c0\u0471\3\2\2\2\u00c2\u04a8\3\2\2\2\u00c4\u04aa\3\2"+
		"\2\2\u00c6\u04b2\3\2\2\2\u00c8\u04be\3\2\2\2\u00ca\u04c9\3\2\2\2\u00cc"+
		"\u04d7\3\2\2\2\u00ce\u04d9\3\2\2\2\u00d0\u04e2\3\2\2\2\u00d2\u04e7\3\2"+
		"\2\2\u00d4\u04fb\3\2\2\2\u00d6\u04fd\3\2\2\2\u00d8\u0500\3\2\2\2\u00da"+
		"\u050e\3\2\2\2\u00dc\u0513\3\2\2\2\u00de\u051e\3\2\2\2\u00e0\u0523\3\2"+
		"\2\2\u00e2\u052f\3\2\2\2\u00e4\u0534\3\2\2\2\u00e6\u053c\3\2\2\2\u00e8"+
		"\u054a\3\2\2\2\u00ea\u054f\3\2\2\2\u00ec\u0561\3\2\2\2\u00ee\u0563\3\2"+
		"\2\2\u00f0\u0569\3\2\2\2\u00f2\u056e\3\2\2\2\u00f4\u0576\3\2\2\2\u00f6"+
		"\u057b\3\2\2\2\u00f8\u0583\3\2\2\2\u00fa\u058b\3\2\2\2\u00fc\u0592\3\2"+
		"\2\2\u00fe\u0594\3\2\2\2\u0100\u059d\3\2\2\2\u0102\u05a5\3\2\2\2\u0104"+
		"\u05a8\3\2\2\2\u0106\u05ae\3\2\2\2\u0108\u05b7\3\2\2\2\u010a\u05bf\3\2"+
		"\2\2\u010c\u05c5\3\2\2\2\u010e\u05cf\3\2\2\2\u0110\u05d1\3\2\2\2\u0112"+
		"\u05d7\3\2\2\2\u0114\u05e3\3\2\2\2\u0116\u05ea\3\2\2\2\u0118\u05f8\3\2"+
		"\2\2\u011a\u05fa\3\2\2\2\u011c\u05fc\3\2\2\2\u011e\u0600\3\2\2\2\u0120"+
		"\u0604\3\2\2\2\u0122\u060e\3\2\2\2\u0124\u0610\3\2\2\2\u0126\u0616\3\2"+
		"\2\2\u0128\u061e\3\2\2\2\u012a\u0630\3\2\2\2\u012c\u0632\3\2\2\2\u012e"+
		"\u0638\3\2\2\2\u0130\u0647\3\2\2\2\u0132\u064a\3\2\2\2\u0134\u065b\3\2"+
		"\2\2\u0136\u065d\3\2\2\2\u0138\u065f\3\2\2\2\u013a\u0665\3\2\2\2\u013c"+
		"\u066b\3\2\2\2\u013e\u0675\3\2\2\2\u0140\u0679\3\2\2\2\u0142\u067b\3\2"+
		"\2\2\u0144\u068b\3\2\2\2\u0146\u069d\3\2\2\2\u0148\u069f\3\2\2\2\u014a"+
		"\u06a1\3\2\2\2\u014c\u06a9\3\2\2\2\u014e\u06b8\3\2\2\2\u0150\u06c7\3\2"+
		"\2\2\u0152\u06cd\3\2\2\2\u0154\u06d3\3\2\2\2\u0156\u06d9\3\2\2\2\u0158"+
		"\u06dd\3\2\2\2\u015a\u06ef\3\2\2\2\u015c\u06f1\3\2\2\2\u015e\u06f8\3\2"+
		"\2\2\u0160\u0701\3\2\2\2\u0162\u0707\3\2\2\2\u0164\u070f\3\2\2\2\u0166"+
		"\u0712\3\2\2\2\u0168\u071b\3\2\2\2\u016a\u0722\3\2\2\2\u016c\u072d\3\2"+
		"\2\2\u016e\u0737\3\2\2\2\u0170\u075b\3\2\2\2\u0172\u075d\3\2\2\2\u0174"+
		"\u077a\3\2\2\2\u0176\u0781\3\2\2\2\u0178\u0783\3\2\2\2\u017a\u0789\3\2"+
		"\2\2\u017c\u07b1\3\2\2\2\u017e\u07b3\3\2\2\2\u0180\u07da\3\2\2\2\u0182"+
		"\u0817\3\2\2\2\u0184\u0819\3\2\2\2\u0186\u0853\3\2\2\2\u0188\u0858\3\2"+
		"\2\2\u018a\u0867\3\2\2\2\u018c\u0869\3\2\2\2\u018e\u0875\3\2\2\2\u0190"+
		"\u0881\3\2\2\2\u0192\u088d\3\2\2\2\u0194\u08a6\3\2\2\2\u0196\u08f6\3\2"+
		"\2\2\u0198\u08f8\3\2\2\2\u019a\u093b\3\2\2\2\u019c\u093d\3\2\2\2\u019e"+
		"\u0974\3\2\2\2\u01a0\u0976\3\2\2\2\u01a2\u09a4\3\2\2\2\u01a4\u09bc\3\2"+
		"\2\2\u01a6\u09be\3\2\2\2\u01a8\u09c8\3\2\2\2\u01aa\u09cf\3\2\2\2\u01ac"+
		"\u09d3\3\2\2\2\u01ae\u09d5\3\2\2\2\u01b0\u09e3\3\2\2\2\u01b2\u09e5\3\2"+
		"\2\2\u01b4\u09ef\3\2\2\2\u01b6\u09f3\3\2\2\2\u01b8\u09f5\3\2\2\2\u01ba"+
		"\u09fc\3\2\2\2\u01bc\u09fe\3\2\2\2\u01be\u0a00\3\2\2\2\u01c0\u0a02\3\2"+
		"\2\2\u01c2\u0a04\3\2\2\2\u01c4\u0a06\3\2\2\2\u01c6\u0a15\3\2\2\2\u01c8"+
		"\u0a17\3\2\2\2\u01ca\u0a22\3\2\2\2\u01cc\u0a2d\3\2\2\2\u01ce\u0a38\3\2"+
		"\2\2\u01d0\u0a43\3\2\2\2\u01d2\u0a4e\3\2\2\2\u01d4\u0a5c\3\2\2\2\u01d6"+
		"\u0a78\3\2\2\2\u01d8\u0a93\3\2\2\2\u01da\u0a95\3\2\2\2\u01dc\u0aa5\3\2"+
		"\2\2\u01de\u0ac2\3\2\2\2\u01e0\u0ac4\3\2\2\2\u01e2\u0ac7\3\2\2\2\u01e4"+
		"\u0ad0\3\2\2\2\u01e6\u0ad4\3\2\2\2\u01e8\u0add\3\2\2\2\u01ea\u0ae0\3\2"+
		"\2\2\u01ec\u0ae2\3\2\2\2\u01ee\u0ae5\3\2\2\2\u01f0\u0b02\3\2\2\2\u01f2"+
		"\u01f3\t\2\2\2\u01f3\3\3\2\2\2\u01f4\u01f7\5\6\4\2\u01f5\u01f7\5\16\b"+
		"\2\u01f6\u01f4\3\2\2\2\u01f6\u01f5\3\2\2\2\u01f7\5\3\2\2\2\u01f8\u01fa"+
		"\5\u00f0y\2\u01f9\u01f8\3\2\2\2\u01fa\u01fd\3\2\2\2\u01fb\u01f9\3\2\2"+
		"\2\u01fb\u01fc\3\2\2\2\u01fc\u01fe\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fe\u0207"+
		"\5\b\5\2\u01ff\u0201\5\u00f0y\2\u0200\u01ff\3\2\2\2\u0201\u0204\3\2\2"+
		"\2\u0202\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0205\3\2\2\2\u0204\u0202"+
		"\3\2\2\2\u0205\u0207\7\6\2\2\u0206\u01fb\3\2\2\2\u0206\u0202\3\2\2\2\u0207"+
		"\7\3\2\2\2\u0208\u020b\5\n\6\2\u0209\u020b\5\f\7\2\u020a\u0208\3\2\2\2"+
		"\u020a\u0209\3\2\2\2\u020b\t\3\2\2\2\u020c\u020d\t\3\2\2\u020d\13\3\2"+
		"\2\2\u020e\u020f\t\4\2\2\u020f\r\3\2\2\2\u0210\u0214\5\20\t\2\u0211\u0214"+
		"\5\36\20\2\u0212\u0214\5 \21\2\u0213\u0210\3\2\2\2\u0213\u0211\3\2\2\2"+
		"\u0213\u0212\3\2\2\2\u0214\17\3\2\2\2\u0215\u0218\5\26\f\2\u0216\u0218"+
		"\5\34\17\2\u0217\u0215\3\2\2\2\u0217\u0216\3\2\2\2\u0218\u021d\3\2\2\2"+
		"\u0219\u021c\5\24\13\2\u021a\u021c\5\32\16\2\u021b\u0219\3\2\2\2\u021b"+
		"\u021a\3\2\2\2\u021c\u021f\3\2\2\2\u021d\u021b\3\2\2\2\u021d\u021e\3\2"+
		"\2\2\u021e\21\3\2\2\2\u021f\u021d\3\2\2\2\u0220\u0222\5\u00f2z\2\u0221"+
		"\u0223\5,\27\2\u0222\u0221\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u022b\3\2"+
		"\2\2\u0224\u0225\5\20\t\2\u0225\u0226\7D\2\2\u0226\u0228\5\u00f2z\2\u0227"+
		"\u0229\5,\27\2\u0228\u0227\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022b\3\2"+
		"\2\2\u022a\u0220\3\2\2\2\u022a\u0224\3\2\2\2\u022b\23\3\2\2\2\u022c\u022d"+
		"\7D\2\2\u022d\u022f\5\u00f2z\2\u022e\u0230\5,\27\2\u022f\u022e\3\2\2\2"+
		"\u022f\u0230\3\2\2\2\u0230\25\3\2\2\2\u0231\u0233\5\u00f2z\2\u0232\u0234"+
		"\5,\27\2\u0233\u0232\3\2\2\2\u0233\u0234\3\2\2\2\u0234\27\3\2\2\2\u0235"+
		"\u0236\5\22\n\2\u0236\31\3\2\2\2\u0237\u0238\5\24\13\2\u0238\33\3\2\2"+
		"\2\u0239\u023a\5\26\f\2\u023a\35\3\2\2\2\u023b\u023c\5\u00f2z\2\u023c"+
		"\37\3\2\2\2\u023d\u023e\5\6\4\2\u023e\u023f\5\"\22\2\u023f\u0247\3\2\2"+
		"\2\u0240\u0241\5\20\t\2\u0241\u0242\5\"\22\2\u0242\u0247\3\2\2\2\u0243"+
		"\u0244\5\36\20\2\u0244\u0245\5\"\22\2\u0245\u0247\3\2\2\2\u0246\u023d"+
		"\3\2\2\2\u0246\u0240\3\2\2\2\u0246\u0243\3\2\2\2\u0247!\3\2\2\2\u0248"+
		"\u024c\5\u00f4{\2\u0249\u024b\5\u00f4{\2\u024a\u0249\3\2\2\2\u024b\u024e"+
		"\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2\2\2\u024d#\3\2\2\2\u024e"+
		"\u024c\3\2\2\2\u024f\u0251\5&\24\2\u0250\u024f\3\2\2\2\u0251\u0254\3\2"+
		"\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0255\3\2\2\2\u0254"+
		"\u0252\3\2\2\2\u0255\u0257\7i\2\2\u0256\u0258\5(\25\2\u0257\u0256\3\2"+
		"\2\2\u0257\u0258\3\2\2\2\u0258%\3\2\2\2\u0259\u025a\5\u00f0y\2\u025a\'"+
		"\3\2\2\2\u025b\u025c\7\24\2\2\u025c\u0266\5\36\20\2\u025d\u025e\7\24\2"+
		"\2\u025e\u0262\5\20\t\2\u025f\u0261\5*\26\2\u0260\u025f\3\2\2\2\u0261"+
		"\u0264\3\2\2\2\u0262\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0266\3\2"+
		"\2\2\u0264\u0262\3\2\2\2\u0265\u025b\3\2\2\2\u0265\u025d\3\2\2\2\u0266"+
		")\3\2\2\2\u0267\u0268\7X\2\2\u0268\u0269\5\30\r\2\u0269+\3\2\2\2\u026a"+
		"\u026b\7G\2\2\u026b\u026c\5.\30\2\u026c\u026d\7F\2\2\u026d-\3\2\2\2\u026e"+
		"\u0273\5\60\31\2\u026f\u0270\7C\2\2\u0270\u0272\5\60\31\2\u0271\u026f"+
		"\3\2\2\2\u0272\u0275\3\2\2\2\u0273\u0271\3\2\2\2\u0273\u0274\3\2\2\2\u0274"+
		"/\3\2\2\2\u0275\u0273\3\2\2\2\u0276\u0279\5\16\b\2\u0277\u0279\5\62\32"+
		"\2\u0278\u0276\3\2\2\2\u0278\u0277\3\2\2\2\u0279\61\3\2\2\2\u027a\u027c"+
		"\5\u00f0y\2\u027b\u027a\3\2\2\2\u027c\u027f\3\2\2\2\u027d\u027b\3\2\2"+
		"\2\u027d\u027e\3\2\2\2\u027e\u0280\3\2\2\2\u027f\u027d\3\2\2\2\u0280\u0282"+
		"\7J\2\2\u0281\u0283\5\64\33\2\u0282\u0281\3\2\2\2\u0282\u0283\3\2\2\2"+
		"\u0283\63\3\2\2\2\u0284\u0285\7\24\2\2\u0285\u0289\5\16\b\2\u0286\u0287"+
		"\7+\2\2\u0287\u0289\5\16\b\2\u0288\u0284\3\2\2\2\u0288\u0286\3\2\2\2\u0289"+
		"\65\3\2\2\2\u028a\u028b\b\34\1\2\u028b\u028c\7i\2\2\u028c\u0292\3\2\2"+
		"\2\u028d\u028e\f\3\2\2\u028e\u028f\7D\2\2\u028f\u0291\7i\2\2\u0290\u028d"+
		"\3\2\2\2\u0291\u0294\3\2\2\2\u0292\u0290\3\2\2\2\u0292\u0293\3\2\2\2\u0293"+
		"\67\3\2\2\2\u0294\u0292\3\2\2\2\u0295\u029b\7i\2\2\u0296\u0297\5:\36\2"+
		"\u0297\u0298\7D\2\2\u0298\u0299\7i\2\2\u0299\u029b\3\2\2\2\u029a\u0295"+
		"\3\2\2\2\u029a\u0296\3\2\2\2\u029b9\3\2\2\2\u029c\u029d\b\36\1\2\u029d"+
		"\u029e\7i\2\2\u029e\u02a4\3\2\2\2\u029f\u02a0\f\3\2\2\u02a0\u02a1\7D\2"+
		"\2\u02a1\u02a3\7i\2\2\u02a2\u029f\3\2\2\2\u02a3\u02a6\3\2\2\2\u02a4\u02a2"+
		"\3\2\2\2\u02a4\u02a5\3\2\2\2\u02a5;\3\2\2\2\u02a6\u02a4\3\2\2\2\u02a7"+
		"\u02ad\7i\2\2\u02a8\u02a9\5@!\2\u02a9\u02aa\7D\2\2\u02aa\u02ab\7i\2\2"+
		"\u02ab\u02ad\3\2\2\2\u02ac\u02a7\3\2\2\2\u02ac\u02a8\3\2\2\2\u02ad=\3"+
		"\2\2\2\u02ae\u02af\7i\2\2\u02af?\3\2\2\2\u02b0\u02b1\b!\1\2\u02b1\u02b2"+
		"\7i\2\2\u02b2\u02b8\3\2\2\2\u02b3\u02b4\f\3\2\2\u02b4\u02b5\7D\2\2\u02b5"+
		"\u02b7\7i\2\2\u02b6\u02b3\3\2\2\2\u02b7\u02ba\3\2\2\2\u02b8\u02b6\3\2"+
		"\2\2\u02b8\u02b9\3\2\2\2\u02b9A\3\2\2\2\u02ba\u02b8\3\2\2\2\u02bb\u02bd"+
		"\5D#\2\u02bc\u02bb\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02c1\3\2\2\2\u02be"+
		"\u02c0\5H%\2\u02bf\u02be\3\2\2\2\u02c0\u02c3\3\2\2\2\u02c1\u02bf\3\2\2"+
		"\2\u02c1\u02c2\3\2\2\2\u02c2\u02c7\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c4\u02c6"+
		"\5R*\2\u02c5\u02c4\3\2\2\2\u02c6\u02c9\3\2\2\2\u02c7\u02c5\3\2\2\2\u02c7"+
		"\u02c8\3\2\2\2\u02c8\u02ca\3\2\2\2\u02c9\u02c7\3\2\2\2\u02ca\u02cb\7\2"+
		"\2\3\u02cbC\3\2\2\2\u02cc\u02ce\5F$\2\u02cd\u02cc\3\2\2\2\u02ce\u02d1"+
		"\3\2\2\2\u02cf\u02cd\3\2\2\2\u02cf\u02d0\3\2\2\2\u02d0\u02d2\3\2\2\2\u02d1"+
		"\u02cf\3\2\2\2\u02d2\u02d3\7#\2\2\u02d3\u02d8\7i\2\2\u02d4\u02d5\7D\2"+
		"\2\u02d5\u02d7\7i\2\2\u02d6\u02d4\3\2\2\2\u02d7\u02da\3\2\2\2\u02d8\u02d6"+
		"\3\2\2\2\u02d8\u02d9\3\2\2\2\u02d9\u02db\3\2\2\2\u02da\u02d8\3\2\2\2\u02db"+
		"\u02dc\7B\2\2\u02dcE\3\2\2\2\u02dd\u02de\5\u00f0y\2\u02deG\3\2\2\2\u02df"+
		"\u02e4\5J&\2\u02e0\u02e4\5L\'\2\u02e1\u02e4\5N(\2\u02e2\u02e4\5P)\2\u02e3"+
		"\u02df\3\2\2\2\u02e3\u02e0\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e3\u02e2\3\2"+
		"\2\2\u02e4I\3\2\2\2\u02e5\u02e6\7\34\2\2\u02e6\u02e7\58\35\2\u02e7\u02e8"+
		"\7B\2\2\u02e8K\3\2\2\2\u02e9\u02ea\7\34\2\2\u02ea\u02eb\5:\36\2\u02eb"+
		"\u02ec\7D\2\2\u02ec\u02ed\7V\2\2\u02ed\u02ee\7B\2\2\u02eeM\3\2\2\2\u02ef"+
		"\u02f0\7\34\2\2\u02f0\u02f1\7)\2\2\u02f1\u02f2\58\35\2\u02f2\u02f3\7D"+
		"\2\2\u02f3\u02f4\7i\2\2\u02f4\u02f5\7B\2\2\u02f5O\3\2\2\2\u02f6\u02f7"+
		"\7\34\2\2\u02f7\u02f8\7)\2\2\u02f8\u02f9\58\35\2\u02f9\u02fa\7D\2\2\u02fa"+
		"\u02fb\7V\2\2\u02fb\u02fc\7B\2\2\u02fcQ\3\2\2\2\u02fd\u0301\5T+\2\u02fe"+
		"\u0301\5\u00d0i\2\u02ff\u0301\7B\2\2\u0300\u02fd\3\2\2\2\u0300\u02fe\3"+
		"\2\2\2\u0300\u02ff\3\2\2\2\u0301S\3\2\2\2\u0302\u0305\5V,\2\u0303\u0305"+
		"\5\u00c4c\2\u0304\u0302\3\2\2\2\u0304\u0303\3\2\2\2\u0305U\3\2\2\2\u0306"+
		"\u0307\5X-\2\u0307\u0308\7\f\2\2\u0308\u030a\7i\2\2\u0309\u030b\5\\/\2"+
		"\u030a\u0309\3\2\2\2\u030a\u030b\3\2\2\2\u030b\u030d\3\2\2\2\u030c\u030e"+
		"\5`\61\2\u030d\u030c\3\2\2\2\u030d\u030e\3\2\2\2\u030e\u0310\3\2\2\2\u030f"+
		"\u0311\5b\62\2\u0310\u030f\3\2\2\2\u0310\u0311\3\2\2\2\u0311\u0312\3\2"+
		"\2\2\u0312\u0313\5f\64\2\u0313W\3\2\2\2\u0314\u0316\5Z.\2\u0315\u0314"+
		"\3\2\2\2\u0316\u0319\3\2\2\2\u0317\u0315\3\2\2\2\u0317\u0318\3\2\2\2\u0318"+
		"Y\3\2\2\2\u0319\u0317\3\2\2\2\u031a\u0323\5\u00f0y\2\u031b\u0323\7&\2"+
		"\2\u031c\u0323\7%\2\2\u031d\u0323\7$\2\2\u031e\u0323\7\4\2\2\u031f\u0323"+
		"\7)\2\2\u0320\u0323\7\25\2\2\u0321\u0323\7*\2\2\u0322\u031a\3\2\2\2\u0322"+
		"\u031b\3\2\2\2\u0322\u031c\3\2\2\2\u0322\u031d\3\2\2\2\u0322\u031e\3\2"+
		"\2\2\u0322\u031f\3\2\2\2\u0322\u0320\3\2\2\2\u0322\u0321\3\2\2\2\u0323"+
		"[\3\2\2\2\u0324\u0325\7G\2\2\u0325\u0326\5^\60\2\u0326\u0327\7F\2\2\u0327"+
		"]\3\2\2\2\u0328\u032d\5$\23\2\u0329\u032a\7C\2\2\u032a\u032c\5$\23\2\u032b"+
		"\u0329\3\2\2\2\u032c\u032f\3\2\2\2\u032d\u032b\3\2\2\2\u032d\u032e\3\2"+
		"\2\2\u032e_\3\2\2\2\u032f\u032d\3\2\2\2\u0330\u0331\7\24\2\2\u0331\u0332"+
		"\5\22\n\2\u0332a\3\2\2\2\u0333\u0334\7\33\2\2\u0334\u0335\5d\63\2\u0335"+
		"c\3\2\2\2\u0336\u033b\5\30\r\2\u0337\u0338\7C\2\2\u0338\u033a\5\30\r\2"+
		"\u0339\u0337\3\2\2\2\u033a\u033d\3\2\2\2\u033b\u0339\3\2\2\2\u033b\u033c"+
		"\3\2\2\2\u033ce\3\2\2\2\u033d\u033b\3\2\2\2\u033e\u0342\7>\2\2\u033f\u0341"+
		"\5h\65\2\u0340\u033f\3\2\2\2\u0341\u0344\3\2\2\2\u0342\u0340\3\2\2\2\u0342"+
		"\u0343\3\2\2\2\u0343\u0345\3\2\2\2\u0344\u0342\3\2\2\2\u0345\u0346\7?"+
		"\2\2\u0346g\3\2\2\2\u0347\u034c\5j\66\2\u0348\u034c\5\u00b2Z\2\u0349\u034c"+
		"\5\u00b4[\2\u034a\u034c\5\u00b6\\\2\u034b\u0347\3\2\2\2\u034b\u0348\3"+
		"\2\2\2\u034b\u0349\3\2\2\2\u034b\u034a\3\2\2\2\u034ci\3\2\2\2\u034d\u0353"+
		"\5l\67\2\u034e\u0353\5\u0092J\2\u034f\u0353\5T+\2\u0350\u0353\5\u00d0"+
		"i\2\u0351\u0353\7B\2\2\u0352\u034d\3\2\2\2\u0352\u034e\3\2\2\2\u0352\u034f"+
		"\3\2\2\2\u0352\u0350\3\2\2\2\u0352\u0351\3\2\2\2\u0353k\3\2\2\2\u0354"+
		"\u0355\5n8\2\u0355\u0356\5z>\2\u0356\u0357\5r:\2\u0357\u0358\7B\2\2\u0358"+
		"m\3\2\2\2\u0359\u035b\5p9\2\u035a\u0359\3\2\2\2\u035b\u035e\3\2\2\2\u035c"+
		"\u035a\3\2\2\2\u035c\u035d\3\2\2\2\u035do\3\2\2\2\u035e\u035c\3\2\2\2"+
		"\u035f\u0368\5\u00f0y\2\u0360\u0368\7&\2\2\u0361\u0368\7%\2\2\u0362\u0368"+
		"\7$\2\2\u0363\u0368\7)\2\2\u0364\u0368\7\25\2\2\u0365\u0368\7\61\2\2\u0366"+
		"\u0368\7\64\2\2\u0367\u035f\3\2\2\2\u0367\u0360\3\2\2\2\u0367\u0361\3"+
		"\2\2\2\u0367\u0362\3\2\2\2\u0367\u0363\3\2\2\2\u0367\u0364\3\2\2\2\u0367"+
		"\u0365\3\2\2\2\u0367\u0366\3\2\2\2\u0368q\3\2\2\2\u0369\u036e\5t;\2\u036a"+
		"\u036b\7C\2\2\u036b\u036d\5t;\2\u036c\u036a\3\2\2\2\u036d\u0370\3\2\2"+
		"\2\u036e\u036c\3\2\2\2\u036e\u036f\3\2\2\2\u036fs\3\2\2\2\u0370\u036e"+
		"\3\2\2\2\u0371\u0374\5v<\2\u0372\u0373\7E\2\2\u0373\u0375\5x=\2\u0374"+
		"\u0372\3\2\2\2\u0374\u0375\3\2\2\2\u0375u\3\2\2\2\u0376\u0378\7i\2\2\u0377"+
		"\u0379\5\"\22\2\u0378\u0377\3\2\2\2\u0378\u0379\3\2\2\2\u0379w\3\2\2\2"+
		"\u037a\u037d\5\u01ac\u00d7\2\u037b\u037d\5\u0106\u0084\2\u037c\u037a\3"+
		"\2\2\2\u037c\u037b\3\2\2\2\u037dy\3\2\2\2\u037e\u0381\5|?\2\u037f\u0381"+
		"\5~@\2\u0380\u037e\3\2\2\2\u0380\u037f\3\2\2\2\u0381{\3\2\2\2\u0382\u0385"+
		"\5\b\5\2\u0383\u0385\7\6\2\2\u0384\u0382\3\2\2\2\u0384\u0383\3\2\2\2\u0385"+
		"}\3\2\2\2\u0386\u038a\5\u0080A\2\u0387\u038a\5\u008eH\2\u0388\u038a\5"+
		"\u0090I\2\u0389\u0386\3\2\2\2\u0389\u0387\3\2\2\2\u0389\u0388\3\2\2\2"+
		"\u038a\177\3\2\2\2\u038b\u038e\5\u0086D\2\u038c\u038e\5\u008cG\2\u038d"+
		"\u038b\3\2\2\2\u038d\u038c\3\2\2\2\u038e\u0393\3\2\2\2\u038f\u0392\5\u0084"+
		"C\2\u0390\u0392\5\u008aF\2\u0391\u038f\3\2\2\2\u0391\u0390\3\2\2\2\u0392"+
		"\u0395\3\2\2\2\u0393\u0391\3\2\2\2\u0393\u0394\3\2\2\2\u0394\u0081\3\2"+
		"\2\2\u0395\u0393\3\2\2\2\u0396\u0398\7i\2\2\u0397\u0399\5,\27\2\u0398"+
		"\u0397\3\2\2\2\u0398\u0399\3\2\2\2\u0399\u03a1\3\2\2\2\u039a\u039b\5\u0080"+
		"A\2\u039b\u039c\7D\2\2\u039c\u039e\5\u00f2z\2\u039d\u039f\5,\27\2\u039e"+
		"\u039d\3\2\2\2\u039e\u039f\3\2\2\2\u039f\u03a1\3\2\2\2\u03a0\u0396\3\2"+
		"\2\2\u03a0\u039a\3\2\2\2\u03a1\u0083\3\2\2\2\u03a2\u03a3\7D\2\2\u03a3"+
		"\u03a5\5\u00f2z\2\u03a4\u03a6\5,\27\2\u03a5\u03a4\3\2\2\2\u03a5\u03a6"+
		"\3\2\2\2\u03a6\u0085\3\2\2\2\u03a7\u03a9\7i\2\2\u03a8\u03aa\5,\27\2\u03a9"+
		"\u03a8\3\2\2\2\u03a9\u03aa\3\2\2\2\u03aa\u0087\3\2\2\2\u03ab\u03ac\5\u0082"+
		"B\2\u03ac\u0089\3\2\2\2\u03ad\u03ae\5\u0084C\2\u03ae\u008b\3\2\2\2\u03af"+
		"\u03b0\5\u0086D\2\u03b0\u008d\3\2\2\2\u03b1\u03b2\7i\2\2\u03b2\u008f\3"+
		"\2\2\2\u03b3\u03b4\5|?\2\u03b4\u03b5\5\"\22\2\u03b5\u03bd\3\2\2\2\u03b6"+
		"\u03b7\5\u0080A\2\u03b7\u03b8\5\"\22\2\u03b8\u03bd\3\2\2\2\u03b9\u03ba"+
		"\5\u008eH\2\u03ba\u03bb\5\"\22\2\u03bb\u03bd\3\2\2\2\u03bc\u03b3\3\2\2"+
		"\2\u03bc\u03b6\3\2\2\2\u03bc\u03b9\3\2\2\2\u03bd\u0091\3\2\2\2\u03be\u03bf"+
		"\5\u0094K\2\u03bf\u03c0\5\u0098M\2\u03c0\u03c1\5\u00b0Y\2\u03c1\u0093"+
		"\3\2\2\2\u03c2\u03c4\5\u0096L\2\u03c3\u03c2\3\2\2\2\u03c4\u03c7\3\2\2"+
		"\2\u03c5\u03c3\3\2\2\2\u03c5\u03c6\3\2\2\2\u03c6\u0095\3\2\2\2\u03c7\u03c5"+
		"\3\2\2\2\u03c8\u03d3\5\u00f0y\2\u03c9\u03d3\7&\2\2\u03ca\u03d3\7%\2\2"+
		"\u03cb\u03d3\7$\2\2\u03cc\u03d3\7\4\2\2\u03cd\u03d3\7)\2\2\u03ce\u03d3"+
		"\7\25\2\2\u03cf\u03d3\7-\2\2\u03d0\u03d3\7!\2\2\u03d1\u03d3\7*\2\2\u03d2"+
		"\u03c8\3\2\2\2\u03d2\u03c9\3\2\2\2\u03d2\u03ca\3\2\2\2\u03d2\u03cb\3\2"+
		"\2\2\u03d2\u03cc\3\2\2\2\u03d2\u03cd\3\2\2\2\u03d2\u03ce\3\2\2\2\u03d2"+
		"\u03cf\3\2\2\2\u03d2\u03d0\3\2\2\2\u03d2\u03d1\3\2\2\2\u03d3\u0097\3\2"+
		"\2\2\u03d4\u03d5\5\u009aN\2\u03d5\u03d7\5\u009cO\2\u03d6\u03d8\5\u00aa"+
		"V\2\u03d7\u03d6\3\2\2\2\u03d7\u03d8\3\2\2\2\u03d8\u03e6\3\2\2\2\u03d9"+
		"\u03dd\5\\/\2\u03da\u03dc\5\u00f0y\2\u03db\u03da\3\2\2\2\u03dc\u03df\3"+
		"\2\2\2\u03dd\u03db\3\2\2\2\u03dd\u03de\3\2\2\2\u03de\u03e0\3\2\2\2\u03df"+
		"\u03dd\3\2\2\2\u03e0\u03e1\5\u009aN\2\u03e1\u03e3\5\u009cO\2\u03e2\u03e4"+
		"\5\u00aaV\2\u03e3\u03e2\3\2\2\2\u03e3\u03e4\3\2\2\2\u03e4\u03e6\3\2\2"+
		"\2\u03e5\u03d4\3\2\2\2\u03e5\u03d9\3\2\2\2\u03e6\u0099\3\2\2\2\u03e7\u03ea"+
		"\5z>\2\u03e8\u03ea\7\63\2\2\u03e9\u03e7\3\2\2\2\u03e9\u03e8\3\2\2\2\u03ea"+
		"\u009b\3\2\2\2\u03eb\u03ec\7i\2\2\u03ec\u03ee\7<\2\2\u03ed\u03ef\5\u009e"+
		"P\2\u03ee\u03ed\3\2\2\2\u03ee\u03ef\3\2\2\2\u03ef\u03f0\3\2\2\2\u03f0"+
		"\u03f2\7=\2\2\u03f1\u03f3\5\"\22\2\u03f2\u03f1\3\2\2\2\u03f2\u03f3\3\2"+
		"\2\2\u03f3\u009d\3\2\2\2\u03f4\u03f5\5\u00a0Q\2\u03f5\u03f6\7C\2\2\u03f6"+
		"\u03f7\5\u00a6T\2\u03f7\u03fa\3\2\2\2\u03f8\u03fa\5\u00a6T\2\u03f9\u03f4"+
		"\3\2\2\2\u03f9\u03f8\3\2\2\2\u03fa\u009f\3\2\2\2\u03fb\u0400\5\u00a2R"+
		"\2\u03fc\u03fd\7C\2\2\u03fd\u03ff\5\u00a2R\2\u03fe\u03fc\3\2\2\2\u03ff"+
		"\u0402\3\2\2\2\u0400\u03fe\3\2\2\2\u0400\u0401\3\2\2\2\u0401\u040c\3\2"+
		"\2\2\u0402\u0400\3\2\2\2\u0403\u0408\5\u00a8U\2\u0404\u0405\7C\2\2\u0405"+
		"\u0407\5\u00a2R\2\u0406\u0404\3\2\2\2\u0407\u040a\3\2\2\2\u0408\u0406"+
		"\3\2\2\2\u0408\u0409\3\2\2\2\u0409\u040c\3\2\2\2\u040a\u0408\3\2\2\2\u040b"+
		"\u03fb\3\2\2\2\u040b\u0403\3\2\2\2\u040c\u00a1\3\2\2\2\u040d\u040f\5\u00a4"+
		"S\2\u040e\u040d\3\2\2\2\u040f\u0412\3\2\2\2\u0410\u040e\3\2\2\2\u0410"+
		"\u0411\3\2\2\2\u0411\u0413\3\2\2\2\u0412\u0410\3\2\2\2\u0413\u0414\5z"+
		">\2\u0414\u0415\5v<\2\u0415\u00a3\3\2\2\2\u0416\u0419\5\u00f0y\2\u0417"+
		"\u0419\7\25\2\2\u0418\u0416\3\2\2\2\u0418\u0417\3\2\2\2\u0419\u00a5\3"+
		"\2\2\2\u041a\u041c\5\u00a4S\2\u041b\u041a\3\2\2\2\u041c\u041f\3\2\2\2"+
		"\u041d\u041b\3\2\2\2\u041d\u041e\3\2\2\2\u041e\u0420\3\2\2\2\u041f\u041d"+
		"\3\2\2\2\u0420\u0424\5z>\2\u0421\u0423\5\u00f0y\2\u0422\u0421\3\2\2\2"+
		"\u0423\u0426\3\2\2\2\u0424\u0422\3\2\2\2\u0424\u0425\3\2\2\2\u0425\u0427"+
		"\3\2\2\2\u0426\u0424\3\2\2\2\u0427\u0428\7k\2\2\u0428\u0429\5v<\2\u0429"+
		"\u042c\3\2\2\2\u042a\u042c\5\u00a2R\2\u042b\u041d\3\2\2\2\u042b\u042a"+
		"\3\2\2\2\u042c\u00a7\3\2\2\2\u042d\u042f\5\u00f0y\2\u042e\u042d\3\2\2"+
		"\2\u042f\u0432\3\2\2\2\u0430\u042e\3\2\2\2\u0430\u0431\3\2\2\2\u0431\u0433"+
		"\3\2\2\2\u0432\u0430\3\2\2\2\u0433\u0436\5z>\2\u0434\u0435\7i\2\2\u0435"+
		"\u0437\7D\2\2\u0436\u0434\3\2\2\2\u0436\u0437\3\2\2\2\u0437\u0438\3\2"+
		"\2\2\u0438\u0439\7.\2\2\u0439\u00a9\3\2\2\2\u043a\u043b\7\60\2\2\u043b"+
		"\u043c\5\u00acW\2\u043c\u00ab\3\2\2\2\u043d\u0442\5\u00aeX\2\u043e\u043f"+
		"\7C\2\2\u043f\u0441\5\u00aeX\2\u0440\u043e\3\2\2\2\u0441\u0444\3\2\2\2"+
		"\u0442\u0440\3\2\2\2\u0442\u0443\3\2\2\2\u0443\u00ad\3\2\2\2\u0444\u0442"+
		"\3\2\2\2\u0445\u0448\5\22\n\2\u0446\u0448\5\36\20\2\u0447\u0445\3\2\2"+
		"\2\u0447\u0446\3\2\2\2\u0448\u00af\3\2\2\2\u0449\u044c\5\u010a\u0086\2"+
		"\u044a\u044c\7B\2\2\u044b\u0449\3\2\2\2\u044b\u044a\3\2\2\2\u044c\u00b1"+
		"\3\2\2\2\u044d\u044e\5\u010a\u0086\2\u044e\u00b3\3\2\2\2\u044f\u0450\7"+
		")\2\2\u0450\u0451\5\u010a\u0086\2\u0451\u00b5\3\2\2\2\u0452\u0453\5\u00b8"+
		"]\2\u0453\u0455\5\u00bc_\2\u0454\u0456\5\u00aaV\2\u0455\u0454\3\2\2\2"+
		"\u0455\u0456\3\2\2\2\u0456\u0457\3\2\2\2\u0457\u0458\5\u00c0a\2\u0458"+
		"\u00b7\3\2\2\2\u0459\u045b\5\u00ba^\2\u045a\u0459\3\2\2\2\u045b\u045e"+
		"\3\2\2\2\u045c\u045a\3\2\2\2\u045c\u045d\3\2\2\2\u045d\u00b9\3\2\2\2\u045e"+
		"\u045c\3\2\2\2\u045f\u0464\5\u00f0y\2\u0460\u0464\7&\2\2\u0461\u0464\7"+
		"%\2\2\u0462\u0464\7$\2\2\u0463\u045f\3\2\2\2\u0463\u0460\3\2\2\2\u0463"+
		"\u0461\3\2\2\2\u0463\u0462\3\2\2\2\u0464\u00bb\3\2\2\2\u0465\u0467\5\\"+
		"/\2\u0466\u0465\3\2\2\2\u0466\u0467\3\2\2\2\u0467\u0468\3\2\2\2\u0468"+
		"\u0469\5\u00be`\2\u0469\u046b\7<\2\2\u046a\u046c\5\u009eP\2\u046b\u046a"+
		"\3\2\2\2\u046b\u046c\3\2\2\2\u046c\u046d\3\2\2\2\u046d\u046e\7=\2\2\u046e"+
		"\u00bd\3\2\2\2\u046f\u0470\7i\2\2\u0470\u00bf\3\2\2\2\u0471\u0473\7>\2"+
		"\2\u0472\u0474\5\u00c2b\2\u0473\u0472\3\2\2\2\u0473\u0474\3\2\2\2\u0474"+
		"\u0476\3\2\2\2\u0475\u0477\5\u010c\u0087\2\u0476\u0475\3\2\2\2\u0476\u0477"+
		"\3\2\2\2\u0477\u0478\3\2\2\2\u0478\u0479\7?\2\2\u0479\u00c1\3\2\2\2\u047a"+
		"\u047c\5,\27\2\u047b\u047a\3\2\2\2\u047b\u047c\3\2\2\2\u047c\u047d\3\2"+
		"\2\2\u047d\u047e\7.\2\2\u047e\u0480\7<\2\2\u047f\u0481\5\u019c\u00cf\2"+
		"\u0480\u047f\3\2\2\2\u0480\u0481\3\2\2\2\u0481\u0482\3\2\2\2\u0482\u0483"+
		"\7=\2\2\u0483\u04a9\7B\2\2\u0484\u0486\5,\27\2\u0485\u0484\3\2\2\2\u0485"+
		"\u0486\3\2\2\2\u0486\u0487\3\2\2\2\u0487\u0488\7+\2\2\u0488\u048a\7<\2"+
		"\2\u0489\u048b\5\u019c\u00cf\2\u048a\u0489\3\2\2\2\u048a\u048b\3\2\2\2"+
		"\u048b\u048c\3\2\2\2\u048c\u048d\7=\2\2\u048d\u04a9\7B\2\2\u048e\u048f"+
		"\5<\37\2\u048f\u0491\7D\2\2\u0490\u0492\5,\27\2\u0491\u0490\3\2\2\2\u0491"+
		"\u0492\3\2\2\2\u0492\u0493\3\2\2\2\u0493\u0494\7+\2\2\u0494\u0496\7<\2"+
		"\2\u0495\u0497\5\u019c\u00cf\2\u0496\u0495\3\2\2\2\u0496\u0497\3\2\2\2"+
		"\u0497\u0498\3\2\2\2\u0498\u0499\7=\2\2\u0499\u049a\7B\2\2\u049a\u04a9"+
		"\3\2\2\2\u049b\u049c\5\u016e\u00b8\2\u049c\u049e\7D\2\2\u049d\u049f\5"+
		",\27\2\u049e\u049d\3\2\2\2\u049e\u049f\3\2\2\2\u049f\u04a0\3\2\2\2\u04a0"+
		"\u04a1\7+\2\2\u04a1\u04a3\7<\2\2\u04a2\u04a4\5\u019c\u00cf\2\u04a3\u04a2"+
		"\3\2\2\2\u04a3\u04a4\3\2\2\2\u04a4\u04a5\3\2\2\2\u04a5\u04a6\7=\2\2\u04a6"+
		"\u04a7\7B\2\2\u04a7\u04a9\3\2\2\2\u04a8\u047b\3\2\2\2\u04a8\u0485\3\2"+
		"\2\2\u04a8\u048e\3\2\2\2\u04a8\u049b\3\2\2\2\u04a9\u00c3\3\2\2\2\u04aa"+
		"\u04ab\5X-\2\u04ab\u04ac\7\23\2\2\u04ac\u04ae\7i\2\2\u04ad\u04af\5b\62"+
		"\2\u04ae\u04ad\3\2\2\2\u04ae\u04af\3\2\2\2\u04af\u04b0\3\2\2\2\u04b0\u04b1"+
		"\5\u00c6d\2\u04b1\u00c5\3\2\2\2\u04b2\u04b4\7>\2\2\u04b3\u04b5\5\u00c8"+
		"e\2\u04b4\u04b3\3\2\2\2\u04b4\u04b5\3\2\2\2\u04b5\u04b7\3\2\2\2\u04b6"+
		"\u04b8\7C\2\2\u04b7\u04b6\3\2\2\2\u04b7\u04b8\3\2\2\2\u04b8\u04ba\3\2"+
		"\2\2\u04b9\u04bb\5\u00ceh\2\u04ba\u04b9\3\2\2\2\u04ba\u04bb\3\2\2\2\u04bb"+
		"\u04bc\3\2\2\2\u04bc\u04bd\7?\2\2\u04bd\u00c7\3\2\2\2\u04be\u04c3\5\u00ca"+
		"f\2\u04bf\u04c0\7C\2\2\u04c0\u04c2\5\u00caf\2\u04c1\u04bf\3\2\2\2\u04c2"+
		"\u04c5\3\2\2\2\u04c3\u04c1\3\2\2\2\u04c3\u04c4\3\2\2\2\u04c4\u00c9\3\2"+
		"\2\2\u04c5\u04c3\3\2\2\2\u04c6\u04c8\5\u00ccg\2\u04c7\u04c6\3\2\2\2\u04c8"+
		"\u04cb\3\2\2\2\u04c9\u04c7\3\2\2\2\u04c9\u04ca\3\2\2\2\u04ca\u04cc\3\2"+
		"\2\2\u04cb\u04c9\3\2\2\2\u04cc\u04d2\7i\2\2\u04cd\u04cf\7<\2\2\u04ce\u04d0"+
		"\5\u019c\u00cf\2\u04cf\u04ce\3\2\2\2\u04cf\u04d0\3\2\2\2\u04d0\u04d1\3"+
		"\2\2\2\u04d1\u04d3\7=\2\2\u04d2\u04cd\3\2\2\2\u04d2\u04d3\3\2\2\2\u04d3"+
		"\u04d5\3\2\2\2\u04d4\u04d6\5f\64\2\u04d5\u04d4\3\2\2\2\u04d5\u04d6\3\2"+
		"\2\2\u04d6\u00cb\3\2\2\2\u04d7\u04d8\5\u00f0y\2\u04d8\u00cd\3\2\2\2\u04d9"+
		"\u04dd\7B\2\2\u04da\u04dc\5h\65\2\u04db\u04da\3\2\2\2\u04dc\u04df\3\2"+
		"\2\2\u04dd\u04db\3\2\2\2\u04dd\u04de\3\2\2\2\u04de\u00cf\3\2\2\2\u04df"+
		"\u04dd\3\2\2\2\u04e0\u04e3\5\u00d2j\2\u04e1\u04e3\5\u00e4s\2\u04e2\u04e0"+
		"\3\2\2\2\u04e2\u04e1\3\2\2\2\u04e3\u00d1\3\2\2\2\u04e4\u04e6\5\u00d4k"+
		"\2\u04e5\u04e4\3\2\2\2\u04e6\u04e9\3\2\2\2\u04e7\u04e5\3\2\2\2\u04e7\u04e8"+
		"\3\2\2\2\u04e8\u04ea\3\2\2\2\u04e9\u04e7\3\2\2\2\u04ea\u04eb\7\37\2\2"+
		"\u04eb\u04ed\7i\2\2\u04ec\u04ee\5\\/\2\u04ed\u04ec\3\2\2\2\u04ed\u04ee"+
		"\3\2\2\2\u04ee\u04f0\3\2\2\2\u04ef\u04f1\5\u00d6l\2\u04f0\u04ef\3\2\2"+
		"\2\u04f0\u04f1\3\2\2\2\u04f1\u04f2\3\2\2\2\u04f2\u04f3\5\u00d8m\2\u04f3"+
		"\u00d3\3\2\2\2\u04f4\u04fc\5\u00f0y\2\u04f5\u04fc\7&\2\2\u04f6\u04fc\7"+
		"%\2\2\u04f7\u04fc\7$\2\2\u04f8\u04fc\7\4\2\2\u04f9\u04fc\7)\2\2\u04fa"+
		"\u04fc\7*\2\2\u04fb\u04f4\3\2\2\2\u04fb\u04f5\3\2\2\2\u04fb\u04f6\3\2"+
		"\2\2\u04fb\u04f7\3\2\2\2\u04fb\u04f8\3\2\2\2\u04fb\u04f9\3\2\2\2\u04fb"+
		"\u04fa\3\2\2\2\u04fc\u00d5\3\2\2\2\u04fd\u04fe\7\24\2\2\u04fe\u04ff\5"+
		"d\63\2\u04ff\u00d7\3\2\2\2\u0500\u0504\7>\2\2\u0501\u0503\5\u00dan\2\u0502"+
		"\u0501\3\2\2\2\u0503\u0506\3\2\2\2\u0504\u0502\3\2\2\2\u0504\u0505\3\2"+
		"\2\2\u0505\u0507\3\2\2\2\u0506\u0504\3\2\2\2\u0507\u0508\7?\2\2\u0508"+
		"\u00d9\3\2\2\2\u0509\u050f\5\u00dco\2\u050a\u050f\5\u00e0q\2\u050b\u050f"+
		"\5T+\2\u050c\u050f\5\u00d0i\2\u050d\u050f\7B\2\2\u050e\u0509\3\2\2\2\u050e"+
		"\u050a\3\2\2\2\u050e\u050b\3\2\2\2\u050e\u050c\3\2\2\2\u050e\u050d\3\2"+
		"\2\2\u050f\u00db\3\2\2\2\u0510\u0512\5\u00dep\2\u0511\u0510\3\2\2\2\u0512"+
		"\u0515\3\2\2\2\u0513\u0511\3\2\2\2\u0513\u0514\3\2\2\2\u0514\u0516\3\2"+
		"\2\2\u0515\u0513\3\2\2\2\u0516\u0517\5z>\2\u0517\u0518\5r:\2\u0518\u0519"+
		"\7B\2\2\u0519\u00dd\3\2\2\2\u051a\u051f\5\u00f0y\2\u051b\u051f\7&\2\2"+
		"\u051c\u051f\7)\2\2\u051d\u051f\7\25\2\2\u051e\u051a\3\2\2\2\u051e\u051b"+
		"\3\2\2\2\u051e\u051c\3\2\2\2\u051e\u051d\3\2\2\2\u051f\u00df\3\2\2\2\u0520"+
		"\u0522\5\u00e2r\2\u0521\u0520\3\2\2\2\u0522\u0525\3\2\2\2\u0523\u0521"+
		"\3\2\2\2\u0523\u0524\3\2\2\2\u0524\u0526\3\2\2\2\u0525\u0523\3\2\2\2\u0526"+
		"\u0527\5\u0098M\2\u0527\u0528\5\u00b0Y\2\u0528\u00e1\3\2\2\2\u0529\u0530"+
		"\5\u00f0y\2\u052a\u0530\7&\2\2\u052b\u0530\7\4\2\2\u052c\u0530\7\17\2"+
		"\2\u052d\u0530\7)\2\2\u052e\u0530\7*\2\2\u052f\u0529\3\2\2\2\u052f\u052a"+
		"\3\2\2\2\u052f\u052b\3\2\2\2\u052f\u052c\3\2\2\2\u052f\u052d\3\2\2\2\u052f"+
		"\u052e\3\2\2\2\u0530\u00e3\3\2\2\2\u0531\u0533\5\u00d4k\2\u0532\u0531"+
		"\3\2\2\2\u0533\u0536\3\2\2\2\u0534\u0532\3\2\2\2\u0534\u0535\3\2\2\2\u0535"+
		"\u0537\3\2\2\2\u0536\u0534\3\2\2\2\u0537\u0538\7j\2\2\u0538\u0539\7\37"+
		"\2\2\u0539\u053a\7i\2\2\u053a\u053b\5\u00e6t\2\u053b\u00e5\3\2\2\2\u053c"+
		"\u0540\7>\2\2\u053d\u053f\5\u00e8u\2\u053e\u053d\3\2\2\2\u053f\u0542\3"+
		"\2\2\2\u0540\u053e\3\2\2\2\u0540\u0541\3\2\2\2\u0541\u0543\3\2\2\2\u0542"+
		"\u0540\3\2\2\2\u0543\u0544\7?\2\2\u0544\u00e7\3\2\2\2\u0545\u054b\5\u00ea"+
		"v\2\u0546\u054b\5\u00dco\2\u0547\u054b\5T+\2\u0548\u054b\5\u00d0i\2\u0549"+
		"\u054b\7B\2\2\u054a\u0545\3\2\2\2\u054a\u0546\3\2\2\2\u054a\u0547\3\2"+
		"\2\2\u054a\u0548\3\2\2\2\u054a\u0549\3\2\2\2\u054b\u00e9\3\2\2\2\u054c"+
		"\u054e\5\u00ecw\2\u054d\u054c\3\2\2\2\u054e\u0551\3\2\2\2\u054f\u054d"+
		"\3\2\2\2\u054f\u0550\3\2\2\2\u0550\u0552\3\2\2\2\u0551\u054f\3\2\2\2\u0552"+
		"\u0553\5z>\2\u0553\u0554\7i\2\2\u0554\u0555\7<\2\2\u0555\u0557\7=\2\2"+
		"\u0556\u0558\5\"\22\2\u0557\u0556\3\2\2\2\u0557\u0558\3\2\2\2\u0558\u055a"+
		"\3\2\2\2\u0559\u055b\5\u00eex\2\u055a\u0559\3\2\2\2\u055a\u055b\3\2\2"+
		"\2\u055b\u055c\3\2\2\2\u055c\u055d\7B\2\2\u055d\u00eb\3\2\2\2\u055e\u0562"+
		"\5\u00f0y\2\u055f\u0562\7&\2\2\u0560\u0562\7\4\2\2\u0561\u055e\3\2\2\2"+
		"\u0561\u055f\3\2\2\2\u0561\u0560\3\2\2\2\u0562\u00ed\3\2\2\2\u0563\u0564"+
		"\7\17\2\2\u0564\u0565\5\u00fc\177\2\u0565\u00ef\3\2\2\2\u0566\u056a\5"+
		"\u00f6|\2\u0567\u056a\5\u0102\u0082\2\u0568\u056a\5\u0104\u0083\2\u0569"+
		"\u0566\3\2\2\2\u0569\u0567\3\2\2\2\u0569\u0568\3\2\2\2\u056a\u00f1\3\2"+
		"\2\2\u056b\u056d\5\u00f0y\2\u056c\u056b\3\2\2\2\u056d\u0570\3\2\2\2\u056e"+
		"\u056c\3\2\2\2\u056e\u056f\3\2\2\2\u056f\u0571\3\2\2\2\u0570\u056e\3\2"+
		"\2\2\u0571\u0572\7i\2\2\u0572\u00f3\3\2\2\2\u0573\u0575\5\u00f0y\2\u0574"+
		"\u0573\3\2\2\2\u0575\u0578\3\2\2\2\u0576\u0574\3\2\2\2\u0576\u0577\3\2"+
		"\2\2\u0577\u0579\3\2\2\2\u0578\u0576\3\2\2\2\u0579\u057a\5\u01c4\u00e3"+
		"\2\u057a\u00f5\3\2\2\2\u057b\u057c\7j\2\2\u057c\u057d\58\35\2\u057d\u057f"+
		"\7<\2\2\u057e\u0580\5\u00f8}\2\u057f\u057e\3\2\2\2\u057f\u0580\3\2\2\2"+
		"\u0580\u0581\3\2\2\2\u0581\u0582\7=\2\2\u0582\u00f7\3\2\2\2\u0583\u0588"+
		"\5\u00fa~\2\u0584\u0585\7C\2\2\u0585\u0587\5\u00fa~\2\u0586\u0584\3\2"+
		"\2\2\u0587\u058a\3\2\2\2\u0588\u0586\3\2\2\2\u0588\u0589\3\2\2\2\u0589"+
		"\u00f9\3\2\2\2\u058a\u0588\3\2\2\2\u058b\u058c\7i\2\2\u058c\u058d\7E\2"+
		"\2\u058d\u058e\5\u00fc\177\2\u058e\u00fb\3\2\2\2\u058f\u0593\5\u01c6\u00e4"+
		"\2\u0590\u0593\5\u00fe\u0080\2\u0591\u0593\5\u00f0y\2\u0592\u058f\3\2"+
		"\2\2\u0592\u0590\3\2\2\2\u0592\u0591\3\2\2\2\u0593\u00fd\3\2\2\2\u0594"+
		"\u0596\7>\2\2\u0595\u0597\5\u0100\u0081\2\u0596\u0595\3\2\2\2\u0596\u0597"+
		"\3\2\2\2\u0597\u0599\3\2\2\2\u0598\u059a\7C\2\2\u0599\u0598\3\2\2\2\u0599"+
		"\u059a\3\2\2\2\u059a\u059b\3\2\2\2\u059b\u059c\7?\2\2\u059c\u00ff\3\2"+
		"\2\2\u059d\u05a2\5\u00fc\177\2\u059e\u059f\7C\2\2\u059f\u05a1\5\u00fc"+
		"\177\2\u05a0\u059e\3\2\2\2\u05a1\u05a4\3\2\2\2\u05a2\u05a0\3\2\2\2\u05a2"+
		"\u05a3\3\2\2\2\u05a3\u0101\3\2\2\2\u05a4\u05a2\3\2\2\2\u05a5\u05a6\7j"+
		"\2\2\u05a6\u05a7\58\35\2\u05a7\u0103\3\2\2\2\u05a8\u05a9\7j\2\2\u05a9"+
		"\u05aa\58\35\2\u05aa\u05ab\7<\2\2\u05ab\u05ac\5\u00fc\177\2\u05ac\u05ad"+
		"\7=\2\2\u05ad\u0105\3\2\2\2\u05ae\u05b0\7>\2\2\u05af\u05b1\5\u0108\u0085"+
		"\2\u05b0\u05af\3\2\2\2\u05b0\u05b1\3\2\2\2\u05b1\u05b3\3\2\2\2\u05b2\u05b4"+
		"\7C\2\2\u05b3\u05b2\3\2\2\2\u05b3\u05b4\3\2\2\2\u05b4\u05b5\3\2\2\2\u05b5"+
		"\u05b6\7?\2\2\u05b6\u0107\3\2\2\2\u05b7\u05bc\5x=\2\u05b8\u05b9\7C\2\2"+
		"\u05b9\u05bb\5x=\2\u05ba\u05b8\3\2\2\2\u05bb\u05be\3\2\2\2\u05bc\u05ba"+
		"\3\2\2\2\u05bc\u05bd\3\2\2\2\u05bd\u0109\3\2\2\2\u05be\u05bc\3\2\2\2\u05bf"+
		"\u05c1\7>\2\2\u05c0\u05c2\5\u010c\u0087\2\u05c1\u05c0\3\2\2\2\u05c1\u05c2"+
		"\3\2\2\2\u05c2\u05c3\3\2\2\2\u05c3\u05c4\7?\2\2\u05c4\u010b\3\2\2\2\u05c5"+
		"\u05c9\5\u010e\u0088\2\u05c6\u05c8\5\u010e\u0088\2\u05c7\u05c6\3\2\2\2"+
		"\u05c8\u05cb\3\2\2\2\u05c9\u05c7\3\2\2\2\u05c9\u05ca\3\2\2\2\u05ca\u010d"+
		"\3\2\2\2\u05cb\u05c9\3\2\2\2\u05cc\u05d0\5\u0110\u0089\2\u05cd\u05d0\5"+
		"T+\2\u05ce\u05d0\5\u0114\u008b\2\u05cf\u05cc\3\2\2\2\u05cf\u05cd\3\2\2"+
		"\2\u05cf\u05ce\3\2\2\2\u05d0\u010f\3\2\2\2\u05d1\u05d2\5\u0112\u008a\2"+
		"\u05d2\u05d3\7B\2\2\u05d3\u0111\3\2\2\2\u05d4\u05d6\5\u00a4S\2\u05d5\u05d4"+
		"\3\2\2\2\u05d6\u05d9\3\2\2\2\u05d7\u05d5\3\2\2\2\u05d7\u05d8\3\2\2\2\u05d8"+
		"\u05da\3\2\2\2\u05d9\u05d7\3\2\2\2\u05da\u05db\5z>\2\u05db\u05dc\5r:\2"+
		"\u05dc\u0113\3\2\2\2\u05dd\u05e4\5\u0118\u008d\2\u05de\u05e4\5\u011c\u008f"+
		"\2\u05df\u05e4\5\u0124\u0093\2\u05e0\u05e4\5\u0126\u0094\2\u05e1\u05e4"+
		"\5\u0138\u009d\2\u05e2\u05e4\5\u013e\u00a0\2\u05e3\u05dd\3\2\2\2\u05e3"+
		"\u05de\3\2\2\2\u05e3\u05df\3\2\2\2\u05e3\u05e0\3\2\2\2\u05e3\u05e1\3\2"+
		"\2\2\u05e3\u05e2\3\2\2\2\u05e4\u0115\3\2\2\2\u05e5\u05eb\5\u0118\u008d"+
		"\2\u05e6\u05eb\5\u011e\u0090\2\u05e7\u05eb\5\u0128\u0095\2\u05e8\u05eb"+
		"\5\u013a\u009e\2\u05e9\u05eb\5\u0140\u00a1\2\u05ea\u05e5\3\2\2\2\u05ea"+
		"\u05e6\3\2\2\2\u05ea\u05e7\3\2\2\2\u05ea\u05e8\3\2\2\2\u05ea\u05e9\3\2"+
		"\2\2\u05eb\u0117\3\2\2\2\u05ec\u05f9\5\u010a\u0086\2\u05ed\u05f9\5\u011a"+
		"\u008e\2\u05ee\u05f9\5\u0120\u0091\2\u05ef\u05f9\5\u012a\u0096\2\u05f0"+
		"\u05f9\5\u012c\u0097\2\u05f1\u05f9\5\u013c\u009f\2\u05f2\u05f9\5\u0150"+
		"\u00a9\2\u05f3\u05f9\5\u0152\u00aa\2\u05f4\u05f9\5\u0154\u00ab\2\u05f5"+
		"\u05f9\5\u0158\u00ad\2\u05f6\u05f9\5\u0156\u00ac\2\u05f7\u05f9\5\u015a"+
		"\u00ae\2\u05f8\u05ec\3\2\2\2\u05f8\u05ed\3\2\2\2\u05f8\u05ee\3\2\2\2\u05f8"+
		"\u05ef\3\2\2\2\u05f8\u05f0\3\2\2\2\u05f8\u05f1\3\2\2\2\u05f8\u05f2\3\2"+
		"\2\2\u05f8\u05f3\3\2\2\2\u05f8\u05f4\3\2\2\2\u05f8\u05f5\3\2\2\2\u05f8"+
		"\u05f6\3\2\2\2\u05f8\u05f7\3\2\2\2\u05f9\u0119\3\2\2\2\u05fa\u05fb\7B"+
		"\2\2\u05fb\u011b\3\2\2\2\u05fc\u05fd\7i\2\2\u05fd\u05fe\7K\2\2\u05fe\u05ff"+
		"\5\u0114\u008b\2\u05ff\u011d\3\2\2\2\u0600\u0601\7i\2\2\u0601\u0602\7"+
		"K\2\2\u0602\u0603\5\u0116\u008c\2\u0603\u011f\3\2\2\2\u0604\u0605\5\u0122"+
		"\u0092\2\u0605\u0606\7B\2\2\u0606\u0121\3\2\2\2\u0607\u060f\5\u01b8\u00dd"+
		"\2\u0608\u060f\5\u01e0\u00f1\2\u0609\u060f\5\u01e2\u00f2\2\u060a\u060f"+
		"\5\u01e8\u00f5\2\u060b\u060f\5\u01ec\u00f7\2\u060c\u060f\5\u0196\u00cc"+
		"\2\u060d\u060f\5\u0182\u00c2\2\u060e\u0607\3\2\2\2\u060e\u0608\3\2\2\2"+
		"\u060e\u0609\3\2\2\2\u060e\u060a\3\2\2\2\u060e\u060b\3\2\2\2\u060e\u060c"+
		"\3\2\2\2\u060e\u060d\3\2\2\2\u060f\u0123\3\2\2\2\u0610\u0611\7\31\2\2"+
		"\u0611\u0612\7<\2\2\u0612\u0613\5\u01ac\u00d7\2\u0613\u0614\7=\2\2\u0614"+
		"\u0615\5\u0114\u008b\2\u0615\u0125\3\2\2\2\u0616\u0617\7\31\2\2\u0617"+
		"\u0618\7<\2\2\u0618\u0619\5\u01ac\u00d7\2\u0619\u061a\7=\2\2\u061a\u061b"+
		"\5\u0116\u008c\2\u061b\u061c\7\22\2\2\u061c\u061d\5\u0114\u008b\2\u061d"+
		"\u0127\3\2\2\2\u061e\u061f\7\31\2\2\u061f\u0620\7<\2\2\u0620\u0621\5\u01ac"+
		"\u00d7\2\u0621\u0622\7=\2\2\u0622\u0623\5\u0116\u008c\2\u0623\u0624\7"+
		"\22\2\2\u0624\u0625\5\u0116\u008c\2\u0625\u0129\3\2\2\2\u0626\u0627\7"+
		"\5\2\2\u0627\u0628\5\u01ac\u00d7\2\u0628\u0629\7B\2\2\u0629\u0631\3\2"+
		"\2\2\u062a\u062b\7\5\2\2\u062b\u062c\5\u01ac\u00d7\2\u062c\u062d\7K\2"+
		"\2\u062d\u062e\5\u01ac\u00d7\2\u062e\u062f\7B\2\2\u062f\u0631\3\2\2\2"+
		"\u0630\u0626\3\2\2\2\u0630\u062a\3\2\2\2\u0631\u012b\3\2\2\2\u0632\u0633"+
		"\7,\2\2\u0633\u0634\7<\2\2\u0634\u0635\5\u01ac\u00d7\2\u0635\u0636\7="+
		"\2\2\u0636\u0637\5\u012e\u0098\2\u0637\u012d\3\2\2\2\u0638\u063c\7>\2"+
		"\2\u0639\u063b\5\u0130\u0099\2\u063a\u0639\3\2\2\2\u063b\u063e\3\2\2\2"+
		"\u063c\u063a\3\2\2\2\u063c\u063d\3\2\2\2\u063d\u0642\3\2\2\2\u063e\u063c"+
		"\3\2\2\2\u063f\u0641\5\u0134\u009b\2\u0640\u063f\3\2\2\2\u0641\u0644\3"+
		"\2\2\2\u0642\u0640\3\2\2\2\u0642\u0643\3\2\2\2\u0643\u0645\3\2\2\2\u0644"+
		"\u0642\3\2\2\2\u0645\u0646\7?\2\2\u0646\u012f\3\2\2\2\u0647\u0648\5\u0132"+
		"\u009a\2\u0648\u0649\5\u010c\u0087\2\u0649\u0131\3\2\2\2\u064a\u064e\5"+
		"\u0134\u009b\2\u064b\u064d\5\u0134\u009b\2\u064c\u064b\3\2\2\2\u064d\u0650"+
		"\3\2\2\2\u064e\u064c\3\2\2\2\u064e\u064f\3\2\2\2\u064f\u0133\3\2\2\2\u0650"+
		"\u064e\3\2\2\2\u0651\u0652\7\t\2\2\u0652\u0653\5\u01aa\u00d6\2\u0653\u0654"+
		"\7K\2\2\u0654\u065c\3\2\2\2\u0655\u0656\7\t\2\2\u0656\u0657\5\u0136\u009c"+
		"\2\u0657\u0658\7K\2\2\u0658\u065c\3\2\2\2\u0659\u065a\7\17\2\2\u065a\u065c"+
		"\7K\2\2\u065b\u0651\3\2\2\2\u065b\u0655\3\2\2\2\u065b\u0659\3\2\2\2\u065c"+
		"\u0135\3\2\2\2\u065d\u065e\7i\2\2\u065e\u0137\3\2\2\2\u065f\u0660\7\65"+
		"\2\2\u0660\u0661\7<\2\2\u0661\u0662\5\u01ac\u00d7\2\u0662\u0663\7=\2\2"+
		"\u0663\u0664\5\u0114\u008b\2\u0664\u0139\3\2\2\2\u0665\u0666\7\65\2\2"+
		"\u0666\u0667\7<\2\2\u0667\u0668\5\u01ac\u00d7\2\u0668\u0669\7=\2\2\u0669"+
		"\u066a\5\u0116\u008c\2\u066a\u013b\3\2\2\2\u066b\u066c\7\20\2\2\u066c"+
		"\u066d\5\u0114\u008b\2\u066d\u066e\7\65\2\2\u066e\u066f\7<\2\2\u066f\u0670"+
		"\5\u01ac\u00d7\2\u0670\u0671\7=\2\2\u0671\u0672\7B\2\2\u0672\u013d\3\2"+
		"\2\2\u0673\u0676\5\u0142\u00a2\2\u0674\u0676\5\u014c\u00a7\2\u0675\u0673"+
		"\3\2\2\2\u0675\u0674\3\2\2\2\u0676\u013f\3\2\2\2\u0677\u067a\5\u0144\u00a3"+
		"\2\u0678\u067a\5\u014e\u00a8\2\u0679\u0677\3\2\2\2\u0679\u0678\3\2\2\2"+
		"\u067a\u0141\3\2\2\2\u067b\u067c\7\30\2\2\u067c\u067e\7<\2\2\u067d\u067f"+
		"\5\u0146\u00a4\2\u067e\u067d\3\2\2\2\u067e\u067f\3\2\2\2\u067f\u0680\3"+
		"\2\2\2\u0680\u0682\7B\2\2\u0681\u0683\5\u01ac\u00d7\2\u0682\u0681\3\2"+
		"\2\2\u0682\u0683\3\2\2\2\u0683\u0684\3\2\2\2\u0684\u0686\7B\2\2\u0685"+
		"\u0687\5\u0148\u00a5\2\u0686\u0685\3\2\2\2\u0686\u0687\3\2\2\2\u0687\u0688"+
		"\3\2\2\2\u0688\u0689\7=\2\2\u0689\u068a\5\u0114\u008b\2\u068a\u0143\3"+
		"\2\2\2\u068b\u068c\7\30\2\2\u068c\u068e\7<\2\2\u068d\u068f\5\u0146\u00a4"+
		"\2\u068e\u068d\3\2\2\2\u068e\u068f\3\2\2\2\u068f\u0690\3\2\2\2\u0690\u0692"+
		"\7B\2\2\u0691\u0693\5\u01ac\u00d7\2\u0692\u0691\3\2\2\2\u0692\u0693\3"+
		"\2\2\2\u0693\u0694\3\2\2\2\u0694\u0696\7B\2\2\u0695\u0697\5\u0148\u00a5"+
		"\2\u0696\u0695\3\2\2\2\u0696\u0697\3\2\2\2\u0697\u0698\3\2\2\2\u0698\u0699"+
		"\7=\2\2\u0699\u069a\5\u0116\u008c\2\u069a\u0145\3\2\2\2\u069b\u069e\5"+
		"\u014a\u00a6\2\u069c\u069e\5\u0112\u008a\2\u069d\u069b\3\2\2\2\u069d\u069c"+
		"\3\2\2\2\u069e\u0147\3\2\2\2\u069f\u06a0\5\u014a\u00a6\2\u06a0\u0149\3"+
		"\2\2\2\u06a1\u06a6\5\u0122\u0092\2\u06a2\u06a3\7C\2\2\u06a3\u06a5\5\u0122"+
		"\u0092\2\u06a4\u06a2\3\2\2\2\u06a5\u06a8\3\2\2\2\u06a6\u06a4\3\2\2\2\u06a6"+
		"\u06a7\3\2\2\2\u06a7\u014b\3\2\2\2\u06a8\u06a6\3\2\2\2\u06a9\u06aa\7\30"+
		"\2\2\u06aa\u06ae\7<\2\2\u06ab\u06ad\5\u00a4S\2\u06ac\u06ab\3\2\2\2\u06ad"+
		"\u06b0\3\2\2\2\u06ae\u06ac\3\2\2\2\u06ae\u06af\3\2\2\2\u06af\u06b1\3\2"+
		"\2\2\u06b0\u06ae\3\2\2\2\u06b1\u06b2\5z>\2\u06b2\u06b3\5v<\2\u06b3\u06b4"+
		"\7K\2\2\u06b4\u06b5\5\u01ac\u00d7\2\u06b5\u06b6\7=\2\2\u06b6\u06b7\5\u0114"+
		"\u008b\2\u06b7\u014d\3\2\2\2\u06b8\u06b9\7\30\2\2\u06b9\u06bd\7<\2\2\u06ba"+
		"\u06bc\5\u00a4S\2\u06bb\u06ba\3\2\2\2\u06bc\u06bf\3\2\2\2\u06bd\u06bb"+
		"\3\2\2\2\u06bd\u06be\3\2\2\2\u06be\u06c0\3\2\2\2\u06bf\u06bd\3\2\2\2\u06c0"+
		"\u06c1\5z>\2\u06c1\u06c2\5v<\2\u06c2\u06c3\7K\2\2\u06c3\u06c4\5\u01ac"+
		"\u00d7\2\u06c4\u06c5\7=\2\2\u06c5\u06c6\5\u0116\u008c\2\u06c6\u014f\3"+
		"\2\2\2\u06c7\u06c9\7\7\2\2\u06c8\u06ca\7i\2\2\u06c9\u06c8\3\2\2\2\u06c9"+
		"\u06ca\3\2\2\2\u06ca\u06cb\3\2\2\2\u06cb\u06cc\7B\2\2\u06cc\u0151\3\2"+
		"\2\2\u06cd\u06cf\7\16\2\2\u06ce\u06d0\7i\2\2\u06cf\u06ce\3\2\2\2\u06cf"+
		"\u06d0\3\2\2\2\u06d0\u06d1\3\2\2\2\u06d1\u06d2\7B\2\2\u06d2\u0153\3\2"+
		"\2\2\u06d3\u06d5\7\'\2\2\u06d4\u06d6\5\u01ac\u00d7\2\u06d5\u06d4\3\2\2"+
		"\2\u06d5\u06d6\3\2\2\2\u06d6\u06d7\3\2\2\2\u06d7\u06d8\7B\2\2\u06d8\u0155"+
		"\3\2\2\2\u06d9\u06da\7/\2\2\u06da\u06db\5\u01ac\u00d7\2\u06db\u06dc\7"+
		"B\2\2\u06dc\u0157\3\2\2\2\u06dd\u06de\7-\2\2\u06de\u06df\7<\2\2\u06df"+
		"\u06e0\5\u01ac\u00d7\2\u06e0\u06e1\7=\2\2\u06e1\u06e2\5\u010a\u0086\2"+
		"\u06e2\u0159\3\2\2\2\u06e3\u06e4\7\62\2\2\u06e4\u06e5\5\u010a\u0086\2"+
		"\u06e5\u06e6\5\u015c\u00af\2\u06e6\u06f0\3\2\2\2\u06e7\u06e8\7\62\2\2"+
		"\u06e8\u06ea\5\u010a\u0086\2\u06e9\u06eb\5\u015c\u00af\2\u06ea\u06e9\3"+
		"\2\2\2\u06ea\u06eb\3\2\2\2\u06eb\u06ec\3\2\2\2\u06ec\u06ed\5\u0164\u00b3"+
		"\2\u06ed\u06f0\3\2\2\2\u06ee\u06f0\5\u0166\u00b4\2\u06ef\u06e3\3\2\2\2"+
		"\u06ef\u06e7\3\2\2\2\u06ef\u06ee\3\2\2\2\u06f0\u015b\3\2\2\2\u06f1\u06f5"+
		"\5\u015e\u00b0\2\u06f2\u06f4\5\u015e\u00b0\2\u06f3\u06f2\3\2\2\2\u06f4"+
		"\u06f7\3\2\2\2\u06f5\u06f3\3\2\2\2\u06f5\u06f6\3\2\2\2\u06f6\u015d\3\2"+
		"\2\2\u06f7\u06f5\3\2\2\2\u06f8\u06f9\7\n\2\2\u06f9\u06fa\7<\2\2\u06fa"+
		"\u06fb\5\u0160\u00b1\2\u06fb\u06fc\7=\2\2\u06fc\u06fd\5\u010a\u0086\2"+
		"\u06fd\u015f\3\2\2\2\u06fe\u0700\5\u00a4S\2\u06ff\u06fe\3\2\2\2\u0700"+
		"\u0703\3\2\2\2\u0701\u06ff\3\2\2\2\u0701\u0702\3\2\2\2\u0702\u0704\3\2"+
		"\2\2\u0703\u0701\3\2\2\2\u0704\u0705\5\u0162\u00b2\2\u0705\u0706\5v<\2"+
		"\u0706\u0161\3\2\2\2\u0707\u070c\5\u0082B\2\u0708\u0709\7Y\2\2\u0709\u070b"+
		"\5\22\n\2\u070a\u0708\3\2\2\2\u070b\u070e\3\2\2\2\u070c\u070a\3\2\2\2"+
		"\u070c\u070d\3\2\2\2\u070d\u0163\3\2\2\2\u070e\u070c\3\2\2\2\u070f\u0710"+
		"\7\26\2\2\u0710\u0711\5\u010a\u0086\2\u0711\u0165\3\2\2\2\u0712\u0713"+
		"\7\62\2\2\u0713\u0714\5\u0168\u00b5\2\u0714\u0716\5\u010a\u0086\2\u0715"+
		"\u0717\5\u015c\u00af\2\u0716\u0715\3\2\2\2\u0716\u0717\3\2\2\2\u0717\u0719"+
		"\3\2\2\2\u0718\u071a\5\u0164\u00b3\2\u0719\u0718\3\2\2\2\u0719\u071a\3"+
		"\2\2\2\u071a\u0167\3\2\2\2\u071b\u071c\7<\2\2\u071c\u071e\5\u016a\u00b6"+
		"\2\u071d\u071f\7B\2\2\u071e\u071d\3\2\2\2\u071e\u071f\3\2\2\2\u071f\u0720"+
		"\3\2\2\2\u0720\u0721\7=\2\2\u0721\u0169\3\2\2\2\u0722\u0727\5\u016c\u00b7"+
		"\2\u0723\u0724\7B\2\2\u0724\u0726\5\u016c\u00b7\2\u0725\u0723\3\2\2\2"+
		"\u0726\u0729\3\2\2\2\u0727\u0725\3\2\2\2\u0727\u0728\3\2\2\2\u0728\u016b"+
		"\3\2\2\2\u0729\u0727\3\2\2\2\u072a\u072c\5\u00a4S\2\u072b\u072a\3\2\2"+
		"\2\u072c\u072f\3\2\2\2\u072d\u072b\3\2\2\2\u072d\u072e\3\2\2\2\u072e\u0730"+
		"\3\2\2\2\u072f\u072d\3\2\2\2\u0730\u0731\5z>\2\u0731\u0732\5v<\2\u0732"+
		"\u0733\7E\2\2\u0733\u0734\5\u01ac\u00d7\2\u0734\u016d\3\2\2\2\u0735\u0738"+
		"\5\u017c\u00bf\2\u0736\u0738\5\u01a4\u00d3\2\u0737\u0735\3\2\2\2\u0737"+
		"\u0736\3\2\2\2\u0738\u073c\3\2\2\2\u0739\u073b\5\u0176\u00bc\2\u073a\u0739"+
		"\3\2\2\2\u073b\u073e\3\2\2\2\u073c\u073a\3\2\2\2\u073c\u073d\3\2\2\2\u073d"+
		"\u016f\3\2\2\2\u073e\u073c\3\2\2\2\u073f\u075c\5\2\2\2\u0740\u0744\58"+
		"\35\2\u0741\u0743\5\u01c4\u00e3\2\u0742\u0741\3\2\2\2\u0743\u0746\3\2"+
		"\2\2\u0744\u0742\3\2\2\2\u0744\u0745\3\2\2\2\u0745\u0747\3\2\2\2\u0746"+
		"\u0744\3\2\2\2\u0747\u0748\7D\2\2\u0748\u0749\7\f\2\2\u0749\u075c\3\2"+
		"\2\2\u074a\u074b\7\63\2\2\u074b\u074c\7D\2\2\u074c\u075c\7\f\2\2\u074d"+
		"\u075c\7.\2\2\u074e\u074f\58\35\2\u074f\u0750\7D\2\2\u0750\u0751\7.\2"+
		"\2\u0751\u075c\3\2\2\2\u0752\u0753\7<\2\2\u0753\u0754\5\u01ac\u00d7\2"+
		"\u0754\u0755\7=\2\2\u0755\u075c\3\2\2\2\u0756\u075c\5\u0182\u00c2\2\u0757"+
		"\u075c\5\u018a\u00c6\2\u0758\u075c\5\u0190\u00c9\2\u0759\u075c\5\u0196"+
		"\u00cc\2\u075a\u075c\5\u019e\u00d0\2\u075b\u073f\3\2\2\2\u075b\u0740\3"+
		"\2\2\2\u075b\u074a\3\2\2\2\u075b\u074d\3\2\2\2\u075b\u074e\3\2\2\2\u075b"+
		"\u0752\3\2\2\2\u075b\u0756\3\2\2\2\u075b\u0757\3\2\2\2\u075b\u0758\3\2"+
		"\2\2\u075b\u0759\3\2\2\2\u075b\u075a\3\2\2\2\u075c\u0171\3\2\2\2\u075d"+
		"\u075e\3\2\2\2\u075e\u0173\3\2\2\2\u075f\u077b\5\2\2\2\u0760\u0764\58"+
		"\35\2\u0761\u0763\5\u01c4\u00e3\2\u0762\u0761\3\2\2\2\u0763\u0766\3\2"+
		"\2\2\u0764\u0762\3\2\2\2\u0764\u0765\3\2\2\2\u0765\u0767\3\2\2\2\u0766"+
		"\u0764\3\2\2\2\u0767\u0768\7D\2\2\u0768\u0769\7\f\2\2\u0769\u077b\3\2"+
		"\2\2\u076a\u076b\7\63\2\2\u076b\u076c\7D\2\2\u076c\u077b\7\f\2\2\u076d"+
		"\u077b\7.\2\2\u076e\u076f\58\35\2\u076f\u0770\7D\2\2\u0770\u0771\7.\2"+
		"\2\u0771\u077b\3\2\2\2\u0772\u0773\7<\2\2\u0773\u0774\5\u01ac\u00d7\2"+
		"\u0774\u0775\7=\2\2\u0775\u077b\3\2\2\2\u0776\u077b\5\u0182\u00c2\2\u0777"+
		"\u077b\5\u018a\u00c6\2\u0778\u077b\5\u0196\u00cc\2\u0779\u077b\5\u019e"+
		"\u00d0\2\u077a\u075f\3\2\2\2\u077a\u0760\3\2\2\2\u077a\u076a\3\2\2\2\u077a"+
		"\u076d\3\2\2\2\u077a\u076e\3\2\2\2\u077a\u0772\3\2\2\2\u077a\u0776\3\2"+
		"\2\2\u077a\u0777\3\2\2\2\u077a\u0778\3\2\2\2\u077a\u0779\3\2\2\2\u077b"+
		"\u0175\3\2\2\2\u077c\u0782\5\u0184\u00c3\2\u077d\u0782\5\u018c\u00c7\2"+
		"\u077e\u0782\5\u0192\u00ca\2\u077f\u0782\5\u0198\u00cd\2\u0780\u0782\5"+
		"\u01a0\u00d1\2\u0781\u077c\3\2\2\2\u0781\u077d\3\2\2\2\u0781\u077e\3\2"+
		"\2\2\u0781\u077f\3\2\2\2\u0781\u0780\3\2\2\2\u0782\u0177\3\2\2\2\u0783"+
		"\u0784\3\2\2\2\u0784\u0179\3\2\2\2\u0785\u078a\5\u0184\u00c3\2\u0786\u078a"+
		"\5\u018c\u00c7\2\u0787\u078a\5\u0198\u00cd\2\u0788\u078a\5\u01a0\u00d1"+
		"\2\u0789\u0785\3\2\2\2\u0789\u0786\3\2\2\2\u0789\u0787\3\2\2\2\u0789\u0788"+
		"\3\2\2\2\u078a\u017b\3\2\2\2\u078b\u07b2\5\2\2\2\u078c\u0790\58\35\2\u078d"+
		"\u078f\5\u01c4\u00e3\2\u078e\u078d\3\2\2\2\u078f\u0792\3\2\2\2\u0790\u078e"+
		"\3\2\2\2\u0790\u0791\3\2\2\2\u0791\u0793\3\2\2\2\u0792\u0790\3\2\2\2\u0793"+
		"\u0794\7D\2\2\u0794\u0795\7\f\2\2\u0795\u07b2\3\2\2\2\u0796\u079a\5|?"+
		"\2\u0797\u0799\5\u01c4\u00e3\2\u0798\u0797\3\2\2\2\u0799\u079c\3\2\2\2"+
		"\u079a\u0798\3\2\2\2\u079a\u079b\3\2\2\2\u079b\u079d\3\2\2\2\u079c\u079a"+
		"\3\2\2\2\u079d\u079e\7D\2\2\u079e\u079f\7\f\2\2\u079f\u07b2\3\2\2\2\u07a0"+
		"\u07a1\7\63\2\2\u07a1\u07a2\7D\2\2\u07a2\u07b2\7\f\2\2\u07a3\u07b2\7."+
		"\2\2\u07a4\u07a5\58\35\2\u07a5\u07a6\7D\2\2\u07a6\u07a7\7.\2\2\u07a7\u07b2"+
		"\3\2\2\2\u07a8\u07a9\7<\2\2\u07a9\u07aa\5\u01ac\u00d7\2\u07aa\u07ab\7"+
		"=\2\2\u07ab\u07b2\3\2\2\2\u07ac\u07b2\5\u0186\u00c4\2\u07ad\u07b2\5\u018e"+
		"\u00c8\2\u07ae\u07b2\5\u0194\u00cb\2\u07af\u07b2\5\u019a\u00ce\2\u07b0"+
		"\u07b2\5\u01a2\u00d2\2\u07b1\u078b\3\2\2\2\u07b1\u078c\3\2\2\2\u07b1\u0796"+
		"\3\2\2\2\u07b1\u07a0\3\2\2\2\u07b1\u07a3\3\2\2\2\u07b1\u07a4\3\2\2\2\u07b1"+
		"\u07a8\3\2\2\2\u07b1\u07ac\3\2\2\2\u07b1\u07ad\3\2\2\2\u07b1\u07ae\3\2"+
		"\2\2\u07b1\u07af\3\2\2\2\u07b1\u07b0\3\2\2\2\u07b2\u017d\3\2\2\2\u07b3"+
		"\u07b4\3\2\2\2\u07b4\u017f\3\2\2\2\u07b5\u07db\5\2\2\2\u07b6\u07ba\58"+
		"\35\2\u07b7\u07b9\5\u01c4\u00e3\2\u07b8\u07b7\3\2\2\2\u07b9\u07bc\3\2"+
		"\2\2\u07ba\u07b8\3\2\2\2\u07ba\u07bb\3\2\2\2\u07bb\u07bd\3\2\2\2\u07bc"+
		"\u07ba\3\2\2\2\u07bd\u07be\7D\2\2\u07be\u07bf\7\f\2\2\u07bf\u07db\3\2"+
		"\2\2\u07c0\u07c4\5|?\2\u07c1\u07c3\5\u01c4\u00e3\2\u07c2\u07c1\3\2\2\2"+
		"\u07c3\u07c6\3\2\2\2\u07c4\u07c2\3\2\2\2\u07c4\u07c5\3\2\2\2\u07c5\u07c7"+
		"\3\2\2\2\u07c6\u07c4\3\2\2\2\u07c7\u07c8\7D\2\2\u07c8\u07c9\7\f\2\2\u07c9"+
		"\u07db\3\2\2\2\u07ca\u07cb\7\63\2\2\u07cb\u07cc\7D\2\2\u07cc\u07db\7\f"+
		"\2\2\u07cd\u07db\7.\2\2\u07ce\u07cf\58\35\2\u07cf\u07d0\7D\2\2\u07d0\u07d1"+
		"\7.\2\2\u07d1\u07db\3\2\2\2\u07d2\u07d3\7<\2\2\u07d3\u07d4\5\u01ac\u00d7"+
		"\2\u07d4\u07d5\7=\2\2\u07d5\u07db\3\2\2\2\u07d6\u07db\5\u0186\u00c4\2"+
		"\u07d7\u07db\5\u018e\u00c8\2\u07d8\u07db\5\u019a\u00ce\2\u07d9\u07db\5"+
		"\u01a2\u00d2\2\u07da\u07b5\3\2\2\2\u07da\u07b6\3\2\2\2\u07da\u07c0\3\2"+
		"\2\2\u07da\u07ca\3\2\2\2\u07da\u07cd\3\2\2\2\u07da\u07ce\3\2\2\2\u07da"+
		"\u07d2\3\2\2\2\u07da\u07d6\3\2\2\2\u07da\u07d7\3\2\2\2\u07da\u07d8\3\2"+
		"\2\2\u07da\u07d9\3\2\2\2\u07db\u0181\3\2\2\2\u07dc\u07de\7\"\2\2\u07dd"+
		"\u07df\5,\27\2\u07de\u07dd\3\2\2\2\u07de\u07df\3\2\2\2\u07df\u07e0\3\2"+
		"\2\2\u07e0\u07e5\5\u00f2z\2\u07e1\u07e2\7D\2\2\u07e2\u07e4\5\u00f2z\2"+
		"\u07e3\u07e1\3\2\2\2\u07e4\u07e7\3\2\2\2\u07e5\u07e3\3\2\2\2\u07e5\u07e6"+
		"\3\2\2\2\u07e6\u07e9\3\2\2\2\u07e7\u07e5\3\2\2\2\u07e8\u07ea\5\u0188\u00c5"+
		"\2\u07e9\u07e8\3\2\2\2\u07e9\u07ea\3\2\2\2\u07ea\u07eb\3\2\2\2\u07eb\u07ed"+
		"\7<\2\2\u07ec\u07ee\5\u019c\u00cf\2\u07ed\u07ec\3\2\2\2\u07ed\u07ee\3"+
		"\2\2\2\u07ee\u07ef\3\2\2\2\u07ef\u07f1\7=\2\2\u07f0\u07f2\5f\64\2\u07f1"+
		"\u07f0\3\2\2\2\u07f1\u07f2\3\2\2\2\u07f2\u0818\3\2\2\2\u07f3\u07f4\5<"+
		"\37\2\u07f4\u07f5\7D\2\2\u07f5\u07f7\7\"\2\2\u07f6\u07f8\5,\27\2\u07f7"+
		"\u07f6\3\2\2\2\u07f7\u07f8\3\2\2\2\u07f8\u07f9\3\2\2\2\u07f9\u07fb\5\u00f2"+
		"z\2\u07fa\u07fc\5\u0188\u00c5\2\u07fb\u07fa\3\2\2\2\u07fb\u07fc\3\2\2"+
		"\2\u07fc\u07fd\3\2\2\2\u07fd\u07ff\7<\2\2\u07fe\u0800\5\u019c\u00cf\2"+
		"\u07ff\u07fe\3\2\2\2\u07ff\u0800\3\2\2\2\u0800\u0801\3\2\2\2\u0801\u0803"+
		"\7=\2\2\u0802\u0804\5f\64\2\u0803\u0802\3\2\2\2\u0803\u0804\3\2\2\2\u0804"+
		"\u0818\3\2\2\2\u0805\u0806\5\u016e\u00b8\2\u0806\u0807\7D\2\2\u0807\u0809"+
		"\7\"\2\2\u0808\u080a\5,\27\2\u0809\u0808\3\2\2\2\u0809\u080a\3\2\2\2\u080a"+
		"\u080b\3\2\2\2\u080b\u080d\5\u00f2z\2\u080c\u080e\5\u0188\u00c5\2\u080d"+
		"\u080c\3\2\2\2\u080d\u080e\3\2\2\2\u080e\u080f\3\2\2\2\u080f\u0811\7<"+
		"\2\2\u0810\u0812\5\u019c\u00cf\2\u0811\u0810\3\2\2\2\u0811\u0812\3\2\2"+
		"\2\u0812\u0813\3\2\2\2\u0813\u0815\7=\2\2\u0814\u0816\5f\64\2\u0815\u0814"+
		"\3\2\2\2\u0815\u0816\3\2\2\2\u0816\u0818\3\2\2\2\u0817\u07dc\3\2\2\2\u0817"+
		"\u07f3\3\2\2\2\u0817\u0805\3\2\2\2\u0818\u0183\3\2\2\2\u0819\u081a\7D"+
		"\2\2\u081a\u081c\7\"\2\2\u081b\u081d\5,\27\2\u081c\u081b\3\2\2\2\u081c"+
		"\u081d\3\2\2\2\u081d\u081e\3\2\2\2\u081e\u0820\5\u00f2z\2\u081f\u0821"+
		"\5\u0188\u00c5\2\u0820\u081f\3\2\2\2\u0820\u0821\3\2\2\2\u0821\u0822\3"+
		"\2\2\2\u0822\u0824\7<\2\2\u0823\u0825\5\u019c\u00cf\2\u0824\u0823\3\2"+
		"\2\2\u0824\u0825\3\2\2\2\u0825\u0826\3\2\2\2\u0826\u0828\7=\2\2\u0827"+
		"\u0829\5f\64\2\u0828\u0827\3\2\2\2\u0828\u0829\3\2\2\2\u0829\u0185\3\2"+
		"\2\2\u082a\u082c\7\"\2\2\u082b\u082d\5,\27\2\u082c\u082b\3\2\2\2\u082c"+
		"\u082d\3\2\2\2\u082d\u082e\3\2\2\2\u082e\u0833\5\u00f2z\2\u082f\u0830"+
		"\7D\2\2\u0830\u0832\5\u00f2z\2\u0831\u082f\3\2\2\2\u0832\u0835\3\2\2\2"+
		"\u0833\u0831\3\2\2\2\u0833\u0834\3\2\2\2\u0834\u0837\3\2\2\2\u0835\u0833"+
		"\3\2\2\2\u0836\u0838\5\u0188\u00c5\2\u0837\u0836\3\2\2\2\u0837\u0838\3"+
		"\2\2\2\u0838\u0839\3\2\2\2\u0839\u083b\7<\2\2\u083a\u083c\5\u019c\u00cf"+
		"\2\u083b\u083a\3\2\2\2\u083b\u083c\3\2\2\2\u083c\u083d\3\2\2\2\u083d\u083f"+
		"\7=\2\2\u083e\u0840\5f\64\2\u083f\u083e\3\2\2\2\u083f\u0840\3\2\2\2\u0840"+
		"\u0854\3\2\2\2\u0841\u0842\5<\37\2\u0842\u0843\7D\2\2\u0843\u0845\7\""+
		"\2\2\u0844\u0846\5,\27\2\u0845\u0844\3\2\2\2\u0845\u0846\3\2\2\2\u0846"+
		"\u0847\3\2\2\2\u0847\u0849\5\u00f2z\2\u0848\u084a\5\u0188\u00c5\2\u0849"+
		"\u0848\3\2\2\2\u0849\u084a\3\2\2\2\u084a\u084b\3\2\2\2\u084b\u084d\7<"+
		"\2\2\u084c\u084e\5\u019c\u00cf\2\u084d\u084c\3\2\2\2\u084d\u084e\3\2\2"+
		"\2\u084e\u084f\3\2\2\2\u084f\u0851\7=\2\2\u0850\u0852\5f\64\2\u0851\u0850"+
		"\3\2\2\2\u0851\u0852\3\2\2\2\u0852\u0854\3\2\2\2\u0853\u082a\3\2\2\2\u0853"+
		"\u0841\3\2\2\2\u0854\u0187\3\2\2\2\u0855\u0859\5,\27\2\u0856\u0857\7G"+
		"\2\2\u0857\u0859\7F\2\2\u0858\u0855\3\2\2\2\u0858\u0856\3\2\2\2\u0859"+
		"\u0189\3\2\2\2\u085a\u085b\5\u016e\u00b8\2\u085b\u085c\7D\2\2\u085c\u085d"+
		"\7i\2\2\u085d\u0868\3\2\2\2\u085e\u085f\7+\2\2\u085f\u0860\7D\2\2\u0860"+
		"\u0868\7i\2\2\u0861\u0862\58\35\2\u0862\u0863\7D\2\2\u0863\u0864\7+\2"+
		"\2\u0864\u0865\7D\2\2\u0865\u0866\7i\2\2\u0866\u0868\3\2\2\2\u0867\u085a"+
		"\3\2\2\2\u0867\u085e\3\2\2\2\u0867\u0861\3\2\2\2\u0868\u018b\3\2\2\2\u0869"+
		"\u086a\7D\2\2\u086a\u086b\7i\2\2\u086b\u018d\3\2\2\2\u086c\u086d\7+\2"+
		"\2\u086d\u086e\7D\2\2\u086e\u0876\7i\2\2\u086f\u0870\58\35\2\u0870\u0871"+
		"\7D\2\2\u0871\u0872\7+\2\2\u0872\u0873\7D\2\2\u0873\u0874\7i\2\2\u0874"+
		"\u0876\3\2\2\2\u0875\u086c\3\2\2\2\u0875\u086f\3\2\2\2\u0876\u018f\3\2"+
		"\2\2\u0877\u0878\5<\37\2\u0878\u0879\7@\2\2\u0879\u087a\5\u01ac\u00d7"+
		"\2\u087a\u087b\7A\2\2\u087b\u0882\3\2\2\2\u087c\u087d\5\u0174\u00bb\2"+
		"\u087d\u087e\7@\2\2\u087e\u087f\5\u01ac\u00d7\2\u087f\u0880\7A\2\2\u0880"+
		"\u0882\3\2\2\2\u0881\u0877\3\2\2\2\u0881\u087c\3\2\2\2\u0882\u088a\3\2"+
		"\2\2\u0883\u0884\5\u0172\u00ba\2\u0884\u0885\7@\2\2\u0885\u0886\5\u01ac"+
		"\u00d7\2\u0886\u0887\7A\2\2\u0887\u0889\3\2\2\2\u0888\u0883\3\2\2\2\u0889"+
		"\u088c\3\2\2\2\u088a\u0888\3\2\2\2\u088a\u088b\3\2\2\2\u088b\u0191\3\2"+
		"\2\2\u088c\u088a\3\2\2\2\u088d\u088e\5\u017a\u00be\2\u088e\u088f\7@\2"+
		"\2\u088f\u0890\5\u01ac\u00d7\2\u0890\u0891\7A\2\2\u0891\u0899\3\2\2\2"+
		"\u0892\u0893\5\u0178\u00bd\2\u0893\u0894\7@\2\2\u0894\u0895\5\u01ac\u00d7"+
		"\2\u0895\u0896\7A\2\2\u0896\u0898\3\2\2\2\u0897\u0892\3\2\2\2\u0898\u089b"+
		"\3\2\2\2\u0899\u0897\3\2\2\2\u0899\u089a\3\2\2\2\u089a\u0193\3\2\2\2\u089b"+
		"\u0899\3\2\2\2\u089c\u089d\5<\37\2\u089d\u089e\7@\2\2\u089e\u089f\5\u01ac"+
		"\u00d7\2\u089f\u08a0\7A\2\2\u08a0\u08a7\3\2\2\2\u08a1\u08a2\5\u0180\u00c1"+
		"\2\u08a2\u08a3\7@\2\2\u08a3\u08a4\5\u01ac\u00d7\2\u08a4\u08a5\7A\2\2\u08a5"+
		"\u08a7\3\2\2\2\u08a6\u089c\3\2\2\2\u08a6\u08a1\3\2\2\2\u08a7\u08af\3\2"+
		"\2\2\u08a8\u08a9\5\u017e\u00c0\2\u08a9\u08aa\7@\2\2\u08aa\u08ab\5\u01ac"+
		"\u00d7\2\u08ab\u08ac\7A\2\2\u08ac\u08ae\3\2\2\2\u08ad\u08a8\3\2\2\2\u08ae"+
		"\u08b1\3\2\2\2\u08af\u08ad\3\2\2\2\u08af\u08b0\3\2\2\2\u08b0\u0195\3\2"+
		"\2\2\u08b1\u08af\3\2\2\2\u08b2\u08b3\5> \2\u08b3\u08b5\7<\2\2\u08b4\u08b6"+
		"\5\u019c\u00cf\2\u08b5\u08b4\3\2\2\2\u08b5\u08b6\3\2\2\2\u08b6\u08b7\3"+
		"\2\2\2\u08b7\u08b8\7=\2\2\u08b8\u08f7\3\2\2\2\u08b9\u08ba\58\35\2\u08ba"+
		"\u08bc\7D\2\2\u08bb\u08bd\5,\27\2\u08bc\u08bb\3\2\2\2\u08bc\u08bd\3\2"+
		"\2\2\u08bd\u08be\3\2\2\2\u08be\u08bf\7i\2\2\u08bf\u08c1\7<\2\2\u08c0\u08c2"+
		"\5\u019c\u00cf\2\u08c1\u08c0\3\2\2\2\u08c1\u08c2\3\2\2\2\u08c2\u08c3\3"+
		"\2\2\2\u08c3\u08c4\7=\2\2\u08c4\u08f7\3\2\2\2\u08c5\u08c6\5<\37\2\u08c6"+
		"\u08c8\7D\2\2\u08c7\u08c9\5,\27\2\u08c8\u08c7\3\2\2\2\u08c8\u08c9\3\2"+
		"\2\2\u08c9\u08ca\3\2\2\2\u08ca\u08cb\7i\2\2\u08cb\u08cd\7<\2\2\u08cc\u08ce"+
		"\5\u019c\u00cf\2\u08cd\u08cc\3\2\2\2\u08cd\u08ce\3\2\2\2\u08ce\u08cf\3"+
		"\2\2\2\u08cf\u08d0\7=\2\2\u08d0\u08f7\3\2\2\2\u08d1\u08d2\5\u016e\u00b8"+
		"\2\u08d2\u08d4\7D\2\2\u08d3\u08d5\5,\27\2\u08d4\u08d3\3\2\2\2\u08d4\u08d5"+
		"\3\2\2\2\u08d5\u08d6\3\2\2\2\u08d6\u08d7\7i\2\2\u08d7\u08d9\7<\2\2\u08d8"+
		"\u08da\5\u019c\u00cf\2\u08d9\u08d8\3\2\2\2\u08d9\u08da\3\2\2\2\u08da\u08db"+
		"\3\2\2\2\u08db\u08dc\7=\2\2\u08dc\u08f7\3\2\2\2\u08dd\u08de\7+\2\2\u08de"+
		"\u08e0\7D\2\2\u08df\u08e1\5,\27\2\u08e0\u08df\3\2\2\2\u08e0\u08e1\3\2"+
		"\2\2\u08e1\u08e2\3\2\2\2\u08e2\u08e3\7i\2\2\u08e3\u08e5\7<\2\2\u08e4\u08e6"+
		"\5\u019c\u00cf\2\u08e5\u08e4\3\2\2\2\u08e5\u08e6\3\2\2\2\u08e6\u08e7\3"+
		"\2\2\2\u08e7\u08f7\7=\2\2\u08e8\u08e9\58\35\2\u08e9\u08ea\7D\2\2\u08ea"+
		"\u08eb\7+\2\2\u08eb\u08ed\7D\2\2\u08ec\u08ee\5,\27\2\u08ed\u08ec\3\2\2"+
		"\2\u08ed\u08ee\3\2\2\2\u08ee\u08ef\3\2\2\2\u08ef\u08f0\7i\2\2\u08f0\u08f2"+
		"\7<\2\2\u08f1\u08f3\5\u019c\u00cf\2\u08f2\u08f1\3\2\2\2\u08f2\u08f3\3"+
		"\2\2\2\u08f3\u08f4\3\2\2\2\u08f4\u08f5\7=\2\2\u08f5\u08f7\3\2\2\2\u08f6"+
		"\u08b2\3\2\2\2\u08f6\u08b9\3\2\2\2\u08f6\u08c5\3\2\2\2\u08f6\u08d1\3\2"+
		"\2\2\u08f6\u08dd\3\2\2\2\u08f6\u08e8\3\2\2\2\u08f7\u0197\3\2\2\2\u08f8"+
		"\u08fa\7D\2\2\u08f9\u08fb\5,\27\2\u08fa\u08f9\3\2\2\2\u08fa\u08fb\3\2"+
		"\2\2\u08fb\u08fc\3\2\2\2\u08fc\u08fd\7i\2\2\u08fd\u08ff\7<\2\2\u08fe\u0900"+
		"\5\u019c\u00cf\2\u08ff\u08fe\3\2\2\2\u08ff\u0900\3\2\2\2\u0900\u0901\3"+
		"\2\2\2\u0901\u0902\7=\2\2\u0902\u0199\3\2\2\2\u0903\u0904\5> \2\u0904"+
		"\u0906\7<\2\2\u0905\u0907\5\u019c\u00cf\2\u0906\u0905\3\2\2\2\u0906\u0907"+
		"\3\2\2\2\u0907\u0908\3\2\2\2\u0908\u0909\7=\2\2\u0909\u093c\3\2\2\2\u090a"+
		"\u090b\58\35\2\u090b\u090d\7D\2\2\u090c\u090e\5,\27\2\u090d\u090c\3\2"+
		"\2\2\u090d\u090e\3\2\2\2\u090e\u090f\3\2\2\2\u090f\u0910\7i\2\2\u0910"+
		"\u0912\7<\2\2\u0911\u0913\5\u019c\u00cf\2\u0912\u0911\3\2\2\2\u0912\u0913"+
		"\3\2\2\2\u0913\u0914\3\2\2\2\u0914\u0915\7=\2\2\u0915\u093c\3\2\2\2\u0916"+
		"\u0917\5<\37\2\u0917\u0919\7D\2\2\u0918\u091a\5,\27\2\u0919\u0918\3\2"+
		"\2\2\u0919\u091a\3\2\2\2\u091a\u091b\3\2\2\2\u091b\u091c\7i\2\2\u091c"+
		"\u091e\7<\2\2\u091d\u091f\5\u019c\u00cf\2\u091e\u091d\3\2\2\2\u091e\u091f"+
		"\3\2\2\2\u091f\u0920\3\2\2\2\u0920\u0921\7=\2\2\u0921\u093c\3\2\2\2\u0922"+
		"\u0923\7+\2\2\u0923\u0925\7D\2\2\u0924\u0926\5,\27\2\u0925\u0924\3\2\2"+
		"\2\u0925\u0926\3\2\2\2\u0926\u0927\3\2\2\2\u0927\u0928\7i\2\2\u0928\u092a"+
		"\7<\2\2\u0929\u092b\5\u019c\u00cf\2\u092a\u0929\3\2\2\2\u092a\u092b\3"+
		"\2\2\2\u092b\u092c\3\2\2\2\u092c\u093c\7=\2\2\u092d\u092e\58\35\2\u092e"+
		"\u092f\7D\2\2\u092f\u0930\7+\2\2\u0930\u0932\7D\2\2\u0931\u0933\5,\27"+
		"\2\u0932\u0931\3\2\2\2\u0932\u0933\3\2\2\2\u0933\u0934\3\2\2\2\u0934\u0935"+
		"\7i\2\2\u0935\u0937\7<\2\2\u0936\u0938\5\u019c\u00cf\2\u0937\u0936\3\2"+
		"\2\2\u0937\u0938\3\2\2\2\u0938\u0939\3\2\2\2\u0939\u093a\7=\2\2\u093a"+
		"\u093c\3\2\2\2\u093b\u0903\3\2\2\2\u093b\u090a\3\2\2\2\u093b\u0916\3\2"+
		"\2\2\u093b\u0922\3\2\2\2\u093b\u092d\3\2\2\2\u093c\u019b\3\2\2\2\u093d"+
		"\u0942\5\u01ac\u00d7\2\u093e\u093f\7C\2\2\u093f\u0941\5\u01ac\u00d7\2"+
		"\u0940\u093e\3\2\2\2\u0941\u0944\3\2\2\2\u0942\u0940\3\2\2\2\u0942\u0943"+
		"\3\2\2\2\u0943\u019d\3\2\2\2\u0944\u0942\3\2\2\2\u0945\u0946\5<\37\2\u0946"+
		"\u0948\7]\2\2\u0947\u0949\5,\27\2\u0948\u0947\3\2\2\2\u0948\u0949\3\2"+
		"\2\2\u0949\u094a\3\2\2\2\u094a\u094b\7i\2\2\u094b\u0975\3\2\2\2\u094c"+
		"\u094d\5\16\b\2\u094d\u094f\7]\2\2\u094e\u0950\5,\27\2\u094f\u094e\3\2"+
		"\2\2\u094f\u0950\3\2\2\2\u0950\u0951\3\2\2\2\u0951\u0952\7i\2\2\u0952"+
		"\u0975\3\2\2\2\u0953\u0954\5\u016e\u00b8\2\u0954\u0956\7]\2\2\u0955\u0957"+
		"\5,\27\2\u0956\u0955\3\2\2\2\u0956\u0957\3\2\2\2\u0957\u0958\3\2\2\2\u0958"+
		"\u0959\7i\2\2\u0959\u0975\3\2\2\2\u095a\u095b\7+\2\2\u095b\u095d\7]\2"+
		"\2\u095c\u095e\5,\27\2\u095d\u095c\3\2\2\2\u095d\u095e\3\2\2\2\u095e\u095f"+
		"\3\2\2\2\u095f\u0975\7i\2\2\u0960\u0961\58\35\2\u0961\u0962\7D\2\2\u0962"+
		"\u0963\7+\2\2\u0963\u0965\7]\2\2\u0964\u0966\5,\27\2\u0965\u0964\3\2\2"+
		"\2\u0965\u0966\3\2\2\2\u0966\u0967\3\2\2\2\u0967\u0968\7i\2\2\u0968\u0975"+
		"\3\2\2\2\u0969\u096a\5\22\n\2\u096a\u096c\7]\2\2\u096b\u096d\5,\27\2\u096c"+
		"\u096b\3\2\2\2\u096c\u096d\3\2\2\2\u096d\u096e\3\2\2\2\u096e\u096f\7\""+
		"\2\2\u096f\u0975\3\2\2\2\u0970\u0971\5 \21\2\u0971\u0972\7]\2\2\u0972"+
		"\u0973\7\"\2\2\u0973\u0975\3\2\2\2\u0974\u0945\3\2\2\2\u0974\u094c\3\2"+
		"\2\2\u0974\u0953\3\2\2\2\u0974\u095a\3\2\2\2\u0974\u0960\3\2\2\2\u0974"+
		"\u0969\3\2\2\2\u0974\u0970\3\2\2\2\u0975\u019f\3\2\2\2\u0976\u0978\7]"+
		"\2\2\u0977\u0979\5,\27\2\u0978\u0977\3\2\2\2\u0978\u0979\3\2\2\2\u0979"+
		"\u097a\3\2\2\2\u097a\u097b\7i\2\2\u097b\u01a1\3\2\2\2\u097c\u097d\5<\37"+
		"\2\u097d\u097f\7]\2\2";
	private static final String _serializedATNSegment1 =
		"\u097e\u0980\5,\27\2\u097f\u097e\3\2\2\2\u097f\u0980\3\2\2\2\u0980\u0981"+
		"\3\2\2\2\u0981\u0982\7i\2\2\u0982\u09a5\3\2\2\2\u0983\u0984\5\16\b\2\u0984"+
		"\u0986\7]\2\2\u0985\u0987\5,\27\2\u0986\u0985\3\2\2\2\u0986\u0987\3\2"+
		"\2\2\u0987\u0988\3\2\2\2\u0988\u0989\7i\2\2\u0989\u09a5\3\2\2\2\u098a"+
		"\u098b\7+\2\2\u098b\u098d\7]\2\2\u098c\u098e\5,\27\2\u098d\u098c\3\2\2"+
		"\2\u098d\u098e\3\2\2\2\u098e\u098f\3\2\2\2\u098f\u09a5\7i\2\2\u0990\u0991"+
		"\58\35\2\u0991\u0992\7D\2\2\u0992\u0993\7+\2\2\u0993\u0995\7]\2\2\u0994"+
		"\u0996\5,\27\2\u0995\u0994\3\2\2\2\u0995\u0996\3\2\2\2\u0996\u0997\3\2"+
		"\2\2\u0997\u0998\7i\2\2\u0998\u09a5\3\2\2\2\u0999\u099a\5\22\n\2\u099a"+
		"\u099c\7]\2\2\u099b\u099d\5,\27\2\u099c\u099b\3\2\2\2\u099c\u099d\3\2"+
		"\2\2\u099d\u099e\3\2\2\2\u099e\u099f\7\"\2\2\u099f\u09a5\3\2\2\2\u09a0"+
		"\u09a1\5 \21\2\u09a1\u09a2\7]\2\2\u09a2\u09a3\7\"\2\2\u09a3\u09a5\3\2"+
		"\2\2\u09a4\u097c\3\2\2\2\u09a4\u0983\3\2\2\2\u09a4\u098a\3\2\2\2\u09a4"+
		"\u0990\3\2\2\2\u09a4\u0999\3\2\2\2\u09a4\u09a0\3\2\2\2\u09a5\u01a3\3\2"+
		"\2\2\u09a6\u09a7\7\"\2\2\u09a7\u09a8\5\6\4\2\u09a8\u09aa\5\u01a6\u00d4"+
		"\2\u09a9\u09ab\5\"\22\2\u09aa\u09a9\3\2\2\2\u09aa\u09ab\3\2\2\2\u09ab"+
		"\u09bd\3\2\2\2\u09ac\u09ad\7\"\2\2\u09ad\u09ae\5\20\t\2\u09ae\u09b0\5"+
		"\u01a6\u00d4\2\u09af\u09b1\5\"\22\2\u09b0\u09af\3\2\2\2\u09b0\u09b1\3"+
		"\2\2\2\u09b1\u09bd\3\2\2\2\u09b2\u09b3\7\"\2\2\u09b3\u09b4\5\6\4\2\u09b4"+
		"\u09b5\5\"\22\2\u09b5\u09b6\5\u0106\u0084\2\u09b6\u09bd\3\2\2\2\u09b7"+
		"\u09b8\7\"\2\2\u09b8\u09b9\5\20\t\2\u09b9\u09ba\5\"\22\2\u09ba\u09bb\5"+
		"\u0106\u0084\2\u09bb\u09bd\3\2\2\2\u09bc\u09a6\3\2\2\2\u09bc\u09ac\3\2"+
		"\2\2\u09bc\u09b2\3\2\2\2\u09bc\u09b7\3\2\2\2\u09bd\u01a5\3\2\2\2\u09be"+
		"\u09c2\5\u01a8\u00d5\2\u09bf\u09c1\5\u01a8\u00d5\2\u09c0\u09bf\3\2\2\2"+
		"\u09c1\u09c4\3\2\2\2\u09c2\u09c0\3\2\2\2\u09c2\u09c3\3\2\2\2\u09c3\u01a7"+
		"\3\2\2\2\u09c4\u09c2\3\2\2\2\u09c5\u09c7\5\u00f0y\2\u09c6\u09c5\3\2\2"+
		"\2\u09c7\u09ca\3\2\2\2\u09c8\u09c6\3\2\2\2\u09c8\u09c9\3\2\2\2\u09c9\u09cb"+
		"\3\2\2\2\u09ca\u09c8\3\2\2\2\u09cb\u09cc\7@\2\2\u09cc\u09cd\5\u01ac\u00d7"+
		"\2\u09cd\u09ce\7A\2\2\u09ce\u01a9\3\2\2\2\u09cf\u09d0\5\u01ac\u00d7\2"+
		"\u09d0\u01ab\3\2\2\2\u09d1\u09d4\5\u01ae\u00d8\2\u09d2\u09d4\5\u01b6\u00dc"+
		"\2\u09d3\u09d1\3\2\2\2\u09d3\u09d2\3\2\2\2\u09d4\u01ad\3\2\2\2\u09d5\u09d6"+
		"\5\u01b0\u00d9\2\u09d6\u09d7\7\\\2\2\u09d7\u09d8\5\u01b4\u00db\2\u09d8"+
		"\u01af\3\2\2\2\u09d9\u09e4\7i\2\2\u09da\u09dc\7<\2\2\u09db\u09dd\5\u009e"+
		"P\2\u09dc\u09db\3\2\2\2\u09dc\u09dd\3\2\2\2\u09dd\u09de\3\2\2\2\u09de"+
		"\u09e4\7=\2\2\u09df\u09e0\7<\2\2\u09e0\u09e1\5\u01b2\u00da\2\u09e1\u09e2"+
		"\7=\2\2\u09e2\u09e4\3\2\2\2\u09e3\u09d9\3\2\2\2\u09e3\u09da\3\2\2\2\u09e3"+
		"\u09df\3\2\2\2\u09e4\u01b1\3\2\2\2\u09e5\u09ea\7i\2\2\u09e6\u09e7\7C\2"+
		"\2\u09e7\u09e9\7i\2\2\u09e8\u09e6\3\2\2\2\u09e9\u09ec\3\2\2\2\u09ea\u09e8"+
		"\3\2\2\2\u09ea\u09eb\3\2\2\2\u09eb\u01b3\3\2\2\2\u09ec\u09ea\3\2\2\2\u09ed"+
		"\u09f0\5\u01ac\u00d7\2\u09ee\u09f0\5\u010a\u0086\2\u09ef\u09ed\3\2\2\2"+
		"\u09ef\u09ee\3\2\2\2\u09f0\u01b5\3\2\2\2\u09f1\u09f4\5\u01c6\u00e4\2\u09f2"+
		"\u09f4\5\u01b8\u00dd\2\u09f3\u09f1\3\2\2\2\u09f3\u09f2\3\2\2\2\u09f4\u01b7"+
		"\3\2\2\2\u09f5\u09f6\5\u01ba\u00de\2\u09f6\u09f7\5\u01bc\u00df\2\u09f7"+
		"\u09f8\5\u01ac\u00d7\2\u09f8\u01b9\3\2\2\2\u09f9\u09fd\5<\37\2\u09fa\u09fd"+
		"\5\u018a\u00c6\2\u09fb\u09fd\5\u0190\u00c9\2\u09fc\u09f9\3\2\2\2\u09fc"+
		"\u09fa\3\2\2\2\u09fc\u09fb\3\2\2\2\u09fd\u01bb\3\2\2\2\u09fe\u09ff\t\5"+
		"\2\2\u09ff\u01bd\3\2\2\2\u0a00\u0a01\t\6\2\2\u0a01\u01bf\3\2\2\2\u0a02"+
		"\u0a03\t\7\2\2\u0a03\u01c1\3\2\2\2\u0a04\u0a05\t\b\2\2\u0a05\u01c3\3\2"+
		"\2\2\u0a06\u0a07\7\3\2\2\u0a07\u01c5\3\2\2\2\u0a08\u0a16\5\u01c8\u00e5"+
		"\2\u0a09\u0a0a\5\u01c8\u00e5\2\u0a0a\u0a0b\7J\2\2\u0a0b\u0a0c\5\u01ac"+
		"\u00d7\2\u0a0c\u0a0d\7K\2\2\u0a0d\u0a0e\5\u01c6\u00e4\2\u0a0e\u0a16\3"+
		"\2\2\2\u0a0f\u0a10\5\u01c8\u00e5\2\u0a10\u0a11\7J\2\2\u0a11\u0a12\5\u01ac"+
		"\u00d7\2\u0a12\u0a13\7K\2\2\u0a13\u0a14\5\u01ae\u00d8\2\u0a14\u0a16\3"+
		"\2\2\2\u0a15\u0a08\3\2\2\2\u0a15\u0a09\3\2\2\2\u0a15\u0a0f\3\2\2\2\u0a16"+
		"\u01c7\3\2\2\2\u0a17\u0a18\b\u00e5\1\2\u0a18\u0a19\5\u01ca\u00e6\2\u0a19"+
		"\u0a1f\3\2\2\2\u0a1a\u0a1b\f\3\2\2\u0a1b\u0a1c\7Q\2\2\u0a1c\u0a1e\5\u01ca"+
		"\u00e6\2\u0a1d\u0a1a\3\2\2\2\u0a1e\u0a21\3\2\2\2\u0a1f\u0a1d\3\2\2\2\u0a1f"+
		"\u0a20\3\2\2\2\u0a20\u01c9\3\2\2\2\u0a21\u0a1f\3\2\2\2\u0a22\u0a23\b\u00e6"+
		"\1\2\u0a23\u0a24\5\u01cc\u00e7\2\u0a24\u0a2a\3\2\2\2\u0a25\u0a26\f\3\2"+
		"\2\u0a26\u0a27\7P\2\2\u0a27\u0a29\5\u01cc\u00e7\2\u0a28\u0a25\3\2\2\2"+
		"\u0a29\u0a2c\3\2\2\2\u0a2a\u0a28\3\2\2\2\u0a2a\u0a2b\3\2\2\2\u0a2b\u01cb"+
		"\3\2\2\2\u0a2c\u0a2a\3\2\2\2\u0a2d\u0a2e\b\u00e7\1\2\u0a2e\u0a2f\5\u01ce"+
		"\u00e8\2\u0a2f\u0a35\3\2\2\2\u0a30\u0a31\f\3\2\2\u0a31\u0a32\7Y\2\2\u0a32"+
		"\u0a34\5\u01ce\u00e8\2\u0a33\u0a30\3\2\2\2\u0a34\u0a37\3\2\2\2\u0a35\u0a33"+
		"\3\2\2\2\u0a35\u0a36\3\2\2\2\u0a36\u01cd\3\2\2\2\u0a37\u0a35\3\2\2\2\u0a38"+
		"\u0a39\b\u00e8\1\2\u0a39\u0a3a\5\u01d0\u00e9\2\u0a3a\u0a40\3\2\2\2\u0a3b"+
		"\u0a3c\f\3\2\2\u0a3c\u0a3d\7Z\2\2\u0a3d\u0a3f\5\u01d0\u00e9\2\u0a3e\u0a3b"+
		"\3\2\2\2\u0a3f\u0a42\3\2\2\2\u0a40\u0a3e\3\2\2\2\u0a40\u0a41\3\2\2\2\u0a41"+
		"\u01cf\3\2\2\2\u0a42\u0a40\3\2\2\2\u0a43\u0a44\b\u00e9\1\2\u0a44\u0a45"+
		"\5\u01d2\u00ea\2\u0a45\u0a4b\3\2\2\2\u0a46\u0a47\f\3\2\2\u0a47\u0a48\7"+
		"X\2\2\u0a48\u0a4a\5\u01d2\u00ea\2\u0a49\u0a46\3\2\2\2\u0a4a\u0a4d\3\2"+
		"\2\2\u0a4b\u0a49\3\2\2\2\u0a4b\u0a4c\3\2\2\2\u0a4c\u01d1\3\2\2\2\u0a4d"+
		"\u0a4b\3\2\2\2\u0a4e\u0a4f\b\u00ea\1\2\u0a4f\u0a50\5\u01d4\u00eb\2\u0a50"+
		"\u0a59\3\2\2\2\u0a51\u0a52\f\4\2\2\u0a52\u0a53\7L\2\2\u0a53\u0a58\5\u01d4"+
		"\u00eb\2\u0a54\u0a55\f\3\2\2\u0a55\u0a56\7O\2\2\u0a56\u0a58\5\u01d4\u00eb"+
		"\2\u0a57\u0a51\3\2\2\2\u0a57\u0a54\3\2\2\2\u0a58\u0a5b\3\2\2\2\u0a59\u0a57"+
		"\3\2\2\2\u0a59\u0a5a\3\2\2\2\u0a5a\u01d3\3\2\2\2\u0a5b\u0a59\3\2\2\2\u0a5c"+
		"\u0a5d\b\u00eb\1\2\u0a5d\u0a5e\5\u01d6\u00ec\2\u0a5e\u0a75\3\2\2\2\u0a5f"+
		"\u0a60\f\7\2\2\u0a60\u0a61\5\u01c0\u00e1\2\u0a61\u0a62\5\u01d6\u00ec\2"+
		"\u0a62\u0a74\3\2\2\2\u0a63\u0a64\f\6\2\2\u0a64\u0a65\5\u01c0\u00e1\2\u0a65"+
		"\u0a66\5\u01d6\u00ec\2\u0a66\u0a74\3\2\2\2\u0a67\u0a68\f\5\2\2\u0a68\u0a69"+
		"\5\u01c0\u00e1\2\u0a69\u0a6a\5\u01d6\u00ec\2\u0a6a\u0a74\3\2\2\2\u0a6b"+
		"\u0a6c\f\4\2\2\u0a6c\u0a6d\5\u01c0\u00e1\2\u0a6d\u0a6e\5\u01d6\u00ec\2"+
		"\u0a6e\u0a74\3\2\2\2\u0a6f\u0a70\f\3\2\2\u0a70\u0a71\5\u01c0\u00e1\2\u0a71"+
		"\u0a72\5\16\b\2\u0a72\u0a74\3\2\2\2\u0a73\u0a5f\3\2\2\2\u0a73\u0a63\3"+
		"\2\2\2\u0a73\u0a67\3\2\2\2\u0a73\u0a6b\3\2\2\2\u0a73\u0a6f\3\2\2\2\u0a74"+
		"\u0a77\3\2\2\2\u0a75\u0a73\3\2\2\2\u0a75\u0a76\3\2\2\2\u0a76\u01d5\3\2"+
		"\2\2\u0a77\u0a75\3\2\2\2\u0a78\u0a79\b\u00ec\1\2\u0a79\u0a7a\5\u01da\u00ee"+
		"\2\u0a7a\u0a89\3\2\2\2\u0a7b\u0a7c\f\5\2\2\u0a7c\u0a7d\5\u01d8\u00ed\2"+
		"\u0a7d\u0a7e\5\u01da\u00ee\2\u0a7e\u0a88\3\2\2\2\u0a7f\u0a80\f\4\2\2\u0a80"+
		"\u0a81\5\u01d8\u00ed\2\u0a81\u0a82\5\u01da\u00ee\2\u0a82\u0a88\3\2\2\2"+
		"\u0a83\u0a84\f\3\2\2\u0a84\u0a85\5\u01d8\u00ed\2\u0a85\u0a86\5\u01da\u00ee"+
		"\2\u0a86\u0a88\3\2\2\2\u0a87\u0a7b\3\2\2\2\u0a87\u0a7f\3\2\2\2\u0a87\u0a83"+
		"\3\2\2\2\u0a88\u0a8b\3\2\2\2\u0a89\u0a87\3\2\2\2\u0a89\u0a8a\3\2\2\2\u0a8a"+
		"\u01d7\3\2\2\2\u0a8b\u0a89\3\2\2\2\u0a8c\u0a8d\7G\2\2\u0a8d\u0a94\7G\2"+
		"\2\u0a8e\u0a8f\7F\2\2\u0a8f\u0a94\7F\2\2\u0a90\u0a91\7F\2\2\u0a91\u0a92"+
		"\7F\2\2\u0a92\u0a94\7F\2\2\u0a93\u0a8c\3\2\2\2\u0a93\u0a8e\3\2\2\2\u0a93"+
		"\u0a90\3\2\2\2\u0a94\u01d9\3\2\2\2\u0a95\u0a96\b\u00ee\1\2\u0a96\u0a97"+
		"\5\u01dc\u00ef\2\u0a97\u0aa2\3\2\2\2\u0a98\u0a99\f\4\2\2\u0a99\u0a9a\5"+
		"\u01be\u00e0\2\u0a9a\u0a9b\5\u01dc\u00ef\2\u0a9b\u0aa1\3\2\2\2\u0a9c\u0a9d"+
		"\f\3\2\2\u0a9d\u0a9e\5\u01be\u00e0\2\u0a9e\u0a9f\5\u01dc\u00ef\2\u0a9f"+
		"\u0aa1\3\2\2\2\u0aa0\u0a98\3\2\2\2\u0aa0\u0a9c\3\2\2\2\u0aa1\u0aa4\3\2"+
		"\2\2\u0aa2\u0aa0\3\2\2\2\u0aa2\u0aa3\3\2\2\2\u0aa3\u01db\3\2\2\2\u0aa4"+
		"\u0aa2\3\2\2\2\u0aa5\u0aa6\b\u00ef\1\2\u0aa6\u0aa7\5\u01de\u00f0\2\u0aa7"+
		"\u0ab6\3\2\2\2\u0aa8\u0aa9\f\5\2\2\u0aa9\u0aaa\5\u01c2\u00e2\2\u0aaa\u0aab"+
		"\5\u01de\u00f0\2\u0aab\u0ab5\3\2\2\2\u0aac\u0aad\f\4\2\2\u0aad\u0aae\5"+
		"\u01c2\u00e2\2\u0aae\u0aaf\5\u01de\u00f0\2\u0aaf\u0ab5\3\2\2\2\u0ab0\u0ab1"+
		"\f\3\2\2\u0ab1\u0ab2\5\u01c2\u00e2\2\u0ab2\u0ab3\5\u01de\u00f0\2\u0ab3"+
		"\u0ab5\3\2\2\2\u0ab4\u0aa8\3\2\2\2\u0ab4\u0aac\3\2\2\2\u0ab4\u0ab0\3\2"+
		"\2\2\u0ab5\u0ab8\3\2\2\2\u0ab6\u0ab4\3\2\2\2\u0ab6\u0ab7\3\2\2\2\u0ab7"+
		"\u01dd\3\2\2\2\u0ab8\u0ab6\3\2\2\2\u0ab9\u0ac3\5\u01e0\u00f1\2\u0aba\u0ac3"+
		"\5\u01e2\u00f2\2\u0abb\u0abc\5\u01be\u00e0\2\u0abc\u0abd\5\u01de\u00f0"+
		"\2\u0abd\u0ac3\3\2\2\2\u0abe\u0abf\5\u01be\u00e0\2\u0abf\u0ac0\5\u01de"+
		"\u00f0\2\u0ac0\u0ac3\3\2\2\2\u0ac1\u0ac3\5\u01e4\u00f3\2\u0ac2\u0ab9\3"+
		"\2\2\2\u0ac2\u0aba\3\2\2\2\u0ac2\u0abb\3\2\2\2\u0ac2\u0abe\3\2\2\2\u0ac2"+
		"\u0ac1\3\2\2\2\u0ac3\u01df\3\2\2\2\u0ac4\u0ac5\7R\2\2\u0ac5\u0ac6\5\u01de"+
		"\u00f0\2\u0ac6\u01e1\3\2\2\2\u0ac7\u0ac8\7S\2\2\u0ac8\u0ac9\5\u01de\u00f0"+
		"\2\u0ac9\u01e3\3\2\2\2\u0aca\u0ad1\5\u01e6\u00f4\2\u0acb\u0acc\7I\2\2"+
		"\u0acc\u0ad1\5\u01de\u00f0\2\u0acd\u0ace\7H\2\2\u0ace\u0ad1\5\u01de\u00f0"+
		"\2\u0acf\u0ad1\5\u01f0\u00f9\2\u0ad0\u0aca\3\2\2\2\u0ad0\u0acb\3\2\2\2"+
		"\u0ad0\u0acd\3\2\2\2\u0ad0\u0acf\3\2\2\2\u0ad1\u01e5\3\2\2\2\u0ad2\u0ad5"+
		"\5\u016e\u00b8\2\u0ad3\u0ad5\5<\37\2\u0ad4\u0ad2\3\2\2\2\u0ad4\u0ad3\3"+
		"\2\2\2\u0ad5\u0ada\3\2\2\2\u0ad6\u0ad9\5\u01ea\u00f6\2\u0ad7\u0ad9\5\u01ee"+
		"\u00f8\2\u0ad8\u0ad6\3\2\2\2\u0ad8\u0ad7\3\2\2\2\u0ad9\u0adc\3\2\2\2\u0ada"+
		"\u0ad8\3\2\2\2\u0ada\u0adb\3\2\2\2\u0adb\u01e7\3\2\2\2\u0adc\u0ada\3\2"+
		"\2\2\u0add\u0ade\5\u01e6\u00f4\2\u0ade\u0adf\7R\2\2\u0adf\u01e9\3\2\2"+
		"\2\u0ae0\u0ae1\7R\2\2\u0ae1\u01eb\3\2\2\2\u0ae2\u0ae3\5\u01e6\u00f4\2"+
		"\u0ae3\u0ae4\7S\2\2\u0ae4\u01ed\3\2\2\2\u0ae5\u0ae6\7S\2\2\u0ae6\u01ef"+
		"\3\2\2\2\u0ae7\u0ae8\7<\2\2\u0ae8\u0ae9\5\6\4\2\u0ae9\u0aea\7=\2\2\u0aea"+
		"\u0aeb\5\u01de\u00f0\2\u0aeb\u0b03\3\2\2\2\u0aec\u0aed\7<\2\2\u0aed\u0af1"+
		"\5\16\b\2\u0aee\u0af0\5*\26\2\u0aef\u0aee\3\2\2\2\u0af0\u0af3\3\2\2\2"+
		"\u0af1\u0aef\3\2\2\2\u0af1\u0af2\3\2\2\2\u0af2\u0af4\3\2\2\2\u0af3\u0af1"+
		"\3\2\2\2\u0af4\u0af5\7=\2\2\u0af5\u0af6\5\u01e4\u00f3\2\u0af6\u0b03\3"+
		"\2\2\2\u0af7\u0af8\7<\2\2\u0af8\u0afc\5\16\b\2\u0af9\u0afb\5*\26\2\u0afa"+
		"\u0af9\3\2\2\2\u0afb\u0afe\3\2\2\2\u0afc\u0afa\3\2\2\2\u0afc\u0afd\3\2"+
		"\2\2\u0afd\u0aff\3\2\2\2\u0afe\u0afc\3\2\2\2\u0aff\u0b00\7=\2\2\u0b00"+
		"\u0b01\5\u01ae\u00d8\2\u0b01\u0b03\3\2\2\2\u0b02\u0ae7\3\2\2\2\u0b02\u0aec"+
		"\3\2\2\2\u0b02\u0af7\3\2\2\2\u0b03\u01f1\3\2\2\2\u0137\u01f6\u01fb\u0202"+
		"\u0206\u020a\u0213\u0217\u021b\u021d\u0222\u0228\u022a\u022f\u0233\u0246"+
		"\u024c\u0252\u0257\u0262\u0265\u0273\u0278\u027d\u0282\u0288\u0292\u029a"+
		"\u02a4\u02ac\u02b8\u02bc\u02c1\u02c7\u02cf\u02d8\u02e3\u0300\u0304\u030a"+
		"\u030d\u0310\u0317\u0322\u032d\u033b\u0342\u034b\u0352\u035c\u0367\u036e"+
		"\u0374\u0378\u037c\u0380\u0384\u0389\u038d\u0391\u0393\u0398\u039e\u03a0"+
		"\u03a5\u03a9\u03bc\u03c5\u03d2\u03d7\u03dd\u03e3\u03e5\u03e9\u03ee\u03f2"+
		"\u03f9\u0400\u0408\u040b\u0410\u0418\u041d\u0424\u042b\u0430\u0436\u0442"+
		"\u0447\u044b\u0455\u045c\u0463\u0466\u046b\u0473\u0476\u047b\u0480\u0485"+
		"\u048a\u0491\u0496\u049e\u04a3\u04a8\u04ae\u04b4\u04b7\u04ba\u04c3\u04c9"+
		"\u04cf\u04d2\u04d5\u04dd\u04e2\u04e7\u04ed\u04f0\u04fb\u0504\u050e\u0513"+
		"\u051e\u0523\u052f\u0534\u0540\u054a\u054f\u0557\u055a\u0561\u0569\u056e"+
		"\u0576\u057f\u0588\u0592\u0596\u0599\u05a2\u05b0\u05b3\u05bc\u05c1\u05c9"+
		"\u05cf\u05d7\u05e3\u05ea\u05f8\u060e\u0630\u063c\u0642\u064e\u065b\u0675"+
		"\u0679\u067e\u0682\u0686\u068e\u0692\u0696\u069d\u06a6\u06ae\u06bd\u06c9"+
		"\u06cf\u06d5\u06ea\u06ef\u06f5\u0701\u070c\u0716\u0719\u071e\u0727\u072d"+
		"\u0737\u073c\u0744\u075b\u0764\u077a\u0781\u0789\u0790\u079a\u07b1\u07ba"+
		"\u07c4\u07da\u07de\u07e5\u07e9\u07ed\u07f1\u07f7\u07fb\u07ff\u0803\u0809"+
		"\u080d\u0811\u0815\u0817\u081c\u0820\u0824\u0828\u082c\u0833\u0837\u083b"+
		"\u083f\u0845\u0849\u084d\u0851\u0853\u0858\u0867\u0875\u0881\u088a\u0899"+
		"\u08a6\u08af\u08b5\u08bc\u08c1\u08c8\u08cd\u08d4\u08d9\u08e0\u08e5\u08ed"+
		"\u08f2\u08f6\u08fa\u08ff\u0906\u090d\u0912\u0919\u091e\u0925\u092a\u0932"+
		"\u0937\u093b\u0942\u0948\u094f\u0956\u095d\u0965\u096c\u0974\u0978\u097f"+
		"\u0986\u098d\u0995\u099c\u09a4\u09aa\u09b0\u09bc\u09c2\u09c8\u09d3\u09dc"+
		"\u09e3\u09ea\u09ef\u09f3\u09fc\u0a15\u0a1f\u0a2a\u0a35\u0a40\u0a4b\u0a57"+
		"\u0a59\u0a73\u0a75\u0a87\u0a89\u0a93\u0aa0\u0aa2\u0ab4\u0ab6\u0ac2\u0ad0"+
		"\u0ad4\u0ad8\u0ada\u0af1\u0afc\u0b02";
	public static final String _serializedATN = Utils.join(
		new String[] {
			_serializedATNSegment0,
			_serializedATNSegment1
		},
		""
	);
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}