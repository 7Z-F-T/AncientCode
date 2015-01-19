package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class NeqExpr extends BinaryOpExpr {

	public NeqExpr(Expr left, Expr right, Location location) {
		super(NodeType.NEQ_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "neq");
	}

}
