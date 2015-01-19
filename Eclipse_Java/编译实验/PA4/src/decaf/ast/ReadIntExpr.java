package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ReadIntExpr extends Expr {

	public ReadIntExpr(Location location) {
		setBasicInfo(NodeType.READ_INT_EXPR, location);
	}
	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("readint");
	}

}
