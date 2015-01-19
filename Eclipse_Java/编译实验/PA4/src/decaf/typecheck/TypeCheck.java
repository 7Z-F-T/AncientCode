package decaf.typecheck;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import decaf.Driver;
import decaf.Location;
import decaf.ast.AddExpr;
import decaf.ast.AndExpr;
import decaf.ast.ArrayRef;
import decaf.ast.ArrayType;
import decaf.ast.AssignStmt;
import decaf.ast.BoolConst;
import decaf.ast.BoolType;
import decaf.ast.BreakStmt;
import decaf.ast.CallExpr;
import decaf.ast.ClassDefn;
import decaf.ast.ClassType;
import decaf.ast.DivExpr;
import decaf.ast.DoubleConst;
import decaf.ast.DoubleType;
import decaf.ast.EquExpr;
import decaf.ast.Expr;
import decaf.ast.ExprStmt;
import decaf.ast.Field;
import decaf.ast.ForStmt;
import decaf.ast.FuncDefn;
import decaf.ast.GeqExpr;
import decaf.ast.GtrExpr;
import decaf.ast.IfStmt;
import decaf.ast.IntConst;
import decaf.ast.IntType;
import decaf.ast.LValue;
import decaf.ast.LValueExpr;
import decaf.ast.LeqExpr;
import decaf.ast.LesExpr;
import decaf.ast.ModExpr;
import decaf.ast.MulExpr;
import decaf.ast.NegExpr;
import decaf.ast.NeqExpr;
import decaf.ast.NewArrayExpr;
import decaf.ast.NewObjExpr;
import decaf.ast.NoActionASTVisitor;
import decaf.ast.NotExpr;
import decaf.ast.NullExpr;
import decaf.ast.OrExpr;
import decaf.ast.PrintStmt;
import decaf.ast.Program;
import decaf.ast.ReadIntExpr;
import decaf.ast.ReadLineExpr;
import decaf.ast.ReturnStmt;
import decaf.ast.Statement;
import decaf.ast.StmtBlock;
import decaf.ast.StringConst;
import decaf.ast.StringType;
import decaf.ast.SubExpr;
import decaf.ast.ThisExpr;
import decaf.ast.VarRef;
import decaf.ast.VoidType;
import decaf.ast.WhileStmt;
import decaf.error.BadArgCountError;
import decaf.error.BadArgTypeError;
import decaf.error.BadArrElementError;
import decaf.error.BadLengthArgError;
import decaf.error.BadLengthError;
import decaf.error.BadNewArrayLength;
import decaf.error.BadPrintArgError;
import decaf.error.BadReturnTypeError;
import decaf.error.BadTestExpr;
import decaf.error.BreakOutOfLoopError;
import decaf.error.ClassNotFoundError;
import decaf.error.DecafError;
import decaf.error.FieldNotAccessError;
import decaf.error.FieldNotFoundError;
import decaf.error.IncompatBinOpError;
import decaf.error.IncompatUnOpError;
import decaf.error.NotArrayError;
import decaf.error.NotClassFieldError;
import decaf.error.NotClassMethodError;
import decaf.error.RefNonStaticError;
import decaf.error.SubNotIntError;
import decaf.error.ThisInStaticFuncError;
import decaf.error.UndeclVarError;
import decaf.frontend.Parser;
import decaf.scope.ClassScope;
import decaf.scope.FormalScope;
import decaf.scope.Scope;
import decaf.scope.ScopeStack;
import decaf.scope.Scope.Kind;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.symbol.Variable;
import decaf.type.BaseType;
import decaf.type.Type;

public class TypeCheck extends NoActionASTVisitor {

	private ScopeStack table;

	private Stack<Statement> breaks;

	private Function currentFunction;

	public TypeCheck(ScopeStack table) {
		this.table = table;
		breaks = new Stack<Statement>();
	}

	public static void checkType(Program tree) {
		new TypeCheck(Driver.getDriver().getTable()).visit(tree);
	}

	@Override
	public void visit(AddExpr addExpr) {
		addExpr.type = checkBinaryOp(addExpr.left, addExpr.right, '+', addExpr
				.getLocation());
	}

	@Override
	public void visit(DivExpr divExpr) {
		divExpr.type = checkBinaryOp(divExpr.left, divExpr.right, '/', divExpr
				.getLocation());
	}

