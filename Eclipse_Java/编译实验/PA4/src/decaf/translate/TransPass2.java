package decaf.translate;

import java.util.Stack;

import decaf.ast.AddExpr;
import decaf.ast.AndExpr;
import decaf.ast.ArrayRef;
import decaf.ast.AssignStmt;
import decaf.ast.BoolConst;
import decaf.ast.BreakStmt;
import decaf.ast.CallExpr;
import decaf.ast.ClassDefn;
import decaf.ast.DivExpr;
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
import decaf.ast.SubExpr;
import decaf.ast.ThisExpr;
import decaf.ast.VarDecl;
import decaf.ast.VarDeclStmt;
import decaf.ast.VarRef;
import decaf.ast.WhileStmt;
import decaf.backend.OffsetCounter;
import decaf.machdesc.Intrinsic;
import decaf.symbol.Variable;
import decaf.tac.Label;
import decaf.tac.Temp;
import decaf.type.BaseType;

public class TransPass2 extends NoActionASTVisitor {

	private Translater tr;

	private Temp currentThis;

	private Stack<Label> loopExits;

	public TransPass2(Translater tr) {
		this.tr = tr;
		loopExits = new Stack<Label>();
	}

	@Override
	public void visit(ClassDefn classDefn) {
		for (Field f : classDefn.fields) {
			f.accept(this);
		}
	}

	@Override
	public void visit(FuncDefn funcDefn) {
		if (!funcDefn.statik) {
			currentThis = ((Variable) funcDefn.symbol.getAssociatedScope()
					.lookup("this")).getTemp();
		}
		tr.beginFunc(funcDefn.symbol);
		funcDefn.body.accept(this);
		tr.endFunc();
		currentThis = null;
	}

	@Override
	public void visit(Program program) {
		for (ClassDefn cd : program.classes) {
			cd.accept(this);
		}
	}

	@Override
	public void visit(VarDecl varDecl) {
		if (varDecl.symbol.isLocalVar()) {
			Temp t = Temp.createTempI4();
			t.sym = varDecl.symbol;
			varDecl.symbol.setTemp(t);
		}
	}

	@Override
	public void visit(AddExpr addExpr) {
		addExpr.left.accept(this);
		addExpr.right.accept(this);
		addExpr.val = tr.genAdd(addExpr.left.val, addExpr.right.val);
	}

	@Override
	public void visit(DivExpr divExpr) {
		divExpr.left.accept(this);
		divExpr.right.accept(this);
		divExpr.val = tr.genDiv(divExpr.left.val, divExpr.right.val);
	}

	@Override
	public void visit(MulExpr mulExpr) {
		mulExpr.left.accept(this);
		mulExpr.right.accept(this);
		mulExpr.val = tr.genMul(mulExpr.left.val, mulExpr.right.val);
	}

	@Override
	public void visit(SubExpr subExpr) {
		subExpr.left.accept(this);
		subExpr.right.accept(this);
		subExpr.val = tr.genSub(subExpr.left.val, subExpr.right.val);
	}

	@Override
	public void visit(AndExpr andExpr) {
		andExpr.left.accept(this);
		andExpr.right.accept(this);
		andExpr.val = tr.genLAnd(andExpr.left.val, andExpr.right.val);
	}

	@Override
	public void visit(AssignStmt assignStmt) {
		assignStmt.left.accept(this);
		assignStmt.expr.accept(this);
		switch (assignStmt.left.lvKind) {
		case ARRAY_ELEMENT:
			ArrayRef arrayRef = (ArrayRef) assignStmt.left;
			Temp esz = tr.genLoadImm4(OffsetCounter.WORD_SIZE);
			Temp t = tr.genMul(arrayRef.index.val, esz);
			Temp base = tr.genAdd(arrayRef.array.val, t);
			tr.genStore(assignStmt.expr.val, base, 0);
			break;
		case MEMBER_VAR:
			VarRef varRef = (VarRef) assignStmt.left;
			tr.genStore(assignStmt.expr.val, varRef.owner.val, varRef.symbol
					.getOffset());
			break;
		case PARAM_VAR:
		case LOCAL_VAR:
			tr.genAssign(((VarRef) assignStmt.left).symbol.getTemp(),
					assignStmt.expr.val);
			break;
		}
	}

	@Override
	public void visit(BoolConst boolConst) {
		boolConst.val = tr.genLoadImm4(boolConst.constVal ? 1 : 0);
	}

	@Override
	public void visit(EquExpr equExpr) {
		equExpr.left.accept(this);
		equExpr.right.accept(this);
		if (equExpr.left.type.equal(BaseType.STRING)
				|| equExpr.right.type.equal(BaseType.STRING)) {
			tr.genParm(equExpr.left.val);
			tr.genParm(equExpr.right.val);
			equExpr.val = tr.genDirectCall(Intrinsic.STRING_EQUAL.label,
					BaseType.BOOL);
		} else {
			equExpr.val = tr.genEqu(equExpr.left.val, equExpr.right.val);
		}
	}

