import java.io.*;
import java.net.*;
public class Server {
	public static void main(String[] args) {
		try{
			ServerSocket srvSocket=new ServerSocket(4700);
			String message="Welcome Socket!";
			Socket socket=new Socket();
			socket=srvSocket.accept();
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Now recieveing a message from client:");
			System.out.println(is.readLine());
			System.out.println("Now begin sending a message to client");
			os.println(message);
			os.flush();
			
			os.close();
			is.close();
			socket.close();
			srvSocket.close();
		}catch(Exception e){
			System.out.println("Error");
		}

	}

}