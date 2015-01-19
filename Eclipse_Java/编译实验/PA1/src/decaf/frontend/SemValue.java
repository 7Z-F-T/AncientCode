package decaf.frontend;

import java.util.List;

import decaf.Location;
import decaf.ast.ClassDefn;
import decaf.ast.Expr;
import decaf.ast.Field;
import decaf.ast.FuncDefn;
import decaf.ast.LValue;
import decaf.ast.Program;
import decaf.ast.Statement;
import decaf.ast.VarDecl;
import decaf.ast.Type;
import decaf.utils.MiscUtils;

public class SemValue {

	public int code;

	public Location loc;

	public int ival;

	public boolean bval;

	public double dval;

	public String sval;

	public List<ClassDefn> clist;

	public List<Field> flist;

	public List<VarDecl> vlist;

	public List<Statement> slist;

	public List<Expr> elist;

	public Program prog;

	public ClassDefn cdef;

	public VarDecl vdecl;

	public FuncDefn fdef;

	public Type type;

	public Statement stmt;

	public Expr expr;

	public LValue lvalue;

	/**
	 * 创建一个关键字的语义值
	 * 
	 * @param code
	 *            关键字的代表码
	 * @return 对应关键字的语义值
	 */
	public static SemValue createKeyword(int code) {
		SemValue v = new SemValue();
		v.code = code;
		return v;
	}

	/**
	 * 创建一个操作符的语义值
	 * 
	 * @param code
	 *            操作符的代表码
	 * @return 对应操作符的语义值
	 */
	public static SemValue createOperator(int code) {
		SemValue v = new SemValue();
		v.code = code;
		return v;
	}

	/**
	 * 创建一个整型常量的语义值
	 * 
	 * @param value
	 *            整型常量的值
	 * @return 对应的语义值
	 */
	public static SemValue createIntConst(int value) {
		SemValue v = new SemValue();
		v.code = Parser.INT_CONST;
		v.ival = value;
		return v;
	}

	/**
	 * 创建一个布尔常量的语义值
	 * 
	 * @param value
	 *            布尔常量的值
	 * @return 对应的语义值
	 */
	public static SemValue createBoolConst(boolean value) {
		SemValue v = new SemValue();
		v.code = Parser.BOOL_CONST;
		v.bval = value;
		return v;
	}

	/**
	 * 创建一个浮点常量的语义值
	 * 
	 * @param value
	 *            浮点常量的值
	 * @return 对应的语义值
	 */
	public static SemValue createDoubleConst(double value) {
		SemValue v = new SemValue();
		v.code = Parser.DOUBLE_CONST;
		v.dval = value;
		return v;
	}

	/**
	 * 创建一个字符串常量的语义值
	 * 
	 * @param value
	 *            字符串常量的值
	 * @return 对应的语义值
	 */
	public static SemValue createStrConst(String value) {
		SemValue v = new SemValue();
		v.code = Parser.STRING_CONST;
		v.sval = value;
		return v;
	}

	/**
	 * 创建一个标识符的语义值
	 * 
	 * @param name
	 *            标识符的名字
	 * @return 对应的语义值（标识符名字存放在sval域）
	 */
	public static SemValue createIdentifier(String name) {
		SemValue v = new SemValue();
		v.code = Parser.IDENTIFIER;
		v.sval = name;
		return v;
	}

	/**
	 * 获取这个语义值的字符串表示<br>
	 * 
	 * 我们建议你在构造词法分析器之前先阅读一下这个函数。
	 */
	public String toString() {
		String msg;
		switch (code) {
		// 关键字
		case Parser.BOOL:
			msg = "keyword  : bool";
			break;
		case Parser.BREAK:
			msg = "keyword  : break";
			break;
		case Parser.CLASS:
			msg = "keyword  : class";
			break;
		case Parser.DOUBLE:
			msg = "keyword  : double";
			break;
		case Parser.ELSE:
			msg = "keyword  : else";
			break;
		case Parser.EXTENDS:
			msg = "keyword  : extends";
			break;
		case Parser.FOR:
			msg = "keyword  : for";
			break;
		case Parser.IF:
			msg = "keyword  : if";
			break;
		case Parser.INT:
			msg = "keyword  : int";
			break;
		case Parser.NEW:
			msg = "keyword  : new";
			break;
		case Parser.NULL:
			msg = "keyword  : null";
			break;
		case Parser.PRINT:
			msg = "keyword  : Print";
			break;
		case Parser.READ_INTEGER:
			msg = "keyword  : ReadInteger";
			break;
		case Parser.READ_LINE:
			msg = "keyword  : ReadLine";
			break;
		case Parser.RETURN:
			msg = "keyword  : return";
			break;
		case Parser.STRING:
			msg = "keyword  : string";
			break;
		case Parser.THIS:
			msg = "keyword  : this";
			break;
		case Parser.VOID:
			msg = "keyword  : void";
			break;
		case Parser.WHILE:
			msg = "keyword  : while";
			break;
		case Parser.STATIC:
			msg = "keyword : static";
			break;

		// 常量
		case Parser.BOOL_CONST:
			msg = "constant : " + bval;
			break;
		case Parser.INT_CONST:
			msg = "constant : " + ival;
			break;
		case Parser.STRING_CONST:
			msg = "constant : " + MiscUtils.quote(sval);
			break;
		case Parser.DOUBLE_CONST:
			msg = "constant : " + dval;
			break;

		// 标识符
		case Parser.IDENTIFIER:
			msg = "identifier: " + sval;
			break;

		// 操作符
		case Parser.AND:
			msg = "operator : &&";
			break;
		case Parser.EQUAL:
			msg = "operator : ==";
			break;
		case Parser.GREATER_EQUAL:
			msg = "operator : >=";
			break;
		case Parser.LESS_EQUAL:
			msg = "operator : <=";
			break;
		case Parser.NOT_EQUAL:
			msg = "operator : !=";
			break;
		case Parser.OR:
			msg = "operator : ||";
			break;
		default:
			msg = "operator : " + (char) code;
			break;
		}
		return (String.format("%-15s%s", loc, msg));
	}
}
