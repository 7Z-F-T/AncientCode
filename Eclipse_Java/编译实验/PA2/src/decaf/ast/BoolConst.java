package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class BoolConst extends Expr {

	public boolean constVal;

	public BoolConst(boolean constVal, Location location) {
		setBasicInfo(NodeType.BOOL_CONST, location);
		this.constVal = constVal;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("boolconst " + constVal);
	}

}
