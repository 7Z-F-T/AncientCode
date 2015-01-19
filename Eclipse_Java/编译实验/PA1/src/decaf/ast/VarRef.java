package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class VarRef extends LValue {

	public Expr owner;

	public String name;

	public VarRef(Expr owner, String name, Location location) {
		setBasicInfo(NodeType.VAR_REF, location);
		this.owner = owner;
		this.name = name;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("varref " + name);
		if (owner != null) {
			pw.incIndent();
			owner.printTo(pw);
			pw.decIndent();
		}
	}
}
