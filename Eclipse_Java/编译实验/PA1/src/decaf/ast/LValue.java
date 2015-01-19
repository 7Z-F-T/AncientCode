package decaf.ast;

public abstract class LValue extends ASTNode {
	public enum Kind {
		LOCAL_VAR, PARAM_VAR, MEMBER_VAR, ARRAY_ELEMENT
	}
	public Kind lvKind;
}
