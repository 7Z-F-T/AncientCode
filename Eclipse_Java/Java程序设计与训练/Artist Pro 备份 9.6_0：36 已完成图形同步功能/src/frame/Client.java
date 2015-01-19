package frame;
import java.io.*;
import java.net.*;
import java.util.*;
import handler.*;

public class Client {
	MainFrame frame;//资源
	String ipAddress;
	int port;
	public Client(MainFrame frm, String ip, int p){
		frame=frm;
		ipAddress=ip;
		port=p;
		try{
			Socket newSocket=new Socket(ipAddress,port);
			ClientSendThread thread1=new ClientSendThread(newSocket,frame);
			ClientReceiveThread thread2=new ClientReceiveThread(newSocket,frame);
			thread1.start();
			thread2.start(); 
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
class ClientSendThread extends Thread {
	Socket socket = null;
    MainFrame frame;
	Flip flagHere1,flagHere2;
	public ClientSendThread(Socket soc,MainFrame frm) {
		socket = soc;
		frame=frm;
		flagHere1=new Flip(frame.repaintFlip.status);//监控绘图变化
		flagHere2=new Flip(frame.retellFlip.status);//监控聊天文字变化
		System.out.println("CleintSendThread constructed");
	}

	public void run() {
	    try{
	   	ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
		while(true){
				if(flagHere1.status!=frame.repaintFlip.status){//该开始发送了
				System.out.println("Send To Server");
				os.writeObject(frame.shapeProArray);
				os.flush();
				os.reset();//重要！
				flagHere1.setFlip();//发送一次就停止
			}
			sleep(100);
		}
	    }catch(Exception e){}
	}
}
class ClientReceiveThread extends Thread {
	Socket socket = null;
	MainFrame frame;
	public ClientReceiveThread(Socket soc,MainFrame frm) {
		socket = soc;
		frame=frm;
		System.out.println("ClientReceiveThread constructed");
	}

	public void run() {
	    try{
	    ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
		ArrayList<ShapePro> receivedShapeProArray;
		ShapePro receivedShapePro;
		while(true){
			receivedShapeProArray=(ArrayList<ShapePro>)(is.readObject());
			if(receivedShapeProArray!=null){//收到东西了
				System.out.println("Received From Server");
				frame.shapeProArray.clear();//更新client
				frame.shapeProArray.ensureCapacity(receivedShapeProArray.size());
				for(int i=0;i<receivedShapeProArray.size();i++){
					receivedShapePro=receivedShapeProArray.get(i);
					frame.shapeProArray.add(receivedShapePro);
					if(receivedShapePro.selected==true)
						DrawHandler.currentSelectedShapePro=receivedShapePro;
				}
				frame.canvas.repaint();
			}
		}
	    }catch(Exception e){}
	}
}
