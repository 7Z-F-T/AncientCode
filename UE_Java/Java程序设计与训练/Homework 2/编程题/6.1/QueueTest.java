class Queue{
	char[] elem;//store elements of queue
	int front=0,rear=0;
	int maxSize=0;
	Queue(int sz){
		elem=new char[sz+1];
		maxSize=sz+1;
	}
	public int size=0;
  public boolean put(char ch){
  	if((rear+1)%maxSize==front){//Queue full
  		System.out.println("Queue Full!  "+"Failed to put "+ch);
  		return false;
  	}
    else{
    	 elem[rear]=ch;
    	 rear=(rear+1)%maxSize;
    	 size++;
    	 return true;
    }
  }
  public char get(){
  	if(rear==front){
  		System.out.println("Queue Empty!  "+"Failed to get");
  		return 0;
    }
    else{
    	char tmp=elem[front];
    	front=(front+1)%maxSize;
    	size--;
    	return tmp;
    }
  }
}

public class QueueTest{
	public static void main(String args[]){
		Queue q1=new Queue(100);
		Queue q2=new Queue(4);
		for(char ch='A';ch<='Z';ch++)
		  q1.put(ch);
		while(q1.size!=0)
		  System.out.print(q1.get());
		  
		 System.out.println();
		 
		 q2.put('Z');
		 q2.put('Y');
		 q2.put('X');
		 q2.put('W');
		 q2.put('V');
		 while(q2.size!=0)
		   System.out.print(q2.get());
		   
	}
}
		 