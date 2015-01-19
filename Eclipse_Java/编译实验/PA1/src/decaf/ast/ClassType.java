package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ClassType extends Type {

	public String name;

	public ClassType(String name, Location location) {
		setBasicInfo(NodeType.CLASS_TYPE, location);
		this.name = name;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("classtype " + name);
	}
}
