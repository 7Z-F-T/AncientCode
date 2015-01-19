public class IncreaseDecrease{
	public static void main(String args[]){
		int x1=10,x2=10,x3=10,x4=10;
		int y1=x1++;//y1赋值成10，然后x1变成11
    int y2=x2--;//y2赋值成10，然后x2变成9
    int y3=++x3;//x3变成11,然后y3赋值成11
    int y4=--x4;//x4编程9，然后y4赋值成9
    //最后y1=10,y2=10,y3=11,y4=9
    System.out.println("y1="+y1);
    System.out.println("y2="+y2);
    System.out.println("y3="+y3);
    System.out.println("y4="+y4);
  }
}