	@Override
	public void visit(MulExpr mulExpr) {
		mulExpr.type = checkBinaryOp(mulExpr.left, mulExpr.right, '*', mulExpr
				.getLocation());
	}

	@Override
	public void visit(SubExpr subExpr) {
		subExpr.type = checkBinaryOp(subExpr.left, subExpr.right, '-', subExpr
				.getLocation());
	}

	@Override
	public void visit(ModExpr modExpr) {
		modExpr.type = checkBinaryOp(modExpr.left, modExpr.right, '%', modExpr
				.getLocation());
	}

	@Override
	public void visit(NegExpr negExpr) {
		negExpr.expr.accept(this);
		if (negExpr.expr.type.equal(BaseType.ERROR)
				|| negExpr.expr.type.equal(BaseType.INT)
				|| negExpr.expr.type.equal(BaseType.DOUBLE)) {
			negExpr.type = negExpr.expr.type;
		} else {
			issueError(new IncompatUnOpError(negExpr.getLocation(), "-",
					negExpr.expr.type.toString()));
			negExpr.type = BaseType.ERROR;
		}
	}

	@Override
	public void visit(EquExpr equExpr) {
		equExpr.type = checkBinaryOp(equExpr.left, equExpr.right, Parser.EQUAL,
				equExpr.getLocation());
	}

	@Override
	public void visit(GeqExpr geqExpr) {
		geqExpr.type = checkBinaryOp(geqExpr.left, geqExpr.right,
				Parser.GREATER_EQUAL, geqExpr.getLocation());
	}

	@Override
	public void visit(GtrExpr gtrExpr) {
		gtrExpr.type = checkBinaryOp(gtrExpr.left, gtrExpr.right, '>', gtrExpr
				.getLocation());
	}

	@Override
	public void visit(LeqExpr leqExpr) {
		leqExpr.type = checkBinaryOp(leqExpr.left, leqExpr.right,
				Parser.LESS_EQUAL, leqExpr.getLocation());
	}

	@Override
	public void visit(LesExpr lesExpr) {
		lesExpr.type = checkBinaryOp(lesExpr.left, lesExpr.right, '<', lesExpr
				.getLocation());
	}

	@Override
	public void visit(NeqExpr neqExpr) {
		neqExpr.type = checkBinaryOp(neqExpr.left, neqExpr.right,
				Parser.NOT_EQUAL, neqExpr.getLocation());
	}

	@Override
	public void visit(AndExpr andExpr) {
		andExpr.type = checkBinaryOp(andExpr.left, andExpr.right, Parser.AND,
				andExpr.getLocation());
	}

	@Override
	public void visit(OrExpr orExpr) {
		orExpr.type = checkBinaryOp(orExpr.left, orExpr.right, Parser.OR,
				orExpr.getLocation());
	}

	@Override
	public void visit(NotExpr notExpr) {
		notExpr.expr.accept(this);
		if (!(notExpr.expr.type.equal(BaseType.BOOL) || notExpr.expr.type
				.equal(BaseType.ERROR))) {
			issueError(new IncompatUnOpError(notExpr.getLocation(), "!",
					notExpr.expr.type.toString()));
		}
		notExpr.type = BaseType.BOOL;
	}

	@Override
	public void visit(BoolConst boolConst) {
		boolConst.type = BaseType.BOOL;
	}

	@Override
	public void visit(DoubleConst doubleConst) {
		doubleConst.type = BaseType.DOUBLE;
	}

	@Override
	public void visit(StringConst stringConst) {
		stringConst.type = BaseType.STRING;
	}

	@Override
	public void visit(IntConst intConst) {
		intConst.type = BaseType.INT;
	}

	@Override
	public void visit(LValueExpr valueExpr) {
		valueExpr.lv.usedForRef = valueExpr.usedForRef;
		valueExpr.lv.accept(this);
		valueExpr.type = valueExpr.lv.type;
		valueExpr.isClass = valueExpr.lv.isClass;
	}

	@Override
	public void visit(NullExpr nullExpr) {
		nullExpr.type = BaseType.NULL;
	}

	@Override
	public void visit(ReadIntExpr readIntExpr) {
		readIntExpr.type = BaseType.INT;
	}

