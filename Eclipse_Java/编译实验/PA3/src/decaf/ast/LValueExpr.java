package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class LValueExpr extends Expr {

	public LValue lv;

	public LValueExpr(LValue lv, Location location) {
		setBasicInfo(NodeType.LVALUE_EXPR, location);
		this.lv = lv;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return lv.toString();
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		lv.printTo(pw);
	}
}
