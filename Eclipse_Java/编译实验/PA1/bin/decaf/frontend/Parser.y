/*
 * 本文件提供实现Decaf编译器所需要的BYACC脚本。
 * 在第一阶段中你需要补充完整这个文件中的语法规则。
 * 请参考"YACC--Yet Another Compiler Compiler"中关于如何编写BYACC脚本的说明。
 * 
 * Keltin Leung, Zhang Duo
 * DCST, Tsinghua University
 */
 
%{
package decaf.frontend;

import decaf.ast.*;
import decaf.error.*;
import java.util.*;
%}

%Jclass Parser
%Jextends BaseParser
%Jsemantic SemValue
%Jimplements ReduceListener
%Jnorun
%Jnodebug
%Jnoconstruct

%token VOID   BOOL  INT   DOUBLE   STRING  CLASS 
%token NULL   EXTENDS     THIS     WHILE   FOR   
%token IF     ELSE        RETURN   BREAK   NEW
%token PRINT  READ_INTEGER         READ_LINE
%token STRING_CONST   INT_CONST    BOOL_CONST    DOUBLE_CONST
%token IDENTIFIER	  AND    OR    STATIC
%token LESS_EQUAL   GREATER_EQUAL  EQUAL   NOT_EQUAL
%token '+'  '-'  '*'  '/'  '%'  '='  '>'  '<'  '.'
%token ','  ';'  '!'  '('  ')'  '['  ']'  '{'  '}'

%nonassoc '='
%left OR
%left AND 
%nonassoc EQUAL NOT_EQUAL
%nonassoc LESS_EQUAL GREATER_EQUAL '<' '>'
%left '+' '-'
%left '*' '/' '%'
%right '!'
%left '[' '.'
%nonassoc IF
%nonassoc ELSE

%start Program
 
%%
Program			:	ClassDefnList
					{
						tree = new Program($1.clist, $1.loc);
					}
				;

ClassDefnList       :	ClassDefnList ClassDefn
					{
						$$.clist.add($2.cdef);
					}
                |	ClassDefn
                	{
                		$$.clist = new ArrayList<ClassDefn>();
                		$$.clist.add($1.cdef);
                	}
                ;

VariableDecl    :	Variable ';'
					{
						$$.vdecl = $1.vdecl;
					}
				;

Variable        :	Type IDENTIFIER
					{
						$$.vdecl = new VarDecl($2.sval, $1.type, $2.loc);
					}
				;
				
Type            :	INT
					{
						$$.type = new IntType($1.loc);
					}
					
				|	DOUBLE
					{
						$$.type = new DoubleType($1.loc);
					}
				|	BOOL
					{
						$$.type = new BoolType($1.loc);
					}
				|	STRING
					{
						$$.type = new StringType($1.loc);
					}
				|	VOID
					{
						$$.type = new VoidType($1.loc);
					}
				|	CLASS IDENTIFIER
					{
						$$.type = new ClassType($2.sval,$1.loc);
					}
				|	Type '[' ']'
					{
						$$.type = new ArrayType($1.type,$1.loc);
					}
					
                ;
FormalsList		:	FormalsList ',' Variable
					{
						$$.vlist.add($3.vdecl);
					}
				|	Variable
					{
						//$$=new SemValue();
						$$.vlist=new ArrayList<VarDecl>();
						$$.vlist.add($1.vdecl);
					}
				|	/* empty */
					{
						$$=new SemValue();
						$$.vlist=new ArrayList<VarDecl>();
					}

FunctionDefn	:	STATIC Type IDENTIFIER '(' FormalsList ')' StmtBlock
					{
						$$.fdef=new FuncDefn(true, $3.sval, $2.type, $5.vlist, (StmtBlock)$7.stmt, $3.loc);
					}
				|	Type IDENTIFIER '(' FormalsList ')' StmtBlock
					{
						$$.fdef=new FuncDefn(false, $2.sval, $1.type, $4.vlist, (StmtBlock)$6.stmt, $2.loc);
					}						
						
StmtBlock		:	'{' StmtList '}'
					{
						//$$=new SemValue();
						$$.stmt=new StmtBlock($2.slist,$2.loc);
					}


ClassDefn       :	CLASS IDENTIFIER ExtendsClause '{' FieldList '}'
					{
						$$.cdef = new ClassDefn($2.sval, $3.sval, $5.flist, $1.loc);
					}
                ;

