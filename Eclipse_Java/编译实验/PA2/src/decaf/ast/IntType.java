package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class IntType extends Type {

	public IntType(Location location) {
		setBasicInfo(NodeType.INT_TYPE, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);

	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("inttype");
	}
}
