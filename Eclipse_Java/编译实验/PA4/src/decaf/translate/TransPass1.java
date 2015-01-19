package decaf.translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import decaf.ast.ClassDefn;
import decaf.ast.Field;
import decaf.ast.FuncDefn;
import decaf.ast.NoActionASTVisitor;
import decaf.ast.Program;
import decaf.ast.VarDecl;
import decaf.backend.OffsetCounter;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.symbol.Variable;
import decaf.tac.Temp;

public class TransPass1 extends NoActionASTVisitor {
	private Translater tr;

	private int objectSize;

	private List<Variable> vars;

	public TransPass1(Translater tr) {
		this.tr = tr;
		vars = new ArrayList<Variable>();
	}

	@Override
	public void visit(Program program) {
		for (ClassDefn cd : program.classes) {
			cd.accept(this);
		}
	}

	@Override
	public void visit(ClassDefn classDefn) {
		classDefn.symbol.resolveFieldOrder();
		objectSize = 0;
		vars.clear();
		for (Field f : classDefn.fields) {
			f.accept(this);
		}
		Collections.sort(vars, Symbol.ORDER_COMPARATOR);
		OffsetCounter oc = OffsetCounter.VARFIELD_OFFSET_COUNTER;
		Class c = classDefn.symbol.getParent();
		if (c != null) {
			oc.set(c.getSize());
		} else {
			oc.reset();
		}
		for (Variable v : vars) {
			v.setOffset(oc.next(OffsetCounter.WORD_SIZE));
		}
		tr.createVTable(classDefn.symbol);
		tr.genNewForClass(classDefn.symbol);
	}

	@Override
	public void visit(FuncDefn funcDefn) {
		Function func = funcDefn.symbol;
		if (!func.isStatik()) {
			func.setOffset(func.getOrder() * OffsetCounter.POINTER_SIZE);
		}
		tr.createFuncty(func);
		OffsetCounter oc = OffsetCounter.PARAMETER_OFFSET_COUNTER;
		oc.reset();
		int order;
		if (!func.isStatik()) {
			Variable v = (Variable) func.getAssociatedScope().lookup("this");
			v.setOrder(0);
			Temp t = Temp.createTempI4();
			t.sym = v;
			t.isParam = true;
			v.setTemp(t);
			v.setOffset(oc.next(OffsetCounter.POINTER_SIZE));
			order = 1;
		} else {
			order = 0;
		}
		for (VarDecl vd : funcDefn.formals) {
			vd.symbol.setOrder(order++);
			Temp t = Temp.createTempI4();
			t.sym = vd.symbol;
			t.isParam = true;
			vd.symbol.setTemp(t);
			vd.symbol.setOffset(oc.next(vd.symbol.getTemp().size));
		}
	}

	@Override
	public void visit(VarDecl varDecl) {
		vars.add(varDecl.symbol);
		objectSize += OffsetCounter.WORD_SIZE;
	}
}
