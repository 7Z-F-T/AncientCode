package parse;
import java.io.*;
import java.sql.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.microsoft.sqlserver.*;
import com.microsoft.sqlserver.jdbc.*;
import java.util.*;
import driver.Driver;
import frame.MainFrame;
public class Parser{
	MainFrame mainFrame;
	public Parser(MainFrame frame){
		mainFrame=frame;
	}
	public void startParse(String filename){
		try{
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser saxParser=factory.newSAXParser();
		File file=new File(filename);
		saxParser.parse(file,new MyContentHandler(mainFrame,filename.substring(0,filename.length()-4)));
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
