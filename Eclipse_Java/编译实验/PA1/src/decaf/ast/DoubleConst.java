package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class DoubleConst extends Expr {

	public double constVal;

	public DoubleConst(double constVal, Location location) {
		setBasicInfo(NodeType.DOUBLE_CONST, location);
		this.constVal = constVal;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("doubleconst " + constVal);
	}

}
