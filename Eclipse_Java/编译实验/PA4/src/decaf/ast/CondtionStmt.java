package decaf.ast;

import decaf.Location;

public abstract class CondtionStmt extends Statement {

	public Expr condition;

	public CondtionStmt(NodeType kind, Expr condition, Location location) {
		setBasicInfo(kind, location);
		this.condition = condition;
	}

}
