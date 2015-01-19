package driver;
import java.sql.Connection;
import java.io.*;
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
import parse.Parser;
public class Driver {
	Connection conn;
	Statement stat;
	public static void main (String[] args) {
		Driver driver=new Driver();
		driver.go();
	}
	public void go(){
		try{
		InputStreamReader isr;
		BufferedReader br;
		isr=new InputStreamReader(System.in);
		br=new BufferedReader(isr);
		//连接至sql server数据库
		boolean needLoad;
		System.out.println("连接至数据库...请稍后");
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB";
        String user = "sa";
        String password = "beatlesmania";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url, user, password);
        if(conn!=null){ 
        	System.out.println("数据库连接成功!");
        	System.out.print("SQL>>");
        }
        else{
        	System.out.println("数据库连接失败!");
        	return;
        }
		//命令输入解析
        while(true){
			String line=br.readLine();
			if(line.equalsIgnoreCase("exit")){
				conn.close();
				System.out.println("数据库已关闭!");
				break;
			}
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
			System.out.println("查询前需要重加载xml内容到数据库吗(Y/N)？");
	    	String input=br.readLine();
	    	if(input.equalsIgnoreCase("y"))
	    		needLoad=true;
	    	else
	    		needLoad=false;
			
	        //将xml内容载入数据库中的表中
	        String command;
	        if(needLoad==true){
		        stat=conn.createStatement();
		        stat.executeUpdate("drop table tab1");
		        command="create table tab1(nodeName varchar(20),beginNum int,endNum int,level int)";
		        stat.executeUpdate(command);
				Parser parser=new Parser(this);
				parser.startParse(filename);
	        }
	        //执行查询操作
	        stat=conn.createStatement();
	        System.out.println("查询中，请稍后...");
	        ResultSet rs=null;
	        switch(function){
	        case 0:
	        	command="select * from tab1 where nodeName='"+obj1+"'";
	        	rs=stat.executeQuery(command);
	        	break;
	        case 1:
	        	command="select * from tab1 as t1,tab1 as t2 where t1.nodeName='"+obj1+"' and t2.nodeName='"+obj2+"' and t1.beginNum<t2.beginNum and t1.endNum>t2.endNum and (t2.level-t1.level)=1";
	        	rs=stat.executeQuery(command);
	        	break;
	        case 2:
	        	command="select * from tab1 as t1,tab1 as t2 where t1.nodeName='"+obj1+"' and t2.nodeName='"+obj2+"' and t1.beginNum<t2.beginNum and t1.endNum>t2.endNum";
	        	rs=stat.executeQuery(command);
	        	break;
	        }
	        if(function==0){
	        	int i=0;
		        while(rs!=null&&rs.next()){
		        	i++;
		        	System.out.println("#"+i+"   "+rs.getString(1)+"  "+"{"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"}");
		        }
		        System.out.println("查询完成，共找到"+i+"条目");
		        System.out.print("SQL>>");
	        }
	        else{
	        	int i=0;
	        	System.out.println("查询结果如下:");
	        	while(rs!=null&&rs.next()){
	        		i++;
	 	        	System.out.println("#"+i+"   "+rs.getString(1)+"  "+"{"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"}\t"+rs.getString(5)+"  "+"{"+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+"}");
	 	        }
	        	System.out.println("查询完成，共找到"+i+"条目");
		        System.out.print("SQL>>");
	        }
        }
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public Statement getStatement(){
		return stat;
	}
}
