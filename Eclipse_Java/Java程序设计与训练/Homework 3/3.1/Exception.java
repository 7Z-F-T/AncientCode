
public class Exception {
	public static void main(String[] args) {
		try{
			throwException(1);
		}catch(ArithmeticException e){
			System.out.println(e);
		}
		try{
			throwException(2);
		}catch(NullPointerException e){
			System.out.println(e);
		}
		try{
			throwException(3);
		}catch(ClassCastException e){
			System.out.println(e);
		}
		try{
			throwException(4);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e);
		}

	}
	static void throwException(int i) throws ArithmeticException,
	NullPointerException,ClassCastException,ArrayIndexOutOfBoundsException{
		switch(i){
		case 1: throw new ArithmeticException();
		case 2: throw new NullPointerException();
		case 3: throw new ClassCastException();
		case 4: throw new ArrayIndexOutOfBoundsException();
		}
	}

}

