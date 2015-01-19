package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class IfStmt extends CondtionStmt {

	public Statement trueBranch;

	public Statement falseBranch;

	public IfStmt(Expr condition, Statement trueBranch, Statement falseBranch,
			Location location) {
		super(NodeType.IF_STMT, condition, location);
		this.trueBranch = trueBranch;
		this.falseBranch = falseBranch;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("if");
		pw.incIndent();
		condition.printTo(pw);
		trueBranch.printTo(pw);
		pw.decIndent();
		if (falseBranch != null) {
			pw.println("else");
			pw.incIndent();
			falseBranch.printTo(pw);
			pw.decIndent();
		}

	}

}
