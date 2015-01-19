package decaf.error;

import decaf.Location;

public class ThisInStaticFuncError extends DecafError {

	public ThisInStaticFuncError(Location location) {
		super(location);
	}

	@Override
	protected String getErrMsg() {
		return "can not use this in static function";
	}

}
