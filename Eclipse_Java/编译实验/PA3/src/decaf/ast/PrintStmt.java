package decaf.ast;

import java.util.List;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class PrintStmt extends Statement {

	public List<Expr> exprs;

	public PrintStmt(List<Expr> exprs, Location location) {
		setBasicInfo(NodeType.PRINT_STMT, location);
		this.exprs = exprs;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("print");
		pw.incIndent();
		for (Expr e : exprs) {
			e.printTo(pw);
		}
		pw.decIndent();
	}

}
