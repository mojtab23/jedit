package sidekick.java.parser.antlr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.*;

import sidekick.java.node.*;
import sidekick.util.Location;
import static sidekick.java.parser.antlr.JavaParser.*;

/**
 * 
 * Processor for java sidekick.
 * 
 */
public class JavaSideKickListener extends JavaParserBaseListener {
    
    private CUNode cuNode;
    private Results results = new Results();


    public CUNode getCompilationUnit() {
        if (cuNode == null) {
            cuNode = new CUNode();   
        }
        cuNode.setResults(getResults());
        return cuNode;
    }


    /**
     * @return the accumulated counts of classes, interfaces, methods, and fields.
     */
    public Results getResults() {
        return results;
    }

//******************************************************************************
// Utility methods
//******************************************************************************
    // return a Location representing the start of the rule context
    private Location getStartLocation( ParserRuleContext ctx ) {
        int line = ctx.getStart().getLine();
        int col = ctx.getStart().getCharPositionInLine();
        return new Location( line, col );
    }


    // return a Location representing the end of the rule context
    private Location getEndLocation( ParserRuleContext ctx ) {
        int line = ctx.getStop().getLine();
        int col = ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length();
        return new Location( line, col );
    }


    private void setStartLocation( TigerNode tn, ParserRuleContext ctx ) {
        tn.setStartLocation( getStartLocation( ctx ) );
    }


    private void setEndLocation( TigerNode tn, ParserRuleContext ctx ) {
        tn.setEndLocation( getEndLocation( ctx ) );
    }


    private void setStartLocation( TigerNode tn, Token token ) {
        tn.setStartLocation( new Location( token.getLine(), token.getCharPositionInLine() ) );
    }


    private void setEndLocation( TigerNode tn, Token token ) {
        tn.setEndLocation( new Location( token.getLine(), token.getCharPositionInLine() + token.getText().length() ) );
    }