	@Override
	public void visit(ExprStmt exprStmt) {
		exprStmt.expr.accept(this);
	}

	@Override
	public void visit(IntConst intConst) {
		intConst.val = tr.genLoadImm4(intConst.constVal);
	}

	@Override
	public void visit(LeqExpr leqExpr) {
		leqExpr.left.accept(this);
		leqExpr.right.accept(this);
		leqExpr.val = tr.genLeq(leqExpr.left.val, leqExpr.right.val);
	}

	@Override
	public void visit(LesExpr lesExpr) {
		lesExpr.left.accept(this);
		lesExpr.right.accept(this);
		lesExpr.val = tr.genLes(lesExpr.left.val, lesExpr.right.val);
	}

	@Override
	public void visit(ModExpr modExpr) {
		modExpr.left.accept(this);
		modExpr.right.accept(this);
		modExpr.val = tr.genMod(modExpr.left.val, modExpr.right.val);
	}

	@Override
	public void visit(NegExpr negExpr) {
		negExpr.expr.accept(this);
		negExpr.val = tr.genNeg(negExpr.expr.val);
	}

	@Override
	public void visit(NeqExpr neqExpr) {
		neqExpr.left.accept(this);
		neqExpr.right.accept(this);
		if (neqExpr.left.type.equal(BaseType.STRING)
				|| neqExpr.right.type.equal(BaseType.STRING)) {
			tr.genParm(neqExpr.left.val);
			tr.genParm(neqExpr.right.val);
			neqExpr.val = tr.genLNot(tr.genDirectCall(
					Intrinsic.STRING_EQUAL.label, BaseType.BOOL));
		} else {
			neqExpr.val = tr.genNeq(neqExpr.left.val, neqExpr.right.val);
		}
	}

	@Override
	public void visit(NotExpr notExpr) {
		notExpr.expr.accept(this);
		notExpr.val = tr.genLNot(notExpr.expr.val);
	}

	@Override
	public void visit(NullExpr nullExpr) {
		nullExpr.val = tr.genLoadImm4(0);
	}

	@Override
	public void visit(OrExpr orExpr) {
		orExpr.left.accept(this);
		orExpr.right.accept(this);
		orExpr.val = tr.genLOr(orExpr.left.val, orExpr.right.val);
	}

	@Override
	public void visit(StmtBlock stmtBlock) {
		for (Statement s : stmtBlock.block) {
			s.accept(this);
		}
	}

	@Override
	public void visit(StringConst stringConst) {
		stringConst.val = tr.genLoadStrConst(stringConst.constVal);
	}

	@Override
	public void visit(ThisExpr thisExpr) {
		thisExpr.val = currentThis;
	}

	@Override
	public void visit(VarDeclStmt varDeclStmt) {
		varDeclStmt.decl.accept(this);
	}

	@Override
	public void visit(GeqExpr geqExpr) {
		geqExpr.left.accept(this);
		geqExpr.right.accept(this);
		geqExpr.val = tr.genGeq(geqExpr.left.val, geqExpr.right.val);
	}

	@Override
	public void visit(GtrExpr gtrExpr) {
		gtrExpr.left.accept(this);
		gtrExpr.right.accept(this);
		gtrExpr.val = tr.genGtr(gtrExpr.left.val, gtrExpr.right.val);
	}

	@Override
	public void visit(LValueExpr valueExpr) {
		valueExpr.lv.accept(this);
		switch (valueExpr.lv.lvKind) {
		case ARRAY_ELEMENT:
			ArrayRef arrayRef = (ArrayRef) valueExpr.lv;
			Temp esz = tr.genLoadImm4(OffsetCounter.WORD_SIZE);
			Temp t = tr.genMul(arrayRef.index.val, esz);
			Temp base = tr.genAdd(arrayRef.array.val, t);
			valueExpr.val = tr.genLoad(base, 0);
			break;
		case MEMBER_VAR:
			VarRef varRef = (VarRef) valueExpr.lv;
			valueExpr.val = tr.genLoad(varRef.owner.val, varRef.symbol
					.getOffset());
			break;
		case PARAM_VAR:
		case LOCAL_VAR:
			valueExpr.val = ((VarRef) valueExpr.lv).symbol.getTemp();
			break;
		}
	}

	@Override
	public void visit(ReadIntExpr readIntExpr) {
		readIntExpr.val = tr.genIntrinsicCall(Intrinsic.READ_INT);
	}

	@Override
	public void visit(ReadLineExpr readStringExpr) {
		readStringExpr.val = tr.genIntrinsicCall(Intrinsic.READ_LINE);
	}

