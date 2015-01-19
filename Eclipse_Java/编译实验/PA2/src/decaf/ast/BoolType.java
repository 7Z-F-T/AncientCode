package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class BoolType extends Type {

	public BoolType(Location location) {
		setBasicInfo(NodeType.BOOL_TYPE, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("booltype");
	}
}
