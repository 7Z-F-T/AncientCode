/*
 * 本文件提供实现Decaf编译器所需要的BYACC脚本。
 * 在第一阶段中你需要补充完整这个文件中的语法规则。
 * 请参考"YACC--Yet Another Compiler Compiler"中关于如何编写BYACC脚本的说明。
 * 
 * Keltin Leung
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

%left OR
%left AND 
%nonassoc EQUAL NOT_EQUAL
%nonassoc LESS_EQUAL GREATER_EQUAL '<' '>'
%left  '+' '-'
%left  '*' '/' '%'  
%nonassoc UMINUS '!' 
%nonassoc '[' '.' 
%nonassoc ')' EMPTY
%nonassoc ELSE

%start Program
 
%%
Program			:	ClassList
					{
						tree = new Program($1.clist, $1.loc);
					}
				;

ClassList       :	ClassList ClassDefn
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
                |	VOID
                	{
                		$$.type = new VoidType($1.loc);
                	}
                |	BOOL
                	{
                		$$.type = new BoolType($1.loc);
                	}
                |	STRING
                	{
                		$$.type = new StringType($1.loc);
                	}
                |	DOUBLE
                	{
                		$$.type = new DoubleType($1.loc);
                		//issueError(new DoubleNotSupport($1.loc));
                	}
                |	CLASS IDENTIFIER
                	{
                		$$.type = new ClassType($2.sval, $1.loc);
                	}
                |	Type '[' ']'
                	{
                		$$.type = new ArrayType($1.type, $1.loc);
                	}
                ;

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
				|	FieldList FunctionDefn
					{
						$$.flist.add($2.fdef);
					}
                |	/* empty */
                	{
                		$$ = new SemValue();
                		$$.flist = new ArrayList<Field>();
                	}
                ;
 
Formals         :	VariableList
                |	/* empty */
                	{
                		$$ = new SemValue();
                		$$.vlist = new ArrayList<VarDecl>(); 
                	}
                ;

VariableList    :	VariableList ',' Variable
					{
						$$.vlist.add($3.vdecl);
					}
                |	Variable
                	{
                		$$.vlist = new ArrayList<VarDecl>();
						$$.vlist.add($1.vdecl);
                	}
                ;

FunctionDefn    :	STATIC Type IDENTIFIER '(' Formals ')' StmtBlock
					{
						$$.fdef = new FuncDefn(true, $3.sval, $2.type, $5.vlist, (StmtBlock) $7.stmt, $3.loc);
					}
				|	Type IDENTIFIER '(' Formals ')' StmtBlock
					{
						$$.fdef = new FuncDefn(false, $2.sval, $1.type, $4.vlist, (StmtBlock) $6.stmt, $2.loc);
					}
                ;

StmtBlock       :	'{' StmtList '}'
					{
						$$.stmt = new StmtBlock($2.slist, $1.loc);
					}
                ;
	
StmtList        :	StmtList Stmt
					{
						$$.slist.add($2.stmt);
					}
                |	/* empty */
                	{
                		$$ = new SemValue();
                		$$.slist = new ArrayList<Statement>();
                	}
                ;

Stmt		:	VariableDecl
					{
						$$.stmt = new VarDeclStmt($1.vdecl, $1.loc);
					}
                |	SimpleStmt ';'
                |	IfStmt
                |	WhileStmt
                |	ForStmt
                |	ReturnStmt ';'
                |	PrintStmt ';'
                |	BreakStmt ';'
                |	StmtBlock
 
SimpleStmt      :	LValue '=' Expr
					{
						$$.stmt = new AssignStmt($1.lvalue, $3.expr, $2.loc);
					}
                |	Call
                	{
                		$$.stmt = new ExprStmt($1.expr, $1.loc);
                	}
                |	/* empty */
                	{
                		$$ = new SemValue();
                	}
                ;

Receiver     	:	Expr '.'
                |	/* empty */
                	{
                		$$ = new SemValue();
                	}
                ; 

LValue          :	Receiver IDENTIFIER
					{
						$$.lvalue = new VarRef($1.expr, $2.sval, $2.loc);
						if ($1.loc == null) {
							$$.loc = $2.loc;
						}
					}
                |	Expr '[' Expr ']'
                	{
                		$$.lvalue = new ArrayRef($1.expr, $3.expr, $1.loc);
                	}
                ;

Call            :	Receiver IDENTIFIER '(' Actuals ')'
					{
						$$.expr = new CallExpr($1.expr, $2.sval, $4.elist, $2.loc);
						if ($1.loc == null) {
							$$.loc = $2.loc;
						}
					}
                ;

