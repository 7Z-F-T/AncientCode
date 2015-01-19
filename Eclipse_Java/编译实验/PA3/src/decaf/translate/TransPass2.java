package decaf.translate;

import decaf.symbol.*;
import java.util.*;
import decaf.ast.*;
import decaf.backend.OffsetCounter;
import decaf.error.BadPrintArgError;
import decaf.machdesc.Intrinsic;
import decaf.scope.ClassScope;
import decaf.scope.Scope;
import decaf.symbol.Class;
import decaf.tac.Label;
import decaf.tac.Temp;
import decaf.type.BaseType;

public class TransPass2 extends NoActionASTVisitor {

	private Translater tr;
	
	private Temp currentObject;
	private Temp tempTemp;
	
	private Function currentFunction;
	
	private ArrayList<Label> exitLabels;
	private int loopLayer;

	public TransPass2(Translater tr) {
		this.tr = tr;
		tempTemp=Temp.createTempI4();
		currentObject=tempTemp;
		exitLabels=new ArrayList<Label>();
		exitLabels.add(new Label());
		loopLayer=0;
	}

	@Override
	public void visit(Program program) {
		for (ClassDefn cd : program.classes) {
			cd.accept(this);
		}
	}

	@Override
	public void visit(ClassDefn classDefn) {
		for (Field f : classDefn.fields) {
			f.accept(this);
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
	
	public void visit(FuncDefn funcDefn){
		tr.beginFunc(funcDefn.symbol);
		currentFunction=funcDefn.symbol;
		funcDefn.body.accept(this);
		currentFunction=null;
		tr.endFunc();
	}

	@Override
	
	public void visit(ThisExpr thisExpr){
		if(currentFunction!=null){
			Variable v=(Variable)currentFunction.getAssociatedScope().lookup("this");
			thisExpr.val=v.getTemp();
		}
	}
	
	public void visit(LValueExpr lvExpr){
		lvExpr.lv.accept(this);
		if(lvExpr.lv instanceof VarRef)
			lvExpr.val=((VarRef)lvExpr.lv).symbol.getTemp();
		if(lvExpr.lv instanceof ArrayRef){
			ArrayRef arrayRef = (ArrayRef)lvExpr.lv;
			Temp esz = tr.genLoadImm4(OffsetCounter.WORD_SIZE);
			Temp t = tr.genMul(arrayRef.index.val, esz);
			Temp base = tr.genAdd(arrayRef.array.val, t);
			lvExpr.val=tr.genLoad(base, 0);
		}
	}
	
	public void visit(CallExpr callExpr){
		if(callExpr.receiver!=null)
			callExpr.receiver.accept(this);
		if(callExpr.method.equals("length")){
			callExpr.val=tr.genLoad(callExpr.receiver.val, -4);
			return;
		}
		if(callExpr.symbol.isStatik()==true){//(对象或类名).静态函数
			Label funcLabel=callExpr.symbol.getFuncty().label;
			for(Expr actual:callExpr.actuals){
				actual.accept(this);
			}
			for(Expr actual:callExpr.actuals){
				tr.genParm(actual.val);
			}
			callExpr.val=tr.genDirectCall(funcLabel, callExpr.symbol.getReturnType());
		}
		if(callExpr.symbol.isStatik()==false){//对象.非静态函数
			Temp temp1,temp2;
			temp1=tr.genLoad(callExpr.receiver.val, 0);
			temp2=tr.genLoad(temp1, callExpr.symbol.getOffset());
			for(Expr actual:callExpr.actuals){
				actual.accept(this);
			}
			tr.genParm(callExpr.receiver.val);
			for(Expr actual:callExpr.actuals){
				tr.genParm(actual.val);
			}
			callExpr.val=tr.genIndirectCall(temp2, callExpr.symbol.getReturnType());
		}
	}
	
	public void visit(AddExpr addExpr) {
		addExpr.left.accept(this);
		addExpr.right.accept(this);
		addExpr.val = tr.genAdd(addExpr.left.val, addExpr.right.val);
	}
	
	public void visit(SubExpr subExpr) {
		subExpr.left.accept(this);
		subExpr.right.accept(this);
		subExpr.val = tr.genSub(subExpr.left.val, subExpr.right.val);
	}
	
	public void visit(MulExpr mulExpr) {
		mulExpr.left.accept(this);
		mulExpr.right.accept(this);
		mulExpr.val = tr.genMul(mulExpr.left.val, mulExpr.right.val);
	}
	
	
	public void visit(DivExpr divExpr) {
		divExpr.left.accept(this);
		divExpr.right.accept(this);
		divExpr.val = tr.genDiv(divExpr.left.val, divExpr.right.val);
	}
	
	public void visit(AndExpr andExpr) {
		andExpr.left.accept(this);
		andExpr.right.accept(this);
		andExpr.val = tr.genLAnd(andExpr.left.val, andExpr.right.val);
	}
	
	public void visit(OrExpr OrExpr) {
		OrExpr.left.accept(this);
		OrExpr.right.accept(this);
		OrExpr.val = tr.genLOr(OrExpr.left.val, OrExpr.right.val);
	}
	
	public void visit(GtrExpr gtrExpr) {
		gtrExpr.left.accept(this);
		gtrExpr.right.accept(this);
		gtrExpr.val = tr.genGtr(gtrExpr.left.val, gtrExpr.right.val);
	}
	
	public void visit(GeqExpr geqExpr) {
		geqExpr.left.accept(this);
		geqExpr.right.accept(this);
		geqExpr.val = tr.genGeq(geqExpr.left.val, geqExpr.right.val);
	}
	
	public void visit(LesExpr lesExpr) {
		lesExpr.left.accept(this);
		lesExpr.right.accept(this);
		lesExpr.val = tr.genLes(lesExpr.left.val, lesExpr.right.val);
	}
	
	public void visit(LeqExpr leqExpr) {
		leqExpr.left.accept(this);
		leqExpr.right.accept(this);
		leqExpr.val = tr.genLeq(leqExpr.left.val, leqExpr.right.val);
	}
	
	public void visit(EquExpr equExpr) {
		equExpr.left.accept(this);
		equExpr.right.accept(this);
		equExpr.val = tr.genEqu(equExpr.left.val, equExpr.right.val);
	}
	
	public void visit(NeqExpr neqExpr) {
		neqExpr.left.accept(this);
		neqExpr.right.accept(this);
		neqExpr.val = tr.genNeq(neqExpr.left.val, neqExpr.right.val);
	}
	
	public void visit(ModExpr modExpr) {
		modExpr.left.accept(this);
		modExpr.right.accept(this);
		modExpr.val = tr.genMod(modExpr.left.val, modExpr.right.val);
	}
	
	@Override
	public void visit(NotExpr notExpr) {
		notExpr.expr.accept(this);
		notExpr.val = tr.genLNot(notExpr.expr.val);
	}
	
	public void visit(NegExpr negExpr) {
		negExpr.expr.accept(this);
		negExpr.val = tr.genNeg(negExpr.expr.val);
	}

	@Override
	public void visit(NullExpr nullExpr) {
		nullExpr.val = tr.genLoadImm4(0);
	}

	@Override
	public void visit(StmtBlock stmtBlock) {
		for (Statement s : stmtBlock.block) {
			s.accept(this);
		}
	}

	@Override
	public void visit(ExprStmt exprStmt) {
		exprStmt.expr.accept(this);
	}

	@Override
	public void visit(VarDeclStmt varDeclStmt) {
		varDeclStmt.decl.accept(this);
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

	public void visit(PrintStmt printStmt) {
		for (Expr e : printStmt.exprs) {
			e.accept(this);
		}
		for (Expr e : printStmt.exprs) {
			if(e.type.equal(BaseType.INT)){
				tr.genParm(e.val);
				tr.genIntrinsicCall(Intrinsic.PRINT_INT);
			}
			if(e.type.equal(BaseType.BOOL)){
				tr.genParm(e.val);
				tr.genIntrinsicCall(Intrinsic.PRINT_BOOL);
			}
			if(e.type.equal(BaseType.STRING)){
				tr.genParm(e.val);
				tr.genIntrinsicCall(Intrinsic.PRINT_STRING);
			}	
		}
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
	
	public void visit(WhileStmt whileStmt){
		loopLayer++;
		if(whileStmt.loopBody!=null){
			Label exit=Label.createLabel();
			Label begin=Label.createLabel();
			exitLabels.add(loopLayer,exit);
			tr.genMark(begin);
			whileStmt.condition.accept(this);
			tr.genJZero(whileStmt.condition.val, exit);
			whileStmt.loopBody.accept(this);
			tr.genJump(begin);
			tr.genMark(exit);
		}
		exitLabels.remove(loopLayer);
		loopLayer--;
	}

	public void visit(ForStmt forStmt){
		loopLayer++;
		if(forStmt.init!=null)
			forStmt.init.accept(this);
		if(forStmt.loopBody!=null){
			Label exit=Label.createLabel();
			Label begin=Label.createLabel();
			exitLabels.add(loopLayer,exit);
			tr.genMark(begin);
			if(forStmt.condition!=null)
				forStmt.condition.accept(this);
			tr.genJZero(forStmt.condition.val, exit);
			forStmt.loopBody.accept(this);
			if(forStmt.update!=null)
				forStmt.update.accept(this);
			tr.genJump(begin);
			tr.genMark(exit);
		}
		exitLabels.remove(loopLayer);
		loopLayer--;
	}
	
	public void visit(BreakStmt breakStmt){
		tr.genJump(exitLabels.get(loopLayer));
	}
	public void visit(ReturnStmt rtStmt){
		rtStmt.expr.accept(this);
		tr.genReturn(rtStmt.expr.val);
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
	
	public void visit(VarRef varRef){
		if(varRef.owner!=null){
			varRef.owner.accept(this);
			if(varRef.symbol.isMemberVar()){
				varRef.symbol.setTemp(tr.genLoad(varRef.owner.val, varRef.symbol.getOffset()));
			}
		}
	}
	public void visit(ArrayRef arrayRef) {
		arrayRef.array.accept(this);
		arrayRef.index.accept(this);
		tr.genCheckArrayIndex(arrayRef.array.val, arrayRef.index.val);
	}

	@Override
	public void visit(IntConst intConst) {
		intConst.val = tr.genLoadImm4(intConst.constVal);
	}
	@Override
	public void visit(BoolConst boolConst) {
		boolConst.val = tr.genLoadImm4(boolConst.constVal ? 1 : 0);
	}

	public void visit(StringConst stringConst) {
		stringConst.val = tr.genLoadStrConst(stringConst.constVal);
	}

	@Override
	public void visit(NewArrayExpr newArrayExpr) {
		newArrayExpr.length.accept(this);
		newArrayExpr.val = tr.genNewArray(newArrayExpr.length.val);
	}
	
	public void visit(NewObjExpr noExpr){
		Temp t0=Temp.createTempI4();
		Temp t1=Temp.createTempI4();
		Temp t2=Temp.createTempI4();
		Temp numVar=Temp.createTempI4();
		numVar=Temp.createConstTemp(noExpr.symbol.getNumVar()*4+4);
		tr.genAssign(t0, numVar);
		tr.genParm(t0);
		t2=tr.genIntrinsicCall(Intrinsic.ALLOCATE);
		t1=tr.genLoadVTable(noExpr.symbol.getVtable());
		tr.genStore(t1, t2, 0);
		noExpr.val=t2;
	}
}