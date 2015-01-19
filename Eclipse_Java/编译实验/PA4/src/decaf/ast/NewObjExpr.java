package decaf.ast;

import decaf.Location;
import decaf.symbol.Class;
import decaf.utils.IndentPrintWriter;

public class NewObjExpr extends Expr {

	public String className;

	public Class symbol;

	public NewObjExpr(String className, Location location) {
		setBasicInfo(NodeType.NEW_OBJ_EXPR, location);
		this.className = className;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("newobj " + className);
	}

}