	@Override
	public void visit(ReturnStmt returnStmt) {
		if (returnStmt.expr != null) {
			returnStmt.expr.accept(this);
			tr.genReturn(returnStmt.expr.val);
		} else {
			tr.genReturn(null);
		}

	}

	@Override
	public void visit(PrintStmt printStmt) {
		for (Expr r : printStmt.exprs) {
			r.accept(this);
			tr.genParm(r.val);
			if (r.type.equal(BaseType.BOOL)) {
				tr.genIntrinsicCall(Intrinsic.PRINT_BOOL);
			} else if (r.type.equal(BaseType.INT)) {
				tr.genIntrinsicCall(Intrinsic.PRINT_INT);
			} else if (r.type.equal(BaseType.STRING)) {
				tr.genIntrinsicCall(Intrinsic.PRINT_STRING);
			}
		}
	}

	@Override
	public void visit(ArrayRef arrayRef) {
		arrayRef.array.accept(this);
		arrayRef.index.accept(this);
		tr.genCheckArrayIndex(arrayRef.array.val, arrayRef.index.val);
	}

	@Override
	public void visit(BreakStmt breakStmt) {
		tr.genJump(loopExits.peek());
	}

	@Override
	public void visit(CallExpr callExpr) {
		if (callExpr.isArrayLength) {
			callExpr.receiver.accept(this);
			callExpr.val = tr.genLoad(callExpr.receiver.val,
					-OffsetCounter.WORD_SIZE);
		} else {
			if (callExpr.receiver != null) {
				callExpr.receiver.accept(this);
			}
			for (Expr expr : callExpr.actuals) {
				expr.accept(this);
			}
			if (callExpr.receiver != null) {
				tr.genParm(callExpr.receiver.val);
			}
			for (Expr expr : callExpr.actuals) {
				tr.genParm(expr.val);
			}
			if (callExpr.receiver == null) {
				callExpr.val = tr.genDirectCall(
						callExpr.symbol.getFuncty().label, callExpr.symbol
								.getReturnType());
			} else {
				Temp vt = tr.genLoad(callExpr.receiver.val, 0);
				Temp func = tr.genLoad(vt, callExpr.symbol.getOffset());
				callExpr.val = tr.genIndirectCall(func, callExpr.symbol
						.getReturnType());
			}
		}

	}

	@Override
	public void visit(ForStmt forStmt) {
		if (forStmt.init != null) {
			forStmt.init.accept(this);
		}
		Label cond = Label.createLabel();
		Label loop = Label.createLabel();
		tr.genJump(cond);
		tr.genMark(loop);
		if (forStmt.update != null) {
			forStmt.update.accept(this);
		}
		tr.genMark(cond);
		forStmt.condition.accept(this);
		Label exit = Label.createLabel();
		tr.genJZero(forStmt.condition.val, exit);
		loopExits.push(exit);
		if (forStmt.loopBody != null) {
			forStmt.loopBody.accept(this);
		}
		tr.genJump(loop);
		loopExits.pop();
		tr.genMark(exit);
	}

	@Override
	public void visit(IfStmt ifStmt) {
		ifStmt.condition.accept(this);
		if (ifStmt.falseBranch != null) {
			Label falseLabel = Label.createLabel();
			tr.genJZero(ifStmt.condition.val, falseLabel);
			ifStmt.trueBranch.accept(this);
			Label exit = Label.createLabel();
			tr.genJump(exit);
			tr.genMark(falseLabel);
			ifStmt.falseBranch.accept(this);
			tr.genMark(exit);
		} else if (ifStmt.trueBranch != null) {
			Label exit = Label.createLabel();
			tr.genJZero(ifStmt.condition.val, exit);
			if (ifStmt.trueBranch != null) {
				ifStmt.trueBranch.accept(this);
			}
			tr.genMark(exit);
		}
	}

	@Override
	public void visit(NewArrayExpr newArrayExpr) {
		newArrayExpr.length.accept(this);
		newArrayExpr.val = tr.genNewArray(newArrayExpr.length.val);
	}

	@Override
	public void visit(NewObjExpr newObjExpr) {
		newObjExpr.val = tr.genDirectCall(newObjExpr.symbol.getNewFuncLabel(),
				BaseType.INT);
	}

	@Override
	public void visit(VarRef varRef) {
		if (varRef.lvKind == LValue.Kind.MEMBER_VAR) {
			varRef.owner.accept(this);
		}
	}

	@Override
	public void visit(WhileStmt whileStmt) {
		Label loop = Label.createLabel();
		tr.genMark(loop);
		whileStmt.condition.accept(this);
		Label exit = Label.createLabel();
		tr.genJZero(whileStmt.condition.val, exit);
		loopExits.push(exit);
		if (whileStmt.loopBody != null) {
			whileStmt.loopBody.accept(this);
		}
		tr.genJump(loop);
		loopExits.pop();
		tr.genMark(exit);
	}
}