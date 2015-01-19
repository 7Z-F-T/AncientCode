public class ConsecutiveInt{
	public static void main(String args[]){
		for(int i=1;i<1000;i++){
			int sum=0,j=i;
			while(sum<=1000){
				if(sum==1000){
					System.out.println("Solution:");
					for(int k=i;k<j;k++)
					  System.out.print(k+" ");
					  System.out.println();
					  break;
					}
				else sum+=j++;
			}
		}
	}
}
