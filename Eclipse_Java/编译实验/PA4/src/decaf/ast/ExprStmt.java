package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ExprStmt extends Statement {

	public Expr expr;

	public ExprStmt(Expr expr, Location location) {
		setBasicInfo(NodeType.EXPR_STMT, location);
		this.expr = expr;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		expr.printTo(pw);
	}

}
