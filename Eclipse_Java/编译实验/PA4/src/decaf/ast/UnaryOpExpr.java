package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public abstract class UnaryOpExpr extends Expr {

	public Expr expr;

	public UnaryOpExpr(NodeType kind, Expr expr, Location location) {
		setBasicInfo(kind, location);
		this.expr = expr;
	}

	protected void unaryOperatorToString(IndentPrintWriter pw, String op) {
		pw.println(op);
		pw.incIndent();
		expr.printTo(pw);
		pw.decIndent();
	}
}