	@Override
	public void visit(ReadLineExpr readStringExpr) {
		readStringExpr.type = BaseType.STRING;
	}

	@Override
	public void visit(ArrayRef arrayRef) {
		arrayRef.lvKind = LValue.Kind.ARRAY_ELEMENT;
		arrayRef.array.accept(this);
		if (!arrayRef.array.type.isArrayType()) {
			issueError(new NotArrayError(arrayRef.array.getLocation()));
			arrayRef.type = BaseType.ERROR;
		} else {
			arrayRef.type = ((decaf.type.ArrayType) arrayRef.array.type)
					.getElementType();
		}
		arrayRef.index.accept(this);
		if (!arrayRef.index.type.equal(BaseType.INT)) {
			issueError(new SubNotIntError(arrayRef.getLocation()));
		}
	}

	private void checkCallExpr(CallExpr callExpr, Symbol f) {
		Type receiverType = callExpr.receiver == null ? ((ClassScope) table
				.lookForScope(Scope.Kind.CLASS)).getOwner().getType()
				: callExpr.receiver.type;
		if (f == null) {
			issueError(new FieldNotFoundError(callExpr.getLocation(),
					callExpr.method, receiverType.toString()));
			callExpr.type = BaseType.ERROR;
		} else if (!f.isFunction()) {
			issueError(new NotClassMethodError(callExpr.getLocation(),
					callExpr.method, receiverType.toString()));
			callExpr.type = BaseType.ERROR;
		} else {
			Function func = (Function) f;
			callExpr.symbol = func;
			callExpr.type = func.getReturnType();
			if (callExpr.receiver == null && currentFunction.isStatik()
					&& !func.isStatik()) {
				issueError(new RefNonStaticError(callExpr.getLocation(),
						currentFunction.getName(), func.getName()));
			}
			if (!func.isStatik() && callExpr.receiver != null
					&& callExpr.receiver.isClass) {
				issueError(new NotClassFieldError(callExpr.getLocation(),
						callExpr.method, callExpr.receiver.type.toString()));
			}
			if (func.isStatik()) {
				callExpr.receiver = null;
			} else {
				if (callExpr.receiver == null && !currentFunction.isStatik()) {
					callExpr.receiver = new ThisExpr(callExpr.getLocation());
					callExpr.receiver.accept(this);
				}
			}
			for (Expr e : callExpr.actuals) {
				e.accept(this);
			}
			List<Type> argList = func.getType().getArgList();
			int argCount = func.isStatik() ? callExpr.actuals.size()
					: callExpr.actuals.size() + 1;
			if (argList.size() != argCount) {
				issueError(new BadArgCountError(callExpr.getLocation(),
						callExpr.method, func.isStatik() ? argList.size()
								: argList.size() - 1, callExpr.actuals.size()));
			} else {
				Iterator<Type> iter1 = argList.iterator();
				if (!func.isStatik()) {
					iter1.next();
				}
				Iterator<Expr> iter2 = callExpr.actuals.iterator();
				for (int i = 1; iter1.hasNext(); i++) {
					Type t1 = iter1.next();
					Expr e = iter2.next();
					Type t2 = e.type;
					if (!t2.equal(BaseType.ERROR) && !t2.compatible(t1)) {
						issueError(new BadArgTypeError(e.getLocation(), i, t2
								.toString(), t1.toString()));
					}
				}
			}
		}
	}

	@Override
	public void visit(CallExpr callExpr) {
		if (callExpr.receiver == null) {
			ClassScope cs = (ClassScope) table.lookForScope(Kind.CLASS);
			checkCallExpr(callExpr, cs.lookupVisible(callExpr.method));
			return;
		}
		callExpr.receiver.usedForRef = true;
		callExpr.receiver.accept(this);
		if (callExpr.receiver.type.equal(BaseType.ERROR)) {
			callExpr.type = BaseType.ERROR;
			return;
		}
		if (callExpr.method.equals("length")) {
			if (callExpr.receiver.type.isArrayType()) {
				if (callExpr.actuals.size() > 0) {
					issueError(new BadLengthArgError(callExpr.getLocation(),
							callExpr.actuals.size()));
				}
				callExpr.type = BaseType.INT;
				callExpr.isArrayLength = true;
				return;
			} else if (!callExpr.receiver.type.isClassType()) {
				issueError(new BadLengthError(callExpr.getLocation()));
				callExpr.type = BaseType.ERROR;
				return;
			}
		}

		if (!callExpr.receiver.type.isClassType()) {
			issueError(new NotClassFieldError(callExpr.getLocation(),
					callExpr.method, callExpr.receiver.type.toString()));
			callExpr.type = BaseType.ERROR;
			return;
		}

		ClassScope cs = ((decaf.type.ClassType) callExpr.receiver.type)
				.getClassScope();
		checkCallExpr(callExpr, cs.lookupVisible(callExpr.method));
	}

