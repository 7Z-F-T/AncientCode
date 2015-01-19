package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class ArrayRef extends LValue {

	public Expr array;

	public Expr index;

	public ArrayRef(Expr array, Expr index, Location location) {
		setBasicInfo(NodeType.ARRAY_REF, location);
		this.array = array;
		this.index = index;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("arrref");
		pw.incIndent();
		array.printTo(pw);
		index.printTo(pw);
		pw.decIndent();
	}

}
