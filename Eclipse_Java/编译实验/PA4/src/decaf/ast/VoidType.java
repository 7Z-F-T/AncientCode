package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class VoidType extends Type {

	public VoidType(Location location) {
		setBasicInfo(NodeType.VOID_TYPE, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("voidtype");
	}
}
