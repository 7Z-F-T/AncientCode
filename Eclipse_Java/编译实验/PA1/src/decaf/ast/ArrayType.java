package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ArrayType extends Type {

	public Type elementType;

	public ArrayType(Type elementType, Location location) {
		setBasicInfo(NodeType.ARRAY_TYPE, location);
		this.elementType = elementType;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("arrtype ");
		elementType.printTo(pw);
	}
}
