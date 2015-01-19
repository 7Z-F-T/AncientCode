package frame;
import java.io.*;
import java.net.*;
import java.util.*;

import handler.*;
/**
 * 网络连接客户端程序。建立收、发两个线程，用于和服务器连接并传送数据。
 * @author 侯杰
 *
 */
public class Client {
	MainFrame frame;
	String ipAddress;
	int port;
	/**
	 * 开启一个新的客户终端
	 * @param frm 主程序框架
	 * @param ip 所连接的服务器ip地址
	 * @param p 所连接的端口号
	 */
	public Client(MainFrame frm, String ip, int p){
		frame=frm;
		ipAddress=ip;
		port=p;
		try{
			Socket newSocket=new Socket(ipAddress,port);
			//开启收发线程
			ClientSendThread thread1=new ClientSendThread(newSocket,frame);
			ClientReceiveThread thread2=new ClientReceiveThread(newSocket,frame);
			thread1.start();
			thread2.start(); 
			frame.setTitle("Artist Pro <Connected To Server> (Server IP:"+ipAddress+" Port:"+port+")");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
/**
 * 客户端数据发送线程。向服务器端发送数据
 * @author 侯杰
 *
 */
class ClientSendThread extends Thread {
	Socket socket = null;
    MainFrame frame;
	Flip flagHere1,flagHere2;
	/**
	 * 新建一个客户端数据发送线程
	 * @param soc 与服务器相连的socket
	 * @param frm 主程序框架
	 */
	public ClientSendThread(Socket soc,MainFrame frm) {
		socket = soc;
		frame=frm;
		flagHere1=new Flip(frame.repaintFlip.status);//监控绘图变化
		flagHere2=new Flip(frame.retellFlip.status);//监控聊天文字变化
		System.out.println("CleintSendThread constructed");
	}
	/**
	 * 执行客户端数据发送
	 */
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
				if(flagHere2.status!=frame.retellFlip.status){//该开始发送了
					System.out.println("Send To Server");
					os.writeObject(frame.chatRecord);
					os.flush();
					os.reset();//重要！
					flagHere2.setFlip();//发送一次就停止
				}  
			sleep(100);
		}
	    }catch(Exception e){}
	}
}
/**
 * 客户端数据接收线程。从服务器端接收数据
 * @author 侯杰
 *
 */
class ClientReceiveThread extends Thread {
	Socket socket = null;
	MainFrame frame;
	/**
	 * 新建一个客户端数据接收线程
	 * @param soc 与服务器相连的socket
	 * @param frm 主程序框架
	 */
	public ClientReceiveThread(Socket soc,MainFrame frm) {
		socket = soc;
		frame=frm;
		System.out.println("ClientReceiveThread constructed");
	}
    /**
     * 执行客户端数据接收
     */
	public void run() {
	    try{
	    ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
		ArrayList<ShapePro> receivedShapeProArray;
		ShapePro receivedShapePro;
		StringBuffer receivedStringBuffer;
		while(true){
			Object object=(Object)(is.readObject());
			if(object!=null){//收到东西了
				//利用instanceof判断接受到数据的类型，分别处理
				if(object instanceof ArrayList){
					receivedShapeProArray=(ArrayList<ShapePro>)object;
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
				else if(object instanceof StringBuffer){
					receivedStringBuffer=(StringBuffer)object;
					System.out.println("Received From Server");
					frame.chatRecord.delete(0, frame.chatRecord.length());//更新client
					frame.chatRecord.append(receivedStringBuffer);
					new ChatTextAnalyzer(frame, frame.chatRecord);
				}
			}
			
		}
	    }catch(Exception e){}
	}
}
