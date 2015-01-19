package handler;
import frame.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public abstract class ToolBoxHandler {
	public static class RectangleButton implements ActionListener{
		MainFrame frame;
		public RectangleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.RECTANGLE;
		}
	}
	public static class LineButton implements ActionListener{
		MainFrame frame;
		public LineButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.LINE;
		}
	}
	public static class EllipseButton implements ActionListener{
		MainFrame frame;
		public EllipseButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.ELLIPSE;
		}
	}
	public static class SelectButton implements ActionListener{
		MainFrame frame;
		public SelectButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.SELECT;
		}
	}
	public static class RotateButton implements ActionListener{
		MainFrame frame;
		public RotateButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.ROTATE;
		}
	}
	public static class ScaleButton implements ActionListener{
		MainFrame frame;
		public ScaleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.SCALE;
		}
	}
	

}
