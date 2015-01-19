package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ReturnStmt extends Statement {

	public Expr expr;
	
	public ReturnStmt(Expr expr, Location location) {
		setBasicInfo(NodeType.RETURN_STMT, location);
		this.expr = expr;
	}
	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("return");
		if (expr != null) {
			pw.incIndent();
			expr.printTo(pw);
			pw.decIndent();
		}
	}

}
