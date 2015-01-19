package NetworkTest;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class Client {
	ArrayList<String> stringArray=new ArrayList();//资源
	//Integer value=new Integer(0);
	Flip flag=new Flip(1);//标志位
	public Client(String[] str){
		for(int i=0;i<str.length;i++)
			stringArray.add(str[i]);
		//value=new Integer(str);
		try{
			Socket newSocket=new Socket("127.0.0.1",4700);
			ClientSendThread thread1=new ClientSendThread(newSocket,stringArray,flag);
			ClientReceiveThread thread2=new ClientReceiveThread(newSocket,stringArray,flag);
			thread1.start();
			thread2.start(); 
		}catch(Exception e) {
			System.out.println("Error"+e);
		}
	}
	public static void main(String args[]) {
		Client client=new Client(args);
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		try{
			//String s=sin.readLine();
		//value=new Integer(sin.readLine());flag=new Integer(flag.intValue()*(-1));
		//value=new Integer(sin.readLine());flag=new Integer(flag.intValue()*(-1));
		}catch(Exception e){}
	}
}
class ClientSendThread extends Thread {
	Socket socket = null;
    //Integer value;
	ArrayList<String> stringArray;
	Flip flag;
	Flip flagHere;
	public ClientSendThread(Socket soc,ArrayList<String> strArray,Flip fl) {
		socket = soc;
		stringArray=strArray;
		flag=fl;
		flagHere=new Flip(flag.status);
		System.out.println("CleintSendThread constructed");
	}

	public void run() {
	    try{
	    //System.out.println("ServerSendThread starts!");
		ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
		//Integer flagHere=new Integer(flag.intValue());
		//System.out.println("ClientSendThread ready to work!");
		//System.out.println(" flag"+flag+" flagH"+flagHere);
		flag.setFlip();//假设此时此端资源发生了变化
		while(true){
			//System.out.println(flag+" "+flagHere);
			if((flagHere.status==flag.status)==false){//该开始发送了
				System.out.println("Client Value is:");
				for(int i=0;i<stringArray.size();i++)
					System.out.print(stringArray.get(i));
				System.out.println();
				System.out.println("Send To Server with value:");
				for(int i=0;i<stringArray.size();i++)
					System.out.print(stringArray.get(i));
				System.out.println();
				os.writeObject(stringArray);
				os.flush();
				os.reset();
				flagHere.setFlip();//发送一次就停止
			}
			sleep(100);
		}
	    }catch(Exception e){}
	}
}
class ClientReceiveThread extends Thread {
	Socket socket = null;
	//Integer value;
	Flip flag;
	ArrayList<String> stringArray;
	public ClientReceiveThread(Socket soc,ArrayList<String> strArray,Flip fl) {
		socket = soc;
		stringArray=strArray;
		flag=fl;
		System.out.println("ClientReceiveThread constructed");
	}

	public void run() {
	    try{
	    //System.out.println("ServerReceiveThread starts!");
		ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
		ArrayList<String> receivedStringArray;
		//System.out.println("ClientReceiveThread ready to work!");
		while(true){
			receivedStringArray=(ArrayList<String>)is.readObject();
//			is.reset()
//			Scanner in = new Scanner(socket.getInputStream());
//			String line = in.nextLine();
//			receivedStringArray = new ArrayList<String>();
//			receivedStringArray.add(line);
			if(receivedStringArray!=null){//收到东西了
				System.out.println("Received From Server with value:");
				for(int i=0;i<receivedStringArray.size();i++)
					System.out.print(receivedStringArray.get(i));
				System.out.println();
				stringArray.clear();//更新client
				stringArray.ensureCapacity(receivedStringArray.size());
				for(int i=0;i<receivedStringArray.size();i++)
					stringArray.add(receivedStringArray.get(i));
				System.out.println("Client Value Changed To:");
				for(int i=0;i<stringArray.size();i++)
					System.out.print(stringArray.get(i));
				System.out.println();
				//System.out.println("flag"+flag);
			}
		}
	    }catch(Exception e){}
	}
}