package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class SubExpr extends BinaryOpExpr {

	public SubExpr(Expr left, Expr right, Location location) {
		super(NodeType.SUB_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "sub");
	}

}
