package decaf.ast;

import decaf.type.Type;

public abstract class LValue extends ASTNode {
	public enum Kind {
		LOCAL_VAR, PARAM_VAR, MEMBER_VAR, ARRAY_ELEMENT
	}
	public Kind lvKind;
	
	public Type type;
	
	public boolean isClass;
	
	public boolean usedForRef;
}
