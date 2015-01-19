package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class IntConst extends Expr {

	public int constVal;

	public IntConst(int constVal, Location location) {
		setBasicInfo(NodeType.INT_CONST, location);
		this.constVal = constVal;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("intconst " + constVal);
	}

}
