package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class BreakStmt extends Statement {

	public BreakStmt(Location location) {
		setBasicInfo(NodeType.BREAK_STMT, location);
	}
	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("break");
	}

}
