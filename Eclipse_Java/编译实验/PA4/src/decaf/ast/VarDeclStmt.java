package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class VarDeclStmt extends Statement {

	public VarDecl decl;
	
	public VarDeclStmt(VarDecl decl, Location location) {
		setBasicInfo(NodeType.VAR_DECL_STMT, location);
		this.decl = decl;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		decl.printTo(pw);
	}

}
