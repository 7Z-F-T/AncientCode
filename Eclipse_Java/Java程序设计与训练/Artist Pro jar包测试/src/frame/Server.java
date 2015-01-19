package frame;
import java.io.*;
import java.net.*;
import java.util.*;
import handler.*;

public class Server{
	MainFrame frame;//��Դ
	int port;
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
		frame.setTitle("Artist Pro <Server Created>");
		new ServerThreadManager(serverSocket,frame).start();
	}
}
class ServerThreadManager extends Thread {
	MainFrame frame;
	ServerSocket serverSocket;
	int clientCount=0;
	public ServerThreadManager(ServerSocket srvSocket,MainFrame frm){
		serverSocket=srvSocket;
		frame=frm;
	}
	public void run(){
	    boolean listening=true;
		while(listening)
		{
			try{
			Socket newSocket=serverSocket.accept();
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

class ServerSendThread extends Thread {
	Socket socket = null;
	MainFrame frame;
	Flip flagHere1,flagHere2;
	int clientNum;
	public ServerSendThread(Socket soc,MainFrame frm,int num) {
		socket = soc;
		frame=frm;
		flagHere1=new Flip(frame.repaintFlip.status);//��ػ�ͼ�仯
		flagHere2=new Flip(frame.retellFlip.status);//����������ֱ仯
		clientNum=num;
		System.out.println("ServerSendThread constructed");
	}

	public void run() {
	    try{
	    ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
		while(true){
			if(flagHere1.status!=frame.repaintFlip.status){//�ÿ�ʼ����ͼ����
				System.out.println("Send To Client "+clientNum);
				os.writeObject(frame.shapeProArray);
                os.flush();
				os.reset();//��Ҫ��
				flagHere1.setFlip();//����һ�ξ�ֹͣ
			}
			if(flagHere2.status!=frame.retellFlip.status){//�ÿ�ʼ������
				System.out.println("Send To Client "+clientNum);
				os.writeObject(frame.chatRecord);
				os.flush();
				os.reset();//��Ҫ��
				flagHere2.setFlip();//����һ�ξ�ֹͣ
				}  
			sleep(100);
		}
	    }catch(Exception e){System.out.println(e);}
	}
}
class ServerReceiveThread extends Thread {
	Socket socket = null;
	MainFrame frame;
	int clientNum;
	public ServerReceiveThread(Socket soc,MainFrame frm,int num) {
		socket = soc;
		frame=frm;
		clientNum=num;
		System.out.println("ServerReceiveThread constructed");
	}

	public void run() {
	    try{
	   	ObjectInputStream is=new ObjectInputStream(socket.getInputStream());
		ArrayList<ShapePro> receivedShapeProArray;
		ShapePro receivedShapePro;
		StringBuffer receivedStringBuffer;
		while(true){
			Object object=(Object)(is.readObject());
			if(object!=null){//�յ�������
				if(object instanceof ArrayList){
					receivedShapeProArray=(ArrayList<ShapePro>)object;
					System.out.println("Received From Client "+clientNum);
					frame.shapeProArray.clear();//����server
					frame.shapeProArray.ensureCapacity(receivedShapeProArray.size());
					for(int i=0;i<receivedShapeProArray.size();i++){
						receivedShapePro=receivedShapeProArray.get(i);
						frame.shapeProArray.add(receivedShapePro);
						if(receivedShapePro.selected==true)
							DrawHandler.currentSelectedShapePro=receivedShapePro;
					}
					frame.canvas.repaint();
					frame.repaintFlip.setFlip();//�ı�server�ı�־λ�������Ϳ��Ը������е�client
				}
				else if(object instanceof StringBuffer){
					receivedStringBuffer=(StringBuffer)object;
					System.out.println("Received From Client "+clientNum);
					frame.chatRecord.delete(0, frame.chatRecord.length());//����server
					frame.chatRecord.append(receivedStringBuffer);
					//frame.publicWords.setText(frame.chatRecord.toString());
					new chatTextAnalyzer(frame, frame.chatRecord);
					
					frame.retellFlip.setFlip();//�ı�server�ı�־λ�������Ϳ��Ը������е�client
				}
			}
		}
	    }catch(Exception e){}
	}
}
