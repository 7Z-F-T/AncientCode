package decaf.typecheck;

import java.util.Iterator;

import decaf.Driver;
import decaf.ast.ArrayType;
import decaf.ast.BoolType;
import decaf.ast.ClassDefn;
import decaf.ast.ClassType;
import decaf.ast.DoubleType;
import decaf.ast.Field;
import decaf.ast.ForStmt;
import decaf.ast.FuncDefn;
import decaf.ast.IfStmt;
import decaf.ast.IntType;
import decaf.ast.Program;
import decaf.ast.Statement;
import decaf.ast.StmtBlock;
import decaf.ast.StringType;
import decaf.ast.NoActionASTVisitor;
import decaf.ast.VarDecl;
import decaf.ast.VarDeclStmt;
import decaf.ast.VoidType;
import decaf.ast.WhileStmt;
import decaf.error.BadArrElementError;
import decaf.error.BadInheritanceError;
import decaf.error.BadOverrideError;
import decaf.error.BadVarTypeError;
import decaf.error.ClassNotFoundError;
import decaf.error.DecafError;
import decaf.error.DeclConflictError;
import decaf.error.NoMainClassError;
import decaf.error.OverridingVarError;
import decaf.scope.ClassScope;
import decaf.scope.GlobalScope;
import decaf.scope.LocalScope;
import decaf.scope.ScopeStack;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.symbol.Variable;
import decaf.type.BaseType;
import decaf.type.FuncType;

public class BuildSym extends NoActionASTVisitor {

	private ScopeStack table;

	private void issueError(DecafError error) {
		Driver.getDriver().issueError(error);
	}

	public BuildSym(ScopeStack table) {
		this.table = table;
	}

	public static void buildSymbol(Program tree) {
		new BuildSym(Driver.getDriver().getTable()).visit(tree);
	}

	// root
	@Override
	public void visit(Program program) {
		program.globalScope = new GlobalScope();
		table.open(program.globalScope);
		for (ClassDefn cd : program.classes) {
			Class c = new Class(cd.name, cd.parent, cd.getLocation());
			Class earlier = table.lookupClass(cd.name);
			if (earlier != null) {
				issueError(new DeclConflictError(cd.getLocation(), cd.name,
						earlier.getLocation()));
			} else {
				table.declare(c);
			}
			cd.symbol = c;
		}

		for (ClassDefn cd : program.classes) {
			Class c = cd.symbol;
			if (cd.parent != null && c.getParent() == null) {
				issueError(new ClassNotFoundError(cd.getLocation(), cd.parent));
				c.dettachParent();
			}
			if (calcOrder(c) <= calcOrder(c.getParent())) {
				issueError(new BadInheritanceError(cd.getLocation()));
				c.dettachParent();
			}
		}

		for (ClassDefn cd : program.classes) {
			cd.symbol.createType();
		}

		for (ClassDefn cd : program.classes) {
			cd.accept(this);
			if (Driver.getDriver().getOption().getMainClassName().equals(
					cd.name)) {
				program.main = cd.symbol;
			}
		}

		for (ClassDefn cd : program.classes) {
			checkOverride(cd.symbol);
		}

		if (!isMainClass(program.main)) {
			issueError(new NoMainClassError(Driver.getDriver().getOption()
					.getMainClassName()));
		}
		table.close();
	}

	// visiting declarations
	@Override
	public void visit(ClassDefn classDefn) {
		table.open(classDefn.symbol.getAssociatedScope());
		for (Field f : classDefn.fields) {
			f.accept(this);
		}
		table.close();
	}

