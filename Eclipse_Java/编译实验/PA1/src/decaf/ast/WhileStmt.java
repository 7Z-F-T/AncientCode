package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class WhileStmt extends CondtionStmt {

	public Statement loopBody;

	public WhileStmt(Expr condition, Statement loopBody, Location location) {
		super(NodeType.WHILE_STMT, condition, location);
		this.loopBody = loopBody;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);

	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("while");
		pw.incIndent();
		loopBody.printTo(pw);
		pw.decIndent();
	}

}