ExtendsClause	:	EXTENDS IDENTIFIER
					{
						$$.sval = $2.sval;
					}
                |	/* empty */
                	{
                		$$ = new SemValue();
                	}
                ;

FieldList       :	FieldList VariableDecl
					{
						$$.flist.add($2.vdecl);
					}
				|
					FieldList FunctionDefn
					{
						$$.flist.add($2.fdef);
					}
				|   /* empty */
					{
						$$ = new SemValue();
						$$.flist = new ArrayList<Field>();
					}

StmtList		:	StmtList VariableDecl
				{
					//$$=new SemValue();
					//$$.slist=new ArrayList<Statement>();
					$$.slist.add(new VarDeclStmt($2.vdecl,$2.loc));
				}
				|	StmtList StmtBlock
				{
					$$.slist.add($2.stmt);
				}
				|	StmtList SimpleStmt ';'
				{
					if($2.stmt!=null)
						$$.slist.add($2.stmt);
				}
				|	StmtList PrintStmt ';'
				{
					$$.slist.add($2.stmt);
				}
				|	StmtList BreakStmt ';'
				{
					$$.slist.add($2.stmt);
				}
				|	StmtList ReturnStmt ';'
				{
					$$.slist.add($2.stmt);
				}
				|	StmtList WhileStmt
				{	
					$$.slist.add($2.stmt);
				}
				|	StmtList ForStmt
				{	
					$$.slist.add($2.stmt);
				}
				|	StmtList IfStmt
				{	
					$$.slist.add($2.stmt);
				}
				|	/* empty */
				{
					$$=new SemValue();
					$$.slist=new ArrayList<Statement>();
				}
				
Stmt			:	 VariableDecl
				{
					//$$=new SemValue();
					//$$.slist=new ArrayList<Statement>();
					$$.stmt=new VarDeclStmt($1.vdecl,$1.loc);
				}
				|	StmtBlock
				{
					$$.stmt=$1.stmt;
				}
				|	SimpleStmt ';'
				{
					if($1.stmt!=null)
						$$.stmt=$1.stmt;
				}
				|	PrintStmt ';'
				{
					$$.stmt=$1.stmt;
				}
				|	BreakStmt ';'
				{
					$$.stmt=$1.stmt;
				}
				|	ReturnStmt ';'
				{
					$$.stmt=$1.stmt;
				}
				|	WhileStmt
				{
					$$.stmt=$1.stmt;
				}
				|	ForStmt
				{
					$$.stmt=$1.stmt;
				}
				|	IfStmt
				{
					$$.stmt=$1.stmt;
				}
				
					
                ;
Constant		:	INT_CONST
				{
					$$.expr=new IntConst($1.ival,$1.loc);
				}
				|	DOUBLE_CONST
				{
					$$.expr=new DoubleConst($1.dval,$1.loc);
				}
				|	BOOL_CONST
				{
					$$.expr=new BoolConst($1.bval,$1.loc);
				}
				|	STRING_CONST
				{
					$$.expr=new StringConst($1.sval,$1.loc);
				}
				|	NULL
				{
					$$.expr=new NullExpr($1.loc);
				}