	@Override
	public void visit(NewArrayExpr newArrayExpr) {
		newArrayExpr.elementType.accept(this);
		if (newArrayExpr.elementType.type.equal(BaseType.ERROR)) {
			newArrayExpr.type = BaseType.ERROR;
		} else if (newArrayExpr.elementType.type.equal(BaseType.VOID)) {
			issueError(new BadArrElementError(newArrayExpr.elementType
					.getLocation()));
			newArrayExpr.type = BaseType.ERROR;
		} else {
			newArrayExpr.type = new decaf.type.ArrayType(
					newArrayExpr.elementType.type);
		}
		newArrayExpr.length.accept(this);
		if (!newArrayExpr.length.type.equal(BaseType.ERROR)
				&& !newArrayExpr.length.type.equal(BaseType.INT)) {
			issueError(new BadNewArrayLength(newArrayExpr.length.getLocation()));
		}
	}

	@Override
	public void visit(NewObjExpr newObjExpr) {
		Class c = table.lookupClass(newObjExpr.className);
		newObjExpr.symbol = c;
		if (c == null) {
			issueError(new ClassNotFoundError(newObjExpr.getLocation(),
					newObjExpr.className));
			newObjExpr.type = BaseType.ERROR;
		} else {
			newObjExpr.type = c.getType();
		}
	}

	@Override
	public void visit(ThisExpr thisExpr) {
		if (currentFunction.isStatik()) {
			issueError(new ThisInStaticFuncError(thisExpr.getLocation()));
			thisExpr.type = BaseType.ERROR;
		} else {
			thisExpr.type = ((ClassScope) table.lookForScope(Scope.Kind.CLASS))
					.getOwner().getType();
		}
	}

	@Override
	public void visit(VarRef varRef) {
		if (varRef.owner == null) {
			Symbol v = table.lookupBeforeLocation(varRef.name, varRef
					.getLocation());
			if (v == null) {
				issueError(new UndeclVarError(varRef.getLocation(), varRef.name));
				varRef.type = BaseType.ERROR;
			} else if (v.isVariable()) {
				Variable var = (Variable) v;
				varRef.type = var.getType();
				varRef.symbol = var;
				if (var.isLocalVar()) {
					varRef.lvKind = LValue.Kind.LOCAL_VAR;
				} else if (var.isParam()) {
					varRef.lvKind = LValue.Kind.PARAM_VAR;
				} else {
					if (currentFunction.isStatik()) {
						issueError(new RefNonStaticError(varRef.getLocation(),
								currentFunction.getName(), varRef.name));
					} else {
						varRef.owner = new ThisExpr(varRef.getLocation());
						varRef.owner.accept(this);
					}
					varRef.lvKind = LValue.Kind.MEMBER_VAR;
				}
			} else {
				varRef.type = v.getType();
				if (v.isClass()) {
					if (varRef.usedForRef) {
						varRef.isClass = true;
					} else {
						issueError(new UndeclVarError(varRef.getLocation(),
								varRef.name));
						varRef.type = BaseType.ERROR;
					}

				}
			}
		} else {
			varRef.owner.usedForRef = true;
			varRef.owner.accept(this);
			if (!varRef.owner.type.equal(BaseType.ERROR)) {
				if (varRef.owner.isClass || !varRef.owner.type.isClassType()) {
					issueError(new NotClassFieldError(varRef.getLocation(),
							varRef.name, varRef.owner.type.toString()));
					varRef.type = BaseType.ERROR;
				} else {
					ClassScope cs = ((decaf.type.ClassType) varRef.owner.type)
							.getClassScope();
					Symbol v = cs.lookupVisible(varRef.name);
					if (v == null) {
						issueError(new FieldNotFoundError(varRef.getLocation(),
								varRef.name, varRef.owner.type.toString()));
						varRef.type = BaseType.ERROR;
					} else if (v.isVariable()) {
						decaf.type.ClassType thisType = ((ClassScope) table
								.lookForScope(Scope.Kind.CLASS)).getOwner()
								.getType();
						varRef.type = v.getType();
						if (!thisType.compatible(varRef.owner.type)) {
							issueError(new FieldNotAccessError(varRef
									.getLocation(), varRef.name,
									varRef.owner.type.toString()));
						} else {
							varRef.symbol = (Variable) v;
							varRef.lvKind = LValue.Kind.MEMBER_VAR;
						}
					} else {
						varRef.type = v.getType();
					}
				}
			} else {
				varRef.type = BaseType.ERROR;
			}
		}
	}

