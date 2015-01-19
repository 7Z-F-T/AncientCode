package frame;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.microsoft.sqlserver.*;
import com.microsoft.sqlserver.jdbc.*;
import java.util.*;
import parse.*;

import parse.Parser;
import javax.swing.*;
public class MainFrameHandler implements ActionListener{
	MainFrame mainFrame;
	Connection conn;
	Statement stat;
	public MainFrameHandler(MainFrame frame){
		mainFrame=frame;
		conn=mainFrame.conn;
	}
	public void actionPerformed(ActionEvent ae){
		//输入命令解析
		try{
		String line=mainFrame.sqlInput.getText();
		int function=0;//0:查询某一类项，1:查询父子关系，2:查询祖孙关系
		StringTokenizer tk1=new StringTokenizer(line);
		ArrayList<String> strs=new ArrayList<String>();
		while(tk1.hasMoreTokens()){
			String token=tk1.nextToken();
			if(!token.equalsIgnoreCase("select")&&!token.equalsIgnoreCase("from")){
				strs.add(token);
			}
		}
		String obj1=null,obj2=null;
		if(line.contains("//")){
			StringTokenizer tk2=new StringTokenizer(strs.get(0),"/");
			if(tk2.hasMoreTokens()){
				String token=tk2.nextToken();
				obj1=token;
				token=tk2.nextToken();
				obj2=token;
				function=2;
			}
		}
		else if(line.contains("/")){
			StringTokenizer tk2=new StringTokenizer(strs.get(0),"/");
			if(tk2.hasMoreTokens()){
				String token=tk2.nextToken();
				obj1=token;
				token=tk2.nextToken();
				obj2=token;
				function=1;
			}
		}
		else{
			obj1=strs.get(0);
			obj2=null;
			function=0;
		}
		String filename = strs.get(1);
		String tableName = filename.substring(0,filename.length()-4);
		boolean needLoad=false;
		//判断是否要重新加载xml内容进数据库
		try{
			stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stat.execute("insert into "+tableName+" values('test',0,0,0)");
			stat.execute("delete from "+tableName+" where nodeName='test'");
		}catch(Exception e){
			needLoad=true;
		}
        //将xml内容载入数据库中的表中
        String command;
        if(needLoad==true){
	        stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        command="create table "+tableName+"(nodeName varchar(20),beginNum int,endNum int,level int)";
	        stat.executeUpdate(command);
			Parser parser=new Parser(mainFrame);
			parser.startParse(filename);
        }
        //执行查询操作
        stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        mainFrame.status.setText("");
        mainFrame.status.setText("正在查询，请稍后...");
        mainFrame.paint(mainFrame.getGraphics());
        ResultSet rs=null;
        switch(function){
        case 0:
        	command="select * from "+tableName+" where nodeName='"+obj1+"'";
        	rs=stat.executeQuery(command);
        	break;
        case 1:
        	command="select * from "+tableName+" as t1,"+tableName+" as t2 where t1.nodeName='"+obj1+"' and t2.nodeName='"+obj2+"' and t1.beginNum<t2.beginNum and t1.endNum>t2.endNum and (t2.level-t1.level)=1";
        	rs=stat.executeQuery(command);
        	break;
        case 2:
        	command="select * from "+tableName+" as t1,"+tableName+" as t2 where t1.nodeName='"+obj1+"' and t2.nodeName='"+obj2+"' and t1.beginNum<t2.beginNum and t1.endNum>t2.endNum";
        	rs=stat.executeQuery(command);
        	break;
        }
        if(function==0){
            mainFrame.model=new ResultSetTableModel(rs);
	        mainFrame.table=new JTable(mainFrame.model);
	        mainFrame.remove(mainFrame.scrollPane);
	        mainFrame.scrollPane=new JScrollPane(mainFrame.table);
	        mainFrame.scrollPane.repaint();
	        mainFrame.status.setText("查询完成，共找到"+mainFrame.model.getRowCount()+"条目");
	        mainFrame.add(mainFrame.scrollPane,"Center");
	        mainFrame.validate();
	        mainFrame.repaint();
        }
        else{
        	mainFrame.model=new ResultSetTableModel(rs);
	        mainFrame.table=new JTable(mainFrame.model);
	        mainFrame.remove(mainFrame.scrollPane);
	        mainFrame.scrollPane=new JScrollPane(mainFrame.table);
	        mainFrame.status.setText("查询完成，共找到"+mainFrame.model.getRowCount()+"条目");
	        mainFrame.add(mainFrame.scrollPane,"Center");
	        mainFrame.validate();
	        mainFrame.repaint();
        }
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