Expr			:	Constant
				{
					$$.expr=$1.expr;
				}
				|	THIS
				{
					$$.expr=new ThisExpr($1.loc);
				}
				|	LValue
				{
					$$.expr=new LValueExpr($1.lvalue,$1.loc);
				}
				|	Call
				{
					$$.expr=$1.expr;
				}
				|	'(' Expr ')'
				{
					$$.expr=$2.expr;
				}
				|	READ_INTEGER '(' ')'
				{
					$$.expr=new ReadIntExpr($1.loc);
				}
				|	READ_LINE '(' ')'
				{
					$$.expr=new ReadLineExpr($1.loc);
				}
				|	NEW IDENTIFIER '(' ')'
				{
					$$.expr=new NewObjExpr($2.sval,$2.loc);
				}
				|	NEW Type '[' Expr ']'
				{
					$$.expr=new NewArrayExpr($2.type,$4.expr,$2.loc);
				}
				|	Expr '+' Expr
				{
					$$.expr=new AddExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr '-' Expr
				{
					$$.expr=new SubExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr '*' Expr
				{
					$$.expr=new MulExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr '/' Expr
				{
					$$.expr=new DivExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr '%' Expr
				{
					$$.expr=new ModExpr($1.expr,$3.expr,$1.loc);
				}
				|	'-' Expr %prec '!'
				{
					$$.expr=new NegExpr($2.expr,$2.loc);
				}
				|	Expr '<' Expr
				{
					$$.expr=new LesExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr LESS_EQUAL Expr
				{
					$$.expr=new LeqExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr '>' Expr
				{
					$$.expr=new GtrExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr GREATER_EQUAL Expr
				{
					$$.expr=new GeqExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr EQUAL Expr
				{
					$$.expr=new EquExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr NOT_EQUAL Expr
				{
					$$.expr=new NeqExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr AND Expr
				{
					$$.expr=new AndExpr($1.expr,$3.expr,$1.loc);
				}
				|	Expr OR Expr
				{
					$$.expr=new OrExpr($1.expr,$3.expr,$1.loc);
				}
				|	'!' Expr
				{
					$$.expr=new NotExpr($2.expr,$2.loc);
				}
				

ExprList		:	ExprList ',' Expr
				{
					$$.elist.add($3.expr);
				}
				|	Expr
				{
					$$.elist=new ArrayList<Expr>();
					$$.elist.add($1.expr);
				}
				
PrintStmt		:	PRINT '(' ExprList ')'
				{
					$$.stmt=new PrintStmt($3.elist,$3.loc);
				}
BreakStmt		:	BREAK
				{
					$$.stmt=new BreakStmt($1.loc);
				}
ReturnStmt		:	RETURN Expr
				{
					$$.stmt=new ReturnStmt($2.expr,$1.loc);
				}
				|	RETURN
				{
					$$.stmt=new ReturnStmt(null,$1.loc);
				}
Call			:	Expr '.' IDENTIFIER '(' Actuals ')'
				{
					$$.expr=new CallExpr($1.expr,$3.sval,$5.elist,$3.loc);
				}
				|	IDENTIFIER '(' Actuals ')'
				{
					$$.expr=new CallExpr(null,$1.sval,$3.elist,$3.loc);
				}
Actuals			:	ExprList
				{
					$$.elist=$1.elist;
				}
				|	/* empty */
				{
					$$=new SemValue();
					$$.elist=new ArrayList<Expr>();
				}
LValue			:	Expr '.' IDENTIFIER
				{
					$$.lvalue=new VarRef($1.expr,$3.sval,$3.loc);
				}
				|	IDENTIFIER
				{
					$$.lvalue=new VarRef(null,$1.sval,$1.loc);
				}
				|	Expr '[' Expr ']'
				{
					$$.lvalue=new ArrayRef($1.expr,$3.expr,$1.loc);
				}
SimpleStmt		:	LValue '=' Expr
				{
					$$.stmt=new AssignStmt($1.lvalue,$3.expr,$1.loc);
				}	
				|	Call
				{
					$1.stmt=new ExprStmt($1.expr,$1.loc);
				}
				|	/* empty */
				{
					$$=new SemValue();
				}
BoolExpr		:	Expr
				{
					$$.expr=$1.expr;
				}
WhileStmt		:	WHILE '(' BoolExpr ')' Stmt
				{
					$$.stmt=new WhileStmt($3.expr,$5.stmt,$1.loc);
				}
ForStmt			:	FOR '(' SimpleStmt ';' BoolExpr ';' SimpleStmt ')' Stmt
				{
					$$.stmt=new ForStmt($3.stmt,$5.expr,$7.stmt,$9.stmt,$1.loc);
				}
IfStmt			:	IF '(' BoolExpr ')' Stmt ELSE Stmt %prec ELSE
				{
					$$.stmt=new IfStmt($3.expr,$5.stmt,$7.stmt,$1.loc);
				}
				|	IF '(' BoolExpr ')' Stmt	%prec IF
				{
					$$.stmt=new IfStmt($3.expr,$5.stmt,null,$1.loc);
				}
%%
    
	/**
	 * 打印当前归约所用的语法规则<br>
	 * 请勿修改。
	 */
    public boolean onReduce(String rule) {
		if (rule.startsWith("$$"))
			return false;
		else
			rule = rule.replaceAll(" \\$\\$\\d+", "");

   	    if (rule.endsWith(":"))
    	    System.out.println(rule + " <empty>");
   	    else
			System.out.println(rule);
		return false;
    }
    
    public void diagnose() {
		addReduceListener(this);
		yyparse();
	}