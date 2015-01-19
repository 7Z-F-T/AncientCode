package decaf.ast;

import java.util.List;

import decaf.Location;
import decaf.scope.GlobalScope;
import decaf.symbol.Class;
import decaf.utils.IndentPrintWriter;

public class Program extends ASTNode {
	
	public List<ClassDefn> classes;
	
	public Class main;
	
	public GlobalScope globalScope;

	public Program(List<ClassDefn> classes, Location location) {
		setBasicInfo(NodeType.PROGRAM, location);
		this.classes = classes;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("program");
		pw.incIndent();
		for (ClassDefn d : classes) {
			d.printTo(pw);
		}
		pw.decIndent();
	}

}
