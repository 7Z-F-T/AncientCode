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
			frame.currentPaintProperty.transformable=false;
		}
	}
	public static class RoundRectangleButton implements ActionListener{
		MainFrame frame;
		public RoundRectangleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.ROUNDRECTANGLE;
			frame.currentPaintProperty.transformable=false;
		}
	}
	public static class LineButton implements ActionListener{
		MainFrame frame;
		public LineButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.LINE;
			frame.currentPaintProperty.transformable=false;
		}
	}
	public static class EllipseButton implements ActionListener{
		MainFrame frame;
		public EllipseButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.ELLIPSE;
			frame.currentPaintProperty.transformable=false;
		}
	}
	public static class SelectButton implements ActionListener{
		MainFrame frame;
		public SelectButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.SELECT;
			frame.currentPaintProperty.transformable=false;
		}
	}
	public static class RotateButton implements ActionListener{
		MainFrame frame;
		public RotateButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.ROTATE;
			frame.currentPaintProperty.transformable=true;
		}
	}
	public static class ScaleButton implements ActionListener{
		MainFrame frame;
		public ScaleButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.SCALE;
			frame.currentPaintProperty.transformable=true;
		}
	}
	public static class ShearButton implements ActionListener{
		MainFrame frame;
		public ShearButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.SHEAR;
			frame.currentPaintProperty.transformable=true;
		}
	}
	public static class TextButton implements ActionListener{
		MainFrame frame;
		public TextButton(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.currentFunction=Function.TEXT;
			frame.currentPaintProperty.transformable=false;
		}
	}
	

}
