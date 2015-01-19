package decaf.frontend;

import java.io.IOException;

import decaf.Driver;
import decaf.Location;
import decaf.error.DecafError;
import decaf.error.IntTooLargeError;

public abstract class BaseLexer {

	private Parser parser;

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	abstract int yylex() throws IOException;

	abstract Location getLocation();

	protected void issueError(DecafError error) {
		Driver.getDriver().issueError(error);
	}

	protected void setSemantic(Location where, SemValue v) {
		v.loc = where;
		parser.yylval = v;
	}

	protected int keyword(int code) {
		setSemantic(getLocation(), SemValue.createKeyword(code));
		return code;
	}

	protected int operator(int code) {
		setSemantic(getLocation(), SemValue.createOperator(code));
		return code;
	}

	protected int boolConst(boolean bval) {
		setSemantic(getLocation(), SemValue.createBoolConst(bval));
		return Parser.BOOL_CONST;
	}

	protected int StringConst(String sval, Location loc) {
		setSemantic(loc, SemValue.createStrConst(sval));
		return Parser.STRING_CONST;
	}

	protected int intConst(String ival) {
		try {
			setSemantic(getLocation(), SemValue.createIntConst(Integer.decode(
					ival).intValue()));
		} catch (NumberFormatException e) {
			Driver.getDriver().issueError(
					new IntTooLargeError(getLocation(), ival));
		}
		return Parser.INT_CONST;
	}

	protected int doubleConst(String dval) {
		setSemantic(getLocation(), SemValue.createDoubleConst(Double
				.parseDouble(dval)));
		return Parser.DOUBLE_CONST;
	}

	protected int identifier(String name) {
		setSemantic(getLocation(), SemValue.createIdentifier(name));
		return Parser.IDENTIFIER;
	}

	public void diagnose() throws IOException {
		while (yylex() != 0) {
			System.out.println(parser.yylval);
		}
	}
}
