package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class NotExpr extends UnaryOpExpr {

	public NotExpr(Expr expr, Location location) {
		super(NodeType.NOT_EXPR, expr, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		unaryOperatorToString(pw, "not");
	}

}
