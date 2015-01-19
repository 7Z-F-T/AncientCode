package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class EquExpr extends BinaryOpExpr {

	public EquExpr(Expr left, Expr right, Location location) {
		super(NodeType.EQU_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "equ");
	}

}