Expr            :	LValue
					{
						$$.expr = new LValueExpr($1.lvalue, $1.loc);
					}
                |	Call
                |	Constant
                |	Expr '+' Expr
                	{
                		$$.expr = new AddExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr '-' Expr
                	{
                		$$.expr = new SubExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr '*' Expr
                	{
                		$$.expr = new MulExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr '/' Expr
                	{
                		$$.expr = new DivExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr '%' Expr
                	{
                		$$.expr = new ModExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr EQUAL Expr
                	{
                		$$.expr = new EquExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr NOT_EQUAL Expr
                	{
                		$$.expr = new NeqExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr '<' Expr
                	{
                		$$.expr = new LesExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr '>' Expr
                	{
                		$$.expr = new GtrExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr LESS_EQUAL Expr
                	{
                		$$.expr = new LeqExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr GREATER_EQUAL Expr
                	{
                		$$.expr = new GeqExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr AND Expr
                	{
                		$$.expr = new AndExpr($1.expr, $3.expr, $2.loc);
                	}
                |	Expr OR Expr
                	{
                		$$.expr = new OrExpr($1.expr, $3.expr, $2.loc);
                	}
                |	'(' Expr ')'
                	{
                		$$ = $2;
                	}
                |	'-' Expr  				%prec UMINUS
                	{
                		$$.expr = new NegExpr($2.expr, $1.loc);
                	}
                |	'!' Expr
                	{
                		$$.expr = new NotExpr($2.expr, $1.loc);
                	}
                |	READ_INTEGER '(' ')'
                	{
                		$$.expr = new ReadIntExpr($1.loc);
                	}
                |	READ_LINE '(' ')'
                	{
                		$$.expr = new ReadLineExpr($1.loc);
                	}
                |	THIS
                	{
                		$$.expr = new ThisExpr($1.loc);
                	}
                |	NEW IDENTIFIER '(' ')'
                	{
                		$$.expr = new NewObjExpr($2.sval, $1.loc);
                	}
                |	NEW Type '[' Expr ']'
                	{
                		$$.expr = new NewArrayExpr($2.type, $4.expr, $1.loc);
                	}
                ;
	
Constant        :	INT_CONST
					{
						$$.expr = new IntConst($1.ival, $1.loc);
					}
                |	BOOL_CONST
                	{
						$$.expr = new BoolConst($1.bval, $1.loc);
					}
                |	STRING_CONST
                	{
						$$.expr = new StringConst($1.sval, $1.loc);
					}
                |	DOUBLE_CONST
                	{
						$$.expr = new DoubleConst($1.dval, $1.loc);
						//issueError(new DoubleNotSupport($1.loc));
					}
                |	NULL
                	{
						$$.expr = new NullExpr($1.loc);
					}
                ;

Actuals         :	ExprList
                |	/* empty */
                	{
                		$$ = new SemValue();
                		$$.elist = new ArrayList<Expr>();
                	}
                ;

ExprList        :	ExprList ',' Expr
					{
						$$.elist.add($3.expr);
					}
                |	Expr
                	{
                		$$.elist = new ArrayList<Expr>();
						$$.elist.add($1.expr);
                	}
                ;
    
WhileStmt       :	WHILE '(' Expr ')' Stmt
					{
						$$.stmt = new WhileStmt($3.expr, $5.stmt, $1.loc);
					}
                ;

ForStmt         :	FOR '(' SimpleStmt ';' Expr ';'	SimpleStmt ')' Stmt
					{
						$$.stmt = new ForStmt($3.stmt, $5.expr, $7.stmt, $9.stmt, $1.loc);
					}
                ;

BreakStmt       :	BREAK
					{
						$$.stmt = new BreakStmt($1.loc);
					}
                ;

IfStmt          :	IF '(' Expr ')' Stmt ElseClause
					{
						$$.stmt = new IfStmt($3.expr, $5.stmt, $6.stmt, $1.loc);
					}
                ;

ElseClause      :	ELSE Stmt
					{
						$$.stmt = $2.stmt;
					}
				|	/* empty */				%prec EMPTY
					{
						$$ = new SemValue();
					}
                ;

ReturnStmt      :	RETURN Expr
					{
						$$.stmt = new ReturnStmt($2.expr, $1.loc);
					}
                |	RETURN
                	{
                		$$.stmt = new ReturnStmt(null, $1.loc);
                	}
                ;

PrintStmt       :	PRINT '(' ExprList ')'
					{
						$$.stmt = new PrintStmt($3.elist, $1.loc);
					}
                ;

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