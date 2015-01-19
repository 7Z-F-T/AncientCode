package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class AssignStmt extends Statement {

	public LValue left;

	public Expr expr;

	public AssignStmt(LValue left, Expr expr, Location location) {
		setBasicInfo(NodeType.ASSIGN_STMT, location);
		this.left = left;
		this.expr = expr;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("assign");
		pw.incIndent();
		left.printTo(pw);
		expr.printTo(pw);
		pw.decIndent();
	}

}
