package NetworkTest;

import java.io.*;
import java.net.*;

public class Server{
	Integer value=new Integer(3);//资源
	Integer flag=new Integer(1);//标志位
	public Server(){
		ServerSocket serverSocket=null;
		try{ 
			serverSocket=new ServerSocket(4700);
		}catch(IOException e) {
			System.out.println("Could not listen on port:4700.");
			System.exit(-1);
		}
		new ServerThreadManager(serverSocket,value,flag).start();
	}
	public void test(){
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		try{
		value=new Integer(sin.readLine());flag=new Integer(flag.intValue()*(-1));
		value=new Integer(sin.readLine());flag=new Integer(flag.intValue()*(-1));
		}catch(Exception e){}
	}
	public static void main(String args[]){
		Server server=new Server();
		server.test();
		while(true);
	}
}
class ServerThreadManager extends Thread {
	Integer value;
	Integer flag;
	ServerSocket serverSocket;
	public ServerThreadManager(ServerSocket srvSocket,Integer val,Integer fl){
		serverSocket=srvSocket;
		value=val;
		flag=fl;
	}
	public void run(){
	    boolean listening=true;
		while(listening)
		{
			//System.out.println("ServerThreadManager starts!");
			try{
				Socket newSocket=serverSocket.accept();
				ServerSendThread thread1=new ServerSendThread(newSocket,value,flag);
				ServerReceiveThread thread2=new ServerReceiveThread(newSocket,value,flag);
				thread1.start();
				thread2.start();
				}catch(IOException e){}
		}
	}
}

class ServerSendThread extends Thread {
	Socket socket = null;
    Integer value;
	Integer flag;
	public ServerSendThread(Socket soc,Integer val,Integer fl) {
		socket = soc;
		value=val;
		flag=fl;
	}

	public void run() {
	    try{
	    //System.out.println("ServerSendThread starts!");
		BufferedReader is=new BufferedReader(new
		InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		Integer flagHere=new Integer(flag.intValue());
		System.out.println("ServerSendThread ready to work!");
		flag=new Integer(-1);
		while(true){
			//System.out.println(flag+" "+flagHere);
			if(flagHere.equals(flag)==false){//该开始发送了
				System.out.println("Server Value is:"+value.intValue());
				System.out.println("Send To Client!");
				os.write(value);
				os.flush();
				flagHere=new Integer(flag.intValue());//发送一次就停止
			}
		}
	    }catch(Exception e){}
	}
}
class ServerReceiveThread extends Thread {
	Socket socket = null;
	Integer value;
	Integer flag;
	public ServerReceiveThread(Socket soc, Integer val,Integer fl) {
		socket = soc;
		value=val;
		flag=fl;
	}

	public void run() {
	    try{
	    //System.out.println("ServerReceiveThread starts!");
		BufferedReader is=new BufferedReader(new
		InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		Integer flagHere=new Integer(flag.intValue());
		Integer receivedValue;
		System.out.println("ServerReceiveThread ready to work!");
		while(true){
			receivedValue=is.read();
			if(receivedValue!=-1){//收到东西了
				System.out.println("Received From Client!");
				value=receivedValue;//更新server
				System.out.println("Server Value Changed To:"+value.intValue());
				flag=-flag;//改变server的标志位，这样就可以更新所有的client
			}
		}
	    }catch(Exception e){}
	}
}