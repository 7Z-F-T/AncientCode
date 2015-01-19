package parse;
import java.io.*;
import driver.Driver;
import java.util.Stack;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.microsoft.sqlserver.*;
import com.microsoft.sqlserver.jdbc.*;
import java.util.*;
public class MyContentHandler extends DefaultHandler{
	Driver driver;
	Stack stack=new Stack();//暂存起点编码
	int count=0;//当前编码位置
	int level=0;//当前深度
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	int buffered=0;
	public MyContentHandler(Driver d){
		driver=d;
	}
	public void startDocument()throws SAXException{
		conn=driver.getConnection();
		stat=driver.getStatement();
		String insert="insert into tab1 values(?,?,?,?)";
		try{
		pstat=conn.prepareStatement(insert);
		System.out.println("\n正在将xml内容加载至数据库，请稍后...");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void startElement(String namespaceURI,String lname,String qname,
			Attributes attrs){
		count++;
		stack.push(count);
		level++;
	}
	
	public void endElement(String namespaceURI,String lname,String qname){
		count++;
		try{
		pstat.setString(1, qname);
		pstat.setString(2, stack.pop().toString());
		pstat.setString(3, String.valueOf(count));
		pstat.setString(4, String.valueOf(level));
		pstat.addBatch();
		buffered++;
		if(buffered==2000){
			pstat.executeBatch();
			System.out.print("\r"+"已加载"+count/2+"项");
			buffered=0;
		}
		}catch(Exception e){
			System.out.println(e);
		}
		level--;
	}
	public void endDocument()throws SAXException{
		try{
		pstat.executeBatch();
		System.out.println("\r"+"已加载"+count/2+"项");
		System.out.println("加载完成！");
		System.out.println();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
