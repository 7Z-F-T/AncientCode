public class DifferentTypes2{
	public static void main(String args[]){
		Integer intObj;
		intObj=new Integer(123);
		int i=intObj.intValue();
		float f=intObj.floatValue();
		double d=intObj.doubleValue();
		System.out.println("i="+i+'\n'+"f="+f+'\n'+"d="+d);
	}
}