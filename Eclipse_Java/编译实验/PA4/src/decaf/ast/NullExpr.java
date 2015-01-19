package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class NullExpr extends Expr {

	public NullExpr(Location location) {
		setBasicInfo(NodeType.NULL_EXPR, location);
	}
	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("null");
	}

}
