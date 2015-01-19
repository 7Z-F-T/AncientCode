package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class GeqExpr extends BinaryOpExpr {

	public GeqExpr(Expr left, Expr right, Location location) {
		super(NodeType.GEQ_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "geq");
	}
}
