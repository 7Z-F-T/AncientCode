package decaf.ast;

import decaf.tac.Temp;
import decaf.type.Type;

public abstract class Expr extends ASTNode {

	public Type type;

	public Temp val;

	public boolean isClass;

	public boolean usedForRef;
}
