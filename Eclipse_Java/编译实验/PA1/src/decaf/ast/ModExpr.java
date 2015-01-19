package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ModExpr extends BinaryOpExpr {

	public ModExpr(Expr left, Expr right, Location location) {
		super(NodeType.MOD_EXPR, left, right, location);
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "mod");
	}

}
