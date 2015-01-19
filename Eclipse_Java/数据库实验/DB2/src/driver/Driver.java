package driver;
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
public class Driver {
	public static void main (String[] args) {
		Driver driver=new Driver();
		driver.go();
	}
	public void go(){
		MainFrame mainFrame=new MainFrame();
	}
}
