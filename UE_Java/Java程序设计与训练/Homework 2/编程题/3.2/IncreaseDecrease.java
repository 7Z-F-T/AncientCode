public class IncreaseDecrease{
	public static void main(String args[]){
		int x1=10,x2=10,x3=10,x4=10;
		int y1=x1++;//y1��ֵ��10��Ȼ��x1���11
    int y2=x2--;//y2��ֵ��10��Ȼ��x2���9
    int y3=++x3;//x3���11,Ȼ��y3��ֵ��11
    int y4=--x4;//x4���9��Ȼ��y4��ֵ��9
    //���y1=10,y2=10,y3=11,y4=9
    System.out.println("y1="+y1);
    System.out.println("y2="+y2);
    System.out.println("y3="+y3);
    System.out.println("y4="+y4);
  }
}