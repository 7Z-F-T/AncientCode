package NetworkTest;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server{
	ArrayList<String> stringArray=new ArrayList();//资源
	//Integer value=new Integer(0);
	Flip flag=new Flip(1);//标志位
	public Server(String[] str){
		ServerSocket serverSocket=null;
		for(int i=0;i<str.length;i++)
			stringArray.add(str[i]);
		//value=new Integer(str);
		try{ 
			serverSocket=new ServerSocket(4700);
		}catch(IOException e) {
			System.out.println("Could not listen on port:4700.");
			System.exit(-1);
		}
		new ServerThreadManager(serverSocket,stringArray,flag).start();
	}
	public void test(){
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		try{
			//String s=sin.readLine();
		//value=new Integer(sin.readLine());flag=new Integer(flag.intValue()*(-1));
		//value=new Integer(sin.readLine());flag=new Integer(flag.intValue()*(-1));
		}catch(Exception e){}
	}
	public static void main(String args[]){
		Server server=new Server(args);
		server.test();
		
	}
}
class ServerThreadManager extends Thread {
	//Integer value;
	ArrayList<String> stringArray;
	Flip flag;
	ServerSocket serverSocket;
	int clientCount=0;
	public ServerThreadManager(ServerSocket srvSocket,ArrayList<String> strArray,Flip fl){
		serverSocket=srvSocket;
		stringArray=strArray;
		flag=fl;
	}
	public void run(){
	    boolean listening=true;
		while(listening)
		{
			//System.out.println("ServerThreadManager starts!");
			try{
				Socket newSocket=serverSocket.accept();
				ServerSendThread thread1=new ServerSendThread(newSocket,stringArray,flag,clientCount+1);
				ServerReceiveThread thread2=new ServerReceiveThread(newSocket,stringArray,flag,clientCount+1);
				clientCount++;
				thread1.start();
				thread2.start();
				}catch(IOException e){}
		}
	}
}

class ServerSendThread extends Thread {
	Socket socket = null;
    //Integer value;
	ArrayList<String> stringArray;
	Flip flag;
	Flip flagHere;
	int clientNum;
	public ServerSendThread(Socket soc,ArrayList<String> strArray,Flip fl,int num) {
		socket = soc;
		stringArray=strArray;
		flag=fl;
		flagHere=new Flip(flag.status);
		clientNum=num;
		System.out.println("ServerSendThread constructed");
	}

	public void run() {
	    try{
	    //System.out.println("ServerSendThread starts!");
		ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
		//Integer flagHere=new Integer(flag.intValue());
		//System.out.println("ServerSendThread ready to work!");
		//System.out.println(" flag"+flag+" flagH"+flagHere);
		//flag.setFlip();//假设此时此端资源发生了变化
		while(true){
			//System.out.println(flag+" "+flagHere);
			if((flagHere.status==flag.status)==false){//该开始发送了
				System.out.println("Server Value is:");
				for(int i=0;i<stringArray.size();i++)
					System.out.print(stringArray.get(i));
				System.out.println();
				System.out.println("Send To Client "+clientNum+" with value:");
				for(int i=0;i<stringArray.size();i++)
					System.out.print(stringArray.get(i));
				System.out.println();

				os.writeObject(stringArray);
//				System.out.println("[BETWEEN] "+stringArray.get(0));
//				os.writeObject(stringArray);
//				PrintStream out = new PrintStream(socket.getOutputStream(),true);
//				out.println(stringArray.get(0)+"");
//				os.flush();
				os.reset();
				flagHere.setFlip();//发送一次就停止
			}
			sleep(100);
		}
	    }catch(Exception e){}
	}
}
class ServerReceiveThread extends Thread {
	Socket socket = null;
	//Integer value;
	Flip flag;
	ArrayList<String> stringArray;
	int clientNum;
	public ServerReceiveThread(Socket soc,ArrayList<String> strArray,Flip fl,int num) {
		socket = soc;
		stringArray=strArray;
		flag=fl;
		clientNum=num;
		System.out.println("ServerReceiveThread constructed");
	}

	public void run() {
	    try{
	    //System.out.println("ServerReceiveThread starts!");
		ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
		ArrayList<String> receivedStringArray;
		//System.out.println("ServerReceiveThread ready to work!");
		while(true){
			receivedStringArray=(ArrayList<String>)is.readObject();
			if(receivedStringArray!=null){//收到东西了
				System.out.println("Received From Client "+clientNum+" with value:");
				for(int i=0;i<receivedStringArray.size();i++)
					System.out.print(receivedStringArray.get(i));
				System.out.println();
				stringArray.clear();//更新server
				stringArray.ensureCapacity(receivedStringArray.size());
				for(int i=0;i<receivedStringArray.size();i++)
					stringArray.add(receivedStringArray.get(i));
				System.out.println("Server Value Changed To:");
				for(int i=0;i<stringArray.size();i++)
					System.out.print(stringArray.get(i));
				System.out.println();
				flag.setFlip();//改变server的标志位，这样就可以更新所有的client
				//System.out.println("flag"+flag);
			}
		}
	    }catch(Exception e){}
	}
}