
public class ThreadTest {
	public static void main(String[] args) {
		Thread t1=new MyThread("Thread 1");
		Thread t2=new MyThread("Thread 2");
		Thread t3=new MyThread("Thread 3");
		Thread t4=new MyThread("Thread 4");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t3.setPriority(Thread.NORM_PRIORITY);
		t4.setPriority(Thread.NORM_PRIORITY);
		
		t1.start();
		t2.start();  
		t3.start();
		t4.start();

	}

}
class MyThread extends Thread{
	String str;
	MyThread(String s){
		str=s;
	}
	public void run(){
		for(int i=1;i<=30;i++)
			System.out.println(str+":"+i);
	}
}
