package frame;
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
import javax.swing.*;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.io.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.*;
import java.sql.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.microsoft.sqlserver.*;
import com.microsoft.sqlserver.jdbc.*;
import java.util.*;
import parse.Parser;
import frame.MainFrame;
import frame.ResultSetTableModel;
import javax.swing.*;

import java.awt.*;
public class MainFrame extends JFrame{
	public Connection conn=null;
	public Statement stat=null;
	public ResultSetTableModel model=null;
	public JLabel status=null;
	public JTextField sqlInput=null;
	JTable table;
	JScrollPane scrollPane;
	JPanel top;
	JPanel bottom;
	JLabel sql;
	JButton execute;
	public MainFrame(){
		//窗体初始化
		top=new JPanel();
		bottom=new JPanel();
		setLayout(new BorderLayout());
		add(top,"North");
		add(bottom,"South");
		sql=new JLabel("SQL>>");
		status=new JLabel("正在连接数据库...",SwingConstants.TRAILING);
		sqlInput=new JTextField(40);
		execute=new JButton("执行");
		top.setLayout(new FlowLayout());
		top.add(sql);
		top.add(sqlInput);
		top.add(execute);
		bottom.add(status);
		scrollPane=new JScrollPane();
		setSize(800,600);
		setVisible(true);
		setTitle("数据库大作业 2005010130 侯杰");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//连接至SQL server数据库
		try{
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB";
        String user = "sa";
        String password = "beatlesmania";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url, user, password);
        if(conn!=null){ 
        	status.setText("数据库连接成功!   可以开始接收指令");
        	repaint();
        }
        else{
        	status.setText("数据库连接失败!    请检查数据库是否存在");
        	repaint();
        	return;
        }
		}catch(Exception e){
			e.printStackTrace();
		}
		
		execute.addActionListener(new MainFrameHandler(this));
	}
	
}
