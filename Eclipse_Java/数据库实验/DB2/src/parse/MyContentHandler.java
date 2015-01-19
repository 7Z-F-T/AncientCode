package parse;
import java.io.*;
import driver.Driver;
import frame.MainFrame;
import java.util.Stack;
import javax.swing.*;
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
	MainFrame mainFrame;
	Stack stack=new Stack();//暂存起点编码
	int count=0;//当前编码位置
	int level=0;//当前深度
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	int buffered=0;
	String tableName;
	public MyContentHandler(MainFrame frame,String name){
		mainFrame=frame;
		tableName=name;
	}
	public void startDocument()throws SAXException{
		conn=mainFrame.conn;
		stat=mainFrame.stat;
		String insert="insert into "+tableName+" values(?,?,?,?)";
		try{
		pstat=conn.prepareStatement(insert);
		mainFrame.status.setText("正在加载xml，请稍后...");
		mainFrame.paint(mainFrame.getGraphics());
		mainFrame.repaint();
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
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