	@Override
	public void visit(VarDecl varDecl) {
		varDecl.type.accept(this);
		if (varDecl.type.type.equal(BaseType.VOID)) {
			issueError(new BadVarTypeError(varDecl.getLocation(), varDecl.name));
			// for argList
			varDecl.symbol = new Variable(".error", BaseType.ERROR, varDecl
					.getLocation());
			return;
		}
		Variable v = new Variable(varDecl.name, varDecl.type.type, varDecl
				.getLocation());
		Symbol sym = table.lookup(varDecl.name, true);
		if (sym != null) {
			if (table.getCurrentScope().equals(sym.getScope())) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
			} else if ((sym.getScope().isFormalScope() || sym.getScope()
					.isLocalScope())) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
			} else {
				table.declare(v);
			}
		} else {
			table.declare(v);
		}
		varDecl.symbol = v;
	}

	@Override
	public void visit(FuncDefn funcDefn) {
		funcDefn.returnType.accept(this);
		Function f = new Function(funcDefn.statik, funcDefn.name,
				funcDefn.returnType.type, funcDefn.body, funcDefn.getLocation());
		funcDefn.symbol = f;
		Symbol sym = table.lookup(funcDefn.name, false);
		if (sym != null) {
			issueError(new DeclConflictError(funcDefn.getLocation(),
					funcDefn.name, sym.getLocation()));
		} else {
			table.declare(f);
		}
		table.open(f.getAssociatedScope());
		for (VarDecl d : funcDefn.formals) {
			d.accept(this);
			f.appendParam(d.symbol);
		}
		funcDefn.body.accept(this);
		table.close();
	}

	@Override
	public void visit(VarDeclStmt varDeclStmt) {
		varDeclStmt.decl.accept(this);
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

	// for VarDecl in LocalScope
	@Override
	public void visit(StmtBlock stmtBlock) {
		stmtBlock.associatedScope = new LocalScope(stmtBlock);
		table.open(stmtBlock.associatedScope);
		for (Statement s : stmtBlock.block) {
			s.accept(this);
		}
		table.close();
	}

	@Override
	public void visit(ForStmt forStmt) {
		if (forStmt.loopBody != null) {
			forStmt.loopBody.accept(this);
		}
	}


	@Override
	public void visit(IfStmt ifStmt) {
		if (ifStmt.trueBranch != null) {
			ifStmt.trueBranch.accept(this);
		}
		if (ifStmt.falseBranch != null) {
			ifStmt.falseBranch.accept(this);
		}
	}

	@Override
	public void visit(WhileStmt whileStmt) {
		if (whileStmt.loopBody != null) {
			whileStmt.loopBody.accept(this);
		}
	}

	private int calcOrder(Class c) {
		if (c == null) {
			return -1;
		}
		if (c.getOrder() < 0) {
			c.setOrder(0);
			c.setOrder(calcOrder(c.getParent()) + 1);
		}
		return c.getOrder();
	}

	private void checkOverride(Class c) {
		if (c.isCheck()) {
			return;
		}
		Class parent = c.getParent();
		if (parent == null) {
			return;
		}
		checkOverride(parent);

		ClassScope parentScope = parent.getAssociatedScope();
		ClassScope subScope = c.getAssociatedScope();
		table.open(parentScope);
		Iterator<Symbol> iter = subScope.iterator();
		while (iter.hasNext()) {
			Symbol suspect = iter.next();
			Symbol sym = table.lookup(suspect.getName(), true);
			if (sym != null && !sym.isClass()) {
				if ((suspect.isVariable() && sym.isFunction())
						|| (suspect.isFunction() && sym.isVariable())) {
					issueError(new DeclConflictError(suspect.getLocation(),
							suspect.getName(), sym.getLocation()));
					iter.remove();
				} else if (suspect.isFunction()) {
					if (((Function) suspect).isStatik()
							|| ((Function) sym).isStatik()) {
						issueError(new DeclConflictError(suspect.getLocation(),
								suspect.getName(), sym.getLocation()));
						iter.remove();
					} else if (!suspect.getType().compatible(sym.getType())) {
						issueError(new BadOverrideError(suspect.getLocation(),
								suspect.getName(),
								((ClassScope) sym.getScope()).getOwner()
										.getName()));
						iter.remove();
					}
				} else if (suspect.isVariable()) {
					issueError(new OverridingVarError(suspect.getLocation(),
							suspect.getName()));
					iter.remove();
				}
			}
		}
		table.close();
		c.setCheck(true);
	}

	private boolean isMainClass(Class c) {
		if (c == null) {
			return false;
		}
		table.open(c.getAssociatedScope());
		Symbol main = table.lookup(Driver.getDriver().getOption()
				.getMainFuncName(), false);
		if (main == null || !main.isFunction()) {
			return false;
		}
		((Function) main).setMain(true);
		FuncType type = (FuncType) main.getType();
		return type.getReturnType().equal(BaseType.VOID)
				&& type.numOfParams() == 0 && ((Function) main).isStatik();
	}
}
