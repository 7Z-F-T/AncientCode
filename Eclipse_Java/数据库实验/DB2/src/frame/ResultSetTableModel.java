package frame;
import com.sun.rowset.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.sql.rowset.*;
public class ResultSetTableModel extends AbstractTableModel{
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	public ResultSetTableModel(ResultSet resultSet){
		rs=resultSet;
		try{
			rsmd=rs.getMetaData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getColumnName(int c){
		try{
			return rsmd.getColumnName(c+1);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	public int getColumnCount(){
		try{
			return rsmd.getColumnCount();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	public Object getValueAt(int r, int c){
		try{
			rs.absolute(r+1);
			return rs.getObject(c+1);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public int getRowCount(){
		try{
			rs.last();
			return rs.getRow();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
}
