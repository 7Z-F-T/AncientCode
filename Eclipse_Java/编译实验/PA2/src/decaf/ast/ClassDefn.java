package decaf.ast;

import java.util.List;

import decaf.Location;
import decaf.symbol.Class;
import decaf.utils.IndentPrintWriter;

public class ClassDefn extends ASTNode {

	public String name;

	public String parent;

	public List<Field> fields;

	public Class symbol;

	public ClassDefn(String name, String parent, List<Field> fields,
			Location location) {
		setBasicInfo(NodeType.CLASS_DEFN, location);
		this.name = name;
		this.parent = parent;
		this.fields = fields;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);

	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("class " + name + " "
				+ (parent != null ? parent : "<empty>"));
		pw.incIndent();
		for (Field f : fields) {
			f.printTo(pw);
		}
		pw.decIndent();
	}
}
