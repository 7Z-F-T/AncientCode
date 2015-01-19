package decaf.ast;

import decaf.Location;
import decaf.ast.Type;
import decaf.utils.IndentPrintWriter;

public class NewArrayExpr extends Expr {

	public Type elementType;

	public Expr length;

	public NewArrayExpr(Type elementType, Expr length, Location location) {
		setBasicInfo(NodeType.NEW_ARRAY_EXPR, location);
		this.elementType = elementType;
		this.length = length;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("newarray ");
		elementType.printTo(pw);
		pw.println();
		pw.incIndent();
		length.printTo(pw);
		pw.decIndent();
	}

}
