package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;
import decaf.utils.MiscUtils;

public class StringConst extends Expr {

	public String constVal;

	public StringConst(String constVal, Location location) {
		setBasicInfo(NodeType.STRING_CONST, location);
		this.constVal = constVal;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("stringconst " + MiscUtils.quote(constVal));
	}

}
