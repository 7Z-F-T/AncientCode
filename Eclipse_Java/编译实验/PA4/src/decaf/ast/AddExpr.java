package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class AddExpr extends BinaryOpExpr {

	public AddExpr(Expr left, Expr right, Location location) {
		super(NodeType.ADD_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "add");
	}

}
