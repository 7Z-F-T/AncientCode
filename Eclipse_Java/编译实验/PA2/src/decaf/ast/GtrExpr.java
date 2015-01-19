package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class GtrExpr extends BinaryOpExpr {

	public GtrExpr(Expr left, Expr right, Location location) {
		super(NodeType.GTR_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "gtr");
	}
}
