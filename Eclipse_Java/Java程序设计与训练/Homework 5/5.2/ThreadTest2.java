import java.util.*;
public class ThreadTest2 {
	public static void main(String[] args) {
		Vector<Integer> vector=new Vector<Integer>(30);
		Runnable producer=new Producer(vector);
		Runnable consumer=new Consumer(vector);
		Thread t1=new Thread(producer);
		Thread t2=new Thread(consumer);
		t1.start();
		t2.start();
		
	}

}
class Producer implements Runnable{
	Vector<Integer> vec;
	Producer(Vector<Integer> v){
		vec=v;
	}
	public void run(){
		for(int i=0;i<30;i++){
			synchronized(vec){
			vec.add(i);
			vec.notify();
			}
		}
	}
}
class Consumer implements Runnable{
	Vector<Integer> vec;
	Consumer(Vector<Integer> v){
		vec=v;
	}
	public  void run(){
		Iterator<Integer> it=vec.iterator();
		for(int i=0;i<30;i++){
			synchronized(vec){
			while(vec.size()-1<i){
				try{
					vec.wait();
				}catch(InterruptedException e){}
			}
			}
			System.out.println(vec.elementAt(i));
	    }
	}
}