package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class LeqExpr extends BinaryOpExpr {

	public LeqExpr(Expr left, Expr right, Location location) {
		super(NodeType.LEQ_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "leq");
	}

}