    private void setLocations( TigerNode tn, ParserRuleContext ctx ) {
        setStartLocation( tn, ctx );
        setEndLocation( tn, ctx );
    }
    
    
    /**
     * qualifiedName
     *     : identifier ('.' identifier)*
     *     ;
     */
    private String getQualifiedName(QualifiedNameContext qnc) {
        StringBuilder sb = new StringBuilder();
        List<IdentifierContext> idList = qnc.identifier();
        for (IdentifierContext id : idList) {
            sb.append(id.getText());
            sb.append('.');
        }
        return sb.substring(0, sb.length() - 1);
    }
    

    
//******************************************************************************
// Parser method
//******************************************************************************
    /**
     * compilationUnit
     *     : packageDeclaration? importDeclaration* typeDeclaration*
     *     | moduleDeclaration EOF
     *     ;
     */
    @Override
    public void exitCompilationUnit( CompilationUnitContext ctx ) {
        try {
            cuNode = new CUNode();
            setLocations( cuNode, ctx );

            if (ctx.moduleDeclaration() != null) {
                TigerNode child = processModuleDeclaration(ctx.moduleDeclaration());
                if (child != null) {
                    cuNode.addChild(child);   
                }
            }
            else {
                if ( ctx.typeDeclaration() != null && ctx.typeDeclaration().size() > 0) {
                    for (TypeDeclarationContext tdc : ctx.typeDeclaration()) {
                        TigerNode child = processTypeDeclaration(tdc);
                        if (child != null) {
                            cuNode.addChild(child);
                        }
                    }
                }
    
                if ( ctx.importDeclaration() != null && ctx.importDeclaration().size() > 0) {
                    ImportNode importNode = new ImportNode( "Imports" );
                    for (ImportDeclarationContext idc : ctx.importDeclaration()) {
                        TigerNode child = processImportDeclaration(idc);
                        if (child != null) {
                            importNode.addChild(child);
                        }
                    }
                    cuNode.setImportNode( importNode );
                }
    
                if ( ctx.packageDeclaration() != null ) {
                    TigerNode child = processPackageDeclaration(ctx.packageDeclaration());
                    if (child != null) {
                        cuNode.setPackage( child );
                    }
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
//******************************************************************************
// Methods to process the individual parts
//******************************************************************************

    /**
     * moduleDeclaration
     */
    private TigerNode processModuleDeclaration(ModuleDeclarationContext ctx) {
        String name = "";
        TigerNode node = new ModuleNode();
        if (ctx.qualifiedName() != null) {
            name = getQualifiedName(ctx.qualifiedName());
        }
        node.setName(name);
        if (ctx.moduleBody() != null) {
            ModuleBodyContext mbc = ctx.moduleBody();
            List<ModuleDirectiveContext> directives = mbc.moduleDirective();
            for (ModuleDirectiveContext directive : directives) {
                TigerNode child = new DirectiveNode();
                if (directive.REQUIRES() != null) {
                    child.setName("requires " + getQualifiedName(directive.qualifiedName(0)));
                }
                else if (directive.EXPORTS() != null) {
                    child.setName("exports " + getQualifiedName(directive.qualifiedName(0)));
                }
                else if (directive.OPENS() != null) {
                    child.setName("opens " + getQualifiedName(directive.qualifiedName(0)));
                }
                else if (directive.USES() != null) {
                    child.setName("uses " + getQualifiedName(directive.qualifiedName(0)));
                }
                else if (directive.PROVIDES() != null) {
                    child.setName("provides " + getQualifiedName(directive.qualifiedName(0)));
                }
                child.setModifiers(ModifierSet.getModifiers("public"));
                node.addChild(child);
            }
        }
        return node;
    }
    
    /**
     * packageDeclaration
     *     : annotation* PACKAGE qualifiedName ';'
     *     ;
     */
    public TigerNode processPackageDeclaration( PackageDeclarationContext ctx ) {
        TigerNode node = new TigerNode();
        setLocations( node, ctx );

        if (ctx.qualifiedName() != null) {
            node.setName(getQualifiedName(ctx.qualifiedName()));
        }
        
        return node;
    }

    
    /**
     * importDeclaration
     *     : IMPORT STATIC? qualifiedName ('.' '*')? ';'
     *     ;
     */
    public TigerNode processImportDeclaration( ImportDeclarationContext ctx ) {
        TigerNode node = new ImportNode();
        setLocations( node, ctx );
        if (ctx.qualifiedName() != null) {
            node.setName(getQualifiedName(ctx.qualifiedName()));   
        }
        return node;
    }

	
    /**
     * typeDeclaration
     *     : classOrInterfaceModifier*
     *       (classDeclaration | enumDeclaration | interfaceDeclaration | annotationTypeDeclaration | recordDeclaration)
     *     | ';'
     *     ;
     *     
     */
	public TigerNode processTypeDeclaration(TypeDeclarationContext ctx) {
	    // modifiers
	    ModifierSet modifierSet = new ModifierSet();
	    if (ctx.classOrInterfaceModifier() != null) {
	        List<ClassOrInterfaceModifierContext> modifierList = ctx.classOrInterfaceModifier();
            for (ClassOrInterfaceModifierContext coim : modifierList) {
                if (coim.PUBLIC() != null) {
                     modifierSet.addModifier("public");   
                }
                if (coim.PROTECTED() != null) {
                     modifierSet.addModifier("protected");   
                }
                if (coim.PRIVATE() != null) {
                     modifierSet.addModifier("private");   
                }
                if (coim.STATIC() != null) {
                     modifierSet.addModifier("static");   
                }
                if (coim.ABSTRACT() != null) {
                     modifierSet.addModifier("abstract");   
                }
                if (coim.FINAL() != null) {
                     modifierSet.addModifier("final");   
                }
                if (coim.STRICTFP() != null) {
                     modifierSet.addModifier("strictfp");   
                }
                if (coim.SEALED() != null) {
                     modifierSet.addModifier("sealed");   
                }
                if (coim.NON_SEALED() != null) {
                     modifierSet.addModifier("non_sealed");   
                }
	        }
	    }
	    int modifiers = modifierSet.getModifiers();
	    
	    // class declaration
	    if (ctx.classDeclaration() != null) {
	        return processClassDeclaration(ctx.classDeclaration(), modifiers);   
	    }

	    // enumDeclaration
	    if (ctx.enumDeclaration() != null) {
	        return processEnumDeclaration(ctx.enumDeclaration(), modifiers);   
	    }
	    
	    // interfaceDeclaration
	    if (ctx.interfaceDeclaration() != null) {
	        return processInterfaceDeclaration(ctx.interfaceDeclaration(), modifiers);   
	    }
	    
	    // annotationTypeDeclaration
	    if (ctx.annotationTypeDeclaration() != null) {
	        return processAnnotationTypeDeclaration(ctx.annotationTypeDeclaration(), modifiers);   
	    }
	    
	    // recordDeclaration
	    if (ctx.recordDeclaration() != null) {
	        return processRecordDeclaration(ctx.recordDeclaration(), modifiers);   
	    }
	    return null;
	}
	
   /**
 	* classDeclaration
 	*     : CLASS identifier typeParameters?
 	*       (EXTENDS typeType)?
 	*       (IMPLEMENTS typeList)?
 	*       (PERMITS typeList)? // Java17
 	*       classBody
 	*     ;
 	*/
	private TigerNode processClassDeclaration(ClassDeclarationContext ctx, int modifiers) { 
        ClassNode classNode = new ClassNode( ctx.identifier().getText() );
        setLocations( classNode, ctx );
        classNode.setModifiers(modifiers);
        
	    // type parameters
	    if (ctx.typeParameters() != null) {
	        TypeParametersContext tpc = ctx.typeParameters();
	        if (tpc != null) {
                List<TypeParameterContext> typeParameterList = tpc.typeParameter();
                StringBuilder sb = new StringBuilder();
                for (TypeParameterContext typeParameter : typeParameterList) {
                    sb.append(typeParameter.getText());
                }
                classNode.setTypeParams(sb.toString());
	        }
	    }
	    
        // PERMITS = allowed subclasses
        // Some confusion here -- both implements and permits
        // are followed by a typeList, but the context only has one typeList method,
        // so which parts of the typeList belong to implements and which belong to
        // permits?
        // After some investigation:
        // if there is an implements, then the first item in the typeList are the
        // implements types.
        // if there is no implements and there is a permits, then the first item in
        // the typeList are the permits types.
        // if there is both an implements and a permits, then the first item in the
        // typeList is the implements types and the second item in the typeList is
        // the permits types.
        
        TypeListContext implementsTypeList = null;
        TypeListContext permitsTypeList = null;
        
        if (ctx.IMPLEMENTS() != null && ctx.PERMITS() != null) {
            implementsTypeList = ctx.typeList(0);
            permitsTypeList = ctx.typeList(1);
        }
        else if (ctx.IMPLEMENTS() != null) {
            implementsTypeList = ctx.typeList(0);   
        }            
        else if (ctx.PERMITS() != null) {
            permitsTypeList = ctx.typeList(0);   
        }

        // PERMITS = allowed subclasses
        if (ctx.PERMITS() != null) {
            List<TypeTypeContext> ttc = permitsTypeList.typeType();
            List<TigerNode> permitsTypes = new ArrayList<TigerNode>();
            for (TypeTypeContext t : ttc) {
                ClassOrInterfaceTypeContext coitc = t.classOrInterfaceType();
                if (coitc != null) {
                    TigerNode type = new TigerNode(coitc.getText());
                    setLocations(type, coitc);
                    permitsTypes.add(type);
                }
            }
            classNode.setPermitsList( permitsTypes );
        }
        
        // IMPLEMENTS = superinterfaces
        if ( ctx.IMPLEMENTS() != null ) {
            List<TypeTypeContext> ttc = implementsTypeList.typeType();
            List<TigerNode> implementsTypes = new ArrayList<TigerNode>();
            for (TypeTypeContext t : ttc) {
                ClassOrInterfaceTypeContext coitc = t.classOrInterfaceType();
                if (coitc != null) {
                    TigerNode type = new TigerNode(coitc.getText());
                    setLocations(type, coitc);
                    implementsTypes.add(type);
                }
            }
            classNode.setImplementsList( implementsTypes );
        }
        
        // EXTENDS = superclasses
        if ( ctx.EXTENDS() != null ) {
            TypeTypeContext ttc = ctx.typeType();
            if (ttc != null) {
                ClassOrInterfaceTypeContext coitc = ttc.classOrInterfaceType();
                if (coitc != null) {
                    TigerNode type = new TigerNode(coitc.getText());
                    setLocations(type, coitc);
                    classNode.setExtends(type);
                }
            }
        }

        ClassBodyContext classBodyContext = ctx.classBody();
        List<ClassBodyDeclarationContext> declarations = ( List <ClassBodyDeclarationContext> )classBodyContext.classBodyDeclaration();
        processClassBodyDeclaration( classNode, declarations);
        
        results.incClassCount();
        return classNode;
	
	}
	
	/**
 	 * This is used by class, record, and enum declarations.	
 	 */
	private void processClassBodyDeclaration( TigerNode node, List<ClassBodyDeclarationContext> declarations) {
        for ( ClassBodyDeclarationContext declaration : declarations ) {
            // do member modifiers here
            List<ModifierContext> mcl = declaration.modifier();
            ModifierSet modifierSet = new ModifierSet();
            for (ModifierContext mc : mcl) {
                modifierSet.addModifier(mc.getText());   
            }
            int memberModifiers = modifierSet.getModifiers();
            
            if ( declaration.memberDeclaration() != null ) {
                MemberDeclarationContext dctx = declaration.memberDeclaration();
                
                // methods
                if ( dctx.methodDeclaration() != null ) {
                    node.addChild(processMethodDeclaration( dctx.methodDeclaration(), memberModifiers ));
                }
                
                // generic methods
                if ( dctx.genericMethodDeclaration() != null) {
                    node.addChild(processGenericMethodDeclaration(dctx.genericMethodDeclaration(), memberModifiers));    
                }
                
                // fields
                if ( dctx.fieldDeclaration() != null ) {
                    List<TigerNode> fields = processFieldDeclaration( dctx.fieldDeclaration(), memberModifiers ); 
                    for (TigerNode field : fields) {
                        node.addChild(field);   
                    }
                }
                
                // constructors
                if ( dctx.constructorDeclaration() != null) {
                    node.addChild(processConstructorDeclaration( dctx.constructorDeclaration(), memberModifiers));   
                }
                
                // generic constructors
                if ( dctx.genericConstructorDeclaration() != null) {
                    node.addChild(processGenericConstructorDeclaration( dctx.genericConstructorDeclaration(), memberModifiers));   
                }
                
                // interfaces
                if ( dctx.interfaceDeclaration() != null) {
                    node.addChild(processInterfaceDeclaration( dctx.interfaceDeclaration(), memberModifiers));   
                }
                
                // annotation types
                if ( dctx.annotationTypeDeclaration() != null) {
                    node.addChild(processAnnotationTypeDeclaration( dctx.annotationTypeDeclaration(), memberModifiers));   
                }
                
                // classes
                if ( dctx.classDeclaration() != null) {
                    node.addChild(processClassDeclaration( dctx.classDeclaration(), memberModifiers));   
                }
                
                // enums
                if ( dctx.enumDeclaration() != null) {
                    node.addChild(processEnumDeclaration( dctx.enumDeclaration(), memberModifiers));   
                }
                
                // records
                if ( dctx.recordDeclaration() != null) {
                    node.addChild(processRecordDeclaration( dctx.recordDeclaration(), memberModifiers));   
                }
            }
        }
	}
    /**
     * methodDeclaration
     *     : typeTypeOrVoid identifier formalParameters ('[' ']')* (THROWS qualifiedNameList)? methodBody
     *     ;
     */
    private MethodNode processMethodDeclaration( MethodDeclarationContext methodCtx, int modifiers ) {
        MethodNode methodNode = new MethodNode();

        setLocations( methodNode, methodCtx );

        // modifiers
        methodNode.setModifiers(modifiers);
        
        // return type, just want the type, don't worry about annotations
        TypeTypeOrVoidContext ttvc = methodCtx.typeTypeOrVoid();
        if (ttvc != null) {     // should never be null, is this check necessary?
            Type returnType = null;
            if (ttvc.VOID() != null) {
                returnType = new Type("void");       
            }
            else if (ttvc.typeType() != null) {
                TypeTypeContext tt = ttvc.typeType();
                if (tt.classOrInterfaceType() != null) {
                    List<IdentifierContext> idList = tt.classOrInterfaceType().identifier();
                    StringBuilder sb = new StringBuilder();
                    for (IdentifierContext id : idList) {
                        sb.append(id.getText()).append(' ');
                    }
                    
                    returnType = new Type(sb.substring(0, sb.length() - 1));   
                }
                else if (tt.primitiveType() != null) {
                    returnType = new Type(tt.primitiveType().getText());   
                }
            }
            if (returnType != null) {
                setLocations(returnType, ttvc);
                methodNode.setReturnType(returnType);
            }
        }
        
        // method name
        String name = methodCtx.identifier().getText();
        methodNode.setName(name);
        
        // parameters
        FormalParametersContext paramsCtx = methodCtx.formalParameters();
        
        // receiver parameters -- type identifier.this
        if (paramsCtx.receiverParameter() != null) {
            ReceiverParameterContext rpc = paramsCtx.receiverParameter();
            
            Parameter parameterNode = new Parameter();
            setLocations( parameterNode, rpc );
            Type type = new Type( rpc.typeType().getText() );
            parameterNode.setType( type );
            StringBuilder paramName = new StringBuilder();
            List<IdentifierContext> idList = rpc.identifier();
            for (IdentifierContext id : idList) {
                paramName.append(id.getText()).append('.');
            }
            paramName.append("this");
            parameterNode.setName( paramName.toString() );
            methodNode.addParameter( parameterNode );
        }
        // other parameters
        FormalParameterListContext paramsListCtx = paramsCtx.formalParameterList();
        if (paramsListCtx != null) {
            List<FormalParameterContext> paramsList = paramsListCtx.formalParameter();
            if (paramsList != null) {
                for (FormalParameterContext param : paramsList) {
                    Parameter parameterNode = new Parameter();
                    setLocations( parameterNode, param );
                    Type type = new Type( param.typeType().getText() );
                    parameterNode.setType( type );
                    parameterNode.setName( param.variableDeclaratorId().identifier().getText() );
    
                    // modifiers
                    int size = param.variableModifier().size();
                    String[] paramModifierNames = new String [size];
                    for ( int i = 0; i < size; i++ ) {
                        paramModifierNames[i] = param.variableModifier( i ).getText();
                    }
                    parameterNode.setModifiers( ModifierSet.getModifiers( paramModifierNames ));
                    methodNode.addParameter( parameterNode );
                }
            }
            // last formal parameter -- modifier* type annotation* ... variableId
            if (paramsListCtx.lastFormalParameter() != null) {
                LastFormalParameterContext lfpc = paramsListCtx.lastFormalParameter();
                if ( lfpc != null ) {
                    Parameter parameterNode = new Parameter();
                    setLocations( parameterNode, lfpc );
                    parameterNode.setType(new Type(lfpc.typeType().getText()));
                    parameterNode.setName(lfpc.variableDeclaratorId().getText());
                    
                    // modifiers
                    int size = lfpc.variableModifier().size();
                    String[] modifierNames = new String [size];
                    for ( int i = 0; i < size; i++ ) {
                        modifierNames[i] = lfpc.variableModifier( i ).getText();
                    }
                    parameterNode.setModifiers( ModifierSet.getModifiers( modifierNames ) );
                    
                    // annotations
                    if (lfpc.annotation() != null) {
                        List<AnnotationContext> annotationList = lfpc.annotation();
                        for (AnnotationContext annotation : annotationList) {
                            parameterNode.addAnnotation(processAnnotation(annotation));    
                        }
                    }
                    
                    methodNode.addParameter( parameterNode );
                }
            }
        }
        
        // throws
        if (methodCtx.THROWS() != null) {
            QualifiedNameListContext qnlc = methodCtx.qualifiedNameList();
            if (qnlc != null) {
                List<QualifiedNameContext> nameList = qnlc.qualifiedName();
                List<TigerNode> exceptionList = new ArrayList<TigerNode>();
                for (QualifiedNameContext qname : nameList) {
                    TigerNode tn = new TigerNode( getQualifiedName(qname) );
                    setLocations( tn, qname );
                    exceptionList.add( tn );
                }
                methodNode.setThrows( exceptionList );
            }
        }
        
        results.incMethodCount();
        return methodNode;
    }
    
    /**
     * genericMethodDeclaration
     *     : typeParameters methodDeclaration
     *     ;
     *
     * Treat this as a regular method
     */
    private MethodNode processGenericMethodDeclaration(GenericMethodDeclarationContext ctx, int modifiers) {
        MethodNode methodNode =  processMethodDeclaration(ctx.methodDeclaration(), modifiers);       

	    // type parameters
	    if (ctx.typeParameters() != null) {
	        TypeParametersContext tpc = ctx.typeParameters();
	        if (tpc != null) {
                List<TypeParameterContext> typeParameterList = tpc.typeParameter();
                StringBuilder sb = new StringBuilder();
                for (TypeParameterContext typeParameter : typeParameterList) {
                    sb.append(typeParameter.getText());
                }
                methodNode.setTypeParams(sb.toString());
	        }
	    }
	    return methodNode;
    }
    
    
    /**
 	 * fieldDeclaration
 	 *     : typeType variableDeclarators ';'
 	 *     ;
 	 */
    private List<TigerNode> processFieldDeclaration( FieldDeclarationContext fieldCtx, int modifiers ) {
        // type
        Type type = null;
        if (fieldCtx.typeType() != null) {
            TypeTypeContext tt = fieldCtx.typeType();
            if (tt.classOrInterfaceType() != null) {
                List<IdentifierContext> idList = tt.classOrInterfaceType().identifier();
                StringBuilder sb = new StringBuilder();
                for (IdentifierContext id : idList) {
                    sb.append(id.getText()).append(' ');
                }
                
                type = new Type(sb.substring(0, sb.length() - 1));   
            }
            else if (tt.primitiveType() != null) {
                type = new Type(tt.primitiveType().getText());   
            }
        }
        // variable declarations, make a field node per variable
        List<TigerNode> fields = new ArrayList<TigerNode>();
        int size = fieldCtx.variableDeclarators().variableDeclarator().size();
        for ( int i = 0; i < size; i++ ) {
            VariableDeclaratorContext vdc = fieldCtx.variableDeclarators().variableDeclarator( i );
            String name = vdc.getText();
            VariableDeclarator vd = new VariableDeclarator(name);
            FieldNode fn = new FieldNode( name, modifiers, type );
            fn.addChild(vd);
            setLocations( fn, vdc );
            if (fn.isPrimitive()) {
                results.incPrimitiveFieldCount();
            }
            else {
                results.incReferenceFieldCount();
            }
            results.incReferenceFieldCount();
            fields.add(fn);
        }
        return fields;
    }
    
    /**
     * constructorDeclaration
     *     : identifier formalParameters (THROWS qualifiedNameList)? constructorBody=block
     *     ;
     */
    private ConstructorNode processConstructorDeclaration(ConstructorDeclarationContext ctx, int modifiers) {
        ConstructorNode constructorNode = new ConstructorNode();

        setLocations( constructorNode, ctx );

        // modifiers
        constructorNode.setModifiers(modifiers);
        
        // constructor name
        String name = ctx.identifier().getText();
        constructorNode.setName(name);
        
        // parameters
        FormalParametersContext paramsCtx = ctx.formalParameters();
        
        // receiver parameters -- type identifier.this
        if (paramsCtx.receiverParameter() != null) {
            ReceiverParameterContext rpc = paramsCtx.receiverParameter();
            
            Parameter parameterNode = new Parameter();
            setLocations( parameterNode, rpc );
            Type type = new Type( rpc.typeType().getText() );
            parameterNode.setType( type );
            StringBuilder paramName = new StringBuilder();
            List<IdentifierContext> idList = rpc.identifier();
            for (IdentifierContext id : idList) {
                paramName.append(id.getText()).append('.');
            }
            paramName.append("this");
            parameterNode.setName( paramName.toString() );
            constructorNode.addParameter( parameterNode );
        }
        
        // other parameters
        FormalParameterListContext paramsListCtx = paramsCtx.formalParameterList();
        if (paramsListCtx != null) {
            List<FormalParameterContext> paramsList = paramsListCtx.formalParameter();
            if (paramsList != null) {
                for (FormalParameterContext param : paramsList) {
                    Parameter parameterNode = new Parameter();
                    setLocations( parameterNode, param );
                    Type type = new Type( param.typeType().getText() );
                    parameterNode.setType( type );
                    parameterNode.setName( param.variableDeclaratorId().identifier().getText() );
    
                    // modifiers
                    int size = param.variableModifier().size();
                    String[] paramModifierNames = new String [size];
                    for ( int i = 0; i < size; i++ ) {
                        paramModifierNames[i] = param.variableModifier( i ).getText();
                    }
                    parameterNode.setModifiers( ModifierSet.getModifiers( paramModifierNames ));
                    constructorNode.addParameter( parameterNode );
                }
            }
            // last formal parameter -- modifier* type annotation* ... variableId
            // I'm not handling the annotations, they aren't necessary to show in the sidekick tree.
            if (paramsListCtx.lastFormalParameter() != null) {
                LastFormalParameterContext lfpc = paramsListCtx.lastFormalParameter();
                if ( lfpc != null ) {
                    Parameter parameterNode = new Parameter();
                    setLocations( parameterNode, lfpc );
                    parameterNode.setType(new Type(lfpc.typeType().getText()));
                    parameterNode.setName(lfpc.variableDeclaratorId().getText());
                    
                    // modifiers
                    int size = lfpc.variableModifier().size();
                    String[] modifierNames = new String [size];
                    for ( int i = 0; i < size; i++ ) {
                        modifierNames[i] = lfpc.variableModifier( i ).getText();
                    }
                    parameterNode.setModifiers( ModifierSet.getModifiers( modifierNames ) );
                    
                    constructorNode.addParameter( parameterNode );
                }
            }
        }
        
        // throws
        if (ctx.THROWS() != null) {
            QualifiedNameListContext qnlc = ctx.qualifiedNameList();
            if (qnlc != null) {
                List<QualifiedNameContext> nameList = qnlc.qualifiedName();
                List<TigerNode> exceptionList = new ArrayList<TigerNode>();
                for (QualifiedNameContext qname : nameList) {
                    TigerNode tn = new TigerNode( getQualifiedName(qname) );
                    setLocations( tn, qname );
                    exceptionList.add( tn );
                }
                constructorNode.setThrows( exceptionList );
            }
        }
        
        results.incMethodCount();
        return constructorNode;
    }
    
    /**
     * genericConstructorDeclaration
     *     : typeParameters constructorDeclaration
     *     ;
     *
     */
    private TigerNode processGenericConstructorDeclaration(GenericConstructorDeclarationContext ctx, int modifiers) {
        ConstructorNode node = processConstructorDeclaration(ctx.constructorDeclaration(), modifiers);

	    // type parameters
	    if (ctx.typeParameters() != null) {
	        TypeParametersContext tpc = ctx.typeParameters();
	        if (tpc != null) {
                List<TypeParameterContext> typeParameterList = tpc.typeParameter();
                StringBuilder sb = new StringBuilder();
                for (TypeParameterContext typeParameter : typeParameterList) {
                    sb.append(typeParameter.getText());
                }
                node.setTypeParams(sb.toString());
	        }
	    }
        return node;
    }
    
	/**
 	 * interfaceDeclaration
 	 *     : INTERFACE identifier typeParameters? (EXTENDS typeList)? (PERMITS typeList)? interfaceBody
 	 *     ;
 	 * Other than INTERFACE, this is exactly the same as a class. I tried to avoid duplicate code, but
 	 * it got very messy trying to adjust the same code to work for classes and interfaces.
 	 */
	private TigerNode processInterfaceDeclaration(InterfaceDeclarationContext ctx, int modifiers) {
        InterfaceNode interfaceNode = new InterfaceNode( ctx.identifier().getText() );
        setLocations( interfaceNode, ctx );
        interfaceNode.setModifiers(modifiers);
        
        // PERMITS = allowed subclasses
        // Some confusion here -- both extends and permits
        // are followed by a typeList, but the context only has one typeList method,
        // so which parts of the typeList belong to implements and which belong to
        // permits?
        // After some investigation:
        // if there is an implements, then the first item in the typeList are the
        // extends types.
        // if there is no extends and there is a permits, then the first item in
        // the typeList are the permits types.
        // if there is both an extends and a permits, then the first item in the
        // typeList is the extends types and the second item in the typeList is
        // the permits types.
        
        TypeListContext extendsTypeList = null;
        TypeListContext permitsTypeList = null;
        
        if (ctx.EXTENDS() != null && ctx.PERMITS() != null) {
            extendsTypeList = ctx.typeList(0);
            permitsTypeList = ctx.typeList(1);
        }
        else if (ctx.EXTENDS() != null) {
            extendsTypeList = ctx.typeList(0);   
        }            
        else if (ctx.PERMITS() != null) {
            permitsTypeList = ctx.typeList(0);   
        }

        // PERMITS = allowed subclasses
        if (ctx.PERMITS() != null) {
            List<TypeTypeContext> ttc = permitsTypeList.typeType();
            List<TigerNode> permitsTypes = new ArrayList<TigerNode>();
            for (TypeTypeContext t : ttc) {
                ClassOrInterfaceTypeContext coitc = t.classOrInterfaceType();
                if (coitc != null) {
                    TigerNode type = new TigerNode(coitc.getText());
                    setLocations(type, coitc);
                    permitsTypes.add(type);
                }
            }
            interfaceNode.setPermitsList( permitsTypes );
        }
        
        // EXTENDS = superinterfaces
        if ( ctx.EXTENDS() != null ) {
            List<TypeTypeContext> ttc = extendsTypeList.typeType();
            List<Type> extendsTypes = new ArrayList<Type>();
            for (TypeTypeContext t : ttc) {
                ClassOrInterfaceTypeContext coitc = t.classOrInterfaceType();
                if (coitc != null) {
                    Type type = new Type(coitc.getText());
                    setLocations(type, coitc);
                    extendsTypes.add(type);
                }
            }
            interfaceNode.setExtendsList( extendsTypes );
        }
        
        // add the children of this class. Only need the fields and methods here,
        // the inner classes and interface declarations are handled elsewhere
        InterfaceBodyContext interfaceBodyContext = ctx.interfaceBody();
        List<InterfaceBodyDeclarationContext> declarations = ( List <InterfaceBodyDeclarationContext> )interfaceBodyContext.interfaceBodyDeclaration();
        for ( InterfaceBodyDeclarationContext declaration : declarations ) {
            // do member modifiers here
            List<ModifierContext> mcl = declaration.modifier();
            ModifierSet modifierSet = new ModifierSet();
            for (ModifierContext mc : mcl) {
                modifierSet.addModifier(mc.getText());   
            }
            int memberModifiers = modifierSet.getModifiers();
            
            if ( declaration.interfaceMemberDeclaration() != null ) {
                InterfaceMemberDeclarationContext dctx = declaration.interfaceMemberDeclaration();
                
                // constDeclaration
                if ( dctx.constDeclaration() != null) {
                    List<TigerNode> fields = processConstDeclaration( dctx.constDeclaration(), memberModifiers );
                    for (TigerNode field : fields) {
                        interfaceNode.addChild(field);   
                    }
                }
                
                // interface methods
                if ( dctx.interfaceMethodDeclaration() != null ) {
                    interfaceNode.addChild(processInterfaceMethodDeclaration( dctx.interfaceMethodDeclaration().interfaceCommonBodyDeclaration(), memberModifiers ));
                }
                
                // interface generic methods
                // genericInterfaceMethodDeclaration
                //     : interfaceMethodModifier* typeParameters interfaceCommonBodyDeclaration
                //     ;
                //
                // Treat this as a regular interface method
                if ( dctx.genericInterfaceMethodDeclaration() != null) {
                    GenericInterfaceMethodDeclarationContext gimd = dctx.genericInterfaceMethodDeclaration();
                    MethodNode methodNode = processInterfaceGenericMethodDeclaration( gimd.interfaceCommonBodyDeclaration(), memberModifiers);

                    // type parameters
                    if (gimd.typeParameters() != null) {
                        TypeParametersContext tpc = ctx.typeParameters();
                        if (tpc != null) {
                            List<TypeParameterContext> typeParameterList = tpc.typeParameter();
                            StringBuilder sb = new StringBuilder();
                            for (TypeParameterContext typeParameter : typeParameterList) {
                                sb.append(typeParameter.getText());
                            }
                            methodNode.setTypeParams(sb.toString());
                        }
                    }

                    interfaceNode.addChild(methodNode);    
                }
                
                // interfaces
                if ( dctx.interfaceDeclaration() != null) {
                    interfaceNode.addChild(processInterfaceDeclaration(  dctx.interfaceDeclaration(), memberModifiers));   
                }
                
                // annotation types
                if ( dctx.annotationTypeDeclaration() != null) {
                    interfaceNode.addChild(processAnnotationTypeDeclaration( dctx.annotationTypeDeclaration(), memberModifiers));   
                }
                
                // classes
                if ( dctx.classDeclaration() != null) {
                    interfaceNode.addChild(processClassDeclaration( dctx.classDeclaration(), memberModifiers));   
                }
                
                // enums
                if ( dctx.enumDeclaration() != null) {
                    interfaceNode.addChild(processEnumDeclaration( dctx.enumDeclaration(), memberModifiers));   
                }
                
                // records
                if ( dctx.recordDeclaration() != null) {
                    interfaceNode.addChild(processRecordDeclaration( dctx.recordDeclaration(), memberModifiers));   
                }
            }
        }
        
        results.incClassCount();
        return interfaceNode;
	}
	
	/**
 	 * 
 	 * annotation
 	 *     : ('@' qualifiedName | altAnnotationQualifiedName) ('(' ( elementValuePairs | elementValue )? ')')?
 	 *     ;
 	 * 
 	 */
	private AnnotationNode processAnnotation(AnnotationContext ctx) {
	    String name = null;
	    if (ctx.qualifiedName() != null) {
	        name = ctx.qualifiedName().getText();    
	    }
	    else if (ctx.altAnnotationQualifiedName() != null) {
	        name = ctx.altAnnotationQualifiedName().getText();   
	    }
	    AnnotationNode annotationNode = new AnnotationNode(name);
	    setLocations(annotationNode, ctx);
	    
	    if (ctx.elementValue() != null) {
	        ElementValueContext evc = ctx.elementValue();
	        if (evc.annotation() != null) {
	            annotationNode.addAnnotation(processAnnotation(evc.annotation()));   
	        }
	        else if (evc.expression() != null) {
	            TigerNode child = new TigerNode(evc.expression().getText());
	            setLocations(child, evc);
	            annotationNode.addChild(child);
	        }
	    }
	    else if (ctx.elementValuePairs() != null) {
	        // TODO: finish this    
	    }
	    return annotationNode;
	}

	/**
 	 * annotationTypeDeclaration
 	 *     : '@' INTERFACE identifier annotationTypeBody
 	 *     ;
  	 * annotationTypeBody
  	 *     : '{' (annotationTypeElementDeclaration)* '}'
  	 *     ;
  	 * 
  	 * annotationTypeElementDeclaration
  	 *     : modifier* annotationTypeElementRest
  	 *     | ';' // this is not allowed by the grammar, but apparently allowed by the actual compiler
  	 *     ;
  	 * 
  	 * annotationTypeElementRest
  	 *     : typeType annotationMethodOrConstantRest ';'
  	 *     | classDeclaration ';'?
  	 *     | interfaceDeclaration ';'?
  	 *     | enumDeclaration ';'?
  	 *     | annotationTypeDeclaration ';'?
  	 *     | recordDeclaration ';'? // Java17
  	 *     ;
  	 * 
  	 * annotationMethodOrConstantRest
  	 *     : annotationMethodRest
  	 *     | annotationConstantRest
  	 *     ;
  	 * 
  	 * annotationMethodRest
  	 *     : identifier '(' ')' defaultValue?
  	 *     ;
  	 * 
  	 * annotationConstantRest
  	 *     : variableDeclarators
  	 *     ;
  	 * 
  	 * defaultValue
  	 *     : DEFAULT elementValue
  	 *     ;
 	 *
 	 * TODO: handle the annotationTypeBody
 	 */
	private TigerNode processAnnotationTypeDeclaration(AnnotationTypeDeclarationContext ctx, int modifiers) {
	    String name = "@ interface " + ctx.identifier().getText();
	    return new AnnotationTypeNode(name, modifiers);
	}

	/**
 	 * enumDeclaration
 	 *     : ENUM identifier (IMPLEMENTS typeList)? '{' enumConstants? ','? enumBodyDeclarations? '}'
 	 *     ;
  	 */
	private TigerNode processEnumDeclaration(EnumDeclarationContext ctx, int modifiers) {
	    EnumNode node = new EnumNode(ctx.identifier().getText(), modifiers);
	    setLocations(node, ctx);
	    
	    if (ctx.typeList() != null) {
            TypeListContext tlc = ctx.typeList();
            List<TypeTypeContext> ttc = tlc.typeType();
            List<TigerNode> implementsTypes = new ArrayList<TigerNode>();
            for (TypeTypeContext t : ttc) {
                ClassOrInterfaceTypeContext coitc = t.classOrInterfaceType();
                if (coitc != null) {
                    TigerNode type = new TigerNode(coitc.getText());
                    setLocations(type, coitc);
                    implementsTypes.add(type);
                }
            }
            node.setImplementsList( implementsTypes );
	    }
	    
	    if (ctx.enumConstants() != null) {
	        EnumConstantsContext enumConstants = ctx.enumConstants();
	        List<EnumConstantContext> enumConstantsList = enumConstants.enumConstant();
	        for (EnumConstantContext enumConstant : enumConstantsList) {
	            String constantName = enumConstant.identifier().getText();
	            TigerNode constantNode = new TigerNode(constantName) {
	                public int getOrdinal() {
	                    return TigerNode.ENUM;   
	                }
	            };
	            setLocations(constantNode, enumConstant);
	            node.addChild(constantNode);
	            
                // annotation
                //    : ('@' qualifiedName | altAnnotationQualifiedName) ('(' ( elementValuePairs | elementValue )? ')')?
                //    ;
                //	             
                if (enumConstant.annotation() != null) {
                    List<AnnotationContext> annotationList = enumConstant.annotation();
                    for (AnnotationContext annotation : annotationList) {
                        AnnotationNode annotationNode = processAnnotation(annotation);
                        constantNode.addAnnotation(annotationNode);
                    }
                }
                
                // arguments
                //     : '(' expressionList? ')'
                //     ;                
                if (enumConstant.arguments() != null) {
                    ArgumentsContext ac = enumConstant.arguments();
                    ExpressionListContext elc = ac.expressionList();
                    if (elc != null) {
                        List<ExpressionContext> expressionList = elc.expression();
                        for (ExpressionContext expression : expressionList) {
                            // TODO: really should go deeper into these expressions
                            TigerNode child = null;
                            if (expression.primary() != null) {
                                child = new TigerNode(expression.primary().getText());
                            }
                            else if (expression.methodCall() != null) {
                                child = new TigerNode(expression.methodCall().getText());
                            }
                            else if (expression.creator() != null) {
                                child = new TigerNode(expression.creator().getText());
                            }
                            if (child != null) {
                                setLocations(child, expression);
                                constantNode.addChild(child);
                            }
                        }
                    }
                }
                
                if (enumConstant.classBody() != null) {
                    ClassBodyContext classBodyContext = enumConstant.classBody();
                    List<ClassBodyDeclarationContext> declarations = ( List <ClassBodyDeclarationContext> )classBodyContext.classBodyDeclaration();
                    processClassBodyDeclaration( constantNode, declarations);
                }
	        }
	    }
	    
	    if (ctx.enumBodyDeclarations() != null) {
	        EnumBodyDeclarationsContext ebdc = ctx.enumBodyDeclarations();
            List<ClassBodyDeclarationContext> declarations = ebdc.classBodyDeclaration();
            processClassBodyDeclaration( node, declarations);
	    }
	    
	    return node;
	}
	
	/**
	 * java 17
 	 * recordDeclaration
 	 *     : RECORD identifier typeParameters? recordHeader
 	 *       (IMPLEMENTS typeList)?
 	 *       recordBody
 	 *     ;
  	 *
  	 */
	private TigerNode processRecordDeclaration(RecordDeclarationContext ctx, int modifiers) {
	    String name = ctx.identifier().getText();
	    RecordNode node = new RecordNode(name, modifiers);
	    setLocations(node, ctx);
	    
	    // type parameters
	    if (ctx.typeParameters() != null) {
	        TypeParametersContext tpc = ctx.typeParameters();
	        if (tpc != null) {
                List<TypeParameterContext> typeParameterList = tpc.typeParameter();
                StringBuilder sb = new StringBuilder();
                for (TypeParameterContext typeParameter : typeParameterList) {
                    sb.append(typeParameter.getText());
                }
                node.setTypeParams(sb.toString());
	        }
	    }
	    
	    
        if ( ctx.typeList() != null ) {
            TypeListContext tlc = ctx.typeList();
            List<TypeTypeContext> ttc = tlc.typeType();
            List<TigerNode> implementsTypes = new ArrayList<TigerNode>();
            for (TypeTypeContext t : ttc) {
                ClassOrInterfaceTypeContext coitc = t.classOrInterfaceType();
                if (coitc != null) {
                    TigerNode type = new TigerNode(coitc.getText());
                    setLocations(type, coitc);
                    implementsTypes.add(type);
                }
            }
            node.setImplementsList( implementsTypes );
        }
        
        RecordHeaderContext recordHeader = ctx.recordHeader();
        RecordComponentListContext recordComponentList = recordHeader.recordComponentList();
        if (recordComponentList != null) {
            List<RecordComponentContext> recordComponents = recordComponentList.recordComponent();
            for (RecordComponentContext recordComponent : recordComponents) {
                Type type = new Type(recordComponent.typeType().getText());
                String componentName = recordComponent.identifier().getText();
                RecordComponent rc = new RecordComponent(type, componentName);
                node.addParameter(rc);
            }
	    }
        RecordBodyContext recordBody = ctx.recordBody();
        List<ClassBodyDeclarationContext> classBody = recordBody.classBodyDeclaration();
        processClassBodyDeclaration(node, classBody);
	    return node;
	}
	
	/**
 	 * constDeclaration
 	 *     : typeType constantDeclarator (',' constantDeclarator)* ';'
 	 *     ;
  	 * constantDeclarator
  	 *     : identifier ('[' ']')* '=' variableInitializer
  	 *     ;
  	 *
  	 * These are just fields in an interface.
  	 */
	private List<TigerNode> processConstDeclaration(ConstDeclarationContext ctx, int modifiers) {

        Type type = new Type( ctx.typeType().getText() );
	    
        List<TigerNode> fields = new ArrayList<TigerNode>();
	    List<ConstantDeclaratorContext> idList = ctx.constantDeclarator();
        for ( ConstantDeclaratorContext id : idList ) {
            String name = id.getText();
            VariableDeclarator vd = new VariableDeclarator(name);
            FieldNode fn = new FieldNode( name, modifiers, type );
            fn.addChild(vd);
            setLocations( fn, id );
            if (fn.isPrimitive()) {
                results.incPrimitiveFieldCount();
            }
            else {
                results.incReferenceFieldCount();
            }
            results.incReferenceFieldCount();
            fields.add(fn);
        }
        return fields;
	}

	
	/**
 	 * interfaceMethodDeclaration
 	 *     : interfaceMethodModifier* interfaceCommonBodyDeclaration
 	 *     ;
 	 * 
 	 */
	private MethodNode processInterfaceMethodDeclaration( InterfaceCommonBodyDeclarationContext ctx, int modifiers ) {
        MethodNode methodNode = new MethodNode();

        setLocations( methodNode, ctx );

        // modifiers
        methodNode.setModifiers(modifiers);
        
        // return type
        TypeTypeOrVoidContext ttvc = ctx.typeTypeOrVoid();
        if (ttvc != null) {     // should never be null, is this check necessary?
            Type returnType = new Type(ttvc.getText());
            setLocations(returnType, ttvc);
            methodNode.setReturnType(returnType);
        }
        
        // method name
        String name = ctx.identifier().getText();
        methodNode.setName(name);
        
        // parameters
        FormalParametersContext paramsCtx = ctx.formalParameters();

        // receiver parameters -- type identifier.this
        if (paramsCtx.receiverParameter() != null) {
            ReceiverParameterContext rpc = paramsCtx.receiverParameter();
            
            Parameter parameterNode = new Parameter();
            setLocations( parameterNode, rpc );
            Type type = new Type( rpc.typeType().getText() );
            parameterNode.setType( type );
            StringBuilder paramName = new StringBuilder();
            List<IdentifierContext> idList = rpc.identifier();
            for (IdentifierContext id : idList) {
                paramName.append(id.getText()).append('.');
            }
            paramName.append("this");
            parameterNode.setName( paramName.toString() );
            methodNode.addParameter( parameterNode );
        }
        
        // other parameters
        FormalParameterListContext paramsListCtx = paramsCtx.formalParameterList();
        if (paramsListCtx != null) {
            List<FormalParameterContext> paramsList = paramsListCtx.formalParameter();
            if (paramsList != null) {
                for (FormalParameterContext param : paramsList) {
                    Parameter parameterNode = new Parameter();
                    setLocations( parameterNode, param );
                    Type type = new Type( param.typeType().getText() );
                    parameterNode.setType( type );
                    parameterNode.setName( param.variableDeclaratorId().identifier().getText() );
    
                    // modifiers
                    int size = param.variableModifier().size();
                    String[] paramModifierNames = new String [size];
                    for ( int i = 0; i < size; i++ ) {
                        paramModifierNames[i] = param.variableModifier( i ).getText();
                    }
                    parameterNode.setModifiers( ModifierSet.getModifiers( paramModifierNames ));
                    methodNode.addParameter( parameterNode );
                }
            }
            // last formal parameter -- modifier* type annotation* ... variableId
            if (paramsListCtx.lastFormalParameter() != null) {
                LastFormalParameterContext lfpc = paramsListCtx.lastFormalParameter();
                if ( lfpc != null ) {
                    Parameter parameterNode = new Parameter();
                    setLocations( parameterNode, lfpc );
                    parameterNode.setType(new Type(lfpc.typeType().getText()));
                    parameterNode.setName(lfpc.variableDeclaratorId().getText());
                    
                    // modifiers
                    int size = lfpc.variableModifier().size();
                    String[] modifierNames = new String [size];
                    for ( int i = 0; i < size; i++ ) {
                        modifierNames[i] = lfpc.variableModifier( i ).getText();
                    }
                    parameterNode.setModifiers( ModifierSet.getModifiers( modifierNames ) );
                    
                    // annotations
                    if (lfpc.annotation() != null) {
                        List<AnnotationContext> annotationList = lfpc.annotation();
                        for (AnnotationContext annotation : annotationList) {
                            parameterNode.addAnnotation(processAnnotation(annotation));    
                        }
                    }
                    
                    methodNode.addParameter( parameterNode );
                }
            }
        }
        
        // throws
        if (ctx.THROWS() != null) {
            QualifiedNameListContext qnlc = ctx.qualifiedNameList();
            if (qnlc != null) {
                List<QualifiedNameContext> nameList = qnlc.qualifiedName();
                List<TigerNode> exceptionList = new ArrayList<TigerNode>();
                for (QualifiedNameContext qname : nameList) {
                    TigerNode tn = new TigerNode( getQualifiedName(qname) );
                    setLocations( tn, qname );
                    exceptionList.add( tn );
                }
                methodNode.setThrows( exceptionList );
            }
        }
        
        results.incMethodCount();
        return methodNode;
	}
	
	/**
 	 * genericInterfaceMethodDeclaration
 	 *     : interfaceMethodModifier* typeParameters interfaceCommonBodyDeclaration
 	 *     ;
 	 *
     * Treat this as a regular interface method
 	 */
	private MethodNode processInterfaceGenericMethodDeclaration( InterfaceCommonBodyDeclarationContext ctx, int modifiers) {
        return processInterfaceMethodDeclaration(ctx, modifiers);
	}
}