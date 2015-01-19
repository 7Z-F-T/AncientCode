package decaf.ast;

import decaf.Location;
import decaf.ast.Type;
import decaf.symbol.Variable;
import decaf.utils.IndentPrintWriter;

public class VarDecl extends Field {

	public String name;

	public Type type;
	
	public Variable symbol;

	public VarDecl(String name, Type type, Location location) {
		setBasicInfo(NodeType.VAR_DECL, location);
		this.name = name;
		this.type = type;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.print("vardecl " + name + " ");
		type.printTo(pw);
		pw.println();
	}

}
