package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class NegExpr extends UnaryOpExpr {

	public NegExpr(Expr expr, Location location) {
		super(NodeType.NEG_EXPR, expr, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		unaryOperatorToString(pw, "neg");
	}

}
