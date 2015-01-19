package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class MulExpr extends BinaryOpExpr {

	public MulExpr(Expr left, Expr right, Location location) {
		super(NodeType.MUL_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "mul");
	}

}
