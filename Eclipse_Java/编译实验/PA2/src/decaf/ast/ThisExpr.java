package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ThisExpr extends Expr {

	public ThisExpr(Location l) {
		setBasicInfo(NodeType.THIS_EXPR, l);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("this");
	}
}
