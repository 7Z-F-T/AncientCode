package NetworkTest;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class Client {
	Integer value=new Integer(9);//资源
	Integer flag=new Integer(1);//标志位
	public Client(){
		try{
			Socket newSocket=new Socket("127.0.0.1",4700);
			ClientSendThread thread1=new ClientSendThread(newSocket,value,flag);
			ClientReceiveThread thread2=new ClientReceiveThread(newSocket,value,flag);
			thread1.start();
			thread2.start(); 
		}catch(Exception e) {
			System.out.println("Error"+e);
		}
	}
	public static void main(String args[]) {
		Client client=new Client();
		while(true);
	}
}
class ClientSendThread extends Thread {
	Socket socket = null;
    Integer value;
	Integer flag;
	public ClientSendThread(Socket soc,Integer val,Integer fl) {
		socket = soc;
		value=val;
		flag=fl;
	}

	public void run() {
	    try{
	   // System.out.println("ClientSendThread starts!");
		BufferedReader is=new BufferedReader(new
		InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		Integer flagHere=new Integer(flag.intValue());
		System.out.println("ClientSendThread ready to work!");
		while(true){
			if(flagHere.equals(flag)==false){//该开始发送了
				System.out.println("Client Value is:"+value.intValue());
				System.out.println("Send To Server!");
				os.write(value);
				os.flush();
				flagHere=new Integer(flag.intValue());//发送一次就停止
			}
		}
	    }catch(Exception e){}
	}
}
class ClientReceiveThread extends Thread {
	Socket socket = null;
	Integer value;
	Integer flag;
	public ClientReceiveThread(Socket soc, Integer val,Integer fl) {
		socket = soc;
		value=val;
		flag=fl;
	}

	public void run() {
	    try{
	   // System.out.println("ClientReceiveThread starts!");
		BufferedReader is=new BufferedReader(new
		InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		Integer flagHere=new Integer(flag.intValue());
		Integer receivedValue;
		System.out.println("ClientReceiveThread ready to work!");
		while(true){
			receivedValue=is.read();
			if(receivedValue!=-1){//收到东西了
				System.out.println("Recieved From Server!");
				value=receivedValue;//更新client
				System.out.println("Client Value Changed To:"+value.intValue());
			}
		}
	    }catch(Exception e){}
	}
}