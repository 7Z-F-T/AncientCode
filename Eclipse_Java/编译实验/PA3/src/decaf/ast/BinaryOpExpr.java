package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public abstract class BinaryOpExpr extends Expr {

	public Expr left;

	public Expr right;

	public BinaryOpExpr(NodeType kind, Expr left, Expr right, Location location) {
		setBasicInfo(kind, location);
		this.left = left;
		this.right = right;
	}

	protected void binaryOperatorPrintTo(IndentPrintWriter pw, String op) {
		pw.println(op);
		pw.incIndent();
		left.printTo(pw);
		right.printTo(pw);
		pw.decIndent();
	}

}
