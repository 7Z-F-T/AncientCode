package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ReadLineExpr extends Expr {

	public ReadLineExpr(Location location) {
		setBasicInfo(NodeType.READ_LINE_EXPR, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("readline");
	}

}
