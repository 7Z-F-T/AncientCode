package handler;
import frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public abstract class MenuHandler /*implements ActionListener*/{
	public static class Exit implements ActionListener{
		MainFrame frame;
		public Exit(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	public static class Mac implements ActionListener{
		MainFrame frame;
		public Mac(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	public static class Metal implements ActionListener{
		MainFrame frame;
		public Metal(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	public static class Motif implements ActionListener{
		MainFrame frame;
		public Motif(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	
	public static class Windows implements ActionListener{
		MainFrame frame;
		public Windows(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
}
