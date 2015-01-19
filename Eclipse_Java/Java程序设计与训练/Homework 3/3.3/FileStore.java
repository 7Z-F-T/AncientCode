import java.io.*;
public class FileStore {
	public static void main (String[] args) {
		//definition
		InputStreamReader isr;
		BufferedReader br;
		OutputStreamWriter osw;
		BufferedWriter bw;
		FileOutputStream fos;
		String str=System.getProperty("line.separator");//str实际就是一个换行符
		//read contents to memory
		try{
			isr=new InputStreamReader(System.in);
			br=new BufferedReader(isr);
			StringBuffer sb=new StringBuffer();
			String s=br.readLine();
			while(s.equals("quit")==false && s.equals("QUIT")==false){
				sb.append(s);
				sb.append(str);
				s=br.readLine();	
			}
			//input filename
			System.out.println("Please input a filename:");
			s=br.readLine();
			//write contents to file
			fos=new FileOutputStream(s);
			osw=new OutputStreamWriter(fos);
			bw=new BufferedWriter(osw);
			bw.write(sb.toString());
			bw.flush();
			System.out.println(s+" has been created successfully!");
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}

	}

}
