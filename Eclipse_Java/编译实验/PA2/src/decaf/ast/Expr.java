package decaf.ast;

import decaf.type.Type;

public abstract class Expr extends ASTNode {

	public Type type;

	public boolean isClass;

	public boolean usedForRef;
}
