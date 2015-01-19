package decaf.error;

public final class RuntimeError {

	private RuntimeError() {
	}

	public static final String ARRAY_INDEX_OUT_OF_BOUND = "Decaf runtime error: Array subscript out of bounds\n";

	public static final String NEGATIVE_ARR_SIZE = "Decaf runtime error: Cannot create negative-sized array\n";
}
