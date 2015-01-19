import java.io.*;
import java.net.*;
public class Client {
	public static void main(String[] args) {
		try{
			Socket socket=new Socket("127.0.0.1",4700);
			String message="Hello Socket Server!";
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Now begin sending a message to server");
			os.println(message);
			os.flush();
			System.out.println("Now recieveing a message from server:");
			System.out.println(is.readLine());
			
			os.close();
			is.close();
			socket.close();
		}catch(Exception e){
			System.out.println("Error");
		}

	}

}
