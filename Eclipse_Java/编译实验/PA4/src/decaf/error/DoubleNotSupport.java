package decaf.error;

import decaf.Location;

/**
 * example：code generation for doubles is not supported<br>
 * PA3
 * 
 *   
 * 
 */
public class DoubleNotSupport extends DecafError {

	public DoubleNotSupport(Location location) {
		super(location);
	}

	@Override
	protected String getErrMsg() {
		return "code generation for doubles is not supported";
	}

}
