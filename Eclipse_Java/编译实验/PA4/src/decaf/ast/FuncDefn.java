package decaf.ast;

import java.util.List;

import decaf.Location;
import decaf.symbol.Function;
import decaf.utils.IndentPrintWriter;

public class FuncDefn extends Field {
	
	public boolean statik;

	public String name;

	public Type returnType;

	public List<VarDecl> formals;

	public StmtBlock body;

	public Function symbol;

	public FuncDefn(boolean statik, String name, Type returnType, List<VarDecl> formals,
			StmtBlock body, Location location) {
		setBasicInfo(NodeType.FUNC_DEFN, location);
		this.statik = statik;
		this.name = name;
		this.returnType = returnType;
		this.formals = formals;
		this.body = body;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);

	}
	
	@Override
	public void printTo(IndentPrintWriter pw) {
		if (statik) {
			pw.print("static ");
		}
		pw.print("func " + name + " ");
		returnType.printTo(pw);
		pw.println();
		pw.incIndent();
		pw.println("formals");
		pw.incIndent();
		for (VarDecl d : formals) {
			d.printTo(pw);
		}
		pw.decIndent();
		body.printTo(pw);
		pw.decIndent();
	}

}
