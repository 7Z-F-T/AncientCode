package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ForStmt extends CondtionStmt {

	public Statement init;

	public Statement update;

	public Statement loopBody;

	public ForStmt(Statement init, Expr condition, Statement update,
			Statement loopBody, Location location) {
		super(NodeType.FOR_STMT, condition, location);
		this.init = init;
		this.update = update;
		this.loopBody = loopBody;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);

	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("for");
		pw.incIndent();
		if (init != null) {
			init.printTo(pw);
		} else {
			pw.println("<emtpy>");
		}
		condition.printTo(pw);
		if (update != null) {
			update.printTo(pw);
		} else {
			pw.println("<emtpy>");
		}
		loopBody.printTo(pw);
		pw.decIndent();
	}
}
