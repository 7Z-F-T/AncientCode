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
public class Parser{
	Driver driver;
	public Parser(Driver d){
		driver=d;
	}
	public void startParse(String filename){
		try{
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser saxParser=factory.newSAXParser();
		File file=new File(filename);
		saxParser.parse(file,new MyContentHandler(driver));
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
