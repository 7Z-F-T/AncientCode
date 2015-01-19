package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class StringType extends Type {

	public StringType(Location location) {
		setBasicInfo(NodeType.STRING_TYPE, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("stringtype");
	}
}