	@Override
	public void visit(ClassDefn classDefn) {
		table.open(classDefn.symbol.getAssociatedScope());
		for (Field f : classDefn.fields) {
			f.accept(this);
		}
		table.close();
	}

	@Override
	public void visit(FuncDefn funcDefn) {
		this.currentFunction = funcDefn.symbol;
		table.open(funcDefn.symbol.getAssociatedScope());
		funcDefn.body.accept(this);
		table.close();
	}

	@Override
	public void visit(Program program) {
		table.open(program.globalScope);
		for (ClassDefn cd : program.classes) {
			cd.accept(this);
		}
		table.close();
	}

	@Override
	public void visit(StmtBlock stmtBlock) {
		table.open(stmtBlock.associatedScope);
		for (Statement s : stmtBlock.block) {
			s.accept(this);
		}
		table.close();
	}

	@Override
	public void visit(AssignStmt assignStmt) {
		assignStmt.left.accept(this);
		assignStmt.expr.accept(this);
		if (!assignStmt.left.type.equal(BaseType.ERROR)
				&& (assignStmt.left.type.isFuncType() || !assignStmt.expr.type
						.compatible(assignStmt.left.type))) {
			issueError(new IncompatBinOpError(assignStmt.getLocation(),
					assignStmt.left.type.toString(), "=", assignStmt.expr.type
							.toString()));
		}
	}

	@Override
	public void visit(BreakStmt breakStmt) {
		if (breaks.empty()) {
			issueError(new BreakOutOfLoopError(breakStmt.getLocation()));
		}
	}

	@Override
	public void visit(ExprStmt exprStmt) {
		exprStmt.expr.accept(this);
	}

	@Override
	public void visit(ForStmt forStmt) {
		if (forStmt.init != null) {
			forStmt.init.accept(this);
		}
		checkTestExpr(forStmt.condition);
		if (forStmt.update != null) {
			forStmt.update.accept(this);
		}
		breaks.add(forStmt);
		if (forStmt.loopBody != null) {
			forStmt.loopBody.accept(this);
		}
		breaks.pop();
	}

	@Override
	public void visit(IfStmt ifStmt) {
		checkTestExpr(ifStmt.condition);
		if (ifStmt.trueBranch != null) {
			ifStmt.trueBranch.accept(this);
		}
		if (ifStmt.falseBranch != null) {
			ifStmt.falseBranch.accept(this);
		}
	}

	@Override
	public void visit(PrintStmt printStmt) {
		int i = 0;
		for (Expr e : printStmt.exprs) {
			e.accept(this);
			i++;
			if (!e.type.equal(BaseType.ERROR) && !e.type.equal(BaseType.BOOL)
					&& !e.type.equal(BaseType.INT)
					&& !e.type.equal(BaseType.STRING)) {
				issueError(new BadPrintArgError(e.getLocation(), Integer
						.toString(i), e.type.toString()));
			}
		}
	}

	@Override
	public void visit(ReturnStmt returnStmt) {
		decaf.type.Type returnType = ((FormalScope) table
				.lookForScope(Scope.Kind.FORMAL)).getOwner().getReturnType();
		if (returnStmt.expr != null) {
			returnStmt.expr.accept(this);
		}
		if (returnType.equal(BaseType.VOID)) {
			if (returnStmt.expr != null) {
				issueError(new BadReturnTypeError(returnStmt.getLocation(),
						returnType.toString(), returnStmt.expr.type.toString()));
			}
		} else if (returnStmt.expr == null) {
			issueError(new BadReturnTypeError(returnStmt.getLocation(),
					returnType.toString(), "void"));
		} else if (!returnStmt.expr.type.equal(BaseType.ERROR)
				&& !returnStmt.expr.type.compatible(returnType)) {
			issueError(new BadReturnTypeError(returnStmt.getLocation(),
					returnType.toString(), returnStmt.expr.type.toString()));
		}
	}

