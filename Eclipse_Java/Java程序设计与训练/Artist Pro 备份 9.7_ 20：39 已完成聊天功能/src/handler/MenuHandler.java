package handler;
import frame.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
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
	public static class Save implements ActionListener{
		MainFrame frame;
		public Save(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File newFile=fc.getSelectedFile();
				try{
				FileOutputStream fo=new FileOutputStream(newFile);
				ObjectOutputStream oo=new ObjectOutputStream(fo);
				oo.writeObject(frame.shapeProArray);
				}catch(Exception ex){System.out.print(ex);}
			}
			
		}
	}
	public static class Open implements ActionListener{
		MainFrame frame;
		public Open(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JFileChooser fc=new JFileChooser(".");
			if(fc.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
				File newFile=fc.getSelectedFile();
				try{
				FileInputStream fi=new FileInputStream(newFile);
				ObjectInputStream oi=new ObjectInputStream(fi);
				System.out.println(newFile);
				ArrayList<ShapePro> newShapeProArray=(ArrayList<ShapePro>)(oi.readObject());
				System.out.println(frame.shapeProArray.size());
				frame.shapeProArray.clear();
				frame.shapeProArray.ensureCapacity(newShapeProArray.size());
				for(int i=0;i<newShapeProArray.size();i++){
					frame.shapeProArray.add(newShapeProArray.get(i));
					System.out.println("outputint");
				}
				frame.canvas.repaint();
				}catch(Exception ex){System.out.println(ex);}
			}
			
		}
	}
	public static class Create implements ActionListener{
		MainFrame frame;
		public Create(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.shapeProArray.clear();
			frame.canvas.repaint();
		}
	}
	public static class CreateServer implements ActionListener{
		MainFrame frame;
		public CreateServer(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			CreateServerDialog createServerDialog=new CreateServerDialog(frame);
		}
	}
	public static class ConnectToServer implements ActionListener{
		MainFrame frame;
		public ConnectToServer(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			ConnectToServerDialog connectToServerDialog=new ConnectToServerDialog(frame);
		}
	}
}
