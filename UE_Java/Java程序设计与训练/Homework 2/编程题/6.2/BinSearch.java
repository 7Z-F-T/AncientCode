public class BinSearch{
	public static void main(String args[]){
		int[] Data={12, 16,19,22,25,32,39,48,55,57, 58,63,68,69,70, 78,84,88,90,97};
		Search.beginSearch(Data,58);
		Search.beginSearch(Data,99);
	}
}

class Search{
	public static void beginSearch(int[] data, int obj){
			int front=0,rear=data.length-1;
			int num=1;//number of executing searching
			int mid=(front+rear)/2;
			while(front<=rear){
				//System.out.println(front+","+rear+","+mid);
				if(obj<data[mid]){
					rear=mid-1;
					mid=(front+rear)/2;
					num++;
				}
				else if(obj>data[mid]){
					front=mid+1;
					mid=(front+rear)/2;
					num++;
				}
				else break;
			}
			if(front<=rear){//found
			  System.out.println(obj+" Found !");
			  System.out.println("Index:"+mid);
			  System.out.println("Number of excuting searching:"+num);
			}
			else{//not found
				System.out.println(obj+" Not found!");
				System.out.println("Number of excuting searching:"+num);
			}
	}
}
		  