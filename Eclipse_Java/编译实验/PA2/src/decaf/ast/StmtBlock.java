package decaf.ast;

import java.util.List;

import decaf.Location;
import decaf.scope.LocalScope;
import decaf.utils.IndentPrintWriter;

public class StmtBlock extends Statement {

	public List<Statement> block;
	
	public LocalScope associatedScope;
	
	public StmtBlock(List<Statement> block, Location location) {
		setBasicInfo(NodeType.STMT_BLOCK, location);
		this.block = block;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("stmtblock");
		pw.incIndent();
		for (Statement s : block) {
			s.printTo(pw);
		}
		pw.decIndent();
	}

}
