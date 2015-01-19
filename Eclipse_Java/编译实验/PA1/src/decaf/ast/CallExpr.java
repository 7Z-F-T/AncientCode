package decaf.ast;

import java.util.List;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class CallExpr extends Expr {

	public Expr receiver;

	public String method;

	public List<Expr> actuals;

	public boolean isArrayLength;

	public CallExpr(Expr receiver, String method, List<Expr> actuals,
			Location location) {
		setBasicInfo(NodeType.CALL_EXPR, location);
		this.receiver = receiver;
		this.method = method;
		this.actuals = actuals;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("call " + method);
		pw.incIndent();
		if (receiver != null) {
			receiver.printTo(pw);
		} else {
			pw.println("<empty>");
		}
		
		for (Expr e : actuals) {
			e.printTo(pw);
		}
		pw.decIndent();
	}
}
