package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class OrExpr extends BinaryOpExpr {

	public OrExpr(Expr left, Expr right, Location location) {
		super(NodeType.OR_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "or");
	}
}
