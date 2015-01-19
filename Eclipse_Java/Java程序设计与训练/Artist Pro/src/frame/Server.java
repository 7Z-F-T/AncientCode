package frame;
import java.io.*;
import java.net.*;
import java.util.*;
import handler.*;
/**
 * 网络连接服务器端程序。建立收、发两个线程，用于和客户端连接并传送数据
 * @author 侯杰
 *
 */
public class Server{
	MainFrame frame;
	int port;
	/**
	 * 开启一个新的服务器端
	 * @param frm 主程序框架
	 * @param p 端口号
	 */
	public Server(MainFrame frm, int p){
		frame=frm;
		port=p;
		ServerSocket serverSocket=null;
		try{ 
			serverSocket=new ServerSocket(port);
		}catch(Exception e) {
			System.out.println(e);
			System.exit(-1);
		}
		frame.setTitle("Artist Pro <Server Created> (port:"+p+")");
		//开启服务器进程管理线程
		new ServerThreadManager(serverSocket,frame).start();
	}
}
/**
 * 服务器线程管理线程。当有新的客户端连接后，自动为每个新的客户端增设收发线程。
 * @author 侯杰
 *
 */
class ServerThreadManager extends Thread {
	MainFrame frame;
	ServerSocket serverSocket;
	int clientCount=0;
	/**
	 * 建立一个新的线程管理线程
	 * @param srvSocket 与客户端相连的socket
	 * @param frm 主程序框架
	 */
	public ServerThreadManager(ServerSocket srvSocket,MainFrame frm){
		serverSocket=srvSocket;
		frame=frm;
	}
	/**
	 * 监视客户端连接情况并根据需要新建新的收发线程
	 */
	public void run(){
	    boolean listening=true;
		while(listening)
		{
			try{
			Socket newSocket=serverSocket.accept();//接收新的客户端接入
			//开启收发线程
			ServerSendThread thread1=new ServerSendThread(newSocket,frame,clientCount+1);
			ServerReceiveThread thread2=new ServerReceiveThread(newSocket,frame,clientCount+1);
			clientCount++;
			thread1.start();
			thread2.start();
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
/**
 * 服务器端数据发送线程。向客户端发送数据
 * @author 侯杰
 *
 */
class ServerSendThread extends Thread {
	Socket socket = null;
	MainFrame frame;
	Flip flagHere1,flagHere2;
	int clientNum;
	/**
	 * 新建一个服务器端数据发送线程
	 * @param soc 与客户端相连的socket
	 * @param frm 主程序框架
	 * @param num 用来区分不同客户端的编号
	 */
	public ServerSendThread(Socket soc,MainFrame frm,int num) {
		socket = soc;
		frame=frm;
		flagHere1=new Flip(frame.repaintFlip.status);//监控绘图变化
		flagHere2=new Flip(frame.retellFlip.status);//监控聊天文字变化
		clientNum=num;
		System.out.println("ServerSendThread constructed");
	}
    /**
     * 执行服务器端数据发送
     */
	public void run() {
	    try{
	    ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
		while(true){
			if(flagHere1.status!=frame.repaintFlip.status){//该开始发送图形了
				System.out.println("Send To Client "+clientNum);
				os.writeObject(frame.shapeProArray);
                os.flush();
				os.reset();//重要！
				flagHere1.setFlip();//发送一次就停止
			}
			if(flagHere2.status!=frame.retellFlip.status){//该开始发送了
				System.out.println("Send To Client "+clientNum);
				os.writeObject(frame.chatRecord);
				os.flush();
				os.reset();//重要！
				flagHere2.setFlip();//发送一次就停止
				}  
			sleep(100);
		}
	    }catch(Exception e){System.out.println(e);}
	}
}
/**
 * 服务器端数据接收线程。从客户端接收数据
 * @author 侯杰
 *
 */
class ServerReceiveThread extends Thread {
	Socket socket = null;
	MainFrame frame;
	int clientNum;
	/**
	 * 新建一个服务器端数据接收线程
	 * @param soc 与客户端相连的socket
	 * @param frm 主程序框架
	 * @param num 用来区分不同客户端的编号
	 */
	public ServerReceiveThread(Socket soc,MainFrame frm,int num) {
		socket = soc;
		frame=frm;
		clientNum=num;
		System.out.println("ServerReceiveThread constructed");
	}
    /**
     * 执行服务器端数据接收
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
					System.out.println("Received From Client "+clientNum);
					frame.shapeProArray.clear();//更新server
					frame.shapeProArray.ensureCapacity(receivedShapeProArray.size());
					for(int i=0;i<receivedShapeProArray.size();i++){
						receivedShapePro=receivedShapeProArray.get(i);
						frame.shapeProArray.add(receivedShapePro);
						if(receivedShapePro.selected==true)
							DrawHandler.currentSelectedShapePro=receivedShapePro;
					}
					frame.canvas.repaint();
					frame.repaintFlip.setFlip();//改变server的标志位，这样就可以更新所有的client
				}
				else if(object instanceof StringBuffer){
					receivedStringBuffer=(StringBuffer)object;
					System.out.println("Received From Client "+clientNum);
					frame.chatRecord.delete(0, frame.chatRecord.length());//更新server
					frame.chatRecord.append(receivedStringBuffer);
					new ChatTextAnalyzer(frame, frame.chatRecord);
					frame.retellFlip.setFlip();//改变server的标志位，这样就可以更新所有的client
				}
			}
		}
	    }catch(Exception e){}
	}
}
