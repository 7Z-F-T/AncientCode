package decaf.scope;

import decaf.ast.Statement;
import decaf.ast.StmtBlock;
import decaf.symbol.Symbol;
import decaf.utils.IndentPrintWriter;

public class LocalScope extends Scope {

	private StmtBlock astNode;

	public LocalScope(StmtBlock astNode) {
		this.astNode = astNode;
	}

	@Override
	public Kind getKind() {
		return Kind.LOCAL;
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("LOCAL SCOPE:");
		pw.incIndent();
		for (Symbol symbol : symbols.values()) {
			pw.println(symbol);
		}

		for (Statement s : astNode.block) {
			if (s instanceof StmtBlock) {
				((StmtBlock) s).associatedScope.printTo(pw);
			}
		}
		pw.decIndent();
	}

	@Override
	public boolean isLocalScope() {
		return true;
	}
}
