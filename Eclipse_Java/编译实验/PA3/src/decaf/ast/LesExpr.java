package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class LesExpr extends BinaryOpExpr {

	public LesExpr(Expr left, Expr right, Location location) {
		super(NodeType.LES_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "les");
	}
}
