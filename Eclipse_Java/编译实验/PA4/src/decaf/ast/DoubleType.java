package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class DoubleType extends Type {

	public DoubleType(Location location) {
		setBasicInfo(NodeType.DOUBLE_TYPE, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);

	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("doubletype");
	}
}
