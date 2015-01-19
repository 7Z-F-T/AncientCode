package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public abstract class ASTNode {
	public enum NodeType {
		PROGRAM, CLASS_DEFN, VAR_DECL, FUNC_DEFN, INT_TYPE, BOOL_TYPE,
		DOUBLE_TYPE, STRING_TYPE, VOID_TYPE, CLASS_TYPE, ARRAY_TYPE,
		ASSIGN_STMT, RETURN_STMT, PRINT_STMT, IF_STMT, FOR_STMT, WHILE_STMT,
		BREAK_STMT, EXPR_STMT, VAR_DECL_STMT, STMT_BLOCK, CALL_EXPR, VAR_REF,
		ARRAY_REF, LVALUE_EXPR, INT_CONST, BOOL_CONST, DOUBLE_CONST,
		STRING_CONST, THIS_EXPR, NULL_EXPR, READ_INT_EXPR, READ_LINE_EXPR,
		NEW_OBJ_EXPR, NEW_ARRAY_EXPR, ADD_EXPR, SUB_EXPR, MUL_EXPR, DIV_EXPR,
		MOD_EXPR, NEG_EXPR, AND_EXPR, OR_EXPR, NOT_EXPR, GTR_EXPR, GEQ_EXPR,
		EQU_EXPR, NEQ_EXPR, LEQ_EXPR, LES_EXPR
	}

	protected NodeType kind;

	protected Location location;

	protected void setBasicInfo(NodeType kind, Location location) {
		this.kind = kind;
		this.location = location;
	}

	public NodeType getKind() {
		return kind;
	}

	public Location getLocation() {
		return location;
	}

	public abstract void accept(IASTVisitor visitor);

	public abstract void printTo(IndentPrintWriter pw);
}