	@Override
	public void visit(WhileStmt whileStmt) {
		checkTestExpr(whileStmt.condition);
		breaks.add(whileStmt);
		if (whileStmt.loopBody != null) {
			whileStmt.loopBody.accept(this);
		}
		breaks.pop();
	}

	// visiting types
	@Override
	public void visit(IntType intType) {
		intType.type = BaseType.INT;
	}

	@Override
	public void visit(BoolType boolType) {
		boolType.type = BaseType.BOOL;
	}

	@Override
	public void visit(DoubleType doubleType) {
		doubleType.type = BaseType.DOUBLE;
	}

	@Override
	public void visit(StringType stringType) {
		stringType.type = BaseType.STRING;
	}

	@Override
	public void visit(VoidType voidType) {
		voidType.type = BaseType.VOID;
	}

	@Override
	public void visit(ClassType classType) {
		Class c = table.lookupClass(classType.name);
		if (c == null) {
			issueError(new ClassNotFoundError(classType.getLocation(),
					classType.name));
			classType.type = BaseType.ERROR;
		} else {
			classType.type = c.getType();
		}
	}

	@Override
	public void visit(ArrayType arrayType) {
		arrayType.elementType.accept(this);
		if (arrayType.elementType.type.equal(BaseType.ERROR)) {
			arrayType.type = BaseType.ERROR;
		} else if (arrayType.elementType.type.equal(BaseType.VOID)) {
			issueError(new BadArrElementError(arrayType.getLocation()));
			arrayType.type = BaseType.ERROR;
		} else {
			arrayType.type = new decaf.type.ArrayType(
					arrayType.elementType.type);
		}
	}

	private void issueError(DecafError error) {
		Driver.getDriver().issueError(error);
	}

	private Type checkBinaryOp(Expr left, Expr right, int op, Location location) {
		left.accept(this);
		right.accept(this);

		if (left.type.equal(BaseType.ERROR) || right.type.equal(BaseType.ERROR)) {
			switch (op) {
			case '+':
			case '-':
			case '*':
			case '/':
				return left.type;
			case '%':
				return BaseType.INT;
			default:
				return BaseType.BOOL;
			}
		}

		boolean compatible = false;
		Type returnType = BaseType.ERROR;
		switch (op) {
		case '+':
		case '-':
		case '*':
		case '/':
			compatible = (left.type.equal(BaseType.INT) || left.type
					.equal(BaseType.DOUBLE))
					&& left.type.equal(right.type);
			returnType = left.type;
			break;
		case '>':
		case Parser.GREATER_EQUAL:
		case '<':
		case Parser.LESS_EQUAL:
			compatible = (left.type.equal(BaseType.INT) || left.type
					.equal(BaseType.DOUBLE))
					&& left.type.equal(right.type);
			returnType = BaseType.BOOL;
			break;
		case '%':
			compatible = left.type.equal(BaseType.INT)
					&& right.type.equal(BaseType.INT);
			returnType = BaseType.INT;
			break;
		case Parser.EQUAL:
		case Parser.NOT_EQUAL:
			compatible = left.type.compatible(right.type)
					|| right.type.compatible(left.type);
			returnType = BaseType.BOOL;
			break;
		case Parser.AND:
		case Parser.OR:
			compatible = left.type.equal(BaseType.BOOL)
					&& right.type.equal(BaseType.BOOL);
			returnType = BaseType.BOOL;
			break;
		default:
			break;
		}

		if (!compatible) {
			issueError(new IncompatBinOpError(location, left.type.toString(),
					Parser.opStr(op), right.type.toString()));
		}
		return returnType;
	}

	private void checkTestExpr(Expr expr) {
		expr.accept(this);
		if (!expr.type.equal(BaseType.ERROR) && !expr.type.equal(BaseType.BOOL)) {
			issueError(new BadTestExpr(expr.getLocation()));
		}
	}
}